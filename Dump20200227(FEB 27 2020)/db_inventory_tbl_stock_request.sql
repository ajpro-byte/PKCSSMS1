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
INSERT INTO `tbl_stock_request` VALUES ('ID0104202091143','corp','corpcorp','corp','100','200','300','280',NULL,'Tiles corp.'),('ID0227202092453','tiles','description','new','30','100','120','110',NULL,'Jack'),('ID0227202092539','new','description','category','100','400','500','450','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0hôÏ÷\0\0\0sRGB\0®Îé\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0Ã\0\0ÃÇo¨d\0\0IDAT(Sc@×4µÿ÷íl?3lç˜æÀMùˆ³2»?™˜ü,ÝxøèB.X0f2Hr­åæs^[Z:ÿÌHú¿™Wø>X`¾­óÿ—v¶ïÀœ»OßÑ3ØùÁ4VpVJ®õºŒüÿ‡*j‡Íaæ]ÀÉÿÿ²­Íÿ\reÿßêèþ?.*9,	 £U¶½ßÈ#øî%\0JL_ºáÐ~(—8p@LjË]{Û‰P.*\0¹dÈpwì–Ôx®¥•pTPÔê¿÷¿¯.Nÿ/K+|_Á-´¬\0äçÕ|\"ÿLnÿ\nPnI`I\0…ãVEµ›E¤þ‹øHPÒ\Z*Ž_Ö<}îÞ\"I ë RÉs7žþŸºp&È$u 7A¥`’P.ù`‡ìQé{ _~	òûwMIu<þñ\"b˜FP<‚\"\Z„w‰¿‡*ƒ(¹·Aõ9\'õ#?36þÿÙÚ\nŒaAxTTªä\"Ÿ¡Ú!`—@ÜZ¡ Â ƒ@©´  „MÐÙªNÎZòaYû„ÿ}Æ–`ƒ@QÿÉÉáëMMg¨2L\0ŠP’…:(×€å-‹@iä5Pö‚*G€%köÊßyôvL#¶(%œN?rööÍù«÷Î\0\"00\0\0˜Q°¿U\0\0\0\0IEND®B`‚','vbn');
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
