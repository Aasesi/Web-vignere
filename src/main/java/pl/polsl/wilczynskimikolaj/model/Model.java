/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.model;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The 'Model' class contains app logic of encoding, decoding and creating key
 * for vignere cypher.
 *
 * @author Mikołaj Wilczyński
 * @version 1.0
 */
public class Model {
    
    
    /**
     * Encodes the input text using a generated key.
     *
     * @param text The text to be encoded.
     * @param key The key used for encoding.
     * @return The encoded text.
     * @throws KeyGenerationException If key generation fails due to empty text or key.
     */
    public String encode(String text, String key) throws KeyGenerationException
    {
        String textCopy = text;
        // Replacing and remembering all spaces in original string
        List<Integer> spacePosList = IntStream.range(0, text.length())
        .filter(i -> textCopy.charAt(i) == ' ')
        .boxed()
        .collect(Collectors.toList());
        
        text = text.replaceAll(" ", "");
        
        // Generate the key
        String generatedKey;
        try {
            generatedKey = makeKey(text, key);
        } catch (KeyGenerationException e) {
            throw e;
        }
        
        String result = "";
        
        // Rember original cases of letters
        HashMap<Integer, LetterCase> letterCases = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if(Character.isUpperCase(text.charAt(i)))
            {
                letterCases.put(i, LetterCase.UPPERCASE);
            }
            else
            {
                letterCases.put(i, LetterCase.LOWERCASE);
            }
        }
        
        // Change to uppercase
        text = text.toUpperCase();
        
        // Encoding
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            int x = (c + (generatedKey.charAt(i))) % 26;
            x += 'A';
            result +=(char)(x);
        }
        
        // Return letters to its original cases
        StringBuilder resultCases = new StringBuilder(result);
        for(int i = 0; i< text.length();i++)
        {
            if(letterCases.get(i).equals(LetterCase.LOWERCASE))
            {
                resultCases.setCharAt(i,
                        Character.toLowerCase(result.charAt(i)));
            }
        }
        result = resultCases.toString();
        
        // Insert all spaces back
        StringBuilder resultWithSpace = new StringBuilder(result);
        for (int i : spacePosList) {
            resultWithSpace.insert(i, ' ');
        }
        result = resultWithSpace.toString();
        
        return result;
    }
    
    /**
     * Decodes the input text using a generated key.
     *
     * @param text The text to be decoded.
     * @param key The key used for decoding.
     * @return The decoded text.
     * @throws KeyGenerationException If key generation fails due to empty text or key.
     */
    public String decode(String text, String key) throws KeyGenerationException
    {
        String textCopy = text;
        // Replacing and remembering all spaces in original string
        List<Integer> spacePosList = IntStream.range(0, text.length())
                .filter(i -> textCopy.charAt(i) == ' ')
                .boxed()
                .collect(Collectors.toList());
        text = text.replaceAll(" ", "");
        
        // Generate the key
        String generatedKey;
        try {
            generatedKey = makeKey(text, key);
        } catch (KeyGenerationException e) {
            throw e;
        }
        String result = "";
        
        // Rember original cases of letters
        HashMap<Integer, LetterCase> letterCases = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if(Character.isUpperCase(text.charAt(i)))
            {
                letterCases.put(i, LetterCase.UPPERCASE);
            }
            else
            {
                letterCases.put(i, LetterCase.LOWERCASE);
            }
        }
        text = text.toUpperCase();
        
        // Decode
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(c < 'A' | c > 'Z')
            {
                result += ' ';
                continue;
            }
            int x = (c - (generatedKey.charAt(i)) + 26) % 26;
            x += 'A';
            result +=(char)(x);
        }
        
        // Return letters to its original cases
        StringBuilder resultCases = new StringBuilder(result);
        for(int i = 0; i< text.length();i++)
        {
            if(letterCases.get(i).equals(LetterCase.LOWERCASE))
            {
                resultCases.setCharAt(i,
                        Character.toLowerCase(result.charAt(i)));
            }
        }
        result = resultCases.toString();
        
        // Insert all spaces back
        StringBuilder resultWithSpace = new StringBuilder(result);
        for (int i : spacePosList) {
            resultWithSpace.insert(i, ' ');
        }
        result = resultWithSpace.toString();
        
        return result;
    }
    
     /**
     * Generates a key based on the input text and key. 
     *
     * @param text The input text for which the key is generated.
     * @param key The initial key.
     * @return The generated key.
     * @throws KeyGenerationException If generation fails due to empty text or empty key.
     */
    public String makeKey(String text, String key) throws KeyGenerationException
    {
        String textNoSpace = text;
        textNoSpace = textNoSpace.replaceAll(" ", "");
        key = key.replaceAll(" ", "");
        if (textNoSpace.isEmpty() || key.isEmpty()) {
            throw new KeyGenerationException("Key or text can not be empty");
        }
        
        key = key.toUpperCase();
        
        StringBuilder generatedKey = new StringBuilder();
        int keyLength = key.length();
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            char keyChar = key.charAt(i % keyLength);
            generatedKey.append(keyChar);
        }

        return generatedKey.toString();
    }
}
