package ca.ucalgary.edu.ensf380.subway.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulatorController {
    private Process simulatorProcess;
    private ExecutorService executor;

    public SimulatorController() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void startSimulator() {
        try {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "./Simulator/SubwaySimulator.jar",
                    "--in", "./Simulator/data/subway.csv", "--out", "./Simulator/out");
            builder.redirectErrorStream(true);
            simulatorProcess = builder.start();

            executor.submit(this::readSimulatorOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSimulatorOutput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(simulatorProcess.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Simulator: " + line);
                // TODO: Process simulator output
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSimulator() {
        if (simulatorProcess != null) {
            simulatorProcess.destroy();
            simulatorProcess = null;
        }
    }

    public void shutdown() {
        stopSimulator();
        executor.shutdown();
    }
}