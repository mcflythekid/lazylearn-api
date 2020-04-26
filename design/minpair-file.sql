CREATE TABLE `minpair_file` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `minpair` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `audiopath` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `side` int(11) default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;