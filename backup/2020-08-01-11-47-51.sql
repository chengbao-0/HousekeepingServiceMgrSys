-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: housekeepingservicemgrsys
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `user` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `adminID` int NOT NULL COMMENT '管理员编号',
  PRIMARY KEY (`adminID`),
  UNIQUE KEY `USER` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin_1','e10adc3949ba59abbe56e057f20f883e',1),('admin_2','e10adc3949ba59abbe56e057f20f883e',2),('admin_3','e10adc3949ba59abbe56e057f20f883e',3),('admin_4','e10adc3949ba59abbe56e057f20f883e',4);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applyforhousekeeper`
--

DROP TABLE IF EXISTS `applyforhousekeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applyforhousekeeper` (
  `user` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `ID` int NOT NULL COMMENT '编号',
  `name` varchar(8) DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') NOT NULL COMMENT '性别',
  `service` varchar(20) DEFAULT NULL COMMENT '服务类型',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `startTime` time DEFAULT NULL COMMENT '开始工作时间',
  `endTime` time DEFAULT NULL COMMENT '结束工作时间',
  `registerState` enum('审核中','未通过') NOT NULL COMMENT '审核状态',
  PRIMARY KEY (`ID`),
  KEY `service` (`service`),
  CONSTRAINT `applyforhousekeeper_ibfk_1` FOREIGN KEY (`service`) REFERENCES `service` (`service`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applyforhousekeeper`
--

LOCK TABLES `applyforhousekeeper` WRITE;
/*!40000 ALTER TABLE `applyforhousekeeper` DISABLE KEYS */;
/*!40000 ALTER TABLE `applyforhousekeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `clientID` int NOT NULL COMMENT '客户编号',
  `name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(50) DEFAULT NULL COMMENT '家庭住址',
  `paidState` enum('待支付','正常') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '付费状态',
  PRIMARY KEY (`clientID`),
  UNIQUE KEY `USER` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('client_1','a04be466e303d5c4e5029a18e8b3b27a',1,'小紫','女',NULL,NULL,'正常'),('client_2','12f1dce41fd8df8f168c77e601ac5375',2,'小兰','女',NULL,NULL,'正常'),('client_3','5fcfb36cab8af3fac6bfcfd69d1f1ef2',3,'小月','女',NULL,NULL,'正常'),('client_4','12f1dce41fd8df8f168c77e601ac5375',4,'小绿','女',NULL,NULL,'正常'),('client_5','da24ddca4acc52e464ed1a77fa537d7e',5,'小白','男',NULL,NULL,'正常');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `housekeeper`
--

DROP TABLE IF EXISTS `housekeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `housekeeper` (
  `user` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `housekeeperID` int NOT NULL COMMENT '员工编号',
  `name` varchar(8) NOT NULL COMMENT '姓名',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `service` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提供服务',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系方式',
  `avgScore` double(2,1) DEFAULT NULL COMMENT '历史评分',
  `startTime` time DEFAULT NULL COMMENT '工作开始时间',
  `endTime` time DEFAULT NULL COMMENT '工作结束时间',
  `state` enum('雇佣中','未雇佣','忙碌') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态(0-未雇佣；1-已雇佣)',
  `clientID` int DEFAULT NULL COMMENT '雇主编号',
  PRIMARY KEY (`housekeeperID`),
  KEY `service` (`service`),
  KEY `clientID` (`clientID`),
  CONSTRAINT `housekeeper_ibfk_1` FOREIGN KEY (`service`) REFERENCES `service` (`service`),
  CONSTRAINT `housekeeper_ibfk_2` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `housekeeper`
--

LOCK TABLES `housekeeper` WRITE;
/*!40000 ALTER TABLE `housekeeper` DISABLE KEYS */;
INSERT INTO `housekeeper` VALUES ('housekeeper1','c33367701511b4f6020ec61ded352059',1,'赵六','女','保健',NULL,9.5,'06:00:00','09:00:00','未雇佣',NULL),('housekeeper2','c33367701511b4f6020ec61ded352059',2,'李四','男','维修',NULL,NULL,'08:00:00','10:00:00','雇佣中',1),('housekeeper3','c33367701511b4f6020ec61ded352059',3,'王五','女','看护',NULL,5.0,'12:00:00','21:00:00','雇佣中',NULL),('housekeeper4','c33367701511b4f6020ec61ded352059',4,'张三','男','保洁',NULL,0.0,'14:00:00','19:30:00','未雇佣',NULL),('housekeeper5','c33367701511b4f6020ec61ded352059',5,'钱二','女','保健',NULL,0.0,'19:30:00','21:00:00','未雇佣',NULL),('housekeeper6','c33367701511b4f6020ec61ded352059',6,'何八','女','保姆',NULL,1.0,'06:00:00','12:00:00','未雇佣',NULL),('housekeeper7','c33367701511b4f6020ec61ded352059',7,'红桃','女','保姆',NULL,7.0,'07:00:00','16:30:00','雇佣中',NULL),('housekeeper8','c33367701511b4f6020ec61ded352059',8,'唐僧','男','看护',NULL,0.0,'08:00:00','20:00:00','未雇佣',NULL),('housekeeper9','c33367701511b4f6020ec61ded352059',9,'黑桃','女','保健',NULL,0.0,'09:00:00','10:30:00','未雇佣',NULL);
/*!40000 ALTER TABLE `housekeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `serviceID` int NOT NULL COMMENT '服务编号',
  `service` varchar(20) NOT NULL COMMENT '服务类型',
  `hourlyWage` double NOT NULL COMMENT '时薪',
  PRIMARY KEY (`serviceID`,`service`),
  UNIQUE KEY `UNIQUE` (`service`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'保健',10),(2,'维修',20),(3,'保姆',30),(4,'看护',40),(5,'保洁',50);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicerecord`
--

DROP TABLE IF EXISTS `servicerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicerecord` (
  `formID` bigint NOT NULL COMMENT '表单编号',
  `service` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
  `clientID` int NOT NULL COMMENT '客户编号',
  `housekeeperID` int NOT NULL COMMENT '家政人员编号',
  `employDate` date DEFAULT NULL COMMENT '雇佣日期',
  `startEmployTime` time DEFAULT NULL COMMENT '开始雇佣时间',
  `endEmployTime` time DEFAULT NULL COMMENT '结束雇佣时间',
  `employDays` int DEFAULT NULL COMMENT '雇佣天数',
  `totalCompensation` double DEFAULT NULL COMMENT '总计薪酬',
  `formState` enum('进行中','未付费','已付费','已结算') NOT NULL COMMENT '表单状态',
  `clientScore` double(2,1) DEFAULT NULL COMMENT '客户评分',
  `clientEvaluate` varchar(100) DEFAULT NULL COMMENT '客户评价',
  PRIMARY KEY (`formID`),
  KEY `service` (`service`),
  KEY `clientID` (`clientID`),
  KEY `housekeeperID` (`housekeeperID`),
  CONSTRAINT `servicerecord_ibfk_1` FOREIGN KEY (`service`) REFERENCES `service` (`service`),
  CONSTRAINT `servicerecord_ibfk_2` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`) ON DELETE RESTRICT,
  CONSTRAINT `servicerecord_ibfk_3` FOREIGN KEY (`housekeeperID`) REFERENCES `housekeeper` (`housekeeperID`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicerecord`
--

LOCK TABLES `servicerecord` WRITE;
/*!40000 ALTER TABLE `servicerecord` DISABLE KEYS */;
INSERT INTO `servicerecord` VALUES (1,'保健',3,1,'2016-05-12','12:00:00','16:00:00',15,550,'已结算',9.5,''),(2,'看护',1,3,'2020-07-20','12:00:00','18:00:00',14,3360,'已付费',5.0,'');
/*!40000 ALTER TABLE `servicerecord` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-01 11:47:51
