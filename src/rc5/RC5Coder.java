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
    
    //to algorithm
    private final int numberOfRounds;
    private final int numberOfWords;

    public RC5Coder(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        numberOfWords=2*(numberOfRounds+1);
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
