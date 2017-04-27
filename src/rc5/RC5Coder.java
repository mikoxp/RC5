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

    private final int biteSizeNumber = 64;

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
     * @return
     */
    public byte[] encryptPart(byte[] data) {
        return null;
    }

    /**
     *
     * @param data
     * @return
     */
    public byte[] decryptPart(byte[] data) {
        return null;
    }

}
