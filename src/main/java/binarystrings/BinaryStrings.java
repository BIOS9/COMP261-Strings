package main.java.binarystrings;

import javafx.util.Pair;

public class BinaryStrings {
    public static String encode(String a, String b) {
        int totalLength = a.length() + b.length();
        String binary = Integer.toBinaryString(a.length());
        int binaryBits = (int)(Math.log(totalLength + 1) / Math.log(2));
        while (binary.length() < binaryBits) {
            binary = "0" + binary;
        }
        return binary + a + b;
    }

    public static Pair<String, String> decode(String c) {
        int n = c.length();
        while (n > 0) {
            double l = Math.log(n + 1) / Math.log(2);

            if(l + n <= c.length()) {
                int aLength = Integer.valueOf(c.substring(0, (int)l), 2);
                System.out.println(aLength);
                String a = c.substring((int)l, aLength + (int)l);
                String b = c.substring((int)l + aLength);
                return new Pair<>(a, b);
            }
            --n;
        }

        return null;
    }
}
