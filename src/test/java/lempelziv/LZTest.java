package test.java.lempelziv;

import main.java.lempelziv.LempelZiv;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LZTest {
    @Test
    public void testPrefixSearch01() {
        char[] test = "hellohello".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(0, result[0], "Best match index was wrong.");
        assertEquals(5, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch02() {
        char[] test = "helaahello".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(0, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch03() {
        char[] test = "abcdebcabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(1, result[0], "Best match index was wrong.");
        assertEquals(2, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch04() {
        char[] test = "abcdeddddd".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 5, test.length, 0, 5);
        assertEquals(3, result[0], "Best match index was wrong.");
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
        assertEquals(0, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch09() {
        char[] test = "abcdefgabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 7, test.length, 0, 7);
        assertEquals(0, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch10() {
        char[] test = "ababcdabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 6, test.length, 0, 6);
        assertEquals(2, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }

    @Test
    public void testPrefixSearch11() {
        char[] test = "abcabdabc".toCharArray();
        int[] result = LempelZiv.findPrefix(test, 6, test.length, 0, 6);
        assertEquals(0, result[0], "Best match index was wrong.");
        assertEquals(3, result[1], "Best match length was wrong.");
    }
}
