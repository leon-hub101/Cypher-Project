// Create a function that takes in a word, converts it to the ascii equivalent
// adds 15 to every letter (to move it on 15 letters in the alphabet)
// reconverts it into the new word
// Use the ascii value system as the base for the conversions
// ascii values for upperCase letters: 65 - 90
// ascii values for lowerCase letters: 97 - 122
let UserMessage = prompt("Welcome to the encoder\nPlease enter your secret message\nYou will receive it encrypted and decrypted again:");

function cypher(str) {
    let newCode = "";
    
    for (let i = 0; i < str.length; i++) {
        let char = str[i]; // A placeholder for non-alphabetic characters
        let asciiCode = str.charCodeAt(i); // For the ascii values of the letters
        
        // Check if the character is a letter (upper or lower case)
        if ((asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122)) {
            
            // Check if it's uppercase
            let isUpperCase = asciiCode >= 65 && asciiCode <= 90; 
            
            // Create a new variable to set the base value of the asciiCode
            // If it's upperCase set the base value to 65
            // If it's lowerCase set the base value to 97
            let base = isUpperCase ? 65 : 97;
            
            // The secret souce!!
            /* Take the ascii code of the original letter and normalise the value 
            (Doing this will bring both value ranges upperCase (65-90) || lowerCase (97-122) to 0-25 range) */
            // Add 15 to the normalised value (This is performing the shift)
            // Use % 26 to wrap the value around so that anything beyond 26 starts at one again i.e 29 % 26 = 3
            // Bring the value back into it's original ascii range by adding it's base
            // This gives us the shifted ascii value which we then convert to the corresponding alphbetical letter.
            let shiftedAsciiVals = ((asciiCode - base + 15) % 26) + base;
            newCode += String.fromCharCode(shiftedAsciiVals);

        } else {
            // Assign the non-alphabetical characters back into the newCode
            newCode += char;
        }
        
    }
    return newCode;
}

const encrypted = cypher(UserMessage);
console.log(`Your message encrypted: ${encrypted}`);

function deCypher(str) {
    let origStr = "";

    for (let i = 0; i < str.length; i++) {
        let char = str[i];
        let asciiCode = str.charCodeAt(i);

        if ((asciiCode >= 65 && asciiCode <= 90) || (asciiCode >= 97 && asciiCode <= 122)) {
            let isUpperCase = asciiCode >= 65 && asciiCode <= 90;
            
            // Reverse the encoding algorithm from the cypher function
            let base = isUpperCase ? 65 : 97;
            let shiftedAsciiVals = ((asciiCode - base - 15 + 26) % 26) + base;
            origStr += String.fromCharCode(shiftedAsciiVals);

        } else {
            origStr += char;
        }
        
    }
    return origStr;
}

const decrypted = deCypher(encrypted);
console.log(`Your message decrypted: ${decrypted}`);
