USE bharath_security_sect5_chap42_db;

-- DROP TABLE if exists USER_ROLE;

-- DROP TABLE if exists coupon;

-- DROP TABLE if exists USER;

-- DROP TABLE if exists ROLE;

CREATE TABLE COUPON(
    id       int AUTO_INCREMENT PRIMARY KEY,
    code     varchar(20) UNIQUE            ,
    discount decimal(8,3)                  ,
    exp_date varchar(100)
);

CREATE TABLE USER(
    ID         INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(20)                ,
    LAST_NAME  VARCHAR(20)                ,
    EMAIL      VARCHAR(20)                ,
    PASSWORD   VARCHAR(256)               ,
    PRIMARY KEY (ID)                      ,
    UNIQUE KEY (EMAIL)
);

CREATE TABLE ROLE(
    ID   INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(20)                ,
    PRIMARY KEY (ID)
);
        
CREATE TABLE USER_ROLE
(
    USER_ID int                              ,
    ROLE_ID int                              ,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);