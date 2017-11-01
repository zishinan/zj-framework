package com.zj.framework.gcode.model;

import java.util.List;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: sql表和实体定义 <br/>
 * @date 2017-09-27 上午 10:12 <br/>
 */
public class PropTable {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 主键，包括是否自增
     */
    private String primaryKey;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * TO类名
     */
    private String toName;
    /**
     * 小写to
     */
    private String lowerToName;
    /**
     * 注释
     */
    private String content;
    /**
     * 字段集合
     */
    private List<PropField> propFields;
    /**
     * 主键
     */
    private PropField primaryField;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<PropField> getPropFields() {
        return propFields;
    }

    public void setPropFields(List<PropField> propFields) {
        this.propFields = propFields;
    }

    public String getToName() {
        return toName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLowerToName() {
        return lowerToName;
    }
    public void setLowerToName(String lowerToName) {
        this.lowerToName = lowerToName;
    }

    public PropField getPrimaryField() {
        return primaryField;
    }

    public void setPrimaryField(PropField primaryField) {
        this.primaryField = primaryField;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
