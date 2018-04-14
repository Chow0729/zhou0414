/*
Navicat MySQL Data Transfer

Source Server         : zhou
Source Server Version : 50511
Source Host           : localhost:3306
Source Database       : grad

Target Server Type    : MYSQL
Target Server Version : 50511
File Encoding         : 65001

Date: 2018-04-14 15:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `grad_category`
-- ----------------------------
DROP TABLE IF EXISTS `grad_category`;
CREATE TABLE `grad_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '食品类别id，主键，自增长',
  `category_name` varchar(20) DEFAULT NULL COMMENT '类别名称',
  `pinyin_name` varchar(50) DEFAULT NULL COMMENT '拼音名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_category
-- ----------------------------
INSERT INTO `grad_category` VALUES ('1', '荤菜', 'huncai', null);
INSERT INTO `grad_category` VALUES ('2', '健康时蔬', 'shucai', null);
INSERT INTO `grad_category` VALUES ('3', '半荤菜', 'banhuncai', null);
INSERT INTO `grad_category` VALUES ('4', '主食', 'zhushi', null);

-- ----------------------------
-- Table structure for `grad_company`
-- ----------------------------
DROP TABLE IF EXISTS `grad_company`;
CREATE TABLE `grad_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `company_name` varchar(128) NOT NULL COMMENT '公司名称',
  `company_eng` varchar(255) DEFAULT NULL COMMENT '公司英文名',
  `tax_no` varchar(32) DEFAULT NULL COMMENT '税号',
  `reg_addr` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `tel` varchar(20) DEFAULT NULL COMMENT '公司固话',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(32) DEFAULT NULL COMMENT '账号',
  `owner` varchar(16) DEFAULT NULL COMMENT '法定人',
  `addr` varchar(255) DEFAULT NULL COMMENT '公司所在详细地址',
  `logo` varchar(255) DEFAULT NULL COMMENT '公司logo url',
  `remark` varchar(600) DEFAULT NULL COMMENT '备注/说明(最多200个汉字)',
  `edit_time` datetime DEFAULT NULL COMMENT '更新时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公司相关信息';

-- ----------------------------
-- Records of grad_company
-- ----------------------------
INSERT INTO `grad_company` VALUES ('1', '天鹅养生苑', 'tian\'e', null, null, null, null, null, null, null, null, null, '2018-03-07 22:39:12', '2018-03-07 22:39:09');

-- ----------------------------
-- Table structure for `grad_customer`
-- ----------------------------
DROP TABLE IF EXISTS `grad_customer`;
CREATE TABLE `grad_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，顾客id,自增长',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '顾客姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `card_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `card_num` varchar(25) DEFAULT NULL COMMENT '证件号码',
  `nationality` varchar(50) DEFAULT NULL COMMENT '国籍',
  `arrive_time` datetime DEFAULT NULL COMMENT '到达时间',
  `leave_time` datetime DEFAULT NULL COMMENT '拟离开时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_customer
-- ----------------------------

-- ----------------------------
-- Table structure for `grad_customer_room`
-- ----------------------------
DROP TABLE IF EXISTS `grad_customer_room`;
CREATE TABLE `grad_customer_room` (
  `customer_room_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '顾客住房登记id,主键，自增长',
  `customer_id` int(11) DEFAULT NULL COMMENT '顾客id,与grad_customer表的主键相关联',
  `room_id` int(11) DEFAULT NULL COMMENT '房间id,与grad_room的主键相关联',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`customer_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_customer_room
-- ----------------------------

-- ----------------------------
-- Table structure for `grad_department`
-- ----------------------------
DROP TABLE IF EXISTS `grad_department`;
CREATE TABLE `grad_department` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门的id',
  `dep_no` varchar(32) NOT NULL COMMENT '部门编号',
  `dep_name` varchar(32) NOT NULL COMMENT '部门名称',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `remark` varchar(600) DEFAULT NULL COMMENT '备注/说明(最多200个汉字)',
  `edit_time` datetime DEFAULT NULL COMMENT '更新时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of grad_department
-- ----------------------------
INSERT INTO `grad_department` VALUES ('1', null, '001', '服务部', '张飒', null, '2018-03-14 20:57:07', '2018-03-14 20:57:10');
INSERT INTO `grad_department` VALUES ('2', null, '002', '餐饮部', null, '', '2018-03-29 22:30:20', null);
INSERT INTO `grad_department` VALUES ('3', null, 'casd', 'fasdfafhiduskhai', null, 'fgasd', '2018-03-29 22:30:28', null);
INSERT INTO `grad_department` VALUES ('4', null, 'casd', 'fasdf', null, 'fgasd', null, null);
INSERT INTO `grad_department` VALUES ('7', null, 'fvasd', 'gfds', null, 'gvdfs', null, null);
INSERT INTO `grad_department` VALUES ('8', null, 'gbsfd', 'ghsfd', null, 'ghsfd', '2018-03-29 22:31:24', '2018-03-29 22:31:20');

-- ----------------------------
-- Table structure for `grad_food`
-- ----------------------------
DROP TABLE IF EXISTS `grad_food`;
CREATE TABLE `grad_food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '食品编号，主键，自增长',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `is_featured` varchar(1) DEFAULT NULL COMMENT '是否特色',
  `packing_fee` double(11,2) DEFAULT NULL COMMENT '打包费用',
  `recent_rating` double(5,1) DEFAULT NULL COMMENT '最近评分',
  `original_price` double(11,2) DEFAULT NULL COMMENT '原价',
  `price` double(11,2) DEFAULT NULL COMMENT '价格',
  `sold_out` varchar(1) DEFAULT NULL COMMENT '是否售完，0表示售完，1表示没有售完',
  `recent_popularity` varchar(10) DEFAULT NULL COMMENT '受欢迎程度',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `min_purchase` int(5) DEFAULT NULL COMMENT '最小购买数量',
  `month_sales` int(5) DEFAULT NULL COMMENT '月销售量',
  `ratinig_count` int(5) DEFAULT NULL COMMENT '评价数量统计',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_food
-- ----------------------------
INSERT INTO `grad_food` VALUES ('1', '/wechat/food/shucai/xiangguqingcai.jpg', '香菇青菜', '0', '2.00', '1.2', '15.00', '15.00', '1', '受欢迎', '100', '1', '33', '14', null, 'haocshadhkaj');
INSERT INTO `grad_food` VALUES ('2', '/wechat/food/huncai/hongshaorou.jpg', '红烧肉', '0', '5.00', null, '38.00', '38.00', null, null, '10', null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('3', '/wechat/food/huncai/xiangchangzhengdan.jpg', '香肠蒸蛋', '0', '2.00', null, '20.00', '20.00', null, null, '10', null, null, null, null, 'savasdfads');
INSERT INTO `grad_food` VALUES ('4', '/wechat/food/huncai/shuizhuroupian.jpg', '水煮肉片', '0', '5.00', null, '38.00', '38.00', null, null, '10', null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('5', '/wechat/food/banhuncai/ganguohuacai.jpg', '干锅花菜', '0', '2.00', null, '25.00', '25.00', null, null, '10', null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('6', '/wechat/food/huncai/hongshaopaigu.jpg', '红烧排骨', '0', '5.00', null, '38.00', '38.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('7', '/wechat/food/huncai/laziji.jpg', '辣子鸡', '0', '5.00', null, '38.00', '38.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('8', '/wechat/food/huncai/xiaochaorou.jpg', '小炒肉', '0', '5.00', null, '38.00', '38.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('9', '/wechat/food/huncai/zhengya.jpg', '蒸鸭', '0', '5.00', null, '38.00', '38.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('10', '/wechat/food/banhuncai/xiaocongchaodan.jpg', '小葱炒蛋', '0', '2.00', null, '20.00', '20.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('11', '/wechat/food/shucai/qingchaowosun.jpg', '清炒莴笋', '0', '2.00', null, '15.00', '15.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('12', '/wechat/food/shucai/jianjiaochaorou.jpg', '尖椒炒肉', '0', '2.00', null, '25.00', '25.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('13', '/wechat/food/shucai/xiangganqincai.jpg', '香干芹菜', '0', '2.00', null, '15.00', '15.00', null, null, null, null, null, null, null, null);
INSERT INTO `grad_food` VALUES ('17', '/wechat/food/shucai/e9d63dfd-6ee2-49a9-8be1-0c70d52a2fc3.png', '保温桶', null, null, null, '514.00', '534.00', null, null, '534', null, null, null, null, '523456');
INSERT INTO `grad_food` VALUES ('18', '/wechat/food/banhuncai/4de4c657-f54a-4309-88b3-b2a28b1eaf9f.jpg', '肉末茄子', null, null, null, '20.00', '22.00', null, null, '10', null, null, null, null, '');
INSERT INTO `grad_food` VALUES ('19', '/wechat/food/zhushi/672f1af0-ae35-4878-ad51-1862b3d2bcc7.jpg', '米饭', null, null, null, '2.00', '2.00', null, null, '100', null, null, null, null, '');
INSERT INTO `grad_food` VALUES ('20', '/wechat/food/zhushi/e9f6dcd8-4482-48e5-84e0-73e533532fbb.jpg', '馒头', null, null, null, '2.00', '2.00', null, null, '100', null, null, null, null, '');

-- ----------------------------
-- Table structure for `grad_food_category`
-- ----------------------------
DROP TABLE IF EXISTS `grad_food_category`;
CREATE TABLE `grad_food_category` (
  `food_catg_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '食品种类id，主键，自增长',
  `food_id` int(11) DEFAULT NULL COMMENT '食品id，外键，与grad_food的主键相关联',
  `category_id` bigint(20) DEFAULT NULL COMMENT '食品类别id，外键，与grad_category表的主键相关联',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`food_catg_id`),
  KEY `FK_Reference_10` (`food_id`),
  KEY `FK_Reference_11` (`category_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`food_id`) REFERENCES `grad_food` (`food_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`category_id`) REFERENCES `grad_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_food_category
-- ----------------------------
INSERT INTO `grad_food_category` VALUES ('1', '1', '2', '2018-04-08 15:33:58');
INSERT INTO `grad_food_category` VALUES ('2', '2', '1', '2018-04-08 15:34:15');
INSERT INTO `grad_food_category` VALUES ('3', '3', '1', '2018-04-08 15:34:35');
INSERT INTO `grad_food_category` VALUES ('4', '4', '1', '2018-04-08 15:34:50');
INSERT INTO `grad_food_category` VALUES ('5', '5', '3', '2018-04-08 15:36:42');
INSERT INTO `grad_food_category` VALUES ('6', '6', '1', '2018-04-08 16:46:55');
INSERT INTO `grad_food_category` VALUES ('7', '7', '1', '2018-04-08 16:47:09');
INSERT INTO `grad_food_category` VALUES ('8', '8', '1', '2018-04-08 16:47:22');
INSERT INTO `grad_food_category` VALUES ('9', '9', '1', '2018-04-08 16:47:31');
INSERT INTO `grad_food_category` VALUES ('10', '10', '3', '2018-04-08 16:46:03');
INSERT INTO `grad_food_category` VALUES ('11', '11', '2', '2018-04-08 16:51:33');
INSERT INTO `grad_food_category` VALUES ('12', '12', '3', '2018-04-08 16:53:37');
INSERT INTO `grad_food_category` VALUES ('13', '13', '2', '2018-04-08 17:38:51');
INSERT INTO `grad_food_category` VALUES ('17', '17', '2', '2018-04-12 21:09:21');
INSERT INTO `grad_food_category` VALUES ('18', '18', '3', '2018-04-13 18:10:36');
INSERT INTO `grad_food_category` VALUES ('19', '19', '4', '2018-04-13 18:17:51');
INSERT INTO `grad_food_category` VALUES ('20', '20', '4', '2018-04-13 18:22:42');

-- ----------------------------
-- Table structure for `grad_order`
-- ----------------------------
DROP TABLE IF EXISTS `grad_order`;
CREATE TABLE `grad_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id，主键，自增长',
  `order_code` varchar(20) DEFAULT NULL COMMENT '订单号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `open_id` varchar(30) DEFAULT NULL COMMENT '下单人的open_id',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '下单人的微信昵称',
  `sum_money` double(11,2) DEFAULT NULL COMMENT '全部商品的总价',
  `cup_number` int(11) DEFAULT NULL COMMENT '购物车商品总数',
  `status` varchar(1) DEFAULT NULL COMMENT '订单状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_order
-- ----------------------------
INSERT INTO `grad_order` VALUES ('1', '0410141949', '2018-04-10 14:22:28', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '73.00', '2', '0', '');
INSERT INTO `grad_order` VALUES ('2', '0410201674', '2018-04-10 20:02:46', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '149.00', '4', '1', '');
INSERT INTO `grad_order` VALUES ('3', '0410202790', '2018-04-10 20:03:20', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '149.00', '4', '0', '');
INSERT INTO `grad_order` VALUES ('4', '0410204562', '2018-04-10 20:07:27', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '149.00', '4', '0', '');
INSERT INTO `grad_order` VALUES ('5', '0411099168', '2018-04-11 09:50:51', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '73.00', '2', '0', '');
INSERT INTO `grad_order` VALUES ('6', '0411104764', '2018-04-11 10:23:54', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '55.00', '2', '0', '');
INSERT INTO `grad_order` VALUES ('7', '0411105905', '2018-04-11 10:50:26', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '35.00', '1', '0', '');
INSERT INTO `grad_order` VALUES ('8', '0412096993', '2018-04-12 09:07:18', 'oKPVn5D5AnzqTXTECGmRY-ri9Oqg', 'Chow', '111.00', '3', '0', '');

-- ----------------------------
-- Table structure for `grad_order_food`
-- ----------------------------
DROP TABLE IF EXISTS `grad_order_food`;
CREATE TABLE `grad_order_food` (
  `order_food_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id，外键，与grad_order表的主键相关联',
  `order_code` varchar(20) DEFAULT NULL COMMENT '订单号',
  `food_id` int(11) DEFAULT NULL COMMENT '商品id，外键，与grad_food表的主键相关联',
  `name` varchar(30) DEFAULT NULL COMMENT '商品名称',
  `price` double(11,2) DEFAULT NULL COMMENT '商品单价',
  `detail` varchar(50) DEFAULT NULL COMMENT '商品规格',
  `number` int(11) DEFAULT NULL COMMENT '商品数量',
  `sum` double(11,2) DEFAULT NULL COMMENT '总价',
  PRIMARY KEY (`order_food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_order_food
-- ----------------------------
INSERT INTO `grad_order_food` VALUES ('1', '1', '0410141949', null, '红烧排骨', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('2', '1', '0410141949', null, '辣子鸡', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('3', '2', '0410201674', null, '水煮肉片', '38.00', '不辣', '3', '38.00');
INSERT INTO `grad_order_food` VALUES ('4', '2', '0410201674', null, '辣子鸡', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('5', '3', '0410202790', null, '水煮肉片', '38.00', '不辣', '3', '38.00');
INSERT INTO `grad_order_food` VALUES ('6', '3', '0410202790', null, '辣子鸡', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('7', '4', '0410204562', null, '水煮肉片', '38.00', '不辣', '3', '38.00');
INSERT INTO `grad_order_food` VALUES ('8', '4', '0410204562', null, '辣子鸡', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('9', '5', '0411099168', null, '辣子鸡', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('10', '5', '0411099168', null, '小炒肉', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('11', '6', '0411104764', null, '小炒肉', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('12', '6', '0411104764', null, '小葱炒蛋', '20.00', '不辣', '1', '20.00');
INSERT INTO `grad_order_food` VALUES ('13', '7', '0411105905', null, '蒸鸭', '38.00', '不辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('14', '8', '0412096993', null, '红烧肉', '38.00', '加辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('15', '8', '0412096993', null, '水煮肉片', '38.00', '微辣', '1', '38.00');
INSERT INTO `grad_order_food` VALUES ('16', '8', '0412096993', null, '红烧肉', '38.00', '不辣', '1', '38.00');

-- ----------------------------
-- Table structure for `grad_resource`
-- ----------------------------
DROP TABLE IF EXISTS `grad_resource`;
CREATE TABLE `grad_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `pid` int(11) DEFAULT '0' COMMENT '本表 resource_id的外键',
  `resource_name` varchar(128) NOT NULL COMMENT '资源名称即菜单名称',
  `resource_url` varchar(255) DEFAULT NULL COMMENT '资源链接即菜单URL',
  `location` varchar(8) NOT NULL COMMENT '菜单显示的位置',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单的图标名称',
  `isenable` int(1) NOT NULL DEFAULT '1' COMMENT '是否可用,1可用,0不可用，默认1',
  `remark` varchar(600) DEFAULT NULL COMMENT '说明或备注(最多200个汉字)',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `style` varchar(255) DEFAULT NULL COMMENT '菜单样式',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`resource_id`),
  KEY `FK_Reference_5` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='资源管理表';

-- ----------------------------
-- Records of grad_resource
-- ----------------------------
INSERT INTO `grad_resource` VALUES ('1', '0', '系统设置', '', 'a', 'fa fa-cog', '1', '', null, '', '2018-03-26 21:27:02', '2018-03-13 20:48:18');
INSERT INTO `grad_resource` VALUES ('2', '1', '用户管理', './user/toUserManage', '1', 'fa fa-user-plus', '1', null, null, null, '2018-03-13 20:49:09', '2018-03-13 20:49:13');
INSERT INTO `grad_resource` VALUES ('3', '1', '角色管理', './role/toRoleManage', '2', 'fa fa-user-secret', '1', null, null, null, '2018-03-13 20:49:41', '2018-03-13 20:49:45');
INSERT INTO `grad_resource` VALUES ('4', '0', '公司管理', '', 'b', 'fa fa-sitemap', '1', '', null, '', null, '2018-03-26 20:59:43');
INSERT INTO `grad_resource` VALUES ('5', '0', '个人中心', '', 'c', '', '1', '', null, '', null, '2018-03-26 20:59:04');
INSERT INTO `grad_resource` VALUES ('6', '5', '修改密码', '', '1', '', '1', '', null, '', null, '2018-03-26 21:02:00');
INSERT INTO `grad_resource` VALUES ('7', '5', '个人资料', '', '2', '', '1', '', null, '', null, '2018-03-26 21:05:25');
INSERT INTO `grad_resource` VALUES ('9', '1', '资源管理', './resource/toResourceManage', '4', 'fa fa-tree', '1', '', null, '', null, '2018-03-28 09:16:44');
INSERT INTO `grad_resource` VALUES ('10', '1', '角色分配', './userRole/toUserRoleManage', '3', 'fa fa-user', '1', '', null, '', null, '2018-03-28 09:26:15');
INSERT INTO `grad_resource` VALUES ('11', '4', '部门管理', './dept/toDeptManage', '1', 'fa fa-desktop', '1', '', null, '', null, '2018-03-28 21:41:54');
INSERT INTO `grad_resource` VALUES ('12', '0', '业务管理', '', 'c', 'fa fa-money', '1', '', null, '', null, '2018-04-01 21:21:03');
INSERT INTO `grad_resource` VALUES ('13', '12', '房间管理', './room/toRoomManage', '1', 'fa fa-building-o', '1', '', null, '', null, '2018-04-01 21:22:20');
INSERT INTO `grad_resource` VALUES ('14', '12', '菜单管理', './food/toFoodManage', '2', 'fa fa-cc-amex', '1', '', null, '', null, '2018-04-10 21:18:00');
INSERT INTO `grad_resource` VALUES ('16', '12', '种类管理', './category/toCategoryManage', '3', 'fa fa-coffee', '1', '', null, '', null, '2018-04-12 22:45:00');
INSERT INTO `grad_resource` VALUES ('17', '12', '订单管理', './order/toOrderManage', '4', 'fa fa-reorder', '1', '', null, '', null, '2018-04-12 22:46:31');

-- ----------------------------
-- Table structure for `grad_role`
-- ----------------------------
DROP TABLE IF EXISTS `grad_role`;
CREATE TABLE `grad_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色代码',
  `remark` varchar(600) DEFAULT NULL COMMENT '备注',
  `isenable` varchar(10) DEFAULT NULL COMMENT '是否可用',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_role
-- ----------------------------
INSERT INTO `grad_role` VALUES ('1', '员工', '0002', '员工', '1', '2018-04-11 17:25:57', '2018-04-11 17:26:00');
INSERT INTO `grad_role` VALUES ('2', '管理员', '0001', '管理员', '1', '2018-04-11 17:26:21', '2018-04-11 17:26:23');
INSERT INTO `grad_role` VALUES ('3', '管理员1', '0003', '发哈几十块的', '1', '2018-04-11 17:17:17', '2018-03-20 21:42:37');

-- ----------------------------
-- Table structure for `grad_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `grad_role_resource`;
CREATE TABLE `grad_role_resource` (
  `role_resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id,外键，与grad_role表的主键相关联',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源id,外键，与grad_resource表的主键相关联',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_resource_id`),
  KEY `FK_Reference_5` (`role_id`),
  KEY `FK_Reference_6` (`resource_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`role_id`) REFERENCES `grad_role` (`role_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`resource_id`) REFERENCES `grad_resource` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_role_resource
-- ----------------------------
INSERT INTO `grad_role_resource` VALUES ('47', '1', '1', '2018-04-11 17:27:24');
INSERT INTO `grad_role_resource` VALUES ('48', '1', '2', '2018-04-11 17:27:33');
INSERT INTO `grad_role_resource` VALUES ('49', '1', '3', '2018-04-11 17:27:43');
INSERT INTO `grad_role_resource` VALUES ('50', '1', '4', '2018-04-11 17:27:54');
INSERT INTO `grad_role_resource` VALUES ('73', '2', '1', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('74', '2', '2', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('75', '2', '3', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('76', '2', '9', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('77', '2', '10', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('78', '2', '4', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('79', '2', '11', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('80', '2', '12', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('81', '2', '13', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('82', '2', '14', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('83', '2', '16', '2018-04-12 22:46:40');
INSERT INTO `grad_role_resource` VALUES ('84', '2', '17', '2018-04-12 22:46:40');

-- ----------------------------
-- Table structure for `grad_room`
-- ----------------------------
DROP TABLE IF EXISTS `grad_room`;
CREATE TABLE `grad_room` (
  `room_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '房间',
  `room_name` varchar(64) DEFAULT NULL COMMENT '房间号',
  `status` varchar(10) DEFAULT NULL COMMENT '房间状态(是否已预订）',
  `room_type` varchar(64) DEFAULT NULL COMMENT '房间类型',
  `room_type_id` int(20) DEFAULT NULL COMMENT '房间类型id,外键，与room表的主键相关联',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`room_id`),
  KEY `FK_Reference_1` (`room_type_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`room_type_id`) REFERENCES `grad_room_type` (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_room
-- ----------------------------

-- ----------------------------
-- Table structure for `grad_room_type`
-- ----------------------------
DROP TABLE IF EXISTS `grad_room_type`;
CREATE TABLE `grad_room_type` (
  `room_type_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '房间类型主键，自增长',
  `room_type_name` varchar(64) DEFAULT NULL COMMENT '房间类型名称',
  `room_num` int(11) DEFAULT NULL COMMENT '该类型房间的最大数量',
  `beds_num` int(11) DEFAULT NULL COMMENT '床位数',
  `room_price` decimal(20,2) DEFAULT NULL COMMENT '房价',
  `status` varchar(20) DEFAULT NULL COMMENT '该类型房源的状态，有房1、无房0、紧张2',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`room_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_room_type
-- ----------------------------
INSERT INTO `grad_room_type` VALUES ('1', '标准间', '5', '2', '158.00', '1', '可以住两个人');
INSERT INTO `grad_room_type` VALUES ('2', 'caskdjhds', '4', '2', '288.00', '1', null);
INSERT INTO `grad_room_type` VALUES ('3', '双人间', '10', '2', '158.00', '1', null);

-- ----------------------------
-- Table structure for `grad_user`
-- ----------------------------
DROP TABLE IF EXISTS `grad_user`;
CREATE TABLE `grad_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(64) DEFAULT NULL COMMENT '用户登录密码',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `marriage` varchar(12) DEFAULT NULL COMMENT '婚姻状况',
  `native_place` varchar(128) DEFAULT NULL COMMENT '籍贯',
  `living_place` varchar(128) DEFAULT NULL COMMENT '现居住地',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `mail` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `isenable` varchar(10) DEFAULT NULL COMMENT '是否可用',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_user
-- ----------------------------
INSERT INTO `grad_user` VALUES ('1', 'admin', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', null, null, '', '', '13845645455', '18257117@139.com', null, '', '1', '', null, '2018-03-13 22:04:55');

-- ----------------------------
-- Table structure for `grad_user_company`
-- ----------------------------
DROP TABLE IF EXISTS `grad_user_company`;
CREATE TABLE `grad_user_company` (
  `uc_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `user_id` int(20) DEFAULT NULL COMMENT '外键，与tp_user的主键关联',
  `company_id` int(11) DEFAULT NULL COMMENT '外键，与tp_company的主键关联',
  `dep_id` int(11) DEFAULT NULL COMMENT '外键，与tp_department的主键关联',
  `emp_no` varchar(32) NOT NULL COMMENT '工号',
  `position` varchar(32) DEFAULT NULL COMMENT '职位',
  `job_title` varchar(32) DEFAULT NULL COMMENT '职称',
  `salary` decimal(14,2) DEFAULT NULL COMMENT '薪资',
  `worked_time` int(1) DEFAULT NULL COMMENT '已经工作时长',
  `entry_time` datetime DEFAULT NULL COMMENT '入职时间',
  `regular_time` datetime DEFAULT NULL COMMENT '转正时间',
  `edit_time` datetime DEFAULT NULL COMMENT '更新时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uc_id`),
  KEY `FK_Reference_8` (`company_id`),
  KEY `FK_Reference_9` (`dep_id`),
  KEY `FK_Reference_7` (`user_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `grad_user` (`user_id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`company_id`) REFERENCES `grad_company` (`company_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`dep_id`) REFERENCES `grad_department` (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户公司相关信息';

-- ----------------------------
-- Records of grad_user_company
-- ----------------------------
INSERT INTO `grad_user_company` VALUES ('1', '1', null, '1', '001', 'vvxc', '', null, null, null, null, null, '2018-03-13 22:04:55');

-- ----------------------------
-- Table structure for `grad_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `grad_user_role`;
CREATE TABLE `grad_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id，外键，与grad_user表的主键相关联',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id，外键，与grad_role表的主键相关联',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_role_id`),
  KEY `FK_Reference_2` (`user_id`),
  KEY `FK_Reference_3` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `grad_user` (`user_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `grad_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grad_user_role
-- ----------------------------
INSERT INTO `grad_user_role` VALUES ('21', '1', '2', '2018-04-11 17:28:49');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) NOT NULL,
  `page` int(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '654', '645');
INSERT INTO `person` VALUES ('2', 'ewr', '324');
INSERT INTO `person` VALUES ('3', 'ffs', '31');
INSERT INTO `person` VALUES ('4', '出生地', '4213');
INSERT INTO `person` VALUES ('5', 'jj', '12');
INSERT INTO `person` VALUES ('6', 'ff', '12');
INSERT INTO `person` VALUES ('7', '11', '11');
INSERT INTO `person` VALUES ('8', '13', '132');
INSERT INTO `person` VALUES ('9', 'dd', '31');
INSERT INTO `person` VALUES ('10', 'dd', '31');
INSERT INTO `person` VALUES ('11', 'ccc', '232');
INSERT INTO `person` VALUES ('12', '订单', '22');
INSERT INTO `person` VALUES ('13', '312432', '4123');
INSERT INTO `person` VALUES ('14', '阿萨德', '321421');
