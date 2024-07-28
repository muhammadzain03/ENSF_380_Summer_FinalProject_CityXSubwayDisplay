package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import ca.ucalgary.edu.ensf380.util.APIClient;

import java.io.IOException;

public class WeatherController {
    private WeatherData weatherData;
    private double latitude;
    private double longitude;

    public WeatherController(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        updateWeatherData();
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void updateWeatherData() {
        try {
            this.weatherData = APIClient.fetchWeather(latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
