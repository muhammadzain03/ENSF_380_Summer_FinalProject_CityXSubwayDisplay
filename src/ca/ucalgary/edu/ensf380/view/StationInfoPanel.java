package ca.ucalgary.edu.ensf380.view;


import javax.swing.*;
import java.awt.*;


public class StationInfoPanel {
	private JPanel panel;
    private JLabel[] stationLabel;

    public StationInfoPanel() {
    	this.panel = new JPanel(new GridLayout(1, 5));
    	panel.setPreferredSize(new Dimension(800, 100));
    	this.stationLabel = new JLabel[5];
    	for (int i = 0; i < 5; i++) {
    		stationLabel[i] = new JLabel("Next stations: Fetching...", SwingConstants.CENTER);
            panel.add(stationLabel[i]);
        }
    }
    
    public JPanel getPanel() {
    	return panel;
    }

    public void updateTrainPosition(String prev, String curr, String next, String next1, String next2) {
    	stationLabel[0].setText("Previous: " + prev);
        stationLabel[1].setText("Current: " + curr);
        stationLabel[2].setText("Next: " + next);
        stationLabel[3].setText("Next: " + next1);
        stationLabel[4].setText("Next: " + next2);
    }
}
