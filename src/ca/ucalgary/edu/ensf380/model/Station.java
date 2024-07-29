package ca.ucalgary.edu.ensf380.model;

public class Station {
    private String name;
    private String code;
    private String coordinates;

    public Station(String name, String code, String coordinates) {
        this.name = name;
        this.code = code;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
