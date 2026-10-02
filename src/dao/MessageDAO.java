package dao;

import db.DBConnection;
import model.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    // ==========================================
    // SAVE MESSAGE
    // ==========================================

    public void save(Message message) throws Exception {

        String sql = """
                INSERT INTO messages
                (user_id, user_message, bot_response)
                VALUES (?, ?, ?)
                """;

        // RUBRIC: JDBC
        // RUBRIC: PreparedStatement
        // RUBRIC: try-with-resources

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, message.getUserId());
            statement.setString(2, message.getUserMessage());
            statement.setString(3, message.getBotResponse());

            statement.executeUpdate();
        }
    }

    // ==========================================
    // GET CHAT HISTORY BY USER
    // ==========================================

    public List<Message> getHistoryByUser(int userId) throws Exception {

        List<Message> history = new ArrayList<>();

        String sql = """
                SELECT *
                FROM messages
                WHERE user_id = ?
                ORDER BY created_at ASC
                """;

        // RUBRIC: Collections - ArrayList
        // RUBRIC: ResultSet
        // RUBRIC: try-with-resources

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Timestamp timestamp =
                            resultSet.getTimestamp("created_at");

                    LocalDateTime createdAt = null;

                    if (timestamp != null) {
                        createdAt = timestamp.toLocalDateTime();
                    }

                    Message message = new Message(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_message"),
                            resultSet.getString("bot_response"),
                            createdAt
                    );

                    history.add(message);
                }
            }
        }

        return history;
    }

    // ==========================================
    // DELETE MESSAGE
    // ==========================================

    public void delete(int id) throws Exception {

        String sql = "DELETE FROM messages WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    // ==========================================
    // UPDATE MESSAGE
    // ==========================================

    public void update(Message message) throws Exception {

        String sql = """
                UPDATE messages
                SET user_message = ?, bot_response = ?
                WHERE id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, message.getUserMessage());
            statement.setString(2, message.getBotResponse());
            statement.setInt(3, message.getId());

            statement.executeUpdate();
        }
    }
}
