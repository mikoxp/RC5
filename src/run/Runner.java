
package run;

import exception.CoderException;
import exception.KeyException;
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
     * @throws exception.CoderException
     * @throws exception.KeyException
     */
    public static void main(String[] args) throws CoderException, KeyException {
        int r = 255;
        String text = "12345678asdfghjk1234abcdqwertyuq";
        String key = "asdfghjklzxcvbnm";
        RC5Key genratedKey=new RC5Key(r, 49);
        RC5Key rc5key = new RC5Key(r, key.getBytes());
        RC5Coder rC5Coder = new RC5Coder(r);
         byte[] encrypt = rC5Coder.encrypt(text.getBytes(), genratedKey);
        byte[] decrypt = rC5Coder.decrypt(encrypt, genratedKey);
        System.out.println(text);
        System.out.println(new String(encrypt));
        System.out.println(new String(decrypt));
        byte[] t=new byte[8];
        t[0]=42;
        t[1]=78;
        t[2]=-109;
        t[3]=115;
        t[4]=84;
        t[5]=12;
        t[6]=21;
        t[7]=-77;
        byte[] encrypt1 = rC5Coder.encrypt(t, genratedKey);
        byte[] d = rC5Coder.decrypt(encrypt1, genratedKey);
        System.out.println(Arrays.equals(d, t));
    }

}
