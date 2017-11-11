/*
Source Database       : activiti_graph
Target Server Type    : MYSQL
Date: 2017-11-09 21:44:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activiti_comment
-- ----------------------------
DROP TABLE IF EXISTS `activiti_comment`;
CREATE TABLE `activiti_comment` (
  `comment_id` bigint(20) NOT NULL,
  `assign_comment` varchar(255) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `thread_id` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activiti_comment
-- ----------------------------

-- ----------------------------
-- Table structure for activiti_obj_re
-- ----------------------------
DROP TABLE IF EXISTS `activiti_obj_re`;
CREATE TABLE `activiti_obj_re` (
  `id` bigint(20) NOT NULL,
  `host_obj_id` bigint(20) NOT NULL,
  `table_name` varchar(255) NOT NULL,
  `assignee` bigint(20) NOT NULL,
  `create_timestamp` datetime DEFAULT NULL,
  `process_instance_id` varchar(64) NOT NULL,
  `activiti_type` varchar(255) DEFAULT NULL,
  `task_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activiti_obj_re
-- ----------------------------

-- ----------------------------
-- Table structure for rbac_group
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group`;
CREATE TABLE `rbac_group` (
  `group_id` bigint(20) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `create_timestamp` datetime DEFAULT NULL,
  `activiti_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group
-- ----------------------------
INSERT INTO `rbac_group` VALUES ('15099777254920', '组一', '2017-11-06 22:15:25', 'act_pm');
INSERT INTO `rbac_group` VALUES ('15099777617111', '组二', '2017-11-06 22:16:02', 'act_pc');
INSERT INTO `rbac_group` VALUES ('15099777720882', '组三', '2017-11-06 22:16:12', 'act_pd');
INSERT INTO `rbac_group` VALUES ('15099778851596', '组五', '2017-11-06 22:18:05', 'act_kg');
INSERT INTO `rbac_group` VALUES ('15102452506780', '组名cL', '2017-11-10 00:34:11', 'act_wm');
INSERT INTO `rbac_group` VALUES ('15102455816830', '玲珑', '2017-11-10 00:39:42', 'act_nl');
INSERT INTO `rbac_group` VALUES ('15102457055650', '玲珑', '2017-11-10 00:41:46', 'act_nl');
INSERT INTO `rbac_group` VALUES ('15102458764371', '利旧', '2017-11-10 00:44:36', 'act_ax');
INSERT INTO `rbac_group` VALUES ('15102461614730', '阿凡达', '2017-11-10 00:49:21', 'act_af');

-- ----------------------------
-- Table structure for rbac_group_user_re
-- ----------------------------
DROP TABLE IF EXISTS `rbac_group_user_re`;
CREATE TABLE `rbac_group_user_re` (
  `id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FKpc2tjic151xpol2q4c0hb1urc` (`group_id`),
  KEY `FK3sjmmp8dhw68i1x7jjrhw781l` (`user_id`),
  CONSTRAINT `FK3sjmmp8dhw68i1x7jjrhw781l` FOREIGN KEY (`user_id`) REFERENCES `rbac_user` (`user_id`),
  CONSTRAINT `FKpc2tjic151xpol2q4c0hb1urc` FOREIGN KEY (`group_id`) REFERENCES `rbac_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_group_user_re
-- ----------------------------
INSERT INTO `rbac_group_user_re` VALUES ('15104048955010', '15099777254920', '15098000038991');
INSERT INTO `rbac_group_user_re` VALUES ('15104049592411', '15099777254920', '15101065941080');
INSERT INTO `rbac_group_user_re` VALUES ('15104058639321', '15099777254920', '15099778073285');
INSERT INTO `rbac_group_user_re` VALUES ('15104058707232', '15099777617111', '15099778073285');

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user` (
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `create_timestamp` datetime NOT NULL,
  `login_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(1) unsigned zerofill NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES ('15097999920830', '张三', '2017-11-04 20:53:12', 'login1', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15098000038991', '张四', '2017-11-04 20:53:24', 'login2', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15098000274222', '张五', '2017-11-04 20:53:47', 'login3', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15098004366083', '张溜', '2017-11-04 21:00:37', 'login4', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15099777932013', '张菜', '2017-11-06 22:16:33', 'login5', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15099778013874', '张毛', '2017-11-06 22:16:41', 'login6', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15099778073285', '星系', '2017-11-06 22:16:47', 'login7', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `rbac_user` VALUES ('15101065941080', '西张', '2017-11-08 10:03:14', 'login8', '202cb962ac59075b964b07152d234b70', '0');
