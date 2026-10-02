package dao;

import db.DBConnection;
import model.Knowledge;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeDAO {

    // ==========================================
    // ADD KNOWLEDGE
    // ==========================================

    public void add(Knowledge knowledge) throws Exception {

        String sql = """
                INSERT INTO knowledge (question, answer)
                VALUES (?, ?)
                """;

        // RUBRIC: JDBC
        // RUBRIC: PreparedStatement
        // RUBRIC: try-with-resources

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, knowledge.getQuestion());
            statement.setString(2, knowledge.getAnswer());

            statement.executeUpdate();
        }
    }

    // ==========================================
    // UPDATE KNOWLEDGE
    // ==========================================

    public void update(Knowledge knowledge) throws Exception {

        String sql = """
                UPDATE knowledge
                SET question = ?, answer = ?
                WHERE id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, knowledge.getQuestion());
            statement.setString(2, knowledge.getAnswer());
            statement.setInt(3, knowledge.getId());

            statement.executeUpdate();
        }
    }

    // ==========================================
    // DELETE KNOWLEDGE
    // ==========================================

    public void delete(int id) throws Exception {

        String sql = "DELETE FROM knowledge WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }

    // ==========================================
    // GET ALL KNOWLEDGE AS LIST
    // ==========================================

    public List<Knowledge> getAllKnowledge() throws Exception {

        List<Knowledge> knowledgeList = new ArrayList<>();

        String sql = "SELECT * FROM knowledge ORDER BY id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Knowledge knowledge = new Knowledge(
                        resultSet.getInt("id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer")
                );

                knowledgeList.add(knowledge);
            }
        }

        return knowledgeList;
    }

    // ==========================================
    // GET ALL AS MAP
    // ==========================================

    // RUBRIC: Collections - Map
    public Map<String, String> getAll() throws Exception {

        Map<String, String> knowledgeMap = new LinkedHashMap<>();

        String sql = "SELECT question, answer FROM knowledge";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");

                knowledgeMap.put(question, answer);
            }
        }

        return knowledgeMap;
    }
}