package ca.ucalgary.edu.ensf380.model;

public class WeatherData {
    private double temperature;
    private String conditions;

    public WeatherData(double temperature, String conditions) {
        this.temperature = temperature;
        this.conditions = conditions;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "Temperature: " + temperature + "°C, Conditions: " + conditions;
    }
}
