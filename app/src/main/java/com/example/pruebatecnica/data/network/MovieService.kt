package com.example.pruebatecnica.data.network

import android.util.Log
import com.example.pruebatecnica.MOVIE_KEY_TOKEN
import com.example.pruebatecnica.core.RetrofitHelper.retrofitService
import com.example.pruebatecnica.data.dto.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    suspend fun getMovies(): List<MovieDTO> {
        return withContext(Dispatchers.IO) {
            val response = retrofitService.getAllMovies(MOVIE_KEY_TOKEN)
            response.body()!!.results
        }
    }
}