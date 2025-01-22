package com.example.sahin_mobapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.logInText).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<View>(R.id.nextButton).setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        clearErrors()

        when {
            email.isEmpty() -> emailLayout.error = "Email cannot be empty"
            !credentialsManager.isEmailValid(email) -> emailLayout.error = "Invalid email format"
            credentialsManager.isEmailAlreadyUsed(email) -> emailLayout.error = "Email already registered"
            password.isEmpty() -> passwordLayout.error = "Password cannot be empty"
            !credentialsManager.isValidPassword(password) -> passwordLayout.error = "Password must be at least 8 characters"
            else -> {
                if (credentialsManager.registerUser(email, password)) {
                    Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                } else {
                    Toast.makeText(context, "Error creating account", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearErrors() {
        emailLayout.error = null
        passwordLayout.error = null
    }
}