package com.example.practicar

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.preferencesDataStore
import coil.load
import coil.request.Disposable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


//Permiten extender clases finales y de terceros. FUNCIONES DE EXTENSION

val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCES_NAME")
fun String.carita(): String {
    return "$this :)"
}

fun Activity.changeColor(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Any?.isNull() = this == null

fun TextView.changeSize(tam: Float) {
    return this.setTextSize(tam)
}

fun EditText.listenerText(listener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d("EXAMPLETEST1", p0.toString())
            Log.d("EXAMPLETEST1", p1.toString())
            Log.d("EXAMPLETEST1", p2.toString())
            Log.d("EXAMPLETEST1", p3.toString())
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d("EXAMPLETEST", p0.toString())
            Log.d("EXAMPLETEST", p1.toString())
            Log.d("EXAMPLETEST", p2.toString())
            Log.d("EXAMPLETEST", p3.toString())

        }

        override fun afterTextChanged(p0: Editable?) {
            listener(p0.toString())
        }

    })
}

fun Activity.toast(text: String, timeToast: Int = Toast.LENGTH_SHORT) {
    return Toast.makeText(this, text, timeToast).show()
}

fun ImageView.loadImageCoin(url: String): Disposable {
    if (url.isNotEmpty())
        return this.load(url)
    else
        return this.load("")
}

fun Date?.customFormat(): String? {
    if (this != null) {
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val formatted = SimpleDateFormat(pattern, Locale.getDefault())
        return formatted.format(this)
    } else {
        return null
    }
}

fun Date?.size(): Int = this.customFormat()?.length ?: 0


data class FriendsClass(val id: Int, val name: String)