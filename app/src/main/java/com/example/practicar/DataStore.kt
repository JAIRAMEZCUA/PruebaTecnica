package com.example.practicar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.pruebatecnica.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Sirve para evitar poner todo el nombre grande del tipo de valor
typealias myMapList = MutableMap<Int, ArrayList<String>>
typealias myFun = (Int, String, myMapList) -> String
//typealias myNestedClass = MyNestedclass.myclassInternal  este es para las inner class

typealias outerClass = OuterClass.InnerClass

class DataStore : AppCompatActivity() {

    lateinit var binding: ActivityDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //La lista no es mutable
        val lista = listOf("JAIR", "JUAN", "CARLOS")

        //si es mutable
        val listaMutable = mutableListOf("JUAN", "ANDRES", "DANAE")
        listaMutable.add("CARMEN")
        listaMutable.add(0, "JAJA")

        for (alumno in lista) {
            Log.d("EJEMPLO", alumno)
        }
        //No es mutable la lista
        lista.plus("DANAE")
        for (alumno in lista.indices) {
            Log.d("EJEMPLOINDICE", alumno.toString())
        }

        for ((indice, name) in lista.withIndex()) {
            Log.d("EJEMPLOINDICE", indice.toString() + "CON EL NOMBRE $name")
        }

        for ((indice, name) in listaMutable.withIndex()) {
            Log.d("EJEMPLOINDICE", "el valor del indice es $indice " + "CON EL NOMBRE $name")
        }

        binding.etName.listenerText {
            toast(it)
        }
        binding.button.setOnClickListener {
            save(binding.etName.text.toString())
            startActivity(Intent(Intent(this, DetailStore::class.java)))
        }

    }

    private fun save(text: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            saveValue(text)
        }
    }

    private suspend fun saveValue(text: String?) {
        dataStore.edit { pref ->
            pref[intPreferencesKey("KEY_ID")] = 1
            pref[stringPreferencesKey("KEY")] = text ?: ""
        }
    }
}


/*
* LAS INNER CLASS  PUEDEN ACCEDER A SU CLASE DONDE ESTA INTANCIADA TANTO A SUS FUNCIONES Y PROPIEDADES
*
* */