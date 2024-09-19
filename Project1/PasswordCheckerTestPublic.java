import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PasswordCheckerTestPublic {
    ArrayList<String> passwordsArray;
    String password = "Hello";
    String passwordConfirm = "hello";
    String allCaps = "HELLO";
    String withDigit = "Hello6";

    @BeforeEach
    void setUp() throws Exception {
        String[] p = {"334455BB", "Im2cool4U", withDigit};
        passwordsArray = new ArrayList<String>();
        passwordsArray.addAll(Arrays.asList(p));
    }

    @AfterEach
    void tearDown() throws Exception {
        passwordsArray = null;
    }

    @Test
    void testComparePasswords() {
        // Correct message should be "Passwords do not match."
        Throwable exception = assertThrows(UnmatchedException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.comparePasswords(password, passwordConfirm);
            }
        });
        assertEquals("Passwords do not match.", exception.getMessage());
    }

    @Test
    void testComparePasswordsWithReturn() {
        assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
        assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
    }

    @Test
    void testUpperAlpha() {
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
        } catch (NoUpperAlphaException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testIsValidLength() {
        // Correct message should be "The password must be at least 6 characters long."
        Throwable exception = assertThrows(LengthException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                PasswordCheckerUtility.isValidLength(password);
            }
        });
        assertEquals("The password must be at least 6 characters long.", exception.getMessage());
    }

    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
        assertEquals(3, results.size());

        // Ensure messages match those from PasswordCheckerUtility
        assertEquals("334455BB: The password must contain at least one lowercase alphabetic character.", results.get(0));
        assertEquals("Im2cool4U: The password must contain at least one special character.", results.get(1));
    }
}
