ALTER TABLE deck ADD vocabdeckid varchar(100) NULL;
ALTER TABLE deck ADD vocabtype int NULL;
CREATE INDEX deck_vocabdeckid_idx USING BTREE ON deck (vocabdeckid) ;
CREATE INDEX deck_vocabtype_idx USING BTREE ON deck (vocabtype);

CREATE TABLE `vocab` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `word` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `phonetic` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `imagepath` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `audiopath` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `personalconnection` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `vocabdeckid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vocab_userid_idx` (`userid`) USING BTREE,
  KEY `vocab_vocabdeckid_idx` (`vocabdeckid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `vocabdeck` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `archived` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
