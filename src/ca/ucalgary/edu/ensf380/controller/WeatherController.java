package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.view.WeatherPanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class WeatherController {
    private WeatherPanel weatherPanel;
    private static final String WEATHER_REGEEX = "^(.*?)\\s+(.*?)\\s+([+-]?\\d+°C)\\s+([←↔→↑↓↖↗↙↘]+\\d+km/h)\\s+(\\d+\\.\\d+mm)$";
    private static final Pattern WEATHER_PATTERN = Pattern.compile(WEATHER_REGEEX);

    public WeatherController(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }

    public void retrieveWeather(String city) {
        new Thread(() -> {
            try {
                String weatherData = fetchWeatherData("https://wttr.in/"+ city + "?format=%l+%C+%t+%w+%p");
                String[] parsedData = parseWeatherData(weatherData);
                if (parsedData != null) {
                    EventQueue.invokeLater(() -> {
                        weatherPanel.updateWeather(
                            parsedData[0], // Location
                            parsedData[1], // Condition
                            parsedData[2], // Temperature
                            parsedData[3], // Wind
                            parsedData[4]  // Precipitation
                        );
                    });
                } else {
                    EventQueue.invokeLater(() -> {
                        weatherPanel.updateWeather(
                            "Unknown", 
                            "Unknown", 
                            "Unknown", 
                            "Unknown", 
                            "Unknown"
                        );
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                EventQueue.invokeLater(() -> {
                    weatherPanel.updateWeather(
                        "Error", 
                        "Error", 
                        "Error", 
                        "Error", 
                        "Error"
                    );
                });
            }
        }).start();
    }
    
    private String fetchWeatherData(String urlString) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setInstanceFollowRedirects(true); 
        conn.setRequestMethod("GET");


        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
            String newLocation = conn.getHeaderField("Location");
            URL newUrl = new URL(newLocation);
            conn = (HttpURLConnection) newUrl.openConnection();
            conn.setRequestMethod("GET");
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line).append("\n");
        }
        rd.close();
        return result.toString();
    }

    private String[] parseWeatherData(String weatherData) {
    	String trimmedData = weatherData.trim();

        Matcher myMatcher = WEATHER_PATTERN.matcher(trimmedData);

        if (myMatcher.find()) {
  
            String location = myMatcher.group(1).trim();
            String condition = myMatcher.group(2).trim();
            String temperature = myMatcher.group(3).trim();
            String wind = myMatcher.group(4).trim();
            String precipitation = myMatcher.group(5).trim();

            return new String[]{location, condition, temperature, wind, precipitation};
        }

        return new String[]{"Unknown", "Unknown", "Unknown", "Unknown", "Unknown"};
    }
}