/*
 Navicat Premium Data Transfer

 Source Server         : guli
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : active_card

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 18/04/2024 21:38:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for counting
-- ----------------------------
DROP TABLE IF EXISTS `counting`;
CREATE TABLE `counting`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of counting
-- ----------------------------
INSERT INTO `counting` VALUES (2, '2024-04-18', '00:02', 1);

-- ----------------------------
-- Table structure for habit_check_items
-- ----------------------------
DROP TABLE IF EXISTS `habit_check_items`;
CREATE TABLE `habit_check_items`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `item_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打卡补充',
  `start_date` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` date NULL DEFAULT NULL COMMENT '结束时间',
  `completed_times` int(0) NULL DEFAULT 0 COMMENT '完成次数',
  `last_checked_date` date NULL DEFAULT NULL COMMENT '最后打卡时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `habit_check_items_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of habit_check_items
-- ----------------------------
INSERT INTO `habit_check_items` VALUES (1, 1, '每天喝水', '2024-04-18', '2024-04-23', 0, '2024-04-18');

-- ----------------------------
-- Table structure for notes
-- ----------------------------
DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notes
-- ----------------------------
INSERT INTO `notes` VALUES (1, 1, '1+1', '2', '2024-04-18 21:31:39');

-- ----------------------------
-- Table structure for pomodoro
-- ----------------------------
DROP TABLE IF EXISTS `pomodoro`;
CREATE TABLE `pomodoro`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pomodoro
-- ----------------------------
INSERT INTO `pomodoro` VALUES (1, '2024-04-18', '25:00', 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', NULL, '2024-04-08 20:00:35');

-- ----------------------------
-- Table structure for wait
-- ----------------------------
DROP TABLE IF EXISTS `wait`;
CREATE TABLE `wait`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `event_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '待办事件详情',
  `ok_time` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_ok` int(0) NULL DEFAULT NULL COMMENT '是否完成',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wait
-- ----------------------------
INSERT INTO `wait` VALUES (1, 'admin', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁尝试');
INSERT INTO `wait` VALUES (2, 'cs', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (3, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (4, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (5, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (6, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (7, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (8, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (9, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (10, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (11, 'css', '早上要吃饭', '2024-04-08 19:40:48', '2024-04-03 19:40:52', 0, '宁changss');
INSERT INTO `wait` VALUES (13, '1', '每天要吃饭', '2024-04-24 21:31:02', NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
