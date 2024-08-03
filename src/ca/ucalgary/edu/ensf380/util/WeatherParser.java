package ca.ucalgary.edu.ensf380.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherParser {
    public static String[] parseWeatherData(String weatherData) {
        // Pattern to handle variations and spaces
        Pattern pattern = Pattern.compile("([A-Za-z\\s]+):\\s*([^\\s]+)\\s*([+-]?\\d+°C)");
        Matcher matcher = pattern.matcher(weatherData);
        if (matcher.find()) {
            String location = matcher.group(1).trim();
            String condition = matcher.group(2).trim();
            String temperature = matcher.group(3).trim();
            return new String[]{location, condition, temperature};
        }
        return new String[]{ "Unknown", "Unknown", "Unknown" };
    }
}
