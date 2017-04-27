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

    private final int biteSizeNumber = 32;

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    private int rotateLeft(int number, int n) {
        return (((number) << (n))) | ((number) >> (biteSizeNumber - (n)));
    }

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    private int rotateRight(int number, int n) {
        return (((number) >> (n))) | ((number) << (biteSizeNumber - (n)));
    }
}
