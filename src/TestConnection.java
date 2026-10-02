import db.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection connection = DBConnection.getConnection();

            System.out.println("================================");
            System.out.println("Database Connected Successfully!");
            System.out.println("================================");

            connection.close();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }
}