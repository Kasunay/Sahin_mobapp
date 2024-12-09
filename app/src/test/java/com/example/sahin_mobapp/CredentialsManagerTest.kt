import com.example.sahin_mobapp.CredentialsManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CredentialsManagerTest {

    private lateinit var credentialsManager: CredentialsManager


    @Test
    fun testIsEmailValid() {
        assertFalse(credentialsManager.isEmailValid(""))
        assertFalse(credentialsManager.isEmailValid("invalidemail"))
        assertTrue(credentialsManager.isEmailValid("valid.email@example.com"))
    }

    @Test
    fun testIsPasswordValid() {
        assertFalse(credentialsManager.isPasswordValid(""))
        assertTrue(credentialsManager.isPasswordValid("validPassword123"))
        assertFalse(credentialsManager.isPasswordValid("short"))
    }

    @Test
    fun testRegister() {
        assertTrue(credentialsManager.register("test@te.st", "password123"))
        assertFalse(credentialsManager.register("test@te.st", "newPassword123"))
        assertFalse(credentialsManager.register("TEST@TE.ST", "password123"))
        assertFalse(credentialsManager.register("invalidemail.com", "password123"))
        assertFalse(credentialsManager.register("valid@te.st", "short"))
    }

    @Test
    fun testAuthenticate() {
        credentialsManager.register("test@te.st", "password123")
        assertTrue(credentialsManager.authenticate("test@te.st", "password123"))
        assertFalse(credentialsManager.authenticate("test@te.st", "wrongPassword"))
        assertFalse(credentialsManager.authenticate("nonexistent@te.st", "password123"))
    }
}

