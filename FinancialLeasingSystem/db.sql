use hwadee
/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:16:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '承租人id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '承租人name',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '承租人密码',
  `career` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '调查报告时可添加：职业',
  `credit_grade_id` int NOT NULL DEFAULT '0' COMMENT '淇＄敤绛夌骇锛屽繀椤?',
  `salary` int DEFAULT NULL COMMENT '调查报告时可添加：工资',
  `assets` bigint DEFAULT NULL COMMENT '调查报告时可添加：资产情况',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份证号码',
  `credit_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '淇＄敤绛夌骇璇勪及',
  `recent_bill` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `facePath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '承租人人脸存储路径',
  `faceUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '承租人人脸访问路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='承租人';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '承租人1号', '123', '公务员', '1', '3600', '500000', '510166200804162029', '当前承租人信用优秀，因为他做人诚实，没有任何逾期记录，拾金不昧', 'file/bill/1647919802374.',null,null);
INSERT INTO `user` VALUES ('2', '承租人2号', '123', '个体经营商', '2', '5600', '600000', '501256200006298989', '当前承租人信用良好，存在一些逾期记录', null,null,null);
INSERT INTO `user` VALUES ('3', '承租人3号', '123', '无业人员', '3', '2400', '200000', '510189200309272830', '当前承租人信用较差，存在较多逾期记录', null,null,null);


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sign_contract`
-- ----------------------------
DROP TABLE IF EXISTS `sign_contract`;
CREATE TABLE `sign_contract` (
  `project_id` int NOT NULL,
  `contract_file` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `legal_staff_id` int DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sign_contract
-- ----------------------------
INSERT INTO `sign_contract` VALUES ('7', 'file/contract/1647070677787.pdf', '1');
INSERT INTO `sign_contract` VALUES ('8', 'file/contract/1647001056912.pdf', '1');
INSERT INTO `sign_contract` VALUES ('10', 'file/contract/1647001139334.pdf', '1');
INSERT INTO `sign_contract` VALUES ('22', 'file/contract/1647956176804.pdf', '5');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `risk_grade_describe`
-- ----------------------------
DROP TABLE IF EXISTS `risk_grade_describe`;
CREATE TABLE `risk_grade_describe` (
  `id` int NOT NULL COMMENT '风险等级id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险等级描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of risk_grade_describe
-- ----------------------------
INSERT INTO `risk_grade_describe` VALUES ('1', '无风险');
INSERT INTO `risk_grade_describe` VALUES ('2', '低风险');
INSERT INTO `risk_grade_describe` VALUES ('3', '高风险');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `quote_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `quote_evaluation`;
CREATE TABLE `quote_evaluation` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `lease_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '租赁物市场售卖价格',
  `lease_rent_cost` int DEFAULT NULL COMMENT '租赁物市场租赁价格',
  `lease_schedule_unit_price` int DEFAULT NULL COMMENT '建议方案 单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '建议方案 单位时间',
  `lease_schedule_duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '建议方案 共计多少单位时间',
  `loan_time` datetime DEFAULT NULL COMMENT '建议放款时间',
  `evaluator_id` int DEFAULT NULL COMMENT '评估员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='报价评估——财务部职员操作';

-- ----------------------------
-- Records of quote_evaluation
-- ----------------------------
INSERT INTO `quote_evaluation` VALUES ('4', '65000', '10000', '10000', '日', '6', '2022-03-25 16:34:15', '3');
INSERT INTO `quote_evaluation` VALUES ('5', '30000', '10000', '10000', '日', '3', '2022-03-25 16:37:57', '3');
INSERT INTO `quote_evaluation` VALUES ('6', '30000', '10000', '10000', '日', '3', '2022-03-25 16:38:01', '3');
INSERT INTO `quote_evaluation` VALUES ('7', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:09', '3');
INSERT INTO `quote_evaluation` VALUES ('8', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:15', '3');
INSERT INTO `quote_evaluation` VALUES ('9', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:29', '3');
INSERT INTO `quote_evaluation` VALUES ('10', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:36', '3');
INSERT INTO `quote_evaluation` VALUES ('11', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:43', '3');
INSERT INTO `quote_evaluation` VALUES ('12', '40000', '10000', '10000', '日', '4', '2022-02-20 16:38:49', '3');
INSERT INTO `quote_evaluation` VALUES ('13', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:02', '3');
INSERT INTO `quote_evaluation` VALUES ('14', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:09', '3');
INSERT INTO `quote_evaluation` VALUES ('15', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:14', '3');
INSERT INTO `quote_evaluation` VALUES ('22', '1', '1', '10000', '日', '10', '2022-02-02 00:00:00', '3');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_state`
-- ----------------------------
DROP TABLE IF EXISTS `project_state`;
CREATE TABLE `project_state` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目状态id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对状态阶段的描述：\r\n1.立项申请 2.风险评估 3.报价评估 4.项目终审 5.合同签约 6.放款审批 7.正常履约 8.逾期未交 9.变更审批之风险评估 10.变更审批之财务评估 11.变更审批之项目审批（业务经理审批） 12.资产处置审批 13.资产处置 14.结束\r\n',
  `authority_id` int DEFAULT NULL COMMENT '和权限联系，方便项目分配staff',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_state
-- ----------------------------
INSERT INTO `project_state` VALUES ('1', '立项申请', '1');
INSERT INTO `project_state` VALUES ('2', '风险评估', '2');
INSERT INTO `project_state` VALUES ('3', '报价评估', '3');
INSERT INTO `project_state` VALUES ('4', '项目终审', '4');
INSERT INTO `project_state` VALUES ('5', '合同签约', '5');
INSERT INTO `project_state` VALUES ('6', '放款审批', '3');
INSERT INTO `project_state` VALUES ('7', '正常履约', '1');
INSERT INTO `project_state` VALUES ('8', '逾期未交', '1');
INSERT INTO `project_state` VALUES ('9', '变更风险评估', '2');
INSERT INTO `project_state` VALUES ('10', '变更财务评估', '3');
INSERT INTO `project_state` VALUES ('11', '变更项目终审', '4');
INSERT INTO `project_state` VALUES ('12', '资产处置财务审批', '3');
INSERT INTO `project_state` VALUES ('13', '资产处置最终审批', '4');
INSERT INTO `project_state` VALUES ('14', '资产处置', '1');
INSERT INTO `project_state` VALUES ('15', '结束', '1');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_info`
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `applicant_id` int NOT NULL COMMENT '承租人姓名',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名',
  `project_time` datetime NOT NULL COMMENT '项目开始时间',
  `leased_asset` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '租赁物',
  `supplier` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商',
  `price` int NOT NULL COMMENT '价格',
  `lease_schedule_unit_price` int NOT NULL COMMENT '租赁计划 单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '租赁计划 单位时间',
  `lease_schedule_duration` int NOT NULL COMMENT '租赁计划 共计多少单位时间',
  `pawn_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '抵押物名称',
  `pawn_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '抵押物类别',
  `pawn_value` int NOT NULL COMMENT '抵押物现在的价值',
  `certificate` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '涓婁紶鍥剧墖鎴栬€呮枃浠?',
  `state_id` int NOT NULL DEFAULT '2' COMMENT '閻樿埖鈧巩d',
  `current_staff_id` int NOT NULL DEFAULT '1' COMMENT '鍒嗛厤鐨勫憳宸d',
  `sales_id` int DEFAULT '1' COMMENT '璺熻繘鐨勪笟鍔″憳id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES ('1', '1', '华迪融资项目1（立项申请状态）', '2022-04-01 14:17:31', '电脑设备', '华为', '30000', '10000', '日', '3', '房产证书', '房产', '200000', '', '1', '1', '1');
INSERT INTO `project_info` VALUES ('2', '2', '华迪融资项目2（风险评估状态）', '2022-04-01 15:25:08', '租赁物2', '供应商2', '50000', '10000', '日', '5', '抵押物2', '抵押类型2', '3000000', '', '2', '2', '1');
INSERT INTO `project_info` VALUES ('3', '3', '华迪融资项目3（报价评估状态）', '2022-04-01 15:28:21', '租赁物3', '供应商3', '40000', '10000', '日', '4', '抵押物3', '抵押类型3', '400000', '', '3', '3', '1');
INSERT INTO `project_info` VALUES ('4', '1', '华迪融资项目4（项目终审状态）', '2022-04-01 15:33:14', '租赁物4', '供应商4', '60000', '10000', '日', '6', '抵押物4', '抵押类型4', '5000000', '', '4', '4', '1');
INSERT INTO `project_info` VALUES ('5', '2', '华迪融资项目6（合同签约状态）', '2022-04-01 15:36:27', '租赁物5', '供应商5', '30000', '10000', '日', '3', '抵押物5', '抵押类型5', '300000', '', '5', '5', '1');
INSERT INTO `project_info` VALUES ('6', '3', '华迪融资项目7（放款审批）', '2022-04-01 15:57:41', '租赁物6', '供应商6', '30000', '10000', '日', '3', '抵押物6', '抵押类型6', '400000', '', '6', '3', '1');
INSERT INTO `project_info` VALUES ('7', '1', '华迪融资项目7（正常履约）', '2022-02-28 16:02:59', '租赁物7', '供应商7', '110000', '10000', '日', '11', '抵押物7', '抵押类型7', '399999', '', '7', '1', '1');
INSERT INTO `project_info` VALUES ('8', '2', '华迪融资项目8（逾期未交）', '2022-02-28 16:08:19', '租赁物8', '供应商8', '110000', '10000', '日', '11', '抵押物8', '抵押类型8', '20000', '', '15', '1', '1');
INSERT INTO `project_info` VALUES ('9', '3', '华迪融资项目9（变更风险评估状态）', '2022-02-28 16:09:58', '租赁物9', '供应商9', '110000', '10000', '日', '11', '抵押物9', '抵押类型9', '80000', '', '9', '2', '1');
INSERT INTO `project_info` VALUES ('10', '1', '华迪融资项目10（变更财务评估状态）', '2022-02-28 16:11:32', '租赁物10', '供应商10', '110000', '10000', '日', '11', '抵押物10', '抵押类型10', '70000', '', '10', '3', '1');
INSERT INTO `project_info` VALUES ('11', '2', '华迪融资租赁项目11（变更终审状态）', '2022-02-28 16:13:22', '租赁物11', '供应商11', '110000', '10000', '日', '11', '抵押物11', '抵押类型11', '500000', '', '11', '4', '1');
INSERT INTO `project_info` VALUES ('12', '3', '华迪融资项目12（资产处置财务审批状态）', '2022-02-25 16:15:15', '租赁物12', '供应商12', '40000', '10000', '日', '4', '抵押物12', '抵押类型12', '300000', '', '12', '3', '1');
INSERT INTO `project_info` VALUES ('13', '1', '华迪融资项目13（资产处置最终审批）', '2022-02-25 16:17:54', '租赁物13', '供应商13', '40000', '10000', '日', '4', '抵押物13', '抵押类型13', '48888', '', '13', '4', '1');
INSERT INTO `project_info` VALUES ('14', '2', '华迪融资项目14（资产处置状态）', '2022-02-25 16:20:05', '租赁物14', '供应商14', '40000', '10000', '日', '4', '抵押物14', '抵押类型14', '700000', '', '14', '1', '1');
INSERT INTO `project_info` VALUES ('15', '1', '华迪融资管理项目15（结束状态）', '2022-02-25 16:21:43', '租赁物15', '供应商15', '40000', '10000', '日', '4', '抵押物15', '抵押类型15', '399999', '', '15', '1', '1');
INSERT INTO `project_info` VALUES ('20', '1', 'test', '2022-03-12 17:30:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647077453221.pdf', '1', '1', '1');
INSERT INTO `project_info` VALUES ('21', '1', 'mytest', '2022-03-22 11:31:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647919906736.pdf', '1', '1', '1');
INSERT INTO `project_info` VALUES ('22', '1', 'mytest2', '2022-03-22 21:25:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647955564022.pdf', '7', '1', '1');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:15:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_audit`
-- ----------------------------
DROP TABLE IF EXISTS `project_audit`;
CREATE TABLE `project_audit` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `audit_opinion_boolean` bit(1) DEFAULT NULL COMMENT '审核是否通过（Boolean）',
  `audit_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核意见（string）',
  `auditor_id` int DEFAULT NULL COMMENT '审核员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='项目方案审核——业务经理操作';

-- ----------------------------
-- Records of project_audit
-- ----------------------------
INSERT INTO `project_audit` VALUES ('5', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('6', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('7', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('8', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('9', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('10', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('11', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('12', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('13', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('14', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('15', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('22', '', '111', '4');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `project_alter_application`
-- ----------------------------
DROP TABLE IF EXISTS `project_alter_application`;
CREATE TABLE `project_alter_application` (
  `project_id` int NOT NULL COMMENT '项目id',
  `lease_schedule_unit_price` int DEFAULT NULL COMMENT '变更单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '变更单位时间',
  `lease_schedule_duration` int DEFAULT NULL COMMENT '变更期数',
  `retake_effect_time` datetime DEFAULT NULL COMMENT '再生效时间',
  `risk_evaluator_id` int DEFAULT '2' COMMENT '椋庨櫓璇勪及鍛榠d',
  `risk_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '椋庨櫓璇勪及鎰忚',
  `finance_evaluator_id` int DEFAULT '3' COMMENT '璐㈡斂璇勪及鍛榠d',
  `finance_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '璐㈠姟閮ㄨ瘎浼版剰瑙?',
  `manager_id` int NOT NULL DEFAULT '4' COMMENT '涓氬姟缁忕悊id',
  `manager_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '缁堝鈥斺€斾笟鍔＄粡鐞嗘剰瑙?',
  `if_passed` bit(1) DEFAULT b'0' COMMENT '涓氬姟缁忕悊鏄惁閫氳繃',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='项目变更申请书';

-- ----------------------------
-- Records of project_alter_application
-- ----------------------------
INSERT INTO `project_alter_application` VALUES ('9', '14000', '日', '5', '2022-04-01 17:39:29', '2', 'default', '3', 'default', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('10', '15000', '日', '6', '2022-04-01 17:40:52', '2', '本项目修改方案风险较小', '3', 'default', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('11', '8000', '日', '15', '2022-03-04 17:42:32', '2', '本项目修改方案风险较小', '3', '本项目变更在公司财务承担范围之内', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('12', '12000', '日', '7', '2022-03-07 17:47:02', '2', '本项目修改方案风险较小', '3', '本项目变更在公司财务承担范围之内', '4', '本项目修改方案综合质量较高，同意修改', '');
INSERT INTO `project_alter_application` VALUES ('22', '1', '日', '1', '2022-03-23 05:48:00', '1', 'fff', '3', 'hhh', '4', '111', '');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pay_type`
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type` (
  `id` int NOT NULL COMMENT '缴费类型id',
  `type_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缴费类型描述：1-正常缴费 2-提前缴费 3-补缴 4-购置租赁物',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pay_type
-- ----------------------------
INSERT INTO `pay_type` VALUES ('1', '正常缴费');
INSERT INTO `pay_type` VALUES ('2', '提前缴费');
INSERT INTO `pay_type` VALUES ('3', '补缴');
INSERT INTO `pay_type` VALUES ('4', '购置租赁物');



/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `loan_approval`
-- ----------------------------
DROP TABLE IF EXISTS `loan_approval`;
CREATE TABLE `loan_approval` (
  `project_id` int NOT NULL COMMENT '项目id',
  `audit_opinion_boolean` bit(1) DEFAULT NULL COMMENT '审核是否通过（Boolean）',
  `audit_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核意见（string）',
  `auditor_id` int DEFAULT NULL COMMENT '审核员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='放款审批——财务部职员操作';

-- ----------------------------
-- Records of loan_approval
-- ----------------------------
INSERT INTO `loan_approval` VALUES ('7', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('8', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('9', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('10', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('11', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('12', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('13', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('14', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('15', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('22', '', '1111', '3');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `credit_grade_describe`
-- ----------------------------
DROP TABLE IF EXISTS `credit_grade_describe`;
CREATE TABLE `credit_grade_describe` (
  `id` int NOT NULL COMMENT '信用等级id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信用等级描述：1.优秀 2.良好 3.较差',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of credit_grade_describe
-- ----------------------------
INSERT INTO `credit_grade_describe` VALUES ('1', '优秀');
INSERT INTO `credit_grade_describe` VALUES ('2', '良好');
INSERT INTO `credit_grade_describe` VALUES ('3', '较差');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `credit_grade_describe`
-- ----------------------------
DROP TABLE IF EXISTS `credit_grade_describe`;
CREATE TABLE `credit_grade_describe` (
  `id` int NOT NULL COMMENT '信用等级id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信用等级描述：1.优秀 2.良好 3.较差',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of credit_grade_describe
-- ----------------------------
INSERT INTO `credit_grade_describe` VALUES ('1', '优秀');
INSERT INTO `credit_grade_describe` VALUES ('2', '良好');
INSERT INTO `credit_grade_describe` VALUES ('3', '较差');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cash_flow`
-- ----------------------------
DROP TABLE IF EXISTS `cash_flow`;
CREATE TABLE `cash_flow` (
  `project_id` int NOT NULL COMMENT '项目id',
  `flow_time` datetime NOT NULL COMMENT '资金流动时间记录',
  `amount` int DEFAULT NULL COMMENT '流动金额',
  `pay_account_id` int DEFAULT NULL COMMENT '支付账号id',
  `pay_type_id` int DEFAULT NULL COMMENT '支付类型id',
  `if_paid` bit(1) DEFAULT NULL COMMENT '是否已经缴费',
  PRIMARY KEY (`project_id`,`flow_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cash_flow
-- ----------------------------
INSERT INTO `cash_flow` VALUES ('7', '2022-02-20 16:40:09', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-01 17:07:26', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-02 21:38:42', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-03 21:39:42', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-04 21:40:10', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-02-20 16:41:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-02-28 17:27:38', '10000', '2', '0', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-03-01 17:27:13', '10000', '2', '3', '');
INSERT INTO `cash_flow` VALUES ('9', '2022-02-20 17:00:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('9', '2022-02-28 16:10:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-02-20 16:50:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-02-28 16:15:00', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-01 21:42:34', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-02 21:42:53', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-03 21:43:28', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-04 21:44:06', '10000', '1', '0', '');
INSERT INTO `cash_flow` VALUES ('11', '2022-02-20 16:51:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('11', '2022-02-28 16:18:00', '10000', '2', '1', '');
INSERT INTO `cash_flow` VALUES ('12', '2022-02-20 16:53:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('12', '2022-02-25 16:00:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('13', '2022-02-20 16:54:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('13', '2022-02-25 16:10:00', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('14', '2022-02-20 16:55:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('14', '2022-02-25 16:20:00', '10000', '2', '1', '');
INSERT INTO `cash_flow` VALUES ('15', '2022-02-20 16:58:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('15', '2022-02-25 16:45:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('22', '2022-03-22 21:36:40', '110000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('22', '2022-03-23 05:48:00', '1', '1', '0', '');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:14:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `backstage_crew`
-- ----------------------------
DROP TABLE IF EXISTS `backstage_crew`;
CREATE TABLE `backstage_crew` (
  `staff_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `staff_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工名字',
  `authority_id` int NOT NULL COMMENT '权限名：1.业务员  2.风险评估员  3.财务部职员  4.业务经理  5.法务部职员',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工密码',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `yearly_work_hours` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '从业时间',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of backstage_crew
-- ----------------------------
INSERT INTO `backstage_crew` VALUES ('1', '业务员', '1', '123', '10086', '2');
INSERT INTO `backstage_crew` VALUES ('2', '风险评估员', '2', '123', '10087', '4');
INSERT INTO `backstage_crew` VALUES ('3', '财务部职员', '3', '123', '10089', '5');
INSERT INTO `backstage_crew` VALUES ('4', '业务经理', '4', '123', '10081', '6');
INSERT INTO `backstage_crew` VALUES ('5', '法务部职员', '5', '123', '10083', '9');
INSERT INTO `backstage_crew` VALUES ('6', '业务员test', '1', '123', '10000', '1');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:13:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authority_id` int NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名：1.业务员  2.风险评估员  3.财务部职员  4.业务经理  5.法务部职员',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '业务员');
INSERT INTO `authority` VALUES ('2', '风险评估员');
INSERT INTO `authority` VALUES ('3', '财务部职员');
INSERT INTO `authority` VALUES ('4', '业务经理');
INSERT INTO `authority` VALUES ('5', '法务部职员');

-- ----------------------------
-- Table structure for `backstage_crew`
-- ----------------------------
DROP TABLE IF EXISTS `backstage_crew`;
CREATE TABLE `backstage_crew` (
  `staff_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `staff_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工名字',
  `authority_id` int NOT NULL COMMENT '权限名：1.业务员  2.风险评估员  3.财务部职员  4.业务经理  5.法务部职员',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工密码',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `yearly_work_hours` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '从业时间',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of backstage_crew
-- ----------------------------
INSERT INTO `backstage_crew` VALUES ('1', '业务员', '1', '123', '10086', '2');
INSERT INTO `backstage_crew` VALUES ('2', '风险评估员', '2', '123', '10087', '4');
INSERT INTO `backstage_crew` VALUES ('3', '财务部职员', '3', '123', '10089', '5');
INSERT INTO `backstage_crew` VALUES ('4', '业务经理', '4', '123', '10081', '6');
INSERT INTO `backstage_crew` VALUES ('5', '法务部职员', '5', '123', '10083', '9');
INSERT INTO `backstage_crew` VALUES ('6', '业务员test', '1', '123', '10000', '1');


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:12:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `assets_disposal`
-- ----------------------------
DROP TABLE IF EXISTS `assets_disposal`;
CREATE TABLE `assets_disposal` (
  `project_id` int NOT NULL COMMENT '项目id',
  `pawn_disposal` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '抵押物处理方式：前端注入：归还、变卖、暂存',
  `lease_disposal` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '租赁物处理方式：前端注入：变卖、暂存',
  `appraised_value` int DEFAULT '0' COMMENT '浼板€?',
  `finance_evaluator_id` int NOT NULL DEFAULT '2' COMMENT '璐㈠姟閮ㄤ汉鍛榠d',
  `finance_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '璐㈠姟閮ㄦ剰瑙?',
  `manager_id` int DEFAULT '4' COMMENT '涓氬姟缁忕悊id',
  `manager_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '涓氬姟缁忕悊鎰忚',
  `if_passed` bit(1) DEFAULT b'0' COMMENT '缁忕悊鏄惁鍚屾剰閫氳繃',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='资产处置报告';

-- ----------------------------
-- Records of assets_disposal
-- ----------------------------
INSERT INTO `assets_disposal` VALUES ('8', '归还', null, '111', '3', '111', '4', '111', '');
INSERT INTO `assets_disposal` VALUES ('12', ' 归还', '变卖', '80000', '3', null, '4', null, null);
INSERT INTO `assets_disposal` VALUES ('13', '变卖', '暂存', '70000', '3', '项目资产处置合理，财务方面同意处置', '4', null, null);
INSERT INTO `assets_disposal` VALUES ('14', '归还', '暂存', '100000', '3', '项目资产处置合理，财务方面同意处置', '4', '经调查研究，项目资产处置合理', '');
INSERT INTO `assets_disposal` VALUES ('15', '暂存', '暂存', '60000', '3', '项目资产处置合理，财务方面同意处置', '4', '经调查研究，项目资产处置合理', '');

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authority_id` int NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名：1.业务员  2.风险评估员  3.财务部职员  4.业务经理  5.法务部职员',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '业务员');
INSERT INTO `authority` VALUES ('2', '风险评估员');
INSERT INTO `authority` VALUES ('3', '财务部职员');
INSERT INTO `authority` VALUES ('4', '业务经理');
INSERT INTO `authority` VALUES ('5', '法务部职员');

-- ----------------------------
-- Table structure for `backstage_crew`
-- ----------------------------
DROP TABLE IF EXISTS `backstage_crew`;
CREATE TABLE `backstage_crew` (
  `staff_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `staff_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工名字',
  `authority_id` int NOT NULL COMMENT '权限名：1.业务员  2.风险评估员  3.财务部职员  4.业务经理  5.法务部职员',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '员工密码',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `yearly_work_hours` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '从业时间',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of backstage_crew
-- ----------------------------
INSERT INTO `backstage_crew` VALUES ('1', '业务员', '1', '123', '10086', '2');
INSERT INTO `backstage_crew` VALUES ('2', '风险评估员', '2', '123', '10087', '4');
INSERT INTO `backstage_crew` VALUES ('3', '财务部职员', '3', '123', '10089', '5');
INSERT INTO `backstage_crew` VALUES ('4', '业务经理', '4', '123', '10081', '6');
INSERT INTO `backstage_crew` VALUES ('5', '法务部职员', '5', '123', '10083', '9');
INSERT INTO `backstage_crew` VALUES ('6', '业务员test', '1', '123', '10000', '1');

-- ----------------------------
-- Table structure for `cash_flow`
-- ----------------------------
DROP TABLE IF EXISTS `cash_flow`;
CREATE TABLE `cash_flow` (
  `project_id` int NOT NULL COMMENT '项目id',
  `flow_time` datetime NOT NULL COMMENT '资金流动时间记录',
  `amount` int DEFAULT NULL COMMENT '流动金额',
  `pay_account_id` int DEFAULT NULL COMMENT '支付账号id',
  `pay_type_id` int DEFAULT NULL COMMENT '支付类型id',
  `if_paid` bit(1) DEFAULT NULL COMMENT '是否已经缴费',
  PRIMARY KEY (`project_id`,`flow_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cash_flow
-- ----------------------------
INSERT INTO `cash_flow` VALUES ('7', '2022-02-20 16:40:09', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-01 17:07:26', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-02 21:38:42', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-03 21:39:42', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('7', '2022-03-04 21:40:10', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-02-20 16:41:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-02-28 17:27:38', '10000', '2', '0', '');
INSERT INTO `cash_flow` VALUES ('8', '2022-03-01 17:27:13', '10000', '2', '3', '');
INSERT INTO `cash_flow` VALUES ('9', '2022-02-20 17:00:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('9', '2022-02-28 16:10:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-02-20 16:50:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-02-28 16:15:00', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-01 21:42:34', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-02 21:42:53', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-03 21:43:28', '10000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('10', '2022-03-04 21:44:06', '10000', '1', '0', '');
INSERT INTO `cash_flow` VALUES ('11', '2022-02-20 16:51:00', '110000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('11', '2022-02-28 16:18:00', '10000', '2', '1', '');
INSERT INTO `cash_flow` VALUES ('12', '2022-02-20 16:53:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('12', '2022-02-25 16:00:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('13', '2022-02-20 16:54:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('13', '2022-02-25 16:10:00', '10000', '1', '1', '');
INSERT INTO `cash_flow` VALUES ('14', '2022-02-20 16:55:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('14', '2022-02-25 16:20:00', '10000', '2', '1', '');
INSERT INTO `cash_flow` VALUES ('15', '2022-02-20 16:58:00', '40000', '0', '4', '');
INSERT INTO `cash_flow` VALUES ('15', '2022-02-25 16:45:00', '10000', '3', '1', '');
INSERT INTO `cash_flow` VALUES ('22', '2022-03-22 21:36:40', '110000', '1', '3', '');
INSERT INTO `cash_flow` VALUES ('22', '2022-03-23 05:48:00', '1', '1', '0', '');

-- ----------------------------
-- Table structure for `credit_grade_describe`
-- ----------------------------
DROP TABLE IF EXISTS `credit_grade_describe`;
CREATE TABLE `credit_grade_describe` (
  `id` int NOT NULL COMMENT '信用等级id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信用等级描述：1.优秀 2.良好 3.较差',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of credit_grade_describe
-- ----------------------------
INSERT INTO `credit_grade_describe` VALUES ('1', '优秀');
INSERT INTO `credit_grade_describe` VALUES ('2', '良好');
INSERT INTO `credit_grade_describe` VALUES ('3', '较差');

-- ----------------------------
-- Table structure for `loan_approval`
-- ----------------------------
DROP TABLE IF EXISTS `loan_approval`;
CREATE TABLE `loan_approval` (
  `project_id` int NOT NULL COMMENT '项目id',
  `audit_opinion_boolean` bit(1) DEFAULT NULL COMMENT '审核是否通过（Boolean）',
  `audit_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核意见（string）',
  `auditor_id` int DEFAULT NULL COMMENT '审核员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='放款审批——财务部职员操作';

-- ----------------------------
-- Records of loan_approval
-- ----------------------------
INSERT INTO `loan_approval` VALUES ('7', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('8', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('9', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('10', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('11', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('12', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('13', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('14', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('15', '', '本项目在公司的经济承担范围内，允许放款', '3');
INSERT INTO `loan_approval` VALUES ('22', '', '1111', '3');

-- ----------------------------
-- Table structure for `pay_type`
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type` (
  `id` int NOT NULL COMMENT '缴费类型id',
  `type_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缴费类型描述：1-正常缴费 2-提前缴费 3-补缴 4-购置租赁物',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pay_type
-- ----------------------------
INSERT INTO `pay_type` VALUES ('1', '正常缴费');
INSERT INTO `pay_type` VALUES ('2', '提前缴费');
INSERT INTO `pay_type` VALUES ('3', '补缴');
INSERT INTO `pay_type` VALUES ('4', '购置租赁物');

-- ----------------------------
-- Table structure for `project_alter_application`
-- ----------------------------
DROP TABLE IF EXISTS `project_alter_application`;
CREATE TABLE `project_alter_application` (
  `project_id` int NOT NULL COMMENT '项目id',
  `lease_schedule_unit_price` int DEFAULT NULL COMMENT '变更单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '变更单位时间',
  `lease_schedule_duration` int DEFAULT NULL COMMENT '变更期数',
  `retake_effect_time` datetime DEFAULT NULL COMMENT '再生效时间',
  `risk_evaluator_id` int DEFAULT '2' COMMENT '椋庨櫓璇勪及鍛榠d',
  `risk_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '椋庨櫓璇勪及鎰忚',
  `finance_evaluator_id` int DEFAULT '3' COMMENT '璐㈡斂璇勪及鍛榠d',
  `finance_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '璐㈠姟閮ㄨ瘎浼版剰瑙?',
  `manager_id` int NOT NULL DEFAULT '4' COMMENT '涓氬姟缁忕悊id',
  `manager_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '' COMMENT '缁堝鈥斺€斾笟鍔＄粡鐞嗘剰瑙?',
  `if_passed` bit(1) DEFAULT b'0' COMMENT '涓氬姟缁忕悊鏄惁閫氳繃',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='项目变更申请书';

-- ----------------------------
-- Records of project_alter_application
-- ----------------------------
INSERT INTO `project_alter_application` VALUES ('9', '14000', '日', '5', '2022-04-01 17:39:29', '2', 'default', '3', 'default', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('10', '15000', '日', '6', '2022-04-01 17:40:52', '2', '本项目修改方案风险较小', '3', 'default', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('11', '8000', '日', '15', '2022-03-04 17:42:32', '2', '本项目修改方案风险较小', '3', '本项目变更在公司财务承担范围之内', '4', 'default', '');
INSERT INTO `project_alter_application` VALUES ('12', '12000', '日', '7', '2022-03-07 17:47:02', '2', '本项目修改方案风险较小', '3', '本项目变更在公司财务承担范围之内', '4', '本项目修改方案综合质量较高，同意修改', '');
INSERT INTO `project_alter_application` VALUES ('22', '1', '日', '1', '2022-03-23 05:48:00', '1', 'fff', '3', 'hhh', '4', '111', '');

-- ----------------------------
-- Table structure for `project_audit`
-- ----------------------------
DROP TABLE IF EXISTS `project_audit`;
CREATE TABLE `project_audit` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `audit_opinion_boolean` bit(1) DEFAULT NULL COMMENT '审核是否通过（Boolean）',
  `audit_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核意见（string）',
  `auditor_id` int DEFAULT NULL COMMENT '审核员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='项目方案审核——业务经理操作';

-- ----------------------------
-- Records of project_audit
-- ----------------------------
INSERT INTO `project_audit` VALUES ('5', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('6', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('7', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('8', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('9', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('10', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('11', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('12', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('13', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('14', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('15', '', '本人认为此项目具有很大的效益性', '4');
INSERT INTO `project_audit` VALUES ('22', '', '111', '4');

-- ----------------------------
-- Table structure for `project_info`
-- ----------------------------
DROP TABLE IF EXISTS `project_info`;
CREATE TABLE `project_info` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `applicant_id` int NOT NULL COMMENT '承租人姓名',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名',
  `project_time` datetime NOT NULL COMMENT '项目开始时间',
  `leased_asset` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '租赁物',
  `supplier` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '供应商',
  `price` int NOT NULL COMMENT '价格',
  `lease_schedule_unit_price` int NOT NULL COMMENT '租赁计划 单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '租赁计划 单位时间',
  `lease_schedule_duration` int NOT NULL COMMENT '租赁计划 共计多少单位时间',
  `pawn_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '抵押物名称',
  `pawn_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '抵押物类别',
  `pawn_value` int NOT NULL COMMENT '抵押物现在的价值',
  `certificate` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '涓婁紶鍥剧墖鎴栬€呮枃浠?',
  `state_id` int NOT NULL DEFAULT '2' COMMENT '閻樿埖鈧巩d',
  `current_staff_id` int NOT NULL DEFAULT '1' COMMENT '鍒嗛厤鐨勫憳宸d',
  `sales_id` int DEFAULT '1' COMMENT '璺熻繘鐨勪笟鍔″憳id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_info
-- ----------------------------
INSERT INTO `project_info` VALUES ('1', '1', '华迪融资项目1（立项申请状态）', '2022-04-01 14:17:31', '电脑设备', '华为', '30000', '10000', '日', '3', '房产证书', '房产', '200000', '', '1', '1', '1');
INSERT INTO `project_info` VALUES ('2', '2', '华迪融资项目2（风险评估状态）', '2022-04-01 15:25:08', '租赁物2', '供应商2', '50000', '10000', '日', '5', '抵押物2', '抵押类型2', '3000000', '', '2', '2', '1');
INSERT INTO `project_info` VALUES ('3', '3', '华迪融资项目3（报价评估状态）', '2022-04-01 15:28:21', '租赁物3', '供应商3', '40000', '10000', '日', '4', '抵押物3', '抵押类型3', '400000', '', '3', '3', '1');
INSERT INTO `project_info` VALUES ('4', '1', '华迪融资项目4（项目终审状态）', '2022-04-01 15:33:14', '租赁物4', '供应商4', '60000', '10000', '日', '6', '抵押物4', '抵押类型4', '5000000', '', '4', '4', '1');
INSERT INTO `project_info` VALUES ('5', '2', '华迪融资项目6（合同签约状态）', '2022-04-01 15:36:27', '租赁物5', '供应商5', '30000', '10000', '日', '3', '抵押物5', '抵押类型5', '300000', '', '5', '5', '1');
INSERT INTO `project_info` VALUES ('6', '3', '华迪融资项目7（放款审批）', '2022-04-01 15:57:41', '租赁物6', '供应商6', '30000', '10000', '日', '3', '抵押物6', '抵押类型6', '400000', '', '6', '3', '1');
INSERT INTO `project_info` VALUES ('7', '1', '华迪融资项目7（正常履约）', '2022-02-28 16:02:59', '租赁物7', '供应商7', '110000', '10000', '日', '11', '抵押物7', '抵押类型7', '399999', '', '7', '1', '1');
INSERT INTO `project_info` VALUES ('8', '2', '华迪融资项目8（逾期未交）', '2022-02-28 16:08:19', '租赁物8', '供应商8', '110000', '10000', '日', '11', '抵押物8', '抵押类型8', '20000', '', '15', '1', '1');
INSERT INTO `project_info` VALUES ('9', '3', '华迪融资项目9（变更风险评估状态）', '2022-02-28 16:09:58', '租赁物9', '供应商9', '110000', '10000', '日', '11', '抵押物9', '抵押类型9', '80000', '', '9', '2', '1');
INSERT INTO `project_info` VALUES ('10', '1', '华迪融资项目10（变更财务评估状态）', '2022-02-28 16:11:32', '租赁物10', '供应商10', '110000', '10000', '日', '11', '抵押物10', '抵押类型10', '70000', '', '10', '3', '1');
INSERT INTO `project_info` VALUES ('11', '2', '华迪融资租赁项目11（变更终审状态）', '2022-02-28 16:13:22', '租赁物11', '供应商11', '110000', '10000', '日', '11', '抵押物11', '抵押类型11', '500000', '', '11', '4', '1');
INSERT INTO `project_info` VALUES ('12', '3', '华迪融资项目12（资产处置财务审批状态）', '2022-02-25 16:15:15', '租赁物12', '供应商12', '40000', '10000', '日', '4', '抵押物12', '抵押类型12', '300000', '', '12', '3', '1');
INSERT INTO `project_info` VALUES ('13', '1', '华迪融资项目13（资产处置最终审批）', '2022-02-25 16:17:54', '租赁物13', '供应商13', '40000', '10000', '日', '4', '抵押物13', '抵押类型13', '48888', '', '13', '4', '1');
INSERT INTO `project_info` VALUES ('14', '2', '华迪融资项目14（资产处置状态）', '2022-02-25 16:20:05', '租赁物14', '供应商14', '40000', '10000', '日', '4', '抵押物14', '抵押类型14', '700000', '', '14', '1', '1');
INSERT INTO `project_info` VALUES ('15', '1', '华迪融资管理项目15（结束状态）', '2022-02-25 16:21:43', '租赁物15', '供应商15', '40000', '10000', '日', '4', '抵押物15', '抵押类型15', '399999', '', '15', '1', '1');
INSERT INTO `project_info` VALUES ('20', '1', 'test', '2022-03-12 17:30:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647077453221.pdf', '1', '1', '1');
INSERT INTO `project_info` VALUES ('21', '1', 'mytest', '2022-03-22 11:31:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647919906736.pdf', '1', '1', '1');
INSERT INTO `project_info` VALUES ('22', '1', 'mytest2', '2022-03-22 21:25:00', 'test租赁物', 'test供应商', '110000', '10000', '日', '10', 'test抵押物', 'test抵押物类别', '200000', 'file/certificate/1647955564022.pdf', '7', '1', '1');

-- ----------------------------
-- Table structure for `project_state`
-- ----------------------------
DROP TABLE IF EXISTS `project_state`;
CREATE TABLE `project_state` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目状态id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对状态阶段的描述：\r\n1.立项申请 2.风险评估 3.报价评估 4.项目终审 5.合同签约 6.放款审批 7.正常履约 8.逾期未交 9.变更审批之风险评估 10.变更审批之财务评估 11.变更审批之项目审批（业务经理审批） 12.资产处置审批 13.资产处置 14.结束\r\n',
  `authority_id` int DEFAULT NULL COMMENT '和权限联系，方便项目分配staff',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of project_state
-- ----------------------------
INSERT INTO `project_state` VALUES ('1', '立项申请', '1');
INSERT INTO `project_state` VALUES ('2', '风险评估', '2');
INSERT INTO `project_state` VALUES ('3', '报价评估', '3');
INSERT INTO `project_state` VALUES ('4', '项目终审', '4');
INSERT INTO `project_state` VALUES ('5', '合同签约', '5');
INSERT INTO `project_state` VALUES ('6', '放款审批', '3');
INSERT INTO `project_state` VALUES ('7', '正常履约', '1');
INSERT INTO `project_state` VALUES ('8', '逾期未交', '1');
INSERT INTO `project_state` VALUES ('9', '变更风险评估', '2');
INSERT INTO `project_state` VALUES ('10', '变更财务评估', '3');
INSERT INTO `project_state` VALUES ('11', '变更项目终审', '4');
INSERT INTO `project_state` VALUES ('12', '资产处置财务审批', '3');
INSERT INTO `project_state` VALUES ('13', '资产处置最终审批', '4');
INSERT INTO `project_state` VALUES ('14', '资产处置', '1');
INSERT INTO `project_state` VALUES ('15', '结束', '1');

-- ----------------------------
-- Table structure for `quote_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `quote_evaluation`;
CREATE TABLE `quote_evaluation` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `lease_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '租赁物市场售卖价格',
  `lease_rent_cost` int DEFAULT NULL COMMENT '租赁物市场租赁价格',
  `lease_schedule_unit_price` int DEFAULT NULL COMMENT '建议方案 单位价格',
  `lease_schedule_unit_time` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '建议方案 单位时间',
  `lease_schedule_duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '建议方案 共计多少单位时间',
  `loan_time` datetime DEFAULT NULL COMMENT '建议放款时间',
  `evaluator_id` int DEFAULT NULL COMMENT '评估员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='报价评估——财务部职员操作';

-- ----------------------------
-- Records of quote_evaluation
-- ----------------------------
INSERT INTO `quote_evaluation` VALUES ('4', '65000', '10000', '10000', '日', '6', '2022-03-25 16:34:15', '3');
INSERT INTO `quote_evaluation` VALUES ('5', '30000', '10000', '10000', '日', '3', '2022-03-25 16:37:57', '3');
INSERT INTO `quote_evaluation` VALUES ('6', '30000', '10000', '10000', '日', '3', '2022-03-25 16:38:01', '3');
INSERT INTO `quote_evaluation` VALUES ('7', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:09', '3');
INSERT INTO `quote_evaluation` VALUES ('8', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:15', '3');
INSERT INTO `quote_evaluation` VALUES ('9', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:29', '3');
INSERT INTO `quote_evaluation` VALUES ('10', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:36', '3');
INSERT INTO `quote_evaluation` VALUES ('11', '110000', '10000', '10000', '日', '11', '2022-02-20 16:38:43', '3');
INSERT INTO `quote_evaluation` VALUES ('12', '40000', '10000', '10000', '日', '4', '2022-02-20 16:38:49', '3');
INSERT INTO `quote_evaluation` VALUES ('13', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:02', '3');
INSERT INTO `quote_evaluation` VALUES ('14', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:09', '3');
INSERT INTO `quote_evaluation` VALUES ('15', '40000', '10000', '10000', '日', '4', '2022-02-20 16:39:14', '3');
INSERT INTO `quote_evaluation` VALUES ('22', '1', '1', '10000', '日', '10', '2022-02-02 00:00:00', '3');

-- ----------------------------
-- Table structure for `risk_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `risk_evaluation`;
CREATE TABLE `risk_evaluation` (
  `project_id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `risk_grade_id` int DEFAULT NULL COMMENT '风险等级',
  `risk_evaluation_opinions` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险评估建议',
  `evaluator_id` int DEFAULT NULL COMMENT '评估员id',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='风险评估——风险评估员操作';

-- ----------------------------
-- Records of risk_evaluation
-- ----------------------------
INSERT INTO `risk_evaluation` VALUES ('3', '1', '经本人评估，本项目基本无风险点', '2');
INSERT INTO `risk_evaluation` VALUES ('4', '2', '经本人评估，此项目具有一定的风险，属于低风险项目', '2');
INSERT INTO `risk_evaluation` VALUES ('5', '3', '经本人评估，此项目具有较高的风险，建议重新制定租赁方案', '2');
INSERT INTO `risk_evaluation` VALUES ('6', '1', '经本人评估，本项目基本无风险点', '2');
INSERT INTO `risk_evaluation` VALUES ('7', '2', '经本人评估，此项目具有一定的风险，属于低风险项目', '2');
INSERT INTO `risk_evaluation` VALUES ('8', '3', '经本人评估，此项目具有较高的风险，建议重新制定租赁方案', '2');
INSERT INTO `risk_evaluation` VALUES ('9', '1', '经本人评估，本项目基本无风险点', '2');
INSERT INTO `risk_evaluation` VALUES ('10', '2', '经本人评估，此项目具有一定的风险，属于低风险项目', '2');
INSERT INTO `risk_evaluation` VALUES ('11', '3', '经本人评估，此项目具有较高的风险，建议重新制定租赁方案', '2');
INSERT INTO `risk_evaluation` VALUES ('12', '1', '经本人评估，本项目基本无风险点', '2');
INSERT INTO `risk_evaluation` VALUES ('13', '2', '经本人评估，此项目具有一定的风险，属于低风险项目', '2');
INSERT INTO `risk_evaluation` VALUES ('14', '3', '经本人评估，此项目具有较高的风险，建议重新制定租赁方案', '2');
INSERT INTO `risk_evaluation` VALUES ('15', '1', '经本人评估，本项目基本无风险点', '2');
INSERT INTO `risk_evaluation` VALUES ('22', '1', 'fff', '2');

-- ----------------------------
-- Table structure for `risk_grade_describe`
-- ----------------------------
DROP TABLE IF EXISTS `risk_grade_describe`;
CREATE TABLE `risk_grade_describe` (
  `id` int NOT NULL COMMENT '风险等级id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '风险等级描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of risk_grade_describe
-- ----------------------------
INSERT INTO `risk_grade_describe` VALUES ('1', '无风险');
INSERT INTO `risk_grade_describe` VALUES ('2', '低风险');
INSERT INTO `risk_grade_describe` VALUES ('3', '高风险');

-- ----------------------------
-- Table structure for `sign_contract`
-- ----------------------------
DROP TABLE IF EXISTS `sign_contract`;
CREATE TABLE `sign_contract` (
  `project_id` int NOT NULL,
  `contract_file` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `legal_staff_id` int DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sign_contract
-- ----------------------------
INSERT INTO `sign_contract` VALUES ('7', 'file/contract/1647070677787.pdf', '1');
INSERT INTO `sign_contract` VALUES ('8', 'file/contract/1647001056912.pdf', '1');
INSERT INTO `sign_contract` VALUES ('10', 'file/contract/1647001139334.pdf', '1');
INSERT INTO `sign_contract` VALUES ('22', 'file/contract/1647956176804.pdf', '5');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '承租人id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '承租人name',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '承租人密码',
  `career` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '调查报告时可添加：职业',
  `credit_grade_id` int NOT NULL DEFAULT '0' COMMENT '淇＄敤绛夌骇锛屽繀椤?',
  `salary` int DEFAULT NULL COMMENT '调查报告时可添加：工资',
  `assets` bigint DEFAULT NULL COMMENT '调查报告时可添加：资产情况',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份证号码',
  `credit_evaluation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '淇＄敤绛夌骇璇勪及',
  `recent_bill` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='承租人';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '承租人1号', '123', '公务员', '1', '3600', '500000', '510166200804162029', '当前承租人信用优秀，因为他做人诚实，没有任何逾期记录，拾金不昧', 'file/bill/1647919802374.');
INSERT INTO `user` VALUES ('2', '承租人2号', '123', '个体经营商', '2', '5600', '600000', '501256200006298989', '当前承租人信用良好，存在一些逾期记录', null);
INSERT INTO `user` VALUES ('3', '承租人3号', '123', '无业人员', '3', '2400', '200000', '510189200309272830', '当前承租人信用较差，存在较多逾期记录', null);


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : hwadee

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-22 22:12:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `assets_check`
-- ----------------------------
DROP TABLE IF EXISTS `assets_check`;
CREATE TABLE `assets_check` (
  `project_id` int NOT NULL COMMENT '项目id',
  `check_time` datetime NOT NULL COMMENT '检查时间',
  `check_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '检查对象，前端注入：租赁物or抵押物',
  `check_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '检查结果，前端注入：正常or损坏or遗失',
  `ps` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci COMMENT='资产检查表';

-- ----------------------------
-- Records of assets_check
-- ----------------------------
INSERT INTO `assets_check` VALUES ('7', '2022-02-28 18:39:20', '租赁物', '正常', '抵押物完好');
INSERT INTO `assets_check` VALUES ('7', '2022-03-01 18:40:35', '抵押物', '正常', '抵押物完好');
INSERT INTO `assets_check` VALUES ('7', '2022-03-01 18:49:52', '抵押物', '正常', '抵押物完好');
INSERT INTO `assets_check` VALUES ('8', '2022-03-12 00:25:00', '租赁物', '正常', '11');
INSERT INTO `assets_check` VALUES ('22', '2022-03-23 05:37:00', '租赁物', '正常', '111');

