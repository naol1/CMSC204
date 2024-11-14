import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MorseCodeConverter class provides methods to convert Morse code to English
 * and print the data of a Morse code tree in LNR order for testing purposes.
 * 
 * Author: Naol
 */
public class MorseCodeConverter {
    
    // Static instance of MorseCodeTree used for Morse code conversion
    private static MorseCodeTree codeTree = new MorseCodeTree();
    
    /**
     * Returns a string with all the data in the tree in LNR (Inorder) order
     * with a space between them. This is mainly used for testing purposes to
     * verify the correct construction of the MorseCodeTree.
     * 
     * @return the data in the tree in LNR order separated by a space.
     */
    public static String printTree() {
        String data = "";
        ArrayList<String> list = new ArrayList<>();
        
        // Convert the tree data to an ArrayList in LNR order
        list = codeTree.toArrayList();
        
        // Construct the output string with data separated by spaces
        for (int i = 0; i < list.size(); i++) {
            data += list.get(i);
        }
        
        // Trim any trailing spaces from the result
        data = data.trim();
        return data;
    }
    
    /**
     * Converts a given Morse code string into English. Each letter in Morse
     * code is delimited by a space (' '), and each word is delimited by a '/'.
     * 
     * Example: 
     * Input Morse code: ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * Output: "hello world"
     * 
     * @param morseCode - the Morse code string to be converted.
     * @return the English translation of the given Morse code.
     */
    public static String convertToEnglish(String morseCode) {
        String[] codeWords; // Array to hold Morse code words
        String[] codeLetters; // Array to hold individual Morse code letters
        String result = ""; // Resulting English translation
        
        // Split the input Morse code by words (delimited by '/')
        codeWords = morseCode.split("/");
        
        // Iterate over each word and convert it to English
        for (int i = 0; i < codeWords.length; i++) {
            codeWords[i] = codeWords[i].trim(); // Remove leading/trailing spaces
            codeLetters = codeWords[i].split(" "); // Split word into letters
            
            // Convert each letter to English and append to result
            for (int j = 0; j < codeLetters.length; j++) {
                result += codeTree.fetch(codeLetters[j]);
            }
            
            // Add a space between words in the final result
            result += " ";
        }
        
        // Trim any trailing spaces from the final result
        result = result.trim();
        return result;
    }
    
    /**
     * Converts a file containing Morse code into English. Each letter is
     * delimited by a space (' '), and each word is delimited by a '/'.
     * 
     * Example:
     * File content: ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * Returned string: "hello world"
     * 
     * @param codeFile - the file containing Morse code to be converted.
     * @return the English translation of the Morse code in the file.
     * @throws FileNotFoundException if the file cannot be found.
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        Scanner in = new Scanner(codeFile); // Scanner to read the file
        String result = ""; // Resulting English translation
        
        // Read the file line by line and convert each line to English
        while (in.hasNext()) {
            result += convertToEnglish(in.nextLine());
        }
        
        in.close(); // Close the scanner to release resources
        return result;
    }
}
