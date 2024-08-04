package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.NewsArticle;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<NewsArticle> articles;
    private JLabel newsLabel;
    private Timer timer;


    public NewsPanel() throws IOException {
        this.articles = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 50));
        newsLabel = new JLabel("Loading news...", SwingConstants.CENTER);
        newsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(newsLabel, BorderLayout.CENTER);
        final String API_KEY = "c743a3ea8e1d418dbac5f997175c7290";
        
		URL url = new URL("https://newsapi.org/v2/top-headlines?country=" + "CA" + "&apiKey=" + API_KEY);
       HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
       connection.setRequestMethod("GET");
       
       if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
    	   StringBuilder str = new StringBuilder();
    	   Scanner scanner = new Scanner(connection.getInputStream());
    	   
    	   while(scanner.hasNext()) {
    		   str.append(scanner.nextLine());
    	   
       }
    	   scanner.close();
    	   
    	   String response = str.toString();
    	   Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
           Matcher matcher = pattern.matcher(response);
           
           // Print all titles
           if (matcher.find()) {
               System.out.println(matcher.group(1));
               newsLabel.setText(matcher.group(1));
           }
    	   
    	
       }else {
    	   System.out.println("Error in fetching GET");
       }
        
        timer = new Timer(100, e -> scrollNews());
        timer.start();
    }

    
    private void scrollNews() {
        String text = newsLabel.getText();
        newsLabel.setText(text.substring(1) + text.charAt(0));
    }


}
