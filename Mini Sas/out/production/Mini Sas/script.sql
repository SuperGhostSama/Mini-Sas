CREATE DATABASE biblio;
USE biblio;

CREATE TABLE Author (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE LibraryUser (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     email VARCHAR(255) UNIQUE NOT NULL,
     phone VARCHAR(20) UNIQUE
);

CREATE TABLE Book (
      id INT AUTO_INCREMENT PRIMARY KEY,
      title VARCHAR(255) NOT NULL,
      author_id INT,
      isbn VARCHAR(20) NOT NULL,
      quantity INT NOT NULL,
      available INT NOT NULL,
      reserved INT NOT NULL,
      lost INT NOT NULL,
      FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE BorrowingRecord (
     id INT AUTO_INCREMENT PRIMARY KEY,
     book_id INT,
     libraryuser_id INT,
     borrowdate DATE,
     returndate DATE,
     last_updated TIMESTAMP,
     FOREIGN KEY (book_id) REFERENCES Book(id),
     FOREIGN KEY (libraryuser_id) REFERENCES LibraryUser(id)
);

DELIMITER //
CREATE TRIGGER update_timestamp
    BEFORE UPDATE ON BorrowingRecord
    FOR EACH ROW
BEGIN
    SET NEW.last_updated = NOW();
END ;
// DELIMITER ;

