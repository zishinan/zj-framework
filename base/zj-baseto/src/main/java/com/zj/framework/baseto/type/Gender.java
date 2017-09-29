package com.zj.framework.baseto.type;

import com.zj.framework.baseto.eo.IdEnum;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 性别 <br/>
 * @date 2017-09-26 下午 3:10 <br/>
 */
public enum Gender implements IdEnum {

    MALE(1, "男"),
    FEMALE(0, "女"),
    SECURITY(2, "保密");

    private Integer id;

    private String desc;

    Gender(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static Gender getInstance(Integer id) {
        if (id == null) {
            return null;
        }
        for (Gender tmp : Gender.values()) {
            if (tmp.id.intValue() == id.intValue()) {
                return tmp;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
