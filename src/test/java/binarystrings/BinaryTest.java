package test.java.binarystrings;

import javafx.util.Pair;
import main.java.binarystrings.BinaryStrings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTest {
    @Test
    public void TestEncode01() {
        String encoded = BinaryStrings.encode("1", "0");
        assertEquals("110", encoded);
    }

    @Test
    public void TestEncode02() {
        String encoded = BinaryStrings.encode("110", "100");
        assertEquals("11110100", encoded);
    }

    @Test
    public void TestDecode01() {
        Pair<String, String> decoded = BinaryStrings.decode("110");
        assertEquals("1", decoded.getKey());
        assertEquals("0", decoded.getValue());
    }

    @Test
    public void TestDecode02() {
        Pair<String, String> decoded = BinaryStrings.decode("11110100");
        assertEquals("110", decoded.getKey());
        assertEquals("100", decoded.getValue());
    }
}
