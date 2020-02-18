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
-- Table structure for table `tbl_dump_sale`
--

DROP TABLE IF EXISTS `tbl_dump_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_dump_sale` (
  `Customer` varchar(45) DEFAULT NULL,
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `ProductCategory` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `Price` varchar(45) DEFAULT NULL,
  `TotalPrice` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_dump_sale`
--

LOCK TABLES `tbl_dump_sale` WRITE;
/*!40000 ALTER TABLE `tbl_dump_sale` DISABLE KEYS */;
INSERT INTO `tbl_dump_sale` VALUES ('Juan Tamad','ID10233810192019','g','dfg','d','11','50','50.0','7:44 AM','Dec-18-2019'),('Recardo Dalisay','ID12125410212019','user8','user8','user8','2','78','78.0','1:10 PM','Dec-18-2019'),('Recardo Dalisay','ID10233810192019','g','dfg','d','1','23','23.0','1:11 PM','Dec-18-2019'),('Recardo Dalisay','','','','','','','0.00','1:12 PM','Dec-18-2019'),('maria','ID6001110202019','asd','asd','asd','1','340','340.0','11:22 AM','Dec-26-2019'),('Recardo Dalisay','ID10231810192019','reqghj','reqghj','reqghjghj','8','234','1170.0','Dec-26-2019','11:25 AM'),('Recardo Dalisay','ID6001110202019','asd','asd','asd','2','345','345.0','Dec-26-2019','12:13 PM'),('Recardo Dalisay','ID10231810192019','reqghj','reqghj','reqghjghj','2','234','468.0','Dec-27-2019','3:09 PM'),('maria','ID11131910192019','fd','df','d','1','65','65.0','Dec-27-2019','3:09 PM'),('maria','ID6001110202019','asd','asd','asd','1','340','340.0','Dec-27-2019','3:10 PM'),('tom cat','ID10231810192019','reqghj','reqghj','reqghjghj','2','234','468.0','Dec-28-2019','11:53 AM'),('maria','ID10231810192019','reqghj','reqghj','reqghjghj','8','100','800.0','Jan-04-2020','9:47 AM'),('Recardo Dalisay','ID6001110202019','asd','asd','asd','1','345','345.0','Jan-06-2020','9:40 AM'),('maria','ID11131910192019','fd','df','d','1','60','60.0','Jan-26-2020','3:02 PM'),('Juan Tamad','ID11131910192019','fd','df','d','1','40','60.0','Feb-03-2020','8:46 PM');
/*!40000 ALTER TABLE `tbl_dump_sale` ENABLE KEYS */;
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
