CREATE DATABASE IF NOT EXISTS `scheduler` 
CREATE DATABASE IF NOT EXISTS `test_scheduler` 
USE `scheduler`;

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `scheduler` (
  `id` bigint(20) NOT NULL,
  `delivery_time` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;