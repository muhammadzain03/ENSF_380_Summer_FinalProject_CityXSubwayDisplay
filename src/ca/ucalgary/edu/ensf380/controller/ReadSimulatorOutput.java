package ca.ucalgary.edu.ensf380.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import ca.ucalgary.edu.ensf380.MyApp3;
import ca.ucalgary.edu.ensf380.model.Train;

public class ReadSimulatorOutput {
	private static ArrayList<Train> trains = new ArrayList<Train>();

	public ReadSimulatorOutput() {

		
	}
	
	public ArrayList<Train> getTrains(){
		//System.out.print(trains.get(0).getPosition());
		return trains;
		
	}
	
	
	
    
    public void readOutput() {
            
        	String folderPath = "./out"; 
        	trains.clear();
        	File folder = new File(folderPath);
        	File[] listOfFiles = folder.listFiles();
        	File latest = getLatestFile(listOfFiles);
            File csvFile = latest;
            System.out.println("Reading file: " + csvFile.getName());
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                
                if ((line = br.readLine()) != null) {
                   
                }
                while ((line = br.readLine()) != null) {
                   
                    String[] values = line.split(",");
                   
                    
                    setTrains(values[1], values[2], values[3]);
                        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    	
    }
    
    
    private File getLatestFile(File[] files) {
    	if (files == null || files.length == 0) {
    		return null;
    	}
    	
    	Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
    	return files[0];
    }
    
    private void setTrains(String number, String station, String dir) {
    	Train train = new Train(number, station, dir);
    	trains.add(train);
    	
    }

}




