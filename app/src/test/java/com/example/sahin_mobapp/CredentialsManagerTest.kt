package com.example.sahin_mobapp

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager
        val isEmailValid = credentialsManager.isEmailValid("")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager
        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")
        assertFalse(isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertTrue(isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager
        val isPasswordValid = credentialsManager.isValidPassword("")
        assertFalse(isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager
        val isPasswordValid = credentialsManager.isValidPassword("password123")
        assertTrue(isPasswordValid)
    }
}
