package com.example.sahin_mobapp

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun emailValidation_returns_false_for_empty_email() {
        assertFalse(credentialsManager.isEmailValid(""))
    }

    @Test
    fun emailValidation_returns_false_for_invalid_email_format() {
        assertFalse(credentialsManager.isEmailValid("invalid-email"))
    }

    @Test
    fun emailValidation_returns_true_for_valid_email() {
        assertTrue(credentialsManager.isEmailValid("test@example.com"))
    }

    @Test
    fun passwordValidation_returns_false_for_empty_password() {
        assertFalse(credentialsManager.isPasswordValid(""))
    }

    @Test
    fun passwordValidation_returns_true_for_non_empty_password() {
        assertTrue(credentialsManager.isPasswordValid("mypassword123"))
    }
}
