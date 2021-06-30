create database test;
CREATE TABLE IF NOT EXISTS tenant_configs(
ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
TENANT_ID VARCHAR(255) NOT NULL,
URL VARCHAR(255) NOT NULL
);

INSERT INTO TENANT_CONFIGS(TENANT_ID,URL)
VALUES
('test1', 'r2dbc:pool:mysql://root:local@localhost:3306/test1'),
('test2', 'r2dbc:pool:mysql://root:local@localhost:3306/test2');

create database test1;
create database test2;


CREATE TABLE IF NOT EXISTS test1.CUSTOMERS(
ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
FIRST_NAME VARCHAR(255) NOT NULL,
LAST_NAME VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS test2.CUSTOMERS(
ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
FIRST_NAME VARCHAR(255) NOT NULL,
LAST_NAME VARCHAR(255) NOT NULL
);
