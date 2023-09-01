package com.example.notificaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificaciones.sealed.ErrorDate
import com.example.notificaciones.sealed.Game
import com.example.practicar.toast
import com.example.pruebatecnica.R

class NotificationsActivity : AppCompatActivity() {


    val games = listOf<Game>(
        Game(1, "DEADMOUSE", ErrorDate.SinError),
        Game(2, "FIFA", ErrorDate.RayadoError),
        Game(3, "FIFA", ErrorDate.versionError("1.2"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        games.forEach {
            when (it.errorDate) {
                ErrorDate.InternetError -> toast("ERROR")
                ErrorDate.RayadoError -> toast("RAYADO")
                ErrorDate.SinError -> toast("SIN ERROR")
                is ErrorDate.versionError -> (it.errorDate as ErrorDate.versionError).error
            }
        }
    }
}