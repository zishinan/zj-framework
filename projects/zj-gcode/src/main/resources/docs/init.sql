CREATE TABLE `REBATE_BLACK_MID` (
    `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `MID` bigint NOT NULL COMMENT 'mid',
    `CONTENT` varchar ( 255 ) NOT NULL COMMENT '备注',
    `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
    `OPERATOR` varchar ( 255 ) NOT NULL COMMENT '操作人'
  ,  PRIMARY KEY (`ID`)
);

