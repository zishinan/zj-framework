package com.zj.framework.gcode.model;

import java.util.List;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: sql表 <br/>
 * @date 2017-09-27 上午 10:12 <br/>
 */
public class SqlTable {
    private String tableName;
    private String primaryKey;
    private List<SqlField> sqlFields;

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

    public List<SqlField> getSqlFields() {
        return sqlFields;
    }

    public void setSqlFields(List<SqlField> sqlFields) {
        this.sqlFields = sqlFields;
    }
}
