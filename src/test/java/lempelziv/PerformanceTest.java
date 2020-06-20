package test.java.lempelziv;

import main.java.lempelziv.LempelZiv;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;

public class PerformanceTest {
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

    @Test
    public void warAndPeaceTest() throws IOException {
        String data = Util.readString("data/war_and_peace.txt");
        LempelZiv lz = new LempelZiv();

        for(int i = 8; i < 2048; i *= 2) {
            int windowSize = i;
            String[] compressedData = new String[1];
            long compressTime = time(() -> {
                compressedData[0] = lz.compress(data, windowSize);
            }, 5, 2);
            long decompressTime = time(() -> {
                lz.decompress(compressedData[0]);
            }, 5, 2);
            System.out.println("LZ Compress Time: " + compressTime + "ms\t\tDecompress Time: " +  decompressTime + "ms\t\tWindow Size: " + windowSize + "\t\tSize: " + compressedData[0].length());
        }
    }
}
