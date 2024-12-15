import com.example.sahin_mobapp.CredentialsManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialsManagerTest {

    @Test
    fun testEmailValidation() {
        assertTrue(CredentialsManager.isValidEmail("test@example.com"))
        assertEquals(false, CredentialsManager.isValidEmail("invalid-email"))
    }

    @Test
    fun testPasswordValidation() {
        assertTrue(CredentialsManager.isValidPassword("password123"))
        assertEquals(false, CredentialsManager.isValidPassword(""))
    }

    @Test
    fun testRegistration() {
        val email = "user1@example.com"
        val password = "securePass"
        val result = CredentialsManager.register(email, password)

        assertEquals("Registration successful.", result)
        assertEquals(password, CredentialsManager.getAllAccounts()[email])
    }

    @Test
    fun testDuplicateRegistration() {
        val email = "duplicate@example.com"
        val password = "pass123"

        // First registration
        val firstResult = CredentialsManager.register(email, password)
        assertEquals("Registration successful.", firstResult)

        // Attempt to register with the same email again
        val secondResult = CredentialsManager.register(email, "newPass123")
        assertEquals("Email is already registered.", secondResult)
    }

    @Test
    fun testLoginValidation() {
        val email = "login@example.com"
        val password = "mypassword"

        CredentialsManager.register(email, password)

        assertTrue(CredentialsManager.isValidLogin(email, password))

        assertEquals(false, CredentialsManager.isValidLogin(email, "wrongpassword"))
    }
}
