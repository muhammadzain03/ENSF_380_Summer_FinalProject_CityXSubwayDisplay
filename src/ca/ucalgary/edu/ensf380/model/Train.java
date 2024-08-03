package ca.ucalgary.edu.ensf380.model;

public class Train {
    private String id;
    private String position;
    private String status;

    public Train(String id, String position, String status) {
        this.id = id;
        this.position = position;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
