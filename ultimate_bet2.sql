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
INSERT INTO `bet` VALUES (37,100.00,1.87,'2018-06-06 21:50:22','won',187.00,8,19,1),(56,100.00,1.57,'2018-06-06 21:51:43','won',157.00,43,15,1),(58,100.00,3.46,'2018-06-06 21:52:18','lost',346.00,41,22,1),(64,100.00,1.41,'2018-06-06 22:02:54','won',141.00,50,22,60),(66,10.00,1.47,'2018-06-06 22:03:11','lost',14.70,46,17,60),(82,10.00,2.42,'2018-06-06 22:13:25','lost',24.20,78,28,71),(104,20.00,2.42,'2018-06-07 11:30:13','lost',48.40,78,28,100),(106,20.00,2.42,'2018-06-07 11:49:42','lost',48.40,78,28,88),(108,20.00,1.88,'2018-06-07 11:49:53','won',37.60,79,14,88),(110,20.00,2.65,'2018-06-07 11:50:07','lost',53.00,79,18,88),(122,10.00,2.25,'2018-06-07 12:00:14','won',22.50,114,21,88);
/*!40000 ALTER TABLE `bet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrylb2w10mvr2e6cwbisk75obj` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
INSERT INTO `favourite` VALUES (89,88),(93,92),(101,100);
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite_teams`
--

DROP TABLE IF EXISTS `favourite_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite_teams` (
  `favourite_id` bigint(20) NOT NULL,
  `teams_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_8rvnm0ov9vhmmf52dm37ropll` (`teams_id`),
  KEY `FKn7ip36iff3k3jntg4kgq935ve` (`favourite_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite_teams`
--

LOCK TABLES `favourite_teams` WRITE;
/*!40000 ALTER TABLE `favourite_teams` DISABLE KEYS */;
INSERT INTO `favourite_teams` VALUES (89,13),(93,19),(101,14);
/*!40000 ALTER TABLE `favourite_teams` ENABLE KEYS */;
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
INSERT INTO `game` VALUES (5,21,1.71,3.07,'2018-09-17 08:00:00',0.6415094339622641,0.3584905660377358,1,7,NULL,1,13,28),(6,22,2.17,2.23,'2018-09-17 09:00:00',0.5074626865671642,0.4925373134328358,1,4,NULL,1,20,21),(7,23,1.25,9.35,'2018-09-17 08:00:00',0.8823529411764706,0.11764705882352941,6,0,NULL,1,14,27),(8,24,1.87,2.67,'2018-09-17 09:00:00',0.5882352941176471,0.4117647058823529,7,2,NULL,1,19,22),(9,25,1.84,2.73,'2018-09-17 08:00:00',0.5964912280701754,0.40350877192982454,5,7,NULL,1,15,26),(10,26,2.04,2.39,'2018-09-17 09:00:00',0.5396825396825397,0.4603174603174603,0,8,NULL,1,18,23),(11,27,1.91,2.6,'2018-09-17 08:00:00',0.576271186440678,0.423728813559322,9,11,NULL,1,16,25),(12,28,1.56,3.74,'2018-09-17 09:00:00',0.7058823529411765,0.29411764705882354,12,5,NULL,1,17,24),(39,29,2.19,2.21,'2018-09-17 10:00:00',0.50187265917603,0.49812734082397003,6,14,NULL,1,13,21),(40,30,1.71,3.07,'2018-09-17 11:00:00',0.6415094339622641,0.3584905660377358,11,7,NULL,1,20,28),(41,31,2,2.44,'2018-09-17 10:00:00',0.55,0.45,0,12,NULL,1,14,22),(42,32,1.32,6.6,'2018-09-17 11:00:00',0.8333333333333334,0.16666666666666666,12,1,NULL,1,19,27),(43,33,2.04,2.39,'2018-09-17 10:00:00',0.5396825396825397,0.4603174603174603,14,6,NULL,1,15,23),(44,34,2.09,2.32,'2018-09-17 11:00:00',0.5253456221198156,0.47465437788018433,3,14,NULL,1,18,26),(45,35,2.06,2.36,'2018-09-17 10:00:00',0.5346534653465347,0.46534653465346537,1,12,NULL,1,16,24),(46,36,2.03,2.41,'2018-09-17 11:00:00',0.5427509293680297,0.45724907063197023,0,14,NULL,1,17,25),(47,37,2.22,2.18,'2018-09-17 12:00:00',0.4962962962962963,0.5037037037037037,5,10,NULL,1,13,20),(48,38,2.05,2.37,'2018-09-17 13:00:00',0.5365853658536586,0.4634146341463415,6,7,NULL,1,21,28),(49,39,2.17,2.23,'2018-09-17 12:00:00',0.5060240963855421,0.4939759036144578,0,11,NULL,1,14,19),(50,40,1.34,6.05,'2018-09-17 13:00:00',0.8181818181818182,0.18181818181818182,10,8,NULL,1,22,27),(51,41,2.15,2.25,'2018-09-17 12:00:00',0.5112474437627812,0.4887525562372188,8,9,NULL,1,15,18),(52,42,2.08,2.34,'2018-09-17 13:00:00',0.5296803652968036,0.4703196347031963,9,4,NULL,1,23,26),(53,43,2.59,1.91,'2018-09-17 12:00:00',0.4251968503937008,0.5748031496062992,6,10,NULL,1,16,17),(54,44,2.27,2.13,'2018-09-17 13:00:00',0.483835005574136,0.5161649944258639,7,0,NULL,1,24,25),(74,45,2.23,2.17,'2018-09-17 16:00:00',0.49369571308489774,0.5063042869151023,6,1,'A1',1,21,25),(75,46,2.11,2.3,'2018-09-17 16:00:00',0.5223880597014925,0.47761194029850745,9,11,'B1',1,19,23),(76,47,2.14,2.27,'2018-09-17 16:00:00',0.5144660704892162,0.4855339295107838,4,13,'C1',1,20,24),(77,48,1.89,2.64,'2018-09-17 16:00:00',0.582995951417004,0.41700404858299595,10,1,'D1',1,22,26),(78,49,2.42,2.02,'2018-09-17 17:00:00',0.4552332912988651,0.544766708701135,4,8,'A2',1,28,16),(79,50,1.88,2.65,'2018-09-17 17:00:00',0.5843478260869566,0.4156521739130435,10,4,'B2',1,14,18),(80,51,2.3,2.11,'2018-09-17 17:00:00',0.4785714285714286,0.5214285714285715,10,14,'C2',1,13,17),(81,52,5.4,1.38,'2018-09-17 17:00:00',0.20382165605095542,0.7961783439490446,1,7,'D2',1,27,15),(114,53,2.21,2.19,'2018-09-18 11:00:00',0.49710664094791956,0.5028933590520804,14,7,'A1',1,21,24),(115,54,2.23,2.17,'2018-09-18 11:00:00',0.4927536231884058,0.5072463768115942,8,4,'B1',1,23,22),(116,55,2.29,2.12,'2018-09-18 11:00:00',0.4802019665160776,0.5197980334839224,7,12,'A2',1,25,20),(117,56,1.91,2.6,'2018-09-18 11:00:00',0.5761316872427984,0.42386831275720166,13,4,'B2',1,19,26),(118,57,2.28,2.12,'2018-09-18 10:00:00',0.4819734345351044,0.5180265654648957,10,0,'A3',1,16,17),(119,58,2.12,2.29,'2018-09-18 10:00:00',0.5190434012400355,0.48095659875996455,2,8,'B3',1,14,15),(120,59,2.31,2.1,'2018-09-18 10:00:00',0.4767472761094871,0.5232527238905129,5,1,'A4',1,28,13),(121,60,1.39,5.21,'2018-09-18 10:00:00',0.7887788778877888,0.21122112211221122,8,4,'B4',1,18,27),(125,61,2.43,2.01,'2018-09-18 14:00:00',0.45326633165829144,0.5467336683417086,0,0,'1',0,21,23),(126,62,2.45,2,'2018-09-18 14:00:00',0.4489544895448955,0.5510455104551045,0,0,'3',0,24,22),(127,63,2.36,2.06,'2018-09-18 14:00:00',0.4661582459485224,0.5338417540514776,0,0,'5',0,20,19),(128,64,2.1,2.31,'2018-09-18 14:00:00',0.5230101302460203,0.4769898697539797,0,0,'7',0,25,26),(129,65,2.28,2.13,'2018-09-18 13:00:00',0.483349191246432,0.5166508087535681,0,0,'9',0,16,15),(130,66,2.28,2.12,'2018-09-18 13:00:00',0.4823321554770318,0.5176678445229682,0,0,'11',0,17,14),(131,67,2.27,2.13,'2018-09-18 13:00:00',0.4840798704803022,0.5159201295196978,0,0,'13',0,28,18),(132,68,1.39,5.33,'2018-09-18 13:00:00',0.7936316001612254,0.2063683998387747,0,0,'15',0,13,27);
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
INSERT INTO `groups` VALUES (29,1,'A'),(30,2,'B'),(31,3,'C'),(32,4,'D');
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
INSERT INTO `hibernate_sequence` VALUES (133),(133),(133),(133),(133),(133),(133),(133);
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
INSERT INTO `operation` VALUES (3,50.00,'2018-06-06 21:39:36','add money',2),(4,100.00,'2018-06-06 21:39:48','add money',2),(34,100.00,'2018-06-06 21:49:43','placed a bet',2),(35,90.00,'2018-06-06 21:49:54','cancel bet nr 33 - return money',2),(36,100.00,'2018-06-06 21:50:10','add money',2),(38,100.00,'2018-06-06 21:50:22','placed a bet',2),(55,187.00,'2018-06-06 21:50:52','won bet nr 37',2),(57,100.00,'2018-06-06 21:51:43','placed a bet',2),(59,100.00,'2018-06-06 21:52:18','placed a bet',2),(62,100.00,'2018-06-06 21:58:08','add money',61),(63,50.00,'2018-06-06 21:58:38','add money',61),(65,100.00,'2018-06-06 22:02:54','placed a bet',61),(67,10.00,'2018-06-06 22:03:11','placed a bet',61),(68,10.00,'2018-06-06 22:03:29','add money',61),(69,157.00,'2018-06-06 22:03:42','won bet nr 56',2),(70,141.00,'2018-06-06 22:04:52','won bet nr 64',61),(73,200.00,'2018-06-06 22:10:51','add money',72),(83,10.00,'2018-06-06 22:13:25','placed a bet',72),(91,100.00,'2018-06-06 23:32:05','add money',90),(95,20.00,'2018-06-07 09:40:33','add money',90),(96,50.00,'2018-06-07 11:07:45','add money',94),(98,10.00,'2018-06-07 11:08:42','placed a bet',94),(99,9.00,'2018-06-07 11:09:26','cancel bet nr 97 - return money',94),(103,50.00,'2018-06-07 11:29:00','add money',102),(105,20.00,'2018-06-07 11:30:13','placed a bet',102),(107,20.00,'2018-06-07 11:49:42','placed a bet',90),(109,20.00,'2018-06-07 11:49:53','placed a bet',90),(111,20.00,'2018-06-07 11:50:07','placed a bet',90),(112,37.60,'2018-06-07 11:56:45','won bet nr 108',90),(113,20.00,'2018-06-07 11:59:01','add money',90),(123,10.00,'2018-06-07 12:00:14','placed a bet',90),(124,22.50,'2018-06-07 12:04:16','won bet nr 122',90);
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
INSERT INTO `role` VALUES (1,'user');
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
  `teams_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33a073mt098l3x7qkht6rkt21` (`teams_id`),
  KEY `FKo3mildltdsbu7o4s5nswf5wtp` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (13,5,16,NULL,5,'Grandmaster Flash Warszawa',4,-27,1,7.69140625,0,29,NULL),(14,6,16,NULL,3,'KWR Knury Kamieniec Wrocławski',3,-17,2,9.15625,2,30,NULL),(15,7,16,NULL,2,'muJAHedini dysku Warszawa',3,17,3,8.484375,3,31,NULL),(16,8,16,NULL,3,'KS AZS AWF Flow Wrocław',4,-3,4,7.9375,2,32,NULL),(17,9,16,NULL,2,'Zawierucha Warszawa',3,-9,5,8.53125,3,32,NULL),(18,10,16,NULL,3,'RJP Warszawa',4,-20,6,7.46875,2,31,NULL),(19,11,16,NULL,1,'KS BC Kosmodysk Warszawa',1,34,7,8.75,4,30,NULL),(20,12,16,NULL,2,'Kato Vice Katowice',2,2,8,7.640625,3,29,NULL),(21,13,16,NULL,1,'4hands Warszawa',1,22,9,7.046875,4,29,NULL),(22,14,16,NULL,2,'KrakUF Kraków',2,14,10,8.75,3,30,NULL),(23,15,16,NULL,1,'Uwaga Pies Poznań',2,11,11,8.5,4,31,NULL),(24,16,16,NULL,2,'KS Nine Hills Chełmno',1,13,12,7.12890625,3,32,NULL),(25,17,16,NULL,3,'Frisbnik Rybnik',2,-1,13,7.05859375,2,32,NULL),(26,18,16,NULL,3,'Brave Beavers Dopiewo',1,-10,14,6.4375,2,31,NULL),(27,19,16,NULL,5,'Ultimatum Gdańsk',4,-29,15,2,0,30,NULL),(28,20,16,NULL,2,'Mad Hatters MOSiR Płock',3,3,16,7.0078125,3,29,NULL);
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
INSERT INTO `user_role` VALUES (1,1),(60,1),(71,1),(84,1),(86,1),(88,1),(92,1),(100,1);
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
  `favourite_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK4a81vy1fw2fj12kvtttg1nxea` (`favourite_id`),
  KEY `FK2ndfo1foff7a36v7f6sst12ix` (`wallet_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','ola@gmail.com','Ola','Ola','$2a$10$K3XA9YhcCC9OBW1OiwBKOeKoGvHP.9GmelMezHqz1sFftyhIgkZ42','ola',2,NULL),(60,'','jan@jan.com','Jan','Madej','$2a$10$u62V63dDRJfkv4QJr2TmrOmYdkkmAYGCRnPfr5EfJqGZt56t14ibi','janmadej',61,NULL),(71,'','john.doe@doe.com','John','Doe','$2a$10$ve5dtA1FbD7.iIrwp7cN9.Kp.JAgVL.v0L5nvLUoISc6w394ZEVDe','John_Doe',72,NULL),(84,'','janina@janina.com','Janina','Nowak','$2a$10$scToDlHtmOBIJIL7tY0u4OexYIKIuBeohATZpD0syc00kxxsRVjoy','janina',85,NULL),(86,'','ewa@ewa.pl','ewa','ewa','$2a$10$3hu7XmTSXL5W6yN1Ct/ut.3BIQgoU4PtuY4PTSpUuuyW93qoH5lg6','ewa',87,NULL),(88,'','anna123@anna123.pl','anna','anna','$2a$10$s5tnUCxz4L5u5l9xh0zYZeIt5vNbD8VrRB1NPNik1l8ZY8.UIjHjy','anna',90,89),(92,'','jozek@jozek.com','jozek','jozek','$2a$10$tBk8McisslYCqphmOr9MgOkLYWsuHkzdIes7XLdYagYYAs5K6Xroi','jozek',94,93),(100,'','tomasz@tomasz.tomasz','tomasz','tomasz','$2a$10$mUr9xHdPu8mXpof5.cwk/.HHPIa5EFp5osNi/20GQsLG7m63PcJWq','tomasz',102,101);
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
INSERT INTO `wallet` VALUES (2,284.00,1),(61,191.00,60),(72,190.00,71),(85,0.00,84),(87,0.00,86),(90,130.10,88),(94,49.00,92),(102,30.00,100);
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

-- Dump completed on 2018-06-07 12:20:00
