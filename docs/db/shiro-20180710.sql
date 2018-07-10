/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : zb-shiro

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-07-11 01:53:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) DEFAULT NULL COMMENT '权限访问路径',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级权限id',
  `type` int(1) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(3) DEFAULT '0' COMMENT '排序',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '1', '工作台', '工作台', '/workdest', '0', '1', '1', 'fa fa-home', '1', '2017-09-27 21:22:02', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('2', '2', '权限管理', '权限管理', '', '0', '0', '2', 'fa fa-th-list', '1', '2017-07-13 15:04:42', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('3', '201', '用户管理', '用户管理', '/user/list', '2', '1', '1', 'fa fa-circle-o', '1', '2017-07-13 15:05:47', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('4', '20101', '新增', '新增用户', '/user/add', '3', '2', '0', null, '1', '2017-07-13 15:06:50', '2018-02-28 17:58:46');
INSERT INTO `permission` VALUES ('5', '20102', '编辑', '编辑用户', '/user/edit', '3', '2', '0', null, '1', '2017-07-13 15:08:03', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('6', '20103', '删除', '删除用户', '/user/delete', '3', '2', '0', null, '1', '2017-07-13 15:08:42', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('7', '20104', '详情', '用户详情', '/user/detail', '3', '2', '0', null, '1', '2017-07-13 15:10:32', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('8', '20105', '分配角色', '分配角色', '/user/assign/role', '3', '2', '0', null, '1', '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `permission` VALUES ('9', '202', '角色管理', '角色管理', '/role/list', '2', '1', '2', 'fa fa-circle-o', '1', '2017-07-17 14:39:09', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('10', '20201', '新增', '新增角色', '/role/add', '9', '2', '0', null, '1', '2017-07-17 14:39:46', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('11', '20202', '编辑', '编辑角色', '/role/edit', '9', '2', '0', null, '1', '2017-07-17 14:40:15', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('12', '20203', '删除', '删除角色', '/role/delete', '9', '2', '0', null, '1', '2017-07-17 14:40:57', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('13', '20204', '详情', '角色详情', '/role/detail', '9', '2', '0', null, '1', '2017-07-17 14:41:42', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('14', '20206', '分配权限', '分配权限', '/role/assign/permission', '9', '2', '0', null, '1', '2017-09-26 07:33:05', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('15', '203', '资源管理', '资源管理', '/permission/list', '2', '1', '3', 'fa fa-circle-o', '1', '2017-09-26 07:33:51', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('16', '20301', '新增', '新增资源', '/permission/add', '15', '2', '0', null, '1', '2017-09-26 08:06:58', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('17', '20302', '编辑', '编辑资源', '/permission/edit', '15', '2', '0', null, '1', '2017-09-27 21:29:04', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('18', '20303', '删除', '删除资源', '/permission/delete', '15', '2', '0', null, '1', '2017-09-27 21:29:50', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('19', '20304', '详情', '资源详情', '/permission/detail', '15', '2', '0', null, '1', '2017-09-27 21:30:25', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('99', '20106', '分配角色-角色列表', '分配角色-角色列表查询', '/user/assign/role/list', '3', '2', '0', null, '1', '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `permission` VALUES ('100', '20107', '启用用户', '启用用户', '/user/reuse', '3', '2', '0', null, '1', '2017-10-09 12:49:50', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('101', '20205', '启用', '启用角色', '/role/reuse', '9', '2', '0', null, '1', '2017-10-09 17:30:56', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('102', '20207', '分配权限-列表', '分配权限-列表', '/role/assign/permission/list', '9', '2', '0', null, '1', '2017-10-10 15:31:36', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('128', '1000001780642338', '运维管理', '运维管理', '', '0', '0', '3', 'fa fa-th-list', '1', '2018-07-06 15:19:26', '2018-07-06 15:19:26');
INSERT INTO `permission` VALUES ('129', '1000000856521304', '数据监控', '数据监控', '/database/monitoring', '128', '1', '1', 'fa fa-circle-o', '1', '2018-07-06 15:19:55', '2018-07-06 15:19:55');
INSERT INTO `permission` VALUES ('130', '1000001530827911', '系统工具', '系统工具', '', '0', '0', '4', 'fa fa-th-list', '1', '2018-07-06 15:20:38', '2018-07-06 15:20:38');
INSERT INTO `permission` VALUES ('131', '1000001391883508', '图标工具', '图标工具', '/icons', '130', '1', '1', 'fa fa-circle-o', '1', '2018-07-06 15:21:00', '2018-07-06 15:21:00');
INSERT INTO `permission` VALUES ('132', '1000001205659046', '批量删除', '批量删除角色', '/role/batch/delete', '9', '2', '0', '', '1', '2018-07-10 22:20:43', '2018-07-10 22:20:43');
INSERT INTO `permission` VALUES ('133', '1000001006835058', '批量删除', '批量删除用户', '/user/batch/delete', '3', '2', '0', '', '1', '2018-07-11 01:53:09', '2018-07-11 01:53:09');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '状态：1有效；2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1', '超级管理员', '超级管理员', '1', '2017-06-28 20:30:05', '2017-06-28 20:30:10');
INSERT INTO `role` VALUES ('2', '2', '管理员', '管理员', '1', '2017-06-30 23:35:19', '2017-10-11 09:32:33');
INSERT INTO `role` VALUES ('3', '3', '普通用户', '普通用户', '1', '2017-06-30 23:35:44', '2017-10-09 17:37:48');
INSERT INTO `role` VALUES ('4', '4', '数据库管理员', '数据库管理员', '1', '2017-07-12 11:50:22', '2017-10-09 17:38:02');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `permission_id` varchar(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1570 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1469', '3', '1');
INSERT INTO `role_permission` VALUES ('1470', '3', '2');
INSERT INTO `role_permission` VALUES ('1471', '3', '201');
INSERT INTO `role_permission` VALUES ('1472', '3', '20104');
INSERT INTO `role_permission` VALUES ('1473', '3', '202');
INSERT INTO `role_permission` VALUES ('1474', '3', '20204');
INSERT INTO `role_permission` VALUES ('1475', '3', '203');
INSERT INTO `role_permission` VALUES ('1476', '3', '20304');
INSERT INTO `role_permission` VALUES ('1541', '1', '1');
INSERT INTO `role_permission` VALUES ('1542', '1', '2');
INSERT INTO `role_permission` VALUES ('1543', '1', '201');
INSERT INTO `role_permission` VALUES ('1544', '1', '20101');
INSERT INTO `role_permission` VALUES ('1545', '1', '20102');
INSERT INTO `role_permission` VALUES ('1546', '1', '20103');
INSERT INTO `role_permission` VALUES ('1547', '1', '20104');
INSERT INTO `role_permission` VALUES ('1548', '1', '20105');
INSERT INTO `role_permission` VALUES ('1549', '1', '20106');
INSERT INTO `role_permission` VALUES ('1550', '1', '20107');
INSERT INTO `role_permission` VALUES ('1551', '1', '1000001006835058');
INSERT INTO `role_permission` VALUES ('1552', '1', '202');
INSERT INTO `role_permission` VALUES ('1553', '1', '20201');
INSERT INTO `role_permission` VALUES ('1554', '1', '20202');
INSERT INTO `role_permission` VALUES ('1555', '1', '20203');
INSERT INTO `role_permission` VALUES ('1556', '1', '20204');
INSERT INTO `role_permission` VALUES ('1557', '1', '20206');
INSERT INTO `role_permission` VALUES ('1558', '1', '20205');
INSERT INTO `role_permission` VALUES ('1559', '1', '20207');
INSERT INTO `role_permission` VALUES ('1560', '1', '1000001205659046');
INSERT INTO `role_permission` VALUES ('1561', '1', '203');
INSERT INTO `role_permission` VALUES ('1562', '1', '20301');
INSERT INTO `role_permission` VALUES ('1563', '1', '20302');
INSERT INTO `role_permission` VALUES ('1564', '1', '20303');
INSERT INTO `role_permission` VALUES ('1565', '1', '20304');
INSERT INTO `role_permission` VALUES ('1566', '1', '1000001780642338');
INSERT INTO `role_permission` VALUES ('1567', '1', '1000000856521304');
INSERT INTO `role_permission` VALUES ('1568', '1', '1000001530827911');
INSERT INTO `role_permission` VALUES ('1569', '1', '1000001391883508');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL,
  `salt` varchar(128) DEFAULT NULL COMMENT '加密盐值',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `sex` int(255) DEFAULT NULL COMMENT '年龄：1男2女',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效; 2删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'admin', '872359cc44c637cc73b7cd55c06d95e4', '8cd50474d2a3c1e88298e91df8bf6f1c', null, null, null, null, '1', '2018-05-23 21:22:06', '2018-05-23 21:22:06', '2018-07-06 15:29:01');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('64', '1', '1');
INSERT INTO `user_role` VALUES ('68', '1000001493229733', '1');
INSERT INTO `user_role` VALUES ('69', '1000001153077192', '3');
