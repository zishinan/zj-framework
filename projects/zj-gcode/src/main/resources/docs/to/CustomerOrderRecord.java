package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 回复工单 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class CustomerOrderRecord extends BaseTO {
    /**
    * 主键
    */
    private Long id;
    /**
    * 工单单号
    */
    private Long orderId;
    /**
    * 回复类型(1-客服回复；2-联运回复；3-用户反馈；4-结果反馈)
    */
    private Integer recordType;
    /**
    * 回复人
    */
    private String operoter;
    /**
    * 回复时间
    */
    private Date createTime;
    /**
    * 进度说明（展示给用户看的）
    */
    private String process;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getRecordType(){
        return recordType;
    }
    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
    public String getOperoter(){
        return operoter;
    }
    public void setOperoter(String operoter) {
        this.operoter = operoter;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getProcess(){
        return process;
    }
    public void setProcess(String process) {
        this.process = process;
    }
}