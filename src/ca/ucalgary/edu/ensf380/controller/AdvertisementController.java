package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.Advertisement;
import ca.ucalgary.edu.ensf380.util.DatabaseUtil;
import ca.ucalgary.edu.ensf380.view.AdvertisementPanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdvertisementController {
    private List<Advertisement> advertisements;
    private int currentAdIndex = 0;
    private Timer timer;
    private AdvertisementPanel advertisementPanel;

    // Constructor to initialize the AdvertisementController
    public AdvertisementController(AdvertisementPanel advertisementPanel) {
        this.advertisementPanel = advertisementPanel;
        try {
            this.advertisements = loadAds(); // Load advertisements from the database
        } catch (Exception e) {
            e.printStackTrace();
        }
        startAdRotation(); // Start rotating the advertisements
    }

    // Method to load advertisements from the database
    public List<Advertisement> loadAds() throws SQLException {
        List<Advertisement> ads = new ArrayList<>();
        String query = "SELECT * FROM advertisements";
        DatabaseUtil dbUtil = new DatabaseUtil();

        try {
            dbUtil.connect(); // Connect to the database
            ResultSet resultSet = dbUtil.query(query);

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
        return ads; // Return the list of advertisements
    }

    // Method to start rotating advertisements
    private void startAdRotation() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentAdIndex < advertisements.size()) {
                    Advertisement ad = advertisements.get(currentAdIndex);
                    advertisementPanel.displayAdvertisement(ad); // Display the current advertisement
                    currentAdIndex++;
                } else {
                    advertisementPanel.displayMap(); // Display the map every 10 seconds for 5 seconds
                    currentAdIndex = 0;
                }
            }
        }, 0, 10000); // Rotate ads every 10 seconds
    }

    // Method to stop the advertisement rotation
    public void stopAdRotation() {
        if (timer != null) {
            timer.cancel(); // Cancel the timer
        }
    }
}
