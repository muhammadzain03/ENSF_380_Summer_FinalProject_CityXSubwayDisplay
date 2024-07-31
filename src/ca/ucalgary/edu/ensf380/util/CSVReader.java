package ca.ucalgary.edu.ensf380.util;

import ca.ucalgary.edu.ensf380.model.Train;
import ca.ucalgary.edu.ensf380.model.SubwayLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static String getLatestFile(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".csv"));
        if (files == null || files.length == 0) {
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile.getPath();
    }

    public static List<Train> readTrains(String filePath) throws IOException {
        List<Train> trains = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String position = values[0];
                SubwayLine lineObj = new SubwayLine(values[1]); 
                String status = values[2];
                Train train = new Train(position, lineObj, status);
                trains.add(train);
            }
        }
        return trains;
    }
}
