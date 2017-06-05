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
public class ImplementationTests {

    private RC5Coder coder;
    private RC5Key key;
    private PrintWriter printWriter;
    private final String path = "csv";

    public ImplementationTests() {
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
    private long oneTimeEncryptBlockNumber(int numberOFBlock) {
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
        int step = (int) Math.pow(10, 6);
        int counter = (end - start) / step + 1;
        for (int i = start; i <= end; i += step) {
            time = oneTimeEncryptBlockNumber(i);
            System.out.printf("%d;%d to end %d\n", i, time, counter);
            printWriter.printf("%d;%d\n", i, time);
            counter--;
        }
        printWriter.close();
        System.out.println("Finish Succesull: timeToEncryptTheNumberOfBlocks");
    }

    private long oneTimeEncryptNumberOfRounds(int numberOfRounds) throws CoderException {
        long result = 0;
        long start = 0;
        long end = 0;
        int n = (int) Math.pow(10, 7);
        byte[] data = new byte[n * 8];
        RC5Coder rc5 = new RC5Coder(numberOfRounds);
        RC5Key rc5Key = new RC5Key(numberOfRounds, 16);
        if (numberOfRounds > 35) {
            rc5Key = new RC5Key(numberOfRounds, 255);
        }

        start = System.currentTimeMillis();
        try {
            rc5.encrypt(data, rc5Key);
        } catch (CoderException ex) {
            return -1;
        }
        end = System.currentTimeMillis();
        result = end - start;
        return result;
    }

    public void timeToEncryptNumberOFRounds() throws FileNotFoundException, CoderException {
        Date d = new Date();
        long time;
        String fileName = path + "//number of rounds " + d.getDate() + "-" + d.getMonth() + " time " + d.getTime() + ".csv";
        printWriter = new PrintWriter(new File(fileName));
        System.out.println("Start: timeToEncryptNumberOFRounds");
        time = oneTimeEncryptNumberOfRounds(1);
        System.out.printf("%d;%d \n", 1, time);
        printWriter.printf("%d;%d\n", 1, time);
        for (int i = 5; i <= 255; i += 5) {
            time = oneTimeEncryptNumberOfRounds(i);
            System.out.printf("%d;%d\n ", i, time);
            printWriter.printf("%d;%d\n", i, time);
        }
        printWriter.close();
        System.out.println("Finish Succesull: timeToEncryptNumberOFRounds");
    }
    private long oneTimeEncryptNumberOfThreads(int numberOThreads) throws CoderException {
        long result = 0;
        long start = 0;
        long end = 0;
        int n = (int) Math.pow(10, 7);
        byte[] data = new byte[n * 8];
       

        start = System.currentTimeMillis();
        try {
            coder.encrypt(data, key, numberOThreads);
        } catch (CoderException ex) {
            return -1;
        }
        end = System.currentTimeMillis();
        result = end - start;
        return result;
    }
    public void timeToEncryptNumberOFThreads() throws FileNotFoundException, CoderException {
        Date d = new Date();
        long time;
        String fileName = path + "//Threads " + d.getDate() + "-" + d.getMonth() + " time " + d.getTime() + ".csv";
        printWriter = new PrintWriter(new File(fileName));
        System.out.println("Start: timeToEncryptNumberOFThreads");
        
        for (int i = 1; i <= 10; i ++) {
            time = oneTimeEncryptNumberOfThreads(i);
            System.out.printf("%d;%d\n ", i, time);
            printWriter.printf("%d;%d\n", i, time);
        }
        printWriter.close();
        System.out.println("Finish Succesull:timeToEncryptNumberOFThreads");
    }
}
