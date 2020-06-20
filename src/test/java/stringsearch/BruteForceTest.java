package test.java.stringsearch;

import main.java.stringsearch.BruteForce;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;

public class BruteForceTest {
    @Test
    public void testWarAndPeace() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Util.readString("data/war_and_peace.txt"));
    }

    @Test
    public void testTaisho() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Util.readString("data/taisho.txt"));
    }

    @Test
    public void testPi() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Util.readString("data/pi.txt"));
    }

    @Test
    public void testLenna() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Util.readString("data/lenna.txt"));
    }

    @Test
    public void testApollo() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Util.readString("data/apollo.txt"));
    }
}
