import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SubwayScreenApp extends JFrame {
    private AdvertisementPanel adPanel;
    private NewsPanel newsPanel;
    private WeatherPanel weatherPanel;
    private TrainInfo trainInfoPanel;
    private AdvertisementController adController;
    private NewsController newsController;
    private WeatherController weatherController;
    private SimulatorController simulatorController;

    public SubwayScreenApp() {
        setTitle("Subway Screen App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeControllers();
        initializePanels();
        setupLayout();

        // Start background tasks
        startBackgroundTasks();
    }

    private void initializeControllers() {
        adController = new AdvertisementController();
        newsController = new NewsController();
        weatherController = new WeatherController();
        simulatorController = new SimulatorController();
    }

    private void initializePanels() {
        adPanel = new AdvertisementPanel();
        newsPanel = new NewsPanel();
        weatherPanel = new WeatherPanel();
        trainInfoPanel = new TrainInfo();
    }

    private void setupLayout() {
        // Main panel layout setup
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Advertisement Panel (largest section)
        mainPanel.add(adPanel, BorderLayout.CENTER);

        // Weather Panel (top right corner)
        JPanel topRightPanel = new JPanel(new BorderLayout());
        topRightPanel.add(weatherPanel, BorderLayout.NORTH);

        // News Panel (below weather panel)
        topRightPanel.add(newsPanel, BorderLayout.SOUTH);

        mainPanel.add(topRightPanel, BorderLayout.EAST);

        // Train Info Panel (bottom section)
        mainPanel.add(trainInfoPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void startBackgroundTasks() {
        // Run tasks in separate threads to keep UI responsive

        // Load advertisements
        new Thread(() -> {
            try {
                adController.loadAds();
                adPanel.cycleAdvertisements();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Fetch news
        new Thread(() -> {
            try {
                newsController.fetchNews();
                newsPanel.updateNews(/* pass news articles here */);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Retrieve weather information
        new Thread(() -> {
            try {
                weatherController.retrieveWeather();
                weatherPanel.updateWeather(/* pass weather data here */);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Simulate train positions
        new Thread(() -> {
            try {
                while (true) {
                    simulatorController.simulate();
                    trainInfoPanel.updateTrainPositions();
                    Thread.sleep(15000); // Update every 15 seconds
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SubwayScreenApp app = new SubwayScreenApp();
            app.setVisible(true);
        });
    }
}
