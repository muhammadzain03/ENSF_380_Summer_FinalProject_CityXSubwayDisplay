package ca.ucalgary.edu.ensf380.view;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherPanel {
	private JPanel panel;
    private JLabel weatherLabel;
    private JLabel timeLabel;
    private Timer timer;

    public WeatherPanel() {
    	this.panel = new JPanel(new GridLayout(2, 1));
    	panel.setPreferredSize(new Dimension(200, 400));   
    	this.timeLabel = new JLabel("Loading time...", SwingConstants.CENTER);
    	this.weatherLabel = new JLabel("Loading weather data...");
    	this.timer = new Timer(1000, e -> updateTime());  
    	timer.start();
        panel.add(timeLabel);
        panel.add(weatherLabel);
    }
    
    public JPanel getPanel() {
        return panel;  
    }
    
    
    private void updateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText(now.format(formatter));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    }

    public void updateWeather(String location, String condition, String temperature, String wind, String precipitation) {
        String weatherInfo = String.format("<html>Location: %s<br>Condition: %s<br>Temperature: %s<br>Wind: %s<br>Precipitation: %s</html>", location, condition, temperature, wind, precipitation);
        weatherLabel.setText(weatherInfo);
    }
}