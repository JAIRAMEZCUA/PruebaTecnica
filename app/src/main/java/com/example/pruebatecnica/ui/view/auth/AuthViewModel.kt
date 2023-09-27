package com.example.pruebatecnica.ui.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebatecnica.data.model.Movie
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val _status = MutableLiveData<LoginStatus>()
    val status: LiveData<LoginStatus>
        get() = _status

    fun login(email: String, password: String) {
        _status.value = LoginStatus.Loading()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _status.value = LoginStatus.Success(it.result!!.user!!.email)
                } else {
                    _status.value = LoginStatus.Error(it.exception!!.message!!)
                }
            }
    }

    fun signUp(
        email: String, password: String
    ) {
        _status.value = LoginStatus.Loading()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _status.value = LoginStatus.Success(it.result!!.user!!.email)
                } else {
                    _status.value = LoginStatus.Error(it.exception!!.message!!)
                }
            }
    }

}