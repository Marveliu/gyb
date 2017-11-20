/*
Navicat MySQL Data Transfer

Source Server         : Marveliu
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : nutzwk

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-20 17:55:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `shopid` varchar(32) DEFAULT NULL COMMENT '预留商城ID',
  `title` varchar(120) DEFAULT NULL COMMENT '文章标题',
  `info` varchar(500) DEFAULT NULL COMMENT '文章简介',
  `author` varchar(50) DEFAULT NULL COMMENT '文章作者',
  `picurl` varchar(255) DEFAULT NULL COMMENT '标题图',
  `content` text COMMENT '文章内容',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `publishAt` int(32) DEFAULT NULL COMMENT '发布时间',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `channelId` varchar(32) DEFAULT NULL,
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('7b3c0ddd17ec4e1cb342e148eafa9ad0', null, '啊手动阀', '', '超级管理员', '/upload/image/20171116/099up2eg58ij2oi69g8j83jpiu.png', '', '0', '1510826365', '1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510826375', '0');

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `shopid` varchar(32) DEFAULT NULL COMMENT '预留商城ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `name` varchar(100) DEFAULT NULL COMMENT '栏目名称',
  `type` varchar(20) DEFAULT NULL COMMENT '栏目类型',
  `url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式',
  `isShow` tinyint(1) DEFAULT NULL COMMENT '是否显示',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('db04902d47364a849f1199e71977b4be', null, '', '0001', '测试啊', 'article', '', '_self', '0', '0', '1', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510655108', '0');

-- ----------------------------
-- Table structure for cms_class_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_class_link`;
CREATE TABLE `cms_class_link` (
  `classId` varchar(32) DEFAULT NULL,
  `linkId` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_class_link
-- ----------------------------

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_link
-- ----------------------------

-- ----------------------------
-- Table structure for cms_link_class
-- ----------------------------
DROP TABLE IF EXISTS `cms_link_class`;
CREATE TABLE `cms_link_class` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(120) DEFAULT NULL COMMENT '分类名称',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_link_class
-- ----------------------------

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('site', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '405a28c9389d4a8581a29c283dc9f5b9', '1510541997', '0');

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
  `status` int(32) DEFAULT NULL COMMENT '状态',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_auth
-- ----------------------------
INSERT INTO `gy_auth` VALUES ('528fcfcb7a5a4635a2ca0bc6b0782af5', '171003', '1510822624', '/upload/image/20171116/7ttqf84arki3ir168f3ccibd2n.png', '/upload/image/20171116/6tnc1paregj9jpqof2j9be1ts9.png', '/upload/image/20171116/u4plqqcrhgib7rtu24gd406gu4.png', '/upload/image/20171116/pagqob1bmggebqg9v7n8sk85ep.png', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1510903211', '0', '尼玛也');
INSERT INTO `gy_auth` VALUES ('f726f016881543a19e002cfbfdd10ff6', '171001', '1510913059', '/upload/image/20171117/pfl7ded4gcg04qkebddulu5rqr.png', '/upload/image/20171117/qveihfqolcgk3oq2h5gtsj9svj.png', '/upload/image/20171117/p0ovgrimu8ivdpi35iqp1a60pe.png', '/upload/image/20171117/p1rs4mueeojm3rj497v0l5bcht.png', '1', '', '1510913059', '0', null);

-- ----------------------------
-- Table structure for gy_inf
-- ----------------------------
DROP TABLE IF EXISTS `gy_inf`;
CREATE TABLE `gy_inf` (
  `id` varchar(32) NOT NULL COMMENT '雇员编号',
  `userid` varchar(32) DEFAULT NULL COMMENT '账号编号',
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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gy_inf
-- ----------------------------
INSERT INTO `gy_inf` VALUES ('171001', '6b5e6721e0164f41a646dcdc13537901', '刘尚楠', '897920245', '15927448761', '1510822523', '0', '420682199609123518', '3', '12', '1', '1510912976', '0', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511159123', '0');
INSERT INTO `gy_inf` VALUES ('171003', '28e8a294dd73436583f6d5ab0c8bceaf', '周江', '897920245', '15927448761', '1510822523', '0', '420682199609123519', '1', '12', '1', '1510822523', '0', '0', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157492', '0');

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lib_skill
-- ----------------------------
INSERT INTO `lib_skill` VALUES ('1dae681fb07c47f1aedd80022fd63e42', '54136e06f07647bb828ba9128cce6e59', '00030001', '数学', null, '12342314', '124234', null, '3', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511171371', '0');
INSERT INTO `lib_skill` VALUES ('465e29e2069e4637915acbc9aa17375b', '', '0004', 'ps', null, '123', '12341234', null, '3', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511171351', '0');
INSERT INTO `lib_skill` VALUES ('54136e06f07647bb828ba9128cce6e59', '', '0003', '测试', null, '01', '哈哈', null, '3', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1511166549', '0');

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lib_task
-- ----------------------------
INSERT INTO `lib_task` VALUES ('1d2f6ef4ff56455ba3fe13c807375d5e', 'eba1c63c2beb46a5a99a12d3520c9685', '00030001', '海报', null, 'ds001', '海报种类的设计', null, '3', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511167444', '0');
INSERT INTO `lib_task` VALUES ('daeeaae25c534140a73efdcb6f39cda1', 'eba1c63c2beb46a5a99a12d3520c9685', '00030002', 'asdf', null, 'asdf', 'saddf', null, '3', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511167807', '0');
INSERT INTO `lib_task` VALUES ('eba1c63c2beb46a5a99a12d3520c9685', '', '0003', '设计', null, 'ds', '设计一些乱七八糟的事情', null, '3', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1511167415', '0');

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
INSERT INTO `lib_task_skill` VALUES ('eba1c63c2beb46a5a99a12d3520c9685', '465e29e2069e4637915acbc9aa17375b');

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api` (
  `id` varchar(32) NOT NULL,
  `appName` varchar(20) DEFAULT NULL COMMENT 'appName',
  `appId` varchar(255) DEFAULT NULL COMMENT 'appId',
  `appSecret` varchar(255) DEFAULT NULL COMMENT 'appSecret',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_api
-- ----------------------------

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
  PRIMARY KEY (`configKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('AppDomain', '127.0.0.1', '系统域名', '', '1510391361', '0');
INSERT INTO `sys_config` VALUES ('AppName', '雇佣帮雇佣管理系统', '系统名称', '', '1510391361', '0');
INSERT INTO `sys_config` VALUES ('AppShrotName', 'Gyb', '系统短名称', '', '1510391361', '0');
INSERT INTO `sys_config` VALUES ('AppUploadPath', '/upload', '文件上传文件夹', '', '1510391361', '0');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_DICT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL COMMENT '创建昵称',
  `type` varchar(20) DEFAULT NULL COMMENT '日志类型',
  `tag` varchar(50) DEFAULT NULL COMMENT '日志标识',
  `src` varchar(255) DEFAULT NULL COMMENT '执行类',
  `ip` varchar(255) DEFAULT NULL COMMENT '来源IP',
  `msg` text COMMENT '日志内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', 'be3244520c5b421fb018cbd4473f5f06', '1510391417', null);
INSERT INTO `sys_log` VALUES ('2', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510391490', null);
INSERT INTO `sys_log` VALUES ('3', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510391555', null);
INSERT INTO `sys_log` VALUES ('4', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510391754', null);
INSERT INTO `sys_log` VALUES ('5', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510391852', null);
INSERT INTO `sys_log` VALUES ('6', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510394995', null);
INSERT INTO `sys_log` VALUES ('7', '超级管理员', 'aop.after', '添加角色', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#addDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510395050', null);
INSERT INTO `sys_log` VALUES ('8', '超级管理员', 'aop.after', '添加用户到角色', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#pushUser', '127.0.0.1', '角色名称:游客,用户ID:5ef2004ae6fb4d2090ec1f8cc247dec4', '405a28c9389d4a8581a29c283dc9f5b9', '1510395063', null);
INSERT INTO `sys_log` VALUES ('9', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510395067', null);
INSERT INTO `sys_log` VALUES ('10', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510459182', null);
INSERT INTO `sys_log` VALUES ('11', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510459243', null);
INSERT INTO `sys_log` VALUES ('12', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510459346', null);
INSERT INTO `sys_log` VALUES ('13', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:', '', '1510463199', null);
INSERT INTO `sys_log` VALUES ('14', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:', '', '1510463232', null);
INSERT INTO `sys_log` VALUES ('15', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:sb', '', '1510463287', null);
INSERT INTO `sys_log` VALUES ('16', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '102849513ea1400cbe6db7999ad420e3', '1510463391', null);
INSERT INTO `sys_log` VALUES ('17', '1', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '102849513ea1400cbe6db7999ad420e3', '1510463416', null);
INSERT INTO `sys_log` VALUES ('18', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510463420', null);
INSERT INTO `sys_log` VALUES ('19', '超级管理员', 'aop.after', '添加角色', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#addDo', '127.0.0.1', '角色名称:未认证雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510463511', null);
INSERT INTO `sys_log` VALUES ('20', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员中心', '405a28c9389d4a8581a29c283dc9f5b9', '1510463576', null);
INSERT INTO `sys_log` VALUES ('21', '超级管理员', 'aop.after', '新建单位', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUnitController#addDo', '127.0.0.1', '单位名称:系统雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510464366', null);
INSERT INTO `sys_log` VALUES ('22', '超级管理员', 'aop.after', '新建用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#addDo', '127.0.0.1', '用户名:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1510464586', null);
INSERT INTO `sys_log` VALUES ('23', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:雇员管理', '405a28c9389d4a8581a29c283dc9f5b9', '1510464772', null);
INSERT INTO `sys_log` VALUES ('24', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510464935', null);
INSERT INTO `sys_log` VALUES ('25', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:个人中心', '405a28c9389d4a8581a29c283dc9f5b9', '1510465041', null);
INSERT INTO `sys_log` VALUES ('26', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510465051', null);
INSERT INTO `sys_log` VALUES ('27', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510465069', null);
INSERT INTO `sys_log` VALUES ('28', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:平台新闻', '405a28c9389d4a8581a29c283dc9f5b9', '1510465152', null);
INSERT INTO `sys_log` VALUES ('29', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:平台项目', '405a28c9389d4a8581a29c283dc9f5b9', '1510465216', null);
INSERT INTO `sys_log` VALUES ('30', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510465229', null);
INSERT INTO `sys_log` VALUES ('31', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:未认证雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510465241', null);
INSERT INTO `sys_log` VALUES ('32', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510465266', null);
INSERT INTO `sys_log` VALUES ('33', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:hello', '', '1510465552', null);
INSERT INTO `sys_log` VALUES ('34', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:hello', '', '1510465600', null);
INSERT INTO `sys_log` VALUES ('35', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510468121', null);
INSERT INTO `sys_log` VALUES ('36', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510468452', null);
INSERT INTO `sys_log` VALUES ('37', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510468861', null);
INSERT INTO `sys_log` VALUES ('38', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510468907', null);
INSERT INTO `sys_log` VALUES ('39', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '102849513ea1400cbe6db7999ad420e3', '1510468917', null);
INSERT INTO `sys_log` VALUES ('40', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510468930', null);
INSERT INTO `sys_log` VALUES ('41', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510469078', null);
INSERT INTO `sys_log` VALUES ('42', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510469306', null);
INSERT INTO `sys_log` VALUES ('43', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510469310', null);
INSERT INTO `sys_log` VALUES ('44', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510469319', null);
INSERT INTO `sys_log` VALUES ('45', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510470353', null);
INSERT INTO `sys_log` VALUES ('46', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510470568', null);
INSERT INTO `sys_log` VALUES ('47', '超级管理员', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510470609', null);
INSERT INTO `sys_log` VALUES ('48', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510470789', null);
INSERT INTO `sys_log` VALUES ('49', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510470823', null);
INSERT INTO `sys_log` VALUES ('50', '超级管理员', 'aop.after', '重置密码', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#resetPwd', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510470834', null);
INSERT INTO `sys_log` VALUES ('51', '超级管理员', 'aop.after', '重置密码', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#resetPwd', '127.0.0.1', '用户名:superadmin', '405a28c9389d4a8581a29c283dc9f5b9', '1510470838', null);
INSERT INTO `sys_log` VALUES ('52', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510470841', null);
INSERT INTO `sys_log` VALUES ('53', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510470848', null);
INSERT INTO `sys_log` VALUES ('54', '1', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510471049', null);
INSERT INTO `sys_log` VALUES ('55', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510471055', null);
INSERT INTO `sys_log` VALUES ('56', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510472083', null);
INSERT INTO `sys_log` VALUES ('57', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510472089', null);
INSERT INTO `sys_log` VALUES ('58', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510536469', null);
INSERT INTO `sys_log` VALUES ('59', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员信息修改', '405a28c9389d4a8581a29c283dc9f5b9', '1510536616', null);
INSERT INTO `sys_log` VALUES ('60', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:超管权限', '405a28c9389d4a8581a29c283dc9f5b9', '1510536654', null);
INSERT INTO `sys_log` VALUES ('61', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510536668', null);
INSERT INTO `sys_log` VALUES ('62', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510536673', null);
INSERT INTO `sys_log` VALUES ('63', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510536677', null);
INSERT INTO `sys_log` VALUES ('64', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:超管权限', '405a28c9389d4a8581a29c283dc9f5b9', '1510537525', null);
INSERT INTO `sys_log` VALUES ('65', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510537529', null);
INSERT INTO `sys_log` VALUES ('66', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510537539', null);
INSERT INTO `sys_log` VALUES ('67', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:增加雇员信息', '405a28c9389d4a8581a29c283dc9f5b9', '1510537718', null);
INSERT INTO `sys_log` VALUES ('68', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员信息编辑', '405a28c9389d4a8581a29c283dc9f5b9', '1510537766', null);
INSERT INTO `sys_log` VALUES ('69', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员信息删除', '405a28c9389d4a8581a29c283dc9f5b9', '1510537795', null);
INSERT INTO `sys_log` VALUES ('70', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510537895', null);
INSERT INTO `sys_log` VALUES ('71', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510537897', null);
INSERT INTO `sys_log` VALUES ('72', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510537900', null);
INSERT INTO `sys_log` VALUES ('73', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510537902', null);
INSERT INTO `sys_log` VALUES ('74', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', 'c2f40780c0bf40bea1f0a75d74bade0b', '405a28c9389d4a8581a29c283dc9f5b9', '1510537921', null);
INSERT INTO `sys_log` VALUES ('75', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', 'c2f40780c0bf40bea1f0a75d74bade0b', '405a28c9389d4a8581a29c283dc9f5b9', '1510537935', null);
INSERT INTO `sys_log` VALUES ('76', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', 'c2f40780c0bf40bea1f0a75d74bade0b', '405a28c9389d4a8581a29c283dc9f5b9', '1510537988', null);
INSERT INTO `sys_log` VALUES ('77', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:信息修改', '405a28c9389d4a8581a29c283dc9f5b9', '1510538124', null);
INSERT INTO `sys_log` VALUES ('78', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:身份认证', '405a28c9389d4a8581a29c283dc9f5b9', '1510538168', null);
INSERT INTO `sys_log` VALUES ('79', '超级管理员', 'aop.after', '删除菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#delete', '127.0.0.1', '菜单名称:身份认证', '405a28c9389d4a8581a29c283dc9f5b9', '1510538189', null);
INSERT INTO `sys_log` VALUES ('80', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:身份认证', '405a28c9389d4a8581a29c283dc9f5b9', '1510538219', null);
INSERT INTO `sys_log` VALUES ('81', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:未认证雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510538252', null);
INSERT INTO `sys_log` VALUES ('82', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510538282', null);
INSERT INTO `sys_log` VALUES ('83', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510538287', null);
INSERT INTO `sys_log` VALUES ('84', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510538310', null);
INSERT INTO `sys_log` VALUES ('85', '1', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510538643', null);
INSERT INTO `sys_log` VALUES ('86', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510538655', null);
INSERT INTO `sys_log` VALUES ('87', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:雇员信息列表', '405a28c9389d4a8581a29c283dc9f5b9', '1510538954', null);
INSERT INTO `sys_log` VALUES ('88', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:个人中心', '405a28c9389d4a8581a29c283dc9f5b9', '1510542505', null);
INSERT INTO `sys_log` VALUES ('89', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510569476', null);
INSERT INTO `sys_log` VALUES ('90', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员身份审核', '405a28c9389d4a8581a29c283dc9f5b9', '1510569699', null);
INSERT INTO `sys_log` VALUES ('91', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:雇员身份审核', '405a28c9389d4a8581a29c283dc9f5b9', '1510569719', null);
INSERT INTO `sys_log` VALUES ('92', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员身份审核增加', '405a28c9389d4a8581a29c283dc9f5b9', '1510569758', null);
INSERT INTO `sys_log` VALUES ('93', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员身份审核删除', '405a28c9389d4a8581a29c283dc9f5b9', '1510569774', null);
INSERT INTO `sys_log` VALUES ('94', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:雇员身份审核编辑', '405a28c9389d4a8581a29c283dc9f5b9', '1510569791', null);
INSERT INTO `sys_log` VALUES ('95', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:身份信息', '405a28c9389d4a8581a29c283dc9f5b9', '1510569893', null);
INSERT INTO `sys_log` VALUES ('96', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:身份信息', '405a28c9389d4a8581a29c283dc9f5b9', '1510570069', null);
INSERT INTO `sys_log` VALUES ('97', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:信息修改', '405a28c9389d4a8581a29c283dc9f5b9', '1510570087', null);
INSERT INTO `sys_log` VALUES ('98', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:身份认证', '405a28c9389d4a8581a29c283dc9f5b9', '1510570155', null);
INSERT INTO `sys_log` VALUES ('99', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510570178', null);
INSERT INTO `sys_log` VALUES ('100', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1510570179', null);
INSERT INTO `sys_log` VALUES ('101', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510570198', null);
INSERT INTO `sys_log` VALUES ('102', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:游客', '405a28c9389d4a8581a29c283dc9f5b9', '1510570210', null);
INSERT INTO `sys_log` VALUES ('103', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:未认证雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510570222', null);
INSERT INTO `sys_log` VALUES ('104', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570225', null);
INSERT INTO `sys_log` VALUES ('105', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570227', null);
INSERT INTO `sys_log` VALUES ('106', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:雇员身份审核删除', '405a28c9389d4a8581a29c283dc9f5b9', '1510570250', null);
INSERT INTO `sys_log` VALUES ('107', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:雇员身份审核编辑', '405a28c9389d4a8581a29c283dc9f5b9', '1510570260', null);
INSERT INTO `sys_log` VALUES ('108', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570266', null);
INSERT INTO `sys_log` VALUES ('109', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570268', null);
INSERT INTO `sys_log` VALUES ('110', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570293', null);
INSERT INTO `sys_log` VALUES ('111', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510570300', null);
INSERT INTO `sys_log` VALUES ('112', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570772', null);
INSERT INTO `sys_log` VALUES ('113', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570795', null);
INSERT INTO `sys_log` VALUES ('114', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510570803', null);
INSERT INTO `sys_log` VALUES ('115', '1', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1510570869', null);
INSERT INTO `sys_log` VALUES ('116', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510570878', null);
INSERT INTO `sys_log` VALUES ('117', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:lover', '', '1510641295', null);
INSERT INTO `sys_log` VALUES ('118', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:lover', '', '1510641463', null);
INSERT INTO `sys_log` VALUES ('119', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:lover', '', '1510641653', null);
INSERT INTO `sys_log` VALUES ('120', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510641678', null);
INSERT INTO `sys_log` VALUES ('121', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510641760', null);
INSERT INTO `sys_log` VALUES ('122', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510642113', null);
INSERT INTO `sys_log` VALUES ('123', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510642121', null);
INSERT INTO `sys_log` VALUES ('124', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510642510', null);
INSERT INTO `sys_log` VALUES ('125', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1510642576', null);
INSERT INTO `sys_log` VALUES ('126', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510642683', null);
INSERT INTO `sys_log` VALUES ('127', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '111', '405a28c9389d4a8581a29c283dc9f5b9', '1510642707', null);
INSERT INTO `sys_log` VALUES ('128', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '-1111', '405a28c9389d4a8581a29c283dc9f5b9', '1510642775', null);
INSERT INTO `sys_log` VALUES ('129', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510646853', null);
INSERT INTO `sys_log` VALUES ('130', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '-1111', '405a28c9389d4a8581a29c283dc9f5b9', '1510646865', null);
INSERT INTO `sys_log` VALUES ('131', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '111', '405a28c9389d4a8581a29c283dc9f5b9', '1510646868', null);
INSERT INTO `sys_log` VALUES ('132', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '171110', '405a28c9389d4a8581a29c283dc9f5b9', '1510646888', null);
INSERT INTO `sys_log` VALUES ('133', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510646914', null);
INSERT INTO `sys_log` VALUES ('134', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646938', null);
INSERT INTO `sys_log` VALUES ('135', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646939', null);
INSERT INTO `sys_log` VALUES ('136', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646940', null);
INSERT INTO `sys_log` VALUES ('137', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646941', null);
INSERT INTO `sys_log` VALUES ('138', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646942', null);
INSERT INTO `sys_log` VALUES ('139', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510646972', null);
INSERT INTO `sys_log` VALUES ('140', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647006', null);
INSERT INTO `sys_log` VALUES ('141', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647529', null);
INSERT INTO `sys_log` VALUES ('142', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647597', null);
INSERT INTO `sys_log` VALUES ('143', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647602', null);
INSERT INTO `sys_log` VALUES ('144', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647659', null);
INSERT INTO `sys_log` VALUES ('145', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647666', null);
INSERT INTO `sys_log` VALUES ('146', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '', '1510647669', null);
INSERT INTO `sys_log` VALUES ('147', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:lover', '', '1510647674', null);
INSERT INTO `sys_log` VALUES ('148', '1', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', 'f994654ff9b449e8b16324e0d566e7fc', '1510648294', null);
INSERT INTO `sys_log` VALUES ('149', '1', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', 'f994654ff9b449e8b16324e0d566e7fc', '1510648370', null);
INSERT INTO `sys_log` VALUES ('150', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510651397', null);
INSERT INTO `sys_log` VALUES ('151', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510651474', null);
INSERT INTO `sys_log` VALUES ('152', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510651476', null);
INSERT INTO `sys_log` VALUES ('153', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510651496', null);
INSERT INTO `sys_log` VALUES ('154', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510655056', null);
INSERT INTO `sys_log` VALUES ('155', '超级管理员', 'aop.after', '鏂板缓鏍忕洰', 'cn.wizzer.app.web.modules.controllers.platform.cms.CmsChannelController#addDo', '127.0.0.1', '鏍忕洰鍚嶇О:测试啊', '405a28c9389d4a8581a29c283dc9f5b9', '1510655108', null);
INSERT INTO `sys_log` VALUES ('156', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510658693', null);
INSERT INTO `sys_log` VALUES ('157', '超级管理员', 'aop.after', '鍒犻櫎鐢ㄦ埛', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '鐢ㄦ埛鍚�:test', '405a28c9389d4a8581a29c283dc9f5b9', '1510658704', null);
INSERT INTO `sys_log` VALUES ('158', '超级管理员', 'aop.after', '鏂板缓鐢ㄦ埛', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:尼玛', '405a28c9389d4a8581a29c283dc9f5b9', '1510658733', null);
INSERT INTO `sys_log` VALUES ('159', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510658820', null);
INSERT INTO `sys_log` VALUES ('160', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510727901', null);
INSERT INTO `sys_log` VALUES ('161', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510727940', null);
INSERT INTO `sys_log` VALUES ('162', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510731795', null);
INSERT INTO `sys_log` VALUES ('163', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733035', null);
INSERT INTO `sys_log` VALUES ('164', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733509', null);
INSERT INTO `sys_log` VALUES ('165', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733550', null);
INSERT INTO `sys_log` VALUES ('166', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733640', null);
INSERT INTO `sys_log` VALUES ('167', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733813', null);
INSERT INTO `sys_log` VALUES ('168', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510733893', null);
INSERT INTO `sys_log` VALUES ('169', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510734003', null);
INSERT INTO `sys_log` VALUES ('170', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510734059', null);
INSERT INTO `sys_log` VALUES ('171', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510735330', null);
INSERT INTO `sys_log` VALUES ('172', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510735330', null);
INSERT INTO `sys_log` VALUES ('173', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510735331', null);
INSERT INTO `sys_log` VALUES ('174', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510735378', null);
INSERT INTO `sys_log` VALUES ('175', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510735446', null);
INSERT INTO `sys_log` VALUES ('176', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:123', '405a28c9389d4a8581a29c283dc9f5b9', '1510735694', null);
INSERT INTO `sys_log` VALUES ('177', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '17null0null1', '405a28c9389d4a8581a29c283dc9f5b9', '1510735837', null);
INSERT INTO `sys_log` VALUES ('178', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '17151001', '405a28c9389d4a8581a29c283dc9f5b9', '1510736798', null);
INSERT INTO `sys_log` VALUES ('179', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '171110,17151001', '405a28c9389d4a8581a29c283dc9f5b9', '1510736870', null);
INSERT INTO `sys_log` VALUES ('180', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#addDo', '127.0.0.1', '171000', '405a28c9389d4a8581a29c283dc9f5b9', '1510737264', null);
INSERT INTO `sys_log` VALUES ('181', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510737545', null);
INSERT INTO `sys_log` VALUES ('182', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510739288', null);
INSERT INTO `sys_log` VALUES ('183', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510739291', null);
INSERT INTO `sys_log` VALUES ('184', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510740298', null);
INSERT INTO `sys_log` VALUES ('185', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510740300', null);
INSERT INTO `sys_log` VALUES ('186', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510741032', null);
INSERT INTO `sys_log` VALUES ('187', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741212', null);
INSERT INTO `sys_log` VALUES ('188', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741430', null);
INSERT INTO `sys_log` VALUES ('189', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741568', null);
INSERT INTO `sys_log` VALUES ('190', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741579', null);
INSERT INTO `sys_log` VALUES ('191', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741583', null);
INSERT INTO `sys_log` VALUES ('192', '超级管理员', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '405a28c9389d4a8581a29c283dc9f5b9', '1510741602', null);
INSERT INTO `sys_log` VALUES ('193', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510741815', null);
INSERT INTO `sys_log` VALUES ('194', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510741817', null);
INSERT INTO `sys_log` VALUES ('195', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510741870', null);
INSERT INTO `sys_log` VALUES ('196', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510742158', null);
INSERT INTO `sys_log` VALUES ('197', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510742495', null);
INSERT INTO `sys_log` VALUES ('198', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510742495', null);
INSERT INTO `sys_log` VALUES ('199', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510742942', null);
INSERT INTO `sys_log` VALUES ('200', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510743039', null);
INSERT INTO `sys_log` VALUES ('201', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510743042', null);
INSERT INTO `sys_log` VALUES ('202', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510743398', null);
INSERT INTO `sys_log` VALUES ('203', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:', '', '1510743505', null);
INSERT INTO `sys_log` VALUES ('204', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510743509', null);
INSERT INTO `sys_log` VALUES ('205', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510744113', null);
INSERT INTO `sys_log` VALUES ('206', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:213', '', '1510744362', null);
INSERT INTO `sys_log` VALUES ('207', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:1234', '', '1510744451', null);
INSERT INTO `sys_log` VALUES ('208', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510816755', null);
INSERT INTO `sys_log` VALUES ('209', '超级管理员', 'info', '鐢ㄦ埛鐧诲嚭', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '鎴愬姛閫�鍑虹郴缁燂紒', '405a28c9389d4a8581a29c283dc9f5b9', '1510816982', null);
INSERT INTO `sys_log` VALUES ('210', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510818469', null);
INSERT INTO `sys_log` VALUES ('211', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:1', '', '1510818885', null);
INSERT INTO `sys_log` VALUES ('212', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:1', '', '1510819704', null);
INSERT INTO `sys_log` VALUES ('213', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:fuckyou', '', '1510819715', null);
INSERT INTO `sys_log` VALUES ('214', '', 'aop.after', '鏂伴泧鍛樻敞鍐�', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '鐢ㄦ埛鍚�:fuckyou', '', '1510819728', null);
INSERT INTO `sys_log` VALUES ('215', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:liushangnan', '', '1510820206', null);
INSERT INTO `sys_log` VALUES ('216', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我日你奶子', '', '1510820305', null);
INSERT INTO `sys_log` VALUES ('217', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我日你奶子', '', '1510820386', null);
INSERT INTO `sys_log` VALUES ('218', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我日你奶子', '', '1510820952', null);
INSERT INTO `sys_log` VALUES ('219', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我日你奶子', '', '1510820955', null);
INSERT INTO `sys_log` VALUES ('220', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我日你奶子', '', '1510820963', null);
INSERT INTO `sys_log` VALUES ('221', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:舔我大鸡巴', '', '1510821072', null);
INSERT INTO `sys_log` VALUES ('222', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:123', '', '1510821240', null);
INSERT INTO `sys_log` VALUES ('223', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:12341234', '', '1510821938', null);
INSERT INTO `sys_log` VALUES ('224', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:sadfasdf', '', '1510822167', null);
INSERT INTO `sys_log` VALUES ('225', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510822233', null);
INSERT INTO `sys_log` VALUES ('226', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:', '', '1510822530', null);
INSERT INTO `sys_log` VALUES ('227', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:', '', '1510822564', null);
INSERT INTO `sys_log` VALUES ('228', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我爱大咪咪', '', '1510822610', null);
INSERT INTO `sys_log` VALUES ('229', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:我爱大咪咪', '', '1510822625', null);
INSERT INTO `sys_log` VALUES ('230', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510825537', null);
INSERT INTO `sys_log` VALUES ('231', '超级管理员', 'aop.after', 'gy_auth', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#delete', '127.0.0.1', 'd841742d59134b9b8388d4699e6114f8', '405a28c9389d4a8581a29c283dc9f5b9', '1510825570', null);
INSERT INTO `sys_log` VALUES ('232', '超级管理员', 'aop.after', 'gy_auth', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#delete', '127.0.0.1', 'a10bac1f01fa4ff39321768529eb69fa', '405a28c9389d4a8581a29c283dc9f5b9', '1510825572', null);
INSERT INTO `sys_log` VALUES ('233', '超级管理员', 'aop.after', '添加文章', 'cn.wizzer.app.web.modules.controllers.platform.cms.CmsArticleController#addDo', '127.0.0.1', '文章标题:啊手动阀', '405a28c9389d4a8581a29c283dc9f5b9', '1510826375', null);
INSERT INTO `sys_log` VALUES ('234', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510826754', null);
INSERT INTO `sys_log` VALUES ('235', '超级管理员', 'info', '鐢ㄦ埛鐧婚檰', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '鎴愬姛鐧诲綍绯荤粺锛�', '405a28c9389d4a8581a29c283dc9f5b9', '1510887426', null);
INSERT INTO `sys_log` VALUES ('236', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510887569', null);
INSERT INTO `sys_log` VALUES ('237', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510888339', null);
INSERT INTO `sys_log` VALUES ('238', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510888341', null);
INSERT INTO `sys_log` VALUES ('239', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510890405', null);
INSERT INTO `sys_log` VALUES ('240', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510896733', null);
INSERT INTO `sys_log` VALUES ('241', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510896899', null);
INSERT INTO `sys_log` VALUES ('242', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510896941', null);
INSERT INTO `sys_log` VALUES ('243', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510899552', null);
INSERT INTO `sys_log` VALUES ('244', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510901271', null);
INSERT INTO `sys_log` VALUES ('245', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510902030', null);
INSERT INTO `sys_log` VALUES ('246', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510902429', null);
INSERT INTO `sys_log` VALUES ('247', '超级管理员', 'aop.after', 'gy_auth', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#editDo', '127.0.0.1', '528fcfcb7a5a4635a2ca0bc6b0782af5', '405a28c9389d4a8581a29c283dc9f5b9', '1510903211', null);
INSERT INTO `sys_log` VALUES ('248', '超级管理员', 'aop.after', '重置密码', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#resetPwd', '127.0.0.1', '用户名:舔我大鸡巴', '405a28c9389d4a8581a29c283dc9f5b9', '1510905266', null);
INSERT INTO `sys_log` VALUES ('249', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510905957', null);
INSERT INTO `sys_log` VALUES ('250', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1510906101', null);
INSERT INTO `sys_log` VALUES ('251', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '17002', '405a28c9389d4a8581a29c283dc9f5b9', '1510906460', null);
INSERT INTO `sys_log` VALUES ('252', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '1712001', '405a28c9389d4a8581a29c283dc9f5b9', '1510906558', null);
INSERT INTO `sys_log` VALUES ('253', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#delete', '127.0.0.1', '171000', '405a28c9389d4a8581a29c283dc9f5b9', '1510906562', null);
INSERT INTO `sys_log` VALUES ('254', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510906575', null);
INSERT INTO `sys_log` VALUES ('255', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510906603', null);
INSERT INTO `sys_log` VALUES ('256', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510907134', null);
INSERT INTO `sys_log` VALUES ('257', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510907142', null);
INSERT INTO `sys_log` VALUES ('258', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510907271', null);
INSERT INTO `sys_log` VALUES ('259', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510908453', null);
INSERT INTO `sys_log` VALUES ('260', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510908467', null);
INSERT INTO `sys_log` VALUES ('261', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510909203', null);
INSERT INTO `sys_log` VALUES ('262', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510909206', null);
INSERT INTO `sys_log` VALUES ('263', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510909860', null);
INSERT INTO `sys_log` VALUES ('264', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510909863', null);
INSERT INTO `sys_log` VALUES ('265', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510909882', null);
INSERT INTO `sys_log` VALUES ('266', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510909892', null);
INSERT INTO `sys_log` VALUES ('267', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510910565', null);
INSERT INTO `sys_log` VALUES ('268', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510910573', null);
INSERT INTO `sys_log` VALUES ('269', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510910603', null);
INSERT INTO `sys_log` VALUES ('270', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510911527', null);
INSERT INTO `sys_log` VALUES ('271', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510911535', null);
INSERT INTO `sys_log` VALUES ('272', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1510912782', null);
INSERT INTO `sys_log` VALUES ('273', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510912789', null);
INSERT INTO `sys_log` VALUES ('274', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510912854', null);
INSERT INTO `sys_log` VALUES ('275', '', 'aop.after', '新雇员注册', 'cn.wizzer.app.web.modules.controllers.front.gy.GyHomeController#addDo', '127.0.0.1', '用户名:liushangnan', '', '1510913059', null);
INSERT INTO `sys_log` VALUES ('276', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1510913065', null);
INSERT INTO `sys_log` VALUES ('277', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511063014', null);
INSERT INTO `sys_log` VALUES ('278', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511079298', null);
INSERT INTO `sys_log` VALUES ('279', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511079497', null);
INSERT INTO `sys_log` VALUES ('280', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511079594', null);
INSERT INTO `sys_log` VALUES ('281', '1', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1511079599', null);
INSERT INTO `sys_log` VALUES ('282', '1', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', 'f994654ff9b449e8b16324e0d566e7fc', '1511079717', null);
INSERT INTO `sys_log` VALUES ('283', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511079723', null);
INSERT INTO `sys_log` VALUES ('284', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:lover', '405a28c9389d4a8581a29c283dc9f5b9', '1511079749', null);
INSERT INTO `sys_log` VALUES ('285', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:尼玛', '405a28c9389d4a8581a29c283dc9f5b9', '1511079769', null);
INSERT INTO `sys_log` VALUES ('286', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:sb', '405a28c9389d4a8581a29c283dc9f5b9', '1511079774', null);
INSERT INTO `sys_log` VALUES ('287', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:12341234', '405a28c9389d4a8581a29c283dc9f5b9', '1511079792', null);
INSERT INTO `sys_log` VALUES ('288', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511079800', null);
INSERT INTO `sys_log` VALUES ('289', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511079823', null);
INSERT INTO `sys_log` VALUES ('290', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511079829', null);
INSERT INTO `sys_log` VALUES ('291', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511081666', null);
INSERT INTO `sys_log` VALUES ('292', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511081674', null);
INSERT INTO `sys_log` VALUES ('293', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511081858', null);
INSERT INTO `sys_log` VALUES ('294', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511081868', null);
INSERT INTO `sys_log` VALUES ('295', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511082534', null);
INSERT INTO `sys_log` VALUES ('296', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511082540', null);
INSERT INTO `sys_log` VALUES ('297', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511082551', null);
INSERT INTO `sys_log` VALUES ('298', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511082953', null);
INSERT INTO `sys_log` VALUES ('299', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511082962', null);
INSERT INTO `sys_log` VALUES ('300', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511082973', null);
INSERT INTO `sys_log` VALUES ('301', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '528fcfcb7a5a4635a2ca0bc6b0782af5', '28e8a294dd73436583f6d5ab0c8bceaf', '1511083364', null);
INSERT INTO `sys_log` VALUES ('302', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511083705', null);
INSERT INTO `sys_log` VALUES ('303', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511083837', null);
INSERT INTO `sys_log` VALUES ('304', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511083859', null);
INSERT INTO `sys_log` VALUES ('305', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511083868', null);
INSERT INTO `sys_log` VALUES ('306', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511084203', null);
INSERT INTO `sys_log` VALUES ('307', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511084637', null);
INSERT INTO `sys_log` VALUES ('308', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511084818', null);
INSERT INTO `sys_log` VALUES ('309', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511088228', null);
INSERT INTO `sys_log` VALUES ('310', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088236', null);
INSERT INTO `sys_log` VALUES ('311', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088327', null);
INSERT INTO `sys_log` VALUES ('312', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088341', null);
INSERT INTO `sys_log` VALUES ('313', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088532', null);
INSERT INTO `sys_log` VALUES ('314', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088726', null);
INSERT INTO `sys_log` VALUES ('315', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511088808', null);
INSERT INTO `sys_log` VALUES ('316', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511089082', null);
INSERT INTO `sys_log` VALUES ('317', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511089088', null);
INSERT INTO `sys_log` VALUES ('318', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511090358', null);
INSERT INTO `sys_log` VALUES ('319', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511090479', null);
INSERT INTO `sys_log` VALUES ('320', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511090635', null);
INSERT INTO `sys_log` VALUES ('321', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511090711', null);
INSERT INTO `sys_log` VALUES ('322', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511090775', null);
INSERT INTO `sys_log` VALUES ('323', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092180', null);
INSERT INTO `sys_log` VALUES ('324', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092210', null);
INSERT INTO `sys_log` VALUES ('325', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092231', null);
INSERT INTO `sys_log` VALUES ('326', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092281', null);
INSERT INTO `sys_log` VALUES ('327', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092284', null);
INSERT INTO `sys_log` VALUES ('328', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092294', null);
INSERT INTO `sys_log` VALUES ('329', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092313', null);
INSERT INTO `sys_log` VALUES ('330', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092330', null);
INSERT INTO `sys_log` VALUES ('331', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092354', null);
INSERT INTO `sys_log` VALUES ('332', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092359', null);
INSERT INTO `sys_log` VALUES ('333', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092374', null);
INSERT INTO `sys_log` VALUES ('334', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092414', null);
INSERT INTO `sys_log` VALUES ('335', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092425', null);
INSERT INTO `sys_log` VALUES ('336', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092444', null);
INSERT INTO `sys_log` VALUES ('337', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092519', null);
INSERT INTO `sys_log` VALUES ('338', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511092999', null);
INSERT INTO `sys_log` VALUES ('339', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511093061', null);
INSERT INTO `sys_log` VALUES ('340', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093085', null);
INSERT INTO `sys_log` VALUES ('341', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093116', null);
INSERT INTO `sys_log` VALUES ('342', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093235', null);
INSERT INTO `sys_log` VALUES ('343', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093339', null);
INSERT INTO `sys_log` VALUES ('344', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093439', null);
INSERT INTO `sys_log` VALUES ('345', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093486', null);
INSERT INTO `sys_log` VALUES ('346', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093588', null);
INSERT INTO `sys_log` VALUES ('347', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093600', null);
INSERT INTO `sys_log` VALUES ('348', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093665', null);
INSERT INTO `sys_log` VALUES ('349', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093670', null);
INSERT INTO `sys_log` VALUES ('350', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093698', null);
INSERT INTO `sys_log` VALUES ('351', '超级管理员', 'aop.after', '身份认证不通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#disable', '127.0.0.1', '用户名:', '405a28c9389d4a8581a29c283dc9f5b9', '1511093704', null);
INSERT INTO `sys_log` VALUES ('352', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511094599', null);
INSERT INTO `sys_log` VALUES ('353', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511094616', null);
INSERT INTO `sys_log` VALUES ('354', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511094724', null);
INSERT INTO `sys_log` VALUES ('355', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511094961', null);
INSERT INTO `sys_log` VALUES ('356', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511094973', null);
INSERT INTO `sys_log` VALUES ('357', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511094983', null);
INSERT INTO `sys_log` VALUES ('358', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511095119', null);
INSERT INTO `sys_log` VALUES ('359', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511095193', null);
INSERT INTO `sys_log` VALUES ('360', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511095202', null);
INSERT INTO `sys_log` VALUES ('361', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511095270', null);
INSERT INTO `sys_log` VALUES ('362', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511095428', null);
INSERT INTO `sys_log` VALUES ('363', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511139198', null);
INSERT INTO `sys_log` VALUES ('364', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511139245', null);
INSERT INTO `sys_log` VALUES ('365', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511139325', null);
INSERT INTO `sys_log` VALUES ('366', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511139386', null);
INSERT INTO `sys_log` VALUES ('367', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511139600', null);
INSERT INTO `sys_log` VALUES ('368', '超级管理员', 'aop.after', '修改用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#editDo', '127.0.0.1', '用户名:superadmin->superadmin', '405a28c9389d4a8581a29c283dc9f5b9', '1511140305', null);
INSERT INTO `sys_log` VALUES ('369', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511140438', null);
INSERT INTO `sys_log` VALUES ('370', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511140481', null);
INSERT INTO `sys_log` VALUES ('371', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511140675', null);
INSERT INTO `sys_log` VALUES ('372', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511140838', null);
INSERT INTO `sys_log` VALUES ('373', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511140877', null);
INSERT INTO `sys_log` VALUES ('374', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511142017', null);
INSERT INTO `sys_log` VALUES ('375', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '', '405a28c9389d4a8581a29c283dc9f5b9', '1511142031', null);
INSERT INTO `sys_log` VALUES ('376', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511142078', null);
INSERT INTO `sys_log` VALUES ('377', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511154762', null);
INSERT INTO `sys_log` VALUES ('378', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511154883', null);
INSERT INTO `sys_log` VALUES ('379', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155225', null);
INSERT INTO `sys_log` VALUES ('380', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155352', null);
INSERT INTO `sys_log` VALUES ('381', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155383', null);
INSERT INTO `sys_log` VALUES ('382', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155426', null);
INSERT INTO `sys_log` VALUES ('383', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155579', null);
INSERT INTO `sys_log` VALUES ('384', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155764', null);
INSERT INTO `sys_log` VALUES ('385', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155991', null);
INSERT INTO `sys_log` VALUES ('386', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511155996', null);
INSERT INTO `sys_log` VALUES ('387', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156009', null);
INSERT INTO `sys_log` VALUES ('388', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156047', null);
INSERT INTO `sys_log` VALUES ('389', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156074', null);
INSERT INTO `sys_log` VALUES ('390', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156119', null);
INSERT INTO `sys_log` VALUES ('391', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156367', null);
INSERT INTO `sys_log` VALUES ('392', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156381', null);
INSERT INTO `sys_log` VALUES ('393', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156417', null);
INSERT INTO `sys_log` VALUES ('394', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511156477', null);
INSERT INTO `sys_log` VALUES ('395', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156503', null);
INSERT INTO `sys_log` VALUES ('396', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156529', null);
INSERT INTO `sys_log` VALUES ('397', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156620', null);
INSERT INTO `sys_log` VALUES ('398', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156683', null);
INSERT INTO `sys_log` VALUES ('399', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:123', '405a28c9389d4a8581a29c283dc9f5b9', '1511156865', null);
INSERT INTO `sys_log` VALUES ('400', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156976', null);
INSERT INTO `sys_log` VALUES ('401', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511156998', null);
INSERT INTO `sys_log` VALUES ('402', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511157053', null);
INSERT INTO `sys_log` VALUES ('403', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171003', '405a28c9389d4a8581a29c283dc9f5b9', '1511157076', null);
INSERT INTO `sys_log` VALUES ('404', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511157087', null);
INSERT INTO `sys_log` VALUES ('405', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157095', null);
INSERT INTO `sys_log` VALUES ('406', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157106', null);
INSERT INTO `sys_log` VALUES ('407', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511157454', null);
INSERT INTO `sys_log` VALUES ('408', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511157468', null);
INSERT INTO `sys_log` VALUES ('409', '123', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157478', null);
INSERT INTO `sys_log` VALUES ('410', '123', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyPersonController#editDo', '127.0.0.1', '171003', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157492', null);
INSERT INTO `sys_log` VALUES ('411', '123', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157518', null);
INSERT INTO `sys_log` VALUES ('412', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511158926', null);
INSERT INTO `sys_log` VALUES ('413', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171001', '405a28c9389d4a8581a29c283dc9f5b9', '1511159042', null);
INSERT INTO `sys_log` VALUES ('414', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171001', '405a28c9389d4a8581a29c283dc9f5b9', '1511159098', null);
INSERT INTO `sys_log` VALUES ('415', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171001', '405a28c9389d4a8581a29c283dc9f5b9', '1511159110', null);
INSERT INTO `sys_log` VALUES ('416', '超级管理员', 'aop.after', 'gy_inf', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyInfController#editDo', '127.0.0.1', '171001', '405a28c9389d4a8581a29c283dc9f5b9', '1511159123', null);
INSERT INTO `sys_log` VALUES ('417', '超级管理员', 'aop.after', '认证通过', 'cn.wizzer.app.web.modules.controllers.platform.gy.GyAuthController#enable', '127.0.0.1', '雇员编号:', '405a28c9389d4a8581a29c283dc9f5b9', '1511159541', null);
INSERT INTO `sys_log` VALUES ('418', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:平台基础数据', '405a28c9389d4a8581a29c283dc9f5b9', '1511159596', null);
INSERT INTO `sys_log` VALUES ('419', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:技能库管理', '405a28c9389d4a8581a29c283dc9f5b9', '1511159617', null);
INSERT INTO `sys_log` VALUES ('420', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:任务种类', '405a28c9389d4a8581a29c283dc9f5b9', '1511159637', null);
INSERT INTO `sys_log` VALUES ('421', '超级管理员', 'aop.after', '删除用户', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUserController#delete', '127.0.0.1', '用户名:舔我大鸡巴', '405a28c9389d4a8581a29c283dc9f5b9', '1511162116', null);
INSERT INTO `sys_log` VALUES ('422', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164098', null);
INSERT INTO `sys_log` VALUES ('423', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:技能添加', '405a28c9389d4a8581a29c283dc9f5b9', '1511164152', null);
INSERT INTO `sys_log` VALUES ('424', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:技能删除', '405a28c9389d4a8581a29c283dc9f5b9', '1511164176', null);
INSERT INTO `sys_log` VALUES ('425', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:技能删除', '405a28c9389d4a8581a29c283dc9f5b9', '1511164194', null);
INSERT INTO `sys_log` VALUES ('426', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1511164297', null);
INSERT INTO `sys_log` VALUES ('427', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1511164299', null);
INSERT INTO `sys_log` VALUES ('428', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164302', null);
INSERT INTO `sys_log` VALUES ('429', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164304', null);
INSERT INTO `sys_log` VALUES ('430', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164401', null);
INSERT INTO `sys_log` VALUES ('431', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:技能库管理', '405a28c9389d4a8581a29c283dc9f5b9', '1511164426', null);
INSERT INTO `sys_log` VALUES ('432', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164428', null);
INSERT INTO `sys_log` VALUES ('433', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511164430', null);
INSERT INTO `sys_log` VALUES ('434', '超级管理员', 'aop.after', '新建单位', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUnitController#addDo', '127.0.0.1', '单位名称:photoshop', '405a28c9389d4a8581a29c283dc9f5b9', '1511165111', null);
INSERT INTO `sys_log` VALUES ('435', '超级管理员', 'aop.after', '删除单位', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUnitController#delete', '127.0.0.1', '单位名称:photoshop', '405a28c9389d4a8581a29c283dc9f5b9', '1511165236', null);
INSERT INTO `sys_log` VALUES ('436', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:photoshop', '405a28c9389d4a8581a29c283dc9f5b9', '1511165293', null);
INSERT INTO `sys_log` VALUES ('437', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:sketch', '405a28c9389d4a8581a29c283dc9f5b9', '1511165345', null);
INSERT INTO `sys_log` VALUES ('438', '超级管理员', 'aop.after', '新建单位', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUnitController#addDo', '127.0.0.1', '单位名称:育才宾馆', '405a28c9389d4a8581a29c283dc9f5b9', '1511165400', null);
INSERT INTO `sys_log` VALUES ('439', '超级管理员', 'aop.after', '删除单位', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysUnitController#delete', '127.0.0.1', '单位名称:育才宾馆', '405a28c9389d4a8581a29c283dc9f5b9', '1511165408', null);
INSERT INTO `sys_log` VALUES ('440', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511165718', null);
INSERT INTO `sys_log` VALUES ('441', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:fk', '405a28c9389d4a8581a29c283dc9f5b9', '1511165755', null);
INSERT INTO `sys_log` VALUES ('442', '超级管理员', 'aop.after', '删除技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#delete', '127.0.0.1', '技能名称:fk', '405a28c9389d4a8581a29c283dc9f5b9', '1511166103', null);
INSERT INTO `sys_log` VALUES ('443', '超级管理员', 'aop.after', '编辑技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#editDo', '127.0.0.1', '技能名称:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511166332', null);
INSERT INTO `sys_log` VALUES ('444', '超级管理员', 'aop.after', '编辑技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#editDo', '127.0.0.1', '技能名称:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511166366', null);
INSERT INTO `sys_log` VALUES ('445', '超级管理员', 'aop.after', '编辑技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#editDo', '127.0.0.1', '技能名称:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511166402', null);
INSERT INTO `sys_log` VALUES ('446', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511166533', null);
INSERT INTO `sys_log` VALUES ('447', '超级管理员', 'aop.after', '编辑技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#editDo', '127.0.0.1', '技能名称:测试', '405a28c9389d4a8581a29c283dc9f5b9', '1511166549', null);
INSERT INTO `sys_log` VALUES ('448', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167027', null);
INSERT INTO `sys_log` VALUES ('449', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:任务种类添加', '405a28c9389d4a8581a29c283dc9f5b9', '1511167119', null);
INSERT INTO `sys_log` VALUES ('450', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:任务种类编辑', '405a28c9389d4a8581a29c283dc9f5b9', '1511167135', null);
INSERT INTO `sys_log` VALUES ('451', '超级管理员', 'aop.after', '新建菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#addDo', '127.0.0.1', '菜单名称:任务种类删除', '405a28c9389d4a8581a29c283dc9f5b9', '1511167154', null);
INSERT INTO `sys_log` VALUES ('452', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysRoleController#editMenuDo', '127.0.0.1', '角色名称:系统管理员', '405a28c9389d4a8581a29c283dc9f5b9', '1511167183', null);
INSERT INTO `sys_log` VALUES ('453', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167189', null);
INSERT INTO `sys_log` VALUES ('454', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167191', null);
INSERT INTO `sys_log` VALUES ('455', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:任务种类删除', '405a28c9389d4a8581a29c283dc9f5b9', '1511167216', null);
INSERT INTO `sys_log` VALUES ('456', '超级管理员', 'aop.after', '编辑菜单', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysMenuController#editDo', '127.0.0.1', '菜单名称:任务种类', '405a28c9389d4a8581a29c283dc9f5b9', '1511167237', null);
INSERT INTO `sys_log` VALUES ('457', '超级管理员', 'info', '用户登出', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#logout', '127.0.0.1', '成功退出系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167239', null);
INSERT INTO `sys_log` VALUES ('458', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167241', null);
INSERT INTO `sys_log` VALUES ('459', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511167376', null);
INSERT INTO `sys_log` VALUES ('460', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:设计', '405a28c9389d4a8581a29c283dc9f5b9', '1511167415', null);
INSERT INTO `sys_log` VALUES ('461', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:海报', '405a28c9389d4a8581a29c283dc9f5b9', '1511167444', null);
INSERT INTO `sys_log` VALUES ('462', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:吃土', '405a28c9389d4a8581a29c283dc9f5b9', '1511167471', null);
INSERT INTO `sys_log` VALUES ('463', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:吃土', '405a28c9389d4a8581a29c283dc9f5b9', '1511167478', null);
INSERT INTO `sys_log` VALUES ('464', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:吃土', '405a28c9389d4a8581a29c283dc9f5b9', '1511167503', null);
INSERT INTO `sys_log` VALUES ('465', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:卧槽', '405a28c9389d4a8581a29c283dc9f5b9', '1511167546', null);
INSERT INTO `sys_log` VALUES ('466', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:卧槽', '405a28c9389d4a8581a29c283dc9f5b9', '1511167558', null);
INSERT INTO `sys_log` VALUES ('467', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:卧槽', '405a28c9389d4a8581a29c283dc9f5b9', '1511167593', null);
INSERT INTO `sys_log` VALUES ('468', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:asdf', '405a28c9389d4a8581a29c283dc9f5b9', '1511167688', null);
INSERT INTO `sys_log` VALUES ('469', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:asdf', '405a28c9389d4a8581a29c283dc9f5b9', '1511167764', null);
INSERT INTO `sys_log` VALUES ('470', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:asdf', '405a28c9389d4a8581a29c283dc9f5b9', '1511167777', null);
INSERT INTO `sys_log` VALUES ('471', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#addDo', '127.0.0.1', '技能 名称:asdf', '405a28c9389d4a8581a29c283dc9f5b9', '1511167812', null);
INSERT INTO `sys_log` VALUES ('472', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511170663', null);
INSERT INTO `sys_log` VALUES ('473', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#editSkillDo', '127.0.0.1', '角色名称:', '405a28c9389d4a8581a29c283dc9f5b9', '1511170998', null);
INSERT INTO `sys_log` VALUES ('474', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#editSkillDo', '127.0.0.1', '角色名称:', '405a28c9389d4a8581a29c283dc9f5b9', '1511171145', null);
INSERT INTO `sys_log` VALUES ('475', '超级管理员', 'info', '用户登陆', 'cn.wizzer.app.web.modules.controllers.platform.sys.SysLoginController#doLogin', '127.0.0.1', '成功登录系统！', '405a28c9389d4a8581a29c283dc9f5b9', '1511171205', null);
INSERT INTO `sys_log` VALUES ('476', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#editSkillDo', '127.0.0.1', '角色名称:设计', '405a28c9389d4a8581a29c283dc9f5b9', '1511171227', null);
INSERT INTO `sys_log` VALUES ('477', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:ps', '405a28c9389d4a8581a29c283dc9f5b9', '1511171351', null);
INSERT INTO `sys_log` VALUES ('478', '超级管理员', 'aop.after', '新建技能', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibSkillController#addDo', '127.0.0.1', '技能 名称:数学', '405a28c9389d4a8581a29c283dc9f5b9', '1511171371', null);
INSERT INTO `sys_log` VALUES ('479', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#editSkillDo', '127.0.0.1', '角色名称:设计', '405a28c9389d4a8581a29c283dc9f5b9', '1511171383', null);
INSERT INTO `sys_log` VALUES ('480', '超级管理员', 'aop.after', '修改角色菜单', 'cn.wizzer.app.web.modules.controllers.platform.lib.LibTaskController#editSkillDo', '127.0.0.1', '角色名称:设计', '405a28c9389d4a8581a29c283dc9f5b9', '1511171424', null);

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_MENU_PATH` (`path`),
  UNIQUE KEY `INDEX_SYS_MENU_PREM` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('02e86a61e99746bea34236ea73dd52a5', '', '0003', 'CMS', 'CMS', 'menu', '', '', '', '1', '0', 'cms', null, '19', '1', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468895671', '0');
INSERT INTO `sys_menu` VALUES ('0706112ff5dc46e388064a99bcdb0561', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030004', '关键词回复', 'Keyword', 'menu', '/platform/wx/reply/conf/keyword', 'data-pjax', '', '1', '0', 'wx.reply.keyword', null, '10', '0', '', '1467472362', '0');
INSERT INTO `sys_menu` VALUES ('0772a3f1f877424c80a34f8bc9da0268', 'b225e7eafec846f29023cca78f63bc18', '0001000100040002', '修改菜单', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.menu.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('077cb6be4c7c41cc8955ee045a4f0286', '68cdbf694f71445c8587a20234d6fe31', '0003000300020001', '添加链接', 'Add', 'data', '', '', '', '0', '0', 'cms.link.link.add', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468897043', '0');
INSERT INTO `sys_menu` VALUES ('09147fe6c278458cb77c4162fff447e3', '1c8a24e0cf094e8e99346aa8b0e4391b', '00080001', '技能库管理', null, 'menu', '/platform/lib/skill', '', '', '1', '0', 'lib.skill', null, '55', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511164426', '0');
INSERT INTO `sys_menu` VALUES ('0a43d291e0c94ad88c8b690009279e34', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020004', '保存排序', 'Save', 'data', '', '', '', '0', '0', 'wx.conf.menu.sort', null, '0', '0', '', '1467474314', '0');
INSERT INTO `sys_menu` VALUES ('0a6e43782e8342f0aeb2703912df88a9', 'e7287128c8a5470081ed782a282d30c6', '0001000100020002', '修改用户', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.user.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('0a972ce655cb4c84809d58668b655900', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020002', '修改图文', 'Edit', 'data', '', '', '', '0', '0', 'wx.reply.news.edit', null, '0', '0', '', '1467473596', '0');
INSERT INTO `sys_menu` VALUES ('0b097e9dbad649048f956355d1e91278', '5adfb7b54d784242a7c524734a10d4bd', '000100010009', '应用管理', 'App', 'menu', '/platform/sys/api', 'data-pjax', null, '1', '0', 'sys.manager.api', null, '38', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('0cc1b6bedbc648a2bb8cab0ca2bbf02c', '60d42fa10a804c23a502aa06d786182a', '000800020003', '任务种类删除', null, 'data', '', '', '', '1', '0', 'lib.task.delete', null, '62', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511167216', '0');
INSERT INTO `sys_menu` VALUES ('0e73d7b9cf8443c1a48ad197bc9a65ec', '298f7a5f36574102a87268d7ffbe77d6', '000400010003', '雇员信息删除', null, 'data', '', '', '', '0', '0', 'platform.gy.inf.delete', null, '47', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510537795', '0');
INSERT INTO `sys_menu` VALUES ('0ec22065b0134f0091d5ce9f92e83183', '60d42fa10a804c23a502aa06d786182a', '000800020002', '任务种类编辑', null, 'data', '', '', '', '0', '0', 'lib.task.edit', null, '61', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511167135', '0');
INSERT INTO `sys_menu` VALUES ('10c8ae20fa6942c5b54a95df03270273', '5adfb7b54d784242a7c524734a10d4bd', '000100010007', '定时任务', 'Task', 'menu', '/platform/sys/task', 'data-pjax', null, '1', '0', 'sys.manager.task', null, '36', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('1385ae887e5c4b8aa33fbf228be7f907', '6afc5075913d4df4b44a6476080e35a0', '000200050001', '模板编号', 'Id', 'menu', '/platform/wx/tpl/id', 'data-pjax', '', '1', '0', 'wx.tpl.id', null, '15', '0', '', '1470406854', '0');
INSERT INTO `sys_menu` VALUES ('15c6c2d81a954601833726705fef48de', 'b225e7eafec846f29023cca78f63bc18', '0001000100040003', '删除菜单', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.menu.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('1734e586e96941268a4c5248b593cef9', 'f426468abf714b1599729f8c36ebbb0d', '0002000200010001', '回复消息', 'Reply', 'data', '', '', '', '0', '0', 'wx.msg.user.reply', null, '0', '0', '', '1467473127', '0');
INSERT INTO `sys_menu` VALUES ('17500ef3a9e44b4fabb240162a164fcb', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020003', '删除文章', 'Delete', 'data', '', '', '', '0', '0', 'cms.content.article.delete', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896170', '0');
INSERT INTO `sys_menu` VALUES ('17e1ee23ca1443f1bc886c2f5eb7c24b', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030002', '图文内容', 'News', 'menu', '/platform/wx/reply/news', 'data-pjax', '', '1', '0', 'wx.reply.news', null, '8', '0', '', '1467471926', '0');
INSERT INTO `sys_menu` VALUES ('1c8a24e0cf094e8e99346aa8b0e4391b', '', '0008', '平台基础数据', null, 'menu', '', '', '', '1', '0', 'lib', null, '54', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1511159596', '0');
INSERT INTO `sys_menu` VALUES ('1f6f98ceb052434cb5ea86ad84ddc971', '5adfb7b54d784242a7c524734a10d4bd', '000100010006', '日志管理', 'Log', 'menu', '/platform/sys/log', 'data-pjax', null, '1', '0', 'sys.manager.log', null, '35', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('1faa04cc182a4849b94a99286d972b0d', '', '0004', '雇员管理', null, 'menu', '', '', '', '1', '0', 'gy', null, '41', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1510464772', '0');
INSERT INTO `sys_menu` VALUES ('2275cb125710414e91b617dd7c62f12c', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020001', '添加图文', 'add', 'data', '', '', '', '0', '0', 'wx.reply.news.add', null, '0', '0', '', '1467473585', '0');
INSERT INTO `sys_menu` VALUES ('234f8ec3c2bc42bf9f6202aecae36fd6', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030001', '文本内容', 'Txt', 'menu', '/platform/wx/reply/txt', 'data-pjax', '', '1', '0', 'wx.reply.txt', null, '7', '0', '', '1467471884', '0');
INSERT INTO `sys_menu` VALUES ('24a47951159749459729dd1146229ea7', 'e7287128c8a5470081ed782a282d30c6', '0001000100020003', '删除用户', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.user.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('298f7a5f36574102a87268d7ffbe77d6', '1faa04cc182a4849b94a99286d972b0d', '00040001', '雇员信息列表', null, 'menu', '/platform/gy/inf', '', '', '1', '0', 'platform.gy.inf', null, '44', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510538954', '0');
INSERT INTO `sys_menu` VALUES ('2a63040409094f1e9dc535dd78ce15b7', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030003', '删除绑定', 'Delete', 'data', '', '', '', '0', '0', 'wx.reply.follow.delete', null, '0', '0', '', '1467474080', '0');
INSERT INTO `sys_menu` VALUES ('2cb327ad59b140828fd26eb2a46cb948', '4cd8e4e9519e4cff95465194fdcc8d88', '000200030003', '关注自动回复', 'Follow', 'menu', '/platform/wx/reply/conf/follow', 'data-pjax', '', '1', '0', 'wx.reply.follow', null, '9', '0', '', '1467472280', '0');
INSERT INTO `sys_menu` VALUES ('2dc85d8f3bb041918b35e0883db79a4c', '1f6f98ceb052434cb5ea86ad84ddc971', '0001000100060001', '清除日志', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.log.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('2fab774f8b6d40cb9d7e187babab2d91', 'bcf64d623fdd4519ae345b7a08c071a1', '000200040002', '菜单配置', 'Menu', 'menu', '/platform/wx/conf/menu', 'data-pjax', '', '1', '0', 'wx.conf.menu', null, '13', '0', '', '1467472649', '0');
INSERT INTO `sys_menu` VALUES ('3050a2d827814a84a45b392166f6bfce', '31aae024cd5d47999aa9e73becc14cb5', '000400020001', '雇员身份审核增加', null, 'data', '', '', '', '0', '0', 'platform.gy.auth.add', null, '51', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510569758', '0');
INSERT INTO `sys_menu` VALUES ('3099f497480c4b1987bce3f3a26c3fb4', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020003', '群发消息', 'Push', 'data', '', '', '', '0', '0', 'wx.msg.mass.pushNews', null, '0', '0', '', '1467473400', '0');
INSERT INTO `sys_menu` VALUES ('309dc29ad3c34408a68df8f867a5c9ff', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010001', '添加帐号', 'Add', 'data', '', '', '', '0', '0', 'wx.conf.account.add', null, '0', '0', '', '1467474187', '0');
INSERT INTO `sys_menu` VALUES ('30a5e70a1456447ebf90b5546e9bc321', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030002', '修改绑定', 'Edit', 'data', '', '', '', '0', '0', 'wx.reply.follow.edit', null, '0', '0', '', '1467474056', '0');
INSERT INTO `sys_menu` VALUES ('31aae024cd5d47999aa9e73becc14cb5', '1faa04cc182a4849b94a99286d972b0d', '00040002', '雇员身份审核', null, 'menu', '/platform/gy/auth', '', '', '1', '0', 'platform.gy.auth', null, '50', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1510569719', '0');
INSERT INTO `sys_menu` VALUES ('31ed2243077c44448cce26abfd5ae574', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010002', '修改分类', 'Edit', 'data', '', '', '', '0', '0', 'cms.link.class.edit', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896957', '0');
INSERT INTO `sys_menu` VALUES ('329094c727f949ca8a22728f2ec560ff', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030003', '删除角色', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.role.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('33aed9298643424783116e0cf0f7fcbe', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020001', '添加文章', 'Add', 'data', '', '', '', '0', '0', 'cms.content.article.add', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896151', '0');
INSERT INTO `sys_menu` VALUES ('36ce16235ac14cebbdfd7a4ddcd02a7b', '', '0001', '系统', 'System', 'menu', '', '', '', '1', '0', 'sys', '系统', '28', '1', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('36e0faf5062b4f6b95d4167cbb1f8fea', '68cdbf694f71445c8587a20234d6fe31', '0003000300020002', '修改链接', 'Edit', 'data', '', '', '', '0', '0', 'cms.link.link.edit', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468897051', '0');
INSERT INTO `sys_menu` VALUES ('3888f05aa4064f788ba7ec51c495ce7c', '1385ae887e5c4b8aa33fbf228be7f907', '0002000500010002', '删除编号', 'Delete', 'data', '', '', '', '0', '0', 'wx.tpl.id.delete', null, '0', '0', '', '1470407068', '0');
INSERT INTO `sys_menu` VALUES ('3b2b0c0ae215448f9ab7e53503cec4d6', '437b5771ab0a42ec886a69e050c74a52', '0001000100050001', '添加参数', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.conf.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('3c24111091ad4a70ad2d9cc361311d2f', '68cdbf694f71445c8587a20234d6fe31', '0003000300020003', '删除链接', 'Delete', 'data', '', '', '', '0', '0', 'cms.link.link.delete', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468897060', '0');
INSERT INTO `sys_menu` VALUES ('3f330d729ca34dc9825c46122be1bfae', '02e86a61e99746bea34236ea73dd52a5', '00030003', '广告链接', 'AD', 'menu', '', '', 'ti-link', '1', '0', 'cms.link', null, '25', '1', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896230', '0');
INSERT INTO `sys_menu` VALUES ('437b5771ab0a42ec886a69e050c74a52', '5adfb7b54d784242a7c524734a10d4bd', '000100010005', '系统参数', 'Param', 'menu', '/platform/sys/conf', 'data-pjax', null, '1', '0', 'sys.manager.conf', null, '34', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('44da90bc76a5419a841f4924333f7a66', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020002', '修改菜单', 'Edit', 'data', '', '', '', '0', '0', 'wx.conf.menu.edit', null, '0', '0', '', '1467474294', '0');
INSERT INTO `sys_menu` VALUES ('45d958ca78304f25b51f6c71cf66f6d8', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020001', '添加菜单', 'Add', 'data', '', '', '', '0', '0', 'wx.conf.menu.add', null, '0', '0', '', '1467474283', '0');
INSERT INTO `sys_menu` VALUES ('46210709bff149d78d4d7914cb9daa72', '', '0005', '个人中心', null, 'menu', '', '', '', '1', '0', 'gy.person', null, '18', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1510542505', '0');
INSERT INTO `sys_menu` VALUES ('4781372b00bb4d52b429b58e72b80c68', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010001', '添加栏目', 'Add', 'data', '', '', '', '0', '0', 'cms.content.channel.add', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896049', '0');
INSERT INTO `sys_menu` VALUES ('4bb07c9f6685429d8a2aa5d9d2b4fed5', '46210709bff149d78d4d7914cb9daa72', '00050001', '信息修改', null, 'menu', '/platform/gy/person/inf', '', '', '1', '0', 'gy.person.inf', null, '48', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510570087', '0');
INSERT INTO `sys_menu` VALUES ('4bdbe5fa320d4873890b1764bfbd2f47', '5adfb7b54d784242a7c524734a10d4bd', '000100010001', '单位管理', 'Unit', 'menu', '/platform/sys/unit', 'data-pjax', null, '1', '0', 'sys.manager.unit', null, '30', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('4cd8e4e9519e4cff95465194fdcc8d88', 'b0edc6861a494b79b97990dc05f0a524', '00020003', '自动回复', 'AutoReply', 'menu', '', '', 'ti-back-left', '1', '0', 'wx.reply', null, '6', '1', '', '1467471610', '0');
INSERT INTO `sys_menu` VALUES ('4d0d72499bd84d9b827e3eccafade2e5', '6731cf86a00e4db283099ef7c7211448', '0001000100080003', '删除路由', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.route.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('4dc997fef71e4862b9db22de8e99a618', 'b19b23b0459a4754bf1fb8cb234450f2', '0002000100010001', '同步会员信息', 'Sync', 'data', '', '', '', '0', '0', 'wx.user.list.sync', null, '0', '0', '', '1467473044', '0');
INSERT INTO `sys_menu` VALUES ('507b184823614a52a3a96571a1507129', '437b5771ab0a42ec886a69e050c74a52', '0001000100050002', '修改参数', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.conf.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('50ba60ee650e4c739e6abc3ab71e4960', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010004', '栏目排序', 'Sort', 'data', '', '', '', '0', '0', 'cms.content.channel.sort', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896092', '0');
INSERT INTO `sys_menu` VALUES ('5244f5c38eb24b918e9ad64d456daa38', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020005', '推送到微信', 'Push', 'data', '', '', '', '0', '0', 'wx.conf.menu.push', null, '0', '0', '', '1467474330', '0');
INSERT INTO `sys_menu` VALUES ('52a16b54ab384d628d40bd5304b3c812', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100001', '添加字典', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.dict.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('56d0658c5a8848818ac05e8ffa5c0570', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020001', '添加图文', 'Add', 'data', '', '', '', '0', '0', 'wx.msg.mass.addNews', null, '0', '0', '', '1467473338', '0');
INSERT INTO `sys_menu` VALUES ('5adfb7b54d784242a7c524734a10d4bd', '36ce16235ac14cebbdfd7a4ddcd02a7b', '00010001', '系统管理', 'Manager', 'menu', '', '', 'ti-settings', '1', '0', 'sys.manager', '系统管理', '29', '1', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('5f255c41da5a4befb176e78e5ba2c89c', '', '0006', '平台新闻', null, 'menu', '', '', '', '1', '0', 'public.cms', null, '42', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510465152', '0');
INSERT INTO `sys_menu` VALUES ('5f2f54d9dab84be8bab26a13431f2b59', '31aae024cd5d47999aa9e73becc14cb5', '000400020003', '雇员身份审核编辑', null, 'data', '', '', '', '1', '0', 'platform.gy.auth.edit', null, '53', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510570260', '0');
INSERT INTO `sys_menu` VALUES ('6075fc0cf0ef441b9d93cc3cab3445bf', '6b6de8c720c645a1808e1c3e9ccbfc90', '000300020002', '文章管理', 'Article', 'menu', '/platform/cms/article', 'data-pjax', '', '1', '0', 'cms.content.article', null, '24', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896141', '0');
INSERT INTO `sys_menu` VALUES ('60d42fa10a804c23a502aa06d786182a', '1c8a24e0cf094e8e99346aa8b0e4391b', '00080002', '任务种类', null, 'menu', '/platform/lib/task', '', '', '1', '0', 'lib.task', null, '56', '1', '405a28c9389d4a8581a29c283dc9f5b9', '1511167237', '0');
INSERT INTO `sys_menu` VALUES ('66cc21d7ce104dd6877cbce114c59fb3', 'bcf64d623fdd4519ae345b7a08c071a1', '000200040001', '帐号配置', 'Account', 'menu', '/platform/wx/conf/account', 'data-pjax', '', '1', '0', 'wx.conf.account', null, '12', '0', '', '1467472624', '0');
INSERT INTO `sys_menu` VALUES ('6731cf86a00e4db283099ef7c7211448', '5adfb7b54d784242a7c524734a10d4bd', '000100010008', '自定义路由', 'Route', 'menu', '/platform/sys/route', 'data-pjax', null, '1', '0', 'sys.manager.route', null, '37', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('682626f7d4d348729f84f7427bc142cc', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100002', '修改字典', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.dict.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('68cdbf694f71445c8587a20234d6fe31', '3f330d729ca34dc9825c46122be1bfae', '000300030002', '链接管理', 'Link', 'menu', '/platform/cms/link/link', 'data-pjax', '', '1', '0', 'cms.link.link', null, '27', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468897031', '0');
INSERT INTO `sys_menu` VALUES ('6afc5075913d4df4b44a6476080e35a0', 'b0edc6861a494b79b97990dc05f0a524', '00020005', '模板消息', 'Template', 'menu', '', '', 'ti-notepad', '1', '0', 'wx.tpl', null, '14', '1', '', '1470406797', '0');
INSERT INTO `sys_menu` VALUES ('6b6de8c720c645a1808e1c3e9ccbfc90', '02e86a61e99746bea34236ea73dd52a5', '00030002', '内容管理', 'Content', 'menu', '', '', 'ti-pencil-alt', '1', '0', 'cms.content', null, '22', '1', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468895990', '0');
INSERT INTO `sys_menu` VALUES ('6bb17a41f6394ed0a8a6faf5ff781354', '9f20a757a6bc40ddbb650c70debbf660', '000200020002', '群发消息', 'Mass', 'menu', '/platform/wx/msg/mass', 'data-pjax', '', '1', '0', 'wx.msg.mass', null, '5', '0', '', '1467471561', '0');
INSERT INTO `sys_menu` VALUES ('6fc4a70a0f13430dbb3ca9b42a8520d9', '0b097e9dbad649048f956355d1e91278', '0001000100090003', '删除应用', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.api.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('7021fa1a6f76415d9dc21ca28caa2dae', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110001', '添加插件', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.plugin.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('7125a72beee34b21ab3df9bf01b7bce6', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010003', '删除分类', 'Delete', 'data', '', '', '', '0', '0', 'cms.link.class.delete', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896968', '0');
INSERT INTO `sys_menu` VALUES ('7147640d3dfe48e8a03009df11c610da', '46210709bff149d78d4d7914cb9daa72', '00050002', '身份认证', null, 'menu', '/platform/gy/person/auth', '', '', '1', '0', 'gy.person.auth', null, '49', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510570155', '0');
INSERT INTO `sys_menu` VALUES ('72f75a6078384584b996d71bbfcf646d', '437b5771ab0a42ec886a69e050c74a52', '0001000100050003', '删除参数', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.conf.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('733d3f35d49f45af99ca9220048583ba', '0706112ff5dc46e388064a99bcdb0561', '0002000300040003', '删除绑定', 'Delete', 'data', '', '', '', '0', '0', 'wx.reply.keyword.delete', null, '0', '0', '', '1467474136', '0');
INSERT INTO `sys_menu` VALUES ('73a29d3f99224426b5a87c92da122275', 'd1e991ad38a8424daf9f7eb000ee27f4', '0003000100010001', '保存配置', 'Save', 'data', '', '', '', '0', '0', 'cms.site.settings.save', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468895899', '0');
INSERT INTO `sys_menu` VALUES ('792d6e9db8c7485db632894148052486', '10c8ae20fa6942c5b54a95df03270273', '0001000100070003', '删除任务', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.task.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('7c040dfd8db347e5956a3bc1764653dc', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010003', '删除文本', 'Delete', 'data', '', '', '', '0', '0', 'wx.reply.txt.delete', null, '0', '0', '', '1467473540', '0');
INSERT INTO `sys_menu` VALUES ('7db6207d0dab4d6e95a7eee4f2efe875', '9822bafbe3454dfd8e8b974ebc304d03', '0003000300010001', '添加分类', 'Add', 'data', '', '', '', '0', '0', 'cms.link.class.add', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896947', '0');
INSERT INTO `sys_menu` VALUES ('81772c143625453b84f367ee6c444b5d', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110003', '删除插件', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.plugin.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('8515f0f788ea435f9b41f32891da1f4e', '60d42fa10a804c23a502aa06d786182a', '000800020001', '任务种类添加', null, 'data', '', '', '', '0', '0', 'lib.task.add', null, '60', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511167119', '0');
INSERT INTO `sys_menu` VALUES ('879a4e4883a0465a939dfc493232e2c2', '10c8ae20fa6942c5b54a95df03270273', '0001000100070001', '添加任务', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.task.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('8d93fd924f3542dea28e4335c4654834', '0b097e9dbad649048f956355d1e91278', '0001000100090002', '修改应用', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.api.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('93f7b2bce2054dcf8921654ccbbde876', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030002', '修改角色', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.role.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('96554b09a2dd4f82bab7546fa59acd35', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010002', '修改帐号', 'Edit', 'data', '', '', '', '0', '0', 'wx.conf.account.edit', null, '0', '0', '', '1467474197', '0');
INSERT INTO `sys_menu` VALUES ('9822bafbe3454dfd8e8b974ebc304d03', '3f330d729ca34dc9825c46122be1bfae', '000300030001', '链接分类', 'Class', 'menu', '/platform/cms/link/class', 'data-pjax', '', '1', '0', 'cms.link.class', null, '26', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896932', '0');
INSERT INTO `sys_menu` VALUES ('9a9557177d334c209cf73c3817fe3b63', '2fab774f8b6d40cb9d7e187babab2d91', '0002000400020003', '删除菜单', 'Delete', 'data', '', '', '', '0', '0', 'wx.conf.menu.delete', null, '0', '0', '', '1467474304', '0');
INSERT INTO `sys_menu` VALUES ('9f20a757a6bc40ddbb650c70debbf660', 'b0edc6861a494b79b97990dc05f0a524', '00020002', '消息管理', 'Message', 'menu', '', '', 'ti-pencil-alt', '1', '0', 'wx.msg', null, '3', '1', '', '1467471415', '0');
INSERT INTO `sys_menu` VALUES ('a09dd5fc3517447b9668457fb77c2d08', '5adfb7b54d784242a7c524734a10d4bd', '000100010003', '角色管理', 'Role', 'menu', '/platform/sys/role', 'data-pjax', null, '1', '0', 'sys.manager.role', null, '32', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('a11163584dfe456cbfd6fb2d4b74391b', 'cabbe834a7474675b899e8442b5c2604', '0002000500020001', '获取列表', 'Get', 'data', '', '', '', '0', '0', 'wx.tpl.list.get', null, '0', '0', '', '1470407390', '0');
INSERT INTO `sys_menu` VALUES ('adf38e430e8c4a8d87d0070ccf3358ff', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030001', '添加角色', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.role.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('ae4adeb99c08458d83d7c663bffa0eca', 'ebaa84b4d7654791afa4cf116413f12e', '0001000100100003', '删除字典', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.dict.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('b0edc6861a494b79b97990dc05f0a524', '', '0002', '微信', 'Wechat', 'menu', '', '', '', '1', '0', 'wx', null, '0', '1', '', '1467471229', '0');
INSERT INTO `sys_menu` VALUES ('b19b23b0459a4754bf1fb8cb234450f2', 'e4256d7b0ffc4a02906cf900322b6213', '000200010001', '会员列表', 'List', 'menu', '/platform/wx/user/index', 'data-pjax', '', '1', '0', 'wx.user.list', null, '2', '0', '', '1467471357', '0');
INSERT INTO `sys_menu` VALUES ('b225e7eafec846f29023cca78f63bc18', '5adfb7b54d784242a7c524734a10d4bd', '000100010004', '菜单管理', 'Menu', 'menu', '/platform/sys/menu', 'data-pjax', null, '1', '0', 'sys.manager.menu', null, '33', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('b2631bbdbf824cc4b74d819c87962c0d', '6b6de8c720c645a1808e1c3e9ccbfc90', '000300020001', '栏目管理', 'Channel', 'menu', '/platform/cms/channel', 'data-pjax', '', '1', '0', 'cms.content.channel', null, '23', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896018', '0');
INSERT INTO `sys_menu` VALUES ('bcf64d623fdd4519ae345b7a08c071a1', 'b0edc6861a494b79b97990dc05f0a524', '00020004', '微信配置', 'Config', 'menu', '', '', 'fa fa-weixin', '1', '0', 'wx.conf', null, '11', '1', '', '1467472498', '0');
INSERT INTO `sys_menu` VALUES ('bd606a93c49a407e8193bea7ad0370e7', 'e7287128c8a5470081ed782a282d30c6', '0001000100020001', '添加用户', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.user.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('c3a44b478d3241b899b9c3f4611bc2b6', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010001', '添加文本', 'Add', 'data', '', '', '', '0', '0', 'wx.reply.txt.add', null, '0', '0', '', '1467473460', '0');
INSERT INTO `sys_menu` VALUES ('c76a84f871d047db955dd1465c845ac1', '6afc5075913d4df4b44a6476080e35a0', '000200050003', '发送记录', 'Log', 'menu', '/platform/wx/tpl/log', 'data-pjax', '', '1', '0', 'wx.tpl.log', null, '17', '0', '', '1470406926', '0');
INSERT INTO `sys_menu` VALUES ('cab7887b8ded412788f988544559780f', '31aae024cd5d47999aa9e73becc14cb5', '000400020002', '雇员身份审核删除', null, 'data', '', '', '', '1', '0', 'platform.gy.auth.delete', null, '52', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510570250', '0');
INSERT INTO `sys_menu` VALUES ('cabbe834a7474675b899e8442b5c2604', '6afc5075913d4df4b44a6476080e35a0', '000200050002', '模板列表', 'List', 'menu', '/platform/wx/tpl/list', 'data-pjax', '', '1', '0', 'wx.tpl.list', null, '16', '0', '', '1470406883', '0');
INSERT INTO `sys_menu` VALUES ('cbb294ea95654891b5c0adbbbf6d19b3', '09147fe6c278458cb77c4162fff447e3', '000800010002', '技能删除', null, 'data', '', '', '', '0', '0', 'lib.skill.edit', null, '58', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511164176', '0');
INSERT INTO `sys_menu` VALUES ('cbc21bae64934398b3379835f3a75550', '09147fe6c278458cb77c4162fff447e3', '000800010001', '技能添加', null, 'data', '', '', '', '0', '0', 'lib.skill.add', null, '57', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511164152', '0');
INSERT INTO `sys_menu` VALUES ('cdac024c3c7a459d914ea0e62c662554', '', '0007', '平台项目', null, 'menu', '', '', '', '1', '0', 'public.xm', null, '43', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510465216', '0');
INSERT INTO `sys_menu` VALUES ('ce709456e867425297955b3c40406d7e', '6bb17a41f6394ed0a8a6faf5ff781354', '0002000200020002', '删除图文', 'Delete', 'data', '', '', '', '0', '0', 'wx.msg.mass.delNews', null, '0', '0', '', '1467473363', '0');
INSERT INTO `sys_menu` VALUES ('cecb646ee700497fbf1ea3b0c776eb5a', '6731cf86a00e4db283099ef7c7211448', '0001000100080002', '修改路由', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.route.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('d157b33b8490445abbc892f332fd5cbb', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010001', '添加单位', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.unit.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('d1e991ad38a8424daf9f7eb000ee27f4', 'd920314e925c451da6d881e7a29743b7', '000300010001', '网站配置', 'Settings', 'menu', '/platform/cms/site', 'data-pjax', '', '1', '0', 'cms.site.settings', null, '21', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468895881', '0');
INSERT INTO `sys_menu` VALUES ('d20b5be25e2843c4a3c5e4a4a9b929e6', '298f7a5f36574102a87268d7ffbe77d6', '000400010002', '雇员信息编辑', null, 'data', '', '', '', '0', '0', 'platform.gy.inf.edit', null, '46', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510537766', '0');
INSERT INTO `sys_menu` VALUES ('d568f4c2b687404e8aec7b9edcae5767', '66cc21d7ce104dd6877cbce114c59fb3', '0002000400010003', '删除帐号', 'Delete', 'data', '', '', '', '0', '0', 'wx.conf.account.delete', null, '0', '0', '', '1467474209', '0');
INSERT INTO `sys_menu` VALUES ('d86c48e786cb45418c069cefa3056c0a', '6731cf86a00e4db283099ef7c7211448', '0001000100080001', '添加路由', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.route.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('d883f98ee747464bace45cf4ad111a85', 'efeed24e41054b3da9edbddbf7cedeac', '0001000100110002', '启用禁用', 'Update', 'data', null, null, null, '0', '0', 'sys.manager.plugin.update', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('d920314e925c451da6d881e7a29743b7', '02e86a61e99746bea34236ea73dd52a5', '00030001', '站点管理', 'Site', 'menu', '', '', 'ti-world', '1', '0', 'cms.site', null, '20', '1', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468895821', '0');
INSERT INTO `sys_menu` VALUES ('d9b453267f7240bcbae9f87169caeb6f', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030004', '分配菜单', 'SetMenu', 'data', null, null, null, '0', '0', 'sys.manager.role.menu', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('dd965b2c1dfd493fb5efc7e4bcac99d4', '2cb327ad59b140828fd26eb2a46cb948', '0002000300030001', '添加绑定', 'Add', 'data', '', '', '', '0', '0', 'wx.reply.follow.add', null, '0', '0', '', '1467474026', '0');
INSERT INTO `sys_menu` VALUES ('e40193cfca8e425694f0b31cc2f0f04c', 'a09dd5fc3517447b9668457fb77c2d08', '0001000100030005', '分配用户', 'SetUser', 'data', null, null, null, '0', '0', 'sys.manager.role.user', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('e4256d7b0ffc4a02906cf900322b6213', 'b0edc6861a494b79b97990dc05f0a524', '00020001', '微信会员', 'Member', 'menu', '', '', 'fa fa-user', '1', '0', 'wx.user', null, '1', '1', '', '1467471292', '0');
INSERT INTO `sys_menu` VALUES ('e461c62a1d5441619cd35612f3b40691', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010002', '修改栏目', 'Edit', 'data', '', '', '', '0', '0', 'cms.content.channel.edit', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896060', '0');
INSERT INTO `sys_menu` VALUES ('e52675e8b10045cb891662623b455861', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010002', '修改单位', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.unit.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('e6b6224617b04090a76e46a4b048fb96', '1385ae887e5c4b8aa33fbf228be7f907', '0002000500010001', '添加编号', 'Add', 'data', '', '', '', '0', '0', 'wx.tpl.id.add', null, '0', '0', '', '1470407055', '0');
INSERT INTO `sys_menu` VALUES ('e7287128c8a5470081ed782a282d30c6', '5adfb7b54d784242a7c524734a10d4bd', '000100010002', '用户管理', 'User', 'menu', '/platform/sys/user', 'data-pjax', null, '1', '0', 'sys.manager.user', null, '31', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('e864c78aba63448892cbcb6a3a7f4da7', '0706112ff5dc46e388064a99bcdb0561', '0002000300040001', '添加绑定', 'Add', 'data', '', '', '', '0', '0', 'wx.reply.keyword.add', null, '0', '0', '', '1467474113', '0');
INSERT INTO `sys_menu` VALUES ('ebaa84b4d7654791afa4cf116413f12e', '5adfb7b54d784242a7c524734a10d4bd', '000100010010', '数据字典', 'Dict', 'menu', '/platform/sys/dict', 'data-pjax', null, '1', '0', 'sys.manager.dict', null, '39', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('ed9f26e5926b4b87a2f2e605b02a2a7e', '4bdbe5fa320d4873890b1764bfbd2f47', '0001000100010003', '删除单位', 'Delete', 'data', null, null, null, '0', '0', 'sys.manager.unit.delete', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('ef9f436c61654ec09efbfa79a40061cf', '6075fc0cf0ef441b9d93cc3cab3445bf', '0003000200020002', '修改文章', 'Edit', 'data', '', '', '', '0', '0', 'cms.content.article.edit', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896159', '0');
INSERT INTO `sys_menu` VALUES ('efeed24e41054b3da9edbddbf7cedeac', '5adfb7b54d784242a7c524734a10d4bd', '000100010011', '插件管理', 'Plugin', 'menu', '/platform/sys/plugin', 'data-pjax', null, '1', '0', 'sys.manager.plugin', null, '40', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('f426468abf714b1599729f8c36ebbb0d', '9f20a757a6bc40ddbb650c70debbf660', '000200020001', '会员消息', 'Msg', 'menu', '/platform/wx/msg/user', 'data-pjax', '', '1', '0', 'wx.msg.user', null, '4', '1', '', '1467471478', '0');
INSERT INTO `sys_menu` VALUES ('f5aa5aff8e0f4d1b914785d9d7fbc163', '10c8ae20fa6942c5b54a95df03270273', '0001000100070002', '修改任务', 'Edit', 'data', null, null, null, '0', '0', 'sys.manager.task.edit', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('f627f3ad43984ab28ed85328c98b77b6', '09147fe6c278458cb77c4162fff447e3', '000800010003', '技能删除', null, 'data', '', '', '', '0', '0', 'lib.skill.delete', null, '59', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1511164194', '0');
INSERT INTO `sys_menu` VALUES ('f6fba69c3b704d79834b8bd2cc753729', 'b2631bbdbf824cc4b74d819c87962c0d', '0003000200010003', '删除栏目', 'Delete', 'data', '', '', '', '0', '0', 'cms.content.channel.delete', null, '0', '0', '1a19ef09b12344b4a797d6e6dfe7fb29', '1468896072', '0');
INSERT INTO `sys_menu` VALUES ('f9a6cce659334b0987384723b7707bad', '0b097e9dbad649048f956355d1e91278', '0001000100090001', '添加应用', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.api.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('f9f54329eabd4d3a8b2229212007a54a', '298f7a5f36574102a87268d7ffbe77d6', '000400010001', '增加雇员信息', null, 'data', '', '', '', '0', '0', 'platform.gy.inf.add', null, '45', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510537718', '0');
INSERT INTO `sys_menu` VALUES ('fc340d1786864183bed4d56183905461', 'b225e7eafec846f29023cca78f63bc18', '0001000100040001', '添加菜单', 'Add', 'data', null, null, null, '0', '0', 'sys.manager.menu.add', null, '0', '0', '', '1510391361', '0');
INSERT INTO `sys_menu` VALUES ('fc52d5284b8f4522802383c1ef732242', '17e1ee23ca1443f1bc886c2f5eb7c24b', '0002000300020003', '删除图文', 'Delete', 'data', '', '', '', '0', '0', 'wx.reply.news.delete', null, '0', '0', '', '1467473606', '0');
INSERT INTO `sys_menu` VALUES ('fd63a8e389e04ff3a86c3cea53a3b9d5', '234f8ec3c2bc42bf9f6202aecae36fd6', '0002000300010002', '修改文本', 'Edit', 'data', '', '', '', '0', '0', 'wx.reply.txt.edit', null, '0', '0', '', '1467473519', '0');
INSERT INTO `sys_menu` VALUES ('ff6cd243a77c4ae98dacf6149c816c75', '0706112ff5dc46e388064a99bcdb0561', '0002000300040002', '修改绑定', 'Edit', 'data', '', '', '', '0', '0', 'wx.reply.keyword.edit', null, '0', '0', '', '1467474125', '0');

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_PLUGIN` (`code`),
  UNIQUE KEY `INDEX_SYS_CLASSNAME` (`className`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_plugin
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_blob_triggers`;
CREATE TABLE `sys_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_calendars`;
CREATE TABLE `sys_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_cron_triggers`;
CREATE TABLE `sys_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_fired_triggers`;
CREATE TABLE `sys_qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_SYS_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_SYS_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_SYS_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_job_details`;
CREATE TABLE `sys_qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_SYS_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_locks`;
CREATE TABLE `sys_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_locks
-- ----------------------------
INSERT INTO `sys_qrtz_locks` VALUES ('defaultScheduler', 'STATE_ACCESS');
INSERT INTO `sys_qrtz_locks` VALUES ('defaultScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for sys_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_paused_trigger_grps`;
CREATE TABLE `sys_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_scheduler_state`;
CREATE TABLE `sys_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `sys_qrtz_scheduler_state` VALUES ('defaultScheduler', 'DESKTOP-OI9TLB21511171183934', '1511171484600', '20000');

-- ----------------------------
-- Table structure for sys_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_simple_triggers`;
CREATE TABLE `sys_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_simprop_triggers`;
CREATE TABLE `sys_qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `sys_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `sys_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `sys_qrtz_triggers`;
CREATE TABLE `sys_qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_SYS_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_SYS_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_SYS_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_SYS_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `sys_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `sys_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_qrtz_triggers
-- ----------------------------

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_ROLE_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '未认证雇员', 'gy-level1', null, '0', '', '注册账号但是没有认证的雇员', '405a28c9389d4a8581a29c283dc9f5b9', '1510463511', '0');
INSERT INTO `sys_role` VALUES ('592b631bb0874944acd918e34529d045', '游客', 'gy', null, '0', 'd331f97e475d45b6be862f059f80f53e', '你妈也', '405a28c9389d4a8581a29c283dc9f5b9', '1510395050', '0');
INSERT INTO `sys_role` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '系统管理员', 'sysadmin', 'Sysadmin', '0', '', 'System Admin', '', '1510391361', '0');

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
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', '5f255c41da5a4befb176e78e5ba2c89c');
INSERT INTO `sys_role_menu` VALUES ('592b631bb0874944acd918e34529d045', 'cdac024c3c7a459d914ea0e62c662554');
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
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '4bb07c9f6685429d8a2aa5d9d2b4fed5');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '7147640d3dfe48e8a03009df11c610da');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '5f255c41da5a4befb176e78e5ba2c89c');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('4f69e65125ef482ab88524c6dbe3d3a2', '46210709bff149d78d4d7914cb9daa72');
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
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4bb07c9f6685429d8a2aa5d9d2b4fed5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '7147640d3dfe48e8a03009df11c610da');
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
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f9f54329eabd4d3a8b2229212007a54a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd20b5be25e2843c4a3c5e4a4a9b929e6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0e73d7b9cf8443c1a48ad197bc9a65ec');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3050a2d827814a84a45b392166f6bfce');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cab7887b8ded412788f988544559780f');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5f2f54d9dab84be8bab26a13431f2b59');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5f255c41da5a4befb176e78e5ba2c89c');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cdac024c3c7a459d914ea0e62c662554');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cbc21bae64934398b3379835f3a75550');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'cbb294ea95654891b5c0adbbbf6d19b3');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'f627f3ad43984ab28ed85328c98b77b6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '60d42fa10a804c23a502aa06d786182a');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '8515f0f788ea435f9b41f32891da1f4e');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0ec22065b0134f0091d5ce9f92e83183');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '0cc1b6bedbc648a2bb8cab0ca2bbf02c');
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
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '46210709bff149d78d4d7914cb9daa72');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd1e991ad38a8424daf9f7eb000ee27f4');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'd920314e925c451da6d881e7a29743b7');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '02e86a61e99746bea34236ea73dd52a5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', 'b2631bbdbf824cc4b74d819c87962c0d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6b6de8c720c645a1808e1c3e9ccbfc90');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '6075fc0cf0ef441b9d93cc3cab3445bf');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '9822bafbe3454dfd8e8b974ebc304d03');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '3f330d729ca34dc9825c46122be1bfae');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '68cdbf694f71445c8587a20234d6fe31');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '4bdbe5fa320d4873890b1764bfbd2f47');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '5adfb7b54d784242a7c524734a10d4bd');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '36ce16235ac14cebbdfd7a4ddcd02a7b');
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
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '298f7a5f36574102a87268d7ffbe77d6');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1faa04cc182a4849b94a99286d972b0d');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '31aae024cd5d47999aa9e73becc14cb5');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '09147fe6c278458cb77c4162fff447e3');
INSERT INTO `sys_role_menu` VALUES ('859f09a331ba41e9b3d97ef5223e44de', '1c8a24e0cf094e8e99346aa8b0e4391b');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_route
-- ----------------------------
INSERT INTO `sys_route` VALUES ('73794be6224b401f92c4cf602dcf213f', '/sysadmin', '/platform/login', 'hide', '0', '', '1510391364', '0');

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
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('ba75cb278b6c4ced849120de87ac1c84', '测试任务', 'cn.wizzer.app.web.commons.quartz.job.TestJob', '微信号：wizzer | 欢迎发送红包以示支持，多谢。。', '*/5 * * * * ?', '{\"hi\":\"Wechat:wizzer | send red packets of support,thank u\"}', null, null, '1', '', '1510391364', '0');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_UNIT_PATH` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_unit
-- ----------------------------
INSERT INTO `sys_unit` VALUES ('d331f97e475d45b6be862f059f80f53e', '', '0001', '武汉哎嘀信息科技有限公司', 'System', null, null, '湖北武汉洪山区武汉理工大学创业园710', '', '897920245@qq.com', 'http://liushangnan.win', '1', '0', '', '1510391361', '0');
INSERT INTO `sys_unit` VALUES ('fd02b028caed459590b68dde6d47f115', '', '0002', '系统雇员', null, '', '雇佣帮雇员管理系统雇员', '武汉市哎嘀信息科技有限公司', '', null, null, '2', '0', '405a28c9389d4a8581a29c283dc9f5b9', '1510464366', '0');

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
  `isOnline` tinyint(1) DEFAULT NULL COMMENT '是否在线',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `email` varchar(255) DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_SYS_USER_LOGINNAMAE` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('28e8a294dd73436583f6d5ab0c8bceaf', '我爱大咪咪', 'jtuk7wnAJkHKX+fVN9mdN0ENESn0steUaC9rCZc7IWU=', 'jdlP6LrT/2sJ1F9+L2QlQQ==', '123', '0', '0', '897920245@qq.com', '1511157478', null, '11', null, null, '0', '0', '0', '1', 'fd02b028caed459590b68dde6d47f115', '28e8a294dd73436583f6d5ab0c8bceaf', '1511157492', '0');
INSERT INTO `sys_user` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', 'superadmin', 'HhMeOxyUkFNDYCcr9YMnYYdtjikGxvUW5kta4veAf7k=', 'UYS3QFynjDo+aXsmLHOAuw==', '超级管理员', '1', '0', '8979202452@qq.com', '1511171205', '127.0.0.1', '93', null, 'palette.css', '0', '0', '0', '0', 'd331f97e475d45b6be862f059f80f53e', '405a28c9389d4a8581a29c283dc9f5b9', '1511140305', '0');
INSERT INTO `sys_user` VALUES ('5ef2004ae6fb4d2090ec1f8cc247dec4', 'public', 'av1WrFBbWNWpkTNwbg4xvHQfvK5jpdU284BLY/aA3D4=', 'PxVBJdN+Sj5jzP1CPC4CnA==', '普通游客', '0', '0', 'wizzer@qq.com', '0', '127.0.0.1', '0', null, 'palette.css', '0', '0', '0', '1', 'd331f97e475d45b6be862f059f80f53e', '', '1510391362', '0');
INSERT INTO `sys_user` VALUES ('6b5e6721e0164f41a646dcdc13537901', 'liushangnan', 'kl7zZMxLSZfYHH7yjFoVYm8yV/V9jLxH6z9f46dZyQM=', '+td9dEtvwThxU74WIQzqmA==', '騷豬', '0', '0', '897920245@qq.com', '0', null, '0', null, null, '0', '0', '0', '1', 'fd02b028caed459590b68dde6d47f115', '405a28c9389d4a8581a29c283dc9f5b9', '1511159123', '0');

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
INSERT INTO `sys_user_role` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', '859f09a331ba41e9b3d97ef5223e44de');
INSERT INTO `sys_user_role` VALUES ('5ef2004ae6fb4d2090ec1f8cc247dec4', '592b631bb0874944acd918e34529d045');
INSERT INTO `sys_user_role` VALUES ('28e8a294dd73436583f6d5ab0c8bceaf', '4f69e65125ef482ab88524c6dbe3d3a2');
INSERT INTO `sys_user_role` VALUES ('6b5e6721e0164f41a646dcdc13537901', '4f69e65125ef482ab88524c6dbe3d3a2');

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
INSERT INTO `sys_user_unit` VALUES ('405a28c9389d4a8581a29c283dc9f5b9', 'd331f97e475d45b6be862f059f80f53e');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `age` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_person
-- ----------------------------

-- ----------------------------
-- Table structure for wx_config
-- ----------------------------
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE `wx_config` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `appname` varchar(120) DEFAULT NULL COMMENT '公众号名称',
  `ghid` varchar(100) DEFAULT NULL COMMENT '原始ID',
  `appid` varchar(50) DEFAULT NULL COMMENT 'Appid',
  `appsecret` varchar(50) DEFAULT NULL COMMENT 'Appsecret',
  `encodingAESKey` varchar(100) DEFAULT NULL COMMENT 'EncodingAESKey',
  `token` varchar(100) DEFAULT NULL COMMENT 'Token',
  `access_token` varchar(255) DEFAULT NULL COMMENT 'access_token',
  `access_token_expires` int(32) DEFAULT NULL COMMENT 'access_token_expires',
  `access_token_lastat` varchar(50) DEFAULT NULL COMMENT 'access_token_lastat',
  `payEnabled` tinyint(1) DEFAULT NULL COMMENT '禁用支付',
  `payInfo` text COMMENT '支付信息',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_config
-- ----------------------------

-- ----------------------------
-- Table structure for wx_mass
-- ----------------------------
DROP TABLE IF EXISTS `wx_mass`;
CREATE TABLE `wx_mass` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '群发名称',
  `type` varchar(20) DEFAULT NULL COMMENT '群发类型',
  `media_id` varchar(255) DEFAULT NULL COMMENT '媒体文件ID',
  `scope` varchar(20) DEFAULT NULL COMMENT 'Scope',
  `content` text COMMENT 'Content',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_mass
-- ----------------------------

-- ----------------------------
-- Table structure for wx_mass_news
-- ----------------------------
DROP TABLE IF EXISTS `wx_mass_news`;
CREATE TABLE `wx_mass_news` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `thumb_media_id` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `author` varchar(120) DEFAULT NULL COMMENT '作者',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content_source_url` varchar(255) DEFAULT NULL COMMENT '原地址',
  `content` text COMMENT '图文内容',
  `digest` text COMMENT '摘要',
  `show_cover_pic` int(32) DEFAULT NULL COMMENT '显示封面',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_mass_news
-- ----------------------------

-- ----------------------------
-- Table structure for wx_mass_send
-- ----------------------------
DROP TABLE IF EXISTS `wx_mass_send`;
CREATE TABLE `wx_mass_send` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `massId` varchar(32) DEFAULT NULL COMMENT '群发ID',
  `receivers` text COMMENT 'Openid列表',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `msgId` varchar(32) DEFAULT NULL COMMENT 'msgId',
  `errCode` varchar(32) DEFAULT NULL COMMENT 'errCode',
  `errMsg` varchar(255) DEFAULT NULL COMMENT 'errMsg',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_mass_send
-- ----------------------------

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parentId` varchar(32) DEFAULT NULL COMMENT '父ID',
  `path` varchar(100) DEFAULT NULL COMMENT '树路径',
  `menuName` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `menuType` varchar(20) DEFAULT NULL COMMENT '菜单类型',
  `menuKey` varchar(20) DEFAULT NULL COMMENT '关键词',
  `url` varchar(255) DEFAULT NULL COMMENT '网址',
  `appid` varchar(255) DEFAULT NULL COMMENT '小程序appid',
  `pagepath` varchar(255) DEFAULT NULL COMMENT '小程序入口页',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `hasChildren` tinyint(1) DEFAULT NULL COMMENT '有子节点',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_menu
-- ----------------------------

-- ----------------------------
-- Table structure for wx_msg
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg`;
CREATE TABLE `wx_msg` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `type` varchar(20) DEFAULT NULL COMMENT '信息类型',
  `content` text COMMENT '信息内容',
  `replyId` varchar(32) DEFAULT NULL COMMENT '回复ID',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_msg
-- ----------------------------

-- ----------------------------
-- Table structure for wx_msg_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg_reply`;
CREATE TABLE `wx_msg_reply` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `msgid` varchar(32) DEFAULT NULL COMMENT 'msgid',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `type` varchar(20) DEFAULT NULL COMMENT '信息类型',
  `content` text COMMENT '信息内容',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_msg_reply
-- ----------------------------

-- ----------------------------
-- Table structure for wx_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_reply`;
CREATE TABLE `wx_reply` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `type` varchar(20) DEFAULT NULL COMMENT '回复类型',
  `msgType` varchar(20) DEFAULT NULL COMMENT '消息类型',
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键词',
  `content` text COMMENT '回复内容',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_reply
-- ----------------------------

-- ----------------------------
-- Table structure for wx_reply_news
-- ----------------------------
DROP TABLE IF EXISTS `wx_reply_news`;
CREATE TABLE `wx_reply_news` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '摘要',
  `picUrl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(255) DEFAULT NULL COMMENT '文章路径',
  `location` int(32) DEFAULT NULL COMMENT '排序字段',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_reply_news
-- ----------------------------

-- ----------------------------
-- Table structure for wx_reply_txt
-- ----------------------------
DROP TABLE IF EXISTS `wx_reply_txt`;
CREATE TABLE `wx_reply_txt` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_reply_txt
-- ----------------------------

-- ----------------------------
-- Table structure for wx_tpl_id
-- ----------------------------
DROP TABLE IF EXISTS `wx_tpl_id`;
CREATE TABLE `wx_tpl_id` (
  `id` varchar(32) NOT NULL COMMENT '模板编号',
  `template_id` varchar(255) DEFAULT NULL COMMENT '模板ID',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_tpl_id
-- ----------------------------

-- ----------------------------
-- Table structure for wx_tpl_list
-- ----------------------------
DROP TABLE IF EXISTS `wx_tpl_list`;
CREATE TABLE `wx_tpl_list` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `template_id` varchar(100) DEFAULT NULL COMMENT '模板ID',
  `title` varchar(255) DEFAULT NULL COMMENT '模板标题',
  `primary_industry` varchar(100) DEFAULT NULL COMMENT '主营行业',
  `deputy_industry` varchar(100) DEFAULT NULL COMMENT '副营行业',
  `content` varchar(300) DEFAULT NULL COMMENT '模板内容',
  `example` varchar(300) DEFAULT NULL COMMENT '模板示例',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_WX_TPL_LIST` (`template_id`,`wxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_tpl_list
-- ----------------------------

-- ----------------------------
-- Table structure for wx_tpl_log
-- ----------------------------
DROP TABLE IF EXISTS `wx_tpl_log`;
CREATE TABLE `wx_tpl_log` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `content` text COMMENT '发送内容',
  `status` int(32) DEFAULT NULL COMMENT '发送状态',
  `result` text COMMENT '发送结果',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_tpl_log
-- ----------------------------

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `openid` varchar(50) DEFAULT NULL COMMENT 'openid',
  `unionid` varchar(50) DEFAULT NULL COMMENT 'unionid',
  `nickname` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `subscribe` tinyint(1) DEFAULT NULL COMMENT '是否关注',
  `subscribeAt` int(32) DEFAULT NULL COMMENT '关注时间',
  `sex` int(32) DEFAULT NULL COMMENT '性别',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '头像',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `opBy` varchar(32) DEFAULT NULL COMMENT '操作人',
  `opAt` int(32) DEFAULT NULL COMMENT '操作时间',
  `delFlag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_WX_USER_OPENID` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_user
-- ----------------------------

-- ----------------------------
-- View structure for v_gy
-- ----------------------------
DROP VIEW IF EXISTS `v_gy`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_gy` AS select `gy_inf`.`id` AS `gyid`,`gy_inf`.`realname` AS `realname`,`gy_inf`.`phone` AS `phone`,`sys_user`.`email` AS `email`,`gy_auth`.`reAuthTime` AS `reAuthTime`,`gy_auth`.`idcardF` AS `idcardF`,`gy_auth`.`idcardB` AS `idcardB`,`gy_auth`.`stuCardF` AS `stuCardF`,`gy_auth`.`stuCardB` AS `stuCardB`,`gy_auth`.`status` AS `status`,`sys_user`.`loginname` AS `loginname`,`sys_user`.`username` AS `username`,`gy_inf`.`qq` AS `qq`,`gy_inf`.`birthday` AS `birthday`,`gy_inf`.`sex` AS `sex`,`gy_inf`.`idcard` AS `idcard`,`gy_inf`.`college` AS `college`,`gy_inf`.`school` AS `school`,`gy_inf`.`major` AS `major`,`gy_inf`.`regYear` AS `regYear`,`gy_inf`.`stuLevel` AS `stuLevel`,`gy_auth`.`id` AS `authid`,`gy_auth`.`note` AS `note`,`gy_inf`.`userid` AS `userid` from ((`gy_inf` join `gy_auth` on((`gy_auth`.`gyid` = `gy_inf`.`id`))) join `sys_user` on((`gy_inf`.`userid` = `sys_user`.`id`))) ;
