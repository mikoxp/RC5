package run;

import exception.CoderException;
import exception.KeyException;
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
    public static void main(String[] args) throws KeyException, CoderException {
        int numberOfRounds = 255;
        int sizeOfKey=10;
        String text = "1234567890abcdef1234567890abcdef";
        RC5Key rc5key = new RC5Key(numberOfRounds, sizeOfKey);
        RC5Coder rC5Coder = new RC5Coder(numberOfRounds);
        byte[] encrypt = rC5Coder.encrypt(text.getBytes(), rc5key,5);
        byte[] decrypt = rC5Coder.decrypt(encrypt, rc5key);
        //System.out.println("Key:"+new String(rc5key.getKey()));
        System.out.println(text);
        System.out.println(new String(encrypt));
        System.out.println(new String(decrypt));
    }
}
