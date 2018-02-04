-- [mysqld]
-- character-set-server = utf8mb4
-- collation-server = utf8mb4_unicode_ci

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` VARCHAR(100) PRIMARY KEY,
  `email` VARCHAR(100) UNIQUE,
  `hashedpassword` VARCHAR(100),
  `createdon` DATETIME,
  `updatedon` DATETIME,
  `registeripaddress` VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` VARCHAR(100) PRIMARY KEY,
  `userid` VARCHAR(100),
  `deckid` VARCHAR(100),
  `front` LONGTEXT,
  `back` LONGTEXT,
  `step` INT,
  `wakeupon` DATETIME,
  `createdon` DATETIME,
  `updatedon` DATETIME,
  `learnedon` DATETIME,
  INDEX(`userid`),
  INDEX(`deckid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `deck`;
CREATE TABLE `deck` (
  `id` VARCHAR(100) PRIMARY KEY,
  `userid` VARCHAR(100),
  `name` VARCHAR(100),
  `createdon` DATETIME,
  `updatedon` DATETIME,
  INDEX(`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `forgetpassword`;
CREATE TABLE `forgetpassword` (
  `id` VARCHAR(100) PRIMARY KEY,
  `userid` VARCHAR(100),
  `status` INT,
  `currentemail` VARCHAR(100),
  `ipaddress` VARCHAR(100),
  `createdon` DATETIME,
  `updatedon` DATETIME,
  `expiredon` DATETIME,
  INDEX(`userid`),
  INDEX(`currentemail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

