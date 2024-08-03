package ca.ucalgary.edu.ensf380.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/subway_screen";
    private static final String USER = "root";
    private static final String PASSWORD = "Qwerty$455";

    public DatabaseUtil() {
        // Initialization code
    }

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection to MySQL database established.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet query(String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection to MySQL database closed.");
        }
    }

    // Utility method to execute update (INSERT, UPDATE, DELETE) queries
    public int update(String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }
}
