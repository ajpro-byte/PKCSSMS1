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
-- Table structure for table `tbl_pr_description`
--

DROP TABLE IF EXISTS `tbl_pr_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pr_description` (
  `PrNumber` varchar(45) NOT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Delivery_Date` varchar(45) DEFAULT NULL,
  `Autorazation` varchar(45) DEFAULT NULL,
  `PaymentMethod` varchar(45) DEFAULT NULL,
  `Vendor` varchar(45) DEFAULT NULL,
  `VendorID` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Contact` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `TotalAmount` varchar(45) DEFAULT NULL,
  `PreparedBy` varchar(45) DEFAULT NULL,
  `ApprovalStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PrNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pr_description`
--

LOCK TABLES `tbl_pr_description` WRITE;
/*!40000 ALTER TABLE `tbl_pr_description` DISABLE KEYS */;
INSERT INTO `tbl_pr_description` VALUES ('','','','','','','unknown','','','','','0.0','admin','Approved'),('PR-0010626','02/25/2020','description','02/27/2020','Admin','COD','vbn','ID1206201964229','vbn','456456','456456','37972.0','admin','Approved'),('PR-00110257','02/25/2020','desafcc','03/19/2020','Admin','COD','kcc','ID1207201932834','gensan','9876543456789','email','13778.0','admin','Approved'),('PR-0020352','02/25/2020','s','02/01/2020','Admin','cod','Juan Tamad','ID1206201961530','Brgy. Masigasig','+63934377535366','JuanTamad@gmail.com','4205.0','admin','Approved'),('PR-0020437','02/25/2020','asdca','02/08/2020','Admin','cod','vbn','ID1206201964229','vbn','456456','456456','4688.0','admin','Approved'),('PR-0085021','02/27/2020','admin	','03/27/2020','Admin','Admin','unknown','ID0222202082704','unknown','unknown','unknown','4128.0','admin','Approved'),('PR-0090435','02/25/2020','for new stocks','02/29/2020','Admin','cod','Tiles corp.','ID0103202041932','gensan','23465','sdfsdf','6386.0','admin','Approved'),('PR-0092051','02/27/2020','user','03/27/2020','Admin','cod','dyd','ID1206201964128','ytu','567','567','235744.0','user','Pending'),('PR-0094109','02/25/2020','des','02/12/2020','Admin','check','Tiles corp.','ID0103202041932','gensan','23465','sdfsdf','2924.0','admin','Approved');
/*!40000 ALTER TABLE `tbl_pr_description` ENABLE KEYS */;
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
