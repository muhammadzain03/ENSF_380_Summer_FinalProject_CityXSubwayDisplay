package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.NewsArticle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<NewsArticle> articles;
    private JLabel newsLabel;
    private Timer timer;


    public NewsPanel() {
        this.articles = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 50));
        newsLabel = new JLabel("Loading news...asasaddsasdjha bsadas kjsdjk basjk sbadjh hjlsadl sabdhjas db sahjld", SwingConstants.CENTER);
        newsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(newsLabel, BorderLayout.CENTER);
        
        timer = new Timer(100, e -> scrollNews());
        timer.start();
    }

    public void updateNews(NewsArticle news) {
        articles.add(news);
        StringBuilder newsText = new StringBuilder();
        for (NewsArticle article : articles) {
            newsText.append(article.getTitle()).append(" (").append(article.getSource()).append(")   ");
        }
        newsLabel.setText(newsText.toString());
    }
    
    private void scrollNews() {
        String text = newsLabel.getText();
        newsLabel.setText(text.substring(1) + text.charAt(0));
    }

}
