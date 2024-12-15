package com.example.sahin_mobapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {

    private val fullNameInputLayout: TextInputLayout by lazy { findViewById(R.id.fullNameInputLayout) }
    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }
    private val phoneInputLayout: TextInputLayout by lazy { findViewById(R.id.phoneInputLayout) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }
    private val termsCheckbox: com.google.android.material.checkbox.MaterialCheckBox by lazy { findViewById(R.id.termsCheckbox) }
    private val nextButton: android.widget.Button by lazy { findViewById(R.id.nextButton) }
    private val logInText: TextView by lazy { findViewById(R.id.logInText) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        logInText.setOnClickListener {
            navigateToLogin()
        }

        nextButton.setOnClickListener {
            registerAccount()
        }
    }

    private fun registerAccount() {
        val fullName = fullNameInputLayout.editText?.text.toString()
        val email = emailInputLayout.editText?.text.toString()
        val phone = phoneInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 8) {
            passwordInputLayout.error = "Password must be at least 8 characters long"
            return
        } else {
            passwordInputLayout.error = null
        }

        if (!termsCheckbox.isChecked) {
            Toast.makeText(this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show()
            return
        }

        val registrationResult = CredentialsManager.register(email, password)

        if (registrationResult == "Registration successful.") {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

            navigateToLogin()
        } else {
            Toast.makeText(this, registrationResult, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
