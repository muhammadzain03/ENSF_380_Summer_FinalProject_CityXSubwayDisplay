package ca.ucalgary.edu.ensf380.controller;

import ca.ucalgary.edu.ensf380.view.NewsPanel;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsController extends DataFetcherController {
	
	private NewsPanel newsPanel;
    private static final String API_KEY = "c743a3ea8e1d418dbac5f997175c7290";
    private static final String NEWS_REGEX = "\"title\":\"(.*?)\"";
    private static final Pattern NEWS_PATTERN = Pattern.compile(NEWS_REGEX);
    
    
    public NewsController(NewsPanel newsPanel) {
    	this.newsPanel = newsPanel;
    }
    
    public void retrieveNews (String countryCode) {
    	new Thread(() -> {
            try {
                String newsData = fetchData("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY);

                Matcher myMatcher = NEWS_PATTERN.matcher(newsData);
                
                StringBuilder allNews = new StringBuilder();
                while (myMatcher.find()) {
                    allNews.append(myMatcher.group(1).trim()).append(". ");
                }

                String news = allNews.toString();
                updateNews(news);
                
            } catch (IOException e) {
                e.printStackTrace();
                updateNews("Failed to fetch news");
            }
        }).start();
    }    
    
    private void updateNews(String news) {
    	newsPanel.updateNewsLabel(news);
    }
}


        
        
       
        
        
       