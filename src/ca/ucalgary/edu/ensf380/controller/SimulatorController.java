package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.Train;
import ca.ucalgary.edu.ensf380.view.TrainInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SimulatorController {
    private TrainInfo trainInfoPanel;
    private List<Train> trains;
    private Process process;
    private Timer timer;

    public SimulatorController(TrainInfo trainInfoPanel) {
        this.trainInfoPanel = trainInfoPanel;
        this.trains = new ArrayList<>();
        startSimulator();
    }

    private void startSimulator() {
        try {
            String[] command = {"java", "-jar", "./exe/SubwaySimulator.jar", "--in", "./data/subway.csv", "--out", "./out"};
            process = new ProcessBuilder(command).start();
            startReadingOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (process != null) {
                process.destroy();
            }
        }));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTrainPositions();
            }
        }, 0, 15000); // Update train positions every 15 seconds
    }

    private void startReadingOutput() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    parseTrainData(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void parseTrainData(String data) {
        trains.clear(); // Clear previous data
        String[] trainData = data.split(", ");
        for (String trainInfo : trainData) {
            String[] parts = trainInfo.split("\\(");
            String trainId = parts[0];
            String position = parts[1].split(",")[0];
            String status = parts[1].split(",")[1].replace(")", "").trim();
            Train train = new Train(trainId, position, status);
            trains.add(train);
        }
        trainInfoPanel.updateTrainPositions(trains);
    }

    private void updateTrainPositions() {
        trainInfoPanel.updateTrainPositions(trains);
    }

    public void stopSimulator() {
        if (process != null) {
            process.destroy();
        }
        if (timer != null) {
            timer.cancel();
        }
    }
}
