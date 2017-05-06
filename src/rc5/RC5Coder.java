
package rc5;

import exception.BiteOperationException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rotate.BiteOperation;

/**
 *
 * @author moles
 */
public class RC5Coder {

    //to algorithm
    private final int numberOfRounds;
    private final int sizeOfPartInBite = 32;
    private final int sizeOfPartInByte = sizeOfPartInBite / 8;
    private final BiteOperation biteOperation;

    /**
     *
     * @param numberOfRounds number of rounds
     */
    public RC5Coder(int numberOfRounds) {
        if(numberOfRounds<1|| numberOfRounds>255){
            throw new IllegalArgumentException("Number of Round must be in (1,255)");
        }
        this.numberOfRounds = numberOfRounds;
        biteOperation = new BiteOperation();
    }

    /**
     *
     * @param data data
     * @return parts
     */
    private int[] divisionIntoParts(byte[] data) {
        int[] tmp = new int[2];
        int n = data.length;
        int nB = n / 2;
        int nA = n - nB;
        int j;
        byte[] a = new byte[sizeOfPartInByte];
        byte[] b = new byte[sizeOfPartInByte];
        n--;
        j = sizeOfPartInByte - 1;
        for (int i = 0; i < nB; i++) {
            b[j] = data[n];
            n--;
            j--;
        }
        j = sizeOfPartInByte - 1;
        for (int i = nA - 1; i >= 0; i--) {
            a[j] = data[n];
            n--;
            j--;
        }
        tmp[0] = new BigInteger(a).intValue();
        tmp[1] = new BigInteger(b).intValue();
        return tmp;
    }

    /**
     *
     * @param a first part
     * @param b second part
     * @return assembling byte data
     */
    private byte[] assemblingParts(int a, int b) {
        byte[] tmp = new byte[2 * sizeOfPartInByte];
        byte[] byteA = new BigInteger("" + a).toByteArray();
        byte[] byteB = new BigInteger("" + b).toByteArray();
        if (byteA.length != sizeOfPartInByte) {
            try {
                byteA = biteOperation.complementToBlock(sizeOfPartInByte, byteA);
            } catch (BiteOperationException ex) {
                Logger.getLogger(RC5Coder.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        if (byteB.length != sizeOfPartInByte) {
            try {
                byteB = biteOperation.complementToBlock(sizeOfPartInByte, byteB);
            } catch (BiteOperationException ex) {
                Logger.getLogger(RC5Coder.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        for (int i = 0; i < sizeOfPartInByte; i++) {
            tmp[i] = byteA[i];
            tmp[sizeOfPartInByte + i] = byteB[i];
        }
        return tmp;
    }

    /**
     *
     * @param data data
     * @param key key
     * @return encrypted data block
     */
    private byte[] encryptBlock(byte[] data, RC5Key key) {
        int a;
        int b;
        int number;
        int[] s = key.getWords();
        int[] parts = divisionIntoParts(data);
        a = parts[0];
        b = parts[1];
        /*_____________________________________*/
        a = a + s[0];
        b = b + s[1];
        for (int i = 1; i <= numberOfRounds; i++) {
            a = a ^ b;
            number = (int) b % sizeOfPartInBite;
            a = biteOperation.rotateLeft(a, number);
            a = a + s[2 * i];
            b = b ^ a;
            number = (int) a % sizeOfPartInBite;
            b = biteOperation.rotateLeft(b, number);
            b = b + s[2 * i + 1];
        }
        /*_____________________________________*/
        byte[] outputData = assemblingParts(a, b);
        return outputData;
    }

    /**
     *
     * @param data data
     * @param key key
     * @return decrypted data block
     */
    private byte[] decryptBlock(byte[] data, RC5Key key) {
        int a;
        int b;
        int number;
        int[] s = key.getWords();
        int[] parts = divisionIntoParts(data);
        a = parts[0];
        b = parts[1];
        /*_____________________________________*/
        for (int i = numberOfRounds; i != 0; i--) {
            number = (int) a % sizeOfPartInBite;
            b = b - s[2 * i + 1];
            b = biteOperation.rotateRight(b, number);
            b = b ^ a;
            number = (int) b % sizeOfPartInBite;
            a = a - s[2 * i];
            a = biteOperation.rotateRight(a, number);
            a = a ^ b;
        }
        a = a - s[0];
        b = b - s[1];
        /*_____________________________________*/
        byte[] outputData = assemblingParts(a, b);
        return outputData;
    }

    /**
     *
     * @param data data
     * @return list of blocks
     */
    private List<byte[]> divisionIntoBlocks(byte[] data) {
        int n = data.length;
        int sizeOfBlock = 2 * sizeOfPartInByte;
        if (n % sizeOfBlock != 0) {
            throw new IllegalArgumentException("Data size must be a multiple of 8");
        }
        int numbersOfBlocks = n / sizeOfBlock;
        byte[] tmp;
        int counter = 0;
        List<byte[]> parts = new ArrayList<>();
        for (int i = 0; i < numbersOfBlocks; i++) {
            tmp = new byte[sizeOfBlock];
            for (int j = 0; j < sizeOfBlock; j++) {
                tmp[j] = data[counter];
                counter++;
            }
            parts.add(tmp);
        }
        return parts;
    }

    /**
     *
     * @param blocks list of blocks
     * @return data
     */
    private byte[] assemblyOfBlocks(List<byte[]> blocks) {
        int sizeOfBlock = 2 * sizeOfPartInByte;
        int n = blocks.size() * sizeOfBlock;
        byte[] outputData = new byte[n];
        int counter = 0;
        for (byte[] block : blocks) {
            for (int i = 0; i < sizeOfBlock; i++) {
                outputData[counter] = block[i];
                counter++;
            }
        }
        return outputData;
    }

    /**
     *
     * @param data data
     * @param key key
     * @return encrypt data
     */
    public byte[] encrypt(byte[] data, RC5Key key) {
        List<byte[]> inputBlocks = divisionIntoBlocks(data);
        List<byte[]> outputBlocks = new ArrayList<>();
        byte[] tmp;
        for (byte[] block : inputBlocks) {
            tmp = encryptBlock(block, key);
            outputBlocks.add(tmp);
        }
        byte[] outputData = assemblyOfBlocks(outputBlocks);
        return outputData;

    }

    /**
     *
     * @param data data
     * @param key key
     * @return decrypt data
     */
    public byte[] decrypt(byte[] data, RC5Key key) {
        List<byte[]> inputBlocks = divisionIntoBlocks(data);
        List<byte[]> outputBlocks = new ArrayList<>();
        byte[] tmp;
        for (byte[] block : inputBlocks) {
            tmp = decryptBlock(block, key);
            outputBlocks.add(tmp);
        }

        byte[] outputData = assemblyOfBlocks(outputBlocks);
        return outputData;
    }

}
