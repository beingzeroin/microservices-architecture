DROP TABLE IF EXISTS USER_DATA;
 
CREATE TABLE USER_DATA (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO USER_DATA (username, password, email) values
	('auser','apass','aemail'),
	('buser','bpass','bemail'),
	('cuser','cpass','cemail')
