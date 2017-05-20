/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package time.tests;

import exception.CoderException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import rc5.RC5Coder;
import rc5.RC5Key;

/**
 *
 * @author moles
 */
public class BlockTests {

    private RC5Coder coder;
    private RC5Key key;
    private PrintWriter printWriter;
    private final String path = "csv";

    public BlockTests() {
        try {
            coder = new RC5Coder(255);
            key = new RC5Key(255, 255);
        } catch (CoderException ex) {

        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     *
     * @return
     */
    private long oneTimeEncrypt(int numberOFBlock) {
        long result = 0;
        long start = 0;
        long end = 0;
        byte[] data = new byte[numberOFBlock * 8];
        start = System.currentTimeMillis();
        try {
            coder.encrypt(data, key);
        } catch (CoderException ex) {
            return -1;
        }
        end = System.currentTimeMillis();
        result = end - start;
        return result;
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void timeToEncryptTheNumberOfBlocks() throws FileNotFoundException {
        Date d = new Date();
        long time = 0;
        String fileName = path + "//number of block " + d.getDate() + "-" + d.getMonth() + " time " + d.getTime() + ".csv";
        printWriter = new PrintWriter(new File(fileName));
        int start = (int) Math.pow(10, 6);
        int end = (int) Math.pow(10, 7);
        int step = 5 * (int) Math.pow(10, 5);
        int counter = (end - start) / step + 1;
        for (int i = start; i <= end; i += step) {
            time += oneTimeEncrypt(i);
            System.out.printf("%d;%d to end %d\n", i, time, counter);
            printWriter.printf("%d;%d\n", i, time);
            counter--;
        }
        printWriter.close();
        System.out.println("Finish Succesull: timeToEncryptTheNumberOfBlocks");
    }

}
