package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }
    private val nextButton: View by lazy { findViewById(R.id.nextButton) }
    private val registerNowText: TextView by lazy { findViewById(R.id.registerNowText) } // Register Now text view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        nextButton.setOnClickListener {
            validateCredentials()
        }

        registerNowText.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateCredentials() {
        val email = emailInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()

        var isValid = true

        if (CredentialsManager.isValidEmail(email)) {
            emailInputLayout.error = null
        } else {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        }

        if (CredentialsManager.isValidPassword(password)) {
            passwordInputLayout.error = null
        } else {
            passwordInputLayout.error = "Password cannot be empty"
            isValid = false
        }

        if (isValid && CredentialsManager.isValidLogin(email, password)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }
}
