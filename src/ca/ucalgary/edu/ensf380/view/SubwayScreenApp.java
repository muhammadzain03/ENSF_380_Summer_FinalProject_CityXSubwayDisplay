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
            SubwayScreenGUI	gui	= new SubwayScreenGUI(trainNumber, city, countryCode);
            
            ReadSimulatorOutput output = new ReadSimulatorOutput();
            StationController stationController = new StationController();
            try {
                Thread.sleep(5000); // 5000 milliseconds = 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (app3.running != false) {
            	try {
                    Thread.sleep(15000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				output.readOutput();
				ArrayList<Train> x = output.getTrains();
				System.out.println(x.get(trainNum).getPosition());
				
				ArrayList<Station> stationList = stationController.getStations();
				for(Station stations : stationList) {
					String previousStation = null;
					String nextStation = null;
					String nextStation1 = null;
					String nextStation2 = null;
					
					if (stations.getCode().equals(x.get(trainNum).getPosition())) {
						System.out.println("Train T"+ x.get(trainNum).getId()+ " is at " + stations.getName() + " moving " + x.get(trainNum).getDirection());
						String currentStation = stations.getName();
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
								}
					            if (station.getNumber().equals(nextStationNumberString1)) {
									 nextStation1 = station.getName();
								}
					            if (station.getNumber().equals(nextStationNumberString2)) {
									 nextStation2 = station.getName();
								}
					            gui.stationInfoPanel.updateTrainPosition(previousStation, currentStation, nextStation, nextStation1, nextStation2);
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
								}
					            if (station.getNumber().equals(nextStationNumberString1)) {
									 nextStation1 = station.getName();
								}
					            if (station.getNumber().equals(nextStationNumberString2)) {
									 nextStation2 = station.getName();
								}
					            gui.stationInfoPanel.updateTrainPosition(previousStation, currentStation, nextStation, nextStation1, nextStation2);
					        }
						}
						
						
					}
				}
			
				
				
				
			}
            
            
        }
        
        
    }


    }
        

        


