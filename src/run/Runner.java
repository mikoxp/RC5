/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.util.Arrays;
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
        String text = "12345678asdfghjk";
        String key = "asdfghjklzxcvbnm";
        RC5Key rc5key = new RC5Key(r, key.getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
         byte[] encrypt = rC5Coder.encrypt(text.getBytes(), rc5key);
        byte[] decrypt = rC5Coder.decrypt(encrypt, rc5key);
        System.out.println(text);
        System.out.println(new String(encrypt));
        System.out.println(new String(decrypt));
        
       
       
    }

}
