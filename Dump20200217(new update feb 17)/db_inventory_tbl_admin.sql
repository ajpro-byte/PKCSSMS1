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
-- Table structure for table `tbl_admin`
--

DROP TABLE IF EXISTS `tbl_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_admin` (
  `AccountID` varchar(45) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `ContactNo` varchar(45) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `Q1` varchar(100) DEFAULT NULL,
  `A1` varchar(100) DEFAULT NULL,
  `Q2` varchar(100) DEFAULT NULL,
  `A2` varchar(100) DEFAULT NULL,
  `Q3` varchar(100) DEFAULT NULL,
  `A3` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_admin`
--

LOCK TABLES `tbl_admin` WRITE;
/*!40000 ALTER TABLE `tbl_admin` DISABLE KEYS */;
INSERT INTO `tbl_admin` VALUES ('ID3035108262019','admin','admin','ADMIN','admin','admin','Admin','Active','What is your favorite movie?','admin','What is the name of your first teacher?','admin','What was the name of your first school?','admin'),('ID3062608262019','admin2','admin2','admin2','admin2','admin2','Admin','Inactive','What is your pet\'s name?','admin2','What is the name of your first teacher?','admin2','What was the name of your first school?','admin2'),('ID10253909162019','admin01','admin01','admin01','admin01','admin01','Admin','Active','What is your favorite color?','admin01','What is your childhood nickname?','admin01','What was the name of your first school?','admin01'),('ID7122310202019','juan','juan','juan','juan','juan','Admin','Active','What is your favorite foor?','juan','What\'s the name of a college you applied to but didn\'t attend?','juan','What\'s the name of the hosipital in which you were born?','juan'),('ID0103202050436','Lee ann','Lee ann','Lee ann','Lee ann','Lee ann','Admin','Active','What is your pet\'s name?','Lee ann','What\'s the name of a college you applied to but didn\'t attend?','Lee ann','What\'s the name of the hosipital in which you were born?','Lee ann'),('ID01192020104157','Jaymar Abrea','admin','admin1234','uhaw','0909732422223','Admin','Active','What is your favorite movie?','admin','What is your childhood nickname?','admin','What was the name of your first school?','admin');
/*!40000 ALTER TABLE `tbl_admin` ENABLE KEYS */;
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
