package com.example.pruebatecnica.data.dto

import com.google.gson.annotations.SerializedName


data class MovieDTO(
    //póster, título, resumen de la película, y la
    //calificación de ésta.
    @SerializedName("id") var id: Int? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
)