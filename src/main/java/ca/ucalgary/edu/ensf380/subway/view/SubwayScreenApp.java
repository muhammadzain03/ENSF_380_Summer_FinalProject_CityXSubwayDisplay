package ca.ucalgary.edu.ensf380.subway.view;

import ca.ucalgary.edu.ensf380.subway.controller.SimulatorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SubwayScreenApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private SimulatorController simulatorController;
    private AdvertisementPanel adPanel;
    private WeatherPanel weatherPanel;
    private NewsPanel newsPanel;
    private TrainInfoPanel trainInfoPanel;

    public SubwayScreenApp() {
        setTitle("Subway Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridBagLayout());

        simulatorController = new SimulatorController();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                simulatorController.shutdown();
            }
        });

        initComponents();
        layoutComponents();

        simulatorController.startSimulator();
    }

    private void initComponents() {
        adPanel = new AdvertisementPanel();
        weatherPanel = new WeatherPanel();
        newsPanel = new NewsPanel();
        trainInfoPanel = new TrainInfoPanel();
    }

    private void layoutComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 0.75;
        gbc.weighty = 0.75;
        gbc.fill = GridBagConstraints.BOTH;
        add(adPanel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0.25;
        add(weatherPanel, gbc);

        gbc.gridy = 1;
        add(newsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.25;
        add(trainInfoPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SubwayScreenApp app = new SubwayScreenApp();
            app.setVisible(true);
        });
    }
}