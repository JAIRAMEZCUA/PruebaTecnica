package com.example.pruebatecnica.ui.view.auth

class SignUpFragmentActionsImpl(
    private val viewModel: AuthViewModel
) : SignUpFragmentActions {
    override fun onSignUpFieldsValidated(
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModel.signUp(email, password)
    }

}
