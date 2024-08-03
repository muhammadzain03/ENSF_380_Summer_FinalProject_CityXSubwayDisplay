package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class WeatherPanel extends JPanel {
    private JLabel weatherLabel;
    private JLabel timeLabel;

    public WeatherPanel() {
        weatherLabel = new JLabel("Loading weather data...");
        timeLabel = new JLabel("Loading time...",SwingConstants.CENTER);
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(200, 400));
        
        add(timeLabel);
        add(weatherLabel);
        
        // Update time every second
        new Timer(1000, e -> updateTime()).start();
        
    }
    
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText(now.format(formatter));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    public void setWeatherData(WeatherData weatherData) {
        String weatherInfo = String.format("Conditions: %s, Temperature: %s", weatherData.getConditions(), weatherData.getTemperature());
        weatherLabel.setText(weatherInfo);
    }
}
