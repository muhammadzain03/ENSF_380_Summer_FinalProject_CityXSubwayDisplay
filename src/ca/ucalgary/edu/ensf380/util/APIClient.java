package ca.ucalgary.edu.ensf380.util;


import ca.ucalgary.edu.ensf380.model.NewsArticle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class APIClient {

    public static Object fetch(String urlString) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }

        // Determine the type of response based on the URL
        if (urlString.contains("newsapi.org")) {
            return parseNews(result.toString());
        } else {
            return 0;//parseWeather(result.toString());
        }
    }

    private static List<NewsArticle> parseNews(String jsonResponse) {
        List<NewsArticle> articles = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray articlesArray = jsonObject.getJSONArray("articles");
        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject articleObject = articlesArray.getJSONObject(i);
            String title = articleObject.getString("title");
            String source = articleObject.getJSONObject("source").getString("name");
            String date = articleObject.getString("publishedAt");
            articles.add(new NewsArticle(title, source, date));
        }
        return articles;
    }

}
