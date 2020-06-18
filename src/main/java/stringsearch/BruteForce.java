package main.java.stringsearch;

public class BruteForce implements StringSearcher {
	private String pattern;

	public BruteForce(String pattern) {
		setPattern(pattern);
	}

	public BruteForce() {}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return this.pattern;
	}

	public int search(String text) {
		// using char arrays is MUCH faster than using String::charAt, so if the string is long enough it's worth converting to arr
		// Also means that concurrent modification of the global pattern will not cause results here to be incorrect
		char[] pattern = this.pattern.toCharArray();
		char[] data = text.toCharArray();

		for(int iText = 0; iText < (data.length - pattern.length) + 1; ++iText) {
			for(int iPattern = 0; iPattern < pattern.length; ++iPattern) {
				if(data[iText + iPattern] != pattern[iPattern]) {
					break;
				}

				if(iPattern == pattern.length - 1) {
					return iText; // Get starting index of match in text
				}
			}
		}

 		return -1; // No results found
	}
}
