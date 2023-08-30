package com.example.pruebatecnica.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebatecnica.GRID_SPAN_COUNT
import com.example.pruebatecnica.MOVIE_KEY
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.databinding.ActivityMovieFavBinding
import com.example.pruebatecnica.ui.view.adapters.MovieAdapterFav
import com.example.pruebatecnica.ui.viewmodel.MovieFavViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MovieFavActivity : AppCompatActivity() {
    private val movieViewModel: MovieFavViewModel by viewModels()
    private val movies: ArrayList<Movie> = arrayListOf()
    private val adapter = MovieAdapterFav()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMovieFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.moviesRecycler
        recycler.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)

        adapter.setOnItemClickListener {
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, it)
            startActivity(intent)
        }
        recycler.adapter = adapter

        movieViewModel.isLoading.observe(this, Observer {
            binding.loadingWheel.isVisible = it
        })
        movieViewModel.movieList.observe(this, Observer { movieList ->
            if (movieList.isEmpty()) {
                Toast.makeText(this, "No hay peliculas favoritas", Toast.LENGTH_LONG).show()
            } else {
                movieList.forEach {
                    if (it.favorite)
                        movies.add(it)
                }
                if (movies.isEmpty()) {
                    Toast.makeText(this, "No hay peliculas favoritas", Toast.LENGTH_LONG).show()
                } else {
                    adapter.submitList(movies)
                }
            }
        })

        binding.searchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(binding.searchMovie.text.toString())
                true
            } else {
                false
            }
        }

    }

    private fun performSearch(text: String) {
        val filtraArray: ArrayList<Movie> = arrayListOf()
        movies.forEach {
            val title = it.title!!
            if (isContains(title, text.trim()))
                filtraArray.add(it)
        }
        adapter.submitList(filtraArray)
    }

    fun isContains(texto: String, cadenaBuscada: String): Boolean {
        // Convertir ambas cadenas a minúsculas (o mayúsculas) antes de la comparación
        val textoLower = texto.lowercase(Locale.getDefault())
        val cadenaBuscadaLower = cadenaBuscada.lowercase(Locale.getDefault())

        // Realizar la comparación
        return textoLower.contains(cadenaBuscadaLower)
    }
}