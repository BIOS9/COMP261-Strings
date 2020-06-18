package test.java.stringsearch;

import main.java.stringsearch.KMP;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class KmpTest {
    @Test
    public void testLpsTable01() {
        int[] table = KMP.buildLpsTable("abcdefghijklm".toCharArray());
        assertArrayEquals(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0},
                table);
    }

    @Test
    public void testLpsTable02() {
        int[] table = KMP.buildLpsTable("abcabcabc".toCharArray());
        assertArrayEquals(new int[]{0,0,0,1,2,3,4,5,6},
                table);
    }

    @Test
    public void testLpsTable03() {
        int[] table = KMP.buildLpsTable("abcabdabc".toCharArray());
        assertArrayEquals(new int[]{0,0,0,1,2,0,1,2,3},
                table);
    }

    @Test
    public void testLpsTable04() {
        int[] table = KMP.buildLpsTable("aabaacabd".toCharArray());
        assertArrayEquals(new int[]{0,1,0,1,2,0,1,0,0},
                table);
    }

    @Test
    public void testLpsTable05() {
        int[] table = KMP.buildLpsTable("abcdabca".toCharArray());
        assertArrayEquals(new int[]{0,0,0,0,1,2,3,1},
                table);
    }

    @Test
    public void testLpsTable06() {
        int[] table = KMP.buildLpsTable("aabaabaaa".toCharArray());
        assertArrayEquals(new int[]{0,1,0,1,2,3,4,5,2},
                table);
    }

    @Test
    public void testSimpleSearch01() {
        KMP kmp = new KMP("abc");
        assertEquals(5, kmp.search("defghabcgef"));
    }

    @Test
    public void testSimpleSearch02() {
        KMP kmp = new KMP("abc");
        assertEquals(7, kmp.search("abacbbcabc"));
    }

    @Test
    public void testSimpleSearch03() {
        KMP kmp = new KMP("abc");
        assertEquals(19, kmp.search("aaaaabbbbbcccaabbcaabc"));
    }

    @Test
    public void testWarAndPeace() throws IOException {
        StringSearchTest.testStringSearch(new KMP(), Files.readAllLines(Paths.get("data/war_and_peace.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testTaisho() throws IOException {
        StringSearchTest.testStringSearch(new KMP(), Files.readAllLines(Paths.get("data/taisho.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testPi() throws IOException {
        StringSearchTest.testStringSearch(new KMP(), Files.readAllLines(Paths.get("data/pi.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testLenna() throws IOException {
        StringSearchTest.testStringSearch(new KMP(), Files.readAllLines(Paths.get("data/Lenna.txt")).stream().collect(Collectors.joining("\n")));
    }

    @Test
    public void testApollo() throws IOException {
        StringSearchTest.testStringSearch(new KMP(), Files.readAllLines(Paths.get("data/Apollo.txt")).stream().collect(Collectors.joining("\n")));
    }
}
