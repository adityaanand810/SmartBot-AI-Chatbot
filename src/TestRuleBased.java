import engine.ChatEngine;
import engine.RuleBasedEngine;

public class TestRuleBased {

    public static void main(String[] args) {

        try {

            ChatEngine engine = new RuleBasedEngine();

            System.out.println("Knowledge Size: "
                    + ((RuleBasedEngine) engine).getKnowledgeSize());

            System.out.println();

            System.out.println(
                    "User: hello"
            );

            System.out.println(
                    "Bot: " + engine.getResponse("hello")
            );

            System.out.println();

            System.out.println(
                    "User: What is Java?"
            );

            System.out.println(
                    "Bot: " + engine.getResponse("What is Java?")
            );

            System.out.println();

            System.out.println(
                    "User: what is AI"
            );

            System.out.println(
                    "Bot: " + engine.getResponse("what is AI")
            );

        } catch (Exception e) {

            System.out.println("Error:");
            e.printStackTrace();
        }
    }
}