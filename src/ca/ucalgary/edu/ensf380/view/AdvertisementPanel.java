package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Advertisement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class AdvertisementPanel extends JPanel {
    private JLabel adLabel;
    private List<Advertisement> advertisements;
    private int currentAdIndex = 0;
    private Timer timer;

    public AdvertisementPanel() {
        setLayout(new BorderLayout());
        adLabel = new JLabel("", SwingConstants.CENTER);
        add(adLabel, BorderLayout.CENTER);
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
        startAdRotation();
    }

    public void displayAdvertisement(Advertisement ad) {
        adLabel.setText("Displaying: " + ad.getMediaType() + " - " + ad.getMediaPath());
    }

    public void displayMap() {
        adLabel.setText("Displaying Subway Map");
    }

    private void startAdRotation() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (advertisements != null && !advertisements.isEmpty()) {
                    if (currentAdIndex < advertisements.size()) {
                        Advertisement ad = advertisements.get(currentAdIndex);
                        displayAdvertisement(ad);
                        currentAdIndex++;
                    } else {
                        displayMap();
                        currentAdIndex = 0;
                    }
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
