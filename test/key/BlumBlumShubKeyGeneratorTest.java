/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author moles
 */
public class BlumBlumShubKeyGeneratorTest {

    private final BlumBlumShubKeyGenerator generator;
    /**
     * constructor
     */
    public BlumBlumShubKeyGeneratorTest() {
        generator = new BlumBlumShubKeyGenerator();
    }
    /**
     * did generate unique byte array
     */
    @Test
    public void generate_umiqueKey_unique() {
        List<byte[]> list = new ArrayList<>();
        int number = 16;
        byte[] data = generator.generate(number);
        list.add(data);
        for (int i = 0; i < 10; i++) {
            data = generator.generate(number);
            for (byte[] b : list) {
                assertNotEquals("not only passed", Arrays.toString(data), Arrays.toString(b));
            }
            list.add(data);
        }
    }

}
