package com.example.pruebatecnica.data.database

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp

/****
 * Project: List
 * From: com.cursosant.list
 * Created by Alain Nicolás Tello on 02/07/23 at 13:59
 * All rights reserved 2023.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class MovieApplication : Application() {
    companion object {
        lateinit var database: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        database = Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "ListDatabase"
        ).build()
    }
}