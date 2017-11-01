package com.zj.framework.gcode.type;

import com.zj.util.string.StringUtil;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 数据类型 <br/>
 * @date 2017-09-27 上午 10:21 <br/>
 */
public enum FieldType {
    /**
     * 继承了{@link com.zj.framework.baseto.eo.IdEnum}的enum类型
     */
    ID_ENUM,
    /**
     * java类,普通pojo
     */
    JAVA_BEAN,
    INTEGER,
    BOOLEAN,
    LONG,
    STRING,
    DATE,
    ;

    public static FieldType getFieldTypeByName(String name){
        if(StringUtil.isWebBlank(name)){
            return null;
        }
        for (FieldType temp : FieldType.values()) {
            if(temp.toString().equals(name)){
                return temp;
            }
        }
        return null;
    }
}
