package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository

class GetMoviesUseCase constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getAllMovies()
}