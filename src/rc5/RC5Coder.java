/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

import java.math.BigInteger;
import rotate.BiteOperation;

/**
 *
 * @author moles
 */
public class RC5Coder {

    //to algorithm
    private final int numberOfRounds;
    private final int numberOfWords;
    private final int sizeOfPart = 4;
    private BiteOperation biteOperation;

    public RC5Coder(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        numberOfWords = 2 * (numberOfRounds + 1);
        biteOperation = new BiteOperation();
    }
    /**
     * 
     * @param data data
     * @return parts
     */
    private int[] divisionIntoParts(byte[] data) {
        int[] tmp = new int[2];
        byte[] a = new byte[sizeOfPart];
        byte[] b = new byte[sizeOfPart];
        for (int i = 0; i < sizeOfPart; i++) {
            a[i] = data[i];
            b[i] = data[sizeOfPart + i];
        }
        tmp[0] = new BigInteger(a).intValue();
        tmp[1] = new BigInteger(b).intValue();
        return tmp;
    }
    /**
     * 
     * @param a first part
     * @param b second part
     * @return 
     */
    private byte[] assemblingParts(int a,int b){
        byte[] tmp=new byte[2*sizeOfPart];
        
        return tmp;
    }
    /**
     *
     * @param data
     * @return encrypted data
     */
    public byte[] encryptPart(byte[] data, int[] S) {
        int A, B;
        int f;
        int[] tmp = divisionIntoParts(data);
        A = tmp[0];
        B = tmp[1];
        A = A + S[0];
        B = B + S[1];
        for (int p = 1; p <= numberOfRounds; p++) {
            A = A ^ B;
            f = (int) B % 32;
            A = biteOperation.rotateLeft(A, f);
            A = A + S[2 * p];
            B = B ^ A;
            f = (int) A % 32;
            B = biteOperation.rotateLeft(B, f);
            B = B + S[2 * p + 1];
        }
        
       
        for (int p = numberOfRounds; p != 0; p--) {
            f = (int) A % 32;
            B = B + S[2 * p + 1];
            B = biteOperation.rotateRight(B, f);
            B = B ^ A;
            f = (int) B % 32;
            A = A - S[2 * p];
            A = biteOperation.rotateRight(A, f);
            A = A ^ B;
        }
        A = A - S[0];
        B = B - S[1];
        return null;
    }

    /**
     *
     * @param data
     * @return decrypted data
     */
    public byte[] decryptPart(byte[] data, int[] S) {
        return null;
    }

}
