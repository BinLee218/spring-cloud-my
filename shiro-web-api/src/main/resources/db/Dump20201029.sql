-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: shiro
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `applications` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统ID',
  `app_name` varchar(20) NOT NULL COMMENT '系统名字',
  `app_desc` varchar(100) NOT NULL COMMENT '系统描述',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `app_name` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用系统';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (1,'vue-admin','后台管理系统','2020-10-27 09:22:41','2020-10-27 09:22:41'),(2,'other-admin','其他系统','2020-10-28 09:25:32','2020-10-28 09:25:32'),(3,'other-admin1','其他系统1','2020-10-28 09:44:42','2020-10-28 09:44:42');
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auth` (
  `auth_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_name` varchar(32) NOT NULL DEFAULT '' COMMENT '权限名称',
  `auth_value` varchar(32) NOT NULL DEFAULT '' COMMENT '权限值',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `app_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`auth_id`),
  UNIQUE KEY `un_auth_value` (`auth_value`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,'首页','100',1,'2020-10-09 05:36:38','2020-10-28 09:30:16',1),(2,'用户管理','101',1,'2020-10-09 05:38:35','2020-10-28 09:30:16',1),(3,'角色权限管理','102',1,'2020-10-09 05:38:35','2020-10-28 09:30:16',1),(4,'角色管理','103',1,'2020-10-09 05:38:35','2020-10-28 09:30:16',1),(5,'权限管理','104',1,'2020-10-09 05:38:35','2020-10-28 09:30:16',1),(6,'登录','105',1,'2020-10-11 08:29:11','2020-10-29 09:58:31',1),(7,'1231','1231',0,'2020-10-29 08:40:36','2020-10-29 08:40:36',2),(8,'添加用户按钮','106',1,'2020-10-29 09:58:31','2020-10-29 09:58:31',1),(9,'添加权限按钮','107',1,'2020-10-29 10:01:24','2020-10-29 10:01:24',1),(10,'修改权限按钮','108',1,'2020-10-29 10:01:24','2020-10-29 10:01:24',1),(11,'修改用户按钮','109',1,'2020-10-29 10:04:10','2020-10-29 10:04:10',1),(12,'添加角色按钮','110',1,'2020-10-29 10:14:19','2020-10-29 10:14:19',1),(13,'修改角色按钮','111',1,'2020-10-29 10:14:33','2020-10-29 10:14:33',1);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_auth`
--

DROP TABLE IF EXISTS `role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_auth` (
  `r_a_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) unsigned NOT NULL,
  `auth_value` varchar(32) NOT NULL,
  PRIMARY KEY (`r_a_id`),
  UNIQUE KEY `un_role_auth` (`role_id`,`auth_value`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_auth`
--

LOCK TABLES `role_auth` WRITE;
/*!40000 ALTER TABLE `role_auth` DISABLE KEYS */;
INSERT INTO `role_auth` VALUES (6,1,'100'),(1,1,'101'),(2,1,'102'),(3,1,'103'),(4,1,'104'),(5,1,'105'),(224,1,'106'),(226,1,'107'),(227,1,'108'),(228,1,'109'),(229,1,'110'),(230,1,'111'),(231,2,'100'),(232,2,'101'),(233,2,'102'),(215,19,'100'),(216,19,'102'),(217,19,'105');
/*!40000 ALTER TABLE `role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名',
  `role_value` varchar(32) NOT NULL DEFAULT '' COMMENT '角色值',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `app_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `un_role_value` (`role_value`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'管理员','admin',1,'2020-10-09 05:33:27','2020-10-09 05:33:27',0),(2,'操作员','operator',1,'2020-10-09 05:33:57','2020-10-29 10:19:49',0);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relation_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联ID',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(64) NOT NULL DEFAULT '' COMMENT '盐',
  `real_name` varchar(32) DEFAULT '' COMMENT '真实姓名',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否可用，0否，1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` int(11) NOT NULL DEFAULT '-1' COMMENT '创建人',
  `update_by` int(11) NOT NULL DEFAULT '-1' COMMENT '修改人',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `un_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (38,0,'admin001','12f0202103717ff2bc026cb4e6837c36','odOsdxdfMr8AEosAHnNt','哈哈1',1,'2020-10-08 07:34:40','2020-10-25 08:57:11',-1,-1,NULL),(39,0,'admin002','12f0202103717ff2bc026cb4e6837c36','odOsdxdfMr8AEosAHnNt','哈哈2',0,'2020-10-11 07:03:21','2020-10-25 08:57:11',-1,-1,NULL),(40,0,'admin123','12f0202103717ff2bc026cb4e6837c36','odOsdxdfMr8AEosAHnNt','哈哈123',1,'2020-10-17 08:27:53','2020-10-25 09:04:29',-1,-1,'2020-10-25 08:57:11'),(42,0,'admin003','7279864a808ee71a2cba04c4aef48d28','XZTt1l2YX1C1pVPe7IqO','哈哈003',0,'2020-10-25 09:02:42','2020-10-25 09:02:42',-1,-1,NULL),(43,0,'111','2861e853875447ba06fd8e108cb75c19','rqtwXyuGlmsvMSoTWvHq','1111',0,'2020-10-26 07:06:22','2020-10-26 07:06:22',-1,-1,NULL),(44,0,'123','de92d84f9af6f58a2cbd1cfa0a21bf64','SuMpFLH802CiT0rPfaMd','123',0,'2020-10-26 07:09:35','2020-10-26 07:09:35',-1,-1,NULL),(45,0,'222','d2d74354d2bb85f1256fdba95eb935af','ndvoU9o2ksWIMxz5P0yL','222',0,'2020-10-26 07:12:56','2020-10-26 07:12:56',-1,-1,NULL),(46,0,'3','5cd8e917c444b87ec547ffd98d6813f2','eqYqEjo1cyZgxcZwe3oE','3',0,'2020-10-26 07:15:56','2020-10-26 07:15:56',-1,-1,NULL),(48,0,'4','a2f62a9556714874035d7b7a3c14d08e','2hBAVWbxC6NiwD4vSsyf','4',0,'2020-10-26 07:34:13','2020-10-26 07:34:13',-1,-1,NULL),(53,0,'5','6defd7954185cbb92ec215fac56824d3','irmkUUHObyaSPzOIjzk1','5',0,'2020-10-26 07:46:20','2020-10-26 07:46:20',-1,-1,NULL),(54,0,'6','e55cdbdd9bbabcc23e7a3d1de4ad13ea','8GG98kMio3uAS4TiUbIZ','6',0,'2020-10-26 07:47:01','2020-10-26 07:47:01',-1,-1,NULL),(55,0,'7','ebf2b6a321d448454d5e0a2b5fda7b73','Zt1Tc7v3HDQnvGZARxGt','7',0,'2020-10-26 07:47:37','2020-10-26 07:47:37',-1,-1,NULL),(56,0,'8','714f9ab9dbb88ab01b9ceafe678282f1','gGO3bFc8ROVj4xJyHFao','8',0,'2020-10-26 07:49:20','2020-10-26 07:49:20',-1,-1,NULL),(57,0,'9','1e1aba05e609db927bca5707823c98bc','sIjetYFcQ6YczSPjoTcn','9',1,'2020-10-26 07:54:04','2020-10-26 07:54:04',-1,-1,NULL),(58,0,'11','f9c0603b163c7d10531e1b58e515ac54','7f276OmyqTXlCY9NeUIm','11',1,'2020-10-26 07:57:08','2020-10-26 07:57:08',-1,-1,NULL),(59,0,'12','8712ecdbf7f706ce7bdf7caf324ca01c','9QNe70pf8OQw3IVQLljR','12',1,'2020-10-26 07:57:36','2020-10-26 07:57:36',-1,-1,NULL),(60,0,'13','d382de98f1ad44babe1e6c06a17de3a1','DniIfUIejONWPaoFdtyC','13',1,'2020-10-26 08:01:28','2020-10-26 08:01:28',-1,-1,NULL),(61,0,'14','07c4db42eedaca1bc116b92b3bf8ef65','ooerO8nOWwIB7bxwypEB','14',1,'2020-10-26 09:56:36','2020-10-26 09:56:36',-1,-1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `u_r_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL DEFAULT '0',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`u_r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号角色中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,38,2),(2,40,1),(3,53,1),(4,54,1),(5,55,1),(6,56,1),(7,57,1),(8,58,2),(9,59,4),(10,60,1),(11,61,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-29 18:26:42
