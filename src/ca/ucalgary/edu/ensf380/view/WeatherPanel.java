package ca.ucalgary.edu.ensf380.view;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherPanel extends JPanel {
    private JLabel weatherLabel;
    private JLabel timeLabel;

    public WeatherPanel() {
        weatherLabel = new JLabel("Loading weather data...");
        timeLabel = new JLabel("Loading time...", SwingConstants.CENTER);
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(200, 400));

        add(timeLabel);
        add(weatherLabel);

        new Timer(1000, e -> updateTime()).start();
    }

    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText(now.format(formatter));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    }

    public void updateWeather(String location, String condition, String temperature, String wind, String precipitation) {
        String weatherInfo = String.format("<html>Location: %s<br>Condition: %s<br>Temperature: %s<br>Wind: %s<<br>Precipitation: %s</html>", location, condition, temperature, wind, precipitation);
        weatherLabel.setText(weatherInfo);
    }
}

