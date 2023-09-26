package com.example.pruebatecnica.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebatecnica.data.database.dao.MovieDao
import com.example.pruebatecnica.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
