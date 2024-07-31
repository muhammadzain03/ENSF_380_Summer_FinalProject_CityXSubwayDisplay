package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.Train;
import ca.ucalgary.edu.ensf380.util.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SimulatorController {
    private Process simulatorProcess;
    private final String simulatorCommand = "java -jar ./exe/SubwaySimulator.jar --in ./data/subway.csv --out ./out";
    private Timer timer;
    private List<Train> trains;

    public SimulatorController() {
        trains = new ArrayList<>();
    }

    public void simulate() throws IOException {
        if (simulatorProcess == null) {
            String[] command = simulatorCommand.split(" ");
            simulatorProcess = new ProcessBuilder(command).start();

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        trains = getTrainData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 15000); // Schedule to run every 15 seconds
        }
    }

    public List<Train> getTrainData() throws IOException {
        String latestFile = CSVReader.getLatestFile("./out");
        return CSVReader.readTrains(latestFile);
    }

    public void stopSimulation() {
        if (simulatorProcess != null) {
            simulatorProcess.destroy();
            simulatorProcess = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
