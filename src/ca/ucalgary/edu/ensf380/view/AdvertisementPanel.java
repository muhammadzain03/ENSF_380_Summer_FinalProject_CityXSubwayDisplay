package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Advertisement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdvertisementPanel extends JPanel {
    private static final String BASE_PATH = "advertisements/"; // Set the base path for advertisement files
    private JLabel adLabel; // Label to display advertisements
    private Timer timer; // Timer to handle ad rotation
    private int currentAdIndex = 0; // Index to keep track of the current advertisement
    private List<Advertisement> advertisements; // List of advertisements to be displayed

    // Constructor to initialize the AdvertisementPanel
    public AdvertisementPanel() {
        setLayout(new BorderLayout()); // Set the layout to BorderLayout
        adLabel = new JLabel("", SwingConstants.CENTER); // Initialize the advertisement label
        add(adLabel, BorderLayout.CENTER); // Add the label to the center of the panel
    }

    // Method to set the list of advertisements
    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
        startAdRotation(); // Start the ad rotation
    }

    // Method to display an advertisement based on its type
    public void displayAdvertisement(Advertisement ad) {
        String mediaType = ad.getMediaType();
        String mediaPath = BASE_PATH + ad.getMediaPath(); // Construct the full path to the media file

        switch (mediaType) {
            case "JPEG":
            case "BMP":
            case "GIF":
                // Display image advertisements (including GIFs)
                ImageIcon imageIcon = new ImageIcon(mediaPath);
                adLabel.setIcon(imageIcon);
                adLabel.setText("");
                break;
            default:
                // Handle unsupported media types
                adLabel.setIcon(null);
                adLabel.setText("Unsupported media type: " + mediaType);
                break;
        }
        revalidate();
        repaint();
    }

    // Method to start the advertisement rotation
    private void startAdRotation() {
        if (timer != null) {
            timer.cancel(); // Cancel any existing timer
        }

        timer = new Timer(); // Initialize a new timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (advertisements != null && !advertisements.isEmpty()) {
                        if (currentAdIndex < advertisements.size()) {
                            // Display the next advertisement in the list
                            Advertisement ad = advertisements.get(currentAdIndex);
                            displayAdvertisement(ad);
                            currentAdIndex = (currentAdIndex + 1) % advertisements.size(); // Cycle back to the start
                        }
                    }
                });
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
