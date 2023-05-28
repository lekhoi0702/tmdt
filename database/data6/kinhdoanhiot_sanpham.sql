-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: kinhdoanhiot
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `TEN` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `LOAI` bigint NOT NULL,
  `GIA` int NOT NULL,
  `DONVITINH` varchar(45) NOT NULL DEFAULT 'Cái',
  `SOLUONGTON` int NOT NULL DEFAULT '0',
  `ICON` varchar(255) NOT NULL DEFAULT 'logo.webp',
  `TRANGTHAI` int NOT NULL DEFAULT '0',
  `MOTA` text,
  PRIMARY KEY (`ID`),
  KEY `FK_THUCDON_LOAITHUCUONG` (`LOAI`),
  CONSTRAINT `FK_THUCDON_LOAITHUCUONG` FOREIGN KEY (`LOAI`) REFERENCES `loaisp` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'Arduino Uno',1,200000,'Cái',201,'logo.webp',1,NULL),(2,'CB Nhiệt độ KJ45',2,10000,'Cái',1400,'logo.webp',1,NULL),(3,'Arduino MICRO',1,200000,'Cái',200,'arduino-nano.webp',1,NULL),(4,'ádfff',1,120000,'cái',0,'gi do',1,NULL),(5,'ewgw',1,200000,'qwer',0,'gi do',1,NULL),(6,'cảm biến ánh sáng',2,30000,'10 cái',0,'gi do',1,NULL),(7,'cảm biên bụi',2,5000,'cái',0,'gi do',1,NULL),(8,'Khung xe',3,80000,'Cái',0,'logo.webp',1,NULL),(9,'Rasbpery Pi 1',1,800000,'Cái',0,'raspberry-pi-4-8g.webp',1,NULL),(10,'gi do',1,4000,'Cái',0,'xy-wt01.webp',1,NULL),(11,'Bộ điều khiển ánh sáng thông minh Philips Hue',1,100000,'cái',100,'560uf35v.webp',1,NULL),(12,'Garmin GPSMAP 64s Handheld GPS Navigator',9,2000,'cái',0,'download.webp',1,NULL),(13,'điện trở sunt',3,2000,'cái',0,'dien-tro-shunt-10mr0-01r-jpeg-jpeg.webp',1,NULL),(14,'biến trở tinh chỉnh',2,10000,'cái',0,'bien-tro-tinh-chinh-ngang-3296x-jpeg.webp',1,NULL),(15,'de ra chan',1,500000,'cái',0,'de-ra-chan-nrf24l01.webp',1,NULL),(16,'lcd',4,10000,'cái',0,'lcd-1602.webp',1,NULL),(17,'mach nguon',1,3000,'cái',0,'mach-nguon-xl6009.webp',1,NULL),(18,'cảm biến nhiệt độ độ ẩm',2,1000000,'cái',0,'cam-bien-do-nhiet-do-do-am-aht10.webp',1,NULL),(19,'buzzer',7,200000,'cái',0,'buzzer-jpeg.webp',1,NULL);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-08 17:20:15
