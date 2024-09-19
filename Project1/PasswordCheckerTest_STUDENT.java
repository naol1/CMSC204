import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordCheckerTest_STUDENT {

    @Before
    public void setUp() throws Exception {
        // Any setup tasks (if needed)
    }

    @After
    public void tearDown() throws Exception {
        // Any cleanup tasks (if needed)
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for the second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        // Test case for valid length
        try {
            assertTrue(PasswordCheckerUtility.isValidLength("Passwd"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for too short password
        Exception exception = assertThrows(LengthException.class, () -> {
            PasswordCheckerUtility.isValidLength("Pass");
        });
        assertEquals("The password must be at least 6 characters long.", exception.getMessage());
    }

    /**
     * Test if the password has at least one uppercase alpha character.
     * This test should throw a NoUpperAlphaException for the second case.
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        // Test case for valid password with uppercase
        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Password123!"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for missing uppercase letter
        Exception exception = assertThrows(NoUpperAlphaException.class, () -> {
            PasswordCheckerUtility.hasUpperAlpha("password123!");
        });
        assertEquals("The password must contain at least one uppercase alphabetic character.", exception.getMessage());
    }

    /**
     * Test if the password has at least one lowercase alpha character.
     * This test should throw a NoLowerAlphaException for the second case.
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        // Test case for valid password with lowercase
        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha("Password123!"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for missing lowercase letter
        Exception exception = assertThrows(NoLowerAlphaException.class, () -> {
            PasswordCheckerUtility.hasLowerAlpha("PASSWORD123!");
        });
        assertEquals("The password must contain at least one lowercase alphabetic character.", exception.getMessage());
    }

    /**
     * Test if the password is weak (between 6-9 characters long).
     * This test should throw a WeakPasswordException for the second case.
     */
    @Test
    public void testIsWeakPassword() {
        // Test case for valid password that's not weak
        try {
            assertFalse(PasswordCheckerUtility.isWeakPassword("Password123!"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for weak password
        Exception exception = assertThrows(WeakPasswordException.class, () -> {
            PasswordCheckerUtility.isWeakPassword("Pass12!");
        });
        assertEquals("The password is weak; it must be more than 9 characters long.", exception.getMessage());
    }

    /**
     * Test if the password has more than 2 of the same character in sequence.
     * This test should throw an InvalidSequenceException for the second case.
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        // Test case for valid password without repeating sequence
        try {
            assertTrue(PasswordCheckerUtility.noSameCharInSequence("Password123!"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for invalid sequence
        Exception exception = assertThrows(InvalidSequenceException.class, () -> {
            PasswordCheckerUtility.noSameCharInSequence("Passssword123!");
        });
        assertEquals("The password must not contain more than two of the same character in sequence.", exception.getMessage());
    }

    /**
     * Test if the password has at least one digit.
     * One test should throw a NoDigitException.
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        // Test case for valid password with digit
        try {
            assertTrue(PasswordCheckerUtility.hasDigit("Password123!"));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }

        // Test case for missing digit
        Exception exception = assertThrows(NoDigitException.class, () -> {
            PasswordCheckerUtility.hasDigit("Password!");
        });
        assertEquals("The password must contain at least one digit.", exception.getMessage());
    }

    /**
     * Test correct passwords. This test should not throw an exception.
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Password123!"));
        } catch (Exception e) {
            fail("No exception should be thrown for a valid password");
        }
    }

    /**
     * Test the invalidPasswords method.
     * Check the results of the ArrayList of Strings returned by the validPasswords method.
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> passwordsArray = new ArrayList<>(Arrays.asList(
            "334455BB",  // No lowercase
            "Im2cool4U", // No special character
            "Hello123",  // No special character
            "AAAA123!"   // Invalid sequence
        ));

        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);

        assertEquals(4, invalidPasswords.size());
        assertEquals("334455BB: The password must contain at least one lowercase alphabetic character.", invalidPasswords.get(0));
        assertEquals("Im2cool4U: The password must contain at least one special character.", invalidPasswords.get(1));
        assertEquals("Hello123: The password must contain at least one special character.", invalidPasswords.get(2));
        assertEquals("AAAA123!: The password must not contain more than two of the same character in sequence.", invalidPasswords.get(3));
    }
}
