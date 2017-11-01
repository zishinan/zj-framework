package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 类别配置表 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class SettingSerise extends BaseTO {
    /**
    * 分类id
    */
    private Long id;
    /**
    * 类型id
    */
    private Long categoryId;
    /**
    * 分类名称
    */
    private String name;
    /**
    * faq排序字段
    */
    private Integer faqSort;
    /**
    * 是否必须登录
    */
    private Boolean needLogin;
    /**
    * 是否跳转美恰
    */
    private Boolean toMeiQia;
    /**
    * 是否上传图片
    */
    private Integer uploadImgStatus;
    /**
    * 是否填写乐号
    */
    private Boolean needMid;
    /**
    * 是否生成工单
    */
    private Boolean createOrder;
    /**
    * 扩展字段ID集合
    */
    private String extrIds;
    /**
    * 美恰客服组
    */
    private String meiQiaGroup;
    /**
    * 美恰客服id
    */
    private String meiQiaId;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getFaqSort(){
        return faqSort;
    }
    public void setFaqSort(Integer faqSort) {
        this.faqSort = faqSort;
    }
    public Boolean isNeedLogin(){
        return needLogin;
    }
    public void setNeedLogin(Boolean needLogin) {
        this.needLogin = needLogin;
    }
    public Boolean isToMeiQia(){
        return toMeiQia;
    }
    public void setToMeiQia(Boolean toMeiQia) {
        this.toMeiQia = toMeiQia;
    }
    public Integer getUploadImgStatus(){
        return uploadImgStatus;
    }
    public void setUploadImgStatus(Integer uploadImgStatus) {
        this.uploadImgStatus = uploadImgStatus;
    }
    public Boolean isNeedMid(){
        return needMid;
    }
    public void setNeedMid(Boolean needMid) {
        this.needMid = needMid;
    }
    public Boolean isCreateOrder(){
        return createOrder;
    }
    public void setCreateOrder(Boolean createOrder) {
        this.createOrder = createOrder;
    }
    public String getExtrIds(){
        return extrIds;
    }
    public void setExtrIds(String extrIds) {
        this.extrIds = extrIds;
    }
    public String getMeiQiaGroup(){
        return meiQiaGroup;
    }
    public void setMeiQiaGroup(String meiQiaGroup) {
        this.meiQiaGroup = meiQiaGroup;
    }
    public String getMeiQiaId(){
        return meiQiaId;
    }
    public void setMeiQiaId(String meiQiaId) {
        this.meiQiaId = meiQiaId;
    }
}