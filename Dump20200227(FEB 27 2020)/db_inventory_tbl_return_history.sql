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
-- Table structure for table `tbl_return_history`
--

DROP TABLE IF EXISTS `tbl_return_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_return_history` (
  `Customer` varchar(45) DEFAULT NULL,
  `CustomerStatus` varchar(45) DEFAULT NULL,
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `TotalAmount` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL,
  `Staffname` varchar(45) DEFAULT NULL,
  `Reason` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_return_history`
--

LOCK TABLES `tbl_return_history` WRITE;
/*!40000 ALTER TABLE `tbl_return_history` DISABLE KEYS */;
INSERT INTO `tbl_return_history` VALUES ('Juan Tamad','Walk In','ID11131910192019','fd','df','65','2','325.0','Dec-22-2019','12:19 PM','admin',''),('Juan Tamad','Walk In','ID3330612072019','vans','shoes','650','2','1300.0','Dec-22-2019','12:19 PM','admin','Damage Item'),('maria','Walk In','ID1213201982318','tshirt','tshirt','250','1','500.0','Dec-22-2019','12:19 PM','admin',''),('maria','Walk In','ID7075608162019','asd','asd','321','4','2889.0','Dec-22-2019','12:19 PM','admin','return only'),('tom cat','Regular','ID3330612072019','vans','shoes','500','2','1500.0','Dec-22-2019','1:56 PM','admin','return only'),('Juan Tamad','Walk In','ID10234710192019','dfg','dfg','56','2','280.0','Dec-20-2019','1:59 PM','admin','no reason'),('maria','Walk In','ID3330612072019','vans','shoes','650','1','650.0','Dec-20-2019','2:00 PM','admin','1'),('Juan Tamad','Walk In','ID7075608162019','asd','asd','321','2','1284.0','Dec-20-2019','2:00 PM','admin','no reason'),('tom cat','Regular','ID3330612072019','vans','shoes','500','1','1500.0','Dec-20-2019','2:00 PM','admin','no reason'),('tom cat','Regular','ID6001810202019','ert','ert','345','2','1380.0','Dec-19-2019','2:02 PM','admin',''),('Juan Tamad','Walk In','ID7071408162019',' asd',' asd','200','1','600.0','Dec-19-2019','2:02 PM','admin','no reason'),('Recardo Dalisay','Regular','ID6001110202019','asd','asd','345','1','690.0','Dec-26-2019','11:21 AM','admin','no reason'),('m','Regular','ID3330612072019','vans','shoes','500','2','1000.0','Dec-27-2019','9:53 AM','admin','reason'),('Recardo Dalisay','Regular','ID7222210202019','ghj','ghjgj','56','1','56.0','Dec-27-2019','6:14 PM','admin','3'),('Recardo Dalisay','Regular','ID6001110202019','asd','asd','345','1','690.0','Dec-27-2019','6:15 PM','admin','2'),('maria','Walk In','ID3024012062019','prd','prd','13','1','39.0','Dec-27-2019','6:15 PM','admin','no reason'),('Juan Tamad','Walk In','ID7071408162019',' asd',' asd','200','1','400.0','Dec-28-2019','12:25 PM','admin','return'),('Juan Tamad','Walk In','ID7114810192019','stock','stock','12','2','24.0','Dec-28-2019','12:50 PM','admin','return'),('Juan Tamad','Walk In','ID11131910192019','fd','df','65','1','65.0','Jan-26-2020','2:56 PM','admin',NULL),('Juan Tamad','Walk In','ID6001110202019','asd','asd','345','3','1035.0','Jan-26-2020','2:56 PM','admin',NULL),('maria','Walk In','ID11131910192019','fd','df','65','1','65.0','Jan-26-2020','2:58 PM','admin','return only'),('Juan Tamad','Walk In','ID3330612072019','vans','shoes','650','2','1300.0','Feb-03-2020','8:43 PM','admin','subra'),('Juan Tamad','Walk In','ID7071408162019',' asd',' asd','200','1','400.0','Feb-09-2020','5:06 PM','admin','a'),('Juan Tamad','Walk In','ID7071408162019',' asd',' asd','200','1','200.0','Feb-27-2020','9:37 AM','admin',''),('Juan Tamad','Walk In','ID10234710192019','dfg','dfg','56','55','56.0','Feb-27-2020','10:50 AM','admin','no reason'),('Jay','Regular','ID3024012062019','prd','prd','13','1','13.0','Feb-27-2020','10:56 AM','admin','no valid reason'),('Jay','Regular','ID3024012062019','prd','prd','13','2','26.0','Feb-27-2020','10:56 AM','admin','no valid reason'),('Jay','Regular','ID3024012062019','prd','prd','13','1','13.0','Feb-27-2020','11:40 AM','admin','wla lang'),('','','ID3330612072019','vans','shoes','650','1','650.0','Feb-27-2020','12:59 PM','admin','no'),('','','ID3330612072019','vans','shoes','650','1','650.0','Feb-27-2020','1:00 PM','admin','no'),('tom cat','Walk In','ID6585410202019','sdf','sdf','4','1','4.0','Feb-27-2020','1:04 PM','admin','test'),('tom cat','Walk In','ID6585410202019','sdf','sdf','4','1','4.0','Feb-27-2020','1:05 PM','admin','wef');
/*!40000 ALTER TABLE `tbl_return_history` ENABLE KEYS */;
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
