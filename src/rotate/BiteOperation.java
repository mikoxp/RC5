/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotate;

/**
 *
 * @author moles
 */
public class BiteOperation {
     private final int biteSizeNumber = Integer.SIZE;
     /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    public int rotateLeft(int number, int n) {
        return (((number) << (n))) | ((number) >>> (biteSizeNumber - (n)));
    }

    /**
     *
     * @param number number
     * @param n number to move
     * @return result
     */
    public int rotateRight(int number, int n) {
        return (((number) >>> (n))) | ((number) << (biteSizeNumber - (n)));
    }
}
