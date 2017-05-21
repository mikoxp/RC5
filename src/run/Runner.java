
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
   
//        BlockTests blockTests=new BlockTests();
//        try {
//            blockTests.timeToEncryptTheNumberOfBlocks();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
//        }
       
        
    }

}
