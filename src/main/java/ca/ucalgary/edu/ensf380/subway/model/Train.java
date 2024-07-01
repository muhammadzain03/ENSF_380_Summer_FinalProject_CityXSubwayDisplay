package ca.ucalgary.edu.ensf380.subway.model;

public class Train {
    private String id;
    private String currentStation;
    private String direction;

    public Train(String id, String currentStation, String direction) {
        this.id = id;
        this.currentStation = currentStation;
        this.direction = direction;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCurrentStation() { return currentStation; }
    public void setCurrentStation(String currentStation) { this.currentStation = currentStation; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
}