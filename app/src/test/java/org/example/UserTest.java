package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // Now instantiating User directly since it is no longer abstract
        user = new User("test@dac.unicamp.br", "Test Name");
    }

    // --- Testing setName ---

    @Test
    void testSetName_Success() {
        user.setName("Rafael Ruas");
        assertEquals("Rafael Ruas", user.getName());
    }

    @Test
    void testSetName_SuccessWithTrimming() {
        user.setName("  Rafael Ruas  ");
        assertEquals("Rafael Ruas", user.getName());
    }

    @Test
    void testSetName_Null_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setName(null));
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testSetName_Empty_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setName("   "));
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testSetName_TooLong_ThrowsException() {
        String longName = "A".repeat(51);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.setName(longName));
        assertEquals("Name cannot exceed 50 characters.", exception.getMessage());
    }

    // --- Testing changePassword ---

    @Test
    void testChangePassword_Success() {
        // Valid password: >8 chars, 1 uppercase, 1 lowercase, 1 symbol, no spaces
        user.changePassword("StrongP@ssw0rd!");
        assertEquals("StrongP@ssw0rd!", user.getPassword());
    }

    @Test
    void testChangePassword_Empty_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword(""));
        assertEquals("Password cannot be empty.", exception.getMessage());
    }

    @Test
    void testChangePassword_TooShort_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword("Aa1@"));
        assertEquals("Password must be at least 8 characters long.", exception.getMessage());
    }

    @Test
    void testChangePassword_NoUppercase_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword("weakp@ssw0rd!"));
        assertEquals("Password must contain at least one uppercase letter.", exception.getMessage());
    }

    @Test
    void testChangePassword_NoLowercase_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword("WEAKP@SSW0RD!"));
        assertEquals("Password must contain at least one lowercase letter.", exception.getMessage());
    }

    @Test
    void testChangePassword_NoSymbol_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword("NoSymbolPassword123"));
        assertEquals("Password must contain at least one special symbol.", exception.getMessage());
    }

    @Test
    void testChangePassword_ContainsSpace_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> user.changePassword("Space P@ssword!"));
        assertEquals("Password cannot contain spaces.", exception.getMessage());
    }
}