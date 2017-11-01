package com.zj.framework.gcode.type;

import com.zj.util.string.StringUtil;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 数据类型 <br/>
 * @date 2017-09-27 上午 10:21 <br/>
 */
public enum SqlType {
    TINYINT("tinyint",1,FieldType.BOOLEAN),
    INT("int",11,FieldType.INTEGER),
    VARCHAR("varchar",255,FieldType.STRING),
    TEXT("text",null,FieldType.STRING),
    DATETIME("datetime",null,FieldType.DATE),
    BIGINT("bigint",null,FieldType.LONG),
    ;
    private String name;
    private Integer defLength;
    private FieldType fieldType;

    SqlType(String name, Integer defLength,FieldType fieldType) {
        this.name = name;
        this.defLength = defLength;
        this.fieldType = fieldType;
    }

    public static SqlType getSqlTypeByName(String name){
        if(StringUtil.isWebBlank(name)){
            return null;
        }
        for (SqlType sqlType : SqlType.values()) {
            if(sqlType.name.equals(name)){
                return sqlType;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public Integer getDefLength() {
        return defLength;
    }
}
