CREATE DATABASE IF NOT EXISTS train_boarding_pass;
USE train_boarding_pass;
SET GLOBAL time_zone = "-05:00";

DROP TABLE boarding_pass;
DROP TABLE schedule;
CREATE TABLE IF NOT EXISTS schedule(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(20) NOT NULL,
    destination VARCHAR(20) NOT NULL,
    departure VARCHAR(19) NOT NULL,
    distance DECIMAL(5, 2) NOT NULL,
    price DECIMAL(5, 2) NOT NULL # 3 digits before the decimal, 2 after
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO schedule(origin, destination, departure, distance, price) VALUES
("Conklin", "Chicago", "2020-11-05 15:00:00", 187.99, 90.99),  ("Conklin", "Detroit", "2020-11-05 12:00:00", 184.00, 40.40),
("Conklin", "Atlanta", "2020-11-05 08:00:00", 800.12, 210.12), ("Conklin", "Grand Rapids", "2020-11-05 12:30:00", 28.91, 15.00),
("Conklin", "Chattanooga", "2020-11-05 02:15:00", 685.1, 315.67),
("Conklin", "Chicago", "2020-11-06 15:00:00", 187.99, 85.99),  ("Conklin", "Detroit", "2020-11-06 12:00:00", 184.00, 35.40),
("Conklin", "Atlanta", "2020-11-06 08:00:00", 800.12, 205.12), ("Conklin", "Grand Rapids", "2020-11-06 12:30:00", 28.91, 10.00),
("Conklin", "Chattanooga", "2020-11-06 02:15:00", 685.1, 310.67),
("Conklin", "Chicago", "2020-11-07 15:00:00", 187.99, 80.99),  ("Conklin", "Detroit", "2020-11-07 12:00:00", 184.00, 30.40),
("Conklin", "Atlanta", "2020-11-07 08:00:00", 800.12, 200.12), ("Conklin", "Grand Rapids", "2020-11-07 12:30:00", 28.91, 5.00),
("Conklin", "Chattanooga", "2020-11-07 02:15:00", 685.1, 305.67);

CREATE TABLE IF NOT EXISTS boarding_pass (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  train_id INT NOT NULL,
  name varchar(45) DEFAULT NULL,
  eta varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  phone varchar(45) DEFAULT NULL,
  gender varchar(45) DEFAULT NULL,
  age int DEFAULT NULL,
  ticket_price float DEFAULT NULL,
  FOREIGN KEY (train_id) REFERENCES schedule(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;