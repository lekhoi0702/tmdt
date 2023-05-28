-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: KINHDOANHIOT
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.22.04.2

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
-- Table structure for table `SANPHAM`
--

DROP TABLE IF EXISTS `SANPHAM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SANPHAM` (
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
  CONSTRAINT `FK_THUCDON_LOAITHUCUONG` FOREIGN KEY (`LOAI`) REFERENCES `LOAISP` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SANPHAM`
--

LOCK TABLES `SANPHAM` WRITE;
/*!40000 ALTER TABLE `SANPHAM` DISABLE KEYS */;
INSERT INTO `SANPHAM` VALUES (1,'Arduino Uno',1,200000,'Cái',201,'arduino-uno-r3-dip.webp',1,'san pham chua co mo ta'),(2,'CB Nhiệt độ KJ45',2,10000,'Cái',1400,'cam-bien-nhiet-do-do-am-am2305-jpeg.webp',1,'san pham chua co mo ta'),(3,'Arduino MICRO',1,200000,'Cái',200,'arduino-nano.webp',1,'san pham chua co mo ta'),(4,'ádfff',2,120000,'cái',0,'nhom-tan-nhiet-jpeg.webp',1,'san pham chua co mo ta'),(5,'ewgw',3,200000,'qwer',0,'module-nguon-12v-300ma-jpeg.webp',1,'san pham chua co mo ta'),(6,'cảm biến ánh sáng',2,30000,'10 cái',0,'cam-bien-cuong-do-anh-sang-gy-49.webp',1,'san pham chua co mo ta'),(7,'cảm biên bụi',2,5000,'cái',0,'cam-bien-tiem-can-njk-5002c.webp',1,'san pham chua co mo ta'),(8,'Khung xe',3,80000,'Cái',0,'khop-noi-banh-xe-jpeg.webp',1,'san pham chua co mo ta'),(9,'Rasbpery Pi 1',4,800000,'Cái',0,'raspberry-pi-4-8g.webp',1,'san pham chua co mo ta'),(10,'gi do',2,4000,'Cái',0,'xy-wt01.webp',1,'san pham chua co mo ta');
/*!40000 ALTER TABLE `SANPHAM` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-27 15:40:00
