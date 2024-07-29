package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.controller.AdvertisementController;
import ca.ucalgary.edu.ensf380.view.WeatherPanel;

import javax.swing.*;
import java.awt.BorderLayout;

public class SubwayScreenApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Subway Screen");
        frame.setLayout(new BorderLayout());

        AdvertisementPanel advertisementPanel = new AdvertisementPanel();
        WeatherPanel weatherPanel = new WeatherPanel();

        frame.add(advertisementPanel, BorderLayout.CENTER);
        frame.add(weatherPanel, BorderLayout.NORTH);

        AdvertisementController adController = new AdvertisementController(advertisementPanel);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
