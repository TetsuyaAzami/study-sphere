DROP SCHEMA IF EXISTS study_sphere;
CREATE SCHEMA study_sphere;
USE study_sphere;

DROP TABLE IF EXISTS `users`;

create table users (
	id VARCHAR(8) PRIMARY KEY,
	username VARCHAR(255),
	password VARCHAR(255),
	email VARCHAR(255),
	age INTEGER,
	enabled BOOLEAN,
	roles VARCHAR(255)
);

INSERT INTO users (id, username, password, email, age, enabled, roles) VALUES
    ('10000000', 'test1', '$2a$08$7563MWncbW/oenHWfZSKE.jWeMARqdO/WZajbstas2xUamOBXDWz6', 'test1@example.com', 30, true, 'ROLE_ADMIN,ROLE_GENERAL'),
    ('10000001', 'test2', '$2a$08$7563MWncbW/oenHWfZSKE.jWeMARqdO/WZajbstas2xUamOBXDWz6', 'test2@example.com', 16, true, 'ROLE_GENERAL'),
    ('10000002', 'test3', '$2a$08$7563MWncbW/oenHWfZSKE.jWeMARqdO/WZajbstas2xUamOBXDWz6', 'test3@example.com', 49, true, 'ROLE_ADMIN');
