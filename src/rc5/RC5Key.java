/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

import java.util.Arrays;
import rotate.BiteOperation;

/**
 *
 * @author moles
 */
public class RC5Key {

    private final int P = 0xb7e15163;
    private final int Q = 0x9e3779b9;
    private final int w = 32;// word in bits
    private final int u = w / 8;// word in byte
    private final int numberOfRounds;
    private final int sizeKeyInByte;
    private final int numberOfWords;
    private final int c;
    private final byte[] key;//key
    private int[] words;
    private BiteOperation biteOperation;

    /**
     *
     * @param numberOfRounds number of rounds
     * @param key key byte
     */
    public RC5Key(int numberOfRounds, byte[] key) {
        this.numberOfRounds = numberOfRounds;
        this.key = key;
        biteOperation = new BiteOperation();
        sizeKeyInByte = key.length;
        c = sizeKeyInByte / u;
        numberOfWords = 2 * (numberOfRounds + 1);
        generateWords();
    }

    /**
     * generate words
     */
    private void generateWords() {
        int[] L = new int[numberOfWords];
        int n;
        int tmp;
        int A=0;
        int B=0;
        int ii=0;
        int j=0;
        words = new int[numberOfWords];
        //------------------------------------
        for (int i = sizeKeyInByte - 1; i >= 0; i--) {
            L[i / u] = (L[i / u] << 8) + key[i];
        }
        words[0] = P;
        for (int i = 1; i < numberOfWords; i++) {
            words[i] = words[i - 1] + Q;
        }
        if (sizeKeyInByte > c) {
            n = sizeKeyInByte;
        } else {
            n = c;
        }
        for(int k=0;k<3*n;k++){
            words[ii]=biteOperation.rotateLeft(words[ii]+A+B, 3);
            A=words[ii];
            tmp=(A+B)%32;
            L[j]=biteOperation.rotateLeft(L[j]+A+B, tmp);
            B=L[j];
            ii=(ii+1)%numberOfWords;
            j=(j+1)%c; 
        }       
    }

    /**
     *
     * @return key bytes
     */
    public byte[] getKey() {
        return key;
    }

    public int[] getWords() {
        return words;
    }

}
