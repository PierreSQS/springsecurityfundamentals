-- DROP DATABASE IF EXISTS bharhatspringsecuritydb;
-- CREATE DATABASE IF NOT EXISTS bharhatspringsecuritydb ;
USE bharhatspringsecuritydb;

CREATE TABLE user
(
    ID INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(20),
    LAST_NAME VARCHAR(20),
    EMAIL VARCHAR(20),
    PASSWORD VARCHAR(256),
    PRIMARY KEY (ID),
    UNIQUE KEY (EMAIL)
);

CREATE TABLE role
(
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(20),
    PRIMARY KEY (ID)
);

CREATE TABLE user_role(
    USER_ID int,
    ROLE_ID int,
    FOREIGN KEY (user_id)
    REFERENCES user(id),
    FOREIGN KEY (role_id)
    REFERENCES role(id)
);

INSERT INTO user(first_name,last_name,email,password) values ('doug','bailey','doug@bailey.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
INSERT INTO user(first_name,last_name,email,password) values ('john','ferguson','john@ferguson.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

INSERT INTO role values(1,'ROLE_ADMIN');
INSERT INTO role values(2,'ROLE_USER');

INSERT INTO user_role VALUES(1,1);
INSERT INTO user_role VALUES(2,2);

#SELECT * FROM user;
#SELECT * FROM role;
#SELECT * FROM user_role;