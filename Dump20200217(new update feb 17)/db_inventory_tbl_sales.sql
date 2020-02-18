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
-- Table structure for table `tbl_sales`
--

DROP TABLE IF EXISTS `tbl_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_sales` (
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `TotalPrice` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL,
  `Month` varchar(45) DEFAULT NULL,
  `Year` varchar(45) DEFAULT NULL,
  `StaffName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sales`
--

LOCK TABLES `tbl_sales` WRITE;
/*!40000 ALTER TABLE `tbl_sales` DISABLE KEYS */;
INSERT INTO `tbl_sales` VALUES ('ID7071408162019',' asd',' asd','200','2','400.0','08/20/2019','12:19:56','08/2019','2019','--------------------'),('ID7075608162019','asd','asd','321','2','642.0','08/20/2019','12:21:14','08/2019','2019','--------------------'),('ID7075608162019','asd','asd','321','2','642.0','08/20/2019','12:21:14','08/2019','2019','--------------------'),('ID7103908172019','qwe','qwe','12','3','36.0','08/20/2019','12:24:05','08/2019','2019','--------------------'),('ID7071408162019',' asd',' asd','200','9','1800.0','08/23/2019','8:32:38','08/2019','2019','--------------------'),('ID7075608162019','asd','asd','321','2','642.0','08/28/2019','6:00:47','08/2019','2019','admin'),('ID7075608162019','asd','asd','321','1','321.0','07/28/2019','6:03:25','07/2019','2019','admin'),('ID7535910192019','request','request','454','5','2270.0','10/20/2019','8:15:45','10/2019','2019','admin'),('ID7535910192019','request','request','454','7','3178.0','10/20/2019','8:17:05','10/2019','2019','admin'),('ID12125410212019','user8','user8','78','1','78.0','07-Dec-19','3:42 PM','12/2019','2019','admin'),('ID3330612072019','vans','shoes','650','3','1950.0','07-Dec-19','3:45 PM','12/2019','2019','admin'),('ID3330612072019','vans','shoes','650','5','3250.0','07-Dec-19','3:48 PM','12/2019','2019','admin'),('ID9240712072019','ad','asd','250','4','1000.0','Dec-11-2019','10:47 AM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','56','7','392.0','Dec-11-2019','11:52 AM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','200','1','200.0','Dec-11-2019','11:56 AM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','200','1','200.0','Dec-11-2019','11:57 AM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','200','4','800.0','Dec-11-2019','12:10 PM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','250','4','1000.0','Dec-11-2019','12:13 PM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','250','2','500.0','Dec-11-2019','12:23 PM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','250','4','1000.0','Dec-11-2019','12:29 PM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','250','6','1500.0','Dec-11-2019','4:15 PM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','56','3','168.0','Dec-11-2019','4:59 PM','Dec/2019','2019','admin'),('ID7075608162019','asd','asd','123','1','123.0','Dec-11-2019','5:00 PM','Dec/2019','2019','admin'),('ID9240712072019','ad','asd','250','2','500.0','Dec-11-2019','7:17 PM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','65','5','325.0','Dec-12-2019','10:26 AM','Dec/2019','2019','admin'),('ID6001110202019','asd','asd','345','2','690.0','Dec-12-2019','10:27 AM','Dec/2019','2019','admin'),('ID3024012062019','prd','prd','13','5','65.0','Dec-12-2019','2:07 PM','Dec/2019','2019','admin'),('ID3024012062019','prd','prd','13','2','26.0','Dec-12-2019','2:07 PM','Dec/2019','2019','admin'),('ID12121810212019','user7','user7','67','5','335.0','Dec-12-2019','2:23 PM','Dec/2019','2019','admin'),('ID12125410212019','user8','user8','78','2','156.0','Dec-13-2019','11:31 AM','Dec/2019','2019','admin'),('ID11131910192019','fd','df','65','4','260.0','Dec-13-2019','8:06 PM','Dec/2019','2019','admin'),('ID7114810192019','stock','stock','12','2','24.0','Dec-15-2019','2:43 AM','Dec/2019','2019','admin'),('ID7071408162019',' asd',' asd','200','4','800.0','Dec-15-2019','2:43 AM','Dec/2019','2019','admin'),('ID12121810212019','user7','user7','67','1','67.0','Dec-15-2019','2:43 AM','Dec/2019','2019','admin'),('ID10233810192019','g','dfg','23','1','23.0','Dec-15-2019','2:44 AM','Dec/2019','2019','admin'),('ID10225010192019','req','req','57','46','2907.0','Dec-15-2019','3:16 AM','Dec/2019','2019','admin'),('ID10230510192019','reqs','req','5','5','30.0','Dec-15-2019','3:16 AM','Dec/2019','2019','admin'),('ID11000510202019','user1','user1','23','1','23.0','Dec-15-2019','3:32 AM','Dec/2019','2019','admin'),('ID11131910192019','fd','df','65','3','325.0','Dec-22-2019','12:17 PM','Dec/2019','2019','admin'),('ID10234710192019','dfg','dfg','56','3','280.0','Dec-22-2019','12:18 PM','Dec/2019','2019','admin'),('ID7075608162019','asd','asd','321','5','2889.0','Dec-22-2019','12:18 PM','Dec/2019','2019','admin'),('ID1213201982318','tshirt','tshirt','250','1','500.0','Dec-22-2019','12:18 PM','Dec/2019','2019','admin'),('ID3330612072019','vans','shoes','500','1','1500.0','Dec-22-2019','1:54 PM','Dec/2019','2019','admin'),('ID3330612072019','vans','shoes','500','2','1500.0','Dec-22-2019','1:55 PM','Dec/2019','2019','admin'),('ID7075608162019','asd','asd','321','2','1284.0','Dec-20-2019','1:57 PM','Dec/2019','2019','admin'),('ID7071408162019',' asd',' asd','200','2','600.0','Dec-19-2019','2:01 PM','Dec/2019','2019','admin'),('ID6001810202019','ert','ert','345','2','1380.0','Dec-19-2019','2:01 PM','Dec/2019','2019','admin'),('ID6001810202019','ert','ert','44','2','44.0','Dec-19-2019','2:01 PM','Dec/2019','2019','admin'),('ID7071408162019',' asd',' asd','100','2','200.0','Dec-24-2019','3:32 PM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','56','9','504.0','Dec-24-2019','3:59 PM','Dec/2019','2019','admin'),('ID6585410202019','sdf','sdf','4','1','4.0','Dec-26-2019','12:41 PM','Dec/2019','2019','admin'),('ID6001110202019','asd','asd','345','2','690.0','Dec-27-2019','6:12 PM','Dec/2019','2019','admin'),('ID3024012062019','prd','prd','13','2','39.0','Dec-27-2019','6:13 PM','Dec/2019','2019','admin'),('ID10592910202019','user','user','12','2','24.0','Dec-27-2019','6:13 PM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','65','2','130.0','Dec-27-2019','6:53 PM','Dec/2019','2019','admin'),('ID6001110202019','asd','asd','345','3','1035.0','Dec-27-2019','6:53 PM','Dec/2019','2019','admin'),('ID10225010192019','req','req','57','1','57.0','Dec-27-2019','6:53 PM','Dec/2019','2019','admin'),('ID6001810202019','ert','ert','44','2','88.0','Dec-28-2019','2:30 PM','Dec/2019','2019','admin'),('ID6001810202019','ert','ert','44','1','44.0','Dec-28-2019','2:31 PM','Dec/2019','2019','admin'),('ID6585410202019','sdf','sdf','234','2','468.0','Dec-28-2019','3:43 PM','Dec/2019','2019','admin'),('ID7222210202019','ghj','ghjgj','56','3','168.0','Jan-04-2020','9:46 AM','Jan/2020','2020','admin'),('ID7071408162019',' asd',' asd','200','1','200.0','Jan-06-2020','9:17 AM','Jan/2020','2020','admin'),('ID11131910192019','fd','df','65','1','65.0','Jan-06-2020','9:30 AM','Jan/2020','2020','admin'),('ID9240712072019','ad','asd','210','2','420.0','Feb-02-2020','1:23 PM','Feb/2020','2020','admin'),('ID7222210202019','ghj','ghjgj','56','1','56.0','Feb-03-2020','8:27 PM','Feb/2020','2020','admin');
/*!40000 ALTER TABLE `tbl_sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 21:17:07
