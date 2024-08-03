package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.controller.AdvertisementController;
import ca.ucalgary.edu.ensf380.controller.WeatherController;
import ca.ucalgary.edu.ensf380.controller.NewsController;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class SubwayScreenGUI extends JFrame {
    public SubwayScreenGUI(String trainNumber, String trainLine) {
        setTitle("Subway Screen - " + trainNumber + " on " + trainLine);
        setLayout(new BorderLayout());

        AdvertisementPanel advertisementPanel = new AdvertisementPanel();
        WeatherPanel weatherPanel = new WeatherPanel();
        NewsPanel newsPanel = new NewsPanel();
        TrainInfo trainInfoPanel = new TrainInfo();

        add(advertisementPanel, BorderLayout.CENTER);
        add(weatherPanel, BorderLayout.NORTH);
        add(newsPanel, BorderLayout.SOUTH);
        add(trainInfoPanel, BorderLayout.EAST);

        new AdvertisementController(advertisementPanel);
        new WeatherController(weatherPanel).retrieveWeather();
        new NewsController(newsPanel).fetchNews("train");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
