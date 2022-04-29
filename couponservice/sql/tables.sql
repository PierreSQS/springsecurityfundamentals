DROP DATABASE IF EXISTS bharhatspringsecuritydb;
CREATE DATABASE IF NOT EXISTS bharhatspringsecuritydb ;
USE bharhatspringsecuritydb;

CREATE TABLE product(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
description varchar(100),
price decimal(8,3) 
);

CREATE TABLE coupon(
id int AUTO_INCREMENT PRIMARY KEY,
code varchar(20) UNIQUE,
discount decimal(8,3),
exp_date varchar(100) 
);