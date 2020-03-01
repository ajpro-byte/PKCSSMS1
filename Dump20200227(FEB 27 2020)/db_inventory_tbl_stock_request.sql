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
-- Table structure for table `tbl_stock_request`
--

DROP TABLE IF EXISTS `tbl_stock_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_stock_request` (
  `ProductID` varchar(45) NOT NULL,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductDescription` varchar(45) DEFAULT NULL,
  `ProductCategory` varchar(45) DEFAULT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  `OriginalPrice` varchar(45) DEFAULT NULL,
  `ValuesSRP` varchar(45) DEFAULT NULL,
  `Sale` varchar(45) DEFAULT NULL,
  `Image` longblob,
  `Supplier` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_stock_request`
--

LOCK TABLES `tbl_stock_request` WRITE;
/*!40000 ALTER TABLE `tbl_stock_request` DISABLE KEYS */;
INSERT INTO `tbl_stock_request` VALUES ('ID0104202091143','corp','corpcorp','corp','100','200','300','280',NULL,'Tiles corp.'),('ID0227202092453','tiles','description','new','30','100','120','110',NULL,'Jack'),('ID0227202092539','new','description','category','100','400','500','450','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0h���\0\0\0sRGB\0���\0\0\0gAMA\0\0���a\0\0\0	pHYs\0\0�\0\0��o�d\0\0IDAT(Sc@�4�����l?3l����M���2�?���,�x��B.�X0f2Hr���s^[Z:��H���W�>X`�����v�����O��3���4VpVJ�������*j��a�]��������\re�����?.*9,	 �U����#��%\0JL_���~(�8p@Lj�]{ۉP.*\0�dȁpw���x���pTP�ꝿ���.N�/K+|_�-��\0���|\"�Ln�\nP�n�I`I\0��VE��E�����HP�\Z*�_�<}���\"�I �R�s7����p�&�$�u 7A�`�P.�`��Q�{ _~	��wMIu<���\"�b�FP<�\"\Z�w���*�(���A�9\'�#?36����\n�aAxTT��\"����!`�@�Z��� �@�� ���M����N�Z�aY���}Ɩ`�@Q�����MMg�2L\0�P��:(׀�-��@i�5P��*G�%k���y�vL#�(%�N?r�������\0\"00\0\0�Q���U\0\0\0\0IEND�B`�','vbn');
/*!40000 ALTER TABLE `tbl_stock_request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 22:05:53
