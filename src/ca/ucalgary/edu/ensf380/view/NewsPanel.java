package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.NewsArticle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewsPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;
    private List<NewsArticle> articles;
    private JLabel newsLabel;
    private int x;

    public NewsPanel() {
        this.articles = new ArrayList<>();
        setLayout(new BorderLayout());
        newsLabel = new JLabel("Loading news...");
        add(newsLabel, BorderLayout.CENTER);
        x = getWidth();
        new Thread(this).start();
    }

    public void updateNews(NewsArticle news) {
        articles.add(news);
        StringBuilder newsText = new StringBuilder();
        for (NewsArticle article : articles) {
            newsText.append(article.getTitle()).append(" (").append(article.getSource()).append(")   ");
        }
        newsLabel.setText(newsText.toString());
    }

    @Override
    public void run() {
        while (true) {
            try {
                SwingUtilities.invokeLater(() -> {
                    x -= 5;
                    if (x + newsLabel.getPreferredSize().width < 0) {
                        x = getWidth();
                    }
                    newsLabel.setLocation(x, newsLabel.getY());
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
