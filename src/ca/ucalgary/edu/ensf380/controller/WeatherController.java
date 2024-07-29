package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import ca.ucalgary.edu.ensf380.util.APIClient;

import java.io.IOException;

public class WeatherController {
    private WeatherData weatherData;

    public WeatherController() {
        updateWeatherData();
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void updateWeatherData() {
        try {
            this.weatherData = APIClient.fetchWeather();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
