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
-- Table structure for table `tbl_customer_purchase`
--

DROP TABLE IF EXISTS `tbl_customer_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_customer_purchase` (
  `Customer` varchar(45) NOT NULL,
  `CustomerStatus` varchar(45) DEFAULT NULL,
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `TotalAmount` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL,
  `Staffname` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_customer_purchase`
--

LOCK TABLES `tbl_customer_purchase` WRITE;
/*!40000 ALTER TABLE `tbl_customer_purchase` DISABLE KEYS */;
INSERT INTO `tbl_customer_purchase` VALUES ('Juan Tamad','Walk In','ID11131910192019','fd','df','65','3','325.0','Dec-22-2019','12:17 PM','admin'),('Juan Tamad','Walk In','ID10234710192019','dfg','dfg','56','2','224.0','Dec-22-2019','12:18 PM','admin'),('maria','Walk In','ID7075608162019','asd','asd','321','5','2889.0','Dec-22-2019','12:18 PM','admin'),('maria','Walk In','ID1213201982318','tshirt','tshirt','250','1','500.0','Dec-22-2019','12:18 PM','admin'),('tom cat','Regular','ID3330612072019','vans','shoes','500','1','1500.0','Dec-22-2019','1:54 PM','admin'),('tom cat','Regular','ID3330612072019','vans','shoes','500','2','1500.0','Dec-22-2019','1:55 PM','admin'),('Juan Tamad','Walk In','ID7075608162019','asd','asd','321','2','1284.0','Dec-20-2019','1:57 PM','admin'),('Juan Tamad','Walk In','ID7071408162019',' asd',' asd','200','2','600.0','Dec-19-2019','2:01 PM','admin'),('tom cat','Regular','ID6001810202019','ert','ert','345','2','1380.0','Dec-19-2019','2:01 PM','admin'),('maria','Walk In','ID6001810202019','ert','ert','44','2','44.0','Dec-19-2019','2:01 PM','admin'),('Recardo Dalisay','Regular','ID7071408162019',' asd',' asd','100','2','200.0','Dec-24-2019','3:32 PM','admin'),('Recardo Dalisay','Regular','ID7222210202019','ghj','ghjgj','56','9','504.0','Dec-24-2019','3:59 PM','admin'),('Juan Tamad','Walk In','ID6585410202019','sdf','sdf','4','1','4.0','Dec-26-2019','12:41 PM','admin'),('Recardo Dalisay','Regular','ID6001110202019','asd','asd','345','2','690.0','Dec-27-2019','6:12 PM','admin'),('maria','Walk In','ID3024012062019','prd','prd','13','2','39.0','Dec-27-2019','6:13 PM','admin'),('maria','Walk In','ID10592910202019','user','user','12','2','24.0','Dec-27-2019','6:13 PM','admin'),('Juan Tamad','Walk In','ID7222210202019','ghj','ghjgj','65','2','130.0','Dec-27-2019','6:53 PM','admin'),('Juan Tamad','Walk In','ID6001110202019','asd','asd','345','3','1035.0','Dec-27-2019','6:53 PM','admin'),('Juan Tamad','Walk In','ID10225010192019','req','req','57','1','57.0','Dec-27-2019','6:53 PM','admin'),('Juan Tamad','Walk In','ID6001810202019','ert','ert','44','2','88.0','Dec-28-2019','2:30 PM','admin'),('maria','Walk In','ID6001810202019','ert','ert','44','1','44.0','Dec-28-2019','2:31 PM','admin'),('tom cat','Regular','ID6585410202019','sdf','sdf','234','2','468.0','Dec-28-2019','3:43 PM','admin'),('Recardo Dalisay','Regular','ID7222210202019','ghj','ghjgj','56','3','168.0','Jan-04-2020','9:46 AM','admin'),('maria','Walk In','ID11131910192019','fd','df','65','1','65.0','Jan-06-2020','9:30 AM','admin'),('Jay','Regular','ID9240712072019','ad','asd','210','2','420.0','Feb-02-2020','1:23 PM','admin'),('Jay','Regular','ID7222210202019','ghj','ghjgj','56','1','56.0','Feb-03-2020','8:27 PM','admin'),('','','ID9240712072019','Name','description','260','1','260.0','Feb-22-2020','11:13 PM','admin'),('','','ID6585410202019','sdf','sdf','4','2','8.0','Feb-22-2020','11:13 PM','admin'),('Juan Tamad','Walk In','ID7114810192019','stock','stock','12','2','24.0','Feb-25-2020','6:45 PM','admin'),('Jay','Regular','ID3024012062019','prd','prd','13','4','52.0','Feb-27-2020','10:55 AM','admin'),('','','ID3330612072019','vans','shoes','650','1','650.0','Feb-27-2020','12:59 PM','admin'),('','','ID9240712072019','Name','description','260','3','780.0','Feb-27-2020','1:00 PM','admin'),('maria','Walk In','ID7071408162019','asd','asd','200','2','400.0','Feb-27-2020','1:01 PM','admin'),('tom cat','Walk In','ID6585410202019','sdf','sdf','4','6','24.0','Feb-27-2020','1:02 PM','admin'),('Juan Tamad','Walk In','ID3024012062019','prd','prd','13','1','13.0','Feb-27-2020','2:27 PM','admin'),('Jay','Regular','ID11002610202019','user4','user4','67','6','402.0','Feb-27-2020','2:27 PM','admin');
/*!40000 ALTER TABLE `tbl_customer_purchase` ENABLE KEYS */;
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
