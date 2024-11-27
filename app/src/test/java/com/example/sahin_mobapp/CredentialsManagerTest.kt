package com.example.sahin_mobapp

import com.example.sahin_mobapp.CredentialsManager
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CredentialsManagerTest {

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenWrongFormatEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("wrongEmailFormat")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("password123")
        assertEquals(true, isPasswordValid)
    }
}
