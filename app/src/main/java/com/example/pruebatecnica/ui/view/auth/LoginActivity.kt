package com.example.pruebatecnica.ui.view.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.ActivityLoginBinding
import com.example.pruebatecnica.ui.view.MoviesMenuActivity

class LoginActivity : AppCompatActivity(), LoginFragment.LoginFragmentActions,
    SignUpFragment.SignUpFragmentActions {


    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.status.observe(this) {
            when (it) {
                is LoginStatus.Error -> {
                    binding.loadingWheel.visibility = View.GONE
                    showErrorDialog(it.messageId)
                }

                is LoginStatus.Loading -> binding.loadingWheel.visibility = View.VISIBLE

                is LoginStatus.Success -> {
                    binding.loadingWheel.visibility = View.GONE
                    val intent = Intent(this, MoviesMenuActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showErrorDialog(messageId: String) {
        AlertDialog.Builder(this)
            .setTitle("ERROR")
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ -> /** Dismiss dialog **/ }
            .create()
            .show()
    }

    override fun onSignUpFieldsValidated(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModel.signUp(email, password)
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    override fun onLoginFieldsValidated(email: String, password: String) {
        viewModel.login(email, password)
    }
}