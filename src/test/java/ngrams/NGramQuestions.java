package test.java.ngrams;

import main.java.ngrams.NGrams;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;

public class NGramQuestions {
    @Test
    public void ngramAssignmentAnswers() throws IOException {
        String text = Util.readString("data/war_and_peace.txt");
        NGrams nGram = new NGrams(5, text);


    }
}
