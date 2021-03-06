package rc5;

import exception.KeyException;
import key.BlumBlumShubKeyGenerator;
import rotate.IntegerBiteOperation;

/**
 *
 * @author moles
 */
public class RC5Key{

    private final int P = 0xb7e15163;
    private final int Q = 0x9e3779b9;
    private final int sizeOfWordInBite = 32;
    private final int sizeOfWordInByte = sizeOfWordInBite / 8;
    private final int sizeKeyInByte;
    private int numberOfWords;
    private final int numberOfRounds;
    private int c;
    private byte[] key;
    private int[] words;
    private final IntegerBiteOperation biteOperation;

    /**
     *
     * @param numberOfRounds number of rounds
     * @param key key byte
     * @throws exception.KeyException key exception
     */
    public RC5Key(int numberOfRounds, byte[] key) throws KeyException {
        if (numberOfRounds < 1 || numberOfRounds > 255) {
            throw new KeyException("Number of Round must be in (1,255)");
        }
        this.numberOfRounds = numberOfRounds;
        this.key = key;
        biteOperation = new IntegerBiteOperation();
        if (key == null) {
            sizeKeyInByte = 0;
        } else {
            sizeKeyInByte = key.length;
        }
        if (sizeKeyInByte > 255) {
            throw new KeyException("Key lenght must be in (0,255)");
        }
        init(numberOfRounds);
    }

    /**
     *
     * @param numberOfRounds number of rounds
     * @param sizeKeyInByte size of key
     */
    public RC5Key(int numberOfRounds, int sizeKeyInByte) {
        this.numberOfRounds = numberOfRounds;
        biteOperation = new IntegerBiteOperation();
        this.sizeKeyInByte = sizeKeyInByte;
        key = generateKey();
        init(numberOfRounds);
    }

    /**
     *
     * @param numberOfRounds number of rounds
     */
    private void init(int numberOfRounds) {
        c = sizeKeyInByte / sizeOfWordInByte;
        if (c == 0) {
            c = 1;
        }
        numberOfWords = 2 * (numberOfRounds + 1);
        if (!(numberOfWords > (sizeKeyInByte - 1) / sizeOfWordInByte)) {
            throw new RuntimeException("The key is too long for the number of rounds");
        }
        generateWords();
    }

    /**
     *
     * @return generate key
     */
    private byte[] generateKey() {
        BlumBlumShubKeyGenerator gen = new BlumBlumShubKeyGenerator();
        return gen.generate(sizeKeyInByte);
    }

    /**
     * generate words
     */
    private void generateWords() {
        int[] L = new int[numberOfWords];
        int n;
        int number;
        int a = 0;
        int b = 0;
        int x = 0;
        int y = 0;
        words = new int[numberOfWords];
        //------------------------------------
        for (int i = sizeKeyInByte - 1; i >= 0; i--) {
            L[i / sizeOfWordInByte] = (L[i / sizeOfWordInByte] << 8) + key[i];
        }
        words[0] = P;
        for (int i = 1; i < numberOfWords; i++) {
            words[i] = words[i - 1] + Q;
        }
        if (sizeKeyInByte > c) {
            n = sizeKeyInByte;
        } else {
            n = c;
        }
        for (int i = 0; i < 3 * n; i++) {
            words[x] = biteOperation.rotateLeft(words[x] + a + b, 3);
            a = words[x];
            number = (a + b) % 32;
            L[y] = biteOperation.rotateLeft(L[y] + a + b, number);
            b = L[y];
            x = (x + 1) % numberOfWords;
            y = (y + 1) % c;
        }
    }

    /**
     *
     * @return key bytes
     */
    public byte[] getKey() {
        return key;
    }

    /**
     *
     * @return words
     */
    public int[] getWords() {
        return words;
    }

    /**
     *
     * @return number of rounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

}
