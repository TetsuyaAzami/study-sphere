DROP SCHEMA IF EXISTS study_sphere;

CREATE SCHEMA study_sphere;

USE study_sphere;

DROP TABLE IF EXISTS email_verifications;

CREATE TABLE email_verifications(
	email_verification_id INT AUTO_INCREMENT PRIMARY KEY,
	uuid VARCHAR(100) NOT NULL,
	email VARCHAR(254) NOT NULL,
	expiration_date DATETIME NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL
);

DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS roles_authorities;
DROP TABLE IF EXISTS roles;
-- DROP TABLE IF EXISTS authorities;

CREATE TABLE roles(
	role_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(100) NOT NULL,
	created_at DATETIME NOT NULL,
	updated_at DATETIME NOT NULL
);

-- CREATE TABLE authorities(
-- 	authority_id INT AUTO_INCREMENT PRIMARY KEY,
-- 	name VARCHAR(50) NOT NULL,
-- 	description VARCHAR(100) NOT NULL,
-- 	created_at DATETIME NOT NULL,
-- 	updated_at DATETIME NOT NULL
-- );

-- CREATE TABLE roles_authorities(
-- 	role_id INT NOT NULL,
-- 	authority_id INT NOT NULL,
-- 	PRIMARY KEY(role_id, authority_id),
-- 	FOREIGN KEY(role_id) REFERENCES roles(role_id),
-- 	FOREIGN KEY(authority_id) REFERENCES authorities(authority_id)
-- );

create table users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	password VARCHAR(128) NOT NULL,
	email VARCHAR(254) NOT NULL UNIQUE,
	role_id INT NOT NULL,
	FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

set foreign_key_checks = 0;
TRUNCATE roles;
set foreign_key_checks = 1;

INSERT INTO roles (name, description, created_at, updated_at)
		VALUES('general', '一般ユーザ', NOW(), NOW());
INSERT INTO roles (name, description, created_at, updated_at)
		VALUES('admin', '管理者ユーザ', NOW(), NOW());

INSERT INTO users(user_name, password, email, role_id) VALUES('test1', '$2a$08$7563MWncbW/oenHWfZSKE.jWeMARqdO/WZajbstas2xUamOBXDWz6', 'test1@example.com', 1);
