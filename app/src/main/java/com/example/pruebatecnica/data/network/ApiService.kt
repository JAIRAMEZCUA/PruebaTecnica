package com.example.pruebatecnica.data.network

import com.example.pruebatecnica.GET_ALL_MOVIES
import com.example.pruebatecnica.data.dto.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(GET_ALL_MOVIES)
    suspend fun getAllMovies(@Query("api_key") api: String): Response<ResponseModel>
}