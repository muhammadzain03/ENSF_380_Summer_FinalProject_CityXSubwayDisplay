package ca.ucalgary.edu.ensf380.subway.model;

import java.util.ArrayList;
import java.util.List;

public class SubwayLine {
    private String name;
    private String color;
    private List<Station> stations;

    public SubwayLine(String name, String color) {
        this.name = name;
        this.color = color;
        this.stations = new ArrayList<>();
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public List<Station> getStations() { return stations; }
    public void addStation(Station station) { this.stations.add(station); }
}