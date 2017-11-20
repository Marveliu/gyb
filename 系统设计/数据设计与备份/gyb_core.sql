CREATE TABLE `gy_inf` (
`gyid` varchar NOT NULL COMMENT '雇员编号',
`userid` varchar(32) NULL,
`realname` varchar(30) NULL COMMENT '真实姓名',
`qq` varchar(20) NULL COMMENT 'qq号码',
`email` varchar(255) NULL COMMENT '邮箱',
`birthday` time(2) NULL DEFAULT 出生日期,
`phone` varchar(11) NULL COMMENT '电话',
`idcard` varchar(25) NULL COMMENT '身份证',
`sex` tinyint(1) NULL DEFAULT 性别,
`college` varchar(30) NULL COMMENT '大学',
`school` varchar(30) NULL COMMENT '学院',
`major` varchar(30) NULL COMMENT '专业',
`regYear` year NULL COMMENT '学校注册年份',
`stuLevel` tinyint(1) NULL COMMENT '学历',
`tatus` varchar(255) NULL,
`status` tinyint(1) NULL COMMENT '雇员状态',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`gyid`) 
);

CREATE TABLE `gy_auth` (
`id` varchar(32) NOT NULL COMMENT '身份认证编号',
`gyid` varchar(30) NULL COMMENT '雇员id',
`time` time NULL COMMENT '记录时间',
`idcardF` varchar(255) NULL COMMENT '身份证正面',
`idcardB` varchar(255) NULL COMMENT '身份证背面',
`stucard` varchar(255) NULL COMMENT '学生证',
`opBy` varchar(32) NULL COMMENT '操作人',
`opAt` time NULL COMMENT '操作日期',
`status` tinyint(1) NULL COMMENT '审核状态',
PRIMARY KEY (`id`) 
);

CREATE TABLE `gy_user` (
);

CREATE TABLE `base` (
`id` varchar(32) NOT NULL COMMENT '雇员编号',
`time` time NULL COMMENT '创建时间',
`status` varchar(255) NULL,
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `sys_skill` (
`id` varchar(32) NOT NULL COMMENT '技能编号',
`parentId` varchar(32) NULL COMMENT '父类编号',
`path` varchar(100) NULL,
`hasChildren` tinyint(1) NULL DEFAULT NULL COMMENT '有子节点',
`name` varchar(100) NULL COMMENT '技能名称',
`aliasName` varchar(100) NULL COMMENT '别名',
`note` varchar(255) NULL COMMENT '技能介绍',
`url` varchar(100) NULL COMMENT '链接',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`status` varchar(255) NULL,
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `xm_inf` (
`id` varchar(32) NOT NULL COMMENT '编号',
`taskid` varchar(255) NULL COMMENT '任务书编号',
`gyid` varchar(32) NULL COMMENT '雇员编号',
`fileUrl` varchar NULL COMMENT '作品上传地址',
`time` time NULL COMMENT '创建时间',
`status` varchar(255) NULL COMMENT '状态',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
)
COMMENT='雇佣项目';

CREATE TABLE `xm_task` (
`id` varchar(32) NOT NULL COMMENT '任务书编号',
`name` varchar(255) NULL COMMENT '项目名称',
`category` varchar(32) NULL COMMENT '项目类别',
`time` time NULL COMMENT '创建时间',
`deadline` time NULL COMMENT '截止日期',
`award` float NULL COMMENT '雇佣酬金',
`status` tinyint(1) NULL,
`note` varchar NULL COMMENT '需求摘要',
`noteUrl` varchar NULL COMMENT '需求说明文档',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
)
COMMENT='雇佣项目';

CREATE TABLE `sys_task` (
`id` varchar(32) NOT NULL COMMENT '技能编号',
`parentId` varchar(32) NULL COMMENT '父类编号',
`path` varchar(100) NULL,
`hasChildren` tinyint(1) NULL DEFAULT NULL COMMENT '有子节点',
`name` varchar(100) NULL COMMENT '任务名称',
`aliasName` varchar(100) NULL COMMENT '别名',
`note` varchar(255) NULL COMMENT '技能介绍',
`url` varchar(100) NULL COMMENT '链接',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`status` varchar(255) NULL,
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `xm_evaluation ` (
`id` varchar(32) NOT NULL COMMENT '雇员编号',
`xmid` varchar(32) NULL COMMENT '项目编号',
`time` time NULL COMMENT '创建时间',
`status` varchar(255) NULL,
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`grade` int NULL COMMENT '评分',
`note` varchar NULL COMMENT '留言',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `xm_bill` (
`id` varchar(32) NOT NULL COMMENT '雇员编号',
`xmid` varchar(32) NULL COMMENT '项目编号',
`award` decimal NULL COMMENT '项目报酬',
`finalpay` decimal NULL COMMENT '最终付款',
`prepay` decimal NULL COMMENT '预付总额',
`note` varchar NULL COMMENT '说明',
`time` time NULL COMMENT '支付日期',
`status` varchar(255) NULL,
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `xm_prepay` (
`id` varchar(32) NOT NULL COMMENT '雇员编号',
`xmid` varchar(32) NULL COMMENT '项目编号',
`premoney` decimal NULL COMMENT '预付款金额',
`time` time NULL COMMENT '创建时间',
`status` varchar(255) NULL,
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`grade` int NULL COMMENT '评分',
`note` varchar NULL COMMENT '留言',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

CREATE TABLE `xm_feedback` (
`id` varchar(32) NOT NULL COMMENT '雇员编号',
`xmid` varchar(32) NULL COMMENT '项目编号',
`parentid` varchar(32) NULL COMMENT '上次反馈编号',
`fileUrl` varchar NULL COMMENT '当前反馈对应文件内容',
`note` varchar NULL COMMENT '反馈说明',
`noteUrl` varchar NULL COMMENT '相关文件下载链接',
`status` varchar(255) NULL COMMENT '状态',
`time` time NULL COMMENT '创建时间',
`opBy` int(32) NULL COMMENT '操作人',
`opAt` int(32) NULL COMMENT '操作时间',
`delFlag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标记',
PRIMARY KEY (`id`) 
);

