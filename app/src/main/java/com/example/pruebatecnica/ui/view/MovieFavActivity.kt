package com.example.pruebatecnica.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebatecnica.GRID_SPAN_COUNT
import com.example.pruebatecnica.MOVIE_KEY
import com.example.pruebatecnica.databinding.ActivityMovieFavBinding
import com.example.pruebatecnica.ui.view.adapters.MovieAdapter
import com.example.pruebatecnica.ui.viewmodel.MovieFavViewModel

class MovieFavActivity : AppCompatActivity() {
    private val movieViewModel: MovieFavViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMovieFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.moviesRecycler
        recycler.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        val adapter = MovieAdapter()
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
            adapter.submitList(movieList)
        })

        binding.toolbarFav.setOnClickListener {
            Toast.makeText(this, "HOLA", Toast.LENGTH_LONG).show()
        }

    }
}