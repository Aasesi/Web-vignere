/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wilczynskimikolaj.model;

/**
 * Operation class represents row inside database.
 *
 * @author Mikołaj
 * @version 1.0
 */
public class Operation {

    private String typeOfChange;
    private String changedText;
    private String originalText;
    private String keyy;

    /**
     * Basic constructor.
     */
    public Operation() {

    }

    /**
     * Constructor with parameters
     *
     *
     * @param typeChange change type encode or decode
     * @param textNew encoded or decoded text
     * @param original original text
     * @param key key value
     */
    public Operation(String typeChange, String textNew, String original, String key) {
        typeOfChange = typeChange;
        changedText = textNew;
        originalText = original;
        keyy = key;
    }

    /**
     * Function to retrive type of change in operation.
     *
     * @return type of change
     */
    public String getTypeOfChange() {
        return typeOfChange;
    }

    /**
     * Function to set type of change in operation.
     *
     * @param typeOfChange Type of the change
     */
    public void setTypeOfChange(String typeOfChange) {
        this.typeOfChange = typeOfChange;
    }

    /**
     * Function to retrive changed text in operation.
     * 
     * @return changed text
     */
    public String getChangedText() {
        return changedText;
    }

    /**
     * Function to set changed text in operation.
     * 
     * @param changedText new text
     */
    public void setChangedText(String changedText) {
        this.changedText = changedText;
    }

    /**
     * Function to retrive original text in operation.
     * 
     * @return original text
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Function to set original text in operation.
     * 
     * @param originalText original text before encoding or decoding
     */
    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    /**
     * Function to retrive key in operation.
     * 
     * @return key
     */
    public String getKey() {
        return keyy;
    }

    /**
     * Function to set key in operation.
     * 
     * @param key key of the change
     */
    public void setKey(String key) {
        this.keyy = key;
    }
}
