/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : remember_words

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/01/2025 15:04:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for words
-- ----------------------------
DROP TABLE IF EXISTS `words`;
CREATE TABLE `words`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Primary key, unique identifier',
  `word` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT 'The word being recorded',
  `meaning` text CHARACTER SET utf8mb4 NULL COMMENT 'The meaning of the word',
  `example` text CHARACTER SET utf8mb4 NULL COMMENT 'Example usage of the word',
  `sentence` text CHARACTER SET utf8mb4 NULL COMMENT 'A sentence containing the word',
  `status` int NULL DEFAULT 0 COMMENT 'Status of the word, e.g., 0 for inactive, 1 for active',
  `review_count` int NULL DEFAULT 0 COMMENT 'Number of times the word has been reviewed',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp of creation',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp of last update',
  `next_review_time` datetime NULL DEFAULT NULL COMMENT 'Scheduled next review time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1876572977532710914 CHARACTER SET = utf8mb4  COMMENT = 'Table to store words with their metadata' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
