package test.java.ngrams;

import javafx.util.Pair;
import main.java.ngrams.NGrams;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;

public class NGramQuestions {
    @Test
    public void ngramAssignmentAnswers() throws IOException {
        String text = Util.readString("data/war_and_peace.txt");
        NGrams nGram = new NGrams(5, text);
        System.out.println("ngram now trained on war_and_peace.txt");
        String sMaori = "Turn your face to the sun and the shadows fall behind you, translation";
        String sEnglish = "Hurihia to aroaro ki te ra tukuna to atarangi kia taka ki muri i a koe";

        Pair<Float, String> q1 = nGram.findProbabilityOf("a tak", 'a');
        System.out.println("Probability of 'a' after \"a tak\": " + q1.getKey() + " n: " + q1.getValue().length() + "\n");

        float maoriResult = nGram.findLogProbabilityOf(sMaori);
        System.out.println("Maori text: " + sMaori);
        System.out.println("Log2 Probability: " + maoriResult + "\n");

        float englishResult = nGram.findLogProbabilityOf(sEnglish);
        System.out.println("English text: " + sEnglish);
        System.out.println("Log2 Probability: " + englishResult + "\n");
    }
}
