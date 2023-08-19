package com.example.pruebatecnica.ui.view.auth

import android.util.Patterns

fun isValidEmail(email: String?): Boolean {
    return !email.isNullOrEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
}