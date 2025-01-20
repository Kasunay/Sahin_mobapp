package com.example.sahin_mobapp

object CredentialsManager {

    private val users: MutableMap<String, String> = mutableMapOf()

    private val emailRegex = Regex(
        "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
    )

    fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun isEmailAlreadyUsed(email: String): Boolean {
        return users.containsKey(email.lowercase())
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun registerUser(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        return if (isEmailAlreadyUsed(normalizedEmail)) {
            false
        } else {
            users[normalizedEmail] = password
            true
        }
    }

    fun validateLogin(email: String, password: String): Boolean {
        return users[email.lowercase()] == password
    }

    fun isHardcodedCredentials(email: String, password: String): Boolean {
        return email == "test@te.st" && password == "1234"
    }
}