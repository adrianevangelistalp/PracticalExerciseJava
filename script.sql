CREATE DATABASE `test`; 

USE `test`;

-- test.account definition

CREATE TABLE `account` (
  `id` bigint NOT NULL,
  `customer_id` bigint NOT NULL,
  `balance` double NOT NULL,
  `state` bit(1) NOT NULL,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.customer definition

CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `state` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.native definition

CREATE TABLE `native` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.report definition

CREATE TABLE `report` (
  `id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  `detail` text,
  `from_date` datetime(6) DEFAULT NULL,
  `to_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- test.movement definition

CREATE TABLE `movement` (
  `id` bigint NOT NULL,
  `date` datetime(6) NOT NULL,
  `type` varchar(20) NOT NULL,
  `account_id` bigint DEFAULT NULL,
  `balance` double NOT NULL,  
  `amount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoemeananv9w9qnbcoccbl70a0` (`account_id`),
  CONSTRAINT `FKoemeananv9w9qnbcoccbl70a0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;