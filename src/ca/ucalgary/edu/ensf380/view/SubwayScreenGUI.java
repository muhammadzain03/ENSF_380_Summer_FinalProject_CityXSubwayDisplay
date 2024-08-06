package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.model.Station;
import ca.ucalgary.edu.ensf380.controller.AdvertisementController;
import ca.ucalgary.edu.ensf380.controller.WeatherController;
import ca.ucalgary.edu.ensf380.controller.NewsController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SubwayScreenGUI {
	
	private AdvertisementPanel advertisementPanel; 
    private WeatherPanel weatherPanel; 
    private NewsPanel newsPanel;
    private StationInfoPanel stationInfoPanel;
    private MapPanel mapPanel;
   
    private AdvertisementController advertisementController;
    private WeatherController weatherController;
    private NewsController newsController;
    
    private Timer displayTimer;
    private JPanel adMapPanel;
    private CardLayout cardLayout;
    
    
    public SubwayScreenGUI(String trainNumber, String city, String countryCode, ArrayList<Station> stations) {
    	
    	this.advertisementPanel = new AdvertisementPanel();
    	this.weatherPanel = new WeatherPanel();
    	this.newsPanel = new NewsPanel();
    	this.stationInfoPanel = new StationInfoPanel();
    	this.mapPanel = new MapPanel(stations);
        this.cardLayout = new CardLayout();
        this.adMapPanel = new JPanel(cardLayout);
    	
    
    	this.advertisementController = new AdvertisementController(advertisementPanel);
    	this.weatherController = new WeatherController(weatherPanel);
    	weatherController.retrieveWeather(city);
    	this.newsController = new NewsController(newsPanel);
    	newsController.retrieveNews(countryCode);
    	
    	EventQueue.invokeLater(() -> {
    		JFrame mainframe = new JFrame("Subway Screen - " + trainNumber);
    		mainframe.setSize(1000, 600);
            mainframe.setLayout(new BorderLayout());
            mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            adMapPanel.add(advertisementPanel.getPanel(), "AdvertisementPanel");
            adMapPanel.add(mapPanel.getPanel(), "MapPanel");
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(adMapPanel, BorderLayout.CENTER);
            mainPanel.add(weatherPanel.getPanel(), BorderLayout.EAST);
            mainframe.getContentPane().add(BorderLayout.CENTER, mainPanel);
            
            JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
            bottomPanel.add(newsPanel.getPanel());    
            bottomPanel.add(stationInfoPanel.getPanel());
            mainframe.getContentPane().add(BorderLayout.SOUTH, bottomPanel);          
            
            mainframe.setVisible(true);
            startDisplayTimer();
            
    	});
    	
    }
    
    private void startDisplayTimer() {
        displayTimer = new Timer(10000, new ActionListener() {
            private boolean showingAd = true;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showingAd) {
                    cardLayout.show(adMapPanel, "MapPanel");
                    advertisementController.resumeAd();
                } else {
                    cardLayout.show(adMapPanel, "AdvertisementPanel");
                    advertisementController.pauseAd();
                }
                showingAd = !showingAd;
            }
        });
        displayTimer.start();
    }
    
    public StationInfoPanel getStationInfoPanel() {
    	return stationInfoPanel;
    }
}


