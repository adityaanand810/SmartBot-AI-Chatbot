package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvLoader {

    private static final Map<String, String> variables = new HashMap<>();

    private EnvLoader() {
        // Utility class
    }

    public static void load() throws IOException {

        Path path = Path.of(".env");

        if (!Files.exists(path)) {
            throw new IOException(".env file not found!");
        }

        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {

            line = line.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] parts = line.split("=", 2);

            if (parts.length == 2) {

                String key = parts[0].trim();
                String value = parts[1].trim();

                variables.put(key, value);
            }
        }
    }

    public static String get(String key) {
        return variables.get(key);
    }
}