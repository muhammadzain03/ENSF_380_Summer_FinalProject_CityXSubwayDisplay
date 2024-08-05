package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.view.WeatherPanel;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class WeatherController extends DataFetcherController{
    private WeatherPanel weatherPanel;
    private static final String WEATHER_REGEEX = "^(.*?)\\s+(.*?)\\s+([+-]?\\d+°C)\\s+([←↔→↑↓↖↗↙↘]+\\d+km/h)\\s+(\\d+\\.\\d+mm)$";
    private static final Pattern WEATHER_PATTERN = Pattern.compile(WEATHER_REGEEX);

    public WeatherController(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }
    

    public void retrieveWeather(String city) {
        new Thread(() -> {
            try {
                String weatherData = fetchData("https://wttr.in/"+ city + "?format=%l+%C+%t+%w+%p");
                String[] parsedData = parseWeatherData(weatherData);
                if (parsedData != null) {
                	updateWeather(
                			parsedData[0], // Location
                            parsedData[1], // Condition
                            parsedData[2], // Temperature
                            parsedData[3], // Wind
                            parsedData[4]  // Precipitation
                            		);
                } else {
                   updateWeather(
                		   "Unknown", 
                           "Unknown", 
                           "Unknown", 
                           "Unknown", 
                           "Unknown"
                           );
                }
            } catch (Exception e) {
                e.printStackTrace();
                updateWeather(
                        "Error", 
                        "Error", 
                        "Error", 
                        "Error", 
                        "Error"
                        );
            }
        }).start();
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
    
    private void updateWeather(String location, String condition, String temperature, String wind, String precipitation) {
        String weatherInfo = String.format("<html>Location: %s<br>Condition: %s<br>Temperature: %s<br>Wind: %s<br>Precipitation: %s</html>", location, condition, temperature, wind, precipitation);
        weatherPanel.updateWeatherLabel(weatherInfo);
    }

}