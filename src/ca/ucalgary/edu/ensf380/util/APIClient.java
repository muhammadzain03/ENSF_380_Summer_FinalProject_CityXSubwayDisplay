package ca.ucalgary.edu.ensf380.util;

import ca.ucalgary.edu.ensf380.model.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIClient {
    private static final String API_KEY = "fd3f2c309dbd9b8ecf2e73c371f0a514"; // Your actual API key
    private static final String API_URL_TEMPLATE = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric";

    public static WeatherData fetchWeather(double latitude, double longitude) throws IOException {
        String apiUrl = String.format(API_URL_TEMPLATE, latitude, longitude, API_KEY);
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        scanner.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(inline);

        double temperature = root.path("main").path("temp").asDouble();
        String conditions = root.path("weather").get(0).path("description").asText();

        return new WeatherData(temperature, conditions);
    }
}
