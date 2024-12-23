package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private val emailField: TextInputEditText
        get() = findViewById(R.id.emailInput)

    private val passwordField: TextInputEditText
        get() = findViewById(R.id.passwordInput)

    private val emailLayout: TextInputLayout
        get() = findViewById(R.id.emailInputLayout)

    private val passwordLayout: TextInputLayout
        get() = findViewById(R.id.passwordInputLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        findViewById<TextView>(R.id.logInText).setOnClickListener {
            navigateToLogin()
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.nextButton).setOnClickListener {
            handleRegistration()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun handleRegistration() {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        clearErrors()

        when {
            email.isEmpty() -> emailLayout.error = "Email cannot be empty"
            !CredentialsManager.isEmailValid(email) -> emailLayout.error = "Invalid email format"
            CredentialsManager.isEmailAlreadyUsed(email) -> emailLayout.error = "Email already registered"
            password.isEmpty() -> passwordLayout.error = "Password cannot be empty"
            !CredentialsManager.isValidPassword(password) -> passwordLayout.error = "Password must be at least 8 characters"
            else -> {
                if (CredentialsManager.registerUser(email, password)) {
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                } else {
                    Toast.makeText(this, "Error creating account", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearErrors() {
        emailLayout.error = null
        passwordLayout.error = null
    }
}
