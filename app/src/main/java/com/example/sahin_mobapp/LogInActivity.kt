package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LogInActivity : AppCompatActivity() {

    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }
    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        findViewById<com.google.android.material.button.MaterialButton>(R.id.nextButton).setOnClickListener {
            validateAndProceed()
        }
    }

    private fun validateAndProceed() {
        val email = emailInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()

        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = if (email.isEmpty()) {
                "Email cannot be empty"
            } else {
                "Invalid email format"
            }
            return
        } else {
            emailInputLayout.error = null
        }

        // Validate password
        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty"
            return
        } else {
            passwordInputLayout.error = null
        }

        // hardcoded credentials
        if (email == "test@te.st" && password == "1234") {
            navigateToMainActivity()
        } else {
            Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
