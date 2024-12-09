package com.example.sahin_mobapp

import android.content.Context
import android.content.SharedPreferences

class CredentialsManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): Boolean {
        val editor = sharedPreferences.edit()
        val existingEmail = sharedPreferences.getString(email, null)
        if (existingEmail != null) {
            return false
        }
        editor.putString(email.lowercase(), password)
        editor.apply()
        return true
    }

    fun authenticate(email: String, password: String): Boolean {
        val storedPassword = sharedPreferences.getString(email.lowercase(), null)
        return storedPassword == password
    }
}
