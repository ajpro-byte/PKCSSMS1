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
-- Table structure for table `tbl_customer_purchase_report`
--

DROP TABLE IF EXISTS `tbl_customer_purchase_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_customer_purchase_report` (
  `Customer` varchar(45) DEFAULT NULL,
  `CustomerStatus` varchar(45) DEFAULT NULL,
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `Productdescription` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `TotalAmount` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL,
  `Staffname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_customer_purchase_report`
--

LOCK TABLES `tbl_customer_purchase_report` WRITE;
/*!40000 ALTER TABLE `tbl_customer_purchase_report` DISABLE KEYS */;
INSERT INTO `tbl_customer_purchase_report` VALUES ('Jay','Regular','ID3024012062019','prd','prd','13','4','52.0','Feb-27-2020','10:55 AM','admin'),('','','ID3330612072019','vans','shoes','650','1','650.0','Feb-27-2020','12:59 PM','admin'),('','','ID9240712072019','Name','description','260','3','780.0','Feb-27-2020','1:00 PM','admin'),('maria','Walk In','ID7071408162019','asd','asd','200','2','400.0','Feb-27-2020','1:01 PM','admin'),('tom cat','Walk In','ID6585410202019','sdf','sdf','4','6','24.0','Feb-27-2020','1:02 PM','admin'),('Juan Tamad','Walk In','ID3024012062019','prd','prd','13','1','13.0','Feb-27-2020','2:27 PM','admin'),('Jay','Regular','ID11002610202019','user4','user4','67','6','402.0','Feb-27-2020','2:27 PM','admin');
/*!40000 ALTER TABLE `tbl_customer_purchase_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 22:05:52
