package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import ca.ucalgary.edu.ensf380.util.APIClient;
import ca.ucalgary.edu.ensf380.view.WeatherPanel;

import java.io.IOException;

public class WeatherController {
    private WeatherPanel weatherPanel;

    public WeatherController(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }

    public void retrieveWeather() {
        String url = "http://wttr.in/Calgary,CA?format=%25C+%25t";
        try {
            WeatherData weatherData = (WeatherData) APIClient.fetch(url);
            weatherPanel.setWeatherData(weatherData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
