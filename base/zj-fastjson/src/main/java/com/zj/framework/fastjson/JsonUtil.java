package com.zj.framework.fastjson;

import com.alibaba.fastjson.JSON;
import com.zj.framework.baseto.type.DatePattern;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 二次封装json转换工具 <br/>
 * @date 2017-09-13 上午 10:44 <br/>
 */
public class JsonUtil {
    /**
     * 转换为string，默认转换时间格式:yyyy-MM-dd HH:mm:ss {@link DatePattern}
     * @param object
     * @return
     */
    public static String toJsonString(Object object){
        return JSON.toJSONStringWithDateFormat(object, DatePattern.yyyyMMdd_HHmmss.getPattern());
    }
}
