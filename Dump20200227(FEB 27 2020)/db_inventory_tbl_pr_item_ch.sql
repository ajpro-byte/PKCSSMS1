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
-- Table structure for table `tbl_pr_item_ch`
--

DROP TABLE IF EXISTS `tbl_pr_item_ch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pr_item_ch` (
  `PrNumber` varchar(45) DEFAULT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `ProductCategory` varchar(45) DEFAULT NULL,
  `Units` varchar(45) DEFAULT NULL,
  `Cost` varchar(45) DEFAULT NULL,
  `Amount` varchar(45) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL,
  `Time` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pr_item_ch`
--

LOCK TABLES `tbl_pr_item_ch` WRITE;
/*!40000 ALTER TABLE `tbl_pr_item_ch` DISABLE KEYS */;
INSERT INTO `tbl_pr_item_ch` VALUES ('PR-0094109','g','user','d','2.0','1212','2424.0','Feb-25-2020','9:41:48'),('PR-0094109','dfg','df','user','100.0','5','500.0','Feb-25-2020','9:42:06'),('PR-00110257','req','asd','reqw','2.0','123','246.0','Feb-25-2020','11:03:55'),('PR-00110257','user4','user4','d','23.0','412','9476.0','Feb-25-2020','11:04:07'),('PR-00110257','prd','shoes','user4','13.0','312','4056.0','Feb-25-2020','11:04:36'),('PR-0010626','dfg','description','req','2.0','123','246.0','Feb-25-2020','1:07:05'),('PR-0010626','user4','dfg','user1','23.0','1223','28129.0','Feb-25-2020','1:07:14'),('PR-0010626','prd','user8','user1','42.0','122','5124.0','Feb-25-2020','1:07:26'),('PR-0010626','sdf','df','tshirt','213.0','21','4473.0','Feb-25-2020','1:07:34'),('PR-0020352','reqs','ert','reqw','1.0','21','21.0','Feb-25-2020','2:04:16'),('PR-0020352','dfg','asd','user4','123.0','34','4182.0','Feb-25-2020','2:04:25'),('PR-0020352','user7','user1','prd','1.0','2','2.0','Feb-25-2020','2:04:32'),('PR-0020437','asd','user7','stock','2.0','1','2.0','Feb-25-2020','2:05:00'),('PR-0020437','ghj','tshirt','tiles','22.0','213','4686.0','Feb-25-2020','2:05:08'),('','tiles','tiles','tiles','1.0','2','2.0','Feb-25-2020','6:15:37'),('PR-0090435','tiles','description','dfg','12.0','221','2652.0','Feb-25-2020','9:05:09'),('PR-0090435','sad','description','dfg','12.0','311','3732.0','Feb-25-2020','9:05:19'),('PR-0085021','reqs','req','req','123.0','12','1476.0','Feb-27-2020','8:50:54'),('PR-0085021','user1','user8','dfg','12.0','221','2652.0','Feb-27-2020','8:51:06'),('PR-0092051','reqs','dfg','category','212.0','1112','235744.0','Feb-27-2020','9:21:23');
/*!40000 ALTER TABLE `tbl_pr_item_ch` ENABLE KEYS */;
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
