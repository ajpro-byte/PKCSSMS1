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
-- Table structure for table `tbl_supplier`
--

DROP TABLE IF EXISTS `tbl_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_supplier` (
  `SupplierID` varchar(45) NOT NULL,
  `Supplier` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `ContactNumber` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Remarks` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_supplier`
--

LOCK TABLES `tbl_supplier` WRITE;
/*!40000 ALTER TABLE `tbl_supplier` DISABLE KEYS */;
INSERT INTO `tbl_supplier` VALUES ('ID0103202041932','Tiles corp.','gensan','23465','sdfsdf','sdfsddd','Active'),('ID1206201961530','Juan Tamad','Brgy. Masigasig','+63934377535366','JuanTamad@gmail.com','Cann Goods, Soft/Hard Drinks','Inactive'),('ID1206201961819','Jack','Mabuhay ','2345678','asdfghj','asdfghjhjk','Active'),('ID1206201963230','asd','asdsa','234','2342343','asdasdasd','Active'),('ID1206201963843','asd','asd','123','2322','hgj','Active'),('ID1206201964128','dyd','ytu','567','567','sdf','Active'),('ID1206201964229','vbn','vbn','456456','456456','gfghfghf','Active'),('ID1206201965035','JACK N\' JILL','National High Way','+2467234234','asdasd','foods/drinks','Active'),('ID1207201932834','kcc','gensan','9876543456789','email','remarks','Active'),('[Click To Auto Generate ID]','','','','','','Active');
/*!40000 ALTER TABLE `tbl_supplier` ENABLE KEYS */;
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
