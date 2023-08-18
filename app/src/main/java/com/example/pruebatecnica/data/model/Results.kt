package com.example.pruebatecnica.data.model


data class Results(
    //póster, título, resumen de la película, y la
    //calificación de ésta.
    var posterPath: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var voteAverage: Double? = null,
)