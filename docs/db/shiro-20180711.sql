/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : zb-shiro

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-07-11 13:48:30
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '1', '工作台', '工作台', '/workdest', '0', '1', '1', 'fa fa-home', '1', '2017-09-27 21:22:02', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('2', '2', '权限管理', '权限管理', '', '0', '0', '2', 'fa fa-th-list', '1', '2017-07-13 15:04:42', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('3', '201', '用户管理', '用户管理', '/user/list', '2', '1', '1', 'fa fa-circle-o', '1', '2017-07-13 15:05:47', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('4', '20101', '新增', '新增用户', '/user/add', '3', '2', '0', null, '1', '2017-07-13 15:06:50', '2018-02-28 17:58:46');
INSERT INTO `permission` VALUES ('5', '20102', '编辑', '编辑用户', '/user/edit', '3', '2', '0', null, '1', '2017-07-13 15:08:03', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('6', '20103', '删除', '删除用户', '/user/delete', '3', '2', '0', null, '1', '2017-07-13 15:08:42', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('7', '20104', '批量删除', '批量删除用户', '/user/batch/delete', '3', '2', '0', '', '1', '2018-07-11 01:53:09', '2018-07-11 01:53:09');
INSERT INTO `permission` VALUES ('8', '20105', '分配角色-角色列表', '分配角色-角色列表查询', '/user/assign/role/list', '3', '2', '0', null, '1', '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `permission` VALUES ('9', '20106', '分配角色', '分配角色', '/user/assign/role', '3', '2', '0', null, '1', '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `permission` VALUES ('10', '202', '角色管理', '角色管理', '/role/list', '2', '1', '2', 'fa fa-circle-o', '1', '2017-07-17 14:39:09', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('11', '20201', '新增', '新增角色', '/role/add', '10', '2', '0', null, '1', '2017-07-17 14:39:46', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('12', '20202', '编辑', '编辑角色', '/role/edit', '10', '2', '0', null, '1', '2017-07-17 14:40:15', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('13', '20203', '删除', '删除角色', '/role/delete', '10', '2', '0', null, '1', '2017-07-17 14:40:57', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('14', '20204', '批量删除', '批量删除角色', '/role/batch/delete', '10', '2', '0', '', '1', '2018-07-10 22:20:43', '2018-07-10 22:20:43');
INSERT INTO `permission` VALUES ('15', '20205', '分配权限-列表', '分配权限-列表', '/role/assign/permission/list', '10', '2', '0', null, '1', '2017-10-10 15:31:36', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('16', '20206', '分配权限', '分配权限', '/role/assign/permission', '10', '2', '0', null, '1', '2017-09-26 07:33:05', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('17', '203', '资源管理', '资源管理', '/permission/list', '2', '1', '3', 'fa fa-circle-o', '1', '2017-09-26 07:33:51', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('18', '20301', '新增', '新增资源', '/permission/add', '17', '2', '0', null, '1', '2017-09-26 08:06:58', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('19', '20302', '编辑', '编辑资源', '/permission/edit', '17', '2', '0', null, '1', '2017-09-27 21:29:04', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('20', '20303', '删除', '删除资源', '/permission/delete', '17', '2', '0', null, '1', '2017-09-27 21:29:50', '2018-02-27 10:53:14');
INSERT INTO `permission` VALUES ('21', '3', '运维管理', '运维管理', '', '0', '0', '3', 'fa fa-th-list', '1', '2018-07-06 15:19:26', '2018-07-06 15:19:26');
INSERT INTO `permission` VALUES ('22', '301', '数据监控', '数据监控', '/database/monitoring', '21', '1', '1', 'fa fa-circle-o', '1', '2018-07-06 15:19:55', '2018-07-06 15:19:55');
INSERT INTO `permission` VALUES ('23', '4', '系统工具', '系统工具', '', '0', '0', '4', 'fa fa-th-list', '1', '2018-07-06 15:20:38', '2018-07-06 15:20:38');
INSERT INTO `permission` VALUES ('24', '401', '图标工具', '图标工具', '/icons', '23', '1', '1', 'fa fa-circle-o', '1', '2018-07-06 15:21:00', '2018-07-06 15:21:00');

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '201');
INSERT INTO `role_permission` VALUES ('4', '1', '20101');
INSERT INTO `role_permission` VALUES ('5', '1', '20102');
INSERT INTO `role_permission` VALUES ('6', '1', '20103');
INSERT INTO `role_permission` VALUES ('7', '1', '20104');
INSERT INTO `role_permission` VALUES ('8', '1', '20105');
INSERT INTO `role_permission` VALUES ('9', '1', '20106');
INSERT INTO `role_permission` VALUES ('10', '1', '202');
INSERT INTO `role_permission` VALUES ('11', '1', '20201');
INSERT INTO `role_permission` VALUES ('12', '1', '20202');
INSERT INTO `role_permission` VALUES ('13', '1', '20203');
INSERT INTO `role_permission` VALUES ('14', '1', '20204');
INSERT INTO `role_permission` VALUES ('15', '1', '20205');
INSERT INTO `role_permission` VALUES ('16', '1', '20206');
INSERT INTO `role_permission` VALUES ('17', '1', '203');
INSERT INTO `role_permission` VALUES ('18', '1', '20301');
INSERT INTO `role_permission` VALUES ('19', '1', '20302');
INSERT INTO `role_permission` VALUES ('20', '1', '20303');
INSERT INTO `role_permission` VALUES ('21', '1', '3');
INSERT INTO `role_permission` VALUES ('22', '1', '301');
INSERT INTO `role_permission` VALUES ('23', '1', '4');
INSERT INTO `role_permission` VALUES ('24', '1', '401');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'admin', '872359cc44c637cc73b7cd55c06d95e4', '8cd50474d2a3c1e88298e91df8bf6f1c', '523179414@qq.com', '18788889999', '1', '20', '1', '2018-05-23 21:22:06', '2018-05-23 21:22:06', '2018-07-11 13:47:10');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
