package utils;

// RUBRIC: Multithreading
// This class extends Thread and runs independently
// from the main Swing thread.

public class TypingThread extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {

        String[] animation = {
                "Bot is typing.",
                "Bot is typing..",
                "Bot is typing..."
        };

        int index = 0;

        while (running) {

            System.out.print(
                    "\r" + animation[index]
            );

            index = (index + 1) % animation.length;

            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.print("\r                    \r");
    }

    public void stopTyping() {

        running = false;

        interrupt();
    }
}