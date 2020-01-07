CREATE DATABASE  IF NOT EXISTS `restaurant_system` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restaurant_system`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant_system
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(200) NOT NULL COMMENT '加密:密码+盐',
  `salt` varchar(200) NOT NULL COMMENT '盐',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_setting`
--

DROP TABLE IF EXISTS `base_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `base_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) NOT NULL COMMENT '创建时间戳\n',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站基本设置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_setting`
--

LOCK TABLES `base_setting` WRITE;
/*!40000 ALTER TABLE `base_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column`
--

DROP TABLE IF EXISTS `column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '栏目id',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父栏目id,0为根栏目',
  `name` varchar(45) NOT NULL COMMENT '栏目名称',
  `en_name` varchar(45) DEFAULT '无' COMMENT '栏目英文名称',
  `column_type_id` int(11) NOT NULL COMMENT '栏目类型id',
  `rank_value` int(11) NOT NULL DEFAULT '100' COMMENT '排序值',
  `icon` varchar(45) DEFAULT NULL COMMENT '图标样式',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key` (`pid`,`column_type_id`,`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='栏目管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column`
--

LOCK TABLES `column` WRITE;
/*!40000 ALTER TABLE `column` DISABLE KEYS */;
INSERT INTO `column` VALUES (1,0,'产品展示','Products',2,100,NULL,1578376332610,1578377520516,0),(2,0,'关于我们','About Us',1,1,NULL,1578385557736,1578385557736,0),(3,1,'川菜','Kawana',2,100,NULL,1578385692362,1578385692362,0);
/*!40000 ALTER TABLE `column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column_type`
--

DROP TABLE IF EXISTS `column_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `column_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL COMMENT '栏目类型值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='栏目类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column_type`
--

LOCK TABLES `column_type` WRITE;
/*!40000 ALTER TABLE `column_type` DISABLE KEYS */;
INSERT INTO `column_type` VALUES (1,'单页系统'),(2,'文章系统');
/*!40000 ALTER TABLE `column_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `company_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL COMMENT '公司介绍',
  `tel` varchar(20) NOT NULL COMMENT '公司联系电话',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_info`
--

LOCK TABLES `company_info` WRITE;
/*!40000 ALTER TABLE `company_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_log` (
  `id` bigint(20) NOT NULL,
  `admin_id` bigint(20) NOT NULL COMMENT '管理员id',
  `ip` varchar(15) DEFAULT NULL COMMENT '登录IP',
  `login_time` bigint(20) NOT NULL COMMENT '登录时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言消息id',
  `name` varchar(10) NOT NULL COMMENT '称呼',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `tel` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `ip` varchar(15) DEFAULT NULL COMMENT 'ip',
  `content` varchar(255) NOT NULL COMMENT '留言内容',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `is_checked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='留言表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'demoData2','demoData2','234235412','127.0.0.1','demoData',1578379655136,1578379655136,1,0),(2,'demoData','demoData','demoData','127.0.0.1','demoData',1578381378209,1578381378209,1,0),(3,'demoData','demoData','demoData','127.0.0.1','demoData',1578381835326,1578381835326,1,0),(4,'data','demoData','demoData','127.0.0.1','demoData',1578382362465,1578382362465,1,0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `column_id` bigint(20) NOT NULL,
  `title` varchar(45) NOT NULL COMMENT '标题',
  `rank_value` int(11) NOT NULL DEFAULT '100' COMMENT '排序值',
  `img` varchar(255) DEFAULT NULL,
  `simple_info` varchar(255) NOT NULL COMMENT '概述',
  `detail_info` text NOT NULL COMMENT '详细内容',
  `click_number` bigint(20) NOT NULL DEFAULT '0' COMMENT '新闻动态的点击数',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶，0未，1已',
  `is_recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐，0未，1已',
  `is_checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否审核，0未，1已',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除，0未，1已',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_column_id` (`column_id`) /*!80000 INVISIBLE */,
  KEY `idx_title` (`title`) /*!80000 INVISIBLE */
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻动态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_info`
--

DROP TABLE IF EXISTS `production_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `production_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `column_id` bigint(20) NOT NULL COMMENT '所属栏目类型id',
  `title` varchar(45) NOT NULL,
  `rank_value` int(11) NOT NULL DEFAULT '100' COMMENT '排序值',
  `img` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `simple_info` varchar(255) NOT NULL COMMENT '概述',
  `detail_info` text NOT NULL COMMENT '详细内容',
  `click_number` bigint(20) NOT NULL DEFAULT '0' COMMENT '点击数',
  `is_top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶，0未，1已',
  `is_recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐，0未，1已',
  `is_checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否审核，0未，1已',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除，0未，1已',
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_column_id` (`column_id`),
  KEY `idx_title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_info`
--

LOCK TABLES `production_info` WRITE;
/*!40000 ALTER TABLE `production_info` DISABLE KEYS */;
INSERT INTO `production_info` VALUES (1,1,'demoData',1,NULL,'demoData','demoData',4,0,0,0,1,1578290698879,1578293118042);
/*!40000 ALTER TABLE `production_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_mode`
--

DROP TABLE IF EXISTS `show_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `show_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '显示模式id',
  `type` char(2) DEFAULT NULL COMMENT '模板',
  `template` varchar(45) DEFAULT NULL COMMENT '模板',
  `create_time` bigint(20) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='显示模式';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_mode`
--

LOCK TABLES `show_mode` WRITE;
/*!40000 ALTER TABLE `show_mode` DISABLE KEYS */;
/*!40000 ALTER TABLE `show_mode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-07 18:14:01
