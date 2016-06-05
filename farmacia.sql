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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'Analgesico','Quita el dolor XD',10,2,2,120,4,4),(3,'Paracetamol','Si quiere paracetamol dale XD',9,5,7,1200,16,10),(4,'Dultin','No se que sea',5,3,2,13,11,7),(5,'paracetamol','inyecciones',10,1,8,44,18,300),(6,'nMedic','Imagino que sera algun medicamento',6,1,3,1200,10,6);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
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
INSERT INTO `vendedor` VALUES ('1','Etnan','Nohara','Noharasan','JuviaKawaii');
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
INSERT INTO `ventas` VALUES (2,1,'2016-05-10 00:00:00',3),(3,1,'2016-05-10 00:00:00',4),(4,1,'2016-05-10 00:00:00',7),(5,1,'2016-05-10 00:00:00',2),(6,1,'2016-05-10 00:00:00',2),(2,1,'2016-05-10 00:00:00',3),(4,1,'2016-05-10 00:00:00',7),(5,1,'2016-05-10 00:00:00',2);
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

-- Dump completed on 2016-05-10 21:13:17
