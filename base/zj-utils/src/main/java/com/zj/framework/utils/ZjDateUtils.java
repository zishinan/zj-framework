package com.zj.framework.utils;

import java.util.Calendar;

/**
 * 补充common-lang3中date相关处理
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 下午 2:36
 **/
public class ZjDateUtils {
    private static final String[] WEEK_DAY_STRS = {"日","一","二","三","四","五","六"};
    /**
     * 获取中文星期几
     * @param time
     * @param prefix 前缀 “星期”、“周”
     * @return
     */
    public static String getDayOfWeekCh(long time,String prefix){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int dayOfweek = c.get(Calendar.DAY_OF_WEEK);
        return prefix + WEEK_DAY_STRS[dayOfweek-1];
    }
}
