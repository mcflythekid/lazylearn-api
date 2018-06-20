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
  `archived` int(11) DEFAULT NULL,
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
  `archived` int(11) DEFAULT NULL,
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

CREATE TABLE `session` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `clientdata` longtext COLLATE utf8mb4_bin NOT NULL,
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
  UNIQUE KEY `user_UN` (`facebookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `user_authority` (
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `authority` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_authority_UN` (`authority`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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

create or replace view `detailed_deck` as
  select
      `d`.`id` AS `id`,
      `d`.`userid` AS `userid`,
      `d`.`name` AS `name`,
      `d`.`createddate` AS `createddate`,
      `d`.`updateddate` AS `updateddate`,
      `d`.`archived` AS `archived`,
      count( `c`.`id` ) AS `totalcard`,
      sum( if(( `c`.`wakeupon` < now()), 1, 0 )) AS `totaltimeupcard`
  from
      (
          `lazylearn_api`.`deck` `d`
      left join `lazylearn_api`.`card` `c` on
          (
              (
                  `d`.`id` = `c`.`deckid`
              )
          )
      )
  group by
      `d`.`id`,
      `d`.`userid`,
      `d`.`name`,
      `d`.`createddate`,
      `d`.`updateddate`,
      `d`.`archived`;

create or replace view `detailed_user` as
  select
      `u`.`id` AS `id`,
      `u`.`email` AS `email`,
      `u`.`ipaddress` AS `ipaddress`,
      `u`.`createddate` AS `createddate`,
      `u`.`updateddate` AS `updateddate`,
      `u`.`fullname` AS `fullname`,
      `u`.`facebookid` AS `facebookid`,
      count( distinct `c`.`id` ) AS `cards`,
      count( distinct `d`.`id` ) AS `decks`
  from
      (
          (
              `lazylearn_api`.`user` `u`
          left join `lazylearn_api`.`deck` `d` on
              (
                  (
                      `u`.`id` = `d`.`userid`
                  )
              )
          )
      left join `lazylearn_api`.`card` `c` on
          (
              (
                  `u`.`id` = `c`.`userid`
              )
          )
      )
  group by
      `u`.`id`,
      `u`.`email`,
      `u`.`ipaddress`,
      `u`.`createddate`,
      `u`.`updateddate`,
      `u`.`fullname`,
      `u`.`facebookid`;