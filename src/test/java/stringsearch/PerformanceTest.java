package test.java.stringsearch;

import main.java.stringsearch.BruteForce;
import main.java.stringsearch.KMP;
import main.java.stringsearch.StringSearcher;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PerformanceTest {
    private static final int ITERATIONS = 2; // Marker may DECREASE this (min 1) if testing is too slow.
    private static final int JVM_WARM_UP = 1; // Marker may make this 0 if testing is too slow.
    private static final int DATASET_DIVISION = 8; // Marker may INCREASE this if testing is too slow.

    private long time(Runnable action, int iterations, int warmUp) {
        // Warm up to JVM to help ensure lazy loading and JIT dont skew results
        for (int i = 0; i < warmUp; ++i) {
            action.run();
        }

        long millisStart = System.currentTimeMillis();
        for (int i = 0; i < iterations; ++i) {
            action.run();
        }
        return (System.currentTimeMillis() - millisStart) / iterations;
    }

    private long timePatterns(StringSearcher searcher, Set<String> patterns, String data, String searcherName, String patternsName) {
        long time = time(() -> {
            for (String word : patterns) {
                searcher.setPattern(word);
                searcher.search(data);
            }
        }, ITERATIONS, JVM_WARM_UP);

        System.out.printf("Time taken for %s: %d ms for %d %s patterns | %.2f ms per pattern.\n", searcherName, time, patterns.size(), patternsName, time / (double)patterns.size());
        return time;
    }

    private void testPerformance(StringSearcher searcher, String name) throws IOException {
        System.out.println("Setting up...");
        String data = Util.readString("data/war_and_peace.txt");
        data = data.substring(0, data.length() / DATASET_DIVISION); // Data is too big and tests take too long, make it smaller

        String badData = Files.readAllLines(Paths.get("data/taisho.txt")).stream().collect(Collectors.joining("\n"));
        badData = badData.substring(0, data.length() / DATASET_DIVISION); // Data is too big and tests take too long, make it smaller

        Set<String> wordlist = new HashSet<>();
        wordlist.addAll(data.chars().parallel().distinct().boxed().map((x) -> String.valueOf((char) x.intValue())).collect(Collectors.toSet()));
        wordlist.addAll(Arrays.stream(data.split("[ \\s-]")).parallel().collect(Collectors.toSet()));
        wordlist.add(data);

        wordlist.addAll(wordlist.stream().parallel().map(x -> StringSearchTest.shuffle(x)).collect(Collectors.toSet()));

        wordlist.addAll(badData.chars().parallel().distinct().boxed().map((x) -> String.valueOf((char) x.intValue())).collect(Collectors.toSet()));
        wordlist.addAll(Arrays.stream(badData.split("[ \\s-]")).parallel().collect(Collectors.toSet()));
        wordlist.add(badData);

        wordlist.remove("");

        Set<String> tinyPatterns = wordlist.stream().parallel().filter((x) -> x.length() < 5).collect(Collectors.toSet());
        Set<String> smallPatterns = wordlist.stream().parallel().filter((x) -> !tinyPatterns.contains(x) && x.length() < 10).collect(Collectors.toSet());
        Set<String> mediumPatterns = wordlist.stream().parallel().filter((x) -> !smallPatterns.contains(x) && x.length() < 50).collect(Collectors.toSet());
        Set<String> largePatterns = wordlist.stream().parallel().filter((x) -> !mediumPatterns.contains(x) && x.length() < 100).collect(Collectors.toSet());
        Set<String> hugePatterns = wordlist.stream().parallel().filter((x) -> !largePatterns.contains(x) && x.length() >= 100).collect(Collectors.toSet());

        System.out.println("Testing...");
        long tinyDuration = timePatterns(searcher, tinyPatterns, data, name, "TINY");
        long smallDuration = timePatterns(searcher, smallPatterns, data, name, "SMALL");
        long mediumDuration = timePatterns(searcher, mediumPatterns, data, name, "MEDIUM");
        long largeDuration = timePatterns(searcher, largePatterns, data, name, "LARGE");
        long hugeDuration = timePatterns(searcher, hugePatterns, data, name, "HUGE");

        System.out.printf("Total time taken for %s was %.2f seconds.\n", name, (tinyDuration + smallDuration + mediumDuration + largeDuration + hugeDuration) / 1000f);
    }


    @Test
    public void TestBruteForce() throws IOException {
        testPerformance(new BruteForce(), "Brute Force");
    }

    @Test
    public void TestKMP() throws IOException {
        testPerformance(new KMP(), "KMP");
    }

}
