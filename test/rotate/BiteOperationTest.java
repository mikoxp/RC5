
package rotate;

import exception.BiteOperationException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author moles
 */
public class BiteOperationTest {

    private final BiteOperation biteOperation = new BiteOperation();

    /**
     * did operation is reversiblility in max value
     */
    @Test
    public void reversibilityOfRotation_sameNumber_reversibility() {
        BigInteger bigInteger = new BigInteger("abcd".getBytes());
        int a = bigInteger.intValue();
        int b = a;
        int number = 32;
        int n = 150;
        for (int i = 0; i < n; i++) {
            b = biteOperation.rotateLeft(b, number);

        }
        for (int i = 0; i < n; i++) {
            b = biteOperation.rotateRight(b, number);
        }
        Assert.assertEquals(a, b);
    }

    /**
     * did operation is reversiblility in random value
     */
    @Test
    public void reversibilityOfRotation_randomNumber_reversibility() {
        BigInteger bigInteger = new BigInteger("abcd".getBytes());
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        int a = bigInteger.intValue();
        int b = a;
        int number = 3;
        int n = 150;
        for (int i = 0; i < n; i++) {
            number = random.nextInt() % 32;
            number = Math.abs(number);
            integers.add(number);
        }
        for (int i = 0; i < n; i++) {
            number = integers.get(i);
            b = biteOperation.rotateLeft(b, number);

        }
        for (int i = n - 1; i >= 0; i--) {
            number = integers.get(i);
            b = biteOperation.rotateRight(b, number);
        }
        Assert.assertEquals(a, b);
    }

    /**
     * if is null function cant work
     *
     * @throws BiteOperationException exception
     */
    @Test(expected = BiteOperationException.class)
    public void complementToBlock_dataIsNull_Exception() throws BiteOperationException {
        biteOperation.complementToBlock(5, null);
    }

    /**
     * standard work
     */
    @Test
    public void complementToBlock_complement_complemented() {
        byte[] mark = new byte[4];
        mark[2] = 15;
        mark[3] = 7;
        byte[] data = new byte[2];
        data[0] = 15;
        data[1] = 7;
        try {
            data = biteOperation.complementToBlock(4, data);
        } catch (BiteOperationException ex) {
            Logger.getLogger(BiteOperationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertArrayEquals(mark, data);
    }

    /**
     * bigger array not change smaller and save data
     *
     * @throws BiteOperationException
     */
    @Test(expected = BiteOperationException.class)
    public void complementToBlock_dataSizeIsBiggerThanSizeBlock_Exception()
            throws BiteOperationException {
        byte[] data = new byte[10];
        biteOperation.complementToBlock(5, data);
    }
}
