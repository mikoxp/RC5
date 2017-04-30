/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

import exception.BiteOperationException;
import java.math.BigInteger;
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
    private final int sizeOfPartInBite=32;
    private final int sizeOfPartInByte = sizeOfPartInBite/8;
    private final BiteOperation biteOperation;

    public RC5Coder(int numberOfRounds) {
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
        int n=data.length;
        int nB=n/2;
        int nA=n-nB;
        int j;
        byte[] a = new byte[sizeOfPartInByte];
        byte[] b = new byte[sizeOfPartInByte];
        n--;
        j=sizeOfPartInByte-1;
        for(int i=0;i<nB;i++){
            b[j]=data[n];
            n--;
            j--;
        }
        j=sizeOfPartInByte-1;
        for(int i=nA-1;i>=0;i--){
            a[j]=data[n];
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
                Logger.getLogger(RC5Coder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (byteB.length != sizeOfPartInByte) {
            try {
                byteB = biteOperation.complementToBlock(sizeOfPartInByte, byteB);
            } catch (BiteOperationException ex) {
                Logger.getLogger(RC5Coder.class.getName()).log(Level.SEVERE, null, ex);
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
    public byte[] encryptBlock(byte[] data, RC5Key key) {
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
    public byte[] decryptBlock(byte[] data, RC5Key key) {
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
     * @param key key
     * @return encrypt data
     */
    public byte[] encrypt(byte[] data, RC5Key key){
        byte[] tmp=null;
        return tmp;
    }
    /**
     * 
     * @param data data
     * @param key key
     * @return decrypt data
     */
    public byte[] decrypt(byte[] data, RC5Key key){
        byte[] tmp=null;
        return tmp;
    }

}
