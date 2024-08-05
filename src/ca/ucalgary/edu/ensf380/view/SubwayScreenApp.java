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
import ca.ucalgary.edu.ensf380.model.Train;

public class SubwayScreenApp {
	
    public static void main(String[] args) throws IOException { 
        if (args.length != 3) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <city> <country_code>");
        }else {
            String trainNumber = args[0];
            String city = args[1];
            String countryCode = args[2];
            
            int trainNum = Integer.parseInt(args[0]) - 1;
            
            MyApp3 app3 = new MyApp3();
            new SubwayScreenGUI(trainNumber, city, countryCode);
            ReadSimulatorOutput output = new ReadSimulatorOutput();
            try {
                Thread.sleep(5000); // 5000 milliseconds = 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (app3.running != false) {
				output.readOutput();
				ArrayList<Train> x = output.getTrains();
				System.out.println(x.get(trainNum).getPosition());
				
			}
            
            
        }
    }
    

    }
        

        


