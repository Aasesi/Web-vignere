package pl.polsl.wilczynskimikolaj.vigneretest;
import pl.polsl.wilczynskimikolaj.model.KeyGenerationException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pl.polsl.wilczynskimikolaj.model.Model;


/**
 * Test class
 *
 * @author Mikołaj Wilczyński
 * @version 1.0
 */
public class VignereTest {
    private Model model;
    
    /**
     * Function tests makeKey exeptions
     */
    @Test
    public void testKeyFail()
    {
        model = new Model();
        try {
            model.makeKey("", "");
            fail("An exception should be thrown when the text or key is empty");
        }
        catch (KeyGenerationException e){}
    }
    
    /**
     * Function checks corectness of key
     */
    @Test
    public void testKeyAssert1()
    {
        model = new Model();
        try {
            String key = model.makeKey("helloworld", "nice");
            assertEquals(key, "NICENICENI", "Key value is not correct!");
        } catch (KeyGenerationException ex) {
            fail("Key Creation fails");
        }
    }
    
    /**
     * Function checks encoding about wrong input data
     */
    @Test
     public void testEncodeWrongInput()
     {
         model = new Model();
         try {
            model.makeKey("    ", " ");
            fail("An exception should be thrown when the text or key is empty");
        }
        catch (KeyGenerationException e){}
     }
    
     /**
      * Function checks encodeed value corectness
      */
     @Test
     public void testEncodeValueCorrect()
     {
         model = new Model();
         try {
             String result = model.encode("Hello Dear Friend", "nice");
             assertEquals(result, "Umnpb Lgee Ntmrvf", "Encoding failed");
         }
         catch(KeyGenerationException ex)
         {
             fail("Key Creation fails");
         }
     }
     
     /**
      * Function checks decode by testing wrong input
      */
     @Test
     public void testDecodeWrongInput()
     {
         model = new Model();
         try {
            model.makeKey("    ", " ba");
            fail("An exception should be thrown when the text or key is empty");
        }
        catch (KeyGenerationException e){}
     }
     
     /**
      * Function checks encodeed value corectness
      */
     @Test
     public void testDecodeValueCorrect()
     {
         model = new Model();
         try {
             String result = model.decode("mw fpicnb", "ayay");
             assertEquals(result, "my friend", "Decoding failed");
         }
         catch(KeyGenerationException ex)
         {
             fail("Key Creation fails");
         }
     }
     
     /**
      * Parametrized test to check key corectness
      * 
      * @param input1
      * @param input2
      * @param expectedOutput 
      */
    @ParameterizedTest
    @CsvSource({"helloworld, nice, NICENICENI"})
    public void shouldMakeKeyCorrectly(String input1, String input2, String expectedOutput)
    {
        model = new Model();
        String key;
        try {
            key = model.makeKey(input1, input2);
            assertEquals(expectedOutput, key, "Key creation failed for inputs: " + input1 + ", " + input2);
        } catch (KeyGenerationException ex) {
        }
    }
    
    /**
     * Parametrized test to check corectness of encoding
     * @param input1
     * @param input2
     * @param expectedOutput 
     */
    @ParameterizedTest
    @CsvSource({"my friend, ayay,mw fpicnb"})
    public void shouldEncodeCorrectly(String input1, String input2, String expectedOutput)
    {
        model = new Model();
        try {
            String encodeText = model.encode(input1, input2);
            assertEquals(expectedOutput, encodeText, "Key creation failed for inputs: " + input1 + ", " + input2);
        } catch (KeyGenerationException ex) {
        }
    }
    
    /**
     * Parametrized test to check decoding corectness
     * @param input1
     * @param input2
     * @param expectedOutput 
     */
    @ParameterizedTest
    @CsvSource({"osw gwfr, not, bed time"})
    public void shouldDecodeCorrectly(String input1, String input2, String expectedOutput)
    {
        model = new Model();
        try {
            String decodedText = model.decode(input1, input2);
            assertEquals(expectedOutput, decodedText, "Key creation failed for inputs: " + input1 + ", " + input2);
        } catch (KeyGenerationException ex) {
        }
    }
}
