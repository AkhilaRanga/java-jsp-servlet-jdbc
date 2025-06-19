CREATE DATABASE java_web;
USE java_web;
CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    job ENUM('DEVELOPER', 'MANAGER', 'DIRECTOR'),
    age INT CHECK (age BETWEEN 18 AND 80)
);