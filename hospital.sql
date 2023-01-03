/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 03/01/2023 21:15:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `uid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `aid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1668862514984', '1');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `deptno` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deptname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  INDEX `deptno`(`deptno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('10', '内科1');
INSERT INTO `department` VALUES ('20', '外科');
INSERT INTO `department` VALUES ('30', '儿科');
INSERT INTO `department` VALUES ('40', '妇科');
INSERT INTO `department` VALUES ('50', '眼科');
INSERT INTO `department` VALUES ('60', '耳鼻喉科');
INSERT INTO `department` VALUES ('70', '口腔科');
INSERT INTO `department` VALUES ('80', '皮肤科');
INSERT INTO `department` VALUES ('90', '中医科');
INSERT INTO `department` VALUES ('100', '针灸推拿科');
INSERT INTO `department` VALUES ('110', '心理咨询室');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `uid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `did` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dsex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dage` int(0) NULL DEFAULT NULL,
  `dnumber` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deptno` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `deptno`(`deptno`) USING BTREE,
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `doctor_ibfk_2` FOREIGN KEY (`deptno`) REFERENCES `department` (`deptno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1668862550339', '10000', '扁鹊', '男', 44, '15728789999', '10');
INSERT INTO `doctor` VALUES ('1670309910615', '310089', '刘一', '男', 55, '12312312222', '10');
INSERT INTO `doctor` VALUES ('1670309937154', '310198', '陈二', '女', 44, '12321312322', '20');
INSERT INTO `doctor` VALUES ('1670309942795', '310277', '甲乙', '男', 55, '12312312312', '20');
INSERT INTO `doctor` VALUES ('1670309961536', '310339', '丙丁', '男', 66, '12312312333', '30');
INSERT INTO `doctor` VALUES ('1670309968318', '310434', '戊己', '男', 45, '17472747222', '30');
INSERT INTO `doctor` VALUES ('1670309974886', '310498', '癸丑', '女', 45, '14592857642', '40');
INSERT INTO `doctor` VALUES ('1670309981486', '310531', '兰斯', '男', 27, '14567897666', '50');
INSERT INTO `doctor` VALUES ('1670309986804', '310559', '美琴', '女', 21, '12312312333', '70');
INSERT INTO `doctor` VALUES ('1670309996640', '310594', '当麻', '男', 33, '12344444423', '80');
INSERT INTO `doctor` VALUES ('1668772234197', '388628', '华佗', '男', 44, '12312312311', '60');
INSERT INTO `doctor` VALUES ('1668772126621', '662451', '哈迪斯', '男', 22, '33333333333', '100');
INSERT INTO `doctor` VALUES ('1670309950302', '668166', '王武', '男', 66, '12312312333', '110');
INSERT INTO `doctor` VALUES ('1670747046264', '747095', '中医', '男', 33, '33333333333', '90');

-- ----------------------------
-- Table structure for duty
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty`  (
  `did` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dutyday` tinyint(0) NULL DEFAULT NULL,
  `dutytime` tinyint(0) NULL DEFAULT NULL COMMENT '1:上午2：下午',
  `dutyamount` int(0) NULL DEFAULT NULL COMMENT '挂号余额',
  `dutycharge` double NULL DEFAULT NULL,
  INDEX `duty_ibfk_1`(`did`) USING BTREE,
  CONSTRAINT `duty_ibfk_1` FOREIGN KEY (`did`) REFERENCES `doctor` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty
-- ----------------------------
INSERT INTO `duty` VALUES ('10000', 1, 1, 30, 8);
INSERT INTO `duty` VALUES ('10000', 1, 2, 28, 8);
INSERT INTO `duty` VALUES ('10000', 3, 1, 30, 8);
INSERT INTO `duty` VALUES ('10000', 3, 2, 30, 8);
INSERT INTO `duty` VALUES ('10000', 5, 1, 29, 8);
INSERT INTO `duty` VALUES ('10000', 5, 2, 29, 8);
INSERT INTO `duty` VALUES ('10000', 2, 1, 29, 8);
INSERT INTO `duty` VALUES ('10000', 2, 2, 0, 8);
INSERT INTO `duty` VALUES ('10000', 4, 1, 30, 8);
INSERT INTO `duty` VALUES ('388628', 1, 1, 30, 8);
INSERT INTO `duty` VALUES ('388628', 1, 2, 30, 8);
INSERT INTO `duty` VALUES ('388628', 2, 2, 30, 8);
INSERT INTO `duty` VALUES ('388628', 2, 1, 30, 8);
INSERT INTO `duty` VALUES ('388628', 3, 1, 30, 8);
INSERT INTO `duty` VALUES ('388628', 4, 1, 30, 8);
INSERT INTO `duty` VALUES ('662451', 1, 1, 30, 12);
INSERT INTO `duty` VALUES ('662451', 1, 2, 30, 12);
INSERT INTO `duty` VALUES ('662451', 2, 2, 30, 12);
INSERT INTO `duty` VALUES ('662451', 2, 1, 30, 12);
INSERT INTO `duty` VALUES ('662451', 3, 1, 30, 12);
INSERT INTO `duty` VALUES ('310089', 1, 1, 30, 8);
INSERT INTO `duty` VALUES ('310089', 1, 2, 30, 8);
INSERT INTO `duty` VALUES ('310089', 2, 1, 30, 8);
INSERT INTO `duty` VALUES ('310089', 2, 2, 30, 8);
INSERT INTO `duty` VALUES ('310089', 3, 1, 30, 8);
INSERT INTO `duty` VALUES ('310089', 3, 2, 30, 8);
INSERT INTO `duty` VALUES ('310089', 4, 1, 30, 8);
INSERT INTO `duty` VALUES ('310089', 4, 2, 30, 8);
INSERT INTO `duty` VALUES ('310277', 1, 1, 30, 13);
INSERT INTO `duty` VALUES ('310277', 1, 2, 30, 13);
INSERT INTO `duty` VALUES ('310277', 2, 1, 30, 13);
INSERT INTO `duty` VALUES ('310277', 2, 2, 30, 13);
INSERT INTO `duty` VALUES ('310277', 3, 1, 30, 13);
INSERT INTO `duty` VALUES ('310277', 3, 2, 30, 13);
INSERT INTO `duty` VALUES ('668166', 6, 1, 30, 8);
INSERT INTO `duty` VALUES ('668166', 6, 2, 30, 8);
INSERT INTO `duty` VALUES ('668166', 0, 1, 30, 8);
INSERT INTO `duty` VALUES ('668166', 0, 2, 30, 8);
INSERT INTO `duty` VALUES ('310339', 5, 1, 30, 8);
INSERT INTO `duty` VALUES ('310339', 5, 2, 30, 8);
INSERT INTO `duty` VALUES ('310339', 2, 1, 30, 8);
INSERT INTO `duty` VALUES ('310339', 2, 2, 30, 8);
INSERT INTO `duty` VALUES ('310434', 1, 1, 30, 8);
INSERT INTO `duty` VALUES ('310434', 1, 2, 30, 8);
INSERT INTO `duty` VALUES ('310434', 3, 1, 30, 8);
INSERT INTO `duty` VALUES ('310434', 3, 2, 30, 8);
INSERT INTO `duty` VALUES ('310434', 6, 1, 30, 8);
INSERT INTO `duty` VALUES ('310434', 6, 2, 30, 8);
INSERT INTO `duty` VALUES ('310498', 2, 1, 30, 8);
INSERT INTO `duty` VALUES ('310498', 2, 2, 30, 8);
INSERT INTO `duty` VALUES ('310498', 5, 1, 29, 8);
INSERT INTO `duty` VALUES ('310498', 5, 2, 30, 8);
INSERT INTO `duty` VALUES ('310531', 1, 1, 30, 8);
INSERT INTO `duty` VALUES ('310531', 1, 2, 30, 8);
INSERT INTO `duty` VALUES ('310531', 2, 1, 30, 8);
INSERT INTO `duty` VALUES ('310531', 2, 2, 30, 8);
INSERT INTO `duty` VALUES ('310531', 3, 1, 30, 8);
INSERT INTO `duty` VALUES ('310531', 3, 2, 30, 8);
INSERT INTO `duty` VALUES ('310531', 4, 1, 30, 8);
INSERT INTO `duty` VALUES ('310531', 4, 2, 30, 8);
INSERT INTO `duty` VALUES ('747095', 2, 1, 29, 18);
INSERT INTO `duty` VALUES ('747095', 2, 2, 30, 18);
INSERT INTO `duty` VALUES ('747095', 3, 1, 29, 18);
INSERT INTO `duty` VALUES ('747095', 3, 2, 30, 18);

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine`  (
  `medid` int(0) NOT NULL AUTO_INCREMENT,
  `medname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `medamount` int(0) NULL DEFAULT NULL,
  `medprice` double NULL DEFAULT NULL,
  PRIMARY KEY (`medid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medicine
-- ----------------------------
INSERT INTO `medicine` VALUES (1, '连花清瘟胶囊', 485, 29.6);
INSERT INTO `medicine` VALUES (2, '三九感冒灵', 488, 14.9);
INSERT INTO `medicine` VALUES (3, '云南白药创可贴', 493, 9);
INSERT INTO `medicine` VALUES (4, '布洛芬缓释胶囊', 500, 24.5);
INSERT INTO `medicine` VALUES (5, '乳酸菌素片', 100, 26);
INSERT INTO `medicine` VALUES (6, '洛夫联苯咔唑溶液喷雾剂', 100, 33);
INSERT INTO `medicine` VALUES (7, '复方氨酚烷胺片', 200, 17);
INSERT INTO `medicine` VALUES (8, '维生素C片', 294, 3);
INSERT INTO `medicine` VALUES (9, '复合维生素B片', 1000, 6);
INSERT INTO `medicine` VALUES (10, '斯利安叶酸片', 200, 75);
INSERT INTO `medicine` VALUES (11, '多维元素片复合维生素B12', 300, 145);
INSERT INTO `medicine` VALUES (12, '碳酸钙D3片', 300, 120);
INSERT INTO `medicine` VALUES (13, '麝香痔疮膏', 200, 19.5);
INSERT INTO `medicine` VALUES (14, '开塞露', 1000, 13.5);
INSERT INTO `medicine` VALUES (15, '健胃消食片', 360, 16);
INSERT INTO `medicine` VALUES (16, '六味地黄丸', 200, 41);
INSERT INTO `medicine` VALUES (17, '驱风油', 2000, 29.5);
INSERT INTO `medicine` VALUES (18, '左甲状腺纳素片', 200, 30.4);
INSERT INTO `medicine` VALUES (19, '甲巯咪唑片', 400, 30.4);
INSERT INTO `medicine` VALUES (20, '恩格列净片', 300, 39);
INSERT INTO `medicine` VALUES (21, '硫酸羟氯喹片', 400, 30);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuid` int(0) NOT NULL,
  `pid` int(0) NULL DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`menuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, NULL, '患者操作', '病人');
INSERT INTO `menu` VALUES (2, 0, NULL, '医生操作', '医生');
INSERT INTO `menu` VALUES (3, 0, NULL, '管理员操作', '管理员');
INSERT INTO `menu` VALUES (4, 1, '/patient', 'PatientInfo', '个人信息');
INSERT INTO `menu` VALUES (5, 2, '/doctor', 'DoctorInfo', '个人信息');
INSERT INTO `menu` VALUES (6, 1, '/reg', 'PatientReg', '挂号');
INSERT INTO `menu` VALUES (7, 2, '/diagnose', 'DoctorDiagnose', '看诊');
INSERT INTO `menu` VALUES (8, 1, '/pay', 'PayBill', '处方缴费/查看病历');
INSERT INTO `menu` VALUES (9, 2, '/pre_history', 'PreHistory', '看诊记录');
INSERT INTO `menu` VALUES (10, 0, NULL, '药房管理', '药房管理员');
INSERT INTO `menu` VALUES (11, 10, '/medicine', 'MedicineManagement', '药品管理');
INSERT INTO `menu` VALUES (12, 3, '/deptOp', 'DeptOperation', '部门信息管理');
INSERT INTO `menu` VALUES (13, 3, '/doctorOp', 'DoctorOperation', '医生信息管理');
INSERT INTO `menu` VALUES (14, 3, '/patientOp', 'PatientOperation', '病人信息管理');
INSERT INTO `menu` VALUES (15, 3, '/userOp', 'UserOperation', '用户信息管理');
INSERT INTO `menu` VALUES (16, 3, '/medicineOp', 'MedicineOperation', '药品信息管理');
INSERT INTO `menu` VALUES (17, 3, '/preOp', 'PrescriptionOperation', '处方信息管理');
INSERT INTO `menu` VALUES (18, 3, '/regOp', 'RegistrationOperation', '挂号队列管理');
INSERT INTO `menu` VALUES (19, 3, '/purchaseOp', 'PurchaseOperation', '药品进货管理');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `pid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `uid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `psex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `page` int(0) NULL DEFAULT NULL,
  `pnumber` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pallergic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('10000', '10000', '李涛', '男', 20, '15728789523', '头孢拉定');
INSERT INTO `patient` VALUES ('10001', '123', '埼玉', '男', 25, '1333222222', '无');
INSERT INTO `patient` VALUES ('255235', '1670255199650', '波罗斯', '男', 27, '14239281933', '青霉素');
INSERT INTO `patient` VALUES ('307832', '1670307798646', '赵六', '男', 33, '12312312311', '33');
INSERT INTO `patient` VALUES ('368930', '1668772126621', '张三', '男', 22, '13827185100', '青霉素，头孢拉定');
INSERT INTO `patient` VALUES ('624323', '1669624238937', '李四', '女', 30, '15888888882', '无');
INSERT INTO `patient` VALUES ('624511', '1669624417987', '王五', '男', 38, '13123232222', '阿奇霉素');
INSERT INTO `patient` VALUES ('624596', '1669624555388', '赵六', '女', 33, '15875646836', '阿司匹林');
INSERT INTO `patient` VALUES ('626124', '1669624725982', '李世民', '男', 33, '14728492822', '马来酸氯苯那敏');

-- ----------------------------
-- Table structure for pharmacy
-- ----------------------------
DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE `pharmacy`  (
  `preid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `medid` int(0) NULL DEFAULT NULL,
  `medname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` int(0) NULL DEFAULT NULL,
  INDEX `medid`(`medid`) USING BTREE,
  CONSTRAINT `pharmacy_ibfk_1` FOREIGN KEY (`medid`) REFERENCES `medicine` (`medid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pharmacy
-- ----------------------------
INSERT INTO `pharmacy` VALUES ('45953', 1, '连花清瘟胶囊', 6);
INSERT INTO `pharmacy` VALUES ('45953', 4, '布洛芬缓释胶囊', 6);
INSERT INTO `pharmacy` VALUES ('23526', 1, '连花清瘟胶囊', 5);
INSERT INTO `pharmacy` VALUES ('23526', 2, '三九感冒灵', 4);
INSERT INTO `pharmacy` VALUES ('55325', 2, '三九感冒灵', 600);
INSERT INTO `pharmacy` VALUES ('67150', 15, '健胃消食片', 20);
INSERT INTO `pharmacy` VALUES ('67150', 8, '维生素C片', 3);
INSERT INTO `pharmacy` VALUES ('76229', 1, '连花清瘟胶囊', 45);

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `regid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `preid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `did` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `predesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `prestate` tinyint(0) NULL DEFAULT NULL,
  `predate` datetime(0) NULL DEFAULT NULL,
  `totalPrice` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`preid`) USING BTREE,
  INDEX `regid`(`regid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`regid`) REFERENCES `registration` (`regid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES ('68436', '23526', '10001', '10000', '1234', 3, '2022-12-05 06:58:00', 207.60);
INSERT INTO `prescription` VALUES ('24362', '45953', '624323', '10000', '心律不齐', 2, '2022-12-03 05:39:00', 324.60);
INSERT INTO `prescription` VALUES ('55245', '55325', '255235', '10000', '123123', 2, '2022-12-05 15:48:00', 8940.00);
INSERT INTO `prescription` VALUES ('67101', '67150', '10001', '10000', '12312', 3, '2022-12-09 06:25:00', 329.00);
INSERT INTO `prescription` VALUES ('76137', '76229', '10001', '10000', '11111', 2, '2022-12-16 07:37:00', 1332.00);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `medid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `medname` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `puramount` int(0) NULL DEFAULT NULL,
  `purdate` datetime(0) NULL DEFAULT NULL,
  `purid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`purid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration`  (
  `regid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `did` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `regtime` datetime(0) NULL DEFAULT NULL,
  `regstate` tinyint(0) NULL DEFAULT 0 COMMENT '0等待中，1请就诊，2已完成',
  `regdesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`regid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `did`(`did`) USING BTREE,
  CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `registration_ibfk_2` FOREIGN KEY (`did`) REFERENCES `doctor` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of registration
-- ----------------------------
INSERT INTO `registration` VALUES ('05823', '255235', '310498', '2022-12-16 03:00:00', 1, '');
INSERT INTO `registration` VALUES ('090', '10000', '10000', '2022-12-01 11:53:00', 1, '');
INSERT INTO `registration` VALUES ('21996', '307832', '10000', '2022-12-16 02:00:00', 1, '');
INSERT INTO `registration` VALUES ('24362', '624323', '10000', '2022-11-29 08:34:00', 0, '');
INSERT INTO `registration` VALUES ('24537', '624511', '10000', '2022-11-30 08:35:00', 2, '');
INSERT INTO `registration` VALUES ('24627', '624596', '10000', '2022-11-29 08:37:00', 2, '');
INSERT INTO `registration` VALUES ('26164', '626124', '10000', '2022-11-29 09:02:00', 2, '');
INSERT INTO `registration` VALUES ('55245', '255235', '10000', '2022-12-07 15:46:00', 0, '');
INSERT INTO `registration` VALUES ('67101', '10001', '10000', '2022-12-10 06:24:00', 0, '');
INSERT INTO `registration` VALUES ('68436', '10001', '10000', '2022-12-03 08:08:00', 0, '');
INSERT INTO `registration` VALUES ('76137', '10001', '10000', '2022-12-16 08:00:00', 0, '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rolename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roleid` int(0) NOT NULL,
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('病人', 1);
INSERT INTO `role` VALUES ('医生', 2);
INSERT INTO `role` VALUES ('管理员', 3);
INSERT INTO `role` VALUES ('药房管理员', 4);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `roleid` int(0) NULL DEFAULT NULL,
  `menuid` int(0) NULL DEFAULT NULL,
  INDEX `roleid`(`roleid`) USING BTREE,
  INDEX `menuid`(`menuid`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `menu` (`menuid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (2, 7);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (2, 9);
INSERT INTO `role_menu` VALUES (4, 10);
INSERT INTO `role_menu` VALUES (4, 11);
INSERT INTO `role_menu` VALUES (3, 3);
INSERT INTO `role_menu` VALUES (3, 12);
INSERT INTO `role_menu` VALUES (3, 13);
INSERT INTO `role_menu` VALUES (3, 14);
INSERT INTO `role_menu` VALUES (3, 15);
INSERT INTO `role_menu` VALUES (3, 16);
INSERT INTO `role_menu` VALUES (3, 17);
INSERT INTO `role_menu` VALUES (3, 18);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `uname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `upassword` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `uemail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `roleid` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `uname`(`uname`) USING BTREE,
  INDEX `roleid`(`roleid`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000', 'litao', '123456', '913201408@qq.com', 1);
INSERT INTO `user` VALUES ('123', '123', '123', '123', 1);
INSERT INTO `user` VALUES ('1668772126621', '321', '321', '123@12.com', 2);
INSERT INTO `user` VALUES ('1668772234197', 'laptop', '123456', '1234@qq.com', 2);
INSERT INTO `user` VALUES ('1668862514984', 'admin', '123', 'admin@a.com', 3);
INSERT INTO `user` VALUES ('1668862550339', 'doctor', '123', '123@q.com', 2);
INSERT INTO `user` VALUES ('1669624238937', 'user1', '123', '123@123.com', 1);
INSERT INTO `user` VALUES ('1669624417987', 'patient1', '123', '123@123.qq', 1);
INSERT INTO `user` VALUES ('1669624555388', 'patient2', '123', '123@12.com', 1);
INSERT INTO `user` VALUES ('1669624725982', 'p3', '123', '123@12.com', 1);
INSERT INTO `user` VALUES ('1669959551022', 'p5', '123', '123@qq.com', 1);
INSERT INTO `user` VALUES ('1669965077998', '33', '111', '99@qq.com', 1);
INSERT INTO `user` VALUES ('1670225144812', 'pharmacy', '123', '123@ph.com', 4);
INSERT INTO `user` VALUES ('1670255199650', 'patient3', '123', '123@q.com', 1);
INSERT INTO `user` VALUES ('1670307798646', 'patient', '123', '2@1.com', 1);
INSERT INTO `user` VALUES ('1670309910615', 'doc', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309937154', 'doc1', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309942795', 'doc2', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309950302', 'doc3', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309961536', 'doc4', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309968318', 'doc5', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309974886', 'doc6', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309981486', 'doc7', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309986804', 'doc8', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670309996640', 'doc9', '123', '12@q.co', 2);
INSERT INTO `user` VALUES ('1670566964198', '1234', '123', '123@ff.com', 1);
INSERT INTO `user` VALUES ('1670747046264', 'doc22', '123', '123@qq.com', 2);

-- ----------------------------
-- View structure for doc_dept
-- ----------------------------
DROP VIEW IF EXISTS `doc_dept`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `doc_dept` AS select `d`.`uid` AS `uid`,`d`.`did` AS `did`,`d`.`dname` AS `dname`,`d`.`dsex` AS `dsex`,`d`.`dage` AS `dage`,`d`.`dnumber` AS `dnumber`,`d`.`deptno` AS `deptno`,`dept`.`deptname` AS `deptname` from (`doctor` `d` join `department` `dept`) where (`d`.`deptno` = `dept`.`deptno`);

-- ----------------------------
-- View structure for doc_duty
-- ----------------------------
DROP VIEW IF EXISTS `doc_duty`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `doc_duty` AS select `doctor`.`uid` AS `uid`,`doctor`.`did` AS `did`,`doctor`.`dname` AS `dname`,`doctor`.`dsex` AS `dsex`,`doctor`.`dage` AS `dage`,`doctor`.`dnumber` AS `dnumber`,`doctor`.`deptno` AS `deptno`,`duty`.`dutyday` AS `dutyday`,`duty`.`dutyamount` AS `dutyamount`,`duty`.`dutytime` AS `dutytime`,`duty`.`dutycharge` AS `dutycharge` from (`doctor` left join `duty` on((`doctor`.`did` = `duty`.`did`)));

-- ----------------------------
-- View structure for pharmacy_med
-- ----------------------------
DROP VIEW IF EXISTS `pharmacy_med`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pharmacy_med` AS select `ph`.`preid` AS `preid`,`ph`.`medid` AS `medid`,`ph`.`medname` AS `medname`,`ph`.`amount` AS `amount`,`med`.`medamount` AS `medamount`,`pre`.`prestate` AS `prestate` from ((`pharmacy` `ph` join `medicine` `med`) join `prescription` `pre` on(((`ph`.`medid` = `med`.`medid`) and (`ph`.`preid` = `pre`.`preid`))));

-- ----------------------------
-- View structure for prescribe
-- ----------------------------
DROP VIEW IF EXISTS `prescribe`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `prescribe` AS select `pre`.`preid` AS `preid`,`doc`.`dname` AS `dname`,`p`.`pname` AS `pname`,`pre`.`prestate` AS `prestate`,`pre`.`predate` AS `predate`,`pre`.`totalPrice` AS `totalPrice` from ((`prescription` `pre` join `doctor` `doc`) join `patient` `p` on(((`pre`.`pid` = `p`.`pid`) and (`pre`.`did` = `doc`.`did`))));

SET FOREIGN_KEY_CHECKS = 1;
