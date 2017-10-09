package com.zj.framework.gcode.model;

import java.util.List;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: sql表和实体定义 <br/>
 * @date 2017-09-27 上午 10:12 <br/>
 */
public class PropTable {
    private String tableName;
    private String primaryKey;
    private List<PropField> propFields;

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
}
