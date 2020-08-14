/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : management_system

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-06-24 23:14:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '宿舍名',
  `stage` int(10) DEFAULT NULL COMMENT '宿舍状态 0-空闲 1-租用中 -1-维修中',
  `startTime` datetime DEFAULT NULL COMMENT '开始租用时间',
  `type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='宿舍表';

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('1', '西二312', '1', '2020-06-23 22:51:09', '2');
INSERT INTO `dormitory` VALUES ('2', '西二412', '1', '2020-06-22 23:30:11', '1');
INSERT INTO `dormitory` VALUES ('3', '西二612', '1', '2020-06-24 18:02:08', '4');
INSERT INTO `dormitory` VALUES ('4', '西二712', '0', null, '4');
INSERT INTO `dormitory` VALUES ('5', '西四132', '0', null, '3');
INSERT INTO `dormitory` VALUES ('6', '西三312', '0', null, '4');
INSERT INTO `dormitory` VALUES ('7', '西二122', '0', null, '4');
INSERT INTO `dormitory` VALUES ('8', '西二112', '0', null, '4');
INSERT INTO `dormitory` VALUES ('9', '西二682', '0', null, '4');
INSERT INTO `dormitory` VALUES ('10', '西二732', '1', null, '3');
INSERT INTO `dormitory` VALUES ('11', '西四143', '0', null, '4');
INSERT INTO `dormitory` VALUES ('12', '西三332', '1', '2020-06-23 10:47:32', '3');
INSERT INTO `dormitory` VALUES ('13', '西七701', '0', null, '3');
INSERT INTO `dormitory` VALUES ('17', '西二333', '0', null, '2');
INSERT INTO `dormitory` VALUES ('18', '西二221', '0', null, '1');
INSERT INTO `dormitory` VALUES ('19', '西二336', '0', null, '2');
INSERT INTO `dormitory` VALUES ('20', '西二321', '0', null, '1');
INSERT INTO `dormitory` VALUES ('21', '西二211', '0', null, '2');
INSERT INTO `dormitory` VALUES ('22', '西二701', '0', null, '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `age` int(10) unsigned DEFAULT NULL COMMENT '年龄',
  `gender` bit(1) DEFAULT NULL COMMENT '性别1-男0-女',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `enabled` int(1) DEFAULT '1' COMMENT '用户状态1-激活中0-已注销 -1管理员',
  `registeredTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `dormitory_id` int(11) DEFAULT NULL COMMENT '宿舍id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '123', '0', '\0', null, '-1', null, null);
INSERT INTO `user` VALUES ('2', '111', '111', '0', '\0', null, '1', '2020-06-21 17:24:42', null);
INSERT INTO `user` VALUES ('7', '张三', null, '23', '', '111111', null, null, '2');
INSERT INTO `user` VALUES ('9', '·12', null, '11', '', '123', '0', '2020-06-22 23:19:57', '2');
INSERT INTO `user` VALUES ('10', '123', null, '32', '\0', '123423', '1', null, '2');
INSERT INTO `user` VALUES ('12', '黄飞鸿', null, '80', '\0', '156892182', '1', null, '12');
INSERT INTO `user` VALUES ('13', 'root', 'root', null, null, null, '-1', '2020-06-23 18:48:17', null);
INSERT INTO `user` VALUES ('15', '111', '111', '0', '\0', null, '-1', null, '0');
INSERT INTO `user` VALUES ('16', '张三', null, '0', '\0', '', '1', null, '1');
INSERT INTO `user` VALUES ('17', '张三', null, '21', '', '19924338546', '1', '2020-06-24 18:05:35', '3');
INSERT INTO `user` VALUES ('18', '李四', null, '23', '', '12324335343', '1', '2020-06-24 18:05:31', '3');
INSERT INTO `user` VALUES ('19', '王五', null, '22', '', '15348575543', '1', '2020-06-24 18:05:29', '3');
INSERT INTO `user` VALUES ('20', '小李', null, '20', '', '13454566766', '1', '2020-06-24 18:05:28', '3');
