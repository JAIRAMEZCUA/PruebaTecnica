package com.example.pruebatecnica.data.network

import com.example.pruebatecnica.MOVIE_KEY_TOKEN
import com.example.pruebatecnica.data.dto.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api: ApiService) {
    suspend fun getMovies(): List<MovieDTO> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMovies(MOVIE_KEY_TOKEN)
            response.body()!!.results
        }
    }
}