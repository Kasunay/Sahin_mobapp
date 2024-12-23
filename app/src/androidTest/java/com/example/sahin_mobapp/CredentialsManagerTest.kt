package com.example.sahin_mobapp

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager

    @Test
    fun isEmailValid_returnsTrue_forValidEmail() {
        assertTrue(credentialsManager.isEmailValid("valid.email@example.com"))
    }

    @Test
    fun isEmailValid_returnsFalse_forInvalidEmail() {
        assertFalse(credentialsManager.isEmailValid("invalid-email"))
    }

    @Test
    fun isEmailAlreadyUsed_returnsFalse_forNewEmail() {
        assertFalse(credentialsManager.isEmailAlreadyUsed("newuser@example.com"))
    }

    @Test
    fun isEmailAlreadyUsed_returnsTrue_forExistingEmail() {
        credentialsManager.registerUser("existinguser@example.com", "password123")
        assertTrue(credentialsManager.isEmailAlreadyUsed("existinguser@example.com"))
    }

    @Test
    fun isValidPassword_returnsTrue_forValidPassword() {
        assertTrue(credentialsManager.isValidPassword("strongPassword123"))
    }

    @Test
    fun isValidPassword_returnsFalse_forShortPassword() {
        assertFalse(credentialsManager.isValidPassword("123"))
    }

    @Test
    fun registerUser_returnsTrue_forNewEmail() {
        val result = credentialsManager.registerUser("newuser@example.com", "password123")
        assertTrue(result)
    }

    @Test
    fun registerUser_returnsFalse_forDuplicateEmail() {
        credentialsManager.registerUser("duplicate@example.com", "password123")
        val result = credentialsManager.registerUser("duplicate@example.com", "newpassword")
        assertFalse(result)
    }

    @Test
    fun validateLogin_returnsTrue_forCorrectCredentials() {
        credentialsManager.registerUser("loginuser@example.com", "password123")
        val result = credentialsManager.validateLogin("loginuser@example.com", "password123")
        assertTrue(result)
    }

    @Test
    fun validateLogin_returnsFalse_forIncorrectPassword() {
        credentialsManager.registerUser("loginuser@example.com", "password123")
        val result = credentialsManager.validateLogin("loginuser@example.com", "wrongpassword")
        assertFalse(result)
    }

    @Test
    fun validateLogin_returnsFalse_forUnregisteredEmail() {
        val result = credentialsManager.validateLogin("unregistered@example.com", "password123")
        assertFalse(result)
    }

    @Test
    fun isHardcodedCredentials_returnsTrue_forTestTeSt_andPassword1234() {
        val result = credentialsManager.isHardcodedCredentials("test@te.st", "1234")
        assertTrue(result)
    }

    @Test
    fun isHardcodedCredentials_returnsFalse_forIncorrectHardcodedCredentials() {
        val result = credentialsManager.isHardcodedCredentials("test@te.st", "wrongpassword")
        assertFalse(result)
    }
}
