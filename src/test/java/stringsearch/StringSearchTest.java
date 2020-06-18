package test.java.stringsearch;

import main.java.stringsearch.StringSearcher;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class StringSearchTest {
    public static void testStringSearch(StringSearcher searcher, String data) {
        Set<String> wordlist = new HashSet<>();
        wordlist.addAll(data.chars().parallel().distinct().boxed().map((x) -> String.valueOf((char)x.intValue())).collect(Collectors.toSet()));
        wordlist.addAll(Arrays.stream(data.split("[ \\s-]")).parallel().collect(Collectors.toSet()));
        wordlist.add(data);
        wordlist.addAll(wordlist.stream().parallel().map(x -> shuffle(x)).collect(Collectors.toSet()));
        wordlist.remove("");
        assumeFalse(wordlist.isEmpty());

        System.out.println("Testing on " + wordlist.size() + " patterns.");

        for(String word : wordlist) {
            searcher.setPattern(word);
            assertEquals(word, searcher.getPattern());

            int searcherIndex = searcher.search(data);
            int oracleIndex = data.indexOf(word);

            if(searcherIndex != oracleIndex) {
                fail("Searcher index (" + searcherIndex + ") and oracle index (" + oracleIndex + ") do not match for pattern \"" + word + "\" pattern length: " + word.length());
            }
        }
    }

    public static String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
