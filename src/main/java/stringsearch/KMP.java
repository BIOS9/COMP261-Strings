package main.java.stringsearch;

import java.util.HashMap;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP implements StringSearcher {
	private String pattern;
	private int[] table;

	public KMP(String pattern) {
		setPattern(pattern);
	}

	public KMP() { }

	public void setPattern(String pattern) {
		this.pattern = pattern;
		table = buildLpsTable(pattern);
	}

	public String getPattern() {
		return pattern;
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String text) {
		String pattern = this.pattern; // To mitigate search failure if pattern is changed concurrently
		int[] table = this.table;
		int i = 0, j = 0;

		while (i < text.length()) {
			if(text.charAt(i) == pattern.charAt(j)) {
				++i; ++j;

				if(j == table.length - 1) { // Match found
					return i - j;
				}
			} else if(j == 0) {
				++i;
			} else {
				j = table[j - 1];
			}
		}

		return -1;
	}

	public static int[] buildLpsTable(String pattern) {
		int i = 1, j = 0;
		int[] table = new int[pattern.length()];
		table[0] = 0;

		while (i < table.length) {
			if(pattern.charAt(i) == pattern.charAt(j)) {
				table[i] = j + 1;
				++i; ++j;
			} else if(j == 0) {
				table[i] = 0;
				++i;
			} else {
				j = table[j - 1];
			}
		}

		return table;
	}
}
