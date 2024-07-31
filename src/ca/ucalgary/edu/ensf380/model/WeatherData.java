package ca.ucalgary.edu.ensf380.model;

public class WeatherData {
    private String temperature;
    private String conditions;

    public WeatherData(String temperature, String conditions) {
        this.temperature = temperature;
        this.conditions = conditions;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
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
        return "Temperature: " + temperature + ", Conditions: " + conditions;
    }
}
