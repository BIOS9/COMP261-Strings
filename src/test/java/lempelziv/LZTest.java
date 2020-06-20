package test.java.lempelziv;

import main.java.lempelziv.LempelZiv;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LZTest {
    @Test
    public void testPrefixSearch01() {
        char[] test = "hellohello".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(5, result[0], "Best match index was wrong.");
        assertEquals(5, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch02() {
        char[] test = "helaahello".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(5, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch03() {
        char[] test = "abcdebcabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(4, result[0], "Best match index was wrong.");
        assertEquals(2, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch04() {
        char[] test = "abcdeddddd".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(2, result[0], "Best match index was wrong.");
        assertEquals(1, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch05() {
        char[] test = "abcdefffff".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertNull(result);
    }

    @Test
    public void testPrefixSearch06() {
        char[] test = "abcddddddd".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 3, test.length, 0, 3);
        assertNull(result);
    }

    @Test
    public void testPrefixSearch07() {
        char[] test = "abcdefghhh".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 7, test.length, 0, 7);
        assertNull(result);
    }

    @Test
    public void testPrefixSearch08() {
        char[] test = "abcabcefgh".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 3, test.length, 0, 3);
        assertEquals(3, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch09() {
        char[] test = "abcdefgabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 7, test.length, 0, 7);
        assertEquals(7, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch10() {
        char[] test = "ababcdabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 6, test.length, 0, 6);
        assertEquals(4, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch11() {
        char[] test = "abcabdabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 6, test.length, 0, 6);
        assertEquals(6, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testEncode01() {
        LempelZiv lz = new LempelZiv();
        String compressed = lz.compress("Hello", 100);
        assertEquals("[0|0|H][0|0|e][0|0|l][1|1|o]", compressed);
    }

    @Test
    public void testEncode02() {
        LempelZiv lz = new LempelZiv();
        String compressed = lz.compress("Hello this is a test of the encoding", 100);
        assertEquals("[0|0|H][0|0|e][0|0|l][1|1|o][0|0| ][0|0|t][0|0|h][0|0|i][0|0|s][5|1|i][3|2|a][10|2|e][9|1|t][15|1|o][0|0|f][18|3|e][22|1|e][0|0|n][0|0|c][27|1|d][25|1|n][0|0|g]", compressed);
    }

    @Test
    public void testDecode01() {
        LempelZiv lz = new LempelZiv();
        String decompressed = lz.decompress("[0|0|H][0|0|e][0|0|l][1|1|o]");
        assertEquals("Hello", decompressed);
    }

    @Test
    public void testDecode02() {
        LempelZiv lz = new LempelZiv();
        String decompressed = lz.decompress("[0|0|H][0|0|e][0|0|l][1|1|o][0|0| ][0|0|t][0|0|h][0|0|i][0|0|s][5|1|i][3|2|a][10|2|e][9|1|t][15|1|o][0|0|f][18|3|e][22|1|e][0|0|n][0|0|c][27|1|d][25|1|n][0|0|g]");
        assertEquals("Hello this is a test of the encoding", decompressed);
    }

    @Test
    public void testApollo() throws IOException {
        LempelZiv lz = new LempelZiv();
        String data = Util.readString("data/apollo.txt");
        String compressed = lz.compress(data, 100);
        assertEquals(data, lz.decompress(compressed));
    }

    @Test
    public void testLenna() throws IOException {
        LempelZiv lz = new LempelZiv();
        String data = Util.readString("data/lenna.txt");
        String compressed = lz.compress(data, 100);
        assertEquals(data, lz.decompress(compressed));
    }

    @Test
    public void testPi() throws IOException {
        LempelZiv lz = new LempelZiv();
        String data = Util.readString("data/pi.txt");
        String compressed = lz.compress(data, 100);
        assertEquals(data, lz.decompress(compressed));
    }

    @Test
    public void testTaisho() throws IOException {
        LempelZiv lz = new LempelZiv();
        String data = Util.readString("data/taisho.txt");
        String compressed = lz.compress(data, 100);
        assertEquals(data, lz.decompress(compressed));
    }

    @Test
    public void testWarAndPeace() throws IOException {
        LempelZiv lz = new LempelZiv();
        String data = Util.readString("data/war_and_peace.txt");
        String compressed = lz.compress(data, 100);
        assertEquals(data, lz.decompress(compressed));
    }
}
