package engine;

import exception.BotException;
import exception.InvalidInputException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import utils.EnvLoader;

// RUBRIC: Polymorphism
// AIApiEngine implements ChatEngine

// RUBRIC: Exception Handling
// InvalidInputException and BotException

// RUBRIC: HTTP Client
// Java 17 HttpClient

public class AIApiEngine implements ChatEngine {

    private final HttpClient httpClient;
    private String apiKey;

    // Gemini stable model
    private final String model = "gemini-3.8-flash";

    // Maximum retry attempts
    private static final int MAX_RETRIES = 3;

    public AIApiEngine() {

        httpClient = HttpClient.newHttpClient();

        try {

            EnvLoader.load();

            apiKey = EnvLoader.get("GEMINI_API_KEY");

        } catch (Exception e) {

            apiKey = null;
        }
    }

    @Override
    public String getResponse(String question)
            throws InvalidInputException, BotException {

        // ==========================================
        // VALIDATE QUESTION
        // ==========================================

        if (question == null || question.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Question cannot be empty."
            );
        }

        // ==========================================
        // CHECK API KEY
        // ==========================================

        if (apiKey == null || apiKey.isBlank()) {

            throw new BotException(
                    "Gemini API key not found in .env file."
            );
        }

        // ==========================================
        // CREATE URL
        // ==========================================

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                        + model
                        + ":generateContent";

        // ==========================================
        // CREATE REQUEST BODY
        // ==========================================

        String requestBody = createRequestBody(question);

        // ==========================================
        // RETRY LOOP
        // ==========================================

        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {

            try {

                HttpRequest request =
                        HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header(
                                        "Content-Type",
                                        "application/json"
                                )
                                .header(
                                        "x-goog-api-key",
                                        apiKey
                                )
                                .POST(
                                        HttpRequest.BodyPublishers
                                                .ofString(requestBody)
                                )
                                .build();

                HttpResponse<String> response =
                        httpClient.send(
                                request,
                                HttpResponse.BodyHandlers.ofString()
                        );

                int statusCode = response.statusCode();

                // ==========================================
                // SUCCESS
                // ==========================================

                if (statusCode >= 200 && statusCode < 300) {

                    String answer =
                            extractText(response.body());

                    if (answer == null || answer.isBlank()) {

                        throw new BotException(
                                "Gemini returned an empty response."
                        );
                    }

                    return answer;
                }

                // ==========================================
                // RETRY FOR 503 / 429
                // ==========================================

                if (statusCode == 503 || statusCode == 429) {

                    if (attempt < MAX_RETRIES) {

                        long delay =
                                (long) Math.pow(2, attempt) * 1000;

                        System.out.println(
                                "Gemini API temporarily unavailable."
                        );

                        System.out.println(
                                "Retry attempt "
                                        + attempt
                                        + " in "
                                        + (delay / 1000)
                                        + " seconds..."
                        );

                        Thread.sleep(delay);

                        continue;
                    }

                    throw new BotException(
                            "Gemini API is temporarily busy. "
                                    + "Please try again after a few seconds.\n\n"
                                    + "HTTP Status: "
                                    + statusCode
                    );
                }

                // ==========================================
                // OTHER API ERRORS
                // ==========================================

                throw new BotException(
                        "Gemini API Error: HTTP "
                                + statusCode
                                + "\n"
                                + response.body()
                );

            } catch (BotException e) {

                throw e;

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                throw new BotException(
                        "Gemini request was interrupted.",
                        e
                );

            } catch (Exception e) {

                throw new BotException(
                        "Failed to connect with Gemini API.",
                        e
                );
            }
        }

        throw new BotException(
                "Gemini API request failed."
        );
    }

    // ==========================================
    // CREATE REQUEST JSON
    // ==========================================

    private String createRequestBody(String question) {

        String safeQuestion = escapeJson(question);

        return "{"
                + "\"contents\":["
                + "{"
                + "\"parts\":["
                + "{"
                + "\"text\":\""
                + safeQuestion
                + "\"}"
                + "]"
                + "}"
                + "]"
                + "}";
    }

    // ==========================================
    // EXTRACT RESPONSE TEXT
    // ==========================================

    private String extractText(String json) {

        String key = "\"text\":";

        int keyPosition = json.indexOf(key);

        if (keyPosition == -1) {

            return null;
        }

        int startQuote =
                json.indexOf(
                        "\"",
                        keyPosition + key.length()
                );

        if (startQuote == -1) {

            return null;
        }

        StringBuilder result =
                new StringBuilder();

        boolean escaped = false;

        for (
                int i = startQuote + 1;
                i < json.length();
                i++
        ) {

            char current = json.charAt(i);

            // ==========================================
            // ESCAPED CHARACTER
            // ==========================================

            if (escaped) {

                switch (current) {

                    case 'n':

                        result.append('\n');

                        break;

                    case 'r':

                        result.append('\r');

                        break;

                    case 't':

                        result.append('\t');

                        break;

                    case '"':

                        result.append('"');

                        break;

                    case '\\':

                        result.append('\\');

                        break;

                    default:

                        result.append(current);
                }

                escaped = false;

            }

            // ==========================================
            // START ESCAPE
            // ==========================================

            else if (current == '\\') {

                escaped = true;
            }

            // ==========================================
            // END OF TEXT
            // ==========================================

            else if (current == '"') {

                break;
            }

            // ==========================================
            // NORMAL CHARACTER
            // ==========================================

            else {

                result.append(current);
            }
        }

        return result.toString();
    }

    // ==========================================
    // ESCAPE JSON
    // ==========================================

    private String escapeJson(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}