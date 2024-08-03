package ca.ucalgary.edu.ensf380.view;

public class SubwayScreenApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SubwayScreenApp <train_number> <train_line>");
            return;
        }

        String trainNumber = args[0];
        String trainLine = args[1];

        new SubwayScreenGUI(trainNumber, trainLine);
    }
}
