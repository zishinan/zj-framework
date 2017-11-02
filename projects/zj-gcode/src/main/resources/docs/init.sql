CREATE TABLE `SETTING_CATEGORY` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `NAME` varchar ( 255 ) NOT NULL COMMENT '类型名称',
    `SORT` int ( 11 ) NOT NULL COMMENT '排序字段'
  ,  PRIMARY KEY (`ID`)
);

CREATE TABLE `SETTING_SERISE` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `CATEGORY_ID` bigint NOT NULL COMMENT '类型id',
    `NAME` varchar ( 255 ) NOT NULL COMMENT '分类名称',
    `FAQ_SORT` int ( 11 ) NOT NULL COMMENT 'faq排序字段',
    `NEED_LOGIN` tinyint ( 1 ) NOT NULL COMMENT '是否必须登录',
    `TO_MEI_QIA` tinyint ( 1 ) NOT NULL COMMENT '是否跳转美恰',
    `UPLOAD_IMG_STATUS` int ( 11 ) NOT NULL COMMENT '是否上传图片',
    `NEED_MID` tinyint ( 1 ) NOT NULL COMMENT '是否填写乐号',
    `CREATE_ORDER` tinyint ( 1 ) NOT NULL COMMENT '是否生成工单',
    `EXTR_IDS` varchar ( 255 ) NOT NULL COMMENT '扩展字段ID集合',
    `FAQ_STATUS` int ( 11 ) NOT NULL COMMENT 'faq分类状态'
  ,  PRIMARY KEY (`ID`)
);

CREATE TABLE `CUSTOMER_FAQ` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'faqid',
    `SETTING_CATEGORY` bigint NOT NULL COMMENT '类型id',
    `SETTING_SERISE` bigint NOT NULL COMMENT '分类id',
    `TITLE` varchar ( 255 ) NOT NULL COMMENT '标题',
    `CONTENT` text NOT NULL COMMENT '文章',
    `FAQ_STATUS` int ( 11 ) NOT NULL COMMENT '状态',
    `FIRST_PAGE` tinyint ( 1 ) NOT NULL COMMENT '是否热门问题',
    `SORT` int ( 11 ) NOT NULL COMMENT '排序字段',
    `VIEW_COUNT` bigint NOT NULL COMMENT '访问数',
    `USEFUL_COUNT` bigint NOT NULL COMMENT '有用数',
    `UNUSEFUL_COUNT` bigint NOT NULL COMMENT '无用数'
  ,  PRIMARY KEY (`ID`)
);

CREATE TABLE `CUSTOMER_ORDER_EXTR` (
    `ORDER_ID` bigint NOT NULL COMMENT '工单单号',
    `EXTR_ID` bigint NOT NULL COMMENT '扩展id',
    `STR_VAL` varchar ( 255 ) NOT NULL COMMENT '扩展值'
  ,  PRIMARY KEY (`ORDER_ID`)
);

CREATE TABLE `CUSTOMER_ORDER_ALLOT` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `SETTING_CATEGORY` bigint NOT NULL COMMENT '分类id',
    `SETTING_SERISE` bigint NOT NULL COMMENT '类型id',
    `MEIQIA_GROUP` varchar ( 255 ) NOT NULL COMMENT '美恰帐号类型',
    `MEIQIA_CUSTOMER` varchar ( 255 ) NOT NULL COMMENT '美恰客服id',
    `CONTENT` varchar ( 255 ) NOT NULL COMMENT '备注',
    `ORDER_ALLOT_STATUS` int ( 11 ) NOT NULL COMMENT '状态'
  ,  PRIMARY KEY (`ID`)
);

CREATE TABLE `CUSTOMER_ORDER` (
    `ORDER_ID` bigint NOT NULL COMMENT '工单单号',
    `MID` bigint NOT NULL COMMENT '乐号',
    `SETTING_CATEGORY` bigint NOT NULL COMMENT '工单类型',
    `SETTING_SERISE` bigint NOT NULL COMMENT '问题分类',
    `CUSTOMER` varchar ( 255 ) NOT NULL COMMENT '人工客服',
    `AREA` varchar ( 255 ) NOT NULL COMMENT '联运区域',
    `CREATE_TIME` datetime DEFAULT null COMMENT '创建时间',
    `DONE_TIME` datetime DEFAULT null COMMENT '解决问题时间',
    `UPDATE_TIME` datetime DEFAULT null COMMENT '更新时间',
    `ORDER_STATUS` int ( 11 ) NOT NULL COMMENT '状态',
    `CONTENT` varchar ( 255 ) NOT NULL COMMENT '问题描述',
    `PICS` varchar ( 255 ) NOT NULL COMMENT '图片（3张）',
    `USEFUL` tinyint ( 1 ) NOT NULL COMMENT '是否有用',
    `SEND_SMS` tinyint ( 1 ) NOT NULL COMMENT '是否发短信'
  ,  PRIMARY KEY (`ORDER_ID`)
);

CREATE TABLE `CUSTOMER_ORDER_RECORD` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `ORDER_ID` bigint NOT NULL COMMENT '工单单号',
    `RECORD_TYPE` int ( 11 ) NOT NULL COMMENT '回复类型(1-客服回复；2-联运回复；3-用户反馈；4-结果反馈)',
    `OPEROTER` varchar ( 255 ) NOT NULL COMMENT '回复人',
    `CREATE_TIME` datetime DEFAULT null COMMENT '回复时间',
    `PROCESS` varchar ( 255 ) COMMENT '进度说明（展示给用户看的）'
  ,  PRIMARY KEY (`ID`)
);

CREATE TABLE `SETTING_ORDER_SERISE_EXTR` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '扩展id',
    `NAME` varchar ( 255 ) NOT NULL COMMENT '字段名称',
    `FORCE` tinyint ( 1 ) NOT NULL COMMENT '是否必填',
    `ALIAS` varchar ( 255 ) NOT NULL COMMENT '别名'
  ,  PRIMARY KEY (`ID`)
);

