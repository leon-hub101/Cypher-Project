import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class Poetry {

    public static void main(String[] args) {
        File poemFile = new File(
                "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\17-004 Java - IO\\poem.txt");

        File encodedPoemFile = new File(
                "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 1\\encodedPoem.txt");

        File vowelsPoemFile = new File(
                "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 2\\capitalVowels.txt");

        File reversedPoemFile = new File(
                "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 3\\reversePoem.txt");

        File restoredPoemFile = new File(
                "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 3\\restoredPoem.txt");

        try (Scanner file = new Scanner(poemFile);
                Formatter f = new Formatter(encodedPoemFile)) {

            StringBuilder poem = new StringBuilder();

            while (file.hasNextLine()) {
                poem.append(file.nextLine() + "\n");
            }

            String encryptedPoem = cipher(poem.toString().trim()); // Calling the Cipher function to encrypt the input
            f.format("%s", encryptedPoem); // Writing the encrypted input to the new file (encodedPoem.txt)
            System.out.println("Encrypted poem written to the new file.");

        } catch (FileNotFoundException e) {
            System.out.println("There's an issue with your files. Please check your file paths.");
            e.printStackTrace();
        }

        try (Scanner file = new Scanner(encodedPoemFile);
                Formatter f = new Formatter(vowelsPoemFile)) {

            StringBuilder vowels = new StringBuilder();

            while (file.hasNextLine()) {
                vowels.append(file.nextLine() + "\n");
            }

            String capitalVowels = capVowel(vowels.toString().trim()); // Calling the capVowel function to capitalise
                                                                       // the vowels
            f.format("%s", capitalVowels); // Writting the modified encrypted poem to the new file (capitalVowels.txt)
            System.out.println("The vowels have been capitalised.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your file path.");
            e.printStackTrace();
        }

        try (Scanner file = new Scanner(vowelsPoemFile);
                Formatter f = new Formatter(reversedPoemFile)) {

            StringBuilder reversedPoem = new StringBuilder();

            while (file.hasNextLine()) {
                reversedPoem.append(file.nextLine() + "\n");
            }

            String restoredCaps = restoreText(reversedPoem.toString().trim()); // Calling the function to restore the
                                                                               // vowels and capped letters
            String reversed = reverseString1(restoredCaps); // Calling the reverseString function to reverse the encoded
                                                            // poem
            String decodedPoem = deCipher(reversed); // Calling the deCipher function to decode the poem
            f.format("%s", decodedPoem); // Writting the decoded and reversed poem to the new file (reversePoem.txt)
            System.out.println("The encrypted poem has been decrypted, reversed and the caps restored.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your file path.");
            e.printStackTrace();
        }

        try (Scanner file = new Scanner(reversedPoemFile);
                Formatter f = new Formatter(restoredPoemFile)) {

            StringBuilder restoredPoem = new StringBuilder();

            while (file.hasNextLine()) {
                restoredPoem.append(file.nextLine() + "\n");
            }

            String reversed = reverseString2(restoredPoem.toString().trim()); // Calling the reverseString function to
                                                                              // reverse the encoded poem
            f.format("%s", reversed); // Writting the restored poem to the new file (restoredPoem.txt)
            System.out.println("The poem has been restored to its original state.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your file path.");
            e.printStackTrace();
        }

    }

    // My cipher function iterates over the input and converts every character to
    // it's Ascii equivalent.
    // It then shifts the Asciivals on by 15 characters, and then reverts the vals
    // to their equivalent in the alphabet. 
    // The output is the shifted letters.
    public static String cipher(String text) {
        StringBuilder newCode = new StringBuilder(); // Using the StringBuilder class for efficiency

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            int asciiCode = text.charAt(i);

            if ((asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122)) {
                boolean isUpperCase = asciiCode >= 65 && asciiCode <= 90;
                int base = isUpperCase ? 65 : 97;

                int shiftedAsciiVals = ((asciiCode - base + 15) % 26) + base;
                newCode.append((char) shiftedAsciiVals);
            } else {
                newCode.append(character);
            }
        }
        return newCode.toString();
    }

    // This function iterates over the input and reverses the encryption process
    public static String deCipher(String text) {
        StringBuilder OrigStr = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            int asciiCode = text.charAt(i);

            if ((asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122)) {
                boolean isUpperCase = asciiCode >= 65 && asciiCode <= 90;
                int UpperBound = isUpperCase ? 90 : 122;

                int shiftedAsciiVals = ((asciiCode - UpperBound - 15) % 26) + UpperBound;
                OrigStr.append((char) shiftedAsciiVals);

            } else {
                OrigStr.append(character);
            }
        }
        return OrigStr.toString();
    }

    // My capVowel function iterates over the poem and capitalises every vowel
    public static String capVowel(String text) {
        StringBuilder OrigStr = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (String.valueOf(currentChar).matches("[aeiouAEIOU]")) { // Using a regex pattern to identify vowels
                OrigStr.append(Character.toUpperCase(currentChar));
            } else {
                OrigStr.append(currentChar);
            }
        }
        return OrigStr.toString();
    }

    // My reverseString function reverses the poem. The end product here is
    // that the poem is written from the final character first to the first
    // character last.
    // So, it's like it's written upside down - This is reverse on steroids!
    public static String reverseString1(String str) {
        StringBuilder reverse = new StringBuilder(str);
        return reverse.reverse().toString();
    }

    // This function restores the poem's capitalisation to its original state
    public static String restoreText(String text) {
        StringBuilder restoredText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (String.valueOf(currentChar).matches("[AEIOUaeiou]")) { // If it's a vowel
                // Convert to lowercase and append to the variable
                restoredText.append(Character.toLowerCase(currentChar));
            } else {
                restoredText.append(currentChar);
            }
        }
        return restoredText.toString();
    }

    // The last function reverses the first reverse function and replaces two capital
    // letters that I could not fix in the reversed state!
    public static String reverseString2(String str) {
        // Reverse the string
        StringBuilder reverse = new StringBuilder(str).reverse();

        // Convert StringBuilder back to String for replacement
        String reversedString = reverse.toString();

        // Replace "prime" with "Prime" after reversing the string
        String result = reversedString.replaceAll("\\bprime\\b", "Prime");

        return result;
    }
}
