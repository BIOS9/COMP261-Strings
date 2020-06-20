package test.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    // Took this code from https://www.journaldev.com/875/java-read-file-to-string
    public static String readString(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, CHARSET);
    }
}
