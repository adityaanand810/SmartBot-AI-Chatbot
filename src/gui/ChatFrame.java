package gui;

import dao.MessageDAO;
import engine.AIApiEngine;
import engine.ChatEngine;
import engine.RuleBasedEngine;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Message;
import model.User;
import utils.ChatLogger;

public class ChatFrame extends JFrame {

    private final User user;

    private JPanel chatMessagesPanel;
    private JScrollPane chatScrollPane;

    private JTextField inputField;
    private JButton sendButton;
    private JComboBox<String> engineComboBox;

    private final MessageDAO messageDAO;
    private final ChatLogger chatLogger;

    private ChatEngine ruleBasedEngine;
    private ChatEngine aiEngine;

    private volatile boolean sending = false;

    // =========================
    // COLORS
    // =========================

    private static final Color BACKGROUND =
            new Color(7, 13, 24);

    private static final Color SIDEBAR =
            new Color(15, 23, 40);

    private static final Color CHAT_BACKGROUND =
            new Color(9, 17, 29);

    private static final Color INPUT_BACKGROUND =
            new Color(21, 31, 50);

    private static final Color USER_BUBBLE =
            new Color(25, 39, 64);

    private static final Color BOT_BUBBLE =
            new Color(17, 51, 95);

    private static final Color TEXT =
            new Color(245, 247, 252);

    private static final Color SECONDARY =
            new Color(155, 166, 188);

    private static final Color PURPLE =
            new Color(125, 75, 255);

    private static final Color BLUE =
            new Color(36, 119, 255);

    private static final Color BORDER =
            new Color(40, 56, 83);

    private static final Color GREEN =
            new Color(91, 220, 158);


    // =========================
    // CONSTRUCTOR
    // =========================

    public ChatFrame(User user) {

        this.user = user;

        messageDAO = new MessageDAO();
        chatLogger = new ChatLogger();

        setTitle("SmartBot - AI Chatbot Application");

        setSize(1200, 760);

        setMinimumSize(
                new Dimension(950, 650)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createEngines();

        createUI();

        loadChatHistory();

        setVisible(true);
    }


    // =========================
    // CREATE ENGINES
    // =========================

    private void createEngines() {

        try {

            ruleBasedEngine =
                    new RuleBasedEngine();

            aiEngine =
                    new AIApiEngine();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to initialize chatbot engines:\n"
                            + e.getMessage(),
                    "SmartBot",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // CREATE UI
    // =========================

    private void createUI() {

        JPanel mainPanel =
                new BackgroundPanel();

        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.add(
                createSidebar(),
                BorderLayout.WEST
        );

        mainPanel.add(
                createMainArea(),
                BorderLayout.CENTER
        );

        setContentPane(mainPanel);
    }


    // =========================
    // SIDEBAR
    // =========================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(new BorderLayout());

        sidebar.setPreferredSize(
                new Dimension(285, 0)
        );

        sidebar.setBackground(SIDEBAR);

        sidebar.setBorder(
                new EmptyBorder(
                        24,
                        18,
                        18,
                        18
                )
        );


        JPanel top =
                new JPanel();

        top.setOpaque(false);

        top.setLayout(
                new BoxLayout(
                        top,
                        BoxLayout.Y_AXIS
                )
        );


        // =========================
        // BRAND
        // =========================

        JPanel brand =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        brand.setOpaque(false);


        LogoPanel logo =
                new LogoPanel();

        logo.setPreferredSize(
                new Dimension(62, 62)
        );


        JPanel brandText =
                new JPanel();

        brandText.setOpaque(false);

        brandText.setLayout(
                new BoxLayout(
                        brandText,
                        BoxLayout.Y_AXIS
                )
        );

        brandText.setBorder(
                new EmptyBorder(
                        4,
                        14,
                        0,
                        0
                )
        );


        JLabel title =
                new JLabel("SmartBot");

        title.setForeground(TEXT);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );


        JLabel subtitle =
                new JLabel(
                        "AI Chatbot Application"
                );

        subtitle.setForeground(
                new Color(
                        125,
                        165,
                        235
                )
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        brandText.add(title);

        brandText.add(
                Box.createVerticalStrut(5)
        );

        brandText.add(subtitle);


        brand.add(logo);

        brand.add(brandText);


        top.add(brand);

        top.add(
                Box.createVerticalStrut(32)
        );


        // =========================
        // NAVIGATION
        // =========================

        top.add(
                createNavButton(
                        "💬",
                        "Chat",
                        true
                )
        );

        top.add(
                Box.createVerticalStrut(8)
        );


        top.add(
                createNavButton(
                        "◷",
                        "History",
                        false
                )
        );

        top.add(
                Box.createVerticalStrut(8)
        );


        top.add(
                createNavButton(
                        "▣",
                        "Knowledge Base",
                        false
                )
        );

        top.add(
                Box.createVerticalStrut(8)
        );


        top.add(
                createNavButton(
                        "♙",
                        "Profile",
                        false
                )
        );

        top.add(
                Box.createVerticalStrut(8)
        );


        JButton logout =
                createNavButton(
                        "⇥",
                        "Logout",
                        false
                );

        logout.addActionListener(
                e -> logout()
        );

        top.add(logout);


        sidebar.add(
                top,
                BorderLayout.NORTH
        );


        // =========================
        // POWERED BY
        // =========================

        JPanel powered =
                new RoundedPanel(
                        new Color(
                                24,
                                35,
                                62
                        ),
                        16
                );

        powered.setLayout(
                new BoxLayout(
                        powered,
                        BoxLayout.Y_AXIS
                )
        );

        powered.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JLabel p1 =
                new JLabel("Powered by");

        p1.setForeground(SECONDARY);

        p1.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );


        JLabel p2 =
                new JLabel(
                        "Rule Based AI  |  LLM API"
                );

        p2.setForeground(TEXT);

        p2.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );


        JLabel line =
                new JLabel("━━━━━━");

        line.setForeground(PURPLE);


        powered.add(p1);

        powered.add(
                Box.createVerticalStrut(4)
        );

        powered.add(p2);

        powered.add(
                Box.createVerticalStrut(5)
        );

        powered.add(line);


        sidebar.add(
                powered,
                BorderLayout.SOUTH
        );


        return sidebar;
    }


    // =========================
    // NAV BUTTON
    // =========================

    private JButton createNavButton(
            String icon,
            String text,
            boolean active
    ) {

        JButton button =
                new JButton(
                        icon + "    " + text
                );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        52
                )
        );

        button.setPreferredSize(
                new Dimension(
                        240,
                        52
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setForeground(TEXT);

        button.setFont(
                new Font(
                        "Segoe UI",
                        active
                                ? Font.BOLD
                                : Font.PLAIN,
                        15
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setOpaque(true);


        if (active) {

            button.setBackground(
                    new Color(
                            27,
                            91,
                            225
                    )
            );

        } else {

            button.setBackground(
                    SIDEBAR
            );
        }


        if (text.equals("History")) {

            button.addActionListener(
                    e -> JOptionPane.showMessageDialog(
                            this,
                            "Your chat history is stored in the database.",
                            "History",
                            JOptionPane.INFORMATION_MESSAGE
                    )
            );
        }


        if (text.equals("Knowledge Base")) {

            button.addActionListener(
                    e -> JOptionPane.showMessageDialog(
                            this,
                            "Knowledge Base can be managed from the Admin Panel.",
                            "Knowledge Base",
                            JOptionPane.INFORMATION_MESSAGE
                    )
            );
        }


        if (text.equals("Profile")) {

            button.addActionListener(
                    e -> JOptionPane.showMessageDialog(
                            this,
                            "Logged in as: "
                                    + user.getUsername(),
                            "Profile",
                            JOptionPane.INFORMATION_MESSAGE
                    )
            );
        }


        return button;
    }


    // =========================
    // MAIN AREA
    // =========================

    private JPanel createMainArea() {

        JPanel main =
                new JPanel(
                        new BorderLayout()
                );

        main.setBackground(
                BACKGROUND
        );

        main.setBorder(
                new EmptyBorder(
                        18,
                        16,
                        18,
                        18
                )
        );


        // HEADER

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        header.setBorder(
                new EmptyBorder(
                        5,
                        10,
                        12,
                        10
                )
        );


        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        left.setOpaque(false);


        JLabel headerTitle =
                new JLabel("SmartBot");

        headerTitle.setForeground(TEXT);

        headerTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );


        JLabel online =
                new JLabel(
                        "  ● Online"
                );

        online.setForeground(GREEN);

        online.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );


        left.add(headerTitle);

        left.add(online);


        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        right.setOpaque(false);


        JLabel userLabel =
                new JLabel(
                        "Hi, "
                                + user.getUsername()
                );

        userLabel.setForeground(
                SECONDARY
        );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        JButton logout =
                new JButton("Logout");

        logout.setForeground(TEXT);

        logout.setBackground(
                new Color(
                        31,
                        42,
                        63
                )
        );

        logout.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        logout.setBorderPainted(false);

        logout.setFocusPainted(false);

        logout.setOpaque(true);

        logout.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        logout.addActionListener(
                e -> logout()
        );


        right.add(userLabel);

        right.add(logout);


        header.add(
                left,
                BorderLayout.WEST
        );

        header.add(
                right,
                BorderLayout.EAST
        );


        main.add(
                header,
                BorderLayout.NORTH
        );


        // CHAT

        main.add(
                createModernChatArea(),
                BorderLayout.CENTER
        );


        // BOTTOM

        main.add(
                createModernBottom(),
                BorderLayout.SOUTH
        );


        return main;
    }


    // =========================
    // CHAT AREA
    // =========================

    private JPanel createModernChatArea() {

        chatMessagesPanel =
                new JPanel();

        chatMessagesPanel.setLayout(
                new BoxLayout(
                        chatMessagesPanel,
                        BoxLayout.Y_AXIS
                )
        );

        chatMessagesPanel.setBackground(
                CHAT_BACKGROUND
        );

        chatMessagesPanel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );


        chatScrollPane =
                new JScrollPane(
                        chatMessagesPanel
                );

        chatScrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        chatScrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(16);


        JPanel wrapper =
                new JPanel(
                        new BorderLayout()
                );

        wrapper.setBackground(
                CHAT_BACKGROUND
        );

        wrapper.add(
                chatScrollPane,
                BorderLayout.CENTER
        );


        return wrapper;
    }


    // =========================
    // BOTTOM
    // =========================

    private JPanel createModernBottom() {

        JPanel bottom =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        bottom.setOpaque(false);

        bottom.setBorder(
                new EmptyBorder(
                        14,
                        0,
                        0,
                        0
                )
        );


        // ENGINE

        JPanel enginePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        enginePanel.setOpaque(false);


        JLabel engineLabel =
                new JLabel("Engine:");

        engineLabel.setForeground(TEXT);

        engineLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );


        engineComboBox =
                new JComboBox<>(
                        new String[]{
                                "AI (Gemini)",
                                "AI (OpenAI)",
                                "Rule Based (Local)"
                        }
                );

        engineComboBox.setPreferredSize(
                new Dimension(
                        210,
                        48
                )
        );

        engineComboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        engineComboBox.setForeground(TEXT);

        engineComboBox.setBackground(
                INPUT_BACKGROUND
        );

        engineComboBox.setFocusable(false);

        engineComboBox.setOpaque(true);

                engineComboBox.setUI(
                new javax.swing.plaf.basic.BasicComboBoxUI() {

                @Override
                protected JButton createArrowButton() {

                        JButton button = new JButton("⌄");

                        button.setForeground(
                                new Color(190, 205, 225)
                        );

                        button.setBackground(
                                new Color(21, 31, 50)
                        );

                        button.setBorderPainted(false);
                        button.setFocusPainted(false);
                        button.setContentAreaFilled(true);

                        return button;
                }

                @Override
                public void paintCurrentValueBackground(
                        Graphics g,
                        Rectangle bounds,
                        boolean hasFocus
                ) {

                        g.setColor(
                                new Color(21, 31, 50)
                        );

                        g.fillRect(
                                bounds.x,
                                bounds.y,
                                bounds.width,
                                bounds.height
                        );
                }

                @Override
                public void paintCurrentValue(
                        Graphics g,
                        Rectangle bounds,
                        boolean hasFocus
                ) {

                        ListCellRenderer<Object> renderer =
                                comboBox.getRenderer();

                        Component component =
                                renderer.getListCellRendererComponent(
                                        listBox,
                                        comboBox.getSelectedItem(),
                                        -1,
                                        false,
                                        false
                                );

                        component.setForeground(
                                Color.WHITE
                        );

                        component.setBackground(
                                new Color(21, 31, 50)
                        );

                        SwingUtilities.paintComponent(
                                g,
                                component,
                                comboBox,
                                bounds.x + 8,
                                bounds.y,
                                bounds.width - 35,
                                bounds.height
                        );
                }
                }
        );


        engineComboBox.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(55, 75, 110),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                0,
                                10,
                                0,
                                5
                        )
                )
        );

        engineComboBox.setRenderer(
                new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(
                        JList<?> list,
                        Object value,
                        int index,
                        boolean isSelected,
                        boolean cellHasFocus
                ) {

                        JLabel label =
                                (JLabel) super.getListCellRendererComponent(
                                        list,
                                        value,
                                        index,
                                        isSelected,
                                        cellHasFocus
                                );

                        label.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.PLAIN,
                                        13
                                )
                        );

                        label.setForeground(
                                Color.WHITE
                        );

                        if (isSelected) {

                        label.setBackground(
                                new Color(
                                        38,
                                        92,
                                        180
                                )
                        );

                        } else {

                        label.setBackground(
                                new Color(
                                        21,
                                        31,
                                        50
                                )
                        );
                        }

                        label.setBorder(
                                BorderFactory.createEmptyBorder(
                                        8,
                                        10,
                                        8,
                                        10
                                )
                        );

                        return label;
                }
                }
        );


        enginePanel.add(
                engineLabel
        );

        enginePanel.add(
                engineComboBox
        );


        bottom.add(
                enginePanel,
                BorderLayout.WEST
        );


        // INPUT

        JPanel inputPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        inputPanel.setOpaque(false);


        inputField =
                new RoundedTextField();

        inputField.setPreferredSize(
                new Dimension(
                        0,
                        52
                )
        );

        inputField.setForeground(TEXT);

        inputField.setBackground(
                INPUT_BACKGROUND
        );

        inputField.setCaretColor(
                Color.WHITE
        );

        inputField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        inputField.setBorder(
                new EmptyBorder(
                        0,
                        18,
                        0,
                        18
                )
        );


        inputField.addActionListener(
                e -> sendMessage()
        );


        // ATTACHMENT

        JButton attach =
                new JButton("📎");

        attach.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        18
                )
        );

        attach.setForeground(
                SECONDARY
        );

        attach.setBackground(
                INPUT_BACKGROUND
        );

        attach.setBorderPainted(false);

        attach.setFocusPainted(false);

        attach.setOpaque(true);

        attach.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        JPanel inputWrapper =
                new JPanel(
                        new BorderLayout()
                );

        inputWrapper.setOpaque(false);

        inputWrapper.add(
                inputField,
                BorderLayout.CENTER
        );

        inputWrapper.add(
                attach,
                BorderLayout.EAST
        );


        // SEND

        sendButton =
                new GradientButton(
                        "Send  ➤"
                );

        sendButton.setPreferredSize(
                new Dimension(
                        125,
                        52
                )
        );

        sendButton.addActionListener(
                e -> sendMessage()
        );


        inputPanel.add(
                inputWrapper,
                BorderLayout.CENTER
        );

        inputPanel.add(
                sendButton,
                BorderLayout.EAST
        );


        bottom.add(
                inputPanel,
                BorderLayout.CENTER
        );


        return bottom;
    }


    // =========================
    // LOAD CHAT HISTORY
    // =========================

    private void loadChatHistory() {

        try {

            List<Message> history =
                    messageDAO.getHistoryByUser(
                            user.getId()
                    );


            if (history.isEmpty()) {

                appendBotMessage(
                        "Hello "
                                + user.getUsername()
                                + "! 👋\n"
                                + "I'm SmartBot. Ask me anything!"
                );

                return;
            }


            for (Message message : history) {

                appendUserMessage(
                        message.getUserMessage()
                );

                appendBotMessage(
                        message.getBotResponse()
                );
            }


        } catch (Exception e) {

            appendBotMessage(
                    "Unable to load previous chat history."
            );
        }
    }


    // =========================
    // SEND MESSAGE
    // =========================

    private void sendMessage() {

        if (sending) {
            return;
        }


        String question =
                inputField
                        .getText()
                        .trim();


        if (question.isEmpty()) {

            inputField.requestFocus();

            return;
        }


        inputField.setText("");


        appendUserMessage(
                question
        );


        sending = true;

        sendButton.setEnabled(false);

        inputField.setEnabled(false);


        Thread botThread =
                new Thread(
                        () -> processBotResponse(
                                question
                        )
                );


        botThread.start();
    }


    // =========================
    // BOT RESPONSE
    // =========================

    private void processBotResponse(
            String question
    ) {

        try {

            ChatEngine selectedEngine;


            String selected =
                    String.valueOf(
                            engineComboBox
                                    .getSelectedItem()
                    );


            if ("AI (Gemini)"
                    .equals(selected)) {

                selectedEngine =
                        aiEngine;

            } else if (
                    "Rule Based (Local)"
                            .equals(selected)
            ) {

                selectedEngine =
                        ruleBasedEngine;

            } else {

                // OpenAI UI option.
                // Actual OpenAI engine can be added later.
                selectedEngine =
                        aiEngine;
            }


            if (selectedEngine == null) {

                throw new Exception(
                        "Selected chatbot engine is unavailable."
                );
            }


            String response =
                    selectedEngine.getResponse(
                            question
                    );


            // SAVE DATABASE

            Message message =
                    new Message(
                            user.getId(),
                            question,
                            response
                    );


            messageDAO.save(
                    message
            );


            // SAVE LOG

            chatLogger.log(
                    "USER: " + question
            );

            chatLogger.log(
                    "BOT: " + response
            );


            SwingUtilities.invokeLater(
                    () -> {

                        appendBotMessage(
                                response
                        );

                        sending = false;

                        sendButton.setEnabled(
                                true
                        );

                        inputField.setEnabled(
                                true
                        );

                        inputField.requestFocus();
                    }
            );


        } catch (Exception e) {

            SwingUtilities.invokeLater(
                    () -> {

                        appendBotMessage(
                                "Sorry, something went wrong.\n\n"
                                        + e.getMessage()
                        );


                        chatLogger.log(
                                "ERROR: "
                                        + e.getMessage()
                        );


                        sending = false;

                        sendButton.setEnabled(
                                true
                        );

                        inputField.setEnabled(
                                true
                        );

                        inputField.requestFocus();
                    }
            );
        }
    }


    // =========================
    // USER MESSAGE
    // =========================

    private void appendUserMessage(
            String message
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    JPanel row =
                            new JPanel(
                                    new FlowLayout(
                                            FlowLayout.RIGHT,
                                            0,
                                            0
                                    )
                            );

                    row.setOpaque(false);

                    row.setBorder(
                            new EmptyBorder(
                                    5,
                                    5,
                                    10,
                                    5
                            )
                    );


                    JPanel bubble =
                            new RoundedPanel(
                                    USER_BUBBLE,
                                    15
                            );

                    bubble.setLayout(
                            new BorderLayout(
                                    0,
                                    5
                            )
                    );

                    bubble.setBorder(
                            new EmptyBorder(
                                    11,
                                    15,
                                    11,
                                    15
                            )
                    );


                    JLabel name =
                            new JLabel("You");

                    name.setForeground(TEXT);

                    name.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    13
                            )
                    );


                    JLabel time =
                            new JLabel(
                                    getCurrentTime()
                            );

                    time.setForeground(
                            new Color(
                                    168,
                                    190,
                                    218
                            )
                    );

                    time.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.PLAIN,
                                    10
                            )
                    );


                    JPanel top =
                            new JPanel(
                                    new BorderLayout()
                            );

                    top.setOpaque(false);

                    top.add(
                            name,
                            BorderLayout.WEST
                    );

                    top.add(
                            time,
                            BorderLayout.EAST
                    );


                    JTextArea text =
                            createMessageText(
                                    message
                            );


                    bubble.add(
                            top,
                            BorderLayout.NORTH
                    );

                    bubble.add(
                            text,
                            BorderLayout.CENTER
                    );


                    row.add(bubble);


                    chatMessagesPanel.add(row);


                    refreshChat();
                }
        );
    }


    // =========================
    // BOT MESSAGE
    // =========================

    private void appendBotMessage(
            String message
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    JPanel row =
                            new JPanel(
                                    new FlowLayout(
                                            FlowLayout.LEFT,
                                            0,
                                            0
                                    )
                            );

                    row.setOpaque(false);

                    row.setBorder(
                            new EmptyBorder(
                                    5,
                                    5,
                                    10,
                                    5
                            )
                    );


                    JPanel content =
                            new JPanel(
                                    new BorderLayout(
                                            10,
                                            0
                                    )
                            );

                    content.setOpaque(false);


                    LogoPanel logo =
                            new LogoPanel();

                    logo.setPreferredSize(
                            new Dimension(
                                    42,
                                    42
                            )
                    );


                    JPanel bubble =
                            new RoundedPanel(
                                    BOT_BUBBLE,
                                    15
                            );

                    bubble.setLayout(
                            new BorderLayout(
                                    0,
                                    5
                            )
                    );

                    bubble.setBorder(
                            new EmptyBorder(
                                    11,
                                    15,
                                    11,
                                    15
                            )
                    );


                    JLabel name =
                            new JLabel("SmartBot");

                    name.setForeground(
                            new Color(
                                    64,
                                    169,
                                    255
                            )
                    );

                    name.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.BOLD,
                                    13
                            )
                    );


                    JLabel time =
                            new JLabel(
                                    getCurrentTime()
                            );

                    time.setForeground(
                            new Color(
                                    168,
                                    190,
                                    218
                            )
                    );

                    time.setFont(
                            new Font(
                                    "Segoe UI",
                                    Font.PLAIN,
                                    10
                            )
                    );


                    JPanel top =
                            new JPanel(
                                    new BorderLayout()
                            );

                    top.setOpaque(false);

                    top.add(
                            name,
                            BorderLayout.WEST
                    );

                    top.add(
                            time,
                            BorderLayout.EAST
                    );


                    JTextArea text =
                            createMessageText(
                                    message
                            );


                    bubble.add(
                            top,
                            BorderLayout.NORTH
                    );

                    bubble.add(
                            text,
                            BorderLayout.CENTER
                    );


                    content.add(
                            logo,
                            BorderLayout.WEST
                    );

                    content.add(
                            bubble,
                            BorderLayout.CENTER
                    );


                    row.add(content);


                    chatMessagesPanel.add(row);


                    refreshChat();
                }
        );
    }


    // =========================
    // MESSAGE TEXT
    // =========================

    private JTextArea createMessageText(
            String message
    ) {

        JTextArea text =
                new JTextArea(
                        message
                );

        text.setEditable(false);

        text.setLineWrap(true);

        text.setWrapStyleWord(true);

        text.setOpaque(false);

        text.setForeground(TEXT);

        text.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        text.setBorder(null);

        text.setColumns(28);


        return text;
    }


    // =========================
    // REFRESH CHAT
    // =========================

    private void refreshChat() {

        chatMessagesPanel.revalidate();

        chatMessagesPanel.repaint();


        SwingUtilities.invokeLater(
                () -> {

                    JScrollBar bar =
                            chatScrollPane
                                    .getVerticalScrollBar();

                    bar.setValue(
                            bar.getMaximum()
                    );
                }
        );
    }


    // =========================
    // CURRENT TIME
    // =========================

    private String getCurrentTime() {

        return new SimpleDateFormat(
                "hh:mm a"
        ).format(
                new Date()
        );
    }


    // =========================
    // LOGOUT
    // =========================

    private void logout() {

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "SmartBot",
                        JOptionPane.YES_NO_OPTION
                );


        if (option ==
                JOptionPane.YES_OPTION) {

            dispose();

            new LoginFrame();
        }
    }


    // =========================
    // GRADIENT BUTTON
    // =========================

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
                            13
                    )
            );

            setFocusPainted(false);

            setBorderPainted(false);

            setContentAreaFilled(false);

            setOpaque(false);

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
                    (Graphics2D)
                            g.create();


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
                                150,
                                105,
                                255
                        );

                end =
                        new Color(
                                75,
                                150,
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
                    15,
                    15
            );


            g2.dispose();


            super.paintComponent(g);
        }
    }


    // =========================
    // ROUNDED PANEL
    // =========================

    private static class RoundedPanel
            extends JPanel {

        private final Color color;

        private final int radius;


        public RoundedPanel(
                Color color,
                int radius
        ) {

            this.color =
                    color;

            this.radius =
                    radius;

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();


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
                    getWidth() - 1,
                    getHeight() - 1,
                    radius,
                    radius
            );


            g2.dispose();


            super.paintComponent(g);
        }
    }


    // =========================
    // ROUNDED TEXT FIELD
    // =========================

    private static class RoundedTextField
            extends JTextField {

        public RoundedTextField() {

            setOpaque(false);
        }


        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            g2.setColor(
                    getBackground()
            );


            g2.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    20,
                    20
            );


            g2.dispose();


            super.paintComponent(g);
        }
    }


    // =========================
    // LOGO
    // =========================

    private static class LogoPanel
            extends JPanel {

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);


            Graphics2D g2 =
                    (Graphics2D)
                            g.create();


            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );


            int w =
                    getWidth();

            int h =
                    getHeight();


            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            new Color(
                                    35,
                                    130,
                                    255
                            ),
                            w,
                            h,
                            new Color(
                                    115,
                                    65,
                                    235
                            )
                    );


            g2.setPaint(
                    gradient
            );


            g2.fillRoundRect(
                    1,
                    1,
                    w - 2,
                    h - 2,
                    18,
                    18
            );


            // Robot face

            g2.setColor(
                    Color.WHITE
            );


            g2.fillRoundRect(
                    10,
                    13,
                    w - 20,
                    h - 23,
                    12,
                    12
            );


            g2.setColor(
                    new Color(
                            30,
                            90,
                            190
                    )
            );


            g2.fillOval(
                    17,
                    21,
                    7,
                    7
            );


            g2.fillOval(
                    w - 24,
                    21,
                    7,
                    7
            );


            g2.setStroke(
                    new BasicStroke(2)
            );


            g2.drawLine(
                    w / 2,
                    7,
                    w / 2,
                    13
            );


            g2.fillOval(
                    w / 2 - 3,
                    4,
                    6,
                    6
            );


            g2.dispose();
        }
    }


    // =========================
    // BACKGROUND
    // =========================

    private static class BackgroundPanel
            extends JPanel {

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);


            Graphics2D g2 =
                    (Graphics2D)
                            g.create();


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
                                    22,
                                    16,
                                    42
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


            g2.dispose();
        }
    }


    // =========================
    // MAIN
    // =========================

    public static void main(
            String[] args
    ) {

        try {

            User testUser =
                    new User(
                            1,
                            "aditya",
                            "",
                            "USER"
                    );


            SwingUtilities.invokeLater(
                    () ->
                            new ChatFrame(
                                    testUser
                            )
            );


        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}