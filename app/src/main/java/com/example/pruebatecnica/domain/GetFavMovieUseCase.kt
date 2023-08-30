package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository
import com.example.pruebatecnica.data.model.Movie
import javax.inject.Inject

class GetFavMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> {
        return repository.getAllMoviesFromDatabase()
    }

    suspend fun invokeInsertFav(movie: Movie, like: Boolean) {
        repository.insertFavMoviesFromDatabase(movie, like)
    }
}