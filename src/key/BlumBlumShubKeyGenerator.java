
package key;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.BitSet;

/**
 *
 * @author moles
 */
public class BlumBlumShubKeyGenerator {

    private long p;
    private long q;
    private long n;
    private long s;
    private long x[];
    private int numberOfBytes;

    /**
     * Default constructor
     */
    public BlumBlumShubKeyGenerator() {

    }

    /**
     *
     * @param numberOfBytes number of bytes
     * @return key
     */
    public byte[] generate(int numberOfBytes) {
        if (numberOfBytes < 0) {
            throw new IllegalArgumentException();
        }
        if (numberOfBytes == 0) {
            return null;
        }
        this.numberOfBytes = numberOfBytes;
        byte[] key;
        int numbersOfBite = numberOfBytes * 8;
        BitSet bitSet = new BitSet(numbersOfBite);
        x = new long[numberOfBytes * 8];
        choosePrimeNumber();
        long start = Math.abs((s * s) % n);
        x[0] = (start * start) % n;
        if ((x[0] % 2) == 1) {
            bitSet.set(0);
        }
        for (int j = 1; j < numbersOfBite; j++) {
            x[j] = (x[j - 1] * x[j - 1]) % n;
            if ((x[j] % 2) == 1) {
                bitSet.set(j);
            }
        }
        key = toByteArray(bitSet);
        if (Arrays.equals(key, new byte[numberOfBytes])) {
            key = generate(numberOfBytes);
        }
        return key;
    }

    /**
     * choose prime number
     */
    private void choosePrimeNumber() {
        SecureRandom random = new SecureRandom();
        BigInteger bigInteger = BigInteger.probablePrime(128, random);
        p = bigInteger.longValue();
        bigInteger = BigInteger.probablePrime(128, random);
        do{
            q = bigInteger.longValue();
        }while(p==q);
        n = p * q;
        s = Math.abs(random.nextLong() % (n - 1)) + 1;
    }

    /**
     *
     * @param bits
     * @return
     */
    private byte[] toByteArray(BitSet bits) {
        byte[] bytes = new byte[numberOfBytes];
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                bytes[bytes.length - i / 8 - 1] |= 1 << (i % 8);
            }
        }
        return bytes;
    }

}
