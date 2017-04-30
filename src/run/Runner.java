/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import rc5.RC5Coder;
import rc5.RC5Key;

/**
 *
 * @author moles
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int r = 10;
        int[] S = new int[2 * r + 2];
        String text = "1234abcd";
        String key = "asdfghjklzxcvbnm";
        RC5Key rc5key = new RC5Key(r, key.getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
        byte[] encryptPart = rC5Coder.encryptBlock(text.getBytes(), rc5key);
        byte[] decryptPart = rC5Coder.decryptBlock(encryptPart, rc5key);
        System.out.println(text);
        System.out.println(new String(encryptPart));
        System.out.println(new String(decryptPart));
    }

}
