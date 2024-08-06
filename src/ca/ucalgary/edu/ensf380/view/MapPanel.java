package ca.ucalgary.edu.ensf380.view;

import javax.swing.*;

import ca.ucalgary.edu.ensf380.model.Station;

import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {
	private ArrayList<Station> stations; // Assuming Point is a custom class or java.awt.Point

    public MapPanel(ArrayList<Station> stations) {
    	 this.stations = stations;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Station station : stations) {
            int x = (int) station.getX()/3; // Assuming getX() and getY() methods in Station class
            int y = (int) station.getY()/3;
            
            // Example: Draw a filled circle at each station location
            g.setColor(Color.RED);
            g.fillOval(x, y, 10, 10);
        }
    }
    
    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
        repaint(); // Trigger repaint to update the panel
    }

    // Example method to update points if needed
    
    public JPanel getPanel() {
		return this;
    	
    }
    
}