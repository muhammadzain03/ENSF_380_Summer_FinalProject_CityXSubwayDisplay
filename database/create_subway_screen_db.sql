USE subway_screen;

CREATE TABLE advertisements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    media_type ENUM('PDF', 'MPG', 'PNG', 'BMP') NOT NULL,
    media_path VARCHAR(255) NOT NULL,
    display_duration INT NOT NULL,  -- Duration in seconds
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO advertisements (media_type, media_path, display_duration) VALUES
('PNG', 'SubwayScreen\Map\Blue.png', 10),
('PNG', 'SubwayScreen\Map\Green.png', 10),
('PNG', 'SubwayScreen\Map\Red.png', 10),
('PNG', 'SubwayScreen\Map\Map.png', 10),
('PNG', 'SubwayScreen\Map\Trains.png', 10);

