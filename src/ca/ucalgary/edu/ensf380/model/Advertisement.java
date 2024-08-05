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

    public int getId() {	// Added to stay consistent with the Database
        return id;
    }

    public String getMediaType() {		// Used in "AdvertisementPanel.java" to determine the type of media to display.
        return mediaType;
    }

    public String getMediaPath() {		// Used in "AdvertisementPanel.java" to get the path of the media file.
        return mediaPath;
    }

    public int getDisplayDuration() {	// Added to stay consistent with the Database
        return displayDuration;
    }
}
