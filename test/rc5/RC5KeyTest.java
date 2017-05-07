
package rc5;

import exception.KeyException;
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
    public void constructor_keyIsNull_generate_words() throws KeyException {
        RC5Key k=new RC5Key(5, null);
        int[] words = k.getWords();
        assertNotEquals(null,words);
    }
    @Test(expected = KeyException.class)
    public void constructor_numberOfRoundsIsNegative_Exception() throws KeyException {
        RC5Key rC5Key = new RC5Key(-1, "123".getBytes());
    }

    @Test(expected = KeyException.class)
    public void constructor_numberOfRoundsIsZero_Exception() throws KeyException {
        RC5Key rC5Key = new RC5Key(0, "123".getBytes());
    }

    @Test(expected = KeyException.class)
    public void constructor_numberOfRoundsIsBiggerTo255_Exception() throws KeyException {
        RC5Key rC5Key = new RC5Key(256, "123".getBytes());
    }
    @Test
    public void generateKey_nothing(){
        RC5Key key;
        for(int i=1;i<=255;i++){
            key=new RC5Key(255, i);
        }
    }
    @Test(expected = RuntimeException.class)
    public void generateKey_keyisTooLong_runtimeException(){
        RC5Key key;
        key=new RC5Key(5, 255);  
    }
    
}
