package com.example.sahin_mobapp

class CredentialsManager {


    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }
}
