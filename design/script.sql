CREATE TABLE `minpair` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `word1` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `word2` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `phonetic1` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `phonetic2` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `audiopath1` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `audiopath2` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `learnedcount` int(11) DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `minpair_userid_idx` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
