package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Train;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial")
public class TrainInfo extends JPanel {
    private JTextArea trainInfoArea;

    public TrainInfo() {
        setLayout(new BorderLayout());
        trainInfoArea = new JTextArea();
        trainInfoArea.setEditable(false);
        add(new JScrollPane(trainInfoArea), BorderLayout.CENTER);
    }

    public void updateTrainPositions(List<Train> trains) {
        StringBuilder trainInfoText = new StringBuilder();
        for (Train train : trains) {
            trainInfoText.append(String.format("Train %s: %s, %s\n", train.getId(), train.getPosition(), train.getStatus()));
        }
        trainInfoArea.setText(trainInfoText.toString());
    }
}
