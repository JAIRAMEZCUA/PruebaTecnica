package com.example.pruebatecnica.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pruebatecnica.data.database.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    suspend fun getAllMoviesFav(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(itemEntity: MovieEntity): Int
}