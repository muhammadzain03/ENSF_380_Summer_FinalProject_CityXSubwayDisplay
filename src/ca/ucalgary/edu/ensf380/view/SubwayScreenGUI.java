package ca.ucalgary.edu.ensf380.view;

import ca.ucalgary.edu.ensf380.controller.AdvertisementController;
import ca.ucalgary.edu.ensf380.controller.WeatherController;
import ca.ucalgary.edu.ensf380.controller.NewsController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class SubwayScreenGUI {
	
	private AdvertisementPanel advertisementPanel; 
    private WeatherPanel weatherPanel; 
    private NewsPanel newsPanel;
    private StationInfoPanel stationInfoPanel;
    private MapPanel mapPanel;
   
    private AdvertisementController advertisementController;
    private WeatherController weatherController;
    private NewsController newsController;
    
    
    public SubwayScreenGUI(String trainNumber, String city, String countryCode) {
    	
    	this.advertisementPanel = new AdvertisementPanel();
    	this.weatherPanel = new WeatherPanel();
    	this.newsPanel = new NewsPanel();
    	this.stationInfoPanel = new StationInfoPanel();
    	this.mapPanel = new MapPanel();
    	
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
            
            JPanel adMapPanel = new JPanel(new CardLayout());
            adMapPanel.add(advertisementPanel.getPanel());
            adMapPanel.add(mapPanel.getPanel());
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(adMapPanel, BorderLayout.CENTER);
            mainPanel.add(weatherPanel.getPanel(), BorderLayout.EAST);
            mainframe.getContentPane().add(BorderLayout.CENTER, mainPanel);
            
            JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
            bottomPanel.add(newsPanel.getPanel());    
            bottomPanel.add(stationInfoPanel.getPanel());
            mainframe.getContentPane().add(BorderLayout.SOUTH, bottomPanel);          
            
            mainframe.setVisible(true);
    	});
    }
    
    public StationInfoPanel getStationInfoPanel() {
    	return stationInfoPanel;
    }
}

