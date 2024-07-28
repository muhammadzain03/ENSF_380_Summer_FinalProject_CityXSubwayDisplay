package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.controller.WeatherController;
import ca.ucalgary.edu.ensf380.model.WeatherData;

import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {
    private JLabel weatherLabel;
    private WeatherController weatherController;

    public WeatherPanel(double latitude, double longitude) {
        setLayout(new BorderLayout());
        weatherLabel = new JLabel("Loading weather data...", SwingConstants.CENTER);
        add(weatherLabel, BorderLayout.CENTER);
        weatherController = new WeatherController(latitude, longitude);
        updateWeather();
    }

    public void updateWeather() {
        new Thread(() -> {
            weatherController.updateWeatherData();
            WeatherData weatherData = weatherController.getWeatherData();
            SwingUtilities.invokeLater(() -> weatherLabel.setText(weatherData.toString()));
        }).start();
    }
}
