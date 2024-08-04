 package ca.ucalgary.edu.ensf380.view;

import java.io.IOException;

public class SubwayScreenApp {
    public static void main(String[] args) throws IOException { 
        if (args.length != 2) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <city>");
            return;
        }

        String trainNumber = args[0];
        String city = args[1];

        new SubwayScreenGUI(trainNumber, city);
    }

}