package com.example.pruebatecnica.domain

import com.example.pruebatecnica.data.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getAllMovies()
}