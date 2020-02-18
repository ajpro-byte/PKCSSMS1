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
-- Table structure for table `tbl_dump`
--

DROP TABLE IF EXISTS `tbl_dump`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_dump` (
  `ProductID` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `ProductCategory` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `OriginalPrice` varchar(45) DEFAULT NULL,
  `ValuesSRP` varchar(45) DEFAULT NULL,
  `Supplier` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_dump`
--

LOCK TABLES `tbl_dump` WRITE;
/*!40000 ALTER TABLE `tbl_dump` DISABLE KEYS */;
INSERT INTO `tbl_dump` VALUES ('ID7114810192019','stock','stock','stock','2','12','12','JACK N\' JILL','Dec-13-2019','5:38 PM'),('ID10231810192019','reqghj','reqghj','reqghjghj','80','234','100','asd','Dec-13-2019','5:38 PM'),('ID10233810192019','g','dfg','d','3','23','50','Jack','Dec-13-2019','5:56 PM'),('ID6001110202019','asd','asd','asd','90','345','340','JACK N\' JILL','Dec-13-2019','8:51 PM'),('ID12125410212019','user8','user8','user8','3','78','78','Jack','Dec-14-2019','9:56 PM'),('ID11131910192019','fd','df','d','0','5','60','Juan Tamad','Dec-18-2019','9:22 AM'),('ID3330612072019','vans','shoes','shoes','1','500','650','Juan Tamad','Dec-27-2019','9:55 AM'),('ID1213201982318','tshirt','tshirt','tshirt','1','200','250','Jack','Dec-27-2019','9:55 AM'),('ID6585410202019','sdf','sdf','sdf','1','234','4','asd','Jan-26-2020','2:49 PM');
/*!40000 ALTER TABLE `tbl_dump` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-17 21:17:05
