/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotate;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author moles
 */
public class BiteOperationTest {
    
   private BiteOperation biteOperation=new BiteOperation();
   
    @Test
    public void testSomeMethod() {
         BigInteger bigInteger=new BigInteger("abcd".getBytes());
         int a= bigInteger.intValue();
        int b=a;
        int number=3;
        int n=50;
        for(int i=0;i<n;i++){
            b=biteOperation.rotateLeft(b, number);
            
        }
        for(int i=0;i<n;i++){
            b=biteOperation.rotateRight(b, number);
        }
        Assert.assertEquals(a, b);
    }
    
}
