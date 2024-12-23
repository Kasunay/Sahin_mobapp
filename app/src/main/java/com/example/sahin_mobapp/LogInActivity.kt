package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_log_in)

        findViewById<TextView>(R.id.registerNowText).setOnClickListener {
            navigateToRegister()
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.nextButton).setOnClickListener {
            handleLogin()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun handleLogin() {
        val email = emailField.text.toString().trim()
        val password = passwordField.text.toString().trim()

        clearErrors()

        when {
            email.isEmpty() -> emailLayout.error = "Email cannot be empty"
            !CredentialsManager.isEmailValid(email) -> emailLayout.error = "Invalid email format"
            password.isEmpty() -> passwordLayout.error = "Password cannot be empty"
            CredentialsManager.isHardcodedCredentials(email, password) -> navigateToMain()
            CredentialsManager.validateLogin(email, password) -> navigateToMain()
            else -> Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearErrors() {
        emailLayout.error = null
        passwordLayout.error = null
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
