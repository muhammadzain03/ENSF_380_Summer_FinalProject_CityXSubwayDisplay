package ca.ucalgary.edu.ensf380.view;

import javax.swing.*;
import javax.swing.table.TableStringConverter;

import ca.ucalgary.edu.ensf380.controller.ReadSimulatorOutput;
import ca.ucalgary.edu.ensf380.controller.StationController;
import ca.ucalgary.edu.ensf380.model.Station;
import ca.ucalgary.edu.ensf380.model.Train;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MapPanel extends JPanel {
	private ArrayList<Station> stations;
	private ArrayList<Train> trainInfo;
	private String trainNum;
	

    public MapPanel(ArrayList<Station> stations, String trainNumber) {
    	 setTrains();
    	 this.stations = stations;
    	 this.trainNum = trainNumber;
   
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Station station : stations) {
            int x = (int) station.getX()/2; // Assuming getX() and getY() methods in Station class
            int y = (int) station.getY()/2;
            
            // Example: Draw a filled circle at each station location
            String stationNumber = station.getCode();
            
            if (stationNumber.startsWith("R")) {
				g.setColor(Color.RED);
			}else if(stationNumber.startsWith("G")) {
				g.setColor(Color.GREEN);
			}else if(stationNumber.startsWith("B")) {
				g.setColor(Color.BLUE);
			}
            
            g.fillOval(x, y, 10, 10);
			 }     
        
        for(Train trainData: trainInfo) {
        	for(Station station : stations) {
        		String stationCode = station.getCode();
        		String trainCode = trainData.getPosition();
        		if (stationCode.equals(trainCode)) {
        			 int x = (int) station.getX()/2; // Assuming getX() and getY() methods in Station class
        	         int y = (int) station.getY()/2;
        	         
        	         
        	         g.setColor(Color.BLACK);
        	         g.fillOval(x, y, 10, 10);
				}
        		
        		
        		
        	}
        	
        }
    
    }
    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
        repaint(); // Trigger repaint to update the panel
    }
    
    public void setTrains() {
        ReadSimulatorOutput output = new ReadSimulatorOutput();
        output.readOutput();
        trainInfo = output.getTrains();
        repaint(); // Trigger repaint to update the panel
    }

    // Example method to update points if needed
    
    public JPanel getPanel() {
		return this;
    	
    }
    
}
