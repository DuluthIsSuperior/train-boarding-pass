CREATE DATABASE IF NOT EXISTS train_boarding_pass;
USE train_boarding_pass;

CREATE TABLE IF NOT EXISTS schedule(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(20) NOT NULL,
    departure DATETIME NOT NULL,
    price DECIMAL(5, 2) NOT NULL # 3 digits before the decimal, 2 after
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO schedule(destination, departure, price) VALUES
("Chicago", "2020-11-05 15:00:00", 90.99), ("Detroit", "2020-11-05 12:00:00", 40.40), ("Atlanta", "2020-11-05 08:00:00", 210.12),
("Grand Rapids", "2020-11-05 12:30:00", 15.00), ("San Francisco", "2020-11-05 02:15:00", 315.67),
("Chicago", "2020-11-06 15:00:00", 85.99), ("Detroit", "2020-11-06 12:00:00", 35.40), ("Atlanta", "2020-11-06 08:00:00", 205.12),
("Grand Rapids", "2020-11-06 12:30:00", 10.00), ("San Francisco", "2020-11-06 02:15:00", 310.67),
("Chicago", "2020-11-07 15:00:00", 80.99), ("Detroit", "2020-11-07 12:00:00", 30.40), ("Atlanta", "2020-11-07 08:00:00", 200.12),
("Grand Rapids", "2020-11-07 12:30:00", 5.00), ("San Francisco", "2020-11-07 02:15:00", 305.67);