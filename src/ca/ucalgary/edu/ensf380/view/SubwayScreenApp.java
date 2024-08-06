 package ca.ucalgary.edu.ensf380.view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import ca.ucalgary.edu.ensf380.MyApp3;
import ca.ucalgary.edu.ensf380.controller.ReadSimulatorOutput;
import ca.ucalgary.edu.ensf380.controller.StationController;
import ca.ucalgary.edu.ensf380.view.StationInfoPanel;
import ca.ucalgary.edu.ensf380.model.Station;
import ca.ucalgary.edu.ensf380.model.Train;

public class SubwayScreenApp {
	
    public static void main(String[] args) { 
        if (args.length != 3) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <city> <country_code>");
        }else {
            String trainNumber = args[0];
            String city = args[1];
            String countryCode = args[2];
            
            int trainNum = Integer.parseInt(args[0]) - 1;
            
            MyApp3 app3 = new MyApp3();
            
            ReadSimulatorOutput output = new ReadSimulatorOutput();
            StationController stationController = new StationController();
            SubwayScreenGUI	gui	= new SubwayScreenGUI(trainNumber, city, countryCode, stationController.getStations());

            while (app3.running != false) {

				output.readOutput();
				ArrayList<Train> x = output.getTrains();
				System.out.println(x.get(trainNum).getPosition());				
				
				stationController.updateTrainPos(trainNum, x, gui);
				String lolString = stationController.nextStationNum;
				
				String audioFolder = "./audio/";

		        String audioFilePath = audioFolder + lolString.trim() +".mp3";

		  
		        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(audioFilePath))) {
		            AdvancedPlayer player = new AdvancedPlayer(bis);
		            player.play();
		        } catch (FileNotFoundException e) {
		            System.err.println("File not found: " + audioFilePath);
		        } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
                    Thread.sleep(13000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		        
			}
            
            
        }
        
        
    }


    }
        
        


