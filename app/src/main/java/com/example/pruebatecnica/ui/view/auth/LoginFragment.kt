package com.example.pruebatecnica.ui.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginFragment : Fragment() {
    private val loginFragmentActions: LoginFragmentActions by inject()
    private lateinit var binding: FragmentLoginBinding
    private val navController: NavController by inject { parametersOf(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.loginRegisterButton.setOnClickListener {
            loginFragmentActions.onRegisterButtonClick(navController)
        }
        binding.loginButton.setOnClickListener {
            validateFields()
        }
        return binding.root
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

interface LoginFragmentActions {
    fun onRegisterButtonClick(navController: NavController)
    fun onLoginFieldsValidated(email: String, password: String)
}
