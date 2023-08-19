package com.example.pruebatecnica.ui.view.auth

import com.google.firebase.auth.AuthCredential

sealed class LoginStatus {
    class Success(val data: String?) : LoginStatus()
    class Loading : LoginStatus()
    class Error(val messageId: String) : LoginStatus()
}