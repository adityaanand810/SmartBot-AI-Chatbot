import engine.AIApiEngine;

public class TestAI {

    public static void main(String[] args) {

        try {

            AIApiEngine ai = new AIApiEngine();

            System.out.println("================================");
            System.out.println("       SMARTBOT AI TEST");
            System.out.println("================================");

            String question = "What is Java? Explain in simple words.";

            System.out.println("User: " + question);

            String response = ai.getResponse(question);

            System.out.println();
            System.out.println("Bot: " + response);

            System.out.println();
            System.out.println("================================");
            System.out.println("       AI TEST SUCCESS");
            System.out.println("================================");

        } catch (Exception e) {

            System.out.println();
            System.out.println("AI TEST FAILED!");
            System.out.println();

            e.printStackTrace();
        }
    }
}