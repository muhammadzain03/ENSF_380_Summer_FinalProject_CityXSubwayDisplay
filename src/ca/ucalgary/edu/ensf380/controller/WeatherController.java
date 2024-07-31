// To control the weather data retrieval and updating the weather panel if needed.

package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import ca.ucalgary.edu.ensf380.util.APIClient;
import ca.ucalgary.edu.ensf380.util.APIException;

import java.io.IOException;

public class WeatherController {
    private WeatherData weatherData;

    public WeatherController() {
        retrieveWeather();
    }

    // getWeatherData method returns the current weather data.
    public WeatherData getWeatherData() {
        return weatherData;
    }

    // retrieveWeather fetches weather data using "APIClient" and handles "IOException" and "APIException".
    public void retrieveWeather() {
        try {
            this.weatherData = (WeatherData) APIClient.fetch("http://wttr.in/Calgary,CA");
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
    }

    public void updateWeatherPanel() {
        // Logic to update the WeatherPanel if needed
    }
}
