package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smartbot";

    private static final String USER = "root";

    // Yahan apna MySQL password likho
    private static final String PASSWORD = "Aditya@81022";


    // RUBRIC: JDBC Connectivity
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}