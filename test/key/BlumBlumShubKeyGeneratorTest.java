
package key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moles
 */
public class BlumBlumShubKeyGeneratorTest {

    private final BlumBlumShubKeyGenerator generator;
    /**
     * constructor
     */
    public BlumBlumShubKeyGeneratorTest() {
        generator = new BlumBlumShubKeyGenerator();
    }
    /**
     * did generate unique byte array
     */
    @Test
    public void generate_umiqueKey_unique() {
        List<byte[]> list = new ArrayList<>();
        int number = 16;
        byte[] data = generator.generate(number);
        list.add(data);
        for (int i = 0; i < 10; i++) {
            data = generator.generate(number);
            for (byte[] b : list) {
                assertNotEquals("not only passed", Arrays.toString(data), Arrays.toString(b));
            }
            list.add(data);
        }
    }
    /**
     * numberOfBytes Is negative so error and exception
     */
    @Test
    public void generate_numberOfBytesIs0_null(){
        int number = 0;
        byte[] data = generator.generate(number);
        Assert.assertArrayEquals(null, data);
    }
    /**
     * numberOfBytes Is 0 so key isnt exsite
     */
    @Test(expected = IllegalArgumentException.class)
    public void generate_numberOfBytesIsNegative_Exception(){
        int number = -1;
        byte[] data = generator.generate(number);
        Assert.assertArrayEquals(null, data);
    }

}
