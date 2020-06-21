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

    @Test
    public void testTrain05() {
        NGrams nGrams = new NGrams(0, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain06() {
        NGrams nGrams = new NGrams(1, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}, a={b=1.0}, b={c=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain07() {
        NGrams nGrams = new NGrams(10, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}, a={b=1.0}, ab={c=1.0}, bc={d=1.0}, b={c=1.0}, abc={d=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain08() {
        NGrams nGrams = new NGrams(3, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}, a={b=1.0}, ab={c=1.0}, bc={d=1.0}, b={c=1.0}, abc={d=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain09() {
        NGrams nGrams = new NGrams(2, "abcd");
        assertEquals("{={a=0.25, b=0.25, c=0.25, d=0.25}, a={b=1.0}, ab={c=1.0}, bc={d=1.0}, b={c=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testTrain10() {
        NGrams nGrams = new NGrams(4, "aacd");
        assertEquals("{={a=0.5, c=0.25, d=0.25}, aa={c=1.0}, a={a=0.5, c=0.5}, aac={d=1.0}, ac={d=1.0}, c={d=1.0}}", nGrams.prefixes.toString());
    }

    @Test
    public void testMostLikely01() {
        NGrams nGrams = new NGrams(4, "aacd");
        assertEquals('c', nGrams.findMostLikelyChar("aa").getKey());
    }

    @Test
    public void testMostLikely02() {
        NGrams nGrams = new NGrams(4, "acadac");
        assertEquals('c', nGrams.findMostLikelyChar("a").getKey());
    }

    @Test
    public void testMostLikelyBackOff01() {
        NGrams nGrams = new NGrams(4, "abcd");
        assertEquals('d', nGrams.findMostLikelyChar("ccc").getKey());
    }

    @Test
    public void testMostLikelyBackOff02() {
        NGrams nGrams = new NGrams(4, "abcdccf");
        assertEquals('f', nGrams.findMostLikelyChar("ccc").getKey());
    }

    @Test
    public void testProbability01() {
        NGrams nGrams = new NGrams(4, "acad");
        assertEquals(0.5f, nGrams.findProbabilityOf("a", 'c').getKey());
        assertEquals(0.5f, nGrams.findProbabilityOf("a", 'd').getKey());
    }

    @Test
    public void testProbability02() {
        NGrams nGrams = new NGrams(4, "aaaa");
        assertEquals(1f, nGrams.findProbabilityOf("", 'a').getKey());
    }

    @Test
    public void testProbability03() {
        NGrams nGrams = new NGrams(4, "abcd");
        assertEquals(0.25f, nGrams.findProbabilityOf("", 'a').getKey());
    }

    @Test
    public void testProbability04() {
        NGrams nGrams = new NGrams(4, "ababab");
        assertEquals(1f, nGrams.findProbabilityOf("ab", 'a').getKey());
    }

    @Test
    public void testProbabilityBackOff01() {
        NGrams nGrams = new NGrams(4, "abcd");
        assertEquals(1f, nGrams.findProbabilityOf("ccc", 'd').getKey());
    }

    @Test
    public void testProbabilityBackOff02() {
        NGrams nGrams = new NGrams(4, "abcdccf");
        assertEquals(1f, nGrams.findProbabilityOf("ccc", 'f').getKey());
    }
}
