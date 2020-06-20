package main.java.stringsearch;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP implements StringSearcher {
	private char[] pattern;
	private int[] table;

	public KMP(String pattern) {
		setPattern(pattern);
	}

	public KMP() { }

	public void setPattern(String pattern) {
		this.pattern = pattern.toCharArray();
		this.table = buildLpsTable(this.pattern);
	}

	public String getPattern() {
		return String.valueOf(pattern);
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String text) {
		// using char arrays is MUCH faster than using String::charAt, so if the string is long enough it's worth converting to arr
		char[] pattern = this.pattern; // Prevents concurrent calling of setPattern causing failure here
		char[] data = text.toCharArray();
		int[] table = this.table;
		int i = 0, j = 0;

		while (i < data.length) {
			if(data[i] == pattern[j]) {
				++i; ++j;

				if(j == table.length) { // Match found
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

	public static int[] buildLpsTable(char[] pattern) {
		int i = 1, j = 0;
		// Array faster than String::charAt
		int[] table = new int[pattern.length];
		table[0] = 0;

		while (i < pattern.length) {
			if(pattern[i] == pattern[j]) {
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
