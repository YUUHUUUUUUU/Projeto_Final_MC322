package src;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    private UserManager userManager;
    private final String TEST_XML_FILE = "users.xml";

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
        // Ensure we start with a clean slate before every test
        deleteXmlFile();
    }

    @AfterEach
    void tearDown() {
        // Clean up the file after the test finishes so we don't leave trash in the project folder
        //deleteXmlFile();
    }

    private void deleteXmlFile() {
        File file = new File(TEST_XML_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    // --- Testing signIn ---

    @Test
    void testSignIn_Success() {
        // Should run without throwing any exceptions and successfully create the file
        assertDoesNotThrow(() -> userManager.signIn("Rafael", "rafael123", "ValidP@ss123"));
        
        File file = new File(TEST_XML_FILE);
        assertTrue(file.exists(), "XML file should be created after a successful sign in.");
    }

    @Test
    void testSignIn_DuplicateEmail_ThrowsException() {
        // First sign in works
        userManager.signIn("Rafael", "rafael123", "ValidP@ss123");

        // Second sign in with the exact same prefix should fail
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.signIn("Another Name", "rafael123", "AnotherP@ss123");
        });
        
        assertEquals("An account with this email already exists.", exception.getMessage());
    }

    @Test
    void testSignIn_InvalidEmail_ThrowsException() {
        // Passes an email prefix with special characters, which should fail validation inside UserManager
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.signIn("Rafael", "rafael.123", "ValidP@ss123");
        });
        
        assertEquals("Email prefix can only contain letters and numbers.", exception.getMessage());
    }

    @Test
    void testSignIn_InvalidName_ThrowsException() {
        // Passes a name with invalid characters
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.signIn("Rafael 123", "rafael123", "ValidP@ss123");
        });
        
        assertEquals("Name contains invalid special characters.", exception.getMessage());
    }

    // --- Testing login ---

    @Test
    void testLogin_Success() {
        // Setup: register a user first
        userManager.signIn("Rafael Ruas", "rafael123", "ValidP@ss123");

        // Action: try to log in
        User loggedInUser = userManager.login("rafael123@dac.unicamp.br", "ValidP@ss123");

        // Assertion
        assertNotNull(loggedInUser);
        assertEquals("Rafael Ruas", loggedInUser.getName());
        assertEquals("rafael123@dac.unicamp.br", loggedInUser.getEmail());
    }

    @Test
    void testLogin_WrongPassword_ThrowsException() {
        // Setup: register a user
        userManager.signIn("Rafael", "rafael123", "ValidP@ss123");

        // Action & Assertion: Try to log in with a bad password
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.login("rafael123@dac.unicamp.br", "WrongP@ssword123");
        });
        
        assertEquals("Invalid email or password.", exception.getMessage());
    }

    @Test
    void testLogin_UserNotFound_ThrowsException() {
        // Setup: register a user
        userManager.signIn("Rafael", "rafael123", "ValidP@ss123");

        // Action & Assertion: Try an email that doesn't exist in the XML
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.login("nobody@dac.unicamp.br", "ValidP@ss123");
        });
        
        assertEquals("Invalid email or password.", exception.getMessage());
    }

    @Test
    void testLogin_NoDatabaseFile_ThrowsException() {
        // We purposely don't create any users, so users.xml doesn't exist
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManager.login("rafael123@dac.unicamp.br", "ValidP@ss123");
        });
        
        assertEquals("No registered users found.", exception.getMessage());
    }
}