package ca.ucalgary.edu.ensf380.model;

public class Train {
    private String id;
    private String position;

    public Train(String id, String position) {
        this.id = id;
        this.position = position;
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

}
