package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Train;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class TrainInfo extends JPanel {
    private JTextArea trainInfoArea;

    public TrainInfo() {
    	setLayout(new GridLayout(1, 5));
    	setPreferredSize(new Dimension(800, 100));
        trainInfoArea = new JTextArea();
        trainInfoArea.setEditable(false);
        for (int i = 0; i < 5; i++) {
            add(new JLabel("Next stations: Fetching..."));
        }
    }

    public void updateTrainPositions(List<Train> trains) {
    	trainInfoArea.setText("");
        StringBuilder trainInfoText = new StringBuilder();
        for (Train train : trains) {
            trainInfoText.append(String.format("Train %s: %s\n", train.getId(), train.getPosition()));
        }
        trainInfoArea.setText(trainInfoText.toString());
        System.out.println(trainInfoText);
    }
}
