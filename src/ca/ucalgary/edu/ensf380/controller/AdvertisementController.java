package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.Advertisement;
import ca.ucalgary.edu.ensf380.util.DatabaseUtil;
import ca.ucalgary.edu.ensf380.view.AdvertisementPanel;

import java.sql.*;
import java.util.*;

public class AdvertisementController {
    private List<Advertisement> advertisements; 	// List to store advertisements
    private int currentAdIndex = 0; 				// Index to track the current advertisement
    private Timer timer; 							// Timer to schedule ad rotation
    private AdvertisementPanel advertisementPanel; 	// Reference to the "AdvertisementPanel.java"

    // Constructor to initialize the AdvertisementController
    public AdvertisementController(AdvertisementPanel advertisementPanel) {
        this.advertisementPanel = advertisementPanel;	// Assign the AdvertisementPanel reference
        try {
            this.advertisements = loadAds(); // Load advertisements from the database
        } catch (SQLException e) {
            System.out.println("Error loading advertisements from the database: " + e.getMessage());
            this.advertisements = new ArrayList<>(); // Initialize to an empty list if loading fails
        }
        startAdRotation(); // Start rotating the advertisements
    }

    // Method to load advertisements from the database
    public List<Advertisement> loadAds() throws SQLException {
        List<Advertisement> ads = new ArrayList<>();	// Initialize the list of advertisements
        String query = "SELECT * FROM advertisements";	// SQL query to select all advertisements
        DatabaseUtil dbUtil = new DatabaseUtil();		// Reference to "DatabaseUtil.java" to handle database operations
        
        try {
            dbUtil.createConnection(); // Connect to the database - called from "DatabaseUtil.java"
            ResultSet resultSet = dbUtil.selectQuery(query);	// Execute the query and get the result set

            // Iterate through the result set and create Advertisement objects
            while (resultSet.next()) {
                Advertisement ad = new Advertisement(
                    resultSet.getInt("id"),
                    resultSet.getString("media_type"),
                    resultSet.getString("media_path"),
                    resultSet.getInt("display_duration")
                );
                ads.add(ad); // Add the advertisement to the list
            }
        } finally {
            dbUtil.close(); // Close the database connection
        }
        return ads;
        // Return the list of advertisements
    }

    // Method to start rotating advertisements
    private void startAdRotation() {
        timer = new Timer();	// Initialize the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	// Check if there are advertisements and the current index is within bounds
                if (advertisements != null && !advertisements.isEmpty()) {
                    Advertisement ad = advertisements.get(currentAdIndex);	// Get the current advertisement
                    advertisementPanel.displayAdvertisement(ad); 			// Display the current advertisement
                    currentAdIndex = (currentAdIndex + 1) % advertisements.size();	// Increment and wrap the index
                }
            }
        }, 0, 10000); // Rotate ads every 10 seconds
    }

    // Method to stop the advertisement rotation
    public void stopAdRotation() {
        if (timer != null) {
            timer.cancel(); // Cancel the timer if it is not null
        }
    }
}
