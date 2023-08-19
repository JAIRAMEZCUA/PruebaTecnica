package com.example.pruebatecnica.ui.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var loginFragmentActions: LoginFragmentActions
    private lateinit var binding: FragmentLoginBinding

    interface LoginFragmentActions {
        fun onLoginFieldsValidated(email: String, password: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentActions = try {
            context as LoginFragmentActions
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement LoginFragmentActions")
        }
    }

    private fun validateFields() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""

        val email = binding.emailEdit.text.toString()

        if (!isValidEmail(email)) {
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.password_must_not_be_empty)
            return
        }

        loginFragmentActions.onLoginFieldsValidated(email, password)
    }

}