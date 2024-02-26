/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.model;

/**
 * Custom exception class that is thrown when there is a problem with generating
 * key for cypher.
 * 
 * @author Mikołaj Wilczyński
 * @version 1.0
 */
public class KeyGenerationException extends Exception {
    /**
     * Creates exception with specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public KeyGenerationException(String message) {
        super(message);
    }
    
}