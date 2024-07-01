package ca.ucalgary.edu.ensf380.subway.model;

public class Station {
    private String name;
    private String lineCode;

    public Station(String name, String lineCode) {
        this.name = name;
        this.lineCode = lineCode;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLineCode() { return lineCode; }
    public void setLineCode(String lineCode) { this.lineCode = lineCode; }
}