package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 分类配置表 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class SettingCategory extends BaseTO {
    /**
    * 自增主键
    */
    private Long id;
    /**
    * 类型名称
    */
    private String name;
    /**
    * 排序字段
    */
    private Integer sort;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSort(){
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}