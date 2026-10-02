import gui.LoginFrame;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Swing GUI ko Event Dispatch Thread par start karna
        // RUBRIC: Swing GUI / Thread Safety

        SwingUtilities.invokeLater(() -> {

            try {

                // System ka default UI theme use karega
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception e) {

                System.out.println(
                        "Unable to set system look and feel."
                );
            }

            // Start SmartBot
            new LoginFrame();
        });
    }
}