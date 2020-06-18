package test.java.stringsearch;

import main.java.stringsearch.BruteForce;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class BruteForceTest {
    @Test
    public void testWarAndPeace() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Files.readAllLines(Paths.get("data/war_and_peace.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testTaisho() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Files.readAllLines(Paths.get("data/taisho.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testPi() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Files.readAllLines(Paths.get("data/pi.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testLenna() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Files.readAllLines(Paths.get("data/Lenna.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testApollo() throws IOException {
        StringSearchTest.testStringSearch(new BruteForce(), Files.readAllLines(Paths.get("data/Apollo.txt")).stream().collect(Collectors.joining("\n")));
    }
}
