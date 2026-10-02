package gui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AdminLoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
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

    private static final Color ADMIN =
            new Color(245, 166, 35);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminLoginFrame() {

        userDAO = new UserDAO();

        setTitle("SmartBot - Admin Access");

        setSize(850, 560);

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
                new BorderLayout(35, 0)
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
                new LogoPanel();

        logoPanel.setPreferredSize(
                new Dimension(75, 75)
        );

        logoPanel.setMaximumSize(
                new Dimension(75, 75)
        );

        logoPanel.setLayout(
                new GridBagLayout()
        );


        JLabel logoText =
                new JLabel("SB");

        logoText.setForeground(
                Color.WHITE
        );

        logoText.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        logoPanel.add(logoText);

        leftPanel.add(logoPanel);


        leftPanel.add(
                Box.createVerticalStrut(28)
        );


        // TITLE

        JLabel heading =
                new JLabel(
                        "<html>"
                                + "SmartBot<br>"
                                + "<span style='color:#F5A623;'>Admin Access</span>"
                                + "</html>"
                );

        heading.setForeground(TEXT);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        leftPanel.add(heading);


        leftPanel.add(
                Box.createVerticalStrut(15)
        );


        // DESCRIPTION

        JLabel description =
                new JLabel(
                        "<html>"
                                + "Manage your knowledge base,<br>"
                                + "control chatbot data and<br>"
                                + "maintain SmartBot."
                                + "</html>"
                );

        description.setForeground(
                SECONDARY
        );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        leftPanel.add(description);


        leftPanel.add(
                Box.createVerticalStrut(38)
        );


        leftPanel.add(
                createFeature(
                        "◆",
                        "Knowledge Base Management"
                )
        );

        leftPanel.add(
                Box.createVerticalStrut(15)
        );

        leftPanel.add(
                createFeature(
                        "◆",
                        "Chatbot Data Control"
                )
        );

        leftPanel.add(
                Box.createVerticalStrut(15)
        );

        leftPanel.add(
                createFeature(
                        "◆",
                        "Secure Admin Access"
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
                        35,
                        38,
                        30,
                        38
                )
        );


        // =================================================
        // CARD HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setOpaque(false);

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );


        // Admin badge

        JLabel badge =
                new JLabel(
                        "  ADMIN ONLY  "
                );

        badge.setForeground(
                ADMIN
        );

        badge.setBackground(
                new Color(
                        245,
                        166,
                        35,
                        25
                )
        );

        badge.setOpaque(true);

        badge.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        badge.setBorder(
                new EmptyBorder(
                        6,
                        10,
                        6,
                        10
                )
        );


        JLabel title =
                new JLabel(
                        "Administrator Login"
                );

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Enter your administrator credentials"
                );

        subtitle.setForeground(
                SECONDARY
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        headerPanel.add(badge);

        headerPanel.add(
                Box.createVerticalStrut(15)
        );

        headerPanel.add(title);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitle);


        card.add(
                headerPanel,
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
                createLabel(
                        "Admin Username"
                )
        );


        usernameField =
                new RoundedTextField(
                        "Enter admin username"
                );

        configureTextField(
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
                createLabel(
                        "Admin Password"
                )
        );


        passwordField =
                new RoundedPasswordField(
                        "Enter admin password"
                );

        configurePasswordField(
                passwordField
        );

        formPanel.add(
                passwordField
        );


        formPanel.add(
                Box.createVerticalStrut(28)
        );


        // LOGIN BUTTON

        loginButton =
                new GradientButton(
                        "🔐  Secure Admin Login"
                );

        loginButton.setPreferredSize(
                new Dimension(
                        330,
                        50
                )
        );

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50
                )
        );

        loginButton.addActionListener(
                e -> loginAdmin()
        );


        formPanel.add(
                loginButton
        );


        formPanel.add(
                Box.createVerticalStrut(18)
        );


        // BACK BUTTON

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

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
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


        // Enter = Login

        getRootPane().setDefaultButton(
                loginButton
        );
    }


    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    private void loginAdmin() {

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
                    "Please enter admin username."
            );

            usernameField.requestFocus();

            return;
        }


        if (password.isEmpty()) {

            showError(
                    "Please enter admin password."
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


            // USER cannot access Admin Panel

            if (user == null
                    || !"ADMIN".equalsIgnoreCase(
                    user.getRole()
            )) {

                showError(
                        "Invalid administrator credentials."
                );

                passwordField.selectAll();

                passwordField.requestFocus();

                return;
            }


            JOptionPane.showMessageDialog(
                    this,
                    "Administrator authentication successful!",
                    "SmartBot",
                    JOptionPane.INFORMATION_MESSAGE
            );


            new AdminFrame();

            dispose();


        } catch (Exception e) {

            showError(
                    "Admin login failed:\n"
                            + e.getMessage()
            );
        }
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
                ADMIN
        );

        iconLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
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
                        13
                )
        );


        panel.add(iconLabel);

        panel.add(textLabel);

        return panel;
    }


    // =====================================================
    // TEXT FIELD CONFIG
    // =====================================================

    private void configureTextField(
            JTextField field
    ) {

        field.setPreferredSize(
                new Dimension(
                        330,
                        48
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setForeground(
                TEXT
        );

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
                new Dimension(
                        330,
                        48
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        field.setForeground(
                TEXT
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );
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
                "Admin Access",
                JOptionPane.ERROR_MESSAGE
        );
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
                                    28,
                                    18,
                                    43
                            )
                    );


            g2.setPaint(
                    gradient
            );


            g2.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );


            // Orange admin glow

            g2.setColor(
                    new Color(
                            245,
                            166,
                            35,
                            18
                    )
            );


            g2.fillOval(
                    -130,
                    -120,
                    320,
                    320
            );


            // Purple glow

            g2.setColor(
                    new Color(
                            124,
                            92,
                            255,
                            18
                    )
            );


            g2.fillOval(
                    getWidth() - 160,
                    getHeight() - 180,
                    320,
                    320
            );


            g2.dispose();
        }
    }


    // =====================================================
    // LOGO
    // =====================================================

    private static class LogoPanel
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
                            PURPLE,
                            getWidth(),
                            getHeight(),
                            BLUE
                    );


            g2.setPaint(
                    gradient
            );


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    20,
                    20
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


            g2.setColor(
                    color
            );


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


            Color start =
                    PURPLE;

            Color end =
                    BLUE;


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


            g2.setPaint(
                    gradient
            );


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


            g2.setColor(
                    INPUT
            );


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

                g2.setFont(
                        getFont()
                );

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


            g2.setColor(
                    INPUT
            );


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

                g2.setFont(
                        getFont()
                );

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
    // MAIN - TEMPORARY TEST
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                AdminLoginFrame::new
        );
    }
}