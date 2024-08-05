package ca.ucalgary.edu.ensf380.util;

import java.sql.*;

public class DatabaseUtil {
    private Connection dbConnect; // Connection object to manage the database connection
    private ResultSet results; // ResultSet object to hold query results

    public DatabaseUtil() {
        // Constructor for DatabaseUtil
    }

    // Method to create a connection to the database
    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/subway_screen", "root", "Qwerty$455");		// (URL, USER, PASSWORD) of database
            System.out.println("Connection to MySQL database established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute a SELECT query and return the results
    public ResultSet selectQuery(String query) {
        ResultSet results = null;
        try {
            Statement statement = dbConnect.createStatement();
            results = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // Method to insert a new advertisement into the database
    public void insertAdvertisement(int id, String mediaType, String mediaPath, int displayDuration) {
        try {
            String query = "INSERT INTO advertisements (id, media_type, media_path, display_duration) VALUES (?,?,?,?)";
            PreparedStatement newstatement = dbConnect.prepareStatement(query);

            newstatement.setInt(1, id);
            newstatement.setString(2, mediaType);
            newstatement.setString(3, mediaPath);
            newstatement.setInt(4, displayDuration);

            int rowCount = newstatement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            newstatement.close();	// Close the statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an advertisement from the database
    public void deleteAdvertisement(String mediaPath) {
        try {
            String query = "DELETE FROM advertisements WHERE media_path = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(query);

            preparedStatement.setString(1, mediaPath);

            int rowCount = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Method to close the database connection and ResultSet
    public void close() {
        try {
            if (results != null) results.close();
            if (dbConnect != null) dbConnect.close();
            System.out.println("Connection to MySQL database closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
