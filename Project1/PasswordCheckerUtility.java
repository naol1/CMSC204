import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
    public PasswordCheckerUtility() {}

    // Compare passwords, throwing an exception if they do not match
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!password.equals(passwordConfirm)) 
            throw new UnmatchedException("Passwords do not match.");
    }

    // Compare passwords and return true/false instead of throwing an exception
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    // Get a list of invalid passwords with reasons for invalidity
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                String message = password + ": " + e.getMessage();
                invalidPasswords.add(message);
            }
        }
        return invalidPasswords;
    }

    // Check if the password has between 6 and 9 characters
    public static boolean hasBetweenSixAndNineChars(String password) {
        return (password.length() >= 6 && password.length() <= 9);
    }

    // Check if the password has at least one digit
    public static boolean hasDigit(String password) throws NoDigitException {
        Pattern digitPattern = Pattern.compile("\\d");
        Matcher matcher = digitPattern.matcher(password);
        if (matcher.find()) return true;

        throw new NoDigitException("The password must contain at least one digit.");
    }

    // Check if the password has at least one special character
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
        Pattern specialPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = specialPattern.matcher(password);
        if (matcher.find()) return true;

        throw new NoSpecialCharacterException("The password must contain at least one special character.");
    }

    // Check if the password has at least one uppercase letter
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        Pattern upperPattern = Pattern.compile("[A-Z]");
        Matcher matcher = upperPattern.matcher(password);
        if (matcher.find()) return true;

        throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character.");
    }

    // Check if the password has at least one lowercase letter
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        Pattern lowerPattern = Pattern.compile("[a-z]");
        Matcher matcher = lowerPattern.matcher(password);
        if (matcher.find()) return true;

        throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character.");
    }

    // Check if the password is at least 6 characters long
    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() >= 6) return true;

        throw new LengthException("The password must be at least 6 characters long.");
    }

    // Check if the password is valid by applying all checks
    public static boolean isValidPassword(String password)
            throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException,
            NoSpecialCharacterException, InvalidSequenceException {
        return isValidLength(password) &&
               hasLowerAlpha(password) &&
               hasUpperAlpha(password) &&
               hasSpecialChar(password) &&
               hasDigit(password) &&
               noSameCharInSequence(password);
    }

    // Check if the password is weak (between 6 and 9 characters)
    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (hasBetweenSixAndNineChars(password))
            throw new WeakPasswordException("The password is weak; it must be more than 9 characters long.");
        return false;
    }

    // Check if there are more than two consecutive same characters
    public static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            char currentChar = password.charAt(i);
            char nextChar = password.charAt(i + 1);
            char nextNextChar = password.charAt(i + 2);
            if (currentChar == nextChar && nextChar == nextNextChar) {
                throw new InvalidSequenceException("The password must not contain more than two of the same character in sequence.");
            }
        }
        return true;
    }
}
