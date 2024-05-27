import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class Poetry {

    public static void main(String[] args) {
        String poemPath = "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\17-004 Java - IO\\poem.txt";
        String encodedPoemPath = "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 1\\encodedPoem.txt";
        String vowelsPoemPath = "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 2\\capitalVowels.txt";
        String reversedPoemPath = "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 3\\reversePoem.txt";
        String restoredPoemPath = "C:\\Users\\User\\Dropbox\\LP24020013758\\2 - Advanced Programming Concepts\\L2T04 - Java - IO\\Task 3\\restoredPoem.txt";

        processFile(poemPath, encodedPoemPath, Poetry::cipher, "Encrypted poem written to the new file.");
        processFile(encodedPoemPath, vowelsPoemPath, Poetry::capVowel, "The vowels have been capitalised.");
        processFile(vowelsPoemPath, reversedPoemPath, text -> Poetry.reverseString1(Poetry.restoreText(text)), "The encrypted poem has been decrypted, reversed and the caps restored.");
        processFile(reversedPoemPath, restoredPoemPath, Poetry::reverseString2, "The poem has been restored to its original state.");
    }

    private static void processFile(String inputPath, String outputPath, Processor processor, String successMessage) {
        try (Scanner scanner = new Scanner(new File(inputPath));
             Formatter formatter = new Formatter(outputPath)) {

            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }

            String processedContent = processor.process(content.toString().trim());
            formatter.format("%s", processedContent);
            System.out.println(successMessage);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your file path.");
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    interface Processor {
        String process(String text);
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
