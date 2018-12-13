/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : gyb_nb

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 27/05/2018 15:18:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

set global log_bin_trust_function_creators=TRUE;

-- 数据字典
-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(20) DEFAULT NULL COMMENT '机构编码',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_DICT_PATH` (`path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('01f06ea6d2564e2d8882279ca5528a6b', '', '0006', '激活', 'checked', 18, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515330085, 0);
INSERT INTO `sys_dict` VALUES ('0235119de18e4fca91463d35bc537185', '3372dc473b2f4088aa23674264925a07', '00090005', '申请完结', 'xmapplystatus3', 60, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1525608048, 0);
INSERT INTO `sys_dict` VALUES ('0f1e9ffa218c445c98f3585806745ec8', '', '0013', '项目账单状态', 'xmbillstatus', 49, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515858340, 0);
INSERT INTO `sys_dict` VALUES ('100ae33c72144f5a988e4bf23eba8b36', '', '0012', '项目状态', 'xminfstatus', 42, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515850716, 0);
INSERT INTO `sys_dict` VALUES ('14b5b08b46eb40c89de436e415cf4d8f', '20a7ad625566484f910bc89d4189a65f', '00030004', '审核不通过', 'gyauth3', 10, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515325700, 0);
INSERT INTO `sys_dict` VALUES ('169097e3189a42caba38e84e7c85eaf7', '20a7ad625566484f910bc89d4189a65f', '00030001', '待审核', 'gyauth0', 7, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1527149126, 0);
INSERT INTO `sys_dict` VALUES ('179dd107fd834522b91d7cb35eabd7fa', '100ae33c72144f5a988e4bf23eba8b36', '00120003', '待雇员确认', 'xminfstatus2', 45, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850788, 0);
INSERT INTO `sys_dict` VALUES ('1e1293d1863048cb8767732bcb02141b', '38053da8c02843048308639e40e883b5', '00080001', '是', 'yes', 26, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515633898, 0);
INSERT INTO `sys_dict` VALUES ('20a7ad625566484f910bc89d4189a65f', '', '0003', '雇员身份验证', 'gyauth', 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515856080, 0);
INSERT INTO `sys_dict` VALUES ('24586250fc304979bb48da1da3bb3b43', 'af2d407e2bf04e91be41173685badb5e', '00100006', '任务书完成状态', 'xmtaskstatus4', 58, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1522561498, 0);
INSERT INTO `sys_dict` VALUES ('32dcc02c7b734e3e87b55a8bff129d19', '66e7badca9c24e4e9c343e43046125c6', '00040001', '本科生', 'stulevel0', 12, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515326027, 0);
INSERT INTO `sys_dict` VALUES ('3372dc473b2f4088aa23674264925a07', '', '0009', '项目申请状态', 'xmapplystatus', 28, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515726283, 0);
INSERT INTO `sys_dict` VALUES ('38053da8c02843048308639e40e883b5', '', '0008', '是否', 'yn', 25, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515633883, 0);
INSERT INTO `sys_dict` VALUES ('39b145b3d8db4cee80c82141d6948cc4', '', '0005', '真假', 'twostatus', 15, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515326282, 0);
INSERT INTO `sys_dict` VALUES ('43503b51dca846378d9c54c76c17774f', 'c9d1bfdfd0a8462393d73d6f0e0ac103', '00110001', '雇员编辑中', 'xmfeedbackstatus0', 37, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515747260, 0);
INSERT INTO `sys_dict` VALUES ('43ec635c839f4216941d3d321d2c27b2', '3372dc473b2f4088aa23674264925a07', '00090003', '申请通过', 'xmapplystatus1', 31, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515726348, 0);
INSERT INTO `sys_dict` VALUES ('4df34b512c4e4b4c85cf0373f55c5890', '01f06ea6d2564e2d8882279ca5528a6b', '00060002', '已激活', 'checked1', 20, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515330106, 0);
INSERT INTO `sys_dict` VALUES ('4eed90d3a67d4cb19c6918b1613dc3ca', '85ea1fe85f5449cd86cbae0c1354f523', '00010001', '****大学', '10497', 2, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517391418, 0);
INSERT INTO `sys_dict` VALUES ('4fdd979a52214949a32e6fd07a5037d9', 'af2d407e2bf04e91be41173685badb5e', '00100002', '任务书申请状态', 'xmtaskstatus2', 34, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1522561473, 0);
INSERT INTO `sys_dict` VALUES ('51671f9a38bd4f86a6811e463ebf2396', '8aff33a7bd024f4eb12852defb0df22b', '00020001', '先生', 'sex0', 4, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515325579, 0);
INSERT INTO `sys_dict` VALUES ('56b6b43c5ed44287bcf5ae001468424e', '100ae33c72144f5a988e4bf23eba8b36', '00120002', '待结算', 'xminfstatus1', 44, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850765, 0);
INSERT INTO `sys_dict` VALUES ('5d7ff1436bb8456bbc9461442716d7ee', 'af2d407e2bf04e91be41173685badb5e', '00100001', '任务书发布阶段', 'xmtaskstatus1', 33, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1522561455, 0);
INSERT INTO `sys_dict` VALUES ('5efb292de7384025bb435ee878a89a2a', '', '0007', '支付方式', 'pay', 21, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515592026, 0);
INSERT INTO `sys_dict` VALUES ('5f6bab0ac0c04c80adad9f52e87fa0b9', '38053da8c02843048308639e40e883b5', '00080002', '否', 'no', 27, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515633912, 0);
INSERT INTO `sys_dict` VALUES ('66e7badca9c24e4e9c343e43046125c6', '', '0004', '学历', 'stulevel', 11, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515325961, 0);
INSERT INTO `sys_dict` VALUES ('68104a03dd33438a8d3225e744b7cbc2', '20a7ad625566484f910bc89d4189a65f', '00030003', '审核通过', 'gyauth2', 9, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515325693, 0);
INSERT INTO `sys_dict` VALUES ('6d1e332e25574e75828ba40e6b6065e6', '100ae33c72144f5a988e4bf23eba8b36', '00120004', '雇员确认，待支付', 'xminfstatus3', 46, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850815, 0);
INSERT INTO `sys_dict` VALUES ('6e5cb9aab81b48e381bf5bf2aedd1e08', '5efb292de7384025bb435ee878a89a2a', '00070003', '银行卡', 'pay2', 24, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515592058, 0);
INSERT INTO `sys_dict` VALUES ('727d2736e1864234a1e4cd83100a6f1e', 'af2d407e2bf04e91be41173685badb5e', '00100005', '任务书跟踪状态', 'xmtaskstatus3', 57, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1522561490, 0);
INSERT INTO `sys_dict` VALUES ('786d1f172d17499aa3ceb3432bb0c123', '100ae33c72144f5a988e4bf23eba8b36', '00120001', '进行中', 'xminfstatus0', 43, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850749, 0);
INSERT INTO `sys_dict` VALUES ('78e15dde627b48b59c9f024a97a19076', '0f1e9ffa218c445c98f3585806745ec8', '00130005', '异常', 'xmbillstatus4', 54, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515928765, 0);
INSERT INTO `sys_dict` VALUES ('7a01f4ca7b9049a895b8b578b0e86061', '39b145b3d8db4cee80c82141d6948cc4', '00050002', '禁用', 'twostatus0', 17, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515326461, 0);
INSERT INTO `sys_dict` VALUES ('8285c37882a44733aa05cfe49e895cd3', '0f1e9ffa218c445c98f3585806745ec8', '00130001', '待填写', 'xmbillstatus0', 50, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515858359, 0);
INSERT INTO `sys_dict` VALUES ('83d465284ab6448bae33b9bd0393ad73', '3372dc473b2f4088aa23674264925a07', '00090001', '等待审核', 'xmapplystatus0', 29, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515726314, 0);
INSERT INTO `sys_dict` VALUES ('85ea1fe85f5449cd86cbae0c1354f523', '', '0001', '学校代码', 'school', 1, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515149469, 0);
INSERT INTO `sys_dict` VALUES ('8980ea26e8d84cc1bbe76bc3ee4cc6db', '0f1e9ffa218c445c98f3585806745ec8', '00130002', '待审核', 'xmbillstatus1', 51, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515858371, 0);
INSERT INTO `sys_dict` VALUES ('8a1b43efd22940ac8c038fc536c78ad3', '100ae33c72144f5a988e4bf23eba8b36', '00120005', '财务已支付', 'xminfstatus4', 47, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850825, 0);
INSERT INTO `sys_dict` VALUES ('8aff33a7bd024f4eb12852defb0df22b', '', '0002', '性别', 'sex', 3, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515325549, 0);
INSERT INTO `sys_dict` VALUES ('a1ef593cd3324cf29f39d8848fea8fc1', 'c9d1bfdfd0a8462393d73d6f0e0ac103', '00110002', '雇员已提交', 'xmfeedbackstatus1', 38, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515747283, 0);
INSERT INTO `sys_dict` VALUES ('ae8043bfd28641eeac328979e887a10f', '66e7badca9c24e4e9c343e43046125c6', '00040002', '研究生', 'stulevel1', 13, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515326046, 0);
INSERT INTO `sys_dict` VALUES ('af2d407e2bf04e91be41173685badb5e', '', '0010', '任务书状态', 'xmtaskstatus', 32, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515738844, 0);
INSERT INTO `sys_dict` VALUES ('b9b5e54adb13461d9fbd05ee83247c4f', '20a7ad625566484f910bc89d4189a65f', '00030002', '认证审核', 'gyauth1', 8, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515325687, 0);
INSERT INTO `sys_dict` VALUES ('bcb51e963061498e8eb9b06e8ccdf4ea', 'c9d1bfdfd0a8462393d73d6f0e0ac103', '00110003', '正在审核中', 'xmfeedbackstatus2', 39, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515747302, 0);
INSERT INTO `sys_dict` VALUES ('c2d6025062894bf0a9bb2f998ada49b2', '0f1e9ffa218c445c98f3585806745ec8', '00130003', '待清算', 'xmbillstatus2', 52, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515928729, 0);
INSERT INTO `sys_dict` VALUES ('c4f350675f9b4871b42840e154b4b803', '85ea1fe85f5449cd86cbae0c1354f523', '00010002', '其他学校', '00000', 55, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517391527, 0);
INSERT INTO `sys_dict` VALUES ('c9d1bfdfd0a8462393d73d6f0e0ac103', '', '0011', '项目反馈状态', 'xmfeedbackstatus', 36, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515747234, 0);
INSERT INTO `sys_dict` VALUES ('cf6df2abadf24b72a665665fc0b34386', '5efb292de7384025bb435ee878a89a2a', '00070001', '支付宝', 'pay0', 22, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515592043, 0);
INSERT INTO `sys_dict` VALUES ('d25a5ebe4d014b0788d4753e05874f7a', '66e7badca9c24e4e9c343e43046125c6', '00040003', '博士生', 'stulevel2', 14, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515326053, 0);
INSERT INTO `sys_dict` VALUES ('d524044261bc45189701924c1bb72970', '8aff33a7bd024f4eb12852defb0df22b', '00020002', '女士', 'sex1', 5, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515325589, 0);
INSERT INTO `sys_dict` VALUES ('d88c80340cdd41c6824784618cbf228c', 'af2d407e2bf04e91be41173685badb5e', '00100003', '任务书编辑阶段', 'xmtaskstatus0', 35, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1522561433, 0);
INSERT INTO `sys_dict` VALUES ('db249a5a44934c8687df7075f1f7901c', '0f1e9ffa218c445c98f3585806745ec8', '00130004', '已清算', 'xmbillstatus3', 53, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515928745, 0);
INSERT INTO `sys_dict` VALUES ('ee142f0bb9b54b3da14b18d6e009b1ec', '3372dc473b2f4088aa23674264925a07', '00090004', '申请不通过', 'xmapplystatus2', 59, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1525608027, 0);
INSERT INTO `sys_dict` VALUES ('ee256b48bd55494e89c18cc438768cf0', '39b145b3d8db4cee80c82141d6948cc4', '00050001', '启用', 'twostatus1', 16, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515326449, 0);
INSERT INTO `sys_dict` VALUES ('f2e43aa807c047329167f69b934783b9', 'c9d1bfdfd0a8462393d73d6f0e0ac103', '00110005', '最终反馈', 'xmfeedbackstatus4', 41, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515747346, 0);
INSERT INTO `sys_dict` VALUES ('f8d822a6c3bc4f96b9cd51e8386358a1', '5efb292de7384025bb435ee878a89a2a', '00070002', '微信', 'pay1', 23, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515592051, 0);
INSERT INTO `sys_dict` VALUES ('f9d3404a9c4646fea8e34fba79817ff8', 'c9d1bfdfd0a8462393d73d6f0e0ac103', '00110004', '审核完成', 'xmfeedbackstatus3', 40, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515747332, 0);
INSERT INTO `sys_dict` VALUES ('f9f34c511d754687b9fe7c8555a95faf', '100ae33c72144f5a988e4bf23eba8b36', '00120006', '项目异常', 'xminfstatus5', 48, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515850844, 0);
INSERT INTO `sys_dict` VALUES ('fc07e6f087a0417981b692312f15db10', '01f06ea6d2564e2d8882279ca5528a6b', '00060001', '未激活', 'checked0', 19, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515330097, 0);
COMMIT;
-- ----------------------------
-- Function structure for getDicnameByCode
-- ----------------------------
DROP FUNCTION IF EXISTS `getDicnameByCode`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getDicnameByCode`( diccode VARCHAR(32) ) RETURNS varchar(32) CHARSET utf8
BEGIN
    RETURN ( SELECT sys_dict.name FROM  sys_dict where sys_dict.code = diccode);
  END;
;;
delimiter ;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `siteid` varchar(32) DEFAULT NULL COMMENT '站点ID',
  `shopid` varchar(32) DEFAULT NULL COMMENT '预留商城ID',
  `title` varchar(120) DEFAULT NULL COMMENT '文章标题',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `info` varchar(500) DEFAULT NULL COMMENT '文章简介',
  `author` varchar(50) DEFAULT NULL COMMENT '文章作者',
  `picurl` varchar(255) DEFAULT NULL COMMENT '标题图',
  `content` text COMMENT '文章内容',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `publishAt` bigint(36) DEFAULT NULL COMMENT '发布时间',
  `endAt` bigint(36) DEFAULT NULL COMMENT '截至时间',
  `status` int(32) DEFAULT '0' COMMENT '同步状态',
  `view_num` int(32) DEFAULT '0' COMMENT '浏览量',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `channelId` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `siteid` varchar(32) DEFAULT NULL COMMENT '站点ID',
  `shopid` varchar(32) DEFAULT NULL COMMENT '预留商城ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '栏目名称',
  `code` varchar(100) DEFAULT NULL COMMENT '栏目标识',
  `type` varchar(20) DEFAULT NULL COMMENT '栏目类型',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `isShow` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_CHANNEL` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_class_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_class_link`;
CREATE TABLE `cms_class_link` (
  `classId` varchar(32) DEFAULT NULL,
  `linkId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(120) DEFAULT NULL COMMENT '链接名称',
  `type` varchar(20) DEFAULT NULL COMMENT '链接类型',
  `picurl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `classId` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_link_class
-- ----------------------------
DROP TABLE IF EXISTS `cms_link_class`;
CREATE TABLE `cms_link_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(120) DEFAULT NULL COMMENT '分类名称',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `shopid` varchar(32) DEFAULT NULL COMMENT '预留商城ID',
  `site_name` varchar(120) DEFAULT NULL COMMENT '名称',
  `site_domain` varchar(120) DEFAULT NULL COMMENT '域名',
  `site_icp` varchar(120) DEFAULT NULL COMMENT 'ICP',
  `site_logo` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `site_wap_logo` varchar(255) DEFAULT NULL COMMENT 'WAPLOGO',
  `site_qq` varchar(20) DEFAULT NULL COMMENT '客服QQ',
  `site_email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `site_tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `weibo_name` varchar(50) DEFAULT NULL COMMENT '微博',
  `weibo_url` varchar(255) DEFAULT NULL COMMENT '微博地址',
  `weibo_qrcode` varchar(255) DEFAULT NULL COMMENT '微博二维码',
  `wechat_name` varchar(50) DEFAULT NULL COMMENT '微信名称',
  `wechat_id` varchar(50) DEFAULT NULL COMMENT '微信ID',
  `wechat_qrcode` varchar(255) DEFAULT NULL COMMENT '微信二维码',
  `seo_keywords` varchar(255) DEFAULT NULL COMMENT '关键词',
  `seo_description` varchar(255) DEFAULT NULL COMMENT '描述',
  `footer_content` text COMMENT '底部版权',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gy_auth
-- ----------------------------
DROP TABLE IF EXISTS `gy_auth`;
CREATE TABLE `gy_auth` (
  `id` varchar(32) NOT NULL COMMENT '认证单号',
  `gyid` varchar(32) DEFAULT NULL COMMENT '雇员编号',
  `reAuthTime` int(32) DEFAULT NULL COMMENT '认证时间',
  `idcardF` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `idcardB` varchar(255) DEFAULT NULL COMMENT '身份证方面',
  `stuCardF` varchar(255) DEFAULT NULL COMMENT '学生证正面',
  `stuCardB` varchar(255) DEFAULT NULL COMMENT '学生证背面',
  `note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gy_inf
-- ----------------------------
DROP TABLE IF EXISTS `gy_inf`;
CREATE TABLE `gy_inf` (
  `id` varchar(32) NOT NULL COMMENT '雇员编号',
  `userid` varchar(32) DEFAULT NULL COMMENT '登陆名',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `birthday` int(32) DEFAULT NULL COMMENT '出生日期',
  `sex` int(32) DEFAULT NULL COMMENT '性别',
  `idcard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `college` varchar(20) DEFAULT NULL COMMENT '就读学校',
  `school` varchar(20) DEFAULT NULL COMMENT '所在学院',
  `major` varchar(20) DEFAULT NULL COMMENT '就读专业',
  `regYear` int(32) DEFAULT NULL COMMENT '注册年份',
  `stuLevel` int(32) DEFAULT NULL COMMENT '最高学历',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gy_pay
-- ----------------------------
DROP TABLE IF EXISTS `gy_pay`;
CREATE TABLE `gy_pay` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `gyid` varchar(32) DEFAULT NULL COMMENT '雇员编号',
  `payid` varchar(32) DEFAULT NULL COMMENT '账号编号',
  `payname` varchar(32) DEFAULT NULL COMMENT '收款人',
  `type` int(32) DEFAULT NULL COMMENT '种类',
  `first` tinyint(1) DEFAULT NULL COMMENT '主要付款方式',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gy_skill
-- ----------------------------
DROP TABLE IF EXISTS `gy_skill`;
CREATE TABLE `gy_skill` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `gyid` varchar(32) DEFAULT NULL COMMENT '雇员id',
  `skillid` varchar(32) DEFAULT NULL COMMENT '技能id',
  `skilllevel` int(32) DEFAULT NULL COMMENT '技能等级',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lib_skill
-- ----------------------------
DROP TABLE IF EXISTS `lib_skill`;
CREATE TABLE `lib_skill` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '技能名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '技能别称',
  `unitcode` varchar(20) DEFAULT NULL COMMENT '技能编码',
  `note` varchar(255) DEFAULT NULL COMMENT '技能介绍',
  `website` varchar(100) DEFAULT NULL COMMENT '链接网站',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lib_skill
-- ----------------------------
BEGIN;
INSERT INTO `lib_skill` VALUES ('05f1fb4f8a9744059a5d729536c369a4', '465e29e2069e4637915acbc9aa17375b', '00040001', '软件使用', NULL, 'sk_b00', '软件类使用的技能', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548088, 0);
INSERT INTO `lib_skill` VALUES ('0f84b3bc05514930a9d574efc5a10e29', '05f1fb4f8a9744059a5d729536c369a4', '000400010011', '字幕编辑器使用', NULL, 'sk_b0011', '任意一款主流的SRT格式字幕编辑器能熟练操作', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548383, 0);
INSERT INTO `lib_skill` VALUES ('15359942f1ea4d83be7cf67d20355eed', '05f1fb4f8a9744059a5d729536c369a4', '000400010004', 'AE软件使用', NULL, 'sk_b0004', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548184, 0);
INSERT INTO `lib_skill` VALUES ('1674a9844e16410c909a1e33acfd2a63', 'f5aa208c5fb3458499611fbc14c849e2', '000500010001', '设计类大类入门测试', NULL, 'sk_t0001', '测试设计类所有需要共性考察的元素，包括知识点和心理、态度等各方面', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548662, 0);
INSERT INTO `lib_skill` VALUES ('1b219d2c61704e448acbc32cffb373ac', 'f5aa208c5fb3458499611fbc14c849e2', '000500010004', '开发类大类入门测试', NULL, 'sk_t0004', '测试开发类需要共性考察的元素，包括知识点和心理、态度等各方面', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548751, 0);
INSERT INTO `lib_skill` VALUES ('21f1d334472e486e9bd0e471e1e14a11', 'a8083bdf907d4acfb7c222468ccea445', '000400020001', '摄影', NULL, 'sk_b0101', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548523, 0);
INSERT INTO `lib_skill` VALUES ('23258f37f221445b8518ad2aaa11d448', '6da94920b29141a99fac4e3134f6a323', '000400040003', 'C语言基础语法', NULL, 'sk_b0303', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517549406, 0);
INSERT INTO `lib_skill` VALUES ('2a39e7e4094646f58c75baa8e5792a82', '05f1fb4f8a9744059a5d729536c369a4', '000400010009', 'PPT软件使用', NULL, 'sk_b0009', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548284, 0);
INSERT INTO `lib_skill` VALUES ('306df82a34e5412a960586cbe53f3e92', '05f1fb4f8a9744059a5d729536c369a4', '000400010005', 'PR软件使用', NULL, 'sk_b0005', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548198, 0);
INSERT INTO `lib_skill` VALUES ('35ecab8ff2274ef5add7b28e6fddaa10', '9f8cf1b1c12b4a9893d1f7bef9a8aea3', '000400030001', '手绘', NULL, 'sk_b0201', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548946, 0);
INSERT INTO `lib_skill` VALUES ('438a2f6b576b4dce9767d95d7766f32a', 'f5aa208c5fb3458499611fbc14c849e2', '000500010003', '文案类大类入门测试', NULL, 'sk_t0003', '测试文案类所有需要共性考察的元素，包括知识点和心理、态度等各方面', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548728, 0);
INSERT INTO `lib_skill` VALUES ('44c1340186f0482c88bea5edbac7d3c6', 'f5aa208c5fb3458499611fbc14c849e2', '000500010002', '影音类大类入门测试', NULL, 'sk_t0002', '测试影音类所有需要共性考察的元素，包括知识点和心理、态度等各方面', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548709, 0);
INSERT INTO `lib_skill` VALUES ('465e29e2069e4637915acbc9aa17375b', '', '0004', '基础技能', NULL, 'sk_b', '基础性的技能', NULL, 3, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548066, 0);
INSERT INTO `lib_skill` VALUES ('48bacaf01690439381bcef73d2e33945', '05f1fb4f8a9744059a5d729536c369a4', '000400010003', 'CorelDRAW使用', NULL, 'sk_b0003', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548166, 0);
INSERT INTO `lib_skill` VALUES ('4cce53dd51be42309a8b70651e3d1814', 'a8083bdf907d4acfb7c222468ccea445', '000400020002', '摄像', NULL, 'sk_b0102', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548530, 0);
INSERT INTO `lib_skill` VALUES ('655bab457e324d258057dfa89f324537', '05f1fb4f8a9744059a5d729536c369a4', '000400010012', '微信后台排版辅助工具使用', NULL, 'sk_b0012', '微信后台排版辅助工具使用（秀米、135等）', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548880, 0);
INSERT INTO `lib_skill` VALUES ('6da94920b29141a99fac4e3134f6a323', '465e29e2069e4637915acbc9aa17375b', '00040004', '开发编程', NULL, 'sk_b03', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517549263, 0);
INSERT INTO `lib_skill` VALUES ('7a18f52aeb644ecf8d74dbb2c9dd8911', '6da94920b29141a99fac4e3134f6a323', '000400040001', 'HTML标签语言', NULL, 'sk_b0301', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517549350, 0);
INSERT INTO `lib_skill` VALUES ('7b620c85530f4e218a60e4cabfc6ce5a', '05f1fb4f8a9744059a5d729536c369a4', '000400010001', 'PS软件使用', NULL, 'sk_b0001', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548131, 0);
INSERT INTO `lib_skill` VALUES ('842238939b6c4e0097d995410e56b477', '05f1fb4f8a9744059a5d729536c369a4', '000400010010', 'WORD软件使用', NULL, 'sk_b0010', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548300, 0);
INSERT INTO `lib_skill` VALUES ('87ff776877814b51ba0c00bb1e09e9f7', '05f1fb4f8a9744059a5d729536c369a4', '000400010006', 'C4D软件使用', NULL, 'sk_b0006', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548233, 0);
INSERT INTO `lib_skill` VALUES ('9d623e2119704811a6d38c1ac6eee5b1', '05f1fb4f8a9744059a5d729536c369a4', '000400010002', 'AI软件使用', NULL, 'sk_b0002', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548146, 0);
INSERT INTO `lib_skill` VALUES ('9f8cf1b1c12b4a9893d1f7bef9a8aea3', '465e29e2069e4637915acbc9aa17375b', '00040003', '知识性技能', NULL, 'sk_b02', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548895, 0);
INSERT INTO `lib_skill` VALUES ('a8083bdf907d4acfb7c222468ccea445', '465e29e2069e4637915acbc9aa17375b', '00040002', '设备操作', NULL, 'sk_b01', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548503, 0);
INSERT INTO `lib_skill` VALUES ('b4c24b699fcd487c9a642f00bb124d47', '', '0006', 'testskill', NULL, 'tsskill', 'this is only for test', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1525605491, 0);
INSERT INTO `lib_skill` VALUES ('c10a3d90c44d443286af1719c427c692', '6da94920b29141a99fac4e3134f6a323', '000400040005', 'C#基础', NULL, 'sk_b0305', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517549452, 0);
INSERT INTO `lib_skill` VALUES ('c96faef9627e4ed88173db2d6602bf8a', '6da94920b29141a99fac4e3134f6a323', '000400040004', 'JAVA语言基础', NULL, 'sk_b0304', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517549429, 0);
INSERT INTO `lib_skill` VALUES ('d439bc089cf442fb980f31fd13b5e667', 'f5aa208c5fb3458499611fbc14c849e2', '000500010005', '线下类大类入门测试', NULL, 'sk_t0005', '测试线下类需要共性考察的元素，包括知识点和心理、态度等各方面 （比如线下可以侧重沟通、组织、表达等能力）', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548763, 0);
INSERT INTO `lib_skill` VALUES ('dcd23d763f964909b53e60aaee53429f', '05f1fb4f8a9744059a5d729536c369a4', '000400010007', '会声会影使用', NULL, 'sk_b0007', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548316, 0);
INSERT INTO `lib_skill` VALUES ('e06ff24402ab4a849c12867b4b7a1402', '', '0005', '业务准入测试', NULL, 'sk_t', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548573, 0);
INSERT INTO `lib_skill` VALUES ('f5aa208c5fb3458499611fbc14c849e2', 'e06ff24402ab4a849c12867b4b7a1402', '00050001', '入门测试', NULL, 'sk_t00', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517548673, 0);
INSERT INTO `lib_skill` VALUES ('f71eb335124a47aebfd70be7cbc7982b', '05f1fb4f8a9744059a5d729536c369a4', '000400010008', 'AU软件使用', NULL, 'sk_b0008', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517548327, 0);
INSERT INTO `lib_skill` VALUES ('fb32fc6899884725b61a4f582d6c6929', '6da94920b29141a99fac4e3134f6a323', '000400040002', 'CSS3基础', NULL, 'sk_b0302', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517549373, 0);
COMMIT;

-- ----------------------------
-- Table structure for lib_task
-- ----------------------------
DROP TABLE IF EXISTS `lib_task`;
CREATE TABLE `lib_task` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '技能名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '技能别称',
  `unitcode` varchar(20) DEFAULT NULL COMMENT '技能编码',
  `note` varchar(255) DEFAULT NULL COMMENT '技能介绍',
  `website` varchar(100) DEFAULT NULL COMMENT '链接网站',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lib_task
-- ----------------------------
BEGIN;
INSERT INTO `lib_task` VALUES ('01131299128241d7bc63872f38e6f0d2', '839964ff0a4c40b2bfff963f3bfd1173', '000600010002', '微信图文稿', NULL, 'wa0102', '微信图文推广稿', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906199, 0);
INSERT INTO `lib_task` VALUES ('058dd6e2cfa842fe97c2ec2edb2984ce', '', '0009', ' test', NULL, 'ts', 'this is only for test', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1525605470, 0);
INSERT INTO `lib_task` VALUES ('11ea547a5b014e7b996891754960ffc6', '881a2c886f5b4545ad7a40c492dc306a', '000300030004', 'APP页面设计', NULL, 'sj0304', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398788, 0);
INSERT INTO `lib_task` VALUES ('1b4a387cafda4b0e91c5440ebcc8ce3c', '881a2c886f5b4545ad7a40c492dc306a', '000300030003', '手机端网页设计', NULL, 'sj0303', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398826, 0);
INSERT INTO `lib_task` VALUES ('21df710599e348a7a3792051bfbe31be', '435004152c234b4aaca7c415aa7e7971', '000700030005', '移动WEB开发', NULL, 'yy0304', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517897948, 0);
INSERT INTO `lib_task` VALUES ('25d0f763898a4115a1a24e28f2d1abda', 'abe2a77d448c4429996b253f65904ee6', '00050003', '后期特效', NULL, 'yy03', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895515, 0);
INSERT INTO `lib_task` VALUES ('29c0d8b1e30c4387b8d3f83cb7e7d934', 'eba1c63c2beb46a5a99a12d3520c9685', '00030001', '平面设计', NULL, 'sj01', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517397414, 0);
INSERT INTO `lib_task` VALUES ('316ebd1bf12942129bcf375f7e34b540', 'e15df45aadf54d2f91edba02a77b6f3d', '000700020001', '网站后台开发', NULL, 'kf0201', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517896008, 0);
INSERT INTO `lib_task` VALUES ('33e044b8dc754969a7e679719b276414', 'f4c11eb47b36474c83f9d04686404cb8', '000600030001', '中译英', NULL, 'wa0301', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906330, 0);
INSERT INTO `lib_task` VALUES ('358031dba16248c8bb5201f74c5e8f7a', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010003', '宣传单设计', NULL, 'sj0103', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398602, 0);
INSERT INTO `lib_task` VALUES ('421e562182004ceea98267c2f36a37b1', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010008', '产品包装设计', NULL, 'sj0108', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398640, 0);
INSERT INTO `lib_task` VALUES ('435004152c234b4aaca7c415aa7e7971', '991c6f76ca6b468cb9a31be841d31078', '00070003', '移动应用开发', NULL, 'kf03', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517897761, 0);
INSERT INTO `lib_task` VALUES ('48cf82bfdcc84c4aa7cf39dc8eadf7bb', 'fdee48a4c1164b64bf4da23567bd3677', '000800010001', '小学家教', NULL, 'xx0101', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906389, 0);
INSERT INTO `lib_task` VALUES ('4f760d1ff47b4b46a912d63a7b93852c', '435004152c234b4aaca7c415aa7e7971', '000700030001', 'iOS应用开发', NULL, 'yy0301', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517897831, 0);
INSERT INTO `lib_task` VALUES ('56370558571f4dba85e508131dee4c5f', '839964ff0a4c40b2bfff963f3bfd1173', '000600010003', '品牌推广软文', NULL, 'wa0103', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906219, 0);
INSERT INTO `lib_task` VALUES ('5b4c150907db4ed2a7d17b99f1621b7c', 'e11867424a434d6bbf2737f330e3d8cc', '000500010001', '中文字幕听写、对轴', NULL, 'yy0101', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895429, 0);
INSERT INTO `lib_task` VALUES ('5da6c38671174d4aa78bf8ea96d470d5', 'abe2a77d448c4429996b253f65904ee6', '00050004', '录音配音', NULL, 'yy04', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895531, 0);
INSERT INTO `lib_task` VALUES ('5dcd541117de4484a6dff8b512ed1bd8', 'e11867424a434d6bbf2737f330e3d8cc', '000500010002', '英文字幕听写、对轴', NULL, 'yy0102', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895442, 0);
INSERT INTO `lib_task` VALUES ('5e4cae1e8a1246d1b4cb3d5e17f2f0a7', 'e11867424a434d6bbf2737f330e3d8cc', '000500010003', '中英文字幕听写、对轴', NULL, 'yy0103', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895451, 0);
INSERT INTO `lib_task` VALUES ('66d8b474cd3a4094a64b9143e87cef70', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010006', '易拉宝设计', NULL, 'sj0106', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398622, 0);
INSERT INTO `lib_task` VALUES ('67e8d47193244dd5a6e1fc8750a5ecd2', '82795bc0447f44f89b0734f71ed5030b', '000600020002', '产品介绍', NULL, 'wa0202', '产品介绍、定位、故事、文案', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906301, 0);
INSERT INTO `lib_task` VALUES ('77d1af0185474bde8e996e27797887b5', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010004', '海报设计', NULL, 'sj0104', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398608, 0);
INSERT INTO `lib_task` VALUES ('7d605921b65a4c4fb3c72400bbceba56', '435004152c234b4aaca7c415aa7e7971', '000700030003', '微信小程序开发', NULL, 'yy0303', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517897898, 0);
INSERT INTO `lib_task` VALUES ('82795bc0447f44f89b0734f71ed5030b', 'e147e70e5bdb48e79453f0a349685c3f', '00060002', '品牌策划', NULL, 'wa02', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517906044, 0);
INSERT INTO `lib_task` VALUES ('833ba3a2e2454aac8e7c216c32471d8c', 'fdee48a4c1164b64bf4da23567bd3677', '000800010003', '高中家教', NULL, 'xx0103', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906412, 0);
INSERT INTO `lib_task` VALUES ('839964ff0a4c40b2bfff963f3bfd1173', 'e147e70e5bdb48e79453f0a349685c3f', '00060001', '文稿撰写', NULL, 'wa01', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517906021, 0);
INSERT INTO `lib_task` VALUES ('881a2c886f5b4545ad7a40c492dc306a', 'eba1c63c2beb46a5a99a12d3520c9685', '00030003', 'UI设计', NULL, 'sj03', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517398739, 0);
INSERT INTO `lib_task` VALUES ('8c5f0e14f6b54ef0bfccbf9aef965476', '991c6f76ca6b468cb9a31be841d31078', '00070001', '前端开发', NULL, 'kf01', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895863, 0);
INSERT INTO `lib_task` VALUES ('991c6f76ca6b468cb9a31be841d31078', '', '0007', '开发类', NULL, 'kf', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895608, 0);
INSERT INTO `lib_task` VALUES ('9d4af8c46c944040a1cce4bc48d85336', 'f4c11eb47b36474c83f9d04686404cb8', '000600030002', '英译中', NULL, 'wa0302', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906350, 0);
INSERT INTO `lib_task` VALUES ('a19189adfeff4f27bdadf6afce525b47', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010001', 'LOGO设计', NULL, 'sj0101', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398557, 0);
INSERT INTO `lib_task` VALUES ('a721e1e0a47b46efb796f3b56f199d28', '', '0008', '线下类', NULL, 'xx', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895618, 0);
INSERT INTO `lib_task` VALUES ('a879470d08cf4c7da3df31540b675741', '82795bc0447f44f89b0734f71ed5030b', '000600020001', '品牌策划', NULL, 'wa0201', '企业简介、品牌故事、品牌定位、口号', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906254, 0);
INSERT INTO `lib_task` VALUES ('abe2a77d448c4429996b253f65904ee6', '', '0005', '影音类', NULL, 'yy', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895303, 0);
INSERT INTO `lib_task` VALUES ('b7af4be5f70b457eb78665f235c5d45a', 'abe2a77d448c4429996b253f65904ee6', '00050002', '后期剪辑', NULL, 'yy02', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895488, 0);
INSERT INTO `lib_task` VALUES ('b885288f10df4e21be71f709812972c3', '839964ff0a4c40b2bfff963f3bfd1173', '000600010001', '讲稿撰写', NULL, 'wa0101', '主持词、竞选词、演讲稿等', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906096, 0);
INSERT INTO `lib_task` VALUES ('c6928384894b42d0b952bd2f179e8233', 'eba1c63c2beb46a5a99a12d3520c9685', '00030002', 'PPT设计', NULL, 'sj02', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517398688, 0);
INSERT INTO `lib_task` VALUES ('cc1a20149bc646bbabf289812191b0c2', 'e15df45aadf54d2f91edba02a77b6f3d', '000700020002', '网站程序开发', NULL, 'kf0202', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517896027, 0);
INSERT INTO `lib_task` VALUES ('cf8b1dfdcafd421e8907e523bcc40eff', 'c6928384894b42d0b952bd2f179e8233', '000300020002', 'PPT美化', NULL, 'sj0202', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398725, 0);
INSERT INTO `lib_task` VALUES ('dbaf112362394bcf8b9aafa864096f15', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010007', '书籍封面设计', NULL, 'sj0107', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398631, 0);
INSERT INTO `lib_task` VALUES ('e01931c6571341bc978d1c90a3f82df2', 'fdee48a4c1164b64bf4da23567bd3677', '000800010002', '初中家教', NULL, 'xx0102', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517906399, 0);
INSERT INTO `lib_task` VALUES ('e11867424a434d6bbf2737f330e3d8cc', 'abe2a77d448c4429996b253f65904ee6', '00050001', '字幕制作', NULL, 'yy01', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895340, 0);
INSERT INTO `lib_task` VALUES ('e147e70e5bdb48e79453f0a349685c3f', '', '0006', '文案类', NULL, 'wa', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895596, 0);
INSERT INTO `lib_task` VALUES ('e15df45aadf54d2f91edba02a77b6f3d', '991c6f76ca6b468cb9a31be841d31078', '00070002', '后台及程序开发', NULL, 'kf02', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517895986, 0);
INSERT INTO `lib_task` VALUES ('e5692554d96c4075990bf1467957c879', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010005', '展板设计', NULL, 'sj0105', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398615, 0);
INSERT INTO `lib_task` VALUES ('e6e302424b794dc784e1376a9b2b9f1c', 'c6928384894b42d0b952bd2f179e8233', '000300020001', 'PPT排版', NULL, 'sj0201', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398718, 0);
INSERT INTO `lib_task` VALUES ('e7bd5bc305c4431eab02fc974e6bb159', '881a2c886f5b4545ad7a40c492dc306a', '000300030002', 'PC端网页设计', NULL, 'sj0302', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398764, 0);
INSERT INTO `lib_task` VALUES ('eaf297aa1fed41058f06a5a3ebb0043d', '881a2c886f5b4545ad7a40c492dc306a', '000300030006', 'H5页面设计', NULL, 'sj0306', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398811, 0);
INSERT INTO `lib_task` VALUES ('eba1c63c2beb46a5a99a12d3520c9685', '', '0003', '设计类', NULL, 'sj', '设计类', NULL, 3, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517396963, 0);
INSERT INTO `lib_task` VALUES ('ec8d45c52aa6431fbedaf69117ad801e', '881a2c886f5b4545ad7a40c492dc306a', '000300030005', '微信小程序页面设计', NULL, 'sj0305', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398799, 0);
INSERT INTO `lib_task` VALUES ('f2b1acc7e21049ec80e3c45c102256a7', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010002', '企业VI设计', NULL, 'sj0102', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398566, 0);
INSERT INTO `lib_task` VALUES ('f3482762cc95432980c4a8558c0ca1d6', '881a2c886f5b4545ad7a40c492dc306a', '000300030001', 'ICON设计', NULL, 'sj0301', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398752, 0);
INSERT INTO `lib_task` VALUES ('f4c11eb47b36474c83f9d04686404cb8', 'e147e70e5bdb48e79453f0a349685c3f', '00060003', '翻译', NULL, 'wa03', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517906063, 0);
INSERT INTO `lib_task` VALUES ('f5be2da13dc7474ca473914cce5a7c7a', '29c0d8b1e30c4387b8d3f83cb7e7d934', '000300010009', '图文设计排版', NULL, 'sj0109', '微信图文消息、长微博、网站新闻图文内容等', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517398655, 0);
INSERT INTO `lib_task` VALUES ('f608589f92c849fbb80dcf395fa396d0', 'abe2a77d448c4429996b253f65904ee6', '00050005', '摄影摄像', NULL, 'yy05', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517895571, 0);
INSERT INTO `lib_task` VALUES ('f94e9ff7a3644bb1a59b35cf051e3b6b', '435004152c234b4aaca7c415aa7e7971', '000700030002', '安卓应用开发', NULL, 'yy0302', '', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517897846, 0);
INSERT INTO `lib_task` VALUES ('fdee48a4c1164b64bf4da23567bd3677', 'a721e1e0a47b46efb796f3b56f199d28', '00080001', '家教推荐', NULL, 'xx01', '', NULL, 6, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1517906365, 0);
COMMIT;

-- ----------------------------
-- Table structure for lib_task_skill
-- ----------------------------
DROP TABLE IF EXISTS `lib_task_skill`;
CREATE TABLE `lib_task_skill` (
  `taskId` varchar(32) DEFAULT NULL,
  `skillId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lib_task_skill
-- ----------------------------
BEGIN;
INSERT INTO `lib_task_skill` VALUES ('1d2f6ef4ff56455ba3fe13c807375d5e', '465e29e2069e4637915acbc9aa17375b');
INSERT INTO `lib_task_skill` VALUES ('1d2f6ef4ff56455ba3fe13c807375d5e', '931c1229bf0344eba5bc0cdf8b8a358a');
INSERT INTO `lib_task_skill` VALUES ('4f75f987031b40cea1bd8696b1281071', '931c1229bf0344eba5bc0cdf8b8a358a');
INSERT INTO `lib_task_skill` VALUES ('0de296624b564c2688da1d3d0d58cead', 'ebba34dff3e340fe92145d7b3555052f');
INSERT INTO `lib_task_skill` VALUES ('058dd6e2cfa842fe97c2ec2edb2984ce', 'b4c24b699fcd487c9a642f00bb124d47');
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `configKey` varchar(100) NOT NULL,
  `configValue` varchar(100) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`configKey`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES ('AppApiDomain', 'http://localhost:9001', '系统Api域名', '405a28c9389d4a8581a29c283dc9f5b9', 1526386116, 0);
INSERT INTO `sys_config` VALUES ('AppDomain', 'www.guyongbang.cn', '系统域名', '', 1510391361, 0);
INSERT INTO `sys_config` VALUES ('AppName', '雇佣帮', '系统名称', '', 1510391361, 0);
INSERT INTO `sys_config` VALUES ('AppShrotName', 'Gyb', '系统短名称', '', 1510391361, 0);
INSERT INTO `sys_config` VALUES ('AppUploadPath', 'upload', '文件上传文件夹', '', 1510391361, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_log_
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_`;
CREATE TABLE `sys_log_` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '创建昵称',
  `type` varchar(20) DEFAULT NULL COMMENT '日志类型',
  `tag` varchar(50) DEFAULT NULL COMMENT '日志标识',
  `src` varchar(255) DEFAULT NULL COMMENT '执行类',
  `ip` varchar(255) DEFAULT NULL COMMENT '来源IP',
  `msg` text COMMENT '日志内容',
  `param` text COMMENT '请求结果',
  `result` text COMMENT '执行结果',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '菜单别名',
  `type` varchar(10) DEFAULT NULL COMMENT '资源类型',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `target` varchar(50) DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `isShow` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `note` varchar(255) DEFAULT NULL COMMENT '菜单介绍',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_MENU_PATH` (`path`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_MENU_PREM` (`permission`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('02e86a61e99746bea34236ea73dd52a5', '', '0003', 'CMS', 'CMS', 'menu', '', '', '', 1, 1, 'cms', NULL, 85, 1, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468895671, 0);
INSERT INTO `sys_menu` VALUES ('0706112ff5dc46e388064a99bcdb0561', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030004', '关键词回复', 'Keyword', 'menu', '/platform/wx/reply/conf/keyword', 'data-pjax', '', 1, 0, 'wx.reply.keyword', NULL, 76, 0, '', 1467472362, 0);
INSERT INTO `sys_menu` VALUES ('0772a3f1f877424c80a34f8bc9da0268', 'b225e7eafec846f29023cca78f63bc18', '0001000100040002', '修改菜单', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.menu.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('077cb6be4c7c41cc8955ee045a4f0286', '68cdbf694f71445c8587a20234d6fe31', '0003000300020001', '添加链接', 'Add', 'data', '', '', '', 0, 0, 'cms.link.link.add', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468897043, 0);
INSERT INTO `sys_menu` VALUES ('09147fe6c278458cb77c4162fff447e3', '1c8a24e0cf094e8e99346aa8b0e4391b', '00080001', '技能库管理', NULL, 'menu', '/platform/lib/skill', '', '', 1, 0, 'lib.skill', NULL, 44, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511164426, 0);
INSERT INTO `sys_menu` VALUES ('0a43d291e0c94ad88c8b690009279e34', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020004', '保存排序', 'Save', 'data', '', '', '', 0, 0, 'wx.conf.menu.sort', NULL, 0, 0, '', 1467474314, 0);
INSERT INTO `sys_menu` VALUES ('0a6e43782e8342f0aeb2703912df88a9', 'e7287128c8a5470081ed782a282d30c6', '0001000100020002', '修改用户', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.user.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('0a972ce655cb4c84809d58668b655900', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020002', '修改图文', 'Edit', 'data', '', '', '', 0, 0, 'wx.reply.news.edit', NULL, 0, 0, '', 1467473596, 0);
INSERT INTO `sys_menu` VALUES ('0b097e9dbad649048f956355d1e91278', '5adfb7b54d784242a7c524734a10d4bd', '000100010009', '应用管理', 'App', 'menu', '/platform/sys/api', 'data-pjax', NULL, 1, 0, 'sys.manager.api', NULL, 63, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('0cc1b6bedbc648a2bb8cab0ca2bbf02c', '60d42fa10a804c23a502aa06d786182a', '000800020003', '任务种类删除', NULL, 'data', '', '', '', 1, 0, 'lib.task.delete', NULL, 51, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511167216, 0);
INSERT INTO `sys_menu` VALUES ('0e73d7b9cf8443c1a48ad197bc9a65ec', '298f7a5f36574102a87268d7ffbe77d6', '000400010003', '雇员信息删除', NULL, 'data', '', '', '', 0, 0, 'platform.gy.inf.delete', NULL, 19, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510537795, 0);
INSERT INTO `sys_menu` VALUES ('0ec22065b0134f0091d5ce9f92e83183', '60d42fa10a804c23a502aa06d786182a', '000800020002', '任务种类编辑', NULL, 'data', '', '', '', 0, 0, 'lib.task.edit', NULL, 50, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511167135, 0);
INSERT INTO `sys_menu` VALUES ('10c8ae20fa6942c5b54a95df03270273', '5adfb7b54d784242a7c524734a10d4bd', '000100010007', '定时任务', 'Task', 'menu', '/platform/sys/task', 'data-pjax', NULL, 1, 0, 'sys.manager.task', NULL, 61, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('1385ae887e5c4b8aa33fbf228be7f907', '6afc5075913d4df4b44a6476080e35a0', '000200050001', '模板编号', 'Id', 'menu', '/platform/wx/tpl/id', 'data-pjax', '', 1, 0, 'wx.tpl.id', NULL, 81, 0, '', 1470406854, 0);
INSERT INTO `sys_menu` VALUES ('15921e56ce7c463e94d2e2b40473bd1d', '46210709bff149d78d4d7914cb9daa72', '00050004', '个人中心', NULL, 'menu', '/platform/gy/person', '', '', 1, 0, 'gy.person', NULL, 36, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515139913, 0);
INSERT INTO `sys_menu` VALUES ('15c6c2d81a954601833726705fef48de', 'b225e7eafec846f29023cca78f63bc18', '0001000100040003', '删除菜单', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.menu.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('1734e586e96941268a4c5248b593cef9', 'f426468abf714b1599729f8c36ebbb0d', '0002000200010001', '回复消息', 'Reply', 'data', '', '', '', 0, 0, 'wx.msg.user.reply', NULL, 0, 0, '', 1467473127, 0);
INSERT INTO `sys_menu` VALUES ('17500ef3a9e44b4fabb240162a164fcb', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020003', '删除文章', 'Delete', 'data', '', '', '', 0, 0, 'cms.content.article.delete', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896170, 0);
INSERT INTO `sys_menu` VALUES ('1765a77196994d55972ae3e9428021c8', 'e28eca57f8f54fe893eed7400afd4a17', '00090001', '申请任务', NULL, 'menu', '/platform/xm/person/applyindex', '', '', 1, 0, 'platform.xm.person.applyindex', NULL, 29, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515242246, 0);
INSERT INTO `sys_menu` VALUES ('17e1ee23ca1443f1bc886c2f5eb7c24b', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030002', '图文内容', 'News', 'menu', '/platform/wx/reply/news', 'data-pjax', '', 1, 0, 'wx.reply.news', NULL, 74, 0, '', 1467471926, 0);
INSERT INTO `sys_menu` VALUES ('1c8a24e0cf094e8e99346aa8b0e4391b', '', '0008', '平台基础数据', NULL, 'menu', '', '', '', 1, 0, 'sys.lib', NULL, 43, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515139820, 0);
INSERT INTO `sys_menu` VALUES ('1e222a5a4b394a92bf3127d34301f8a2', '83951e13c14643898e06b39cb428ec86', '000400030001', '增加雇员交易方式', NULL, 'data', '', '', '', 0, 0, 'platform.gy.pay.add', NULL, 25, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511234193, 0);
INSERT INTO `sys_menu` VALUES ('1f6f98ceb052434cb5ea86ad84ddc971', '5adfb7b54d784242a7c524734a10d4bd', '000100010006', '日志管理', 'Log', 'menu', '/platform/sys/log', 'data-pjax', NULL, 1, 0, 'sys.manager.log', NULL, 60, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('1faa04cc182a4849b94a99286d972b0d', '', '0004', '平台雇员', NULL, 'menu', '', '', '', 1, 0, 'sys.gy', NULL, 15, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515139793, 0);
INSERT INTO `sys_menu` VALUES ('2275cb125710414e91b617dd7c62f12c', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020001', '添加图文', 'add', 'data', '', '', '', 0, 0, 'wx.reply.news.add', NULL, 0, 0, '', 1467473585, 0);
INSERT INTO `sys_menu` VALUES ('234f8ec3c2bc42bf9f6202aecae36fd6', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030001', '文本内容', 'Txt', 'menu', '/platform/wx/reply/txt', 'data-pjax', '', 1, 0, 'wx.reply.txt', NULL, 73, 0, '', 1467471884, 0);
INSERT INTO `sys_menu` VALUES ('23846cd4025e485d9371705cc22a94c6', 'ed6ed141d14443b8a08ac4d941c7bb2e', '000700070001', '立项管理员权限', NULL, 'data', '', '', '', 0, 0, 'platform.xm.inf.manager', NULL, 95, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515748642, 0);
INSERT INTO `sys_menu` VALUES ('24a47951159749459729dd1146229ea7', 'e7287128c8a5470081ed782a282d30c6', '0001000100020003', '删除用户', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.user.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('27e62ac8b9024c029e7f9851f5313eab', '46210709bff149d78d4d7914cb9daa72', '00050003', '交易信息', NULL, 'menu', '/platform/gy/person/payindex', '', '', 1, 0, 'gy.person.pay', NULL, 35, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511246116, 0);
INSERT INTO `sys_menu` VALUES ('298f7a5f36574102a87268d7ffbe77d6', '1faa04cc182a4849b94a99286d972b0d', '00040001', '雇员信息列表', NULL, 'menu', '/platform/gy/inf', '', '', 1, 0, 'platform.gy.inf', NULL, 16, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510538954, 0);
INSERT INTO `sys_menu` VALUES ('2a63040409094f1e9dc535dd78ce15b7', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030003', '删除绑定', 'Delete', 'data', '', '', '', 0, 0, 'wx.reply.follow.delete', NULL, 0, 0, '', 1467474080, 0);
INSERT INTO `sys_menu` VALUES ('2cb327ad59b140828fd26eb2a46cb948', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030003', '关注自动回复', 'Follow', 'menu', '/platform/wx/reply/conf/follow', 'data-pjax', '', 1, 0, 'wx.reply.follow', NULL, 75, 0, '', 1467472280, 0);
INSERT INTO `sys_menu` VALUES ('2dc85d8f3bb041918b35e0883db79a4c', '1f6f98ceb052434cb5ea86ad84ddc971', '0001000100060001', '清除日志', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.log.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('2fab774f8b6d40cb9d7e187babab2d91', 'bcf64d623fdd4519ae345b7a08c071a1', '000200040002', '菜单配置', 'Menu', 'menu', '/platform/wx/conf/menu', 'data-pjax', '', 1, 0, 'wx.conf.menu', NULL, 79, 0, '', 1467472649, 0);
INSERT INTO `sys_menu` VALUES ('3050a2d827814a84a45b392166f6bfce', '31aae024cd5d47999aa9e73becc14cb5', '000400020001', '雇员身份审核增加', NULL, 'data', '', '', '', 0, 0, 'platform.gy.auth.add', NULL, 21, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510569758, 0);
INSERT INTO `sys_menu` VALUES ('3099f497480c4b1987bce3f3a26c3fb4', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020003', '群发消息', 'Push', 'data', '', '', '', 0, 0, 'wx.msg.mass.pushNews', NULL, 0, 0, '', 1467473400, 0);
INSERT INTO `sys_menu` VALUES ('309dc29ad3c34408a68df8f867a5c9ff', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010001', '添加帐号', 'Add', 'data', '', '', '', 0, 0, 'wx.conf.account.add', NULL, 0, 0, '', 1467474187, 0);
INSERT INTO `sys_menu` VALUES ('30a5e70a1456447ebf90b5546e9bc321', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030002', '修改绑定', 'Edit', 'data', '', '', '', 0, 0, 'wx.reply.follow.edit', NULL, 0, 0, '', 1467474056, 0);
INSERT INTO `sys_menu` VALUES ('31aae024cd5d47999aa9e73becc14cb5', '1faa04cc182a4849b94a99286d972b0d', '00040002', '雇员身份审核', NULL, 'menu', '/platform/gy/auth', '', '', 1, 0, 'platform.gy.auth', NULL, 20, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1510569719, 0);
INSERT INTO `sys_menu` VALUES ('31ed2243077c44448cce26abfd5ae574', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010002', '修改分类', 'Edit', 'data', '', '', '', 0, 0, 'cms.link.class.edit', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896957, 0);
INSERT INTO `sys_menu` VALUES ('329094c727f949ca8a22728f2ec560ff', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030003', '删除角色', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.role.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('33aed9298643424783116e0cf0f7fcbe', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020001', '添加文章', 'Add', 'data', '', '', '', 0, 0, 'cms.content.article.add', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896151, 0);
INSERT INTO `sys_menu` VALUES ('36ce16235ac14cebbdfd7a4ddcd02a7b', '', '0001', '系统', 'System', 'menu', '', '', '', 1, 0, 'sys', '系统', 53, 1, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('36e0faf5062b4f6b95d4167cbb1f8fea', '68cdbf694f71445c8587a20234d6fe31', '0003000300020002', '修改链接', 'Edit', 'data', '', '', '', 0, 0, 'cms.link.link.edit', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468897051, 0);
INSERT INTO `sys_menu` VALUES ('3888f05aa4064f788ba7ec51c495ce7c', '1385ae887e5c4b8aa33fbf228be7f907', '0002000500010002', '删除编号', 'Delete', 'data', '', '', '', 0, 0, 'wx.tpl.id.delete', NULL, 0, 0, '', 1470407068, 0);
INSERT INTO `sys_menu` VALUES ('3b2b0c0ae215448f9ab7e53503cec4d6', '437b5771ab0a42ec886a69e050c74a52', '0001000100050001', '添加参数', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.conf.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('3c24111091ad4a70ad2d9cc361311d2f', '68cdbf694f71445c8587a20234d6fe31', '0003000300020003', '删除链接', 'Delete', 'data', '', '', '', 0, 0, 'cms.link.link.delete', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468897060, 0);
INSERT INTO `sys_menu` VALUES ('3f330d729ca34dc9825c46122be1bfae', '02e86a61e99746bea34236ea73dd52a5', '00030003', '广告链接', 'AD', 'menu', '', '', 'ti-link', 1, 0, 'cms.link', NULL, 91, 1, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896230, 0);
INSERT INTO `sys_menu` VALUES ('437b5771ab0a42ec886a69e050c74a52', '5adfb7b54d784242a7c524734a10d4bd', '000100010005', '系统参数', 'Param', 'menu', '/platform/sys/conf', 'data-pjax', NULL, 1, 0, 'sys.manager.conf', NULL, 59, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('44da90bc76a5419a841f4924333f7a66', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020002', '修改菜单', 'Edit', 'data', '', '', '', 0, 0, 'wx.conf.menu.edit', NULL, 0, 0, '', 1467474294, 0);
INSERT INTO `sys_menu` VALUES ('45d958ca78304f25b51f6c71cf66f6d8', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020001', '添加菜单', 'Add', 'data', '', '', '', 0, 0, 'wx.conf.menu.add', NULL, 0, 0, '', 1467474283, 0);
INSERT INTO `sys_menu` VALUES ('46210709bff149d78d4d7914cb9daa72', '', '0005', '雇员信息', NULL, 'menu', '', '', '', 1, 0, 'platform.gy', NULL, 32, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515139868, 0);
INSERT INTO `sys_menu` VALUES ('466cf9e004ae42b19e3e5e53395197df', '1c8a24e0cf094e8e99346aa8b0e4391b', '00080003', '问题反馈', NULL, 'menu', '/platform/lib/bug', '', '', 1, 1, 'lib.bug', NULL, 52, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511233805, 0);
INSERT INTO `sys_menu` VALUES ('4781372b00bb4d52b429b58e72b80c68', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010001', '添加栏目', 'Add', 'data', '', '', '', 0, 0, 'cms.content.channel.add', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896049, 0);
INSERT INTO `sys_menu` VALUES ('4b36cc8c72a946fd80e9321e6ff1fc94', 'cdac024c3c7a459d914ea0e62c662554', '00070006', '任务预付', NULL, 'menu', '/platform/xm/prepay', '', '', 1, 1, 'platform.xm.prepay', NULL, 13, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511928263, 0);
INSERT INTO `sys_menu` VALUES ('4bb07c9f6685429d8a2aa5d9d2b4fed5', '46210709bff149d78d4d7914cb9daa72', '00050001', '信息修改', NULL, 'menu', '/platform/gy/person/inf', '', '', 1, 1, 'gy.person.inf', NULL, 33, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510570087, 0);
INSERT INTO `sys_menu` VALUES ('4bdbe5fa320d4873890b1764bfbd2f47', '5adfb7b54d784242a7c524734a10d4bd', '000100010001', '单位管理', 'Unit', 'menu', '/platform/sys/unit', 'data-pjax', NULL, 1, 0, 'sys.manager.unit', NULL, 55, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('4cd8e4e9519e4cff95465194fdcc8d88', 'b0edc6861a494b79b97990dc05f0a524', '00020003', '自动回复', 'AutoReply', 'menu', '', '', 'ti-back-left', 1, 0, 'wx.reply', NULL, 72, 1, '', 1467471610, 0);
INSERT INTO `sys_menu` VALUES ('4d0d72499bd84d9b827e3eccafade2e5', '6731cf86a00e4db283099ef7c7211448', '0001000100080003', '删除路由', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.route.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('4dc997fef71e4862b9db22de8e99a618', 'b19b23b0459a4754bf1fb8cb234450f2', '0002000100010001', '同步会员信息', 'Sync', 'data', '', '', '', 0, 0, 'wx.user.list.sync', NULL, 0, 0, '', 1467473044, 0);
INSERT INTO `sys_menu` VALUES ('507b184823614a52a3a96571a1507129', '437b5771ab0a42ec886a69e050c74a52', '0001000100050002', '修改参数', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.conf.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('50ba60ee650e4c739e6abc3ab71e4960', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010004', '栏目排序', 'Sort', 'data', '', '', '', 0, 0, 'cms.content.channel.sort', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896092, 0);
INSERT INTO `sys_menu` VALUES ('516d64a2b6584b83800e2948d7f87335', 'bd07a59e350647d0895ea2a1e39a9994', '001000010002', '个人信息增加', NULL, 'data', '', '', '', 0, 0, 'platform.sys.userinf.add', NULL, 41, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515594066, 0);
INSERT INTO `sys_menu` VALUES ('5244f5c38eb24b918e9ad64d456daa38', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020005', '推送到微信', 'Push', 'data', '', '', '', 0, 0, 'wx.conf.menu.push', NULL, 0, 0, '', 1467474330, 0);
INSERT INTO `sys_menu` VALUES ('52a16b54ab384d628d40bd5304b3c812', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100001', '添加字典', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.dict.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('53e10dcef27147388913d6df391946f7', 'cdac024c3c7a459d914ea0e62c662554', '00070004', '任务账单', NULL, 'menu', '/platform/xm/bill', '', '', 1, 0, 'platform.xm.bill', NULL, 12, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511928195, 0);
INSERT INTO `sys_menu` VALUES ('56d0658c5a8848818ac05e8ffa5c0570', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020001', '添加图文', 'Add', 'data', '', '', '', 0, 0, 'wx.msg.mass.addNews', NULL, 0, 0, '', 1467473338, 0);
INSERT INTO `sys_menu` VALUES ('5735058475ef401da0e250ce40eb1db9', '7bea97e18fb6402aa2896ed0c5f3cdf4', '000700020001', '提交项目反馈', NULL, 'data', '', '', '', 0, 0, 'platform.xm.feedback.edit', NULL, 10, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1512214255, 0);
INSERT INTO `sys_menu` VALUES ('5768505c64e64c3395c87358520d158f', '', '0011', '任务广场', NULL, 'menu', '/public/xm', '', '', 1, 0, 'public.xm', NULL, 84, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511511061, 0);
INSERT INTO `sys_menu` VALUES ('5adfb7b54d784242a7c524734a10d4bd', '36ce16235ac14cebbdfd7a4ddcd02a7b', '00010001', '系统管理', 'Manager', 'menu', '', '', 'ti-settings', 1, 0, 'sys.manager', '系统管理', 54, 1, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('5f255c41da5a4befb176e78e5ba2c89c', '', '0006', '平台新闻', NULL, 'menu', '', '', '', 1, 0, 'public.cms', NULL, 94, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510465152, 0);
INSERT INTO `sys_menu` VALUES ('5f2f54d9dab84be8bab26a13431f2b59', '31aae024cd5d47999aa9e73becc14cb5', '000400020003', '雇员身份审核编辑', NULL, 'data', '', '', '', 1, 0, 'platform.gy.auth.edit', NULL, 23, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510570260, 0);
INSERT INTO `sys_menu` VALUES ('6075fc0cf0ef441b9d93cc3cab3445bf', '6b6de8c720c645a1808e1c3e9ccbfc90', '000300020002', '文章管理', 'Article', 'menu', '/platform/cms/article', 'data-pjax', '', 1, 0, 'cms.content.article', NULL, 90, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896141, 0);
INSERT INTO `sys_menu` VALUES ('60d42fa10a804c23a502aa06d786182a', '1c8a24e0cf094e8e99346aa8b0e4391b', '00080002', '任务种类管理', NULL, 'menu', '/platform/lib/task', '', '', 1, 0, 'lib.task', NULL, 48, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1511233774, 0);
INSERT INTO `sys_menu` VALUES ('66cc21d7ce104dd6877cbce114c59fb3', 'bcf64d623fdd4519ae345b7a08c071a1', '000200040001', '帐号配置', 'Account', 'menu', '/platform/wx/conf/account', 'data-pjax', '', 1, 0, 'wx.conf.account', NULL, 78, 0, '', 1467472624, 0);
INSERT INTO `sys_menu` VALUES ('6731cf86a00e4db283099ef7c7211448', '5adfb7b54d784242a7c524734a10d4bd', '000100010008', '自定义路由', 'Route', 'menu', '/platform/sys/route', 'data-pjax', NULL, 1, 0, 'sys.manager.route', NULL, 62, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('682626f7d4d348729f84f7427bc142cc', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100002', '修改字典', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.dict.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('68cdbf694f71445c8587a20234d6fe31', '3f330d729ca34dc9825c46122be1bfae', '000300030002', '链接管理', 'Link', 'menu', '/platform/cms/link/link', 'data-pjax', '', 1, 0, 'cms.link.link', NULL, 93, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468897031, 0);
INSERT INTO `sys_menu` VALUES ('6afc5075913d4df4b44a6476080e35a0', 'b0edc6861a494b79b97990dc05f0a524', '00020005', '模板消息', 'Template', 'menu', '', '', 'ti-notepad', 1, 0, 'wx.tpl', NULL, 80, 1, '', 1470406797, 0);
INSERT INTO `sys_menu` VALUES ('6b6de8c720c645a1808e1c3e9ccbfc90', '02e86a61e99746bea34236ea73dd52a5', '00030002', '内容管理', 'Content', 'menu', '', '', 'ti-pencil-alt', 1, 0, 'cms.content', NULL, 88, 1, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468895990, 0);
INSERT INTO `sys_menu` VALUES ('6bb17a41f6394ed0a8a6faf5ff781354', '9f20a757a6bc40ddbb650c70debbf660', '000200020002', '群发消息', 'Mass', 'menu', '/platform/wx/msg/mass', 'data-pjax', '', 1, 0, 'wx.msg.mass', NULL, 71, 0, '', 1467471561, 0);
INSERT INTO `sys_menu` VALUES ('6ddd6a14fc66445a874a6cb63754ec47', 'bd07a59e350647d0895ea2a1e39a9994', '001000010001', '个人信息修改', NULL, 'data', '', '', '', 0, 0, 'platform.sys.userinf.edit', NULL, 40, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515594053, 0);
INSERT INTO `sys_menu` VALUES ('6eee45326f114d6ba567f64b10055eaa', '', '0010', '员工信息', NULL, 'menu', '', '', '', 1, 0, 'platform.sys.userinf', NULL, 37, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515593467, 0);
INSERT INTO `sys_menu` VALUES ('6fc4a70a0f13430dbb3ca9b42a8520d9', '0b097e9dbad649048f956355d1e91278', '0001000100090003', '删除应用', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.api.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('7021fa1a6f76415d9dc21ca28caa2dae', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110001', '添加插件', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.plugin.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('7125a72beee34b21ab3df9bf01b7bce6', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010003', '删除分类', 'Delete', 'data', '', '', '', 0, 0, 'cms.link.class.delete', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896968, 0);
INSERT INTO `sys_menu` VALUES ('7147640d3dfe48e8a03009df11c610da', '46210709bff149d78d4d7914cb9daa72', '00050002', '身份认证', NULL, 'menu', '/platform/gy/person/auth', '', '', 1, 1, 'gy.person.auth', NULL, 34, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510570155, 0);
INSERT INTO `sys_menu` VALUES ('7177b2331d2d47b9a28a1eca191e1cfc', 'f7a00dcffa7548fca0939335425650b0', '000700050001', '任务申请受理', NULL, 'data', '', '', '', 0, 0, 'platform.xm.apply.deal', NULL, 7, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511864346, 0);
INSERT INTO `sys_menu` VALUES ('72f75a6078384584b996d71bbfcf646d', '437b5771ab0a42ec886a69e050c74a52', '0001000100050003', '删除参数', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.conf.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('733d3f35d49f45af99ca9220048583ba', '0706112ff5dc46e388064a99bcdb0561', '0002000300040003', '删除绑定', 'Delete', 'data', '', '', '', 0, 0, 'wx.reply.keyword.delete', NULL, 0, 0, '', 1467474136, 0);
INSERT INTO `sys_menu` VALUES ('73a29d3f99224426b5a87c92da122275', 'd1e991ad38a8424daf9f7eb000ee27f4', '0003000100010001', '保存配置', 'Save', 'data', '', '', '', 0, 0, 'cms.site.settings.save', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468895899, 0);
INSERT INTO `sys_menu` VALUES ('792d6e9db8c7485db632894148052486', '10c8ae20fa6942c5b54a95df03270273', '0001000100070003', '删除任务', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.task.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('7bea97e18fb6402aa2896ed0c5f3cdf4', 'cdac024c3c7a459d914ea0e62c662554', '00070002', '任务跟踪', NULL, 'menu', '/platform/xm/feedback', '', '', 1, 0, 'platform.xm.feedback', NULL, 9, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511928146, 0);
INSERT INTO `sys_menu` VALUES ('7c040dfd8db347e5956a3bc1764653dc', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010003', '删除文本', 'Delete', 'data', '', '', '', 0, 0, 'wx.reply.txt.delete', NULL, 0, 0, '', 1467473540, 0);
INSERT INTO `sys_menu` VALUES ('7db6207d0dab4d6e95a7eee4f2efe875', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010001', '添加分类', 'Add', 'data', '', '', '', 0, 0, 'cms.link.class.add', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896947, 0);
INSERT INTO `sys_menu` VALUES ('80b7ac4f349e458aa5ce0aade69ee8a8', 'f25bfd5a71ff4c1c9ea30e70db047e22', '000700010001', '任务书添加', NULL, 'data', '', '', '', 0, 0, 'platform.xm.task.add', NULL, 2, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511427346, 0);
INSERT INTO `sys_menu` VALUES ('81772c143625453b84f367ee6c444b5d', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110003', '删除插件', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.plugin.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('83951e13c14643898e06b39cb428ec86', '1faa04cc182a4849b94a99286d972b0d', '00040003', '雇员交易方式', NULL, 'menu', '/platform/gy/pay', '', '', 1, 0, 'platform.gy.pay', NULL, 24, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1511234179, 0);
INSERT INTO `sys_menu` VALUES ('8515f0f788ea435f9b41f32891da1f4e', '60d42fa10a804c23a502aa06d786182a', '000800020001', '任务种类添加', NULL, 'data', '', '', '', 0, 0, 'lib.task.add', NULL, 49, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511167119, 0);
INSERT INTO `sys_menu` VALUES ('86bb35dea0ac402f95b85a274181e759', '83951e13c14643898e06b39cb428ec86', '000400030003', '删除雇员交易方式', NULL, 'data', '', '', '', 1, 0, 'platform.gy.pay.delete', NULL, 27, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511234213, 0);
INSERT INTO `sys_menu` VALUES ('879a4e4883a0465a939dfc493232e2c2', '10c8ae20fa6942c5b54a95df03270273', '0001000100070001', '添加任务', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.task.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('8d93fd924f3542dea28e4335c4654834', '0b097e9dbad649048f956355d1e91278', '0001000100090002', '修改应用', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.api.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('8ef1e45375a6442eaa2272ddfcc270c9', 'cdac024c3c7a459d914ea0e62c662554', '00070008', '任务结算', NULL, 'menu', '/platform/xm/final', '', '', 1, 0, 'platform.xm.final', NULL, 96, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515926090, 0);
INSERT INTO `sys_menu` VALUES ('8f0377fbeaba48c69b49966ebe072e7c', 'f25bfd5a71ff4c1c9ea30e70db047e22', '000700010002', '任务书修改', NULL, 'data', '', '', '', 0, 0, 'platform.xm.task.edit', NULL, 3, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511427358, 0);
INSERT INTO `sys_menu` VALUES ('93f7b2bce2054dcf8921654ccbbde876', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030002', '修改角色', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.role.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('96554b09a2dd4f82bab7546fa59acd35', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010002', '修改帐号', 'Edit', 'data', '', '', '', 0, 0, 'wx.conf.account.edit', NULL, 0, 0, '', 1467474197, 0);
INSERT INTO `sys_menu` VALUES ('9822bafbe3454dfd8e8b974ebc304d03', '3f330d729ca34dc9825c46122be1bfae', '000300030001', '链接分类', 'Class', 'menu', '/platform/cms/link/class', 'data-pjax', '', 1, 0, 'cms.link.class', NULL, 92, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896932, 0);
INSERT INTO `sys_menu` VALUES ('9a9557177d334c209cf73c3817fe3b63', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020003', '删除菜单', 'Delete', 'data', '', '', '', 0, 0, 'wx.conf.menu.delete', NULL, 0, 0, '', 1467474304, 0);
INSERT INTO `sys_menu` VALUES ('9f20a757a6bc40ddbb650c70debbf660', 'b0edc6861a494b79b97990dc05f0a524', '00020002', '消息管理', 'Message', 'menu', '', '', 'ti-pencil-alt', 1, 0, 'wx.msg', NULL, 69, 1, '', 1467471415, 0);
INSERT INTO `sys_menu` VALUES ('a09dd5fc3517447b9668457fb77c2d08', '5adfb7b54d784242a7c524734a10d4bd', '000100010003', '角色管理', 'Role', 'menu', '/platform/sys/role', 'data-pjax', NULL, 1, 0, 'sys.manager.role', NULL, 57, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('a11163584dfe456cbfd6fb2d4b74391b', 'cabbe834a7474675b899e8442b5c2604', '0002000500020001', '获取列表', 'Get', 'data', '', '', '', 0, 0, 'wx.tpl.list.get', NULL, 0, 0, '', 1470407390, 0);
INSERT INTO `sys_menu` VALUES ('a16d7aeb3f844063ad4b7cffb04f3729', '6eee45326f114d6ba567f64b10055eaa', '00100003', '个人信息', NULL, 'menu', '/platform/sys/userinf/person', '', '', 1, 0, 'platform.sys.userinf.person', NULL, 38, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517128167, 0);
INSERT INTO `sys_menu` VALUES ('a9c2eceebd64405a8b046b40bc7815cf', '83951e13c14643898e06b39cb428ec86', '000400030002', '修改雇员交易方式', NULL, 'data', '', '', '', 0, 0, 'platform.gy.pay.edit', NULL, 26, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511234203, 0);
INSERT INTO `sys_menu` VALUES ('adf38e430e8c4a8d87d0070ccf3358ff', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030001', '添加角色', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.role.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('ae4adeb99c08458d83d7c663bffa0eca', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100003', '删除字典', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.dict.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('b0edc6861a494b79b97990dc05f0a524', '', '0002', '微信', 'Wechat', 'menu', '', '', '', 1, 1, 'wx', NULL, 66, 1, '', 1467471229, 0);
INSERT INTO `sys_menu` VALUES ('b19b23b0459a4754bf1fb8cb234450f2', 'e4256d7b0ffc4a02906cf900322b6213', '000200010001', '会员列表', 'List', 'menu', '/platform/wx/user/index', 'data-pjax', '', 1, 0, 'wx.user.list', NULL, 68, 0, '', 1467471357, 0);
INSERT INTO `sys_menu` VALUES ('b225e7eafec846f29023cca78f63bc18', '5adfb7b54d784242a7c524734a10d4bd', '000100010004', '菜单管理', 'Menu', 'menu', '/platform/sys/menu', 'data-pjax', NULL, 1, 0, 'sys.manager.menu', NULL, 58, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('b2631bbdbf824cc4b74d819c87962c0d', '6b6de8c720c645a1808e1c3e9ccbfc90', '000300020001', '栏目管理', 'Channel', 'menu', '/platform/cms/channel', 'data-pjax', '', 1, 0, 'cms.content.channel', NULL, 89, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896018, 0);
INSERT INTO `sys_menu` VALUES ('ba250b9bbb03448186c87d4b7e234e06', 'e28eca57f8f54fe893eed7400afd4a17', '00090002', '任务跟踪', NULL, 'menu', '/platform/xm/person/personxm', '', '', 1, 0, 'platform.xm.person.psersonxm', NULL, 30, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515242260, 0);
INSERT INTO `sys_menu` VALUES ('ba83791e19614a1eaf64d1c05633bbea', 'f7a00dcffa7548fca0939335425650b0', '000700050002', '任务申报删除', NULL, 'data', '', '', '', 0, 0, 'platform.xm.apply.delete', NULL, 8, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515724874, 0);
INSERT INTO `sys_menu` VALUES ('bcf64d623fdd4519ae345b7a08c071a1', 'b0edc6861a494b79b97990dc05f0a524', '00020004', '微信配置', 'Config', 'menu', '', '', 'fa fa-weixin', 1, 0, 'wx.conf', NULL, 77, 1, '', 1467472498, 0);
INSERT INTO `sys_menu` VALUES ('bd07a59e350647d0895ea2a1e39a9994', '6eee45326f114d6ba567f64b10055eaa', '00100001', '员工信息列表', NULL, 'menu', '/platform/sys/userinf', '', '', 1, 0, 'platform.sys.userinf.list', NULL, 39, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515593567, 0);
INSERT INTO `sys_menu` VALUES ('bd606a93c49a407e8193bea7ad0370e7', 'e7287128c8a5470081ed782a282d30c6', '0001000100020001', '添加用户', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.user.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('bf7367d188174ef3970039c19ad4c520', '6eee45326f114d6ba567f64b10055eaa', '00100002', '通讯录', NULL, 'menu', '', '', '', 1, 1, 'gz.contact', NULL, 42, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511402935, 0);
INSERT INTO `sys_menu` VALUES ('c3a44b478d3241b899b9c3f4611bc2b6', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010001', '添加文本', 'Add', 'data', '', '', '', 0, 0, 'wx.reply.txt.add', NULL, 0, 0, '', 1467473460, 0);
INSERT INTO `sys_menu` VALUES ('c76a84f871d047db955dd1465c845ac1', '6afc5075913d4df4b44a6476080e35a0', '000200050003', '发送记录', 'Log', 'menu', '/platform/wx/tpl/log', 'data-pjax', '', 1, 0, 'wx.tpl.log', NULL, 83, 0, '', 1470406926, 0);
INSERT INTO `sys_menu` VALUES ('cab7887b8ded412788f988544559780f', '31aae024cd5d47999aa9e73becc14cb5', '000400020002', '雇员身份审核删除', NULL, 'data', '', '', '', 1, 0, 'platform.gy.auth.delete', NULL, 22, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510570250, 0);
INSERT INTO `sys_menu` VALUES ('cabbe834a7474675b899e8442b5c2604', '6afc5075913d4df4b44a6476080e35a0', '000200050002', '模板列表', 'List', 'menu', '/platform/wx/tpl/list', 'data-pjax', '', 1, 0, 'wx.tpl.list', NULL, 82, 0, '', 1470406883, 0);
INSERT INTO `sys_menu` VALUES ('cbb294ea95654891b5c0adbbbf6d19b3', '09147fe6c278458cb77c4162fff447e3', '000800010002', '技能编辑', NULL, 'data', '', '', '', 0, 0, 'lib.skill.edit', NULL, 46, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517795422, 0);
INSERT INTO `sys_menu` VALUES ('cbc21bae64934398b3379835f3a75550', '09147fe6c278458cb77c4162fff447e3', '000800010001', '技能添加', NULL, 'data', '', '', '', 0, 0, 'lib.skill.add', NULL, 45, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511164152, 0);
INSERT INTO `sys_menu` VALUES ('cdac024c3c7a459d914ea0e62c662554', '', '0007', '平台任务', NULL, 'menu', '', '', '', 1, 0, 'sys.xm', NULL, 0, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515139802, 0);
INSERT INTO `sys_menu` VALUES ('ce709456e867425297955b3c40406d7e', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020002', '删除图文', 'Delete', 'data', '', '', '', 0, 0, 'wx.msg.mass.delNews', NULL, 0, 0, '', 1467473363, 0);
INSERT INTO `sys_menu` VALUES ('cecb646ee700497fbf1ea3b0c776eb5a', '6731cf86a00e4db283099ef7c7211448', '0001000100080002', '修改路由', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.route.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('d157b33b8490445abbc892f332fd5cbb', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010001', '添加单位', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.unit.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('d1e991ad38a8424daf9f7eb000ee27f4', 'd920314e925c451da6d881e7a29743b7', '000300010001', '网站配置', 'Settings', 'menu', '/platform/cms/site', 'data-pjax', '', 1, 0, 'cms.site.settings', NULL, 87, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468895881, 0);
INSERT INTO `sys_menu` VALUES ('d20b5be25e2843c4a3c5e4a4a9b929e6', '298f7a5f36574102a87268d7ffbe77d6', '000400010002', '雇员信息更新', NULL, 'data', '', '', '', 0, 0, 'platform.gy.inf.edit', NULL, 18, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1524721536, 0);
INSERT INTO `sys_menu` VALUES ('d568f4c2b687404e8aec7b9edcae5767', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010003', '删除帐号', 'Delete', 'data', '', '', '', 0, 0, 'wx.conf.account.delete', NULL, 0, 0, '', 1467474209, 0);
INSERT INTO `sys_menu` VALUES ('d64bb9543b43441689117bf2314198ed', 'e28eca57f8f54fe893eed7400afd4a17', '00090003', '任务结算', NULL, 'menu', '/platform/xm/person/xmcompleted', '', '', 1, 0, 'xm.person.finished', NULL, 31, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515891508, 0);
INSERT INTO `sys_menu` VALUES ('d86c48e786cb45418c069cefa3056c0a', '6731cf86a00e4db283099ef7c7211448', '0001000100080001', '添加路由', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.route.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('d883f98ee747464bace45cf4ad111a85', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110002', '启用禁用', 'Update', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.plugin.update', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('d920314e925c451da6d881e7a29743b7', '02e86a61e99746bea34236ea73dd52a5', '00030001', '站点管理', 'Site', 'menu', '', '', 'ti-world', 1, 0, 'cms.site', NULL, 86, 1, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468895821, 0);
INSERT INTO `sys_menu` VALUES ('d9b453267f7240bcbae9f87169caeb6f', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030004', '分配菜单', 'SetMenu', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.role.menu', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('dd965b2c1dfd493fb5efc7e4bcac99d4', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030001', '添加绑定', 'Add', 'data', '', '', '', 0, 0, 'wx.reply.follow.add', NULL, 0, 0, '', 1467474026, 0);
INSERT INTO `sys_menu` VALUES ('e28eca57f8f54fe893eed7400afd4a17', '', '0009', '雇员任务', NULL, 'menu', '', '', '', 1, 0, 'platform.xm.person', NULL, 28, 1, '405a28c9389d4a8581a29c283dc9f5b9', 1515242306, 0);
INSERT INTO `sys_menu` VALUES ('e40193cfca8e425694f0b31cc2f0f04c', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030005', '分配用户', 'SetUser', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.role.user', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('e4256d7b0ffc4a02906cf900322b6213', 'b0edc6861a494b79b97990dc05f0a524', '00020001', '微信会员', 'Member', 'menu', '', '', 'fa fa-user', 1, 0, 'wx.user', NULL, 67, 1, '', 1467471292, 0);
INSERT INTO `sys_menu` VALUES ('e461c62a1d5441619cd35612f3b40691', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010002', '修改栏目', 'Edit', 'data', '', '', '', 0, 0, 'cms.content.channel.edit', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896060, 0);
INSERT INTO `sys_menu` VALUES ('e52675e8b10045cb891662623b455861', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010002', '修改单位', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.unit.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('e6b6224617b04090a76e46a4b048fb96', '1385ae887e5c4b8aa33fbf228be7f907', '0002000500010001', '添加编号', 'Add', 'data', '', '', '', 0, 0, 'wx.tpl.id.add', NULL, 0, 0, '', 1470407055, 0);
INSERT INTO `sys_menu` VALUES ('e7287128c8a5470081ed782a282d30c6', '5adfb7b54d784242a7c524734a10d4bd', '000100010002', '用户管理', 'User', 'menu', '/platform/sys/user', 'data-pjax', NULL, 1, 0, 'sys.manager.user', NULL, 56, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('e864c78aba63448892cbcb6a3a7f4da7', '0706112ff5dc46e388064a99bcdb0561', '0002000300040001', '添加绑定', 'Add', 'data', '', '', '', 0, 0, 'wx.reply.keyword.add', NULL, 0, 0, '', 1467474113, 0);
INSERT INTO `sys_menu` VALUES ('e9dc75e8fb7b4652898c2fc9030aa736', 'f25bfd5a71ff4c1c9ea30e70db047e22', '000700010003', '任务书删除', NULL, 'data', '', '', '', 0, 0, 'platform.xm.task.delete', NULL, 4, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511427370, 0);
INSERT INTO `sys_menu` VALUES ('ebaa84b4d7654791afa4cf116413f12e', '5adfb7b54d784242a7c524734a10d4bd', '000100010010', '数据字典', 'Dict', 'menu', '/platform/sys/dict', 'data-pjax', NULL, 1, 0, 'sys.manager.dict', NULL, 64, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('ed6ed141d14443b8a08ac4d941c7bb2e', 'cdac024c3c7a459d914ea0e62c662554', '00070007', '立项信息', NULL, 'data', '', '', '', 0, 0, 'platform.xm.inf', NULL, 14, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515720666, 0);
INSERT INTO `sys_menu` VALUES ('ed9f26e5926b4b87a2f2e605b02a2a7e', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010003', '删除单位', 'Delete', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.unit.delete', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('ef9f436c61654ec09efbfa79a40061cf', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020002', '修改文章', 'Edit', 'data', '', '', '', 0, 0, 'cms.content.article.edit', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896159, 0);
INSERT INTO `sys_menu` VALUES ('efeed24e41054b3da9edbddbf7cedeac', '5adfb7b54d784242a7c524734a10d4bd', '000100010011', '插件管理', 'Plugin', 'menu', '/platform/sys/plugin', 'data-pjax', NULL, 1, 0, 'sys.manager.plugin', NULL, 65, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('f25bfd5a71ff4c1c9ea30e70db047e22', 'cdac024c3c7a459d914ea0e62c662554', '00070001', '任务书管理', NULL, 'menu', '/platform/xm/task', '', '', 1, 0, 'platform.xm.task', NULL, 1, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511417013, 0);
INSERT INTO `sys_menu` VALUES ('f426468abf714b1599729f8c36ebbb0d', '9f20a757a6bc40ddbb650c70debbf660', '000200020001', '会员消息', 'Msg', 'menu', '/platform/wx/msg/user', 'data-pjax', '', 1, 0, 'wx.msg.user', NULL, 70, 1, '', 1467471478, 0);
INSERT INTO `sys_menu` VALUES ('f5aa5aff8e0f4d1b914785d9d7fbc163', '10c8ae20fa6942c5b54a95df03270273', '0001000100070002', '修改任务', 'Edit', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.task.edit', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('f627f3ad43984ab28ed85328c98b77b6', '09147fe6c278458cb77c4162fff447e3', '000800010003', '技能删除', NULL, 'data', '', '', '', 0, 0, 'lib.skill.delete', NULL, 47, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511164194, 0);
INSERT INTO `sys_menu` VALUES ('f6fba69c3b704d79834b8bd2cc753729', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010003', '删除栏目', 'Delete', 'data', '', '', '', 0, 0, 'cms.content.channel.delete', NULL, 0, 0, '1a19ef09b12344b4a797d6e6dfe7fb29', 1468896072, 0);
INSERT INTO `sys_menu` VALUES ('f7a00dcffa7548fca0939335425650b0', 'cdac024c3c7a459d914ea0e62c662554', '00070005', '任务申报', NULL, 'menu', '/platform/xm/apply', '', '', 1, 0, 'platform.xm.apply', NULL, 6, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511853818, 0);
INSERT INTO `sys_menu` VALUES ('f9a6cce659334b0987384723b7707bad', '0b097e9dbad649048f956355d1e91278', '0001000100090001', '添加应用', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.api.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('f9f54329eabd4d3a8b2229212007a54a', '298f7a5f36574102a87268d7ffbe77d6', '000400010001', '增加雇员信息', NULL, 'data', '', '', '', 0, 0, 'platform.gy.inf.add', NULL, 17, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510537718, 0);
INSERT INTO `sys_menu` VALUES ('fc340d1786864183bed4d56183905461', 'b225e7eafec846f29023cca78f63bc18', '0001000100040001', '添加菜单', 'Add', 'data', NULL, NULL, NULL, 0, 0, 'sys.manager.menu.add', NULL, 0, 0, '', 1510391361, 0);
INSERT INTO `sys_menu` VALUES ('fc52d5284b8f4522802383c1ef732242', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020003', '删除图文', 'Delete', 'data', '', '', '', 0, 0, 'wx.reply.news.delete', NULL, 0, 0, '', 1467473606, 0);
INSERT INTO `sys_menu` VALUES ('fd63a8e389e04ff3a86c3cea53a3b9d5', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010002', '修改文本', 'Edit', 'data', '', '', '', 0, 0, 'wx.reply.txt.edit', NULL, 0, 0, '', 1467473519, 0);
INSERT INTO `sys_menu` VALUES ('fe34161d69bb4167ab07eb5ea4389dc1', 'cdac024c3c7a459d914ea0e62c662554', '00070003', '任务评价', NULL, 'menu', '/platform/xm/evaluation', '', '', 1, 1, 'platform.xm.evaluation', NULL, 11, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511928844, 0);
INSERT INTO `sys_menu` VALUES ('ff6cd243a77c4ae98dacf6149c816c75', '0706112ff5dc46e388064a99bcdb0561', '0002000300040002', '修改绑定', 'Edit', 'data', '', '', '', 0, 0, 'wx.reply.keyword.edit', NULL, 0, 0, '', 1467474125, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '创建昵称',
  `type` int(11) NOT NULL COMMENT '推送方式',
  `tag` varchar(30) NOT NULL COMMENT '推送标示',
  `revid` varchar(50) DEFAULT NULL COMMENT '消息接受人',
  `revaccount` varchar(100) DEFAULT NULL COMMENT '消息接受账号',
  `sendid` varchar(50) DEFAULT NULL COMMENT '消息发送人',
  `ip` varchar(255) DEFAULT NULL COMMENT '来源IP',
  `msg` text COMMENT '推送内容',
  `checked` tinyint(1) DEFAULT NULL COMMENT '是否查阅',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `tmsgclass` varchar(100) DEFAULT NULL COMMENT 'TMsg名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_plugin
-- ----------------------------
DROP TABLE IF EXISTS `sys_plugin`;
CREATE TABLE `sys_plugin` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `code` varchar(255) DEFAULT NULL COMMENT '唯一标识',
  `className` varchar(255) DEFAULT NULL COMMENT '类名',
  `args` varchar(255) DEFAULT NULL COMMENT '执行参数',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_PLUGIN` (`code`),
  UNIQUE KEY `INDEX_SYS_CLASSNAME` (`className`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `aliasName` varchar(50) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  `unitid` varchar(32) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_ROLE_CODE` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('42207005210b48329010ee9b0fd84dd8', '产品经理', 'sys.pm', NULL, 0, 'd331f97e475d45b6be862f059f80f53e', '发布任务书，跟踪维护发的任务书等相关事宜', '405a28c9389d4a8581a29c283dc9f5b9', 1518438729, 0);
INSERT INTO `sys_role` VALUES ('4522f2188b9b466b8835040c9e93c481', '雇员经理', 'sys.gym', NULL, 0, 'd331f97e475d45b6be862f059f80f53e', '运营部 雇员经理权限', '405a28c9389d4a8581a29c283dc9f5b9', 1525674543, 0);
INSERT INTO `sys_role` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '邮箱未认证雇员', 'gy1', NULL, 0, 'fd02b028caed459590b68dde6d47f115', '等待邮箱认证和信息完善', '405a28c9389d4a8581a29c283dc9f5b9', 1515245911, 0);
INSERT INTO `sys_role` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '项目总监', 'sys.superpm', NULL, 0, 'd331f97e475d45b6be862f059f80f53e', '管理全部的项目和项目经理', '405a28c9389d4a8581a29c283dc9f5b9', 1525674515, 0);
INSERT INTO `sys_role` VALUES ('592b631bb0874944acd918e34529d045', '游客', 'gy0', NULL, 0, 'fd02b028caed459590b68dde6d47f115', '普通游客', '405a28c9389d4a8581a29c283dc9f5b9', 1517794786, 0);
INSERT INTO `sys_role` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '系统管理员', 'sysadmin', 'Sysadmin', 0, '', 'System Admin', '', 1510391361, 0);
INSERT INTO `sys_role` VALUES ('8753fa0d90194d3b8efab979f91412e3', '注册雇员', 'gy2', NULL, 0, 'fd02b028caed459590b68dde6d47f115', '邮箱，电话等信息注册', '405a28c9389d4a8581a29c283dc9f5b9', 1515135944, 0);
INSERT INTO `sys_role` VALUES ('a12d5b8282214b2dbc3e3b9d60795dbd', '认证雇员', 'gy3', NULL, 0, 'fd02b028caed459590b68dde6d47f115', '雇员身份在平台认证通过', '405a28c9389d4a8581a29c283dc9f5b9', 1515564307, 0);
INSERT INTO `sys_role` VALUES ('ab986f7f9a5e478089b9cf38ab768dea', '财务', 'sys.fn', NULL, 0, 'd331f97e475d45b6be862f059f80f53e', '', '405a28c9389d4a8581a29c283dc9f5b9', 1517215434, 0);
INSERT INTO `sys_role` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '正式雇员', 'gy4', NULL, 0, 'fd02b028caed459590b68dde6d47f115', '可以正式在平台上面进行雇佣交易', '405a28c9389d4a8581a29c283dc9f5b9', 1515737485, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleId` varchar(32) DEFAULT NULL,
  `menuId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('a12d5b8282214b2dbc3e3b9d60795dbd', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('a12d5b8282214b2dbc3e3b9d60795dbd', '15921e56ce7c463e94d2e2b40473bd1d');
INSERT INTO `sys_role_menu` VALUES ('a12d5b8282214b2dbc3e3b9d60795dbd', '46210709bff149d78d4d7914cb9daa72');
INSERT INTO `sys_role_menu` VALUES ('8753fa0d90194d3b8efab979f91412e3', '15921e56ce7c463e94d2e2b40473bd1d');
INSERT INTO `sys_role_menu` VALUES ('8753fa0d90194d3b8efab979f91412e3', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('8753fa0d90194d3b8efab979f91412e3', '7147640d3dfe48e8a03009df11c610da');
INSERT INTO `sys_role_menu` VALUES ('8753fa0d90194d3b8efab979f91412e3', '46210709bff149d78d4d7914cb9daa72');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '1765a77196994d55972ae3e9428021c8');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', 'ba250b9bbb03448186c87d4b7e234e06');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', 'd64bb9543b43441689117bf2314198ed');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '15921e56ce7c463e94d2e2b40473bd1d');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '27e62ac8b9024c029e7f9851f5313eab');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', 'e28eca57f8f54fe893eed7400afd4a17');
INSERT INTO `sys_role_menu` VALUES ('c91e1aedef45459ea7e96aedaa8c65fe', '46210709bff149d78d4d7914cb9daa72');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '15921e56ce7c463e94d2e2b40473bd1d');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '46210709bff149d78d4d7914cb9daa72');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '4dc997fef71e4862b9db22de8e99a618');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '1734e586e96941268a4c5248b593cef9');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '56d0658c5a8848818ac05e8ffa5c0570');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'ce709456e867425297955b3c40406d7e');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '3099f497480c4b1987bce3f3a26c3fb4');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'c3a44b478d3241b899b9c3f4611bc2b6');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'fd63a8e389e04ff3a86c3cea53a3b9d5');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '7c040dfd8db347e5956a3bc1764653dc');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '2275cb125710414e91b617dd7c62f12c');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '0a972ce655cb4c84809d58668b655900');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'fc52d5284b8f4522802383c1ef732242');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'dd965b2c1dfd493fb5efc7e4bcac99d4');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '30a5e70a1456447ebf90b5546e9bc321');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '2a63040409094f1e9dc535dd78ce15b7');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'e864c78aba63448892cbcb6a3a7f4da7');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'ff6cd243a77c4ae98dacf6149c816c75');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '733d3f35d49f45af99ca9220048583ba');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '309dc29ad3c34408a68df8f867a5c9ff');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '96554b09a2dd4f82bab7546fa59acd35');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'd568f4c2b687404e8aec7b9edcae5767');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '45d958ca78304f25b51f6c71cf66f6d8');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '44da90bc76a5419a841f4924333f7a66');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '9a9557177d334c209cf73c3817fe3b63');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '0a43d291e0c94ad88c8b690009279e34');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '5244f5c38eb24b918e9ad64d456daa38');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'e6b6224617b04090a76e46a4b048fb96');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '3888f05aa4064f788ba7ec51c495ce7c');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'a11163584dfe456cbfd6fb2d4b74391b');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'c76a84f871d047db955dd1465c845ac1');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '5f255c41da5a4befb176e78e5ba2c89c');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'b19b23b0459a4754bf1fb8cb234450f2');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'e4256d7b0ffc4a02906cf900322b6213');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'b0edc6861a494b79b97990dc05f0a524');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'f426468abf714b1599729f8c36ebbb0d');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '9f20a757a6bc40ddbb650c70debbf660');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '6bb17a41f6394ed0a8a6faf5ff781354');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '234f8ec3c2bc42bf9f6202aecae36fd6');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '4cd8e4e9519e4cff95465194fdcc8d88');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '17e1ee23ca1443f1bc886c2f5eb7c24b');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '2cb327ad59b140828fd26eb2a46cb948');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '0706112ff5dc46e388064a99bcdb0561');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '66cc21d7ce104dd6877cbce114c59fb3');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'bcf64d623fdd4519ae345b7a08c071a1');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '2fab774f8b6d40cb9d7e187babab2d91');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '1385ae887e5c4b8aa33fbf228be7f907');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '6afc5075913d4df4b44a6476080e35a0');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'cabbe834a7474675b899e8442b5c2604');
INSERT INTO `sys_role_menu` VALUES ('ab986f7f9a5e478089b9cf38ab768dea', '53e10dcef27147388913d6df391946f7');
INSERT INTO `sys_role_menu` VALUES ('ab986f7f9a5e478089b9cf38ab768dea', 'a16d7aeb3f844063ad4b7cffb04f3729');
INSERT INTO `sys_role_menu` VALUES ('ab986f7f9a5e478089b9cf38ab768dea', '6eee45326f114d6ba567f64b10055eaa');
INSERT INTO `sys_role_menu` VALUES ('ab986f7f9a5e478089b9cf38ab768dea', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'f9f54329eabd4d3a8b2229212007a54a');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'd20b5be25e2843c4a3c5e4a4a9b929e6');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '3050a2d827814a84a45b392166f6bfce');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'cab7887b8ded412788f988544559780f');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '5f2f54d9dab84be8bab26a13431f2b59');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'a16d7aeb3f844063ad4b7cffb04f3729');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'cbc21bae64934398b3379835f3a75550');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '8515f0f788ea435f9b41f32891da1f4e');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '0ec22065b0134f0091d5ce9f92e83183');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', 'cbb294ea95654891b5c0adbbbf6d19b3');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '298f7a5f36574102a87268d7ffbe77d6');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '1faa04cc182a4849b94a99286d972b0d');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '31aae024cd5d47999aa9e73becc14cb5');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '6eee45326f114d6ba567f64b10055eaa');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '09147fe6c278458cb77c4162fff447e3');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '1c8a24e0cf094e8e99346aa8b0e4391b');
INSERT INTO `sys_role_menu` VALUES ('4522f2188b9b466b8835040c9e93c481', '60d42fa10a804c23a502aa06d786182a');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '80b7ac4f349e458aa5ce0aade69ee8a8');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '8f0377fbeaba48c69b49966ebe072e7c');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'e9dc75e8fb7b4652898c2fc9030aa736');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '7177b2331d2d47b9a28a1eca191e1cfc');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'ba83791e19614a1eaf64d1c05633bbea');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '5735058475ef401da0e250ce40eb1db9');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'a16d7aeb3f844063ad4b7cffb04f3729');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'f25bfd5a71ff4c1c9ea30e70db047e22');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'f7a00dcffa7548fca0939335425650b0');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '7bea97e18fb6402aa2896ed0c5f3cdf4');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'fe34161d69bb4167ab07eb5ea4389dc1');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '8ef1e45375a6442eaa2272ddfcc270c9');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'ed6ed141d14443b8a08ac4d941c7bb2e');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '23846cd4025e485d9371705cc22a94c6');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '4b36cc8c72a946fd80e9321e6ff1fc94');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', '6eee45326f114d6ba567f64b10055eaa');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'bf7367d188174ef3970039c19ad4c520');
INSERT INTO `sys_role_menu` VALUES ('42207005210b48329010ee9b0fd84dd8', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '80b7ac4f349e458aa5ce0aade69ee8a8');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '8f0377fbeaba48c69b49966ebe072e7c');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'e9dc75e8fb7b4652898c2fc9030aa736');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '7177b2331d2d47b9a28a1eca191e1cfc');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'ba83791e19614a1eaf64d1c05633bbea');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '5735058475ef401da0e250ce40eb1db9');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '23846cd4025e485d9371705cc22a94c6');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '8ef1e45375a6442eaa2272ddfcc270c9');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'a16d7aeb3f844063ad4b7cffb04f3729');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'f25bfd5a71ff4c1c9ea30e70db047e22');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'f7a00dcffa7548fca0939335425650b0');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '7bea97e18fb6402aa2896ed0c5f3cdf4');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', 'ed6ed141d14443b8a08ac4d941c7bb2e');
INSERT INTO `sys_role_menu` VALUES ('55f08a70cf5e43a8af4575d4a7aec2cf', '6eee45326f114d6ba567f64b10055eaa');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '80b7ac4f349e458aa5ce0aade69ee8a8');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '8f0377fbeaba48c69b49966ebe072e7c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e9dc75e8fb7b4652898c2fc9030aa736');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7177b2331d2d47b9a28a1eca191e1cfc');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ba83791e19614a1eaf64d1c05633bbea');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5735058475ef401da0e250ce40eb1db9');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '53e10dcef27147388913d6df391946f7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '23846cd4025e485d9371705cc22a94c6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '8ef1e45375a6442eaa2272ddfcc270c9');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f9f54329eabd4d3a8b2229212007a54a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd20b5be25e2843c4a3c5e4a4a9b929e6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0e73d7b9cf8443c1a48ad197bc9a65ec');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3050a2d827814a84a45b392166f6bfce');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cab7887b8ded412788f988544559780f');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5f2f54d9dab84be8bab26a13431f2b59');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1e222a5a4b394a92bf3127d34301f8a2');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'a9c2eceebd64405a8b046b40bc7815cf');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '86bb35dea0ac402f95b85a274181e759');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'a16d7aeb3f844063ad4b7cffb04f3729');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6ddd6a14fc66445a874a6cb63754ec47');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '516d64a2b6584b83800e2948d7f87335');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cbc21bae64934398b3379835f3a75550');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cbb294ea95654891b5c0adbbbf6d19b3');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f627f3ad43984ab28ed85328c98b77b6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '8515f0f788ea435f9b41f32891da1f4e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0ec22065b0134f0091d5ce9f92e83183');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0cc1b6bedbc648a2bb8cab0ca2bbf02c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd157b33b8490445abbc892f332fd5cbb');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e52675e8b10045cb891662623b455861');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ed9f26e5926b4b87a2f2e605b02a2a7e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'bd606a93c49a407e8193bea7ad0370e7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0a6e43782e8342f0aeb2703912df88a9');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '24a47951159749459729dd1146229ea7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'adf38e430e8c4a8d87d0070ccf3358ff');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '93f7b2bce2054dcf8921654ccbbde876');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '329094c727f949ca8a22728f2ec560ff');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd9b453267f7240bcbae9f87169caeb6f');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e40193cfca8e425694f0b31cc2f0f04c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'fc340d1786864183bed4d56183905461');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0772a3f1f877424c80a34f8bc9da0268');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '15c6c2d81a954601833726705fef48de');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3b2b0c0ae215448f9ab7e53503cec4d6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '507b184823614a52a3a96571a1507129');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '72f75a6078384584b996d71bbfcf646d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '2dc85d8f3bb041918b35e0883db79a4c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '879a4e4883a0465a939dfc493232e2c2');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f5aa5aff8e0f4d1b914785d9d7fbc163');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '792d6e9db8c7485db632894148052486');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd86c48e786cb45418c069cefa3056c0a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cecb646ee700497fbf1ea3b0c776eb5a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4d0d72499bd84d9b827e3eccafade2e5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f9a6cce659334b0987384723b7707bad');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '8d93fd924f3542dea28e4335c4654834');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6fc4a70a0f13430dbb3ca9b42a8520d9');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '52a16b54ab384d628d40bd5304b3c812');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '682626f7d4d348729f84f7427bc142cc');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ae4adeb99c08458d83d7c663bffa0eca');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7021fa1a6f76415d9dc21ca28caa2dae');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd883f98ee747464bace45cf4ad111a85');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '81772c143625453b84f367ee6c444b5d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4dc997fef71e4862b9db22de8e99a618');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1734e586e96941268a4c5248b593cef9');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '56d0658c5a8848818ac05e8ffa5c0570');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ce709456e867425297955b3c40406d7e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3099f497480c4b1987bce3f3a26c3fb4');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'c3a44b478d3241b899b9c3f4611bc2b6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'fd63a8e389e04ff3a86c3cea53a3b9d5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7c040dfd8db347e5956a3bc1764653dc');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '2275cb125710414e91b617dd7c62f12c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0a972ce655cb4c84809d58668b655900');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'fc52d5284b8f4522802383c1ef732242');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'dd965b2c1dfd493fb5efc7e4bcac99d4');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '30a5e70a1456447ebf90b5546e9bc321');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '2a63040409094f1e9dc535dd78ce15b7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e864c78aba63448892cbcb6a3a7f4da7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ff6cd243a77c4ae98dacf6149c816c75');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '733d3f35d49f45af99ca9220048583ba');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '309dc29ad3c34408a68df8f867a5c9ff');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '96554b09a2dd4f82bab7546fa59acd35');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd568f4c2b687404e8aec7b9edcae5767');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '45d958ca78304f25b51f6c71cf66f6d8');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '44da90bc76a5419a841f4924333f7a66');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '9a9557177d334c209cf73c3817fe3b63');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0a43d291e0c94ad88c8b690009279e34');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5244f5c38eb24b918e9ad64d456daa38');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e6b6224617b04090a76e46a4b048fb96');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3888f05aa4064f788ba7ec51c495ce7c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'a11163584dfe456cbfd6fb2d4b74391b');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'c76a84f871d047db955dd1465c845ac1');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5768505c64e64c3395c87358520d158f');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '73a29d3f99224426b5a87c92da122275');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4781372b00bb4d52b429b58e72b80c68');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e461c62a1d5441619cd35612f3b40691');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f6fba69c3b704d79834b8bd2cc753729');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '50ba60ee650e4c739e6abc3ab71e4960');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '33aed9298643424783116e0cf0f7fcbe');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ef9f436c61654ec09efbfa79a40061cf');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '17500ef3a9e44b4fabb240162a164fcb');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7db6207d0dab4d6e95a7eee4f2efe875');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '31ed2243077c44448cce26abfd5ae574');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7125a72beee34b21ab3df9bf01b7bce6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '077cb6be4c7c41cc8955ee045a4f0286');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '36e0faf5062b4f6b95d4167cbb1f8fea');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3c24111091ad4a70ad2d9cc361311d2f');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1faa04cc182a4849b94a99286d972b0d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '298f7a5f36574102a87268d7ffbe77d6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '31aae024cd5d47999aa9e73becc14cb5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '83951e13c14643898e06b39cb428ec86');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f25bfd5a71ff4c1c9ea30e70db047e22');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f7a00dcffa7548fca0939335425650b0');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7bea97e18fb6402aa2896ed0c5f3cdf4');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'fe34161d69bb4167ab07eb5ea4389dc1');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4b36cc8c72a946fd80e9321e6ff1fc94');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ed6ed141d14443b8a08ac4d941c7bb2e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6eee45326f114d6ba567f64b10055eaa');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'bd07a59e350647d0895ea2a1e39a9994');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'bf7367d188174ef3970039c19ad4c520');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1c8a24e0cf094e8e99346aa8b0e4391b');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '09147fe6c278458cb77c4162fff447e3');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '60d42fa10a804c23a502aa06d786182a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '466cf9e004ae42b19e3e5e53395197df');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '36ce16235ac14cebbdfd7a4ddcd02a7b');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5adfb7b54d784242a7c524734a10d4bd');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4bdbe5fa320d4873890b1764bfbd2f47');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e7287128c8a5470081ed782a282d30c6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'a09dd5fc3517447b9668457fb77c2d08');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'b225e7eafec846f29023cca78f63bc18');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '437b5771ab0a42ec886a69e050c74a52');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1f6f98ceb052434cb5ea86ad84ddc971');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '10c8ae20fa6942c5b54a95df03270273');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6731cf86a00e4db283099ef7c7211448');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0b097e9dbad649048f956355d1e91278');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'ebaa84b4d7654791afa4cf116413f12e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'efeed24e41054b3da9edbddbf7cedeac');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'b19b23b0459a4754bf1fb8cb234450f2');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'e4256d7b0ffc4a02906cf900322b6213');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'b0edc6861a494b79b97990dc05f0a524');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f426468abf714b1599729f8c36ebbb0d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '9f20a757a6bc40ddbb650c70debbf660');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6bb17a41f6394ed0a8a6faf5ff781354');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '234f8ec3c2bc42bf9f6202aecae36fd6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4cd8e4e9519e4cff95465194fdcc8d88');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '17e1ee23ca1443f1bc886c2f5eb7c24b');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '2cb327ad59b140828fd26eb2a46cb948');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0706112ff5dc46e388064a99bcdb0561');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '66cc21d7ce104dd6877cbce114c59fb3');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'bcf64d623fdd4519ae345b7a08c071a1');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '2fab774f8b6d40cb9d7e187babab2d91');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1385ae887e5c4b8aa33fbf228be7f907');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6afc5075913d4df4b44a6476080e35a0');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cabbe834a7474675b899e8442b5c2604');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd1e991ad38a8424daf9f7eb000ee27f4');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd920314e925c451da6d881e7a29743b7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '02e86a61e99746bea34236ea73dd52a5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'b2631bbdbf824cc4b74d819c87962c0d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6b6de8c720c645a1808e1c3e9ccbfc90');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6075fc0cf0ef441b9d93cc3cab3445bf');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '9822bafbe3454dfd8e8b974ebc304d03');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3f330d729ca34dc9825c46122be1bfae');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '68cdbf694f71445c8587a20234d6fe31');
COMMIT;

-- ----------------------------
-- Table structure for sys_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_route`;
CREATE TABLE `sys_route` (
  `id` varchar(32) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '原始路径',
  `toUrl` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  `type` varchar(10) DEFAULT NULL COMMENT '转发类型',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_route
-- ----------------------------
BEGIN;
INSERT INTO `sys_route` VALUES ('73794be6224b401f92c4cf602dcf213f', '/sysadmin', '/platform/login', 'hide', 0, '', 1510391364, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '任务名',
  `jobClass` varchar(255) DEFAULT NULL COMMENT '执行类',
  `note` varchar(255) DEFAULT NULL COMMENT '任务说明',
  `cron` varchar(50) DEFAULT NULL COMMENT '定时规则',
  `data` text COMMENT '执行参数',
  `exeAt` int(32) DEFAULT NULL COMMENT '执行时间',
  `exeResult` text COMMENT '执行结果',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
BEGIN;
INSERT INTO `sys_task` VALUES ('6d7d69862a654610a6a406dd1b5bba30', '测试任务', 'com.marveliu.app.task.commons.ext.quartz.job.TestJob', '微信号：marveliu | 欢迎发送红包以示支持，多谢。。', '*/5 * * * * ?', '{\"hi\":\"Marveliu| send red packets of support,thank u\"}', 1527163465, '执行成功', 0, '405a28c9389d4a8581a29c283dc9f5b9', 1525853041, 0);
COMMIT;


-- ----------------------------
-- Table structure for sys_unit
-- ----------------------------
DROP TABLE IF EXISTS `sys_unit`;
CREATE TABLE `sys_unit` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `aliasName` varchar(100) DEFAULT NULL COMMENT '单位别名',
  `unitcode` varchar(20) DEFAULT NULL COMMENT '机构编码',
  `note` varchar(255) DEFAULT NULL COMMENT '单位介绍',
  `address` varchar(100) DEFAULT NULL COMMENT '单位地址',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '单位邮箱',
  `website` varchar(100) DEFAULT NULL COMMENT '单位网站',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `logo` varchar(255) DEFAULT NULL COMMENT '单位logo',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_unit
-- ----------------------------
BEGIN;
INSERT INTO `sys_unit` VALUES ('49978822d6eb4cc6b9f95a2434dda93f', 'd331f97e475d45b6be862f059f80f53e', '00010001', '产品经理', NULL, 'pm', '产品经理们啊', '', '', NULL, NULL, 3, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1511409410, 0, NULL);
INSERT INTO `sys_unit` VALUES ('4fb5c41d6ab642c9a3ca3d9433463a42', 'd331f97e475d45b6be862f059f80f53e', '00010003', '技术部', NULL, '', '', '', '', NULL, NULL, 5, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1517106890, 0, NULL);
INSERT INTO `sys_unit` VALUES ('8751a4514937472bb3a1f4e6e3104dfe', 'd331f97e475d45b6be862f059f80f53e', '00010002', '财务', NULL, 'cw', '算账发钱', '', '', NULL, NULL, 4, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1515593061, 0, NULL);
INSERT INTO `sys_unit` VALUES ('d331f97e475d45b6be862f059f80f53e', '', '0001', '武汉哎嘀信息科技有限公司', 'System', NULL, NULL, '湖北武汉洪山区武汉理工大学创业园710', '', '897920245@qq.com', 'http://liushangnan.win', 1, 1, '', 1510391361, 0, NULL);
INSERT INTO `sys_unit` VALUES ('fd02b028caed459590b68dde6d47f115', '', '0002', '系统雇员', NULL, '', '雇佣帮雇员管理系统雇员', '武汉市哎嘀信息科技有限公司', '', NULL, NULL, 2, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1510464366, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `loginname` varchar(120) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '密码盐',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `avatar` mediumblob COMMENT '用户头像',
  `isOnline` tinyint(1) DEFAULT NULL COMMENT '是否在线',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `email` varchar(255) DEFAULT NULL,
  `email_checked` tinyint(1) DEFAULT NULL COMMENT '邮箱是否已经验证过',
  `loginAt` int(32) DEFAULT NULL COMMENT '登陆时间',
  `loginIp` varchar(255) DEFAULT NULL COMMENT '登陆IP',
  `loginCount` int(32) DEFAULT NULL COMMENT '登陆次数',
  `customMenu` varchar(255) DEFAULT NULL COMMENT '常用菜单',
  `loginTheme` varchar(100) DEFAULT NULL COMMENT '皮肤样式',
  `loginSidebar` tinyint(1) DEFAULT NULL,
  `loginBoxed` tinyint(1) DEFAULT NULL,
  `loginScroll` tinyint(1) DEFAULT NULL,
  `loginPjax` tinyint(1) DEFAULT NULL,
  `unitid` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `wsid` varchar(32) DEFAULT NULL COMMENT 'wsid',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `phone_checked` tinyint(1) DEFAULT NULL COMMENT '电话是否已经验证过',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `unionid` varchar(100) DEFAULT NULL COMMENT '微信登录unionid',
  `icon` varchar(255) DEFAULT NULL COMMENT '用户头像img',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_SYS_USER_LOGINNAMAE` (`loginname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', 'superadmin', 'HhMeOxyUkFNDYCcr9YMnYYdtjikGxvUW5kta4veAf7k=', 'UYS3QFynjDo+aXsmLHOAuw==', '超级管理员', NULL, 1, 0, '897920245@qq.com', 0, 1527161403, '127.0.0.1', 741, '', 'palette.css', 1, 0, 1, 0, 'd331f97e475d45b6be862f059f80f53e', '405a28c9389d4a8581a29c283dc9f5b9', 1516352120, 0, 'uc0ngmi444h9mrgph1bkjr8d3e', '13277085517', NULL, '897920245', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `userId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', '859f09a331ba41e9b3d97ef5223e44de');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_unit
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_unit`;
CREATE TABLE `sys_user_unit` (
  `userId` varchar(32) DEFAULT NULL,
  `unitId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_unit
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_unit` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', 'd331f97e475d45b6be862f059f80f53e');
COMMIT;

-- ----------------------------
-- Table structure for sys_userinf
-- ----------------------------
DROP TABLE IF EXISTS `sys_userinf`;
CREATE TABLE `sys_userinf` (
  `id` varchar(32) NOT NULL COMMENT '员工编号',
  `userid` varchar(32) DEFAULT NULL COMMENT '登陆名',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `birthday` int(32) DEFAULT NULL COMMENT '出生日期',
  `sex` int(32) DEFAULT NULL COMMENT '性别',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `sys_userinf` VALUES ('gyb201800', '405a28c9389d4a8581a29c283dc9f5b9', 'marveliu', '897920245', '13277085517', 1515600000, 0, 0, '405a28c9389d4a8581a29c283dc9f5b9', 1523682604, 0);
COMMIT;

-- ----------------------------
-- Table structure for xm_apply
-- ----------------------------
DROP TABLE IF EXISTS `xm_apply`;
CREATE TABLE `xm_apply` (
  `id` varchar(50) NOT NULL COMMENT '申请编号',
  `gyid` varchar(32) DEFAULT NULL COMMENT '申请雇员编号',
  `xmtaskid` varchar(32) DEFAULT NULL COMMENT '申请任务书编号',
  `at` int(32) DEFAULT NULL COMMENT '申请时间',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `note` varchar(200) DEFAULT NULL COMMENT '说明',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_bill
-- ----------------------------
DROP TABLE IF EXISTS `xm_bill`;
CREATE TABLE `xm_bill` (
  `id` varchar(32) NOT NULL COMMENT '账单编号',
  `xminfid` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `gypayid` varchar(32) DEFAULT NULL COMMENT '雇员要求付款方式',
  `realgypayid` varchar(32) DEFAULT NULL COMMENT '实际付款方式',
  `prepaysum` float DEFAULT NULL COMMENT '预付总额',
  `paysum` float DEFAULT NULL COMMENT '实际付款总额',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `note` varchar(200) DEFAULT NULL COMMENT '说明',
  `payby` varchar(32) DEFAULT NULL COMMENT '付款人',
  `at` int(32) DEFAULT NULL COMMENT '付款时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `xm_evaluation`;
CREATE TABLE `xm_evaluation` (
  `id` varchar(32) NOT NULL COMMENT '评价编号',
  `xminfid` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `grade` float DEFAULT NULL COMMENT '评分',
  `note` varchar(200) DEFAULT NULL COMMENT '说明',
  `at` int(32) DEFAULT NULL COMMENT '评价时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_feedback
-- ----------------------------
DROP TABLE IF EXISTS `xm_feedback`;
CREATE TABLE `xm_feedback` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '反馈编号',
  `parentid` int(32) DEFAULT NULL COMMENT '父反馈编号',
  `code` varchar(32) DEFAULT NULL COMMENT '反馈编号',
  `xminfid` varchar(32) DEFAULT NULL COMMENT '项目编号',
  `at` int(32) DEFAULT NULL COMMENT '创建时间',
  `fileurl` varchar(255) DEFAULT NULL COMMENT '审核文件',
  `note` text COMMENT '雇员反馈',
  `gyid` varchar(32) DEFAULT NULL COMMENT '雇员编号',
  `nextcommit` int(32) DEFAULT NULL COMMENT '下一次提交时间',
  `reply` text COMMENT '审核反馈',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_inf
-- ----------------------------
DROP TABLE IF EXISTS `xm_inf`;
CREATE TABLE `xm_inf` (
  `id` varchar(50) NOT NULL COMMENT '项目编号',
  `gyid` varchar(32) DEFAULT NULL COMMENT '雇员编号',
  `xmtaskid` varchar(32) DEFAULT NULL COMMENT '任务书编号',
  `at` int(32) DEFAULT NULL COMMENT '立项时间',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `note` varchar(200) DEFAULT NULL COMMENT '说明',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_limit
-- ----------------------------
DROP TABLE IF EXISTS `xm_limit`;
CREATE TABLE `xm_limit` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `xmtaskid` varchar(32) DEFAULT NULL COMMENT '任务书编号',
  `skillid` varchar(32) DEFAULT NULL COMMENT '技能编号',
  `skilllevel` int(32) DEFAULT NULL COMMENT '技能等级',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_prepay
-- ----------------------------
DROP TABLE IF EXISTS `xm_prepay`;
CREATE TABLE `xm_prepay` (
  `id` varchar(32) NOT NULL COMMENT '预付流水',
  `billid` varchar(32) DEFAULT NULL COMMENT '账单编号',
  `gypayid` varchar(32) DEFAULT NULL COMMENT '雇员要求付款方式',
  `realgypayid` varchar(32) DEFAULT NULL COMMENT '实际付款方式',
  `prepaycount` float DEFAULT NULL COMMENT '预付总额',
  `note` varchar(200) DEFAULT NULL COMMENT '预付说明',
  `payby` varchar(32) DEFAULT NULL COMMENT '付款人',
  `at` int(32) DEFAULT NULL COMMENT '付款时间',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xm_task
-- ----------------------------
DROP TABLE IF EXISTS `xm_task`;
CREATE TABLE `xm_task` (
  `id` varchar(32) NOT NULL COMMENT '任务书编号',
  `taskname` varchar(100) DEFAULT NULL COMMENT '任务书名称',
  `original_order` varchar(100) DEFAULT NULL COMMENT '原始订单编号',
  `category` varchar(32) DEFAULT NULL COMMENT '任务书种类编号',
  `award` float DEFAULT NULL COMMENT '酬金',
  `designnum` int(32) DEFAULT NULL COMMENT '设计方案',
  `author` varchar(32) DEFAULT NULL COMMENT '发布者编号',
  `endtime` int(32) DEFAULT NULL COMMENT '终稿时间',
  `firstcommit` int(32) DEFAULT NULL COMMENT '初稿时间',
  `applyendtime` int(32) DEFAULT NULL COMMENT '申报截止时间',
  `info` varchar(500) DEFAULT NULL COMMENT '任务书摘要',
  `content` text COMMENT '任务书内容',
  `publishAt` int(32) DEFAULT NULL COMMENT '发布时间',
  `readnum` int(32) DEFAULT NULL COMMENT '阅读次数',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `fileurl` varchar(255) DEFAULT NULL COMMENT '附件',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` bigint(36) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- View structure for v_gy
-- ----------------------------
DROP VIEW IF EXISTS `v_gy`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_gy` AS select `gy_inf`.`id` AS `id`,`sys_user`.`username` AS `username`,`sys_user`.`qq` AS `qq`,`sys_user`.`phone` AS `phone`,`sys_user`.`email` AS `email`,`sys_user`.`email_checked` AS `email_checked`,`sys_user`.`wsid` AS `wsid`,`sys_user`.`avatar` AS `avatar`,`gy_inf`.`id` AS `gyid`,`gy_inf`.`userid` AS `userid`,`gy_inf`.`realname` AS `realName`,`gy_inf`.`birthday` AS `birthday`,`gy_inf`.`sex` AS `sex`,`getDicnameByCode`(concat('sex',`gy_inf`.`sex`)) AS `sexname`,`gy_inf`.`idcard` AS `idcard`,`getDicNameByCode`(`gy_inf`.`college`) AS `college`,`gy_inf`.`school` AS `school`,`gy_inf`.`major` AS `major`,`sys_user`.`isOnline` AS `isOnline`,`sys_user`.`loginAt` AS `loginAt`,`sys_user`.`loginIp` AS `loginIp`,`sys_user`.`loginCount` AS `loginCount`,`gy_inf`.`stuLevel` AS `stuLevel`,`getDicnameByCode`(concat('stulevel',`gy_inf`.`stuLevel`)) AS `stuLevelname`,`gy_inf`.`regYear` AS `regYear`,`gy_inf`.`status` AS `status`,`getDicnameByCode`(concat('gyauth',`gy_auth`.`status`)) AS `gyauthstatusname`,`gy_auth`.`id` AS `authid`,`gy_auth`.`stuCardB` AS `stuCardB`,`gy_auth`.`stuCardF` AS `stuCardF`,`gy_auth`.`idcardB` AS `idcardB`,`gy_auth`.`idcardF` AS `idcardF`,`gy_auth`.`reAuthTime` AS `reAuthTime`,`sys_user`.`disabled` AS `disabled`,`gy_auth`.`status` AS `gyauthstatus` from ((`gy_inf` left join `gy_auth` on((`gy_auth`.`gyid` = `gy_inf`.`id`))) join `sys_user` on((`gy_inf`.`userid` = `sys_user`.`id`)));

-- ----------------------------
-- View structure for v_gypay
-- ----------------------------
DROP VIEW IF EXISTS `v_gypay`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_gypay` AS select `gy_pay`.`id` AS `id`,`gy_pay`.`gyid` AS `gyid`,`gy_pay`.`payid` AS `payid`,`gy_pay`.`payname` AS `payname`,`gy_pay`.`first` AS `first`,`gy_pay`.`disabled` AS `disabled`,`gy_pay`.`opBy` AS `opBy`,`gy_pay`.`opAt` AS `opAt`,`gy_pay`.`delFlag` AS `delFlag`,`gy_pay`.`type` AS `type`,`gy_inf`.`realname` AS `realname`,`getDicnameByCode`(concat('pay',`gy_pay`.`type`)) AS `typename` from (`gy_pay` join `gy_inf` on((`gy_inf`.`id` = `gy_pay`.`gyid`)));

-- ----------------------------
-- View structure for v_user
-- ----------------------------
DROP VIEW IF EXISTS `v_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user` AS select `sys_user`.`loginname` AS `loginname`,`sys_user`.`username` AS `username`,`sys_user`.`email` AS `email`,`sys_user`.`phone` AS `phone`,`sys_user`.`qq` AS `qq`,`sys_role`.`name` AS `name`,`sys_role`.`code` AS `code`,`sys_role`.`aliasName` AS `aliasName`,`sys_role`.`disabled` AS `disabled`,`sys_user`.`email_checked` AS `email_checked`,`sys_user`.`phone_checked` AS `phone_checked` from ((`sys_user` join `sys_user_role` on((`sys_user_role`.`userId` = `sys_user`.`id`))) join `sys_role` on((`sys_role`.`id` = `sys_user_role`.`roleId`)));

-- ----------------------------
-- View structure for v_xmapply
-- ----------------------------
DROP VIEW IF EXISTS `v_xmapply`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_xmapply` AS select `xm_apply`.`id` AS `id`,`xm_apply`.`gyid` AS `gyid`,`xm_apply`.`xmtaskid` AS `xmtaskid`,`xm_apply`.`at` AS `at`,`xm_apply`.`status` AS `status`,`xm_apply`.`note` AS `note`,`xm_apply`.`opBy` AS `opBy`,`xm_apply`.`opAt` AS `opAt`,`xm_apply`.`delFlag` AS `delFlag`,`xm_task`.`taskname` AS `taskname`,`xm_task`.`author` AS `author`,`sys_userinf`.`realname` AS `authorrealname`,`gy_inf`.`realname` AS `gyrealname`,`getDicnameByCode`(concat('xmapplystatus',`xm_apply`.`status`)) AS `applystatus` from (((`xm_apply` join `xm_task` on((`xm_task`.`id` = `xm_apply`.`xmtaskid`))) left join `sys_userinf` on((`sys_userinf`.`id` = `xm_task`.`author`))) join `gy_inf` on((`gy_inf`.`id` = `xm_apply`.`gyid`)));

-- ----------------------------
-- View structure for v_xmbill
-- ----------------------------
DROP VIEW IF EXISTS `v_xmbill`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_xmbill` AS select `xm_bill`.`id` AS `id`,`xm_bill`.`xminfid` AS `xminfid`,`xm_bill`.`gypayid` AS `gypayid`,`xm_bill`.`realgypayid` AS `realgypayid`,`xm_task`.`award` AS `award`,`xm_bill`.`prepaysum` AS `prepaysum`,`xm_bill`.`paysum` AS `paysum`,`xm_bill`.`note` AS `note`,`xm_bill`.`payby` AS `payby`,`xm_bill`.`at` AS `at`,`xm_bill`.`opBy` AS `opBy`,`xm_bill`.`opAt` AS `opAt`,`xm_bill`.`delFlag` AS `delFlag`,`xm_bill`.`status` AS `status`,`sys_userinf`.`id` AS `sysuserid`,`sys_userinf`.`realname` AS `paybyname`,`xm_task`.`taskname` AS `taskname`,`xm_task`.`author` AS `author`,`sys_userinf1`.`realname` AS `authorrealname`,`getDicnameByCode`(concat('xmbillstatus',`xm_bill`.`status`)) AS `xmbillstatus` from (((((`xm_bill` left join `sys_user` on((`sys_user`.`id` = `xm_bill`.`payby`))) left join `sys_userinf` on((`sys_userinf`.`userid` = `sys_user`.`id`))) join `xm_inf` on((`xm_inf`.`id` = `xm_bill`.`xminfid`))) join `xm_task` on((`xm_task`.`id` = `xm_inf`.`xmtaskid`))) join `sys_userinf` `sys_userinf1` on((`sys_userinf1`.`id` = `xm_task`.`author`)));


-- ----------------------------
-- View structure for v_xmtask
-- ----------------------------
DROP VIEW IF EXISTS `v_xmtask`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_xmtask` AS select `xm_task`.`id` AS `id`,`xm_task`.`original_order` AS `original_order`,`xm_task`.`taskname` AS `taskname`,`xm_task`.`category` AS `category`,`xm_task`.`award` AS `award`,`xm_task`.`designnum` AS `designnum`,`xm_task`.`author` AS `author`,`xm_task`.`endtime` AS `endtime`,`xm_task`.`firstcommit` AS `firstcommit`,`xm_task`.`info` AS `info`,`xm_task`.`content` AS `content`,`xm_task`.`publishAt` AS `publishAt`,`xm_task`.`readnum` AS `readnum`,`xm_task`.`location` AS `location`,`xm_task`.`fileurl` AS `fileurl`,`xm_task`.`disabled` AS `disabled`,`xm_task`.`opBy` AS `opBy`,`xm_task`.`opAt` AS `opAt`,`xm_task`.`delFlag` AS `delFlag`,`xm_task`.`applyendtime` AS `applyendtime`,`xm_task`.`status` AS `status`,`getDicnameByCode`(concat('xmtaskstatus',`xm_task`.`status`)) AS `xmtaskstatus`,`sys_userinf`.`realname` AS `authorrealname` from (`xm_task` join `sys_userinf` on((`sys_userinf`.`id` = `xm_task`.`author`)));

-- ----------------------------
-- View structure for v_xminf
-- ----------------------------
DROP VIEW IF EXISTS `v_xminf`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_xminf` AS select `xm_inf`.`id` AS `id`,`xm_inf`.`gyid` AS `gyid`,`xm_inf`.`status` AS `status`,`getDicnameByCode`(concat('xminfstatus',`xm_inf`.`status`)) AS `xminfstatus`,`xm_inf`.`note` AS `note`,`xm_inf`.`at` AS `at`,`xm_inf`.`xmtaskid` AS `xmtaskid`,`xm_inf`.`opBy` AS `opBy`,`xm_inf`.`opAt` AS `opAt`,`xm_inf`.`delFlag` AS `delFlag`,`xm_task`.`taskname` AS `taskname`,`sys_userinf`.`realname` AS `authorrealname`,`xm_task`.`author` AS `author`,`xm_task`.`category` AS `category`,`xm_task`.`award` AS `award`,`xm_task`.`designnum` AS `designnum`,`xm_task`.`endtime` AS `endtime`,`xm_task`.`firstcommit` AS `firstcommit`,`xm_task`.`applyendtime` AS `applyendtime`,`xm_task`.`info` AS `taskinfo`,`xm_task`.`content` AS `taskcontent`,`xm_task`.`publishAt` AS `publishAt`,`xm_task`.`readnum` AS `readnum`,`xm_task`.`location` AS `location`,`xm_task`.`fileurl` AS `fileurl`,`xm_bill`.`id` AS `xmbillid`,`gy_inf`.`realname` AS `realname`,`xm_bill`.`note` AS `xmbillnote`,`xm_evaluation`.`id` AS `xmevaid`,`xm_evaluation`.`grade` AS `xmevagrade`,`xm_evaluation`.`note` AS `xmevanote`,`xm_bill`.`paysum` AS `paysum` from (((((`xm_inf` join `xm_task` on((`xm_task`.`id` = `xm_inf`.`xmtaskid`))) left join `sys_userinf` on((`sys_userinf`.`id` = `xm_task`.`author`))) left join `xm_bill` on((`xm_bill`.`xminfid` = `xm_inf`.`id`))) left join `gy_inf` on((`gy_inf`.`id` = `xm_inf`.`gyid`))) left join `xm_evaluation` on((`xm_evaluation`.`xminfid` = `xm_inf`.`id`)));

-- ----------------------------
-- View structure for v_xmfeedback
-- ----------------------------
DROP VIEW IF EXISTS `v_xmfeedback`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_xmfeedback` AS select `xm_feedback`.`id` AS `id`,`xm_feedback`.`xminfid` AS `xminfid`,`xm_feedback`.`at` AS `at`,`xm_feedback`.`fileurl` AS `fileurl`,`xm_feedback`.`note` AS `note`,`xm_feedback`.`gyid` AS `gyid`,`xm_feedback`.`nextcommit` AS `nextcommit`,`xm_feedback`.`reply` AS `reply`,`xm_feedback`.`status` AS `status`,`xm_feedback`.`opBy` AS `opBy`,`xm_feedback`.`opAt` AS `opAt`,`xm_feedback`.`delFlag` AS `delFlag`,`v_xminf`.`author` AS `author`,`v_xminf`.`taskname` AS `taskname`,`gy_inf`.`realname` AS `realname`,`gy_inf`.`phone` AS `phone`,`getDicnameByCode`(concat('xmfeedbackstatus',`xm_feedback`.`status`)) AS `xmfeedbackstatus`,`xm_feedback`.`parentid` AS `parentid`,`xm_feedback`.`code` AS `code`,`sys_userinf`.`realname` AS `authorrealname` from (((`xm_feedback` join `v_xminf` on((`xm_feedback`.`xminfid` = `v_xminf`.`id`))) join `gy_inf` on((`gy_inf`.`id` = `xm_feedback`.`gyid`))) left join `sys_userinf` on((`sys_userinf`.`id` = `v_xminf`.`author`)));


DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;  
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;  
DROP TABLE IF EXISTS QRTZ_LOCKS;  
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_TRIGGERS;  
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;  
DROP TABLE IF EXISTS QRTZ_CALENDARS;  
  
CREATE TABLE QRTZ_JOB_DETAILS(  
SCHED_NAME VARCHAR(120) NOT NULL,  
JOB_NAME VARCHAR(200) NOT NULL,  
JOB_GROUP VARCHAR(200) NOT NULL,  
DESCRIPTION VARCHAR(250) NULL,  
JOB_CLASS_NAME VARCHAR(250) NOT NULL,  
IS_DURABLE VARCHAR(1) NOT NULL,  
IS_NONCONCURRENT VARCHAR(1) NOT NULL,  
IS_UPDATE_DATA VARCHAR(1) NOT NULL,  
REQUESTS_RECOVERY VARCHAR(1) NOT NULL,  
JOB_DATA BLOB NULL,  
PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_TRIGGERS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
TRIGGER_NAME VARCHAR(200) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
JOB_NAME VARCHAR(200) NOT NULL,  
JOB_GROUP VARCHAR(200) NOT NULL,  
DESCRIPTION VARCHAR(250) NULL,  
NEXT_FIRE_TIME BIGINT(13) NULL,  
PREV_FIRE_TIME BIGINT(13) NULL,  
PRIORITY INTEGER NULL,  
TRIGGER_STATE VARCHAR(16) NOT NULL,  
TRIGGER_TYPE VARCHAR(8) NOT NULL,  
START_TIME BIGINT(13) NOT NULL,  
END_TIME BIGINT(13) NULL,  
CALENDAR_NAME VARCHAR(200) NULL,  
MISFIRE_INSTR SMALLINT(2) NULL,  
JOB_DATA BLOB NULL,  
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),  
FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)  
REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_SIMPLE_TRIGGERS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
TRIGGER_NAME VARCHAR(200) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
REPEAT_COUNT BIGINT(7) NOT NULL,  
REPEAT_INTERVAL BIGINT(12) NOT NULL,  
TIMES_TRIGGERED BIGINT(10) NOT NULL,  
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),  
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)  
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_CRON_TRIGGERS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
TRIGGER_NAME VARCHAR(200) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
CRON_EXPRESSION VARCHAR(120) NOT NULL,  
TIME_ZONE_ID VARCHAR(80),  
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),  
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)  
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_SIMPROP_TRIGGERS  
  (            
    SCHED_NAME VARCHAR(120) NOT NULL,  
    TRIGGER_NAME VARCHAR(200) NOT NULL,  
    TRIGGER_GROUP VARCHAR(200) NOT NULL,  
    STR_PROP_1 VARCHAR(512) NULL,  
    STR_PROP_2 VARCHAR(512) NULL,  
    STR_PROP_3 VARCHAR(512) NULL,  
    INT_PROP_1 INT NULL,  
    INT_PROP_2 INT NULL,  
    LONG_PROP_1 BIGINT NULL,  
    LONG_PROP_2 BIGINT NULL,  
    DEC_PROP_1 NUMERIC(13,4) NULL,  
    DEC_PROP_2 NUMERIC(13,4) NULL,  
    BOOL_PROP_1 VARCHAR(1) NULL,  
    BOOL_PROP_2 VARCHAR(1) NULL,  
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),  
    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)   
    REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_BLOB_TRIGGERS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
TRIGGER_NAME VARCHAR(200) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
BLOB_DATA BLOB NULL,  
PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),  
INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),  
FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)  
REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_CALENDARS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
CALENDAR_NAME VARCHAR(200) NOT NULL,  
CALENDAR BLOB NOT NULL,  
PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_FIRED_TRIGGERS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
ENTRY_ID VARCHAR(95) NOT NULL,  
TRIGGER_NAME VARCHAR(200) NOT NULL,  
TRIGGER_GROUP VARCHAR(200) NOT NULL,  
INSTANCE_NAME VARCHAR(200) NOT NULL,  
FIRED_TIME BIGINT(13) NOT NULL,  
SCHED_TIME BIGINT(13) NOT NULL,  
PRIORITY INTEGER NOT NULL,  
STATE VARCHAR(16) NOT NULL,  
JOB_NAME VARCHAR(200) NULL,  
JOB_GROUP VARCHAR(200) NULL,  
IS_NONCONCURRENT VARCHAR(1) NULL,  
REQUESTS_RECOVERY VARCHAR(1) NULL,  
PRIMARY KEY (SCHED_NAME,ENTRY_ID))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_SCHEDULER_STATE (  
SCHED_NAME VARCHAR(120) NOT NULL,  
INSTANCE_NAME VARCHAR(200) NOT NULL,  
LAST_CHECKIN_TIME BIGINT(13) NOT NULL,  
CHECKIN_INTERVAL BIGINT(13) NOT NULL,  
PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))  
ENGINE=InnoDB;  
  
CREATE TABLE QRTZ_LOCKS (  
SCHED_NAME VARCHAR(120) NOT NULL,  
LOCK_NAME VARCHAR(40) NOT NULL,  
PRIMARY KEY (SCHED_NAME,LOCK_NAME))  
ENGINE=InnoDB;  
  
CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);  
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);  
  
CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);  
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);  
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);  
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);  
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);  
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);  
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);  
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);  
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);  
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);  
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);  
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);  
  
CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);  
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);  
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);  
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);  
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);  
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);  
  
commit;   