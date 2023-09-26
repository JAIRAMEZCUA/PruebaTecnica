package com.example.pruebatecnica.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pruebatecnica.GRID_SPAN_COUNT
import com.example.pruebatecnica.MOVIE_KEY
import com.example.pruebatecnica.databinding.ActivityMoviesMenuBinding
import com.example.pruebatecnica.ui.view.adapters.MovieAdapter
import com.example.pruebatecnica.ui.viewmodel.MovieMenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MoviesMenuActivity : AppCompatActivity() {

    private val movieViewModel: MovieMenuViewModel by viewModel()
    private val adapter: MovieAdapter by inject(named("MovieAdapter"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviesMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.moviesRecycler
        recycler.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        adapter.setOnItemClickListener {
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, it)
            startActivity(intent)
        }
        recycler.adapter = adapter
        movieViewModel.isLoading.observe(this) {
            binding.loadingWheel.isVisible = it
        }
        movieViewModel.movieList.observe(this) { movieList ->
            adapter.submitList(movieList)
        }
        binding.toolbarFav.setOnClickListener {
            val intent = Intent(this, MovieFavActivity::class.java)
            startActivity(intent)
        }

    }
}