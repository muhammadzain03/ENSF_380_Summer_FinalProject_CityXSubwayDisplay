package ca.ucalgary.edu.ensf380.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ca.ucalgary.edu.ensf380.model.Station;
import ca.ucalgary.edu.ensf380.model.Train;

public class StationController {
	private static ArrayList<Station> stations = new ArrayList<Station>();
    
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
    			setStations(values[4], values[3],values[0] );
    			// Example: print each value in the CSV
    			
    			
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    private void setStations(String name, String code, String num) {
    	Station station = new Station(name, code,num);
    	stations.add(station);
    	
    }
    
    
}
    

	

