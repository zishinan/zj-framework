package com.zj.framework.baseto.type;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 时间格式 <br/>
 * @date 2017-09-13 下午 2:12 <br/>
 */
public enum DatePattern{
    yyyyMMdd_HHmmss("yyyy-MM-dd HH:mm:ss"),
    yyyyMMdd_HHmm("yyyy-MM-dd HH:mm"),
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    MMdd_HHmm("MM-dd HH:mm"),
    yyyyMMdd("yyyy-MM-dd"),
    yyyyMMdd_mini("yyyyMMdd"),
    yyyyMMdd_ch("yyyy年MM月dd日"),
    HHmmss("HH:mm:ss")
    ;
    private String pattern;
    DatePattern(String pattern){
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
