/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import java.math.BigInteger;
import java.util.Arrays;
import rc5.RC5Coder;

/**
 *
 * @author moles
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int r=5;
        int[] S=new int[2*r+2];
        String text="1234abcd";
        RC5Coder rC5Coder=new RC5Coder(r);
        rC5Coder.encryptPart(text.getBytes(), S);
    }
    
}
