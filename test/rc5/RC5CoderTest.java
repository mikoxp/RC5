package rc5;

import exception.CoderException;
import java.security.SecureRandom;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moles
 */
public class RC5CoderTest {

    public RC5CoderTest() {

    }

    @Test(expected = CoderException.class)
    public void constructor_numberOfRoundsIsNegative_Exception() throws CoderException {
        RC5Coder rC5Coder = new RC5Coder(-1);
    }

     @Test(expected = CoderException.class)
    public void constructor_numberOfRoundsIsZero_Exception() throws CoderException {
        RC5Coder rC5Coder = new RC5Coder(0);
    }

     @Test(expected = CoderException.class)
    public void constructor_numberOfRoundsIsBiggerTo255_Exception() throws CoderException {
        RC5Coder rC5Coder = new RC5Coder(256);
    }

    @Test
    public void encryptAndDecrypt_reversibilityOfEncryption_reversiblity() 
            throws CoderException {
        int r = 5;
        int n = 32;
        RC5Key k;
        byte[] encrypt;
        byte[] decrypt;
        byte[] mark;
        RC5Coder rC5Coder = new RC5Coder(r);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            k = new RC5Key(r, n);
            mark = new byte[8];
            random.nextBytes(mark);
            encrypt = rC5Coder.encrypt(mark, k);
            decrypt = rC5Coder.decrypt(encrypt, k);
            assertArrayEquals(Arrays.toString(mark), mark, decrypt);
        }
    }

    @Test
    public void encryptAndDecrypt_DifficultData_OK() throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] d = new byte[8];
        d[3] = 40;
        d[7] = 12;
        byte[] encrypt = rC5Coder.encrypt(d, k);
        byte[] decrypt = rC5Coder.decrypt(encrypt, k);
        assertArrayEquals(d, decrypt);
    }

    @Test
    public void encryptAndDecrypt_DifficultData2_OK() throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] d = new byte[8];
        d[7] = 12;
        byte[] encrypt = rC5Coder.encrypt(d, k);
        byte[] decrypt = rC5Coder.decrypt(encrypt, k);
        assertArrayEquals(d, decrypt);
    }

    @Test
    public void encryptAndDecrypt_DifficultData3_OK() 
            throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] d = new byte[8];
        d[0] = -12;
        byte[] encrypt = rC5Coder.encrypt(d, k);
        byte[] decrypt = rC5Coder.decrypt(encrypt, k);
        assertArrayEquals(d, decrypt);
    }

    @Test
    public void encryptAndDecrypt_DifficultData4_OK() 
            throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] d = new byte[8];
        for (int i = 0; i < 8; i++) {
            d[i] = 127;
        }
        byte[] encrypt = rC5Coder.encrypt(d, k);
        byte[] decrypt = rC5Coder.decrypt(encrypt, k);
        assertArrayEquals(d, decrypt);
    }

    @Test
    public void encryptAndDecrypt_DifficultData5_OK() 
            throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] d = new byte[8];
        for (int i = 0; i < 8; i++) {
            d[i] = -128;
        }
        byte[] encrypt = rC5Coder.encrypt(d, k);
        byte[] decrypt = rC5Coder.decrypt(encrypt, k);
        assertArrayEquals(d, decrypt);
    }

     @Test(expected = CoderException.class)
    public void encrypt_sizeOfDataIsNotMultiply8_Exception() 
            throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] data = new byte[9];
        rC5Coder.encrypt(data, k);

    }

     @Test(expected = CoderException.class)
    public void decrypt_sizeOfDataIsNotMultiply8_Exception() 
            throws CoderException {
        int r = 5;
        RC5Key k = new RC5Key(r, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] data = new byte[9];
        rC5Coder.decrypt(data, k);

    }
    @Test(expected = CoderException.class)
    public void encrypt_diffrentNumberOfRoundInKeyAndCoder_Exception() 
            throws CoderException{
        RC5Key k = new RC5Key(5, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(6);
        rC5Coder.encrypt(new byte[8], k);
    }
    @Test(expected = CoderException.class)
    public void decrypt_diffrentNumberOfRoundInKeyAndCoder_Exception() 
            throws CoderException{
        RC5Key k = new RC5Key(5, "12345".getBytes());
        RC5Coder rC5Coder = new RC5Coder(6);
        rC5Coder.decrypt(new byte[8], k);
    }

}
