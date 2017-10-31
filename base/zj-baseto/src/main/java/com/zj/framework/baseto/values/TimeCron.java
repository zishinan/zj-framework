package com.zj.framework.baseto.values;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 时间cron表达式 <br/>
 * @date 2017-09-29 上午 11:01 <br/>
 */
public interface TimeCron {
    /**
     * 一小时跑一次
     */
    String PER_ONE_HOUR = "0 0 0/1 * * ? ";
}
