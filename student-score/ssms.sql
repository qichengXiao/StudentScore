/*
Navicat MySQL Data Transfer

Source Server         : 萧
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : ssms

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-12-13 15:20:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `channel`
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('1', '语文');
INSERT INTO `channel` VALUES ('2', '数学');
INSERT INTO `channel` VALUES ('3', '英语');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `courseName` varchar(255) DEFAULT NULL,
  `mark` float DEFAULT NULL,
  `stu_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iuu35nsu7jntt36rvkon1hc36` (`stu_id`),
  CONSTRAINT `FK_iuu35nsu7jntt36rvkon1hc36` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('2', '语文', '99', 'aa2');
INSERT INTO `score` VALUES ('3', '语文', '44', 'aa3');
INSERT INTO `score` VALUES ('402880855170df2b015170f0ed220000', '语文', '55', '6666');
INSERT INTO `score` VALUES ('402880855170df2b015170f0ed240001', '数学', '55', '6666');
INSERT INTO `score` VALUES ('402880855170df2b015170f0ed240002', '英语', '55', '6666');
INSERT INTO `score` VALUES ('402881e751610cbb01516114f9220000', '语文', '46', '3114001');
INSERT INTO `score` VALUES ('402881ed5177ed530151783ced730000', '英语', '99', 'aasxc12');
INSERT INTO `score` VALUES ('402881ed5199c2a0015199eba93f0000', '数学', '88', '31554');
INSERT INTO `score` VALUES ('402881ed5199ed9d015199f943080000', '数学', '48', '31554');
INSERT INTO `score` VALUES ('402881ed5199ed9d015199f969640001', '语文', '67', '31554');
INSERT INTO `score` VALUES ('402881ed5199ed9d015199faaf620002', '英语', '67', '31554');
INSERT INTO `score` VALUES ('8a95c7ff518b9d4501518ba467b80000', '语文', '88', '3114001');
INSERT INTO `score` VALUES ('8a95c7ff518b9d4501518ba49aaa0001', '数学', '88', '3114001');
INSERT INTO `score` VALUES ('ff808081516294350151629dec980004', '语文', '24.8', 'aa1');
INSERT INTO `score` VALUES ('ff808081516294350151629dec980005', '数学', '33', 'aa1');

-- ----------------------------
-- Table structure for `stuclass`
-- ----------------------------
DROP TABLE IF EXISTS `stuclass`;
CREATE TABLE `stuclass` (
  `id` varchar(255) NOT NULL,
  `academeName` varchar(255) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuclass
-- ----------------------------
INSERT INTO `stuclass` VALUES ('0001', '计算机学院', '14级软件4班');
INSERT INTO `stuclass` VALUES ('0002', '计算机学院', '14级软件3班');
INSERT INTO `stuclass` VALUES ('0003', '计算机学院', '13级网络2班');
INSERT INTO `stuclass` VALUES ('0011', '自动化学院', '14级物联1班');
INSERT INTO `stuclass` VALUES ('1115', '自动化学院', '班级名称');
INSERT INTO `stuclass` VALUES ('11157', '自动化学院', '班级名称2');
INSERT INTO `stuclass` VALUES ('67678', '计算机学院', '测试班级3');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `stuClass_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5mhq8xtjgfg8nqt7kx8yn6nsf` (`stuClass_id`),
  CONSTRAINT `FK_5mhq8xtjgfg8nqt7kx8yn6nsf` FOREIGN KEY (`stuClass_id`) REFERENCES `stuclass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('3114001', '测试用名', '0001');
INSERT INTO `student` VALUES ('31554', '速度啊', '0002');
INSERT INTO `student` VALUES ('6666', '测试一下咯', '0001');
INSERT INTO `student` VALUES ('997', '测试999', '0001');
INSERT INTO `student` VALUES ('aa1', '萧启程2', '0001');
INSERT INTO `student` VALUES ('aa2', '吴坤金 ', '0001');
INSERT INTO `student` VALUES ('aa3', '写创富', '0001');
INSERT INTO `student` VALUES ('aasxc12', '速度', '0001');
INSERT INTO `student` VALUES ('asc3csd', 'asdc', '0001');
INSERT INTO `student` VALUES ('asdfsdf', '测试s是是是', '0001');
INSERT INTO `student` VALUES ('cecwec', 'wefasdf', '0001');
INSERT INTO `student` VALUES ('ces', 'fasfds', '0001');
INSERT INTO `student` VALUES ('scecs', 'sdfcece', '0001');
