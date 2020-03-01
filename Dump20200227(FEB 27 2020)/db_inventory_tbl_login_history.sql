CREATE DATABASE  IF NOT EXISTS `db_inventory` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_inventory`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: db_inventory
-- ------------------------------------------------------
-- Server version	5.5.21

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
-- Table structure for table `tbl_login_history`
--

DROP TABLE IF EXISTS `tbl_login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_login_history` (
  `AccountID` varchar(45) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `DateLogin` varchar(45) DEFAULT NULL,
  `TimeLogin` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_login_history`
--

LOCK TABLES `tbl_login_history` WRITE;
/*!40000 ALTER TABLE `tbl_login_history` DISABLE KEYS */;
INSERT INTO `tbl_login_history` VALUES ('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:08:00'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:08:17'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:08:27'),('ID0227202084736','User','user','User','Feb-27-2020','9:11:38'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:11:59'),('ID0227202084736','User','user','User','Feb-27-2020','9:13:07'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:14:52'),('ID0227202084736','User','user','User','Feb-27-2020','9:15:05'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:17:49'),('ID0227202084736','User','user','User','Feb-27-2020','9:23:52'),('ID0227202084736','User','user','User','Feb-27-2020','9:26:47'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:27:03'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:45:42'),('ID0227202084658','administrator','admin','Admin','Feb-27-2020','9:56 PM'),('ID0227202084736','User','user','User','Feb-27-2020','9:59 PM');
/*!40000 ALTER TABLE `tbl_login_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 22:05:51
