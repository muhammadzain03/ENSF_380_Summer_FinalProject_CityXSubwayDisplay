package ca.ucalgary.edu.ensf380.model;

public class Advertisement {
    private int id;
    private String mediaType;
    private String mediaPath;
    private int displayDuration;

    public Advertisement(int id, String mediaType, String mediaPath, int displayDuration) {
        this.id = id;
        this.mediaType = mediaType;
        this.mediaPath = mediaPath;
        this.displayDuration = displayDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public int getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(int displayDuration) {
        this.displayDuration = displayDuration;
    }
}
