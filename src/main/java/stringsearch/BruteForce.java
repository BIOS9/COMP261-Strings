package main.java.stringsearch;

public class BruteForce implements StringSearcher {
	public final String pattern;

	public BruteForce(String pattern) {
		this.pattern = pattern;
	}

	public int search(String text) {
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
