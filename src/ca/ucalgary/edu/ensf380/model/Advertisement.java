public class Advertisement {
    private String text;
    private String imagePath;
    private int displayDuration;

    public Advertisement(String text, String imagePath, int displayDuration) {
        this.text = text;
        this.imagePath = imagePath;
        this.displayDuration = displayDuration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(int displayDuration) {
        this.displayDuration = displayDuration;
    }
}
