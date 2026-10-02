package dao;

import db.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Repository<User> {

    // =========================
    // REGISTER USER
    // =========================

    @Override
    public void save(User user) throws Exception {

        String sql = """
                INSERT INTO users (username, password, role)
                VALUES (?, ?, ?)
                """;

        // RUBRIC: JDBC + PreparedStatement + try-with-resources
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            statement.executeUpdate();
        }
    }

    // =========================
    // GET ALL USERS
    // =========================

    @Override
    public List<User> getAll() throws Exception {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        // RUBRIC: ResultSet + try-with-resources
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );

                users.add(user);
            }
        }

        return users;
    }

    // =========================
    // FIND USER BY USERNAME
    // =========================

    public User findByUsername(String username) throws Exception {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                }
            }
        }

        return null;
    }

    // =========================
    // LOGIN
    // =========================

    public User login(String username, String password) throws Exception {

        String sql = """
                SELECT * FROM users
                WHERE username = ? AND password = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                }
            }
        }

        return null;
    }

    // =========================
    // UPDATE USER
    // =========================

    @Override
    public void update(User user) throws Exception {

        String sql = """
                UPDATE users
                SET username = ?, password = ?, role = ?
                WHERE id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());

            statement.executeUpdate();
        }
    }

    // =========================
    // DELETE USER
    // =========================

    @Override
    public void delete(int id) throws Exception {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}