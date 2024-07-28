public class Train {
    private String position;
    private SubwayLine line;
    private String status;

    public Train(String position, SubwayLine line, String status) {
        this.position = position;
        this.line = line;
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public SubwayLine getLine() {
        return line;
    }

    public void setLine(SubwayLine line) {
        this.line = line;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
