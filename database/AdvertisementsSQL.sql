USE subway_screen;

CREATE TABLE  IF NOT EXISTS advertisements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    media_type ENUM('PDF', 'MPG', 'JPEG', 'BMP') NOT NULL,
    media_path VARCHAR(255) NOT NULL,
    display_duration INT
);

INSERT INTO advertisements (media_type, media_path, display_duration) VALUES
('MPG', 'advertisements/ad1.MP4', 10),
('MPG', 'advertisements/ad2.MP4', 10),
('MPG', 'advertisements/ad3.MP4', 10),
('MPG', 'advertisements/ad4.MP4', 10);
