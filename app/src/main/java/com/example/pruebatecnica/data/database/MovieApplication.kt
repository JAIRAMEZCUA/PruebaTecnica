package com.example.pruebatecnica.data.database

import android.app.Application
import com.example.pruebatecnica.di.viewModelModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(viewModelModule)
        }

        FirebaseApp.initializeApp(this)
    }
}