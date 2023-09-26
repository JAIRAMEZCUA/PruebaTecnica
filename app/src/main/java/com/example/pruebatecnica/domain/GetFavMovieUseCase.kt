package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository
import com.example.pruebatecnica.data.model.Movie


class GetFavMovieUseCase constructor(private var repository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> {
        return repository.getAllMoviesFromDatabase()
    }

    suspend fun invokeInsertFav(movie: Movie, like: Boolean) {
        repository.insertFavMoviesFromDatabase(movie, like)
    }
}