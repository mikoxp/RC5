/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author moles
 */
public class BiteOperationTest {

    private BiteOperation biteOperation = new BiteOperation();

    @Test
    public void reversibilityOfRotation_sameNumber_reversibility() {
        BigInteger bigInteger = new BigInteger("abcd".getBytes());
        int a = bigInteger.intValue();
        int b = a;
        int number = 32;
        int n = 150;
        for (int i = 0; i < n; i++) {
            b = biteOperation.rotateLeft(b, number);

        }
        for (int i = 0; i < n; i++) {
            b = biteOperation.rotateRight(b, number);
        }
        Assert.assertEquals(a, b);
    }

    @Test
    public void reversibilityOfRotation_randomNumber_reversibility() {
        BigInteger bigInteger = new BigInteger("abcd".getBytes());
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        int a = bigInteger.intValue();
        int b = a;
        int number = 3;
        int n = 150;
        for (int i = 0; i < n; i++) {
            number = random.nextInt() % 32;
            number = Math.abs(number);
            integers.add(number);
        }
        for (int i = 0; i < n; i++) {
            number = integers.get(i);
            b = biteOperation.rotateLeft(b, number);

        }
        for (int i = n - 1; i >= 0; i--) {
            number = integers.get(i);
            b = biteOperation.rotateRight(b, number);
        }
        Assert.assertEquals(a, b);
    }
}
