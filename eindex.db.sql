BEGIN TRANSACTION;
DROP TABLE IF EXISTS `Student`;
CREATE TABLE IF NOT EXISTS `Student` (
	`person_id`	INTEGER,
	`indexID`	TEXT
);
INSERT INTO `Student` (person_id,indexID) VALUES (1,'1111');
DROP TABLE IF EXISTS `Professor`;
CREATE TABLE IF NOT EXISTS `Professor` (
	`person_id`	INTEGER,
	`title`	TEXT
);
INSERT INTO `Professor` (person_id,title) VALUES (2,'doc. dr.');
DROP TABLE IF EXISTS `Person`;
CREATE TABLE IF NOT EXISTS `Person` (
	`id`	INTEGER,
	`firstName`	TEXT,
	`lastName`	TEXT,
	`email`	TEXT,
	`address`	TEXT,
	`jmbg`	TEXT,
	PRIMARY KEY(`id`)
);
INSERT INTO `Person` (id,firstName,lastName,email,address,jmbg) VALUES (1,'Neko','Neko','neko@neko.com','Ulica 1','1234567890123'),
 (2,'Profesor','Profesor','profesor@profesor.ba','Ulica 2','9876543210123'),
 (3,'Emina','Hadzic','emina@ema.com','Jedna 2','1111111111111');
DROP TABLE IF EXISTS `Login`;
CREATE TABLE IF NOT EXISTS `Login` (
	`id`	INTEGER,
	`username`	TEXT,
	`password`	TEXT,
	`userType`	INTEGER,
	`person_id`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `Login` (id,username,password,userType,person_id) VALUES (1,'neko','neko',0,1),
 (2,'profesor','test',1,2),
 (3,'emina','emina',2,3);
DROP TABLE IF EXISTS `Grade`;
CREATE TABLE IF NOT EXISTS `Grade` (
	`id`	INTEGER,
	`student_id`	INTEGER,
	`course_id`	INTEGER,
	`date`	TEXT,
	`grade`	INTEGER,
	`points`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `Grade` (id,student_id,course_id,date,grade,points) VALUES (1,1,1,'24.2.2020',10,100);
DROP TABLE IF EXISTS `Course`;
CREATE TABLE IF NOT EXISTS `Course` (
	`id`	INTEGER,
	`code`	TEXT,
	`name`	TEXT,
	`credits`	INTEGER,
	`professor_id`	INTEGER,
	`semester`	INTEGER,
	PRIMARY KEY(`id`)
);
INSERT INTO `Course` (id,code,name,credits,professor_id,semester) VALUES (1,'RIO 1','RPR',5,2,3);
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE IF NOT EXISTS `Admin` (
	`person_id`	INTEGER
);
INSERT INTO `Admin` (person_id) VALUES (3);
COMMIT;
