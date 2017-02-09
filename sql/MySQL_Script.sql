CREATE DATABASE `mariadb`;

CREATE TABLE `files_upload` (

	`file_id` int(11) NOT NULL AUTO_INCREMENT,
  
	`file_name` varchar(128) DEFAULT NULL,

	`file_data` longblob,

	PRIMARY KEY (`file_id`)
);

