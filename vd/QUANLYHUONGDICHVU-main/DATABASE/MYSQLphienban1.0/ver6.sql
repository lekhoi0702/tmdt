CREATE DATABASE  IF NOT EXISTS `QUANLYQUANPHUCLONG` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `QUANLYQUANPHUCLONG`;
-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: QUANLYQUANPHUCLONG
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.22.04.1

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
-- Table structure for table `BAN`
--

DROP TABLE IF EXISTS `BAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BAN` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `SOGHE` int NOT NULL,
  `LOAI` bigint NOT NULL,
  `TINHTRANG` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_BAN_LOAIBAN` (`LOAI`),
  CONSTRAINT `FK_BAN_LOAIBAN` FOREIGN KEY (`LOAI`) REFERENCES `LOAIBAN` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BAN`
--

LOCK TABLES `BAN` WRITE;
/*!40000 ALTER TABLE `BAN` DISABLE KEYS */;
INSERT INTO `BAN` VALUES (1,3,2,1),(2,5,3,1),(3,1,1,1),(4,1,4,1),(7,2,3,1),(8,10,3,1),(9,10,3,1),(10,20,5,1),(11,10,5,1),(12,3,3,0),(13,3,2,3),(14,0,1,0);
/*!40000 ALTER TABLE `BAN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHIPHI`
--

DROP TABLE IF EXISTS `CHIPHI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CHIPHI` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `TENCHIPHI` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `SOLUONG` int NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `GIAMOIDONVI` int NOT NULL,
  `LOAI` varchar(3) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DONVI` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NHACUNGCAP` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `GHICHU` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NVTAO` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CHIPHI_NHANVIEN` (`NVTAO`),
  CONSTRAINT `FK_CHIPHI_NHANVIEN` FOREIGN KEY (`NVTAO`) REFERENCES `NHANVIEN` (`MANV`),
  CONSTRAINT `CK__CHIPHI__SOLUONG__607251E5` CHECK ((`SOLUONG` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHIPHI`
--

LOCK TABLES `CHIPHI` WRITE;
/*!40000 ALTER TABLE `CHIPHI` DISABLE KEYS */;
INSERT INTO `CHIPHI` VALUES (1,'BẢO TRÌ',1,'2022-05-07',500000,'VT','CÁI','N;ISAOPHUONGNAM','qer',1),(2,'CÀ PHÊ RANG',10,'2022-05-05',30000,'NL','KG',NULL,NULL,2),(3,'TÁCH SỨ',5,'2022-05-05',20000,'VT','CÁI',NULL,NULL,2),(4,'TÁCH SỨ',3,'2022-05-05',20000,'VT','CÁI',NULL,'NV LÀM VỠ',3),(6,'trả lương Thuận',1,'2022-05-06',50000,'NL','người','','',1),(7,'trả lương Duong',1,'2022-05-05',50000,'K','người','','',1),(8,'trả lương Nam',1,'2022-05-05',50000,'K','NGƯỜI','','',1),(9,'Sữa Đặc Ngôi Sao',10,'2022-05-05',16000,'NL','CÁI','N;ISAOPHUONGNAM','',1);
/*!40000 ALTER TABLE `CHIPHI` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHITIETHD`
--

DROP TABLE IF EXISTS `CHITIETHD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CHITIETHD` (
    
  `MAHD` bigint NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `SOLUONG` int NOT NULL,
  `TONGTIEN` int NOT NULL DEFAULT '0',
`ID` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NONCLUSTERED` (`MAHD`,`MASP`),
  KEY `FK_CHITIETHD_THUCDON` (`MASP`),
  CONSTRAINT `FK_CHITIETHD_HOADON` FOREIGN KEY (`MAHD`) REFERENCES `HOADON` (`ID`),
  CONSTRAINT `FK_CHITIETHD_THUCDON` FOREIGN KEY (`MASP`) REFERENCES `THUCDON` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHITIETHD`
--
LOCK TABLES `CHITIETHD` WRITE;

/*!40000 ALTER TABLE `CHITIETHD` DISABLE KEYS */;
INSERT INTO `CHITIETHD` VALUES (9,'BMT',1,15000,1),(10,'BMT',1,15000,2),(10,'KMC',1,30000,3),(11,'BMT',1,15000,4),(12,'CPD',1,20000,5),(14,'BMT',1,15000,6),(15,'BMT',1,15000,7),(15,'CPS',1,25000,8),(16,'BMT',1,15000,9),(17,'CPS',1,25000,10),(18,'BMT',1,15000,11),(19,'BMT',1,15000,12),(19,'CPD',1,20000,13),(19,'KMC',2,30000,14),(20,'CPD',2,40000,15),(21,'KMC',4,120000,16),(22,'BS',1,20000,17),(23,'BMT',1,15000,18),(24,'BMT',1,15000,19),(26,'CPD',1,20000,20),(28,'BMT',1,15000,21),(29,'BS',1,20000,22),(30,'BS',1,20000,23),(31,'BS',1,20000,24),(32,'BMT',2,15000,25),(33,'BMT',1,15000,26),(34,'BS',1,20000,27),(34,'KTC',1,10000,28),(35,'BMT',1,15000,29),(36,'BB',1,15000,30),(37,'BB',1,15000,31),(38,'BB',1,15000,32),(39,'BS',1,20000,33),(40,'BS',1,20000,34),(41,'BS',1,20000,35),(42,'BB',1,15000,36),(43,'CPD',2,40000,37),(43,'CPS',3,75000,38),(44,'BMT',10,150000,39),(44,'BS',10,300000,40);
/*!40000 ALTER TABLE `CHITIETHD` ENABLE KEYS */;

UNLOCK TABLES;
--
-- Table structure for table `CHUCVU`
--

DROP TABLE IF EXISTS `CHUCVU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CHUCVU` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `TENCHUCVU` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHUCVU`
--

LOCK TABLES `CHUCVU` WRITE;
/*!40000 ALTER TABLE `CHUCVU` DISABLE KEYS */;
INSERT INTO `CHUCVU` VALUES (1,'ADMIN'),(2,'Nhan Vien Pha Che'),(3,'Nhan Vien Phuc Vu'),(4,'Nhan Vien IT'),(5,'Tap Vu'),(6,'Bao Ve');
/*!40000 ALTER TABLE `CHUCVU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HOADON`
--

DROP TABLE IF EXISTS `HOADON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HOADON` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NGAYTHUCHIEN` datetime NOT NULL DEFAULT (curdate()),
  `BAN` bigint NOT NULL DEFAULT '1',
  `NVTHUCHIEN` bigint DEFAULT NULL,
  `TINHTRANG` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_HOADON_BAN` (`BAN`),
  KEY `FK_HOADON_NHANVIEN` (`NVTHUCHIEN`),
  CONSTRAINT `FK_HOADON_BAN` FOREIGN KEY (`BAN`) REFERENCES `BAN` (`ID`),
  CONSTRAINT `FK_HOADON_NHANVIEN` FOREIGN KEY (`NVTHUCHIEN`) REFERENCES `NHANVIEN` (`MANV`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HOADON`
--

LOCK TABLES `HOADON` WRITE;
/*!40000 ALTER TABLE `HOADON` DISABLE KEYS */;
INSERT INTO `HOADON` VALUES (9,'2022-04-24 01:20:13',2,2,1),(10,'2022-04-24 08:51:19',1,2,1),(11,'2022-04-24 08:57:57',1,2,1),(12,'2022-04-24 08:58:54',2,2,1),(13,'2022-04-25 09:36:09',1,3,1),(14,'2022-04-25 09:43:08',1,3,1),(15,'2022-05-01 20:40:30',3,3,1),(16,'2022-05-01 20:40:55',1,3,1),(17,'2022-05-05 08:22:54',2,2,1),(18,'2022-05-05 08:23:01',7,2,1),(19,'2022-05-05 08:23:07',8,2,1),(20,'2022-05-05 08:23:11',9,2,1),(21,'2022-05-05 08:23:16',10,2,1),(22,'2022-05-08 09:35:18',1,5,1),(23,'2022-05-08 10:59:20',1,5,1),(24,'2022-05-09 09:41:38',1,2,1),(25,'2022-05-09 09:42:30',1,2,1),(26,'2022-05-09 09:48:58',4,2,1),(27,'2022-05-09 09:49:16',4,2,1),(28,'2022-05-09 09:51:13',1,2,1),(29,'2022-05-09 09:53:04',8,2,1),(30,'2022-05-09 09:55:49',3,2,1),(31,'2022-05-09 09:55:58',3,2,1),(32,'2022-05-10 22:37:23',1,2,1),(33,'2022-05-10 23:16:58',4,3,1),(34,'2022-05-11 21:28:45',7,2,1),(35,'2022-05-11 21:29:01',10,2,1),(36,'2022-05-13 17:25:06',1,5,1),(37,'2022-05-14 13:51:31',1,5,1),(38,'2022-05-14 13:51:51',8,5,1),(39,'2022-05-14 13:51:59',2,5,1),(40,'2022-05-16 20:27:36',2,5,1),(41,'2022-05-16 22:09:15',12,5,1),(42,'2022-05-16 22:11:34',7,5,1),(43,'2022-05-20 20:04:12',8,5,1),(44,'2022-05-20 20:04:12',1,4,1),(45,'2022-06-20 00:00:00',2,2,1);
/*!40000 ALTER TABLE `HOADON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOAIBAN`
--

DROP TABLE IF EXISTS `LOAIBAN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOAIBAN` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `TENLOAI` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `GIADAT` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOAIBAN`
--

LOCK TABLES `LOAIBAN` WRITE;
/*!40000 ALTER TABLE `LOAIBAN` DISABLE KEYS */;
INSERT INTO `LOAIBAN` VALUES (1,'THƯỜNG',10000),(2,'ĐÔI',20000),(3,'LỚN',50000),(4,'KHÔNG HÚT THUỐC',20000),(5,'VIP',100000),(6,'SIÊU LỚN',100000);
/*!40000 ALTER TABLE `LOAIBAN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOAITHUCUONG`
--

DROP TABLE IF EXISTS `LOAITHUCUONG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `LOAITHUCUONG` (
  `ID` varchar(10) NOT NULL,
  `TENLOAI` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DONVI` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOAITHUCUONG`
--

LOCK TABLES `LOAITHUCUONG` WRITE;
/*!40000 ALTER TABLE `LOAITHUCUONG` DISABLE KEYS */;
INSERT INTO `LOAITHUCUONG` VALUES ('B','BÁNH','CÁI'),('CFP','CÀ PHÊ PHIN','LY'),('DC','ĐANG CHỌN','ĐANG CHỌN'),('FZ','FREEZE','LY'),('GH','GIAO HÀNG TỪ XA','SP'),('K','KHÁC','SP'),('NN','NƯỚC NGỌT','CHAI/LON'),('T','TRÀ','LY');
/*!40000 ALTER TABLE `LOAITHUCUONG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NHANVIEN`
--

DROP TABLE IF EXISTS `NHANVIEN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NHANVIEN` (
  `MANV` bigint NOT NULL AUTO_INCREMENT,
  `HOTEN` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `NGAYSINH` date NOT NULL,
  `GIOITINH` bit(1) NOT NULL DEFAULT b'1',
  `LUONG` int NOT NULL,
  `SDT` varchar(10) NOT NULL,
  `CMND` varchar(15) NOT NULL,
  `DIACHI` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `NGAYVAOLAM` date NOT NULL DEFAULT (curdate()),
  `DANGHI` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`MANV`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NHANVIEN`
--

LOCK TABLES `NHANVIEN` WRITE;
/*!40000 ALTER TABLE `NHANVIEN` DISABLE KEYS */;
INSERT INTO `NHANVIEN` VALUES (1,'NHAN','2003-03-21',_binary '\0',1000000,'0121275555','45460540654','11 NGUYỄN ĐÌNH CHIỂU','0011-07-13',_binary '\0'),(2,'NGUYỄN LUNG LINH','2001-05-29',_binary '',160000,'0308156570','7084065406440','244 Lê Văn Việt','0013-06-07',_binary ''),(3,'NGUYỄN TRẦN ĐỨC THUẬN','0007-07-25',_binary '',60000,'030815654 ','7084065406440','55 NGUYỄN VĂN THỦ','0007-07-13',_binary ''),(4,'ĐINH NHO NAM','0025-07-07',_binary '',60000,'03453454  ','70840634440','55 NGUYỄN VĂN THỦ','0011-09-09',_binary '\0'),(5,'TRẦN VỚ VẨN','1977-06-03',_binary '',80000,'0308156543','7084065240','97 MAN THIỆN','2022-01-01',_binary '\0'),(6,'HUỲNH NGỌC DƯƠNG','2001-01-01',_binary '',60000,'23415654  ','7084065406440','55 NGUYỄN VĂN THỦ','2021-06-06',_binary '\0'),(7,'NGUYỄN Long Lanh','2022-04-21',_binary '\0',999999,'123123','34','33 ert','2022-04-21',_binary '\0'),(8,'NGUYỄN LUNG LINh','2022-04-21',_binary '',123,'123','34','33 ert','2022-04-21',_binary '\0'),(9,'NGUYỄN LUNG LINh','2022-04-21',_binary '',123,'123','34','33 ert','2022-04-21',_binary '\0'),(13,'Mộng Mơ','2022-05-01',_binary '\0',123000,'0308156570','13123123123','244 LÊ VĂN VIỆT 2','2022-05-05',_binary '\0'),(16,'Đức Thuận','2001-02-11',_binary '\0',123010,'0308152234','7084065406443','2323 nguyễn trường tộ','2022-05-12',_binary '\0'),(17,'thuan','2002-01-01',_binary '',1230002,'0932323234','3489787874654','23 nguyện ty','2022-05-06',_binary '\0'),(18,'thuan 3','2001-01-16',_binary '',2342000,'3434343412','70840654064','33 ert','2022-05-05',_binary ''),(19,'NGUYỄN Lóng Lánh long lanh','2002-02-21',_binary '\0',1230000,'0308156570','708406540','2323 nguyễn trường tộ','2022-05-04',_binary ''),(23,'Bad girl','1999-02-01',_binary '',9000000,'0909232323','12345678910','123 nguyen tu q2','2022-02-01',_binary ''),(24,'good boy','1999-02-01',_binary '',9000000,'0909232323','123456756410','123 nguyen tu q2','2022-02-01',_binary '\0');
/*!40000 ALTER TABLE `NHANVIEN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `THUCDON`
--

DROP TABLE IF EXISTS `THUCDON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `THUCDON` (
  `ID` varchar(10) NOT NULL,
  `TEN` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `LOAI` varchar(10) NOT NULL,
  `GIA` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_THUCDON_LOAITHUCUONG` (`LOAI`),
  CONSTRAINT `FK_THUCDON_LOAITHUCUONG` FOREIGN KEY (`LOAI`) REFERENCES `LOAITHUCUONG` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `THUCDON`
--

LOCK TABLES `THUCDON` WRITE;
/*!40000 ALTER TABLE `THUCDON` DISABLE KEYS */;
INSERT INTO `THUCDON` VALUES ('BB','bánh bao','B',15000),('BMT','BÁNH MÌ THỊT','B',15000),('BS','Bạc sỉu','CFP',20000),('CPD','Cà Phê Đen','CFP',20000),('CPS','Cà Phê Sữa','CFP',25000),('KMC','KEM MATCHA','FZ',30000),('KTC','BÁNH MÌ THỊT','B',15000);
/*!40000 ALTER TABLE `THUCDON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERTB`
--

DROP TABLE IF EXISTS `USERTB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `USERTB` (
  `USERNAME` varchar(20) NOT NULL,
  `PASSWD` varchar(50) NOT NULL,
  `ID` bigint NOT NULL,
  `ROLEID` bigint NOT NULL,
  `STATUS` int NOT NULL,
  `EMAIL` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'n19dccn000@studen.ptithcm.edu.vn',
  `ICON` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'lo_highland.png',
  UNIQUE KEY `ID` (`ID`),
  KEY `FK_USERTB_CHUCVU` (`ROLEID`),
  KEY `FK_USERTB_NHANVIEN` (`ID`),
  CONSTRAINT `FK_USERTB_CHUCVU` FOREIGN KEY (`ROLEID`) REFERENCES `CHUCVU` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `FK_USERTB_NHANVIEN` FOREIGN KEY (`ID`) REFERENCES `NHANVIEN` (`MANV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERTB`
--

LOCK TABLES `USERTB` WRITE;
/*!40000 ALTER TABLE `USERTB` DISABLE KEYS */;
INSERT INTO `USERTB` VALUES ('admin','123',1,1,1,'n19dccn203@student.ptithcm.edu.vn','admin2022-05-13_083551.png'),('nhanvien1','123',2,2,1,'n19dccn113@student.ptithcm.edu.vn','nhanvien1ov_pc_mypage_CD00285_b.b6d9543cdb7deadb'),('nhanvien2','123',3,3,1,'n19dccn043@student.ptithcm.edu.vn','lo;_highland.png'),('nhanvien22','123',4,2,1,'meolonvameocon12@gmail.com','1'),('nhanvien3','123',5,2,1,'meolonvameocon@gmail.com','nhanvien32022-05-13_083928.png');
/*!40000 ALTER TABLE `USERTB` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-28 11:24:00
