package test;

import java.util.BitSet;
import java.security.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BloomFilter {
    BitSet bitSet;
    String[] algs;
    int size;
    public BloomFilter(int bytesLength, String... algs) {
        this.size = bytesLength;
        bitSet = new BitSet();
        this.algs = algs;
    }

    public void add(String w) {
        byte[] bts;
        for (String string : algs) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bts = md.digest(w.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = a.intValue();
                test = Math.abs(test) % size;
                bitSet.set(test);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean contains(String w) {
        byte[] bts;
        for (String string :algs) {
            try {
                MessageDigest md = MessageDigest.getInstance(string);
                bts = md.digest(w.getBytes());
                BigInteger a = new BigInteger(bts);
                int test = a.intValue();
                test = Math.abs(test) % size;
                if (bitSet.get(test))
                    return true;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            string.append(bitSet.get(i) ? "1" : "0");
        }
        return string.toString();    }
}
