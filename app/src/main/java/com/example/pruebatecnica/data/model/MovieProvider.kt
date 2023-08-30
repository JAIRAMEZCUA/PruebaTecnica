package com.example.pruebatecnica.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieProvider @Inject constructor() {
    var movies: List<Movie> = emptyList()
}