// Purpose: To display weather data in the GUI.

// The "WeatherPanel" class is a GUI component that displays weather information. 
// It uses "WeatherController" to fetch and manage the weather data.

package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.controller.WeatherController;
import ca.ucalgary.edu.ensf380.model.WeatherData;

import javax.swing.*;
import java.awt.BorderLayout;

public class WeatherPanel extends JPanel {      // The class extends "JPanel", making it a Swing component that can be added to a GUI.
    private JLabel weatherLabel;    //  A label to display weather information.
    private WeatherController weatherController;    // A controller to manage weather data.

    public WeatherPanel() {
        setLayout(new BorderLayout());
        weatherLabel = new JLabel("Loading weather data...", SwingConstants.CENTER);
        add(weatherLabel, BorderLayout.CENTER);
        weatherController = new WeatherController();    // Initializes weatherController to manage weather data.
        displayWeather();
    }

    public void displayWeather() {
        new Thread(() -> {
            weatherController.retrieveWeather();    // Calls 'retrieveWeather()' on the "weatherController" class to update the weather data.
            WeatherData weatherData = weatherController.getWeatherData();
            SwingUtilities.invokeLater(() -> weatherLabel.setText(weatherData.toString()));     // Uses 'SwingUtilities.invokeLater' to update the 'weatherLabel' with the 
        }).start();                                                                             // fetched weather data on the Event Dispatch Thread (EDT) to ensure thread safety.
    }

    public WeatherData getWeatherData() {
        return weatherController.getWeatherData();
    }

    public void setWeatherData(WeatherData weatherData) {
        weatherController = new WeatherController();  // Reinitialize with new data
        displayWeather();
    }
}
