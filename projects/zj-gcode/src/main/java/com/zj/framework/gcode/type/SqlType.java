package com.zj.framework.gcode.type;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 数据类型 <br/>
 * @date 2017-09-27 上午 10:21 <br/>
 */
public enum SqlType {
    TINYINT("tinyint",1),
    INT("int",11),
    VARCHAR("varchar",255),
    DATETIME("datetime",null),
    ;
    private String name;
    private Integer defLength;

    SqlType(String name, Integer defLength) {
        this.name = name;
        this.defLength = defLength;
    }

    public String getName() {
        return name;
    }

    public Integer getDefLength() {
        return defLength;
    }
}
