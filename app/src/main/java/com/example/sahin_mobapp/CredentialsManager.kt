package com.example.sahin_mobapp

object CredentialsManager {
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    private val accounts = mutableMapOf<String, String>()

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && EMAIL_REGEX.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.trim().lowercase()

        if (!isValidEmail(normalizedEmail)) {
            return "Invalid email format. Please use a valid email (e.g., user@example.com)."
        }

        if (accounts.containsKey(normalizedEmail)) {
            return "Email is already registered."
        }

        accounts[normalizedEmail] = password
        return "Registration successful."
    }

    fun isValidLogin(email: String, password: String): Boolean {
        val normalizedEmail = email.trim().lowercase()
        return accounts[normalizedEmail] == password
    }

    fun getAllAccounts(): Map<String, String> {
        return accounts
    }
}
