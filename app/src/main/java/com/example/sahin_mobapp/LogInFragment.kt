package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogInFragment : Fragment() {

    private val credentialsManager = CredentialsManager

    private val emailField: TextInputEditText
        get() = requireView().findViewById(R.id.emailInput)

    private val passwordField: TextInputEditText
        get() = requireView().findViewById(R.id.passwordInput)

    private val emailLayout: TextInputLayout
        get() = requireView().findViewById(R.id.emailInputLayout)

    private val passwordLayout: TextInputLayout
        get() = requireView().findViewById(R.id.passwordInputLayout)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.registerNowText).setOnClickListener {
            navigateToRegister()
        }

        view.findViewById<View>(R.id.nextButton).setOnClickListener {
            handleLogin()
        }
    }

    private fun navigateToRegister() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun handleLogin() {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        clearErrors()

        when {
            email.isEmpty() -> emailLayout.error = "Email cannot be empty"
            !credentialsManager.isEmailValid(email) -> emailLayout.error = "Invalid email format"
            password.isEmpty() -> passwordLayout.error = "Password cannot be empty"
            credentialsManager.isHardcodedCredentials(email, password) -> navigateToMain()
            credentialsManager.validateLogin(email, password) -> navigateToMain()
            else -> Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearErrors() {
        emailLayout.error = null
        passwordLayout.error = null
    }

    private fun navigateToMain() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
