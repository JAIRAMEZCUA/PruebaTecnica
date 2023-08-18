package com.example.pruebatecnica.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    var id: Int? = null,
    var posterPath: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var voteAverage: Double? = null,
    var favorite: Boolean = false
) : Parcelable