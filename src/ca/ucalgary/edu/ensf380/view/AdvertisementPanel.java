package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Advertisement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdvertisementPanel { 
    private static final String BASE_PATH = "advertisements/"; // Set the base path for advertisement files
	private JPanel panel;
    private JLabel adLabel; 

    public AdvertisementPanel() {
    	this.panel = new JPanel(new BorderLayout());
        this.adLabel = new JLabel("Loading ads...", SwingConstants.CENTER); 
        panel.add(adLabel, BorderLayout.CENTER); 
    }
    
    public JPanel getPanel() {
    	return panel;
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
    }
}
