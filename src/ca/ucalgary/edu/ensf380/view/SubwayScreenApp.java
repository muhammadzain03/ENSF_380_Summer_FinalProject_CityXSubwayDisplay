 package ca.ucalgary.edu.ensf380.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.SysexMessage;
import javax.swing.table.TableStringConverter;

import ca.ucalgary.edu.ensf380.MyApp3;
import ca.ucalgary.edu.ensf380.model.Train;

public class SubwayScreenApp {
	
	private static List<Train> trains = new ArrayList<Train>();
	private static TrainInfo trainInfo;

    public static void main(String[] args) throws IOException { 
        if (args.length != 2) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <train_line>");
            return;
        }

        String trainNumber = args[0];
        String trainLine = args[1];
        
        SubwayScreenApp app = new SubwayScreenApp();
        app.initialize(trainNumber, trainLine);
    }
        
        private void initialize(String trainNumber, String trainLine) throws IOException {
        	new MyApp3();
            new SubwayScreenGUI(trainNumber, trainLine);
            this.trainInfo = new TrainInfo(); // Initialize TrainInfo object
            
            try {
            	
                Thread.sleep(5000); // 5 seconds = 5000 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                readOutput();
               
            }
        }
        
       
    
    private static File getLatestFile(File[] files) {
        if (files == null || files.length == 0) {
            return null;
        }
        
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }
    
    private static void readOutput() {
            // Assuming there's only one CSV file, or you can add logic to handle multiple files
        	String folderPath = "./out"; // Replace with your folder path
        	
        	// List all files in the directory
        	File folder = new File(folderPath);
        	File[] listOfFiles = folder.listFiles();
        	File latest = getLatestFile(listOfFiles);
            File csvFile = latest;
            System.out.println("Reading file: " + csvFile.getName());
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                
                if ((line = br.readLine()) != null) {
                    // Ignored header line
                }
                while ((line = br.readLine()) != null) {
                    // Process each line (assuming CSV format)
                	// Move to the next line for the next CSV row
                    String[] values = line.split(",");
                   
                    
                    setTrains(values[1], values[2]);
                    
                    // Example: print each value in the CSV
                    
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            	updateTrainsPanel();
            try {
                Thread.sleep(13000); // 15 seconds = 15,000 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       
    	
    }
    
    private static void setTrains(String number, String station) {
    	Train train = new Train(number, station);
    	trains.add(train);
    	
    	
    	
    }
    
    public static void updateTrainsPanel() {
    	trainInfo.updateTrainPositions(trains);
    	
    }
    
}

