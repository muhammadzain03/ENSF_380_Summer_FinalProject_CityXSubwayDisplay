package ca.ucalgary.edu.ensf380.view;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class NewsPanel{
    private JPanel panel;
    private JLabel newsLabel;
    private Timer timer;

    public NewsPanel() throws IOException {
    	this.panel = new JPanel(new BorderLayout());
    	panel.setPreferredSize(new Dimension(800, 50));
    	this.newsLabel = new JLabel("Loading news...", SwingConstants.CENTER);
    	newsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    	this.timer = new Timer(100, e -> scrollNews());
    	timer.start();
        //this.articles = new ArrayList<>();
        panel.add(newsLabel, BorderLayout.CENTER);

    }
    
    public JPanel getPanel() {
    	return panel;
    }

    
    private void scrollNews() {
        String text = newsLabel.getText();
        newsLabel.setText(text.substring(1) + text.charAt(0));
    }
    
    public void updateNewsLabel(String news) {
    	newsLabel.setText(news);
    }


}
