package com.zj.framework.gcode.model;

import com.zj.framework.gcode.type.FieldType;
import com.zj.framework.gcode.type.SqlType;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 字段定义 <br/>
 * @date 2017-09-27 上午 10:13 <br/>
 */
public class PropField {
//    数据库信息
    /**
     * sql属性名，大写并且“_”分隔
     */
    private String sqlFieldName;
    /**
     * sql属性类型
     */
    private SqlType sqlType;
    /**
     * 属性长度限制
     */
    private int fieldLength;
    /**
     * 是否允许为空
     */
    private boolean allowNull;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否自动递增
     */
    private boolean autoIncrement;
    /**
     * 是否是主键
     */
    private boolean primary;
    /**
     * 是否是索引
     */
    private boolean index;

//    Javabean信息
    /**
     * java类型
     */
    private FieldType fieldType;
    /**
     * java类型显示
     */
    private String fieldTypeString;
    /**
     * javabean属性名
     */
    private String fieldName;
    /**
     * get和set方法用的属性名称，首字母大写
     */
    private String firstUpFieldName;
    /**
     * get方法标识，boolean类型用is
     */
    private String getMethodTarget = "get";

//  共用信息
    /**
     * 备注,注释
     */
    private String content;


    public String getSqlFieldName() {
        return sqlFieldName;
    }

    public void setSqlFieldName(String sqlFieldName) {
        this.sqlFieldName = sqlFieldName;
    }

    public SqlType getSqlType() {
        return sqlType;
    }

    public void setSqlType(SqlType sqlType) {
        this.sqlType = sqlType;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFieldTypeString() {
        return fieldTypeString;
    }

    public void setFieldTypeString(String fieldTypeString) {
        this.fieldTypeString = fieldTypeString;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFirstUpFieldName() {
        return firstUpFieldName;
    }

    public void setFirstUpFieldName(String firstUpFieldName) {
        this.firstUpFieldName = firstUpFieldName;
    }

    public String getGetMethodTarget() {
        return getMethodTarget;
    }

    public void setGetMethodTarget(String getMethodTarget) {
        this.getMethodTarget = getMethodTarget;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
