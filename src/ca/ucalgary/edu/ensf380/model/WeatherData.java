package ca.ucalgary.edu.ensf380.model;

public class WeatherData {
    private String conditions;
    private String temperature;

    public WeatherData(String conditions, String temperature) {
        this.conditions = conditions;
        this.temperature = temperature;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Conditions: " + conditions + ", Temperature: " + temperature;
    }
}
