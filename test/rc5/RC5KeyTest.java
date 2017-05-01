/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moles
 */
public class RC5KeyTest {
    /**
     * constructor
     */
    public RC5KeyTest() {
    }
    /**
     * size key is 0
     */
    @Test
    public void constructor_sizeOfKeyIS0_generate_words() {
        RC5Key k=new RC5Key(5, 0);
        int[] words = k.getWords();
        assertNotEquals(null,words);
    }
    /**
     * key is null
     */
    @Test
    public void constructor_keyIsNull_generate_words() {
        RC5Key k=new RC5Key(5, null);
        int[] words = k.getWords();
        assertNotEquals(null,words);
    }
    
}
