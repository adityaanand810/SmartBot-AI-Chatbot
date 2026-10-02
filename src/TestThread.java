import utils.ChatLogger;
import utils.TypingThread;

public class TestThread {

    public static void main(String[] args) {

        System.out.println("Starting SmartBot threading test...");

        ChatLogger logger = new ChatLogger();

        TypingThread typingThread = new TypingThread();

        logger.log("Threading test started.");

        // RUBRIC: Multithreading
        typingThread.start();

        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        typingThread.stopTyping();

        logger.log("Threading test completed.");

        System.out.println();
        System.out.println("Threading test successful!");
        System.out.println("Check chat_log.txt");
    }
}