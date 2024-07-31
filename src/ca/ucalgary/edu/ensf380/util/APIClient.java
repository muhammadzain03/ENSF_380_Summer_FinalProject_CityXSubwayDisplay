package ca.ucalgary.edu.ensf380.util;

import ca.ucalgary.edu.ensf380.model.WeatherData;

// Imported necessary classes for handling I/O, HTTP connections, and regular expressions.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIClient {

    // Static: Meaning, it can be called without creating an instance of "APIClient".
    public static Object fetch(String urlString) throws IOException, APIException {
        StringBuilder result = new StringBuilder();     // A StringBuilder named "result" is used to store the fetched HTML data.
        URL url = new URL(urlString);                   // A URL object is created with the provided URL string.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");           // An HttpURLConnection is opened to the URL, and the request method is set to GET.

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {    // A BufferedReader reads the input stream from the connection 
            String line;                                                                                    // line by line, appending each line to the result.
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }

        // Use regex to parse weather description from HTML                             // Two "Pattern" objects are created to match the temperature and conditions in the HTML.
        Pattern temperaturePattern = Pattern.compile("Temperature:\\s*(\\S+)");
        Pattern conditionsPattern = Pattern.compile("Conditions:\\s*(\\S+)");

        Matcher temperatureMatcher = temperaturePattern.matcher(result.toString());     // "Matcher" objects are created to find these patterns in the fetched HTML data.
        Matcher conditionsMatcher = conditionsPattern.matcher(result.toString());

        String temperature = "N/A";         // Default values for temperature and conditions are set to "N/A".
        String conditions = "N/A";

        if (temperatureMatcher.find()) {
            temperature = temperatureMatcher.group(1);
        }

        if (conditionsMatcher.find()) {
            conditions = conditionsMatcher.group(1);
        }

        if (temperature.equals("N/A") && conditions.equals("N/A")) {
            throw new APIException("Failed to fetch weather data");
        }

        return new WeatherData(temperature, conditions);
    }
}
