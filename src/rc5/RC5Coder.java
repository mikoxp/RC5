/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

/**
 *
 * @author moles
 */
public class RC5Coder {
    //const
    private final int biteSizeNumber = 64;
    //to algorithm
    private final int numberOfRound;
    

    public RC5Coder(int numberOfRound) {
        this.numberOfRound = numberOfRound;
    }
    

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    private long rotateLeft(long number, int n) {
        return (((number) << (n))) | ((number) >> (biteSizeNumber - (n)));
    }

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    private long rotateRight(long number, int n) {
        return (((number) >> (n))) | ((number) << (biteSizeNumber - (n)));
    }

    /**
     *
     * @param data
     * @return encrypted data
     */
    public byte[] encryptPart(byte[] data,int[] S) {
        return null;
    }

    /**
     *
     * @param data
     * @return decrypted data
     */
    public byte[] decryptPart(byte[] data,int[] S) {
        return null;
    }

}
