DROP DATABASE IF EXISTS challenge_devsu_account;
DROP USER IF EXISTS `user_devsu_account`@`%`;
CREATE DATABASE IF NOT EXISTS challenge_devsu_account CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `user_devsu_account`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `challenge_devsu_account`.* TO `user_devsu_account`@`%`;
FLUSH PRIVILEGES;

CREATE TABLE challenge_devsu_account.account (
id varchar(36) NOT NULL,
person_id varchar(36) NOT NULL,
account_type varchar(16) NOT NULL,
number_account int,
account_balance double,
created_date timestamp,
state varchar(16),
PRIMARY KEY (id),
FOREIGN KEY (person_id) REFERENCES challenge_devsu_person.person(id)
);

CREATE TABLE challenge_devsu_account.movement (
id varchar(36) NOT NULL,
account_id varchar(36) NOT NULL,
movement_type varchar(16) NOT NULL,
amount double,
balance double,
created_date timestamp,
PRIMARY KEY (id),
FOREIGN KEY (account_id) REFERENCES challenge_devsu_account.account(id)
);