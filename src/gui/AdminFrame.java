package gui;

import dao.KnowledgeDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Knowledge;

public class AdminFrame extends JFrame {

    private JTable knowledgeTable;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton logoutButton;

    private final KnowledgeDAO knowledgeDAO;

    // =====================================================
    // COLORS
    // =====================================================

    private static final Color BACKGROUND =
            new Color(8, 12, 22);

    private static final Color TOP_BAR =
            new Color(16, 21, 34);

    private static final Color CARD =
            new Color(20, 26, 40);

    private static final Color TABLE_BG =
            new Color(24, 31, 46);

    private static final Color TEXT =
            new Color(245, 247, 250);

    private static final Color SECONDARY =
            new Color(155, 165, 184);

    private static final Color PURPLE =
            new Color(124, 92, 255);

    private static final Color BLUE =
            new Color(67, 126, 255);

    private static final Color GREEN =
            new Color(55, 190, 135);

    private static final Color RED =
            new Color(225, 75, 90);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminFrame() {

        knowledgeDAO = new KnowledgeDAO();

        setTitle("SmartBot - Admin Dashboard");

        setSize(1050, 680);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        createUI();

        loadKnowledge();

        setVisible(true);
    }


    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new BackgroundPanel();

        mainPanel.setLayout(
                new BorderLayout()
        );


        // =================================================
        // TOP BAR
        // =================================================

        JPanel topBar =
                new JPanel(
                        new BorderLayout()
                );

        topBar.setBackground(
                TOP_BAR
        );

        topBar.setBorder(
                new EmptyBorder(
                        15,
                        22,
                        15,
                        22
                )
        );


        // LEFT BRAND

        JPanel brandPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        brandPanel.setOpaque(false);


        JPanel logo =
                new LogoPanel();

        logo.setPreferredSize(
                new Dimension(48, 48)
        );

        logo.setLayout(
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
                        17
                )
        );

        logo.add(logoText);


        brandPanel.add(logo);

        brandPanel.add(
                Box.createHorizontalStrut(14)
        );


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
                        "SmartBot Admin"
                );

        title.setForeground(
                TEXT
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Knowledge Base Management"
                );

        subtitle.setForeground(
                SECONDARY
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );


        titlePanel.add(title);

        titlePanel.add(subtitle);

        brandPanel.add(titlePanel);


        topBar.add(
                brandPanel,
                BorderLayout.WEST
        );


        // LOGOUT

        logoutButton =
                new JButton(
                        "Logout"
                );

        styleSmallButton(
                logoutButton
        );

        logoutButton.addActionListener(
                e -> logout()
        );


        topBar.add(
                logoutButton,
                BorderLayout.EAST
        );


        mainPanel.add(
                topBar,
                BorderLayout.NORTH
        );


        // =================================================
        // CENTER CONTENT
        // =================================================

        JPanel contentPanel =
                new JPanel();

        contentPanel.setOpaque(false);

        contentPanel.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        20,
                        25
                )
        );


        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);


        JPanel headingBox =
                new JPanel();

        headingBox.setOpaque(false);

        headingBox.setLayout(
                new BoxLayout(
                        headingBox,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel heading =
                new JLabel(
                        "Knowledge Base"
                );

        heading.setForeground(
                TEXT
        );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );


        JLabel description =
                new JLabel(
                        "Teach SmartBot new questions and answers"
                );

        description.setForeground(
                SECONDARY
        );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );


        headingBox.add(heading);

        headingBox.add(
                Box.createVerticalStrut(5)
        );

        headingBox.add(description);


        headerPanel.add(
                headingBox,
                BorderLayout.WEST
        );


        // BUTTONS

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        buttonPanel.setOpaque(false);


        addButton =
                new JButton(
                        "+ Add"
                );

        updateButton =
                new JButton(
                        "✎ Update"
                );

        deleteButton =
                new JButton(
                        "✕ Delete"
                );

        refreshButton =
                new JButton(
                        "⟳ Refresh"
                );


        styleActionButton(
                addButton,
                PURPLE
        );

        styleActionButton(
                updateButton,
                BLUE
        );

        styleActionButton(
                deleteButton,
                RED
        );

        styleActionButton(
                refreshButton,
                new Color(
                        70,
                        80,
                        100
                )
        );


        buttonPanel.add(addButton);

        buttonPanel.add(updateButton);

        buttonPanel.add(deleteButton);

        buttonPanel.add(refreshButton);


        headerPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );


        contentPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =================================================
        // TABLE CARD
        // =================================================

        RoundedPanel tableCard =
                new RoundedPanel(
                        22,
                        CARD
                );

        tableCard.setLayout(
                new BorderLayout()
        );

        tableCard.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        // TABLE MODEL

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Question",
                                "Answer"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        knowledgeTable =
                new JTable(
                        tableModel
                );


        knowledgeTable.setBackground(
                TABLE_BG
        );

        knowledgeTable.setForeground(
                TEXT
        );

        knowledgeTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        knowledgeTable.setRowHeight(
                38
        );

        knowledgeTable.setSelectionBackground(
                new Color(
                        70,
                        62,
                        135
                )
        );

        knowledgeTable.setSelectionForeground(
                Color.WHITE
        );

        knowledgeTable.setGridColor(
                new Color(
                        45,
                        52,
                        70
                )
        );

        knowledgeTable.setAutoCreateRowSorter(
                true
        );


       // HEADER

        javax.swing.table.JTableHeader header =
                knowledgeTable.getTableHeader();

        header.setOpaque(true);

        header.setBackground(
                new Color(30, 37, 55)
        );

        header.setForeground(
                Color.WHITE
        );

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        // Force dark header cells
        header.setDefaultRenderer(
                new javax.swing.table.DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column
                ) {

                        JLabel label =
                                (JLabel) super
                                        .getTableCellRendererComponent(
                                                table,
                                                value,
                                                isSelected,
                                                hasFocus,
                                                row,
                                                column
                                        );

                        label.setOpaque(true);

                        label.setBackground(
                                new Color(30, 37, 55)
                        );

                        label.setForeground(
                                Color.WHITE
                        );

                        label.setFont(
                                new Font(
                                        "Segoe UI",
                                        Font.BOLD,
                                        13
                                )
                        );

                        label.setBorder(
                                new EmptyBorder(
                                        0,
                                        8,
                                        0,
                                        8
                                )
                        );

                        label.setHorizontalAlignment(
                                SwingConstants.LEFT
                        );

                        return label;
                }
                }
        );


                // COLUMN WIDTH

                knowledgeTable
                        .getColumnModel()
                        .getColumn(0)
                        .setPreferredWidth(60);

                knowledgeTable
                        .getColumnModel()
                        .getColumn(1)
                        .setPreferredWidth(280);

                knowledgeTable
                        .getColumnModel()
                        .getColumn(2)
                        .setPreferredWidth(550);


                JScrollPane scrollPane =
                        new JScrollPane(
                                knowledgeTable
                        );

                scrollPane.setBorder(
                        BorderFactory.createEmptyBorder()
                );

                scrollPane.getViewport()
                        .setBackground(
                                TABLE_BG
                        );

                scrollPane.getVerticalScrollBar()
                        .setUnitIncrement(16);


                tableCard.add(
                        scrollPane,
                        BorderLayout.CENTER
                );


                contentPanel.add(
                        tableCard,
                        BorderLayout.CENTER
                );


        // =================================================
        // BUTTON ACTIONS
        // =================================================

        addButton.addActionListener(
                e -> addKnowledge()
        );

        updateButton.addActionListener(
                e -> updateKnowledge()
        );

        deleteButton.addActionListener(
                e -> deleteKnowledge()
        );

        refreshButton.addActionListener(
                e -> loadKnowledge()
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        setContentPane(
                mainPanel
        );
    }


    // =====================================================
    // LOAD KNOWLEDGE
    // =====================================================

    private void loadKnowledge() {

        tableModel.setRowCount(0);

        try {

            List<Knowledge> list =
                    knowledgeDAO
                            .getAllKnowledge();


            for (Knowledge knowledge : list) {

                tableModel.addRow(
                        new Object[]{
                                knowledge.getId(),
                                knowledge.getQuestion(),
                                knowledge.getAnswer()
                        }
                );
            }


        } catch (Exception e) {

            showError(
                    "Unable to load knowledge base:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // ADD KNOWLEDGE
    // =====================================================

    private void addKnowledge() {

        JTextField questionField =
                new JTextField();

        JTextArea answerArea =
                new JTextArea(
                        6,
                        30
                );

        answerArea.setLineWrap(true);

        answerArea.setWrapStyleWord(true);


        JPanel panel =
                createKnowledgeForm(
                        "Add Knowledge",
                        questionField,
                        answerArea
                );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Knowledge",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }


        String question =
                questionField
                        .getText()
                        .trim();

        String answer =
                answerArea
                        .getText()
                        .trim();


        if (question.isEmpty()
                || answer.isEmpty()) {

            showError(
                    "Question and answer cannot be empty."
            );

            return;
        }


        try {

            Knowledge knowledge =
                    new Knowledge(
                            question,
                            answer
                    );


            knowledgeDAO.add(
                    knowledge
            );


            showSuccess(
                    "Knowledge added successfully!"
            );


            loadKnowledge();


        } catch (Exception e) {

            showError(
                    "Unable to add knowledge:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // UPDATE KNOWLEDGE
    // =====================================================

    private void updateKnowledge() {

        int selectedRow =
                knowledgeTable
                        .getSelectedRow();


        if (selectedRow == -1) {

            showWarning(
                    "Please select a row to update."
            );

            return;
        }


        int modelRow =
                knowledgeTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        int id =
                (int) tableModel
                        .getValueAt(
                                modelRow,
                                0
                        );


        String oldQuestion =
                String.valueOf(
                        tableModel.getValueAt(
                                modelRow,
                                1
                        )
                );


        String oldAnswer =
                String.valueOf(
                        tableModel.getValueAt(
                                modelRow,
                                2
                        )
                );


        JTextField questionField =
                new JTextField(
                        oldQuestion
                );


        JTextArea answerArea =
                new JTextArea(
                        oldAnswer,
                        6,
                        30
                );


        answerArea.setLineWrap(true);

        answerArea.setWrapStyleWord(true);


        JPanel panel =
                createKnowledgeForm(
                        "Update Knowledge",
                        questionField,
                        answerArea
                );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Update Knowledge",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }


        String question =
                questionField
                        .getText()
                        .trim();


        String answer =
                answerArea
                        .getText()
                        .trim();


        if (question.isEmpty()
                || answer.isEmpty()) {

            showError(
                    "Question and answer cannot be empty."
            );

            return;
        }


        try {

            Knowledge knowledge =
                    new Knowledge(
                            id,
                            question,
                            answer
                    );


            knowledgeDAO.update(
                    knowledge
            );


            showSuccess(
                    "Knowledge updated successfully!"
            );


            loadKnowledge();


        } catch (Exception e) {

            showError(
                    "Unable to update knowledge:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // DELETE KNOWLEDGE
    // =====================================================

    private void deleteKnowledge() {

        int selectedRow =
                knowledgeTable
                        .getSelectedRow();


        if (selectedRow == -1) {

            showWarning(
                    "Please select a row to delete."
            );

            return;
        }


        int modelRow =
                knowledgeTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        int id =
                (int) tableModel
                        .getValueAt(
                                modelRow,
                                0
                        );


        String question =
                String.valueOf(
                        tableModel.getValueAt(
                                modelRow,
                                1
                        )
                );


        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this knowledge?\n\n"
                                + question,
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }


        try {

            knowledgeDAO.delete(id);


            showSuccess(
                    "Knowledge deleted successfully!"
            );


            loadKnowledge();


        } catch (Exception e) {

            showError(
                    "Unable to delete knowledge:\n"
                            + e.getMessage()
            );
        }
    }


    // =====================================================
    // FORM PANEL
    // =====================================================

    private JPanel createKnowledgeForm(
            String title,
            JTextField questionField,
            JTextArea answerArea
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        JPanel form =
                new JPanel();

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel questionLabel =
                new JLabel(
                        "Question"
                );


        JLabel answerLabel =
                new JLabel(
                        "Answer"
                );


        questionField.setPreferredSize(
                new Dimension(
                        400,
                        35
                )
        );


        JScrollPane answerScroll =
                new JScrollPane(
                        answerArea
                );

        answerScroll.setPreferredSize(
                new Dimension(
                        400,
                        130
                )
        );


        form.add(questionLabel);

        form.add(
                Box.createVerticalStrut(5)
        );

        form.add(questionField);

        form.add(
                Box.createVerticalStrut(12)
        );

        form.add(answerLabel);

        form.add(
                Box.createVerticalStrut(5)
        );

        form.add(answerScroll);


        panel.add(
                form,
                BorderLayout.CENTER
        );


        return panel;
    }


    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "SmartBot",
                        JOptionPane.YES_NO_OPTION
                );


        if (result ==
                JOptionPane.YES_OPTION) {

            dispose();

            new LoginFrame();
        }
    }


    // =====================================================
    // SUCCESS MESSAGE
    // =====================================================

    private void showSuccess(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "SmartBot",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =====================================================
    // WARNING MESSAGE
    // =====================================================

    private void showWarning(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "SmartBot",
                JOptionPane.WARNING_MESSAGE
        );
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
    // SMALL BUTTON STYLE
    // =====================================================

    private void styleSmallButton(
            JButton button
    ) {

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                new Color(
                        35,
                        42,
                        60
                )
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);


        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                new EmptyBorder(
                        8,
                        16,
                        8,
                        16
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }


    // =====================================================
    // ACTION BUTTON STYLE
    // =====================================================

    private void styleActionButton(
            JButton button,
            Color color
    ) {

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setBorderPainted(false);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                new EmptyBorder(
                        9,
                        15,
                        9,
                        15
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
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
                    16,
                    16
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
                                    23,
                                    17,
                                    44
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


            // Purple glow

            g2.setColor(
                    new Color(
                            124,
                            92,
                            255,
                            25
                    )
            );


            g2.fillOval(
                    -150,
                    -120,
                    320,
                    320
            );


            // Blue glow

            g2.setColor(
                    new Color(
                            67,
                            126,
                            255,
                            18
                    )
            );


            g2.fillOval(
                    getWidth() - 180,
                    getHeight() - 150,
                    320,
                    320
            );


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
                AdminFrame::new
        );
    }
}