package main.java.ngrams;

import javafx.util.Pair;

import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NGrams {
    public final Map<String, Map<Character, Float>> prefixes = new HashMap<>();
    public final int maxPrefixSize;

    public NGrams(int maxPrefixSize) {
        this.maxPrefixSize = maxPrefixSize;
    }

    public NGrams(int maxPrefixSize, String text) {
        this.maxPrefixSize = maxPrefixSize;
        train(text);
    }

    public void train(String text) {
        Map<String, Map<Character, Integer>> prefixCounts = new HashMap<>();

        prefixes.clear();
        char[] data = text.toCharArray();

        for (int i = 0; i < data.length; ++i) {
            for (int prefixLength = 0; prefixLength <= maxPrefixSize; ++prefixLength) {
                if (i + prefixLength >= data.length) // Skip prefixes that go out of bounds
                    continue;
                String prefix = String.valueOf(Arrays.copyOfRange(data, i, i + prefixLength));
                char nextChar = data[i + prefixLength];

                if (prefixCounts.containsKey(prefix)) {
                    Map<Character, Integer> map = prefixCounts.get(prefix);
                    if (map.containsKey(nextChar)) {
                        map.put(nextChar, map.get(nextChar) + 1);
                    } else {
                        map.put(nextChar, 1);
                    }
                } else {
                    Map<Character, Integer> map = new HashMap<>();
                    map.put(nextChar, 1);
                    prefixCounts.put(prefix, map);
                }
            }
        }

        for (Map.Entry<String, Map<Character, Integer>> prefix : prefixCounts.entrySet()) {
            int count = prefix.getValue().values().stream().mapToInt(Integer::intValue).sum();
            prefixes.put(prefix.getKey(), prefix.getValue().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / (float) count)));
        }
    }

    /**
     * Finds the most likely char to appear next given a prefix.
     * @param prefix Prefix to use for probability search.
     * @return The best match prefix found after backing-off if no matches found.
     */
    public Pair<Character, String> findMostLikelyChar(String prefix) {
        while (prefix.length() >= 0) {
            if (prefixes.containsKey(prefix)) {
                Map<Character, Float> chars = prefixes.get(prefix);
                if(!chars.isEmpty()) {
                    char bestChar = 0;
                    float prob = 0;
                    for(Map.Entry<Character, Float> c : chars.entrySet()) {
                        if(c.getValue() > prob) {
                            bestChar = c.getKey();
                            prob = c.getValue();
                        }
                    }
                    return new Pair(bestChar, prefix);
                }
            }
            prefix = prefix.substring(1);
        }

        return null;
    }

    /**
     * Finds the probability of a char to appear next given a prefix.
     * @param prefix Prefix to use for probability search.
     * @return The best match prefix found after backing-off if no matches found.
     */
    public Pair<Float, String> findProbabilityOf(String prefix, char c) {
        while (prefix.length() >= 0) {
            if (prefixes.containsKey(prefix)) {
                Map<Character, Float> chars = prefixes.get(prefix);
                if(chars.containsKey(c)) {
                    return new Pair(chars.get(c), prefix);
                }
            }
            prefix = prefix.substring(1);
        }

        return null;
    }

    /**
     * Finds probability of a string occurring.
     * @param text Text to find probability of.
     * @return Log2 probability of the text occurring.
     */
    public float findLogProbabilityOf(String text) {
        float logProb = 0;
        for(int i = 0; i < text.length(); ++i) {
            int nInit = Math.min(maxPrefixSize, i); // Size of prefix. Cuts smaller at start of string.
            char c = text.charAt(i);
            String prefix = text.substring(i - nInit, i);
            Pair<Float, String> prob = findProbabilityOf(prefix, c);
            float finalProb = prob.getKey() == 0 ? 1e-10f : prob.getKey();
            logProb += (Math.log(finalProb) / Math.log(2) + 1e-10);
        }

        return logProb;
    }
}
