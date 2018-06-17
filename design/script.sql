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
--------------------------------------------------------------------------------------------------------------------
drop table `vocab`;
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
  PRIMARY KEY (`id`),
  KEY `vocab_userid_idx` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
--------------------------------------------------------------------------
ALTER TABLE lazylearn_api.deck ADD vocabid varchar(100) NULL;
ALTER TABLE lazylearn_api.deck ADD vocabtype int NULL;
CREATE INDEX deck_vocabid_idx USING BTREE ON lazylearn_api.deck (vocabid) ;
CREATE INDEX deck_vocabtype_idx USING BTREE ON lazylearn_api.deck (vocabtype) ;
