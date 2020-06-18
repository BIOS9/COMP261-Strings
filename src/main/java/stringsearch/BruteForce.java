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
		String pattern = this.pattern; // To mitigate search failure if pattern is changed concurrently
		for(int iText = 0; iText < text.length(); ++iText) {
			for(int iPattern = 0; iPattern < pattern.length(); ++iPattern) {
				if(text.charAt(iText + iPattern) != pattern.charAt(iPattern)) {
					break;
				}

				if(iPattern == pattern.length() - 1) {
					return iText; // Get starting index of match in text
				}
			}
		}

 		return -1; // No results found
	}
}
