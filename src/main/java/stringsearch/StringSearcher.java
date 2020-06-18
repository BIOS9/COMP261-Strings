package main.java.stringsearch;

public interface StringSearcher {
    void setPattern(String pattern);
    String getPattern();
    int search(String text);
}
