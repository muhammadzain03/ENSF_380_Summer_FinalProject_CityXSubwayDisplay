package ca.ucalgary.edu.ensf380.view;


import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {
    private JLabel locationLabel;
    private JLabel conditionLabel;
    private JLabel temperatureLabel;

    public WeatherPanel() {
        setLayout(new GridLayout(3, 1));
        locationLabel = new JLabel("Location: ");
        conditionLabel = new JLabel("Condition: ");
        temperatureLabel = new JLabel("Temperature: ");
        
        add(locationLabel);
        add(conditionLabel);
        add(temperatureLabel);
    }

    public void updateWeather(String location, String condition, String temperature) {
        locationLabel.setText("Location: " + location);
        conditionLabel.setText("Condition: " + condition);
        temperatureLabel.setText("Temperature: " + temperature);
    }
}
