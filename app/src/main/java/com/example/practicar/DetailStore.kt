package com.example.practicar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.pruebatecnica.databinding.ActivityDetailStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class DetailStore : AppCompatActivity() {

    lateinit var binding: ActivityDetailStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            getUser().collect { settings ->

                //TODO ES IMPORTANTE QUE EL SETEO ESTE EN EL HILO PRINCIPAL
                withContext(Dispatchers.Main) {
                    binding.tvNameSave.text = settings.name
                    binding.numId.text = settings.id.toString().carita()
                    binding.numId.changeSize(30f)
                    if (binding.numId.isNull()) {
                        Log.d("ES NULO", "ES NULO")
                    }
                    toast(settings.name)
                }
            }
        }


        //Destructuracion
        val (id, name) = FriendsClass(5, "JUAN")
        toast(name)


        //Strings son INMUTABLE //CADA QUE SE MODIFICA CREA UN NUEVA INSTANCIA
        //Strings builder cambian de contenido sin crear nuevas instancias
        //Las data clases -->
//        Inmutabilidad: Las clases de datos proporcionan una función copy() que te permite
        //        crear copias modificadas de objetos, lo que facilita la creación de versiones inmutables
        //        con valores alterados.

        val a = friendFirst.copy(name = "CARLOS")
        toast(a.name)


        val date = Date()
        val date2: Date?
        date2 = null
        toast(date.size().toString())
        toast(date2.size().toString())

    }

    lateinit var friendFirst: FriendsClass
    private fun getUser() = dataStore.data.map { pref ->
        friendFirst = FriendsClass(
            id = pref[intPreferencesKey("KEY_ID")] ?: 0,
            name = pref[stringPreferencesKey("KEY")] ?: ""
        )
        friendFirst
    }
}