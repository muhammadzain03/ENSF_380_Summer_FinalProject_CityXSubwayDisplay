package ca.ucalgary.edu.ensf380.model;

public class NewsArticle {
    private String title;
    private String source;
    private String date;

    public NewsArticle(String title, String source, String date) {
        this.title = title;
        this.source = source;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Source: " + source);
        System.out.println("Date: " + date);
    }
}
