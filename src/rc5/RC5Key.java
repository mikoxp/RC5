/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc5;

import key.BlumBlumShubKeyGenerator;
import rotate.BiteOperation;

/**
 *
 * @author moles
 */
public class RC5Key {

    private final int P = 0xb7e15163;
    private final int Q = 0x9e3779b9;
    private final int sizeOfWordInBits = 32;
    private final int sizeOfWordInByte = sizeOfWordInBits / 8;
    private final int sizeKeyInByte;
    private final int numberOfWords;
    private final int c;
    private final byte[] key;
    private int[] words;
    private final BiteOperation biteOperation;

    /**
     *
     * @param numberOfRounds number of rounds
     * @param key key byte
     */
    public RC5Key(int numberOfRounds, byte[] key) {
        this.key = key;
        biteOperation = new BiteOperation();
        sizeKeyInByte = key.length;
        c = sizeKeyInByte / sizeOfWordInByte;
        numberOfWords = 2 * (numberOfRounds + 1);
        generateWords();
    }
    /**
     * 
     * @param numberOfRounds number of rounds
     * @param sizeKeyInByte  size of key
     */
    public RC5Key(int numberOfRounds,int sizeKeyInByte){
        biteOperation = new BiteOperation();
        this.sizeKeyInByte=sizeKeyInByte;
        c = sizeKeyInByte / sizeOfWordInByte;
        numberOfWords = 2 * (numberOfRounds + 1);
        key=generateKey();
        generateWords();
    }
    /**
     * 
     * @return generate key
     */
    private byte[] generateKey(){
        byte[] key = null;
        BlumBlumShubKeyGenerator gen=new BlumBlumShubKeyGenerator();
        key=gen.generate(sizeKeyInByte);
        return key;
    }
    /**
     * generate words
     */
    private void generateWords() {
        int[] L = new int[numberOfWords];
        int n;
        int number;
        int a=0;
        int b=0;
        int x=0;
        int y=0;
        words = new int[numberOfWords];
        //------------------------------------
        for (int i = sizeKeyInByte - 1; i >= 0; i--) {
            L[i / sizeOfWordInByte] = (L[i / sizeOfWordInByte] << 8) + key[i];
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
        for(int i=0;i<3*n;i++){
            words[x]=biteOperation.rotateLeft(words[x]+a+b, 3);
            a=words[x];
            number=(a+b)%32;
            L[y]=biteOperation.rotateLeft(L[y]+a+b, number);
            b=L[y];
            x=(x+1)%numberOfWords;
            y=(y+1)%c; 
        }       
    }

    /**
     *
     * @return key bytes
     */
    public byte[] getKey() {
        return key;
    }
    /**
     * 
     * @return words
     */
    public int[] getWords() {
        return words;
    }

}
