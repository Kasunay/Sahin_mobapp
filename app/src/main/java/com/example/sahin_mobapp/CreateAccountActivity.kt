package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        credentialsManager = CredentialsManager(this)

        val emailInput: EditText = findViewById(R.id.emailInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)

        val loginButton: TextView = findViewById(R.id.logInText)

        loginButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                val registrationSuccess = credentialsManager.register(email, password)
                if (registrationSuccess) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LogInActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid email format or empty password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}