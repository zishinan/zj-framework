package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 工单扩展表 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class SettingOrderSeriseExtr extends BaseTO {
    /**
    * 扩展id
    */
    private Long id;
    /**
    * 字段名称
    */
    private Integer name;
    /**
    * 是否必填
    */
    private Boolean force;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getName(){
        return name;
    }
    public void setName(Integer name) {
        this.name = name;
    }
    public Boolean isForce(){
        return force;
    }
    public void setForce(Boolean force) {
        this.force = force;
    }
}