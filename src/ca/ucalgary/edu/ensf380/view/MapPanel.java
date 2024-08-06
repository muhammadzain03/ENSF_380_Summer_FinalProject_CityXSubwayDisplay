package ca.ucalgary.edu.ensf380.view;

import javax.swing.*;
import java.awt.*;

public class MapPanel {
	private JPanel panel;
	private JLabel mapLabel;
	
	
	public MapPanel() {
		this.panel = new JPanel(new BorderLayout());
		this.mapLabel = new JLabel("Loading Map...", SwingConstants.CENTER); 
        panel.add(mapLabel, BorderLayout.CENTER); 
	}
	
	public JPanel getPanel() {
		return panel;
	}
	

    
}
