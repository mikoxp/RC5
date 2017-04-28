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
public class RC5Key {

    private final int P=0xb7e15163;
    private final int Q=0x9e3779b9;
    private final int w=32;// word in bits
    private final int u=w/8;// word in byte
    private final int numberOfRounds;
    private final int numbersOfKeyByte;
    private final int c;
    private final byte[] key;//key
    /**
     * 
     * @param numberOfRounds number of rounds
     * @param key key byte
     */
    public RC5Key(int numberOfRounds, byte[] key) {
        this.numberOfRounds = numberOfRounds;
        this.key = key;
        numbersOfKeyByte=key.length;
        c=numbersOfKeyByte/u;
    }
    /**
     * 
     * @return key bytes 
     */
    public byte[] getKey() {
        return key;
    }
    
    
}
