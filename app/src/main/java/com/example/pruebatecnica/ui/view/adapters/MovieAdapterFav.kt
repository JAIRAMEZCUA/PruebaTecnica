package com.example.pruebatecnica.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pruebatecnica.BASE_URL_IMG
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.databinding.MovieListItemBinding


class MovieAdapterFav : ListAdapter<Movie, MovieAdapterFav.MovieViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (Movie) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieListItemBinding
            .inflate(LayoutInflater.from(parent.context))
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        dogViewHolder: MovieViewHolder,
        position: Int
    ) {
        val movie = getItem(position)
        if (movie.favorite)
            dogViewHolder.bind(movie)
    }

    inner class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieListItemLayout.setOnClickListener {
                onItemClickListener?.invoke(movie)
            }
            binding.movieImage.load(BASE_URL_IMG + movie.posterPath)
        }
    }
}
