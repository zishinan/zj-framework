package com.zj.util.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 数字处理工具类 <br/>
 * @date 2017-10-22 上午 10:24 <br/>
 */
public class NumberUtil {
    private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);

    /**
     * 判断一个整数是否是奇数
     * 用位运算提高性能
     * @param i
     * @return 是返回true,否则返回false
     */
    public static boolean isOdd(int i){
        return (i & 1) != 0;
    }
}
