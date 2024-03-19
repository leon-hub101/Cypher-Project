# ASCII Encoder/Decoder Cypher-Project
A simple encoder and decoder

This is a simple JavaScript program that encodes and decodes messages using a Caesar cipher algorithm. It takes a word as input, converts each letter to its ASCII equivalent, adds 15 to the ASCII value (shifting it 15 letters forward in the alphabet), and then converts it back to the corresponding letters.

Usage

1. Clone this repository to your local machine or download the files directly.

2. Open the `index.html` file in a web browser.

3. You will be prompted with a message asking you to enter your secret message. Type your message into the prompt box and press Enter.

4. After pressing Enter, your message will be encrypted and decrypted again using the Caesar cipher algorithm, and the results will be displayed in the browser's console.

How it Works

The program consists of two main functions: `cypher()` and `deCypher()`. Here's how each function works:

`cypher(str)`

- This function takes a string `str` as input.
- It iterates through each character in the string.
- For each alphabetic character, it calculates the ASCII code.
- It checks if the character is uppercase or lowercase and adjusts the base value accordingly.
- It applies the Caesar cipher encryption by adding 15 to the ASCII value (wrapping around if necessary).
- It converts the shifted ASCII value back to the corresponding letter.
- Non-alphabetic characters remain unchanged.
- The encrypted message is returned.

`deCypher(str)`

- This function takes an encrypted string `str` as input.
- It iterates through each character in the string.
- For each alphabetic character, it calculates the ASCII code.
- It checks if the character is uppercase or lowercase and adjusts the upper bound value accordingly.
- It applies the reverse of the Caesar cipher encryption to decrypt the message by subtracting 15 from the ASCII value (wrapping around if necessary).
- It converts the shifted ASCII value back to the corresponding letter.
- Non-alphabetic characters remain unchanged.
- The decrypted message is returned.

Example

// Example usage
let UserMessage = prompt("Welcome to the encoder\nPlease enter your secret message\nYou will receive it encrypted and decrypted again:");

// Encrypt the message
const encrypted = cypher(UserMessage);
console.log(`Your message encrypted: ${encrypted}`);

// Decrypt the message
const decrypted = deCypher(encrypted)
console.log(`Your message decrypted: ${decrypted}`);
```

Note

- This program assumes English alphabet characters only.
- Non-alphabetic characters (e.g., numbers, symbols) remain unchanged during encryption and decryption.
- For extended functionality or use in production environments, further testing and validation may be required.

Enjoy encrypting and decrypting your messages! 🛡️🔑
