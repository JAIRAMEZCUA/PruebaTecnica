package com.example.pruebatecnica.core

import com.example.pruebatecnica.BASE_URL
import com.example.pruebatecnica.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}