package main.java.lempelziv;

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
            int[] prefix = findPrefix(text, cursor, text.length, (cursor < windowSize) ? 0 : (cursor - windowSize), cursor); // Find best partial match in window
            if (prefix == null) {
                output.append("[0|0|" + text[cursor] + "]");
                cursor++;
            } else {

                // Check if there is another character to grab at the end, if not, dont try grab it
                if (cursor + prefix[1] >= text.length)
                    output.append("[" + prefix[0] + "|" + prefix[1] + "|]");
                else
                    output.append("[" + prefix[0] + "|" + prefix[1] + "|" + text[cursor + prefix[1]] + "]");

                cursor += prefix[1] + 1;
            }
        }

        return output.toString();
    }

    /**
     * Find a partial or full match in the given data using the pattern of prefix.
     */
    public static int[] findPrefix(char[] data, int prefixStart, int prefixEnd, int dataStart, int dataEnd) {
        // Best found result
        int bestMatch = -1;
        int matchLength = 0;

        // Brute force based partial match search
        for (int iData = dataStart; iData < dataEnd; ++iData) {
            int count = 0;

            // Current result
            int currentMatch = -1;
            int currentLength = 0;

            for (int iPrefix = prefixStart; iPrefix < prefixEnd; ++iPrefix) {
                if (iData + count >= dataEnd) // Ensure matching is not done outside of data window
                    break;

                if (data[iData + count] == data[iPrefix]) { // Main comparison
                    if (currentMatch == -1)
                        currentMatch = iData;
                    ++currentLength;
                } else {
                    break;
                }
                ++count; // Length of match so far
            }

            // Compare current result with best match
            if (currentLength > matchLength) {
                // Overwrite if better
                bestMatch = currentMatch;
                matchLength = currentLength;
            }
        }

        if (bestMatch == -1)
            return null;
        return new int[]{prefixStart - bestMatch, matchLength};
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public String decompress(String compressed) {
        StringBuilder sb = new StringBuilder();
        char[] data = compressed.toCharArray();
        for (int i = 0; i < data.length; ++i) {
            if (data[i] != '[') // Require open bracket
                throw new IllegalStateException("Required '[' missing.");
            ++i;

            int[] offset = readInt(data, i); // Read the first offset number
            i = offset[1];

            int[] length = readInt(data, i); // Read the second length number
            i = length[1];

            Character c = data[i];
            if(c == ']') // Dont add character, none available
                c = null;
            else if (c == '[' || c == '|') // Check that a character is valid
                throw new IllegalStateException("Required character missing.");
            else
                ++i;

            if (data[i] != ']') // Require closing bracket
                throw new IllegalStateException("Required ']' missing.");

            if (offset[0] != 0 && length[0] != 0) { // If length and offset are non-zero then reference existing characters by offset
                int textStart = sb.length() - offset[0]; // Start of the text to reference/add
                for (int j = textStart; j < textStart + length[0]; ++j) // Add all referenced characters
                    sb.append(sb.charAt(j));
            }
            if(c != null)
                sb.append(c); // Add character on it's own
        }

        return sb.toString();
    }

    /**
     * Reads integer from compressed LZ77 character block
     * <p>
     * Returns an array containing the read value and the index to start the next read from.
     */
    private int[] readInt(char[] data, int i) {
        int value = 0;
        while (i < data.length - 1) {
            value *= 10;
            value += Integer.parseInt(String.valueOf(data[i]));
            ++i;
            if (data[i] == '|')
                break;
        }
        if (data[i] != '|') // Require separator
            throw new IllegalStateException("Required '|' missing.");
        ++i;
        return new int[]{value, i};
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
