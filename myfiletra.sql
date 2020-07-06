/*
Navicat MySQL Data Transfer

Source Server         : ZX
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : myfiletra

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-07-06 13:30:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `filename` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `filesize` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `filepath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'file',
  PRIMARY KEY (`filepath`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('2012级数分选讲习题册.doc', '609280', 'C:\\Users\\14914\\Desktop\\test\\2012级数分选讲习题册.doc');
INSERT INTO `file` VALUES ('chdtbs.ini', '281', 'C:\\Users\\14914\\Desktop\\test\\chdtbs.ini');
INSERT INTO `file` VALUES ('Java后端开发学习路线-高清版.jpg', '4117749', 'C:\\Users\\14914\\Desktop\\test\\Java后端开发学习路线-高清版.jpg');
INSERT INTO `file` VALUES ('maven_web.png', '26290', 'C:\\Users\\14914\\Desktop\\test\\maven_web.png');
INSERT INTO `file` VALUES ('QQ图片20200328230423.png', '761761', 'C:\\Users\\14914\\Desktop\\test\\QQ图片20200328230423.png');
INSERT INTO `file` VALUES ('QQ图片20200406095523.jpg', '267817', 'C:\\Users\\14914\\Desktop\\test\\QQ图片20200406095523.jpg');
INSERT INTO `file` VALUES ('QQ图片20200421125325.png', '1351261', 'C:\\Users\\14914\\Desktop\\test\\QQ图片20200421125325.png');
INSERT INTO `file` VALUES ('QQ图片20200421125338.png', '1552194', 'C:\\Users\\14914\\Desktop\\test\\QQ图片20200421125338.png');
INSERT INTO `file` VALUES ('图片3.png', '384851', 'C:\\Users\\14914\\Desktop\\test\\图片3.png');
INSERT INTO `file` VALUES ('图片4.png', '1071417', 'C:\\Users\\14914\\Desktop\\test\\图片4.png');
INSERT INTO `file` VALUES ('大三下.png', '165468', 'C:\\Users\\14914\\Desktop\\test\\大三下.png');
INSERT INTO `file` VALUES ('批注 2020-04-29 152044.png', '1179932', 'C:\\Users\\14914\\Desktop\\test\\批注 2020-04-29 152044.png');
INSERT INTO `file` VALUES ('更新说明.txt', '9412', 'C:\\Users\\14914\\Desktop\\test\\更新说明.txt');
INSERT INTO `file` VALUES ('第2章 CORBA基本原理（3）.pdf', '1302366', 'C:\\Users\\14914\\Desktop\\test\\第2章 CORBA基本原理（3）.pdf');
INSERT INTO `file` VALUES ('软件体系结构ppt.txt', '100', 'C:\\Users\\14914\\Desktop\\test\\软件体系结构ppt.txt');
INSERT INTO `file` VALUES ('需求规格说明书.doc', '33792', 'C:\\Users\\14914\\Desktop\\test\\需求规格说明书.doc');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', '123', '222222');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001', '123456', '13566542582');
INSERT INTO `user` VALUES ('002', '123456', '1111111111');
