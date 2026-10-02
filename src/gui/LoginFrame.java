package gui;

import dao.UserDAO;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton registerButton;

    private final UserDAO userDAO;

    // ==============================
    // COLORS
    // ==============================

    private final Color BACKGROUND = new Color(8, 12, 22);
    private final Color CARD = new Color(20, 26, 40);
    private static final Color INPUT = new Color(30, 37, 54);

    private final Color TEXT = new Color(245, 247, 250);
    private final Color SECONDARY = new Color(155, 165, 184);

    private final Color PURPLE = new Color(124, 92, 255);
    private final Color BLUE = new Color(67, 126, 255);


    public LoginFrame() {

        userDAO = new UserDAO();

        setTitle("SmartBot - Login");
        setSize(950, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        createUI();

        setVisible(true);
    }


    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel = new BackgroundPanel();

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

        JPanel leftPanel = new JPanel();

        leftPanel.setOpaque(false);

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // Logo

        JPanel logoPanel = new GlowLogo();

        logoPanel.setPreferredSize(
                new Dimension(80, 80)
        );

        logoPanel.setMaximumSize(
                new Dimension(80, 80)
        );

        logoPanel.setLayout(
                new GridBagLayout()
        );

        JLabel logo = new JLabel("SB");

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


        // Heading

        JLabel heading = new JLabel(
                "<html>"
                        + "Welcome back to<br>"
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


        // Subtitle

        JLabel subtitle = new JLabel(
                "<html>"
                        + "Your intelligent digital assistant.<br>"
                        + "Ask questions, explore ideas and<br>"
                        + "let SmartBot do the thinking."
                        + "</html>"
        );

        subtitle.setForeground(SECONDARY);

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


        // Features

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
        // TITLE
        // =================================================

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel loginTitle =
                new JLabel("Sign in");

        loginTitle.setForeground(TEXT);

        loginTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );


        JLabel loginSubtitle =
                new JLabel(
                        "Continue to your SmartBot account"
                );

        loginSubtitle.setForeground(
                SECONDARY
        );

        loginSubtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        titlePanel.add(loginTitle);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(loginSubtitle);


        card.add(
                titlePanel,
                BorderLayout.NORTH
        );


        // =================================================
        // FORM
        // =================================================

        JPanel formPanel = new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // Username

        formPanel.add(
                createLabel("Username")
        );


        usernameField =
                new RoundedTextField(
                        "Enter your username"
                );

        usernameField.setPreferredSize(
                new Dimension(330, 48)
        );

        usernameField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        usernameField.setForeground(TEXT);

        usernameField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        formPanel.add(
                usernameField
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // Password

        formPanel.add(
                createLabel("Password")
        );


        passwordField =
                new RoundedPasswordField(
                        "Enter your password"
                );

        passwordField.setPreferredSize(
                new Dimension(330, 48)
        );

        passwordField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        passwordField.setForeground(TEXT);

        passwordField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );


        formPanel.add(
                passwordField
        );


        formPanel.add(
                Box.createVerticalStrut(28)
        );


        // Login Button

        loginButton =
                new GradientButton(
                        "Login to SmartBot"
                );

        loginButton.setPreferredSize(
                new Dimension(330, 50)
        );

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        loginButton.addActionListener(
                e -> loginUser()
        );


        formPanel.add(
                loginButton
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // Divider

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

        divider.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        divider.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        formPanel.add(divider);


        formPanel.add(
                Box.createVerticalStrut(15)
        );


        // Register Button

        registerButton =
                new JButton(
                        "Create a new account"
                );

        registerButton.setForeground(
                new Color(
                        150,
                        135,
                        255
                )
        );

        registerButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        registerButton.setFocusPainted(false);

        registerButton.setBorderPainted(false);

        registerButton.setContentAreaFilled(false);

        registerButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        registerButton.addActionListener(
                e -> openRegister()
        );


        formPanel.add(
                registerButton
        );

                // =====================================================
        // ADMIN ACCESS BUTTON
        // =====================================================

        JButton adminButton =
                new JButton("🔐 Admin Access");

        adminButton.setForeground(
                new Color(245, 166, 35)
        );

        adminButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        adminButton.setFocusPainted(false);
        adminButton.setBorderPainted(false);
        adminButton.setContentAreaFilled(false);

        adminButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        adminButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        adminButton.addActionListener(
                e -> new AdminLoginFrame()
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );

        formPanel.add(
                adminButton
        );


        card.add(
                formPanel,
                BorderLayout.CENTER
        );


        mainPanel.add(
                card,
                BorderLayout.CENTER
        );


        setContentPane(mainPanel);


        // Enter key se login

        getRootPane().setDefaultButton(
                loginButton
        );
    }


    // =====================================================
    // LOGIN
    // =====================================================

    private void loginUser() {

        String username =
                usernameField
                        .getText()
                        .trim();


        String password =
                new String(
                        passwordField
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


        try {

            User user =
                    userDAO.login(
                            username,
                            password
                    );


            if (user == null) {

                showError(
                        "Invalid username or password."
                );

                passwordField.requestFocus();

                return;
            }


            // ADMIN LOGIN

            if ("ADMIN".equalsIgnoreCase(
                    user.getRole()
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome Admin!",
                        "SmartBot",
                        JOptionPane.INFORMATION_MESSAGE
                );

                /*
                 * AdminFrame hum next step mein banayenge.
                 */
                new AdminFrame();

            }

            // USER LOGIN

            else {

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome, "
                                + user.getUsername()
                                + "!",
                        "SmartBot",
                        JOptionPane.INFORMATION_MESSAGE
                );

                /*
                 * ChatFrame hum next step mein banayenge.
                 */
                new ChatFrame(user);
            }


            dispose();


        } catch (Exception e) {

            showError(
                    "Login failed:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // REGISTER
    // =====================================================

    private void openRegister() {

        new RegisterFrame();
    }


    // =====================================================
    // ERROR
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

    private class BackgroundPanel
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


            // Gradient background

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
    // GLOW LOGO
    // =====================================================

    private class GlowLogo
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


            // Gradient logo

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

    private class GradientButton
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


            super.paintComponent(g);
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


            super.paintComponent(g);
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                LoginFrame::new
        );
    }
}