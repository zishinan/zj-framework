package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: 工单扩展 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class CurstomerOrderExtr extends BaseTO {
    /**
    * 工单单号
    */
    private Long orderId;
    /**
    * 扩展id
    */
    private Long extrId;
    /**
    * 扩展值
    */
    private String strVal;

    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getExtrId(){
        return extrId;
    }
    public void setExtrId(Long extrId) {
        this.extrId = extrId;
    }
    public String getStrVal(){
        return strVal;
    }
    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }
}