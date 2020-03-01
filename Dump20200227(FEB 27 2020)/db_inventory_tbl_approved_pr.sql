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
-- Table structure for table `tbl_approved_pr`
--

DROP TABLE IF EXISTS `tbl_approved_pr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_approved_pr` (
  `PoNumber` varchar(45) NOT NULL,
  `PrNumber` varchar(45) NOT NULL,
  `VendorID` varchar(45) DEFAULT NULL,
  `VendorName` varchar(45) DEFAULT NULL,
  `TotalCost` varchar(45) DEFAULT NULL,
  `DeliveryStatus` varchar(45) DEFAULT NULL,
  `DateApproved` varchar(45) DEFAULT NULL,
  `TimeApproved` varchar(45) DEFAULT NULL,
  `ApprovedBy` varchar(45) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PoNumber`,`PrNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_approved_pr`
--

LOCK TABLES `tbl_approved_pr` WRITE;
/*!40000 ALTER TABLE `tbl_approved_pr` DISABLE KEYS */;
INSERT INTO `tbl_approved_pr` VALUES ('PO-0010751','PR-0010626','ID1206201964229','vbn','37972.0','Delivered','Feb-25-2020','1:07 PM','admin','Admin'),('PO-00125453','PR-00110257','ID1207201932834','kcc','13778.0','Delivered','Feb-25-2020','12:54 PM','admin','Admin'),('PO-0020538','PR-0020437','ID1206201964229','vbn','4688.0','Delivered','Feb-25-2020','2:05 PM','admin','Admin'),('PO-0085109','PR-0085021','ID0222202082704','unknown','4128.0','Not yet delivered','Feb-27-2020','8:51 AM','admin','Admin'),('PO-0090542','PR-0090435','ID0103202041932','Tiles corp.','6386.0','Delivered','Feb-25-2020','9:05 PM','admin','Admin'),('PO-0094253','PR-0094109','ID0103202041932','Tiles corp.','2924.0','Delivered','Feb-25-2020','9:42 AM','admin','Admin'),('PO-0094427','PR-0094109','ID0103202041932','Tiles corp.','2924.0','Delivered','Feb-25-2020','9:44 AM','admin','Admin');
/*!40000 ALTER TABLE `tbl_approved_pr` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 22:05:50
