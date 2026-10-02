package gui;

import dao.UserDAO;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private JButton registerButton;
    private JButton backButton;

    private final UserDAO userDAO;

    // =====================================================
    // COLORS
    // =====================================================

    private static final Color BACKGROUND =
            new Color(8, 12, 22);

    private static final Color CARD =
            new Color(20, 26, 40);

    private static final Color INPUT =
            new Color(30, 37, 54);

    private static final Color TEXT =
            new Color(245, 247, 250);

    private static final Color SECONDARY =
            new Color(155, 165, 184);

    private static final Color PURPLE =
            new Color(124, 92, 255);

    private static final Color BLUE =
            new Color(67, 126, 255);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RegisterFrame() {

        userDAO = new UserDAO();

        setTitle("SmartBot - Create Account");
        setSize(950, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);

        createUI();

        setVisible(true);
    }


    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new BackgroundPanel();

        mainPanel.setLayout(
                new BorderLayout(40, 0)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        35,
                        45,
                        35,
                        45
                )
        );


        // =================================================
        // LEFT SIDE
        // =================================================

        JPanel leftPanel =
                new JPanel();

        leftPanel.setOpaque(false);

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // LOGO

        JPanel logoPanel =
                new GlowLogo();

        logoPanel.setPreferredSize(
                new Dimension(80, 80)
        );

        logoPanel.setMaximumSize(
                new Dimension(80, 80)
        );

        logoPanel.setLayout(
                new GridBagLayout()
        );


        JLabel logo =
                new JLabel("SB");

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        logoPanel.add(logo);

        leftPanel.add(logoPanel);


        leftPanel.add(
                Box.createVerticalStrut(30)
        );


        // HEADING

        JLabel heading =
                new JLabel(
                        "<html>"
                                + "Create your own<br>"
                                + "<span style='color:#7C5CFF;'>SmartBot</span>"
                                + "</html>"
                );

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        leftPanel.add(heading);


        leftPanel.add(
                Box.createVerticalStrut(15)
        );


        // DESCRIPTION

        JLabel subtitle =
                new JLabel(
                        "<html>"
                                + "Create your account and enter<br>"
                                + "the world of intelligent conversations.<br>"
                                + "Your AI assistant is waiting."
                                + "</html>"
                );

        subtitle.setForeground(
                SECONDARY
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        leftPanel.add(subtitle);


        leftPanel.add(
                Box.createVerticalStrut(40)
        );


        // FEATURES

        leftPanel.add(
                createFeature(
                        "✦",
                        "AI-powered conversations"
                )
        );

        leftPanel.add(
                Box.createVerticalStrut(14)
        );

        leftPanel.add(
                createFeature(
                        "◈",
                        "Smart chat history"
                )
        );

        leftPanel.add(
                Box.createVerticalStrut(14)
        );

        leftPanel.add(
                createFeature(
                        "⚡",
                        "Fast & responsive"
                )
        );


        mainPanel.add(
                leftPanel,
                BorderLayout.WEST
        );


        // =================================================
        // RIGHT CARD
        // =================================================

        RoundedPanel card =
                new RoundedPanel(
                        28,
                        CARD
                );

        card.setLayout(
                new BorderLayout()
        );

        card.setBorder(
                new EmptyBorder(
                        38,
                        40,
                        32,
                        40
                )
        );


        // =================================================
        // CARD TITLE
        // =================================================

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Create Account"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );


        JLabel subtitle2 =
                new JLabel(
                        "Start your SmartBot journey"
                );

        subtitle2.setForeground(
                SECONDARY
        );

        subtitle2.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle2);


        card.add(
                titlePanel,
                BorderLayout.NORTH
        );


        // =================================================
        // FORM
        // =================================================

        JPanel formPanel =
                new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // USERNAME

        formPanel.add(
                createLabel("Username")
        );


        usernameField =
                new RoundedTextField(
                        "Enter your username"
                );

        configureField(
                usernameField
        );

        formPanel.add(
                usernameField
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // PASSWORD

        formPanel.add(
                createLabel("Password")
        );


        passwordField =
                new RoundedPasswordField(
                        "Enter your password"
                );

        configurePasswordField(
                passwordField
        );

        formPanel.add(
                passwordField
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // CONFIRM PASSWORD

        formPanel.add(
                createLabel("Confirm Password")
        );


        confirmPasswordField =
                new RoundedPasswordField(
                        "Re-enter your password"
                );

        configurePasswordField(
                confirmPasswordField
        );

        formPanel.add(
                confirmPasswordField
        );


        formPanel.add(
                Box.createVerticalStrut(28)
        );


        // REGISTER BUTTON

        registerButton =
                new GradientButton(
                        "Create Account"
                );

        registerButton.setPreferredSize(
                new Dimension(330, 50)
        );

        registerButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        registerButton.addActionListener(
                e -> registerUser()
        );


        formPanel.add(
                registerButton
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // DIVIDER

        JLabel divider =
                new JLabel(
                        "────────  OR  ────────"
                );

        divider.setForeground(
                new Color(
                        100,
                        110,
                        130
                )
        );

        divider.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        formPanel.add(
                divider
        );


        formPanel.add(
                Box.createVerticalStrut(15)
        );


        // BACK TO LOGIN

        backButton =
                new JButton(
                        "← Back to Login"
                );

        backButton.setForeground(
                new Color(
                        150,
                        135,
                        255
                )
        );

        backButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        backButton.setFocusPainted(false);

        backButton.setBorderPainted(false);

        backButton.setContentAreaFilled(false);

        backButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        backButton.addActionListener(
                e -> dispose()
        );


        formPanel.add(
                backButton
        );


        card.add(
                formPanel,
                BorderLayout.CENTER
        );


        mainPanel.add(
                card,
                BorderLayout.CENTER
        );


        setContentPane(
                mainPanel
        );
    }


    // =====================================================
    // FIELD CONFIGURATION
    // =====================================================

    private void configureField(
            JTextField field
    ) {

        field.setPreferredSize(
                new Dimension(330, 48)
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setForeground(TEXT);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );
    }


    private void configurePasswordField(
            JPasswordField field
    ) {

        field.setPreferredSize(
                new Dimension(330, 48)
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setForeground(TEXT);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );
    }


    // =====================================================
    // REGISTER LOGIC
    // =====================================================

    private void registerUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        String confirmPassword =
                new String(
                        confirmPasswordField
                                .getPassword()
                );


        if (username.isEmpty()) {

            showError(
                    "Please enter your username."
            );

            usernameField.requestFocus();

            return;
        }


        if (password.isEmpty()) {

            showError(
                    "Please enter your password."
            );

            passwordField.requestFocus();

            return;
        }


        if (password.length() < 4) {

            showError(
                    "Password must contain at least 4 characters."
            );

            passwordField.requestFocus();

            return;
        }


        if (!password.equals(confirmPassword)) {

            showError(
                    "Passwords do not match."
            );

            confirmPasswordField.requestFocus();

            return;
        }


        try {

            User user =
                    new User(
                            username,
                            password,
                            "USER"
                    );


            userDAO.save(user);


            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!",
                    "SmartBot",
                    JOptionPane.INFORMATION_MESSAGE
            );


            dispose();


        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {

                showError(
                        "Username already exists."
                );

            } else {

                showError(
                        "Database error:\n"
                                + e.getMessage()
                );
            }


        } catch (Exception e) {

            showError(
                    "Registration failed:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // ERROR MESSAGE
    // =====================================================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "SmartBot",
                JOptionPane.ERROR_MESSAGE
        );
    }


    // =====================================================
    // LABEL
    // =====================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setForeground(
                new Color(
                        215,
                        220,
                        230
                )
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }


    // =====================================================
    // FEATURE
    // =====================================================

    private JPanel createFeature(
            String icon,
            String text
    ) {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        panel.setOpaque(false);


        JLabel iconLabel =
                new JLabel(
                        icon + "   "
                );

        iconLabel.setForeground(
                new Color(
                        130,
                        105,
                        255
                )
        );

        iconLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );


        JLabel textLabel =
                new JLabel(text);

        textLabel.setForeground(
                new Color(
                        200,
                        207,
                        220
                )
        );

        textLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        panel.add(iconLabel);

        panel.add(textLabel);

        return panel;
    }


    // =====================================================
    // BACKGROUND
    // =====================================================

    private static class BackgroundPanel
            extends JPanel {

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            BACKGROUND,
                            getWidth(),
                            getHeight(),
                            new Color(
                                    27,
                                    18,
                                    48
                            )
                    );


            g2.setPaint(gradient);


            g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );


            // Purple glow

            g2.setColor(
                    new Color(
                            124,
                            92,
                            255,
                            30
                    )
            );


            g2.fillOval(
                    -120,
                    -100,
                    300,
                    300
            );


            // Blue glow

            g2.setColor(
                    new Color(
                            67,
                            126,
                            255,
                            25
                    )
            );


            g2.fillOval(
                    getWidth() - 180,
                    getHeight() - 180,
                    300,
                    300
            );


            g2.dispose();
        }
    }


    // =====================================================
    // ROUNDED PANEL
    // =====================================================

    private static class RoundedPanel
            extends JPanel {

        private final int radius;

        private final Color color;


        public RoundedPanel(
                int radius,
                Color color
        ) {

            this.radius = radius;

            this.color = color;

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(color);


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );


            g2.dispose();
        }
    }


    // =====================================================
    // LOGO
    // =====================================================

    private static class GlowLogo
            extends JPanel {

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            // Glow

            g2.setColor(
                    new Color(
                            124,
                            92,
                            255,
                            45
                    )
            );


            g2.fillOval(
                    -8,
                    -8,
                    getWidth() + 16,
                    getHeight() + 16
            );


            // Logo gradient

            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            PURPLE,
                            getWidth(),
                            getHeight(),
                            BLUE
                    );


            g2.setPaint(gradient);


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    22,
                    22
            );


            g2.dispose();
        }
    }


    // =====================================================
    // GRADIENT BUTTON
    // =====================================================

    private static class GradientButton
            extends JButton {

        public GradientButton(
                String text
        ) {

            super(text);

            setForeground(
                    Color.WHITE
            );

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            Color start = PURPLE;

            Color end = BLUE;


            if (getModel().isRollover()) {

                start =
                        new Color(
                                145,
                                115,
                                255
                        );

                end =
                        new Color(
                                85,
                                145,
                                255
                        );
            }


            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            start,
                            getWidth(),
                            0,
                            end
                    );


            g2.setPaint(gradient);


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    18,
                    18
            );


            g2.dispose();


            super.paintComponent(g);
        }
    }


    // =====================================================
    // ROUNDED TEXT FIELD
    // =====================================================

    private static class RoundedTextField
            extends JTextField {

        private final String placeholder;


        public RoundedTextField(
                String placeholder
        ) {

            this.placeholder =
                    placeholder;

            setOpaque(false);

            setBorder(
                    new EmptyBorder(
                            0,
                            16,
                            0,
                            16
                    )
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(INPUT);


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    16,
                    16
            );


            super.paintComponent(g);


            if (getText().isEmpty()
                    && !hasFocus()) {

                g2.setColor(
                        new Color(
                                115,
                                125,
                                145
                        )
                );

                g2.setFont(getFont());

                g2.drawString(
                        placeholder,
                        16,
                        getHeight() / 2 + 5
                );
            }


            g2.dispose();
        }
    }


    // =====================================================
    // ROUNDED PASSWORD FIELD
    // =====================================================

    private static class RoundedPasswordField
            extends JPasswordField {

        private final String placeholder;


        public RoundedPasswordField(
                String placeholder
        ) {

            this.placeholder =
                    placeholder;

            setOpaque(false);

            setBorder(
                    new EmptyBorder(
                            0,
                            16,
                            0,
                            16
                    )
            );
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(INPUT);


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    16,
                    16
            );


            super.paintComponent(g);


            if (getPassword().length == 0
                    && !hasFocus()) {

                g2.setColor(
                        new Color(
                                115,
                                125,
                                145
                        )
                );

                g2.setFont(getFont());

                g2.drawString(
                        placeholder,
                        16,
                        getHeight() / 2 + 5
                );
            }


            g2.dispose();
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                RegisterFrame::new
        );
    }
}