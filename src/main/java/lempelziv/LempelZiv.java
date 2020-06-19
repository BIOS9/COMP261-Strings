package main.java.lempelziv;

import javafx.util.Pair;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
    public static final int DEFAULT_WINDOW_SIZE = 100;

    public String compress(String input) {
        return compress(input, DEFAULT_WINDOW_SIZE);
    }

    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public String compress(String input, int windowSize) {
        int cursor = 0;
        char[] text = input.toCharArray();
        StringBuilder output = new StringBuilder();

        while (cursor < text.length) {
            int[] prefix = findPrefix(text, cursor, text.length, (cursor < windowSize) ? 0 : (cursor - windowSize), cursor);
            if(prefix != null) {

			}
            cursor++;
        }

        return output.toString();
    }

    public static int[] findPrefix(char[] data, int prefixStart, int prefixEnd, int dataStart, int dataEnd) {
        int bestMatch = -1;
        int matchLength = 0;

        for (int iData = dataStart; iData < dataEnd; ++iData) {
        	int count = 0;
        	int currentMatch = -1;
        	int currentLength = 0;

            for (int iPrefix = prefixStart; iPrefix < prefixEnd; ++iPrefix) {
            	if(iData + count >= dataEnd)
            		break;
				if(data[iData + count] == data[iPrefix]) {
					if(currentMatch == -1)
						currentMatch = iData;
					++currentLength;
				} else {
					break;
				}
				++count;
            }

			if(currentLength > matchLength) {
				bestMatch = currentMatch;
				matchLength = currentLength;
			}
        }

        if (bestMatch == -1)
            return null;
        return new int[] {bestMatch, matchLength};
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public String decompress(String compressed) {
        // TODO fill this in.
        return "";
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}
