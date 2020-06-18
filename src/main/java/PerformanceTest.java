package main.java;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PerformanceTest {
    private static final int ITERATIONS = 1000;
    private static final int JVM_WARM_UP = 500;

    private static String data;
    private static String pattern = "personality--free";
    static {
        try {
            data = Files.lines(Paths.get("data/war_and_peace.txt")).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println("Failed to read data file.");
            e.printStackTrace();
        }
    }

    private long time(Runnable action, int iterations, int warmUp) {
        // Warm up to JVM to ensure lazy loading and JIT dont skew results
        for(int i = 0; i < warmUp; ++i) {
            action.run();
        }

        long millisStart = System.currentTimeMillis();
        for(int i = 0; i < iterations; ++i) {
            action.run();
        }
        return System.currentTimeMillis() - millisStart;
    }

    private void testPerformance(Runnable action, String name) {
        long duration = time(action, ITERATIONS, JVM_WARM_UP);
        double timePerItem = duration / 1000.0;
        System.out.printf("Time taken for %s: %d ms for %d iterations | %.2f microseconds per operation.\n", name, duration, ITERATIONS, timePerItem);
    }

    @Test
    public void TestBruteForce() throws IOException {
        BruteForce bf = new BruteForce();
        testPerformance(() -> {
            bf.search(pattern, data);
        }, "Brute Force");
    }

    @Test
    public void TestKMP() {

    }

}
