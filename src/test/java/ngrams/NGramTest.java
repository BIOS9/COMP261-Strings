package test.java.ngrams;

import main.java.ngrams.NGrams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NGramTest {
    @Test
    public void testTrain01() {
        NGrams nGrams = new NGrams(4, "a");
        assertEquals("{={a=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain02() {
        NGrams nGrams = new NGrams(4, "ab");
        assertEquals("{={a=0.5, b=0.5}, a={b=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain03() {
        NGrams nGrams = new NGrams(4, "aa");
        assertEquals("{={a=1.0}, a={a=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain04() {
        NGrams nGrams = new NGrams(4, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}, a={b=1.0}, ab={c=1.0}, bc={d=1.0}, b={c=1.0}, abc={d=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

}
