package test.java.lempelziv;

import main.java.lempelziv.LempelZiv;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
