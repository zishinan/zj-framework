package com.zj.framework.utils;

import com.zj.framework.baseto.type.DatePattern;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 补充common-lang3中date相关处理
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 下午 2:36
 **/
public class ZjDateUtils extends DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZjDateUtils.class);
    private static final String[] WEEK_DAY_STRS = {"日","一","二","三","四","五","六"};
    private static final String[] DATE_FMTS = {
            DatePattern.yyMMdd_mini.getPattern(),
            DatePattern.yyyyMMdd_mini.getPattern(),
            DatePattern.yyyyMMdd.getPattern(),
            DatePattern.yyyyMMdd_HHmmss.getPattern(),
            DatePattern.yyyyMMdd_HHmm.getPattern(),
            DatePattern.yyyyMMddHHmmss.getPattern(),
            DatePattern.yyyyMMdd_ch.getPattern()
    };
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

    public static Date parseDate(String str) {
        try {
            return parseDate(str, DATE_FMTS);
        } catch (ParseException e) {
            LOGGER.error("没有这种类型的日期转换格式：{}",str);
            return null;
        }
    }

    public static Long parseDate2Long(String str,Long defVal) {
        Date date = parseDate(str);
        if (null == date) {
            return defVal;
        } else {
            return date.getTime();
        }
    }

    public static Long parseDate2Long(String str) {
        Date date = parseDate(str);
        if (null == date) {
            return null;
        } else {
            return date.getTime();
        }
    }

    /**
     * 获取指定日期的开始时间
     * @param date
     * @return
     */
    public static Date getStartTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, cal.getMinimum(11));
        cal.set(12, cal.getMinimum(12));
        cal.set(13, cal.getMinimum(13));
        cal.set(14, cal.getMinimum(14));
        return cal.getTime();
    }

    /**
     * 获取指定日期的结束时间
     * 友情提示：mysql的DateTime类型不能保存毫秒级，会变成第二天开始时间
     * @param date
     * @return
     */
    public static Date getEndTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, cal.getMaximum(11));
        cal.set(12, cal.getMaximum(12));
        cal.set(13, cal.getMaximum(13));
        cal.set(14, cal.getMaximum(14));
        return cal.getTime();
    }


}
