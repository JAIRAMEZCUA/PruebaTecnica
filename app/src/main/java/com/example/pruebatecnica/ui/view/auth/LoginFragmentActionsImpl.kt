package com.example.pruebatecnica.ui.view.auth

import androidx.navigation.NavController
import com.example.pruebatecnica.R

class LoginFragmentActionsImpl(
    private val viewModel: AuthViewModel,
) : LoginFragmentActions {

    override fun onRegisterButtonClick(navController: NavController) {
        navController.navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    override fun onLoginFieldsValidated(email: String, password: String) {
        viewModel.login(email, password)
    }
}
