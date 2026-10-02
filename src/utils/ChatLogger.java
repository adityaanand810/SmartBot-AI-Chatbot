package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ChatLogger {

    private static final String FILE_NAME = "chat_log.txt";

    // RUBRIC: Synchronization
    // synchronized method ensures that only one thread
    // can write to the log file at a time.
    public synchronized void log(String message) {

        try (FileWriter fileWriter =
                     new FileWriter(FILE_NAME, true);
             PrintWriter writer =
                     new PrintWriter(fileWriter)) {

            writer.println(
                    "[" + LocalDateTime.now() + "] " + message
            );

        } catch (IOException e) {

            System.out.println(
                    "Unable to write chat log: " + e.getMessage()
            );
        }
    }
}