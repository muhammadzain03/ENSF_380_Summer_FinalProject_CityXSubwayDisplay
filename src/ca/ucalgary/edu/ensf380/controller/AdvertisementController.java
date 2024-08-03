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

    public AdvertisementController(AdvertisementPanel advertisementPanel) {
        this.advertisementPanel = advertisementPanel;
        try {
            this.advertisements = loadAds();
        } catch (Exception e) {
            e.printStackTrace();
        }
        startAdRotation();
    }

    public List<Advertisement> loadAds() throws SQLException {
        List<Advertisement> ads = new ArrayList<>();
        String query = "SELECT * FROM advertisements";
        DatabaseUtil dbUtil = new DatabaseUtil();

        try {
            dbUtil.connect();
            ResultSet resultSet = dbUtil.query(query);

            while (resultSet.next()) {
                Advertisement ad = new Advertisement(
                    resultSet.getInt("id"),
                    resultSet.getString("media_type"),
                    resultSet.getString("media_path"),
                    resultSet.getInt("display_duration")
                );
                ads.add(ad);
            }
        } finally {
            dbUtil.close();
        }
        return ads;
    }

    private void startAdRotation() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentAdIndex < advertisements.size()) {
                    Advertisement ad = advertisements.get(currentAdIndex);
                    advertisementPanel.displayAdvertisement(ad);
                    currentAdIndex++;
                } else {
                    advertisementPanel.displayMap();
                    currentAdIndex = 0;
                }
            }
        }, 0, 10000); // Rotate ads every 10 seconds
    }

    public void stopAdRotation() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
