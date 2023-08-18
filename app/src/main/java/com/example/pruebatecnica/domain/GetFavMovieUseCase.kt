package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository
import com.example.pruebatecnica.data.model.Movie

class GetFavMovieUseCase() {

    private val repository = MovieRepository()
    suspend operator fun invoke(): List<Movie> {
        val movies = repository.getAllMoviesFromDatabase()
        return movies
    }

    suspend fun invokeInsertFav(movie: Movie, like: Boolean) {
        repository.insertFavMoviesFromDatabase(movie, like)
    }
}