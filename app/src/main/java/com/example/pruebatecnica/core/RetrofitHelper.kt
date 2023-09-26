package com.example.pruebatecnica.core

import com.example.pruebatecnica.data.network.ApiService
import org.koin.java.KoinJavaComponent.getKoin

object RetrofitHelper {

    val retrofitService: ApiService by lazy {
        getKoin().get<ApiService>()
    }
}