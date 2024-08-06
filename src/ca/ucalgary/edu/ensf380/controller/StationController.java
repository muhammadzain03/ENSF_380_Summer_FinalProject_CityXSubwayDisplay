package ca.ucalgary.edu.ensf380.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import ca.ucalgary.edu.ensf380.model.Station;
import ca.ucalgary.edu.ensf380.model.Train;
import ca.ucalgary.edu.ensf380.view.SubwayScreenGUI;

public class StationController {
	private static ArrayList<Station> stations = new ArrayList<Station>();
    
	public String nextStationNum; 
	public String currentStation;
	
    public StationController() {
    	populateStation();
    	
    }
    
    public ArrayList<Station> getStations(){
		return stations;
		
	}
    
    private void populateStation() {
    	String folderPath = "./Map/Map.csv"; // Replace with your folder path
    	
    	// List all files in the directory
    	File folder = new File(folderPath);
    	
    	
    	System.out.println("Reading file: " + folder.getName());
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(folder))) {
    		String line;
    		
    		if ((line = br.readLine()) != null) {
    			// Ignored header line
    		}
    		while ((line = br.readLine()) != null) {
    			// Process each line (assuming CSV format)
    			// Move to the next line for the next CSV row
    			String[] values = line.split(",");
    			
    			
    			//System.out.println(values[3] +" " +  values[4]);
    			setStations(values[4], values[3],values[0], Double.valueOf(values[5]) ,  Double.valueOf(values[6]) );
    			// Example: print each value in the CSV
    			
    			
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    private void setStations(String name, String code, String num, double x, double y) {
    	Station station = new Station(name, code,num ,x, y);
    	stations.add(station);
    	
    	
    }
    
    public void updateTrainPos(int trainNum, ArrayList<Train> x, SubwayScreenGUI gui) {
 
    	 ArrayList<Station> stationList = getStations();
    	 String previousStation = null;
    	 String nextStation = null;
    	 String nextStation1 = null;
    	 String nextStation2 = null;
		for(Station stations : stationList) {
			
			
			if (stations.getCode().equals(x.get(trainNum).getPosition())) {
				System.out.println("Train T"+ x.get(trainNum).getId()+ " is at " + stations.getName() + " moving " + x.get(trainNum).getDirection());
				 currentStation = stations.getName();
				System.out.println(x.get(trainNum).getDirection());
				if (x.get(trainNum).getDirection().equals("forward")) {
					
					int prevStationNumber = Integer.parseInt(stations.getNumber()) - 1;
					String previousStationString = Integer.toString(prevStationNumber);
					
					int nextStationNumber = Integer.parseInt(stations.getNumber()) + 1;
					String nextStationNumberString = String.valueOf(nextStationNumber);
					
					int nextStationNumber1 = Integer.parseInt(stations.getNumber()) + 2;
					String nextStationNumberString1 = String.valueOf(nextStationNumber1);
					
					int nextStationNumber2 = Integer.parseInt(stations.getNumber()) + 3;
					String nextStationNumberString2 = String.valueOf(nextStationNumber2);
					System.out.println("inside IF");
					for (Station station : stationList) {
			            if (station.getNumber().equals(previousStationString)) {
			                previousStation = station.getName();
			                System.out.print(previousStation);
			            }
			            if (station.getNumber().equals(nextStationNumberString)) {
			            	nextStation = station.getName();
			            	nextStationNum = station.getCode();		
			            	}
			            if (station.getNumber().equals(nextStationNumberString1)) {
							 nextStation1 = station.getName();
						}
			            if (station.getNumber().equals(nextStationNumberString2)) {
							 nextStation2 = station.getName();
						}
			            gui.getStationInfoPanel().updateTrainPosition(previousStation, currentStation, nextStation, nextStation1, nextStation2);
			        }
					
				
					
					
				} else if(x.get(trainNum).getDirection().equals("backward")){
					int prevStationNumber = Integer.parseInt(stations.getNumber()) + 1;
					String previousStationString = Integer.toString(prevStationNumber);
					
					int nextStationNumber = Integer.parseInt(stations.getNumber()) - 1;
					String nextStationNumberString = String.valueOf(nextStationNumber);
					
					int nextStationNumber1 = Integer.parseInt(stations.getNumber()) - 2;
					String nextStationNumberString1 = String.valueOf(nextStationNumber1);
					
					int nextStationNumber2 = Integer.parseInt(stations.getNumber()) - 3;
					String nextStationNumberString2 = String.valueOf(nextStationNumber2);
					System.out.println("inside IF");
					for (Station station : stationList) {
			            if (station.getNumber().equals(previousStationString)) {
			                previousStation = station.getName();
			                System.out.print(previousStation);
			            }
			            if (station.getNumber().equals(nextStationNumberString)) {
			            	nextStation = station.getName();
			            	nextStationNum = station.getCode();	
						}
			            if (station.getNumber().equals(nextStationNumberString1)) {
							 nextStation1 = station.getName();
						}
			            if (station.getNumber().equals(nextStationNumberString2)) {
							 nextStation2 = station.getName();
						}
			            gui.getStationInfoPanel().updateTrainPosition(previousStation, currentStation, nextStation, nextStation1, nextStation2);
			        }
				}
				
				
			}
		}
    	
    }
    
    
}
    

	

