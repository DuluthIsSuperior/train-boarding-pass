CREATE DATABASE train_boarding_pass;
USE train_boarding_pass;

DROP TABLE schedule;
CREATE TABLE schedule(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(20) NOT NULL,
    departure DATETIME NOT NULL,
    price DECIMAL(5, 2) NOT NULL # 3 digits before the decimal, 2 after
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO schedule(destination, departure, price) VALUES
("Chicago", "2021-11-05 15:00:00", 90.99), ("Detroit", "2021-11-05 12:00:00", 40.40), ("Atlanta", "2021-11-05 08:00:00", 210.12),
("Grand Rapids", "2021-11-05 12:30:00", 15.00), ("San Francisco", "2021-11-05 02:15:00", 315.67);