package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.model.NewsArticle;
import ca.ucalgary.edu.ensf380.util.APIClient;
import ca.ucalgary.edu.ensf380.view.NewsPanel;

import java.io.IOException;
import java.util.List;

public class NewsController {
    private List<NewsArticle> articles;
    private NewsPanel newsPanel;

    public NewsController(NewsPanel newsPanel) {
        this.newsPanel = newsPanel;
    }

    @SuppressWarnings("unchecked")
    public void fetchNews(String keyword) {
        String url = "https://newsapi.org/v2/everything?q=" + keyword + "&from=2024-07-01&sortBy=publishedAt&apiKey=8c44841018c04d098950cd4805b29661";
        try {
            articles = (List<NewsArticle>) APIClient.fetch(url);
            updateNewsPanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateNewsPanel() {
        for (NewsArticle article : articles) {
            newsPanel.updateNews(article);
        }
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }
}
