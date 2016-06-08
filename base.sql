CREATE DATABASE  IF NOT EXISTS `farmacia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `farmacia`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: farmacia
-- ------------------------------------------------------
-- Server version	5.6.17-log

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
-- Table structure for table `catalogopresentacion`
--

DROP TABLE IF EXISTS `catalogopresentacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogopresentacion` (
  `idPresentacion` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPresentacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogopresentacion`
--

LOCK TABLES `catalogopresentacion` WRITE;
/*!40000 ALTER TABLE `catalogopresentacion` DISABLE KEYS */;
INSERT INTO `catalogopresentacion` VALUES (1,'Enfusion'),(2,'Solucion'),(3,'Jarabe'),(4,'Tabletas'),(5,'Capsulas'),(6,'Unguento'),(7,'Suero'),(8,'Inyectable'),(9,'Intravenoso'),(10,'Parche');
/*!40000 ALTER TABLE `catalogopresentacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogotipo`
--

DROP TABLE IF EXISTS `catalogotipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogotipo` (
  `idTipo` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogotipo`
--

LOCK TABLES `catalogotipo` WRITE;
/*!40000 ALTER TABLE `catalogotipo` DISABLE KEYS */;
INSERT INTO `catalogotipo` VALUES (1,'ml'),(2,'mg'),(3,'l'),(4,'g'),(5,'micro(g)');
/*!40000 ALTER TABLE `catalogotipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL AUTO_INCREMENT,
  `idPaciente` int(11) DEFAULT NULL,
  `idHorario` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `activa` int(11) DEFAULT '0',
  `pagada` int(11) DEFAULT '0',
  PRIMARY KEY (`idCita`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (1,1,1,'2016-05-24','Etnan Nohara',0,0),(2,-1,2,'2016-05-24','Nad',0,0),(3,0,3,'2016-05-24','Bryan rechkoj',0,0),(4,0,4,'2016-05-24','Un chico con cita',0,0),(5,5,5,'2016-05-24','Paciente1',0,0),(6,-1,6,'2016-05-24','Paciente2',0,0),(7,5,7,'2016-05-24','Paciente',0,0),(8,5,5,'2016-05-25','Nohara-san',0,0),(9,0,1,'2016-05-26','sdghjsfhgjhfdf',0,0),(10,0,3,'2016-05-26','wolverine',0,0),(11,5,1,'2016-05-25','paciente1',0,0),(12,-1,2,'2016-05-25','paciente2',0,0),(13,0,1,'2016-05-27','bryan',0,0),(14,0,159,'2016-05-28','Noharasan',0,0),(15,0,160,'2016-05-28','un baka',0,0),(16,0,159,'2016-05-29','Etnan',0,0),(17,5,159,'2016-05-30','Etnan',0,0),(18,6,161,'2016-05-30','Nadia Rubio',0,0),(19,5,162,'2016-05-30','Etnan Nohara',0,0),(20,6,160,'2016-05-30','Nadia Rubio',0,0),(21,5,163,'2016-05-30','Etnan Nohara',0,0),(22,5,164,'2016-05-30','Etnan',0,0),(23,5,165,'2016-05-30','Etnan',0,0),(25,6,187,'2016-05-30','Nadia Rubio',0,0),(26,6,188,'2016-05-30','Nadia Rubio',0,0),(27,6,166,'2016-05-30','Nadia Rubio',0,0),(28,9,168,'2016-05-30','Bryan Recko',0,0),(29,6,167,'2016-05-30','Nadia Rubio',0,0),(30,5,170,'2016-05-30','Etnan Nohara',0,0),(31,5,160,'2016-05-31','Etnan Nohara',0,1),(32,5,159,'2016-05-31','Etnan Nohara',0,1),(33,5,162,'2016-05-31','Etnan Nohara',0,1),(34,0,159,'2016-06-09','nad',0,0),(37,5,168,'2016-05-31','Etnan Nohara',0,1),(38,5,167,'2016-05-31','Etnan Nohara',0,1),(39,6,172,'2016-05-31','Nadia Rubio',1,1),(40,6,179,'2016-05-31','Nadia Rubio',1,1),(41,9,172,'2016-06-02','Bryan Recko',0,1),(42,6,173,'2016-06-02','Nadia Rubio',0,1),(43,5,174,'2016-06-02','Etnan Nohara',0,1),(44,6,235,'2016-06-02','Nadia Rubio',0,1),(45,6,186,'2016-06-02','Nadia Rubio',0,1),(46,6,242,'2016-06-02','Nadia Rubio',0,1),(47,6,159,'2016-06-03','Nadia Rubio',2,1),(48,6,161,'2016-06-03','Nadia Rubio',0,0),(49,6,160,'2016-06-03','Nadia Rubio',0,0),(50,6,164,'2016-06-03','Nadia Rubio',2,1),(51,6,166,'2016-06-03','Nadia Rubio',2,1),(52,0,159,'2016-06-07','Ulices',0,0),(53,0,161,'2016-06-07','nad',0,0),(54,0,160,'2016-06-07','etnan',0,0),(55,0,162,'2016-06-07','bryan',0,0),(56,6,176,'2016-06-03','Nadia Rubio',1,1),(57,0,159,'2016-06-01','asdfg',0,0),(59,5,160,'2016-06-05','Etnan Nohara',0,1),(60,5,175,'2016-06-05','Etnan Nohara',2,1),(61,0,159,'2016-06-05','asd',0,0),(63,5,159,'2016-06-08','Etnan Nohara',0,0),(65,5,159,'2016-06-06','Etnan Nohara',0,1);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compras` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `fecha` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCompra`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,1500,'consulta','2016-05-28 10:53:28'),(2,150,'medicamentos','2016-05-28 13:14:54'),(3,1500,'consulta','2016-05-31 13:28:42'),(4,1500,'consulta','2016-05-31 13:47:10'),(5,1500,'consulta','2016-05-31 13:49:25'),(6,1500,'consulta','2016-05-31 13:50:58'),(7,1500,'consulta','2016-05-31 13:54:04'),(8,1500,'consulta','2016-05-31 13:55:52'),(9,1500,'consulta','2016-05-31 13:57:21'),(10,1500,'consulta','2016-05-31 15:39:42'),(11,1500,'consulta','2016-05-31 15:41:22'),(12,0,'medicamentos','2016-06-03 09:19:37'),(13,100,'medicamentos','2016-06-03 14:32:29'),(14,1300,'medicamentos','2016-06-06 17:50:36'),(15,1500,'consulta','2016-06-06 18:02:17'),(16,1000,'vacunas','2016-06-06 18:03:16'),(17,7200,'medicamentos','2016-06-06 18:04:01'),(18,1500,'consulta','2016-06-06 19:57:25'),(19,0,'medicamentos','2016-06-06 19:57:43'),(20,1000,'vacunas','2016-06-06 19:57:58');
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `idConfig` int(11) NOT NULL,
  `consulta` double DEFAULT NULL,
  `vacunas` double DEFAULT NULL,
  PRIMARY KEY (`idConfig`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (0,1000,300);
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultorio`
--

DROP TABLE IF EXISTS `consultorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultorio` (
  `idConsultorio` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`idConsultorio`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultorio`
--

LOCK TABLES `consultorio` WRITE;
/*!40000 ALTER TABLE `consultorio` DISABLE KEYS */;
INSERT INTO `consultorio` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `consultorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horario`
--

DROP TABLE IF EXISTS `horario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario` (
  `idHorario` int(11) NOT NULL AUTO_INCREMENT,
  `idMedico` int(11) DEFAULT NULL,
  `idConsultorio` int(11) DEFAULT NULL,
  `inicio` time DEFAULT NULL,
  `fin` time DEFAULT NULL,
  PRIMARY KEY (`idHorario`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario`
--

LOCK TABLES `horario` WRITE;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` VALUES (159,6,1,'06:00:00','06:30:00'),(160,6,1,'06:30:00','07:00:00'),(161,6,1,'07:00:00','07:30:00'),(162,6,1,'07:30:00','08:00:00'),(163,6,1,'08:00:00','08:30:00'),(164,6,1,'08:30:00','09:00:00'),(165,6,1,'09:00:00','09:30:00'),(166,6,1,'09:30:00','10:00:00'),(167,6,1,'10:00:00','10:30:00'),(168,6,1,'10:30:00','11:00:00'),(169,6,1,'11:00:00','11:30:00'),(170,6,1,'11:30:00','12:00:00'),(171,6,1,'12:00:00','12:30:00'),(172,6,1,'12:30:00','13:00:00'),(173,6,1,'13:00:00','13:30:00'),(174,6,1,'13:30:00','14:00:00'),(175,6,1,'14:00:00','14:30:00'),(176,6,1,'14:30:00','15:00:00'),(177,6,1,'15:00:00','15:30:00'),(178,6,1,'15:30:00','16:00:00'),(179,6,1,'16:00:00','16:30:00'),(180,6,1,'16:30:00','17:00:00'),(181,6,1,'17:00:00','17:30:00'),(182,6,1,'17:30:00','18:00:00'),(183,6,1,'18:00:00','18:30:00'),(184,6,1,'18:30:00','19:00:00'),(185,6,1,'19:00:00','19:30:00'),(186,6,1,'19:30:00','20:00:00'),(187,7,2,'06:00:00','06:30:00'),(188,7,2,'06:30:00','07:00:00'),(189,7,2,'07:00:00','07:30:00'),(190,7,2,'07:30:00','08:00:00'),(191,7,2,'08:00:00','08:30:00'),(192,7,2,'08:30:00','09:00:00'),(193,7,2,'09:00:00','09:30:00'),(194,7,2,'09:30:00','10:00:00'),(195,7,2,'10:00:00','10:30:00'),(196,7,2,'10:30:00','11:00:00'),(197,7,2,'11:00:00','11:30:00'),(198,7,2,'11:30:00','12:00:00'),(199,7,2,'12:00:00','12:30:00'),(200,7,2,'12:30:00','13:00:00'),(201,7,2,'13:00:00','13:30:00'),(202,7,2,'13:30:00','14:00:00'),(203,7,2,'14:00:00','14:30:00'),(204,7,2,'14:30:00','15:00:00'),(205,7,2,'15:00:00','15:30:00'),(206,7,2,'15:30:00','16:00:00'),(207,7,2,'16:00:00','16:30:00'),(208,7,2,'16:30:00','17:00:00'),(209,7,2,'17:00:00','17:30:00'),(210,7,2,'17:30:00','18:00:00'),(211,7,2,'18:00:00','18:30:00'),(212,7,2,'18:30:00','19:00:00'),(213,7,2,'19:00:00','19:30:00'),(214,7,2,'19:30:00','20:00:00');
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medico` (
  `idMedico` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `Usuario` varchar(45) DEFAULT NULL,
  `Cont` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idMedico`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES (6,'Etnan','Lopez','Neurologia','2015630250','Etnan','630260'),(7,'Nadia','Rubio','Epidemiologia','2015630591','Nadia','630591');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `idPaciente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellidos` varchar(45) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `curp` varchar(45) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `eCronicas` varchar(200) DEFAULT NULL,
  `eFamiliares` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idPaciente`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (0,'-','-',0,'-','-','-','-'),(18,'Etnan','Lopez Torres',20,'LOTE960213HDFPRT05','ESCOM','frikismo','Diabetes');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(70) NOT NULL,
  `Descripcion` varchar(150) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `idTipo` int(11) NOT NULL,
  `idPresentacion` int(11) NOT NULL,
  `precio` double NOT NULL,
  `Stock` int(11) NOT NULL,
  `Contenido` int(11) NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (10,'Ibuprofeno','Tableta o cápsula',2000,2,4,71,200,55),(11,'Metamizol','Comprimido',500,2,5,50,150,67),(12,'Paracetamol','Tableta',500,2,4,40,150,48),(13,'Paracetamol','intravenoso',500,2,9,45,150,27),(14,'Nalbufina','3 ampolletas',500,1,8,40,150,26);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recepcionista`
--

DROP TABLE IF EXISTS `recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recepcionista` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Usuario` varchar(45) NOT NULL,
  `Contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recepcionista`
--

LOCK TABLES `recepcionista` WRITE;
/*!40000 ALTER TABLE `recepcionista` DISABLE KEYS */;
INSERT INTO `recepcionista` VALUES (-1,'NaMYY','Admin','administrar'),(1,'Nadia','Recepcion','recepcionista'),(2,'Diana','Diana','kim');
/*!40000 ALTER TABLE `recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `idVendedor` varchar(9) NOT NULL,
  `Nombres` varchar(60) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Usuario` varchar(45) NOT NULL,
  `Contrasena` varchar(45) NOT NULL,
  PRIMARY KEY (`idVendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES ('-1','Bryan','recko','Cajero','caja'),('1','Etnan','Nohara','Farmaceutico','farmacia'),('2','Jonathan','Martinez','Jonathan','bryan');
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `idProducto` int(11) NOT NULL,
  `idVendedor` int(11) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Cantidad` int(11) NOT NULL,
  KEY `fk_Producto_has_Vendedor_Vendedor1_idx` (`idVendedor`),
  KEY `fk_Producto_has_Vendedor_Producto_idx` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (2,1,'2016-05-27 00:00:00',3),(4,1,'2016-05-27 00:00:00',2),(5,1,'2016-05-27 00:00:00',3),(2,1,'2016-05-27 00:00:00',3),(3,1,'2016-05-27 00:00:00',3),(4,1,'2016-05-27 00:00:00',3),(5,1,'2016-05-27 00:00:00',3),(6,1,'2016-05-27 00:00:00',3),(7,1,'2016-05-27 00:00:00',3),(3,1,'2016-05-27 00:00:00',3),(4,1,'2016-05-27 00:00:00',3),(6,1,'2016-05-27 00:00:00',3),(3,1,'2016-05-27 00:00:00',4),(2,1,'2016-05-27 00:00:00',2),(5,1,'2016-05-27 00:00:00',1),(3,1,'2016-05-27 00:00:00',2),(5,1,'2016-05-27 00:00:00',2),(4,1,'2016-05-27 00:00:00',2),(3,1,'2016-05-27 00:00:00',2),(7,1,'2016-05-27 00:00:00',17),(7,1,'2016-05-28 00:00:00',7),(7,1,'2016-05-28 00:00:00',8),(7,1,'2016-05-28 00:00:00',8),(4,1,'2016-05-28 00:00:00',3),(2,1,'2016-05-28 00:00:00',3),(8,1,'2016-05-28 00:00:00',3),(3,1,'2016-05-28 00:00:00',3),(7,1,'2016-05-28 00:00:00',3),(6,1,'2016-05-28 00:00:00',3),(3,1,'2016-05-29 00:00:00',4),(6,1,'2016-05-29 00:00:00',4),(3,1,'2016-05-30 00:00:00',2),(7,1,'2016-05-30 00:00:00',4),(6,1,'2016-05-30 00:00:00',2),(3,1,'2016-05-31 00:00:00',3),(6,1,'2016-05-31 00:00:00',3),(3,1,'2016-06-05 00:00:00',2),(5,1,'2016-06-05 00:00:00',2),(6,1,'2016-06-05 00:00:00',2),(9,1,'2016-06-06 00:00:00',4),(8,1,'2016-06-06 00:00:00',4),(7,1,'2016-06-06 00:00:00',4),(6,1,'2016-06-06 00:00:00',4),(7,1,'2016-06-06 00:00:00',4),(8,1,'2016-06-06 00:00:00',4),(6,1,'2016-06-06 00:00:00',3),(4,1,'2016-06-06 00:00:00',3),(2,1,'2016-06-06 00:00:00',3),(4,1,'2016-06-06 00:00:00',3),(6,1,'2016-06-06 00:00:00',3),(7,1,'2016-06-06 00:00:00',3),(7,1,'2016-06-06 00:00:00',3),(5,1,'2016-06-06 00:00:00',3),(7,1,'2016-06-06 00:00:00',3);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-06 22:25:59
