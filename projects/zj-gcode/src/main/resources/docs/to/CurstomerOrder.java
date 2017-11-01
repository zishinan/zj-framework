package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 工单表 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class CurstomerOrder extends BaseTO {
    /**
    * 工单单号
    */
    private Long orderId;
    /**
    * 乐号
    */
    private Long mid;
    /**
    * 工单类型
    */
    private Long categoryId;
    /**
    * 问题分类
    */
    private Long seriseId;
    /**
    * 人工客服
    */
    private String customer;
    /**
    * 联运区域
    */
    private String area;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 解决问题时间
    */
    private Date doneTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 问题描述
    */
    private String content;
    /**
    * 图片（3张）
    */
    private String pics;
    /**
    * 是否有用
    */
    private Boolean useful;

    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getMid(){
        return mid;
    }
    public void setMid(Long mid) {
        this.mid = mid;
    }
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getSeriseId(){
        return seriseId;
    }
    public void setSeriseId(Long seriseId) {
        this.seriseId = seriseId;
    }
    public String getCustomer(){
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getArea(){
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getDoneTime(){
        return doneTime;
    }
    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPics(){
        return pics;
    }
    public void setPics(String pics) {
        this.pics = pics;
    }
    public Boolean isUseful(){
        return useful;
    }
    public void setUseful(Boolean useful) {
        this.useful = useful;
    }
}