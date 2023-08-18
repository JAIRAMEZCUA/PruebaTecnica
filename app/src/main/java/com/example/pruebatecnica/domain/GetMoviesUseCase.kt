package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository
import com.example.pruebatecnica.data.model.Movie

class GetMoviesUseCase {
    private val repository = MovieRepository()
    suspend operator fun invoke() = repository.getAllMovies()

}