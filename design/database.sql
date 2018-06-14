CREATE TABLE `card` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `deckid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `front` longtext COLLATE utf8mb4_bin,
  `back` longtext COLLATE utf8mb4_bin,
  `step` int(11) DEFAULT NULL,
  `wakeupon` datetime DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `learnedon` datetime DEFAULT NULL,
  `archived` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE,
  KEY `deckid` (`deckid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `deck` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `hyperdeckid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `hyperdecktype` int(11) DEFAULT NULL,
  `archived` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `reset` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `expireddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `encodedpassword` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `ipaddress` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `sessionkey` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `facebookid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `fullname` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `facebookid` (`facebookid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user_authority` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `authority` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `authority_userid` (`authority`, `userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `session` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `clientdata` longtext COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
