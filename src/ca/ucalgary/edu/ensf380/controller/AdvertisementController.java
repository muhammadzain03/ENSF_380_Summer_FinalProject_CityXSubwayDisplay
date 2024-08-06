package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.view.AdvertisementPanel;
import ca.ucalgary.edu.ensf380.util.DatabaseUtil;

import java.sql.*;
import java.util.*;

public class AdvertisementController {
    private List<Map<String, Object>> advertisements;
    private int currentAdIndex = 0;
    private Timer timer;
    private AdvertisementPanel advertisementPanel;
    
    public AdvertisementController(AdvertisementPanel advertisementPanel) {
        this.advertisementPanel = advertisementPanel;
        try {
            this.advertisements = loadAds();
        } catch (SQLException e) {
            System.out.println("Error loading advertisements from the database: " + e.getMessage());
            this.advertisements = new ArrayList<>(); // Initialize to an empty list if loading fails
        }
        startAdRotation();
    }

    // Method to load advertisements from the database
    public List<Map<String, Object>> loadAds() throws SQLException {
        List<Map<String, Object>> ads = new ArrayList<>();
        String query = "SELECT * FROM advertisements";
        DatabaseUtil dbUtil = new DatabaseUtil();

        try {
            dbUtil.createConnection();
            ResultSet resultSet = dbUtil.selectQuery(query);

            while (resultSet.next()) {
                Map<String, Object> ad = new HashMap<>();
                ad.put("id", resultSet.getInt("id"));
                ad.put("media_type", resultSet.getString("media_type"));
                ad.put("media_path", resultSet.getString("media_path"));
                ad.put("display_duration", resultSet.getInt("display_duration"));
                ads.add(ad);
            }
        } finally {
            dbUtil.close();
        }
        return ads;
    }

    // Method to start rotating advertisements
    public void startAdRotation() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (advertisements != null && !advertisements.isEmpty()) {
                    Map<String, Object> ad = advertisements.get(currentAdIndex);
                    advertisementPanel.displayAdvertisement(ad);
                    currentAdIndex = (currentAdIndex + 1) % advertisements.size();
                    pauseAd(); // Pause after displaying the ad
                }
            }
        }, 0);
    }

    // Method to pause advertisement rotation
    private void pauseAd() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                advertisementPanel.displayAdvertisement(null); // Clear the ad during pause
                resumeAd(); // Resume after pause
            }
        }, 10000); // Display ad for 10 seconds
    }

    // Method to resume advertisement rotation
    private void resumeAd() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startAdRotation(); // Schedule the next ad
            }
        }, 5000); // Pause for 5 seconds
    }

    // Method to stop the advertisement rotation
    public void stopAdRotation() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
