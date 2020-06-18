package test.java.stringsearch;

import main.java.stringsearch.KMP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class KmpTest {
    @Test
    public void testLpsTable01() {
        int[] table = KMP.buildLpsTable("abcdefghijklm");
        assertArrayEquals(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0},
                table);
    }

    @Test
    public void testLpsTable02() {
        int[] table = KMP.buildLpsTable("abcabcabc");
        assertArrayEquals(new int[]{0,0,0,1,2,3,4,5,6},
                table);
    }

    @Test
    public void testLpsTable03() {
        int[] table = KMP.buildLpsTable("abcabdabc");
        assertArrayEquals(new int[]{0,0,0,1,2,0,1,2,3},
                table);
    }

    @Test
    public void testLpsTable04() {
        int[] table = KMP.buildLpsTable("aabaacabd");
        assertArrayEquals(new int[]{0,1,0,1,2,0,1,0,0},
                table);
    }

    @Test
    public void testLpsTable05() {
        int[] table = KMP.buildLpsTable("abcdabca");
        assertArrayEquals(new int[]{0,0,0,0,1,2,3,1},
                table);
    }

    @Test
    public void testLpsTable06() {
        int[] table = KMP.buildLpsTable("aabaabaaa");
        assertArrayEquals(new int[]{0,1,0,1,2,3,4,5,2},
                table);
    }
}
