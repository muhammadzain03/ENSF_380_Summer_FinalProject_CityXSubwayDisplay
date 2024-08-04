 package ca.ucalgary.edu.ensf380.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ca.ucalgary.edu.ensf380.MyApp3;

public class SubwayScreenApp {
    public static void main(String[] args) throws IOException { 
        if (args.length != 2) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <train_line>");
            return;
        }

        String trainNumber = args[0];
        String trainLine = args[1];
        MyApp3.start();
        new SubwayScreenGUI(trainNumber, trainLine);
        
        
        String folderPath = "./out"; // Replace with your folder path
        
        // List all files in the directory
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".csv"));
        
        for (int i = 0; listOfFiles != null && listOfFiles.length > 0; i++) {
            // Assuming there's only one CSV file, or you can add logic to handle multiple files
            File csvFile = listOfFiles[i];
            System.out.println("Reading file: " + csvFile.getName());
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Process each line (assuming CSV format)
                    String[] values = line.split(",");
                    // Example: print each value in the CSV
                    if (values.length > 2) {
                        System.out.print(values[2]);
                    }
                    System.out.println(); // Move to the next line for the next CSV row
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }
}

