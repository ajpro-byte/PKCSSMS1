CREATE DATABASE  IF NOT EXISTS `db_inventory` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_inventory`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: db_inventory
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
-- Table structure for table `tbl_customer`
--

DROP TABLE IF EXISTS `tbl_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_customer` (
  `CustomerID` varchar(45) DEFAULT NULL,
  `Fullname` varchar(45) NOT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `Age` varchar(45) DEFAULT NULL,
  `Street` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `Contact` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `CustomerStatus` varchar(45) DEFAULT NULL,
  `CommitmentStatus` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_customer`
--

LOCK TABLES `tbl_customer` WRITE;
/*!40000 ALTER TABLE `tbl_customer` DISABLE KEYS */;
INSERT INTO `tbl_customer` VALUES ('ID12092019104506','Juan Tamad','Male','30','juan Street','GSC','43534534534','fdfgdfgdfgdfg','Walk In','Active'),('ID12092019104934','tom cat','Male','24','Tom street','gsc','4574','dgsd','Walk In','Active'),('ID1210201972857','maria','Female','20','street','city','7654323456','email','Walk In','Active'),('ID1218201992342','Recardo Dalisay','Male','20','dalisay strt','gensan','87654323456787','@email','Regular','Active'),('ID0002','Jay','Male','23','Street','City','090377532522','@Gmail.com','Regular','Active'),('ID0001','Jay','Male','23','Street','City','090377532522','@Gmail.com','Regular','Active'),('ID0209202020626','romina','Female','28','kadenang ginto','kadenang ginto','1234626262','romina@gmial.com','Regular','Active');
/*!40000 ALTER TABLE `tbl_customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 21:17:06
