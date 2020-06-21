package main.java.ngrams;

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

        for(int i = 0; i < data.length; ++i) {
            for(int prefixLength = 0; prefixLength < maxPrefixSize; ++prefixLength) {
                if(i + prefixLength >= data.length) // Skip prefixes that go out of bounds
                    continue;
                String prefix = String.valueOf(Arrays.copyOfRange(data, i, i + prefixLength));
                char nextChar = data[i + prefixLength];

                if(prefixCounts.containsKey(prefix)) {
                    Map<Character, Integer> map = prefixCounts.get(prefix);
                    if(map.containsKey(nextChar)) {
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

        for(Map.Entry<String, Map<Character, Integer>> prefix : prefixCounts.entrySet()) {
            int count = prefix.getValue().values().stream().mapToInt(Integer::intValue).sum();
            prefixes.put(prefix.getKey(), prefix.getValue().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() / (float)count)));
        }
    }
}
