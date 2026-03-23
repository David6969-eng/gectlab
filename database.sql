CREATE DATABASE library_db;
USE library_db;

CREATE TABLE Book (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100)
);

CREATE TABLE Issue_Table (
    issue_id INT PRIMARY KEY,
    book_id INT,
    member_name VARCHAR(100),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

CREATE TABLE Member (
    member_id INT PRIMARY KEY,
    member_name VARCHAR(100),
    address VARCHAR(200),
    phone_no VARCHAR(15)
);
INSERT INTO Book VALUES
(1, 'Java Programming', 'James Gosling'),
(2, 'DBMS Concepts', 'Korth');

INSERT INTO Issue_Table VALUES
(101, 1, 'Arjun'),
(102, 2, 'Rahul');

INSERT INTO Member VALUES
(1, 'Arjun', 'Kerala', '9999999999'),
(2, 'Rahul', 'Delhi', '8888888888');
