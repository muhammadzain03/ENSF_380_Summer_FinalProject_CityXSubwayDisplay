DROP TABLE IF EXISTS advertisements;

CREATE DATABASE IF NOT EXISTS subway_screen;

USE subway_screen;

CREATE TABLE IF NOT EXISTS advertisements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    media_type ENUM('GIF', 'JPEG', 'BMP') NOT NULL,
    media_path VARCHAR(255) NOT NULL,
    display_duration INT
);

INSERT INTO advertisements (media_type, media_path, display_duration) VALUES
('GIF', 'ad1.gif', 10),
('GIF', 'ad2.gif', 10),
('JPEG', 'ad3.jpeg', 10),
('GIF', 'ad4.gif', 10);
