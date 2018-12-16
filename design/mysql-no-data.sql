-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: lazylearn_api
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `content` longtext COLLATE utf8mb4_bin,
  `url` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `shared` int(11) DEFAULT NULL,
  `category` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `vocabid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `minpairlanguage` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `articlecategory` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE,
  KEY `deckid` (`deckid`) USING BTREE,
  KEY `card_vocabid_idx` (`vocabid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `deck`
--

DROP TABLE IF EXISTS `deck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deck` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL,
  `archived` int(11) DEFAULT NULL,
  `vocabdeckid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `vocabtype` int(11) DEFAULT NULL,
  `trackingid` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shared` int(11) NOT NULL,
  `minpairlanguage` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `articlecategory` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `programid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `deck_un` (`vocabtype`,`vocabdeckid`),
  KEY `userid` (`userid`) USING BTREE,
  KEY `deck_vocabdeckid_idx` (`vocabdeckid`) USING BTREE,
  KEY `deck_vocabtype_idx` (`vocabtype`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `detailed_deck`
--

DROP TABLE IF EXISTS `detailed_deck`;
/*!50001 DROP VIEW IF EXISTS `detailed_deck`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `detailed_deck` AS SELECT 
 1 AS `id`,
 1 AS `userid`,
 1 AS `name`,
 1 AS `createddate`,
 1 AS `updateddate`,
 1 AS `archived`,
 1 AS `vocabdeckid`,
 1 AS `minpairlanguage`,
 1 AS `vocabdeckname`,
 1 AS `totalcard`,
 1 AS `totaltimeupcard`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `detailed_user`
--

DROP TABLE IF EXISTS `detailed_user`;
/*!50001 DROP VIEW IF EXISTS `detailed_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `detailed_user` AS SELECT 
 1 AS `id`,
 1 AS `email`,
 1 AS `ipaddress`,
 1 AS `createddate`,
 1 AS `updateddate`,
 1 AS `fullname`,
 1 AS `facebookid`,
 1 AS `cards`,
 1 AS `decks`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `minpair`
--

DROP TABLE IF EXISTS `minpair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `updateddate` datetime DEFAULT NULL,
  `language` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT 'english',
  PRIMARY KEY (`id`),
  KEY `minpair_userid_idx` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reset`
--

DROP TABLE IF EXISTS `reset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reset` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `expireddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `clientdata` longtext COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userid` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `step` (
  `programid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `step` int(11) NOT NULL,
  `days` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`programid`,`step`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stepprogram`
--

DROP TABLE IF EXISTS `stepprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stepprogram` (
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `userid` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `authority` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `id` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `createddate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_authority_UN` (`authority`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vocab`
--

DROP TABLE IF EXISTS `vocab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vocabdeck`
--

DROP TABLE IF EXISTS `vocabdeck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `detailed_deck`
--

/*!50001 DROP VIEW IF EXISTS `detailed_deck`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detailed_deck` AS select `d`.`id` AS `id`,`d`.`userid` AS `userid`,`d`.`name` AS `name`,`d`.`createddate` AS `createddate`,`d`.`updateddate` AS `updateddate`,`d`.`archived` AS `archived`,`d`.`vocabdeckid` AS `vocabdeckid`,`d`.`minpairlanguage` AS `minpairlanguage`,`vd`.`name` AS `vocabdeckname`,count(`c`.`id`) AS `totalcard`,sum(if((`c`.`wakeupon` < now()),1,0)) AS `totaltimeupcard` from ((`deck` `d` left join `card` `c` on((`d`.`id` = `c`.`deckid`))) left join `vocabdeck` `vd` on((`d`.`vocabdeckid` = `vd`.`id`))) group by `d`.`id`,`d`.`userid`,`d`.`name`,`d`.`createddate`,`d`.`updateddate`,`d`.`archived`,`d`.`vocabdeckid`,`vd`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `detailed_user`
--

/*!50001 DROP VIEW IF EXISTS `detailed_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detailed_user` AS select `u`.`id` AS `id`,`u`.`email` AS `email`,`u`.`ipaddress` AS `ipaddress`,`u`.`createddate` AS `createddate`,`u`.`updateddate` AS `updateddate`,`u`.`fullname` AS `fullname`,`u`.`facebookid` AS `facebookid`,count(distinct `c`.`id`) AS `cards`,count(distinct `d`.`id`) AS `decks` from ((`user` `u` left join `deck` `d` on((`u`.`id` = `d`.`userid`))) left join `card` `c` on((`u`.`id` = `c`.`userid`))) group by `u`.`id`,`u`.`email`,`u`.`ipaddress`,`u`.`createddate`,`u`.`updateddate`,`u`.`fullname`,`u`.`facebookid` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-16 15:34:49
