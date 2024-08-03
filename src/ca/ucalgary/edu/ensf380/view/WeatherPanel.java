package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.WeatherData;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class WeatherPanel extends JPanel {
    private JLabel weatherLabel;
    private JLabel timeLabel;

    public WeatherPanel() {
        weatherLabel = new JLabel("Loading weather data...");
        timeLabel = new JLabel("Loading time...");
        setLayout(new GridLayout(2, 1));
        add(weatherLabel);
        add(timeLabel);

        // Update time every second
        Timer timer = new Timer(1000, e -> {
            timeLabel.setText("Time: " + java.time.LocalTime.now().toString());
        });
        timer.start();
    }

    public void setWeatherData(WeatherData weatherData) {
        String weatherInfo = String.format("Conditions: %s, Temperature: %s", weatherData.getConditions(), weatherData.getTemperature());
        weatherLabel.setText(weatherInfo);
    }
}
