-- MySQL dump 10.13  Distrib 5.7.21, for osx10.12 (x86_64)
--
-- Host: localhost    Database: ultimate_bet
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `bet`
--

DROP TABLE IF EXISTS `bet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bet` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `course` decimal(19,2) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `game_id` bigint(20) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl1as9g4a4n5j4l6csb664hqrl` (`game_id`),
  KEY `FKqpbhjmlktc3yd1fwuiket9hgd` (`team_id`),
  KEY `FKq5rv2e6soot2iosewr8r4i235` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bet`
--

LOCK TABLES `bet` WRITE;
/*!40000 ALTER TABLE `bet` DISABLE KEYS */;
INSERT INTO `bet` VALUES (25,100.00,1.87,'2018-06-06 12:34:01','won',187.00,20,7,53),(27,50.00,9.35,'2018-06-06 12:34:27','lost',467.50,19,15,53),(48,1000.00,2.75,'2018-06-06 12:36:28','lost',2750.00,39,7,53);
/*!40000 ALTER TABLE `bet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` bigint(20) NOT NULL,
  `api_id` bigint(20) DEFAULT NULL,
  `course_for_team1` double NOT NULL,
  `course_for_team2` double NOT NULL,
  `game_time` datetime DEFAULT NULL,
  `odds_for_team1` double NOT NULL,
  `odds_for_team2` double NOT NULL,
  `points_team1` int(11) NOT NULL,
  `points_team2` int(11) NOT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `team1_id` bigint(20) DEFAULT NULL,
  `team2_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjnhlg2dqkrjvaai3e70ymw339` (`team1_id`),
  KEY `FKehwjicpbm71ks5yyuo7co77qj` (`team2_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (17,21,1.71,3.07,'2018-09-17 08:00:00',0.6415094339622641,0.3584905660377358,5,10,NULL,1,1,16),(18,22,2.17,2.23,'2018-09-17 09:00:00',0.5074626865671642,0.4925373134328358,1,6,NULL,1,8,9),(19,23,1.78,2.88,'2018-09-17 08:00:00',0.6181818181818182,0.38181818181818183,4,6,NULL,1,2,15),(20,24,1.87,2.67,'2018-09-17 09:00:00',0.5882352941176471,0.4117647058823529,11,2,NULL,1,7,10),(21,25,1.84,2.73,'2018-09-17 08:00:00',0.5964912280701754,0.40350877192982454,0,5,NULL,1,3,14),(22,26,2.04,2.39,'2018-09-17 09:00:00',0.5396825396825397,0.4603174603174603,5,14,NULL,1,6,11),(23,27,1.44,4.68,'2018-09-17 08:00:00',0.7647058823529411,0.23529411764705882,14,0,NULL,1,4,13),(24,28,1.97,2.49,'2018-09-17 09:00:00',0.5573770491803278,0.4426229508196721,0,2,NULL,1,5,12),(29,29,2.19,2.21,'2018-09-17 10:00:00',0.50187265917603,0.49812734082397003,1,14,NULL,1,1,9),(30,30,1.71,3.07,'2018-09-17 11:00:00',0.6415094339622641,0.3584905660377358,8,7,NULL,1,8,16),(31,31,2.15,2.26,'2018-09-17 10:00:00',0.512396694214876,0.48760330578512395,11,12,NULL,1,2,10),(32,32,2.03,2.4,'2018-09-17 11:00:00',0.5422222222222223,0.4577777777777778,2,9,NULL,1,7,15),(33,33,2.04,2.39,'2018-09-17 10:00:00',0.5396825396825397,0.4603174603174603,8,3,NULL,1,3,11),(34,34,1.84,2.73,'2018-09-17 11:00:00',0.5964912280701754,0.40350877192982454,14,4,NULL,1,6,14),(35,35,1.67,3.22,'2018-09-17 10:00:00',0.6582278481012658,0.34177215189873417,13,12,NULL,1,4,12),(36,36,2,2.44,'2018-09-17 11:00:00',0.5494505494505495,0.45054945054945056,7,13,NULL,1,5,13),(37,37,2.2,2.2,'2018-09-17 12:00:00',0.49907578558225507,0.5009242144177449,5,3,NULL,1,1,8),(38,38,1.73,3.03,'2018-09-17 13:00:00',0.6363636363636364,0.36363636363636365,7,3,NULL,1,9,16),(39,39,2.2,2.2,'2018-09-17 12:00:00',0.5010183299389002,0.4989816700610998,8,12,NULL,1,2,7),(40,40,2.16,2.24,'2018-09-17 13:00:00',0.5086306098964327,0.49136939010356734,5,10,NULL,1,10,15),(41,41,2.2,2.2,'2018-09-17 12:00:00',0.5,0.5,11,1,NULL,1,3,6),(42,42,2.14,2.27,'2018-09-17 13:00:00',0.5148514851485149,0.48514851485148514,1,5,NULL,1,11,14),(43,43,2.01,2.43,'2018-09-17 12:00:00',0.5480427046263345,0.45195729537366547,4,13,NULL,1,4,5),(44,44,1.94,2.55,'2018-09-17 13:00:00',0.5684210526315789,0.43157894736842106,11,6,NULL,1,12,13),(54,45,1.72,3.05,'2018-09-17 08:00:00',0.6398104265402843,0.36018957345971564,9,1,NULL,1,1,16),(55,46,2.18,2.22,'2018-09-17 09:00:00',0.5046554934823091,0.49534450651769085,4,3,NULL,1,8,9),(56,47,2.05,2.37,'2018-09-17 08:00:00',0.5353645266594124,0.4646354733405876,3,1,NULL,1,2,15),(57,48,2.09,2.32,'2018-09-17 09:00:00',0.5257510729613734,0.4742489270386266,5,3,NULL,1,7,10),(58,49,1.89,2.63,'2018-09-17 08:00:00',0.5811965811965812,0.4188034188034188,14,0,NULL,1,3,14),(59,50,2.13,2.28,'2018-09-17 09:00:00',0.5172413793103449,0.4827586206896552,1,9,NULL,1,6,11),(60,51,1.69,3.17,'2018-09-17 08:00:00',0.652542372881356,0.3474576271186441,8,5,NULL,1,4,13),(61,52,2.04,2.39,'2018-09-17 09:00:00',0.5404255319148936,0.4595744680851064,14,8,NULL,1,5,12);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `api_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (50,1,'A'),(51,2,'B'),(52,3,'C'),(53,4,'D');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (62),(62),(62),(62),(62),(62),(62),(62);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation` (
  `operation_id` int(11) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `wallet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`operation_id`),
  KEY `FKlwsydthsda9ce4pf6j9q476v6` (`wallet_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (34,10.00,'2018-06-05 23:47:29','placed a bet',54),(43,37.40,'2018-06-05 23:48:10','won bet nr33',54),(44,20.00,'2018-06-05 23:50:17','add money',54),(54,10.00,'2018-06-05 23:52:05','placed a bet',54),(55,10.00,'2018-06-05 23:53:01','placed a bet',54),(56,17.80,'2018-06-05 23:53:30','won bet nr53',54),(45,200.00,'2018-06-06 06:48:19','add money',54),(46,200.00,'2018-06-06 06:48:23','add money',54),(48,10.00,'2018-06-06 06:48:52','placed a bet',54),(50,10.00,'2018-06-06 06:49:25','placed a bet',54),(52,10.00,'2018-06-06 06:49:51','placed a bet',54),(53,18.70,'2018-06-06 06:52:17','won bet nr 47',54),(57,13.20,'2018-06-06 06:52:42','won bet nr 49',54),(59,10.00,'2018-06-06 06:52:43','placed a bet',54),(60,19.40,'2018-06-06 06:52:47','won bet nr 58',54),(70,10.00,'2018-06-06 06:58:57','placed a bet',54),(71,19.20,'2018-06-06 07:05:37','won bet nr 69',54),(73,10.00,'2018-06-06 07:18:00','placed a bet',54),(74,17.20,'2018-06-06 07:18:03','won bet nr 72',54),(76,100.00,'2018-06-06 07:18:27','placed a bet',54),(77,172.00,'2018-06-06 07:18:28','won bet nr 75',54),(79,500.00,'2018-06-06 07:18:53','placed a bet',54),(80,860.00,'2018-06-06 07:18:58','won bet nr 78',54),(82,100.00,'2018-06-06 07:20:59','placed a bet',54),(83,172.00,'2018-06-06 07:21:00','won bet nr 81',54),(85,100.00,'2018-06-06 07:21:12','placed a bet',54),(87,800.00,'2018-06-06 07:21:41','placed a bet',54),(88,1376.00,'2018-06-06 07:21:46','won bet nr 86',54),(98,100.00,'2018-06-06 07:35:45','placed a bet',54),(99,232.00,'2018-06-06 07:36:02','won bet nr 97',54),(109,20.00,'2018-06-06 07:44:10','placed a bet',54),(110,46.60,'2018-06-06 07:45:53','won bet nr 108',54),(120,10.00,'2018-06-06 11:03:30','placed a bet',54),(121,13.32,'2018-06-06 11:04:31','cancel bet - return money',54),(123,100.00,'2018-06-06 11:07:38','placed a bet',54),(125,100.00,'2018-06-06 11:08:03','placed a bet',54),(126,90.00,'2018-06-06 11:09:29','cancel bet - return money',54),(136,-14.00,'2018-06-06 12:07:10','placed a bet',54),(137,-12.60,'2018-06-06 12:12:52','cancel bet nr 135 - return money',54),(139,100.00,'2018-06-06 12:13:20','placed a bet',54),(149,100.00,'2018-06-06 12:17:02','placed a bet',54),(151,100.00,'2018-06-06 12:17:41','placed a bet',54),(152,90.00,'2018-06-06 12:17:56','cancel bet nr 150 - return money',54),(154,20.00,'2018-06-06 12:18:32','placed a bet',54),(155,265.00,'2018-06-06 12:18:54','won bet nr 148',54),(26,100.00,'2018-06-06 12:34:01','placed a bet',54),(28,50.00,'2018-06-06 12:34:27','placed a bet',54),(47,187.00,'2018-06-06 12:35:17','won bet nr 25',54),(49,1000.00,'2018-06-06 12:36:28','placed a bet',54);
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL,
  `api_id` bigint(20) DEFAULT NULL,
  `final_standing` int(11) NOT NULL,
  `loser_winer_signature` varchar(255) DEFAULT NULL,
  `lost` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `place_in_group` int(11) NOT NULL,
  `point_balance` int(11) NOT NULL,
  `seeding` int(11) NOT NULL,
  `strength` double NOT NULL,
  `won` int(11) NOT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo3mildltdsbu7o4s5nswf5wtp` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,5,0,NULL,2,'Grandmaster Flash Warszawa',1,-8,1,8.4375,2,50),(2,6,0,NULL,3,'KWR Knury Kamieniec Wrocławski',1,-5,2,7.6875,1,51),(3,7,0,NULL,1,'muJAHedini dysku Warszawa',1,24,3,8.5,3,52),(4,8,0,NULL,1,'KS AZS AWF Flow Wrocław',1,9,4,9.625,3,53),(5,9,0,NULL,2,'Zawierucha Warszawa',2,7,5,7.9375,2,53),(6,10,0,NULL,3,'RJP Warszawa',2,-17,6,7.5,1,52),(7,11,0,NULL,1,'KS BC Kosmodysk Warszawa',2,8,7,7.65625,3,51),(8,12,0,NULL,2,'Kato Vice Katowice',2,-5,8,8.46875,2,50),(9,13,0,NULL,1,'4hands Warszawa',3,21,9,8.3125,3,50),(10,14,0,NULL,3,'KrakUF Kraków',3,-15,10,6.90625,1,51),(11,15,0,NULL,2,'Uwaga Pies Poznań',3,8,11,7,2,52),(12,16,0,NULL,2,'KS Nine Hills Chełmno',3,0,12,6.75,2,53),(13,17,0,NULL,3,'Frisbnik Rybnik',4,-16,13,5.125,1,53),(14,18,0,NULL,2,'Brave Beavers Dopiewo',4,-15,14,6.125,2,52),(15,19,0,NULL,1,'Ultimatum Gdańsk',4,12,15,6.671875,3,51),(16,20,0,NULL,3,'Mad Hatters MOSiR Płock',4,-8,16,4.75,1,50);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (4,2),(5,2),(7,2),(14,2),(53,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `adult` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `wallet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK2ndfo1foff7a36v7f6sst12ix` (`wallet_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','aaleksandra.mmarszalek@gmail.com','Aleksandra','Marszalek','$2a$10$oiBd734EozmqE4t0lV7m7uB95pDHvoOInlEiv8oEKOY1V5nTOdr1q','aleksandra-marszalek',NULL),(2,'','jan123@jan.com','Jan','Kowalski','$2a$10$.gitlEW1T7iAIae/ZfFgCuSBdt1vTFhkSxd7Mwcd6DSJGOv/F5LuO','jan.kowalski',NULL),(4,'','aaa@aa','Aaa','Aaa','$2a$10$7qSIG614yr44rSSDU19wIO5xJedOOZR931bascsOtRDJqrdP/xRS2','aaa',NULL),(5,'','anna@op.pl','Anna','Nowak','$2a$10$6J.vroFkLAl7T/jrpdiI0e7TymImX1NKXnBryfLxo5Hn2dMaeV9a2','Anna.Nowak',6),(7,'','jmadej92@gmail.com','Jan','Madej','$2a$10$YMESTuWP4MW7bhTmjsRxDuAy6EUvl8GwEOIzigT1YVDxxQCfL1GuW','janmadej',8),(14,'','tomek@tomek.pl','Tomek','Tomasz','$2a$10$jABJOGYedJOgkMQau8LQh.KKjuKzxo2De8rgftaGotJon6WLlMHam','tomek',15),(53,'','ola@gmail.com','Ola','Ola','$2a$10$qCa6hjuk4c3fcRWi74b/D.Soa5AUHT/2Rh8rAUf47eODLgAmtj60q','ola',54);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallet` (
  `id` bigint(20) NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgbusavqq0bdaodex4ee6v0811` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (6,0.00,NULL),(8,-80.00,7),(15,10.00,14),(54,550.82,53);
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-06 18:22:04
