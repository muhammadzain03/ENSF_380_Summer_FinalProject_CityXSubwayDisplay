package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import ca.ucalgary.edu.ensf380.util.WeatherFetcher;
import ca.ucalgary.edu.ensf380.util.WeatherParser;
import ca.ucalgary.edu.ensf380.view.WeatherPanel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;


public class WeatherController {
    private WeatherPanel weatherPanel;

    public WeatherController(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }

    public void retrieveWeather() {
        new Thread(() -> {
            try {
                String weatherData = WeatherFetcher.fetchWeatherData("https://wttr.in/Toronto?format=3");
                System.out.println("Raw Weather Data: " + weatherData); // Debugging line
                String[] parsedData = WeatherParser.parseWeatherData(weatherData);
                if (parsedData != null) {
                    SwingUtilities.invokeLater(() -> 
                        weatherPanel.updateWeather(parsedData[0], parsedData[1], parsedData[2])
                    );
                } else {
                    SwingUtilities.invokeLater(() -> 
                        weatherPanel.updateWeather("Unknown", "Unknown", "Unknown")
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> 
                    weatherPanel.updateWeather("Error", "Error", "Error")
                );
            }
        }).start();
    }
}
