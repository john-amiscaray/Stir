package io.john.amiscaray.stir.setup;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ExpectedHTMLLoader {

    private static ExpectedHTMLLoader instance;

    private ExpectedHTMLLoader(){}

    public static ExpectedHTMLLoader getInstance() {
        if (instance == null) {
            instance = new ExpectedHTMLLoader();
        }
        return instance;
    }

    public String getHTMLContentOf(String path) throws IOException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString().replaceAll("\\r\\n", "\n");

    }

}
