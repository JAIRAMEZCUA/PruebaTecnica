package com.example.pruebatecnica.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebatecnica.data.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false,
)


fun MovieEntity.toDomain() = Movie(
    id = id,
    posterPath = posterPath,
    title = title,
    overview = overview,
    voteAverage = voteAverage
)

fun Movie.toDatabase() = {
    MovieEntity(
        id = id,
        posterPath = posterPath!!,
        title = title!!,
        overview = overview!!,
        voteAverage = voteAverage!!
    )
}