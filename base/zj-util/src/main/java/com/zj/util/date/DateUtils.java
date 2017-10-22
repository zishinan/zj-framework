package com.zj.util.date;

import com.zj.framework.baseto.type.DatePattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: Date相关工具类 <br/>
 * @date 2017-06-15 上午 10:53 <br/>
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    public static final Long SECOND_LONG = 1000L;
    public static final Long MINUTE_LONG = 60000L;
    public static final Long HOUR_LONG   = 3600000L;
    public static final Long DAY_LONG    = 86400000L;
    public static final Long WEEK_LONG   = 604800000L;
    public static final Long MONTH_LONG  = 2592000000L;
    public static final Long YEAR_LONG   = 31536000000L;
    /**
     * 将String类型的格式化字符串转为long类型<br/>
     * 支持的String格式：<br/>
     * yyyyMMdd<br/>
     * yyyy-MM-dd<br/>
     * yyMMdd<br/>
     * yyyy-MM-dd HH:mm:ss<br/>
     * yyyyMMddHHmm<br/>
     * @param date
     * @return
     */
    public static long covertString2Long(String date) {
        Pattern yyyyMMdd = Pattern.compile("\\d{8}");
        Pattern yyyyMMddHHss = Pattern.compile("\\d{12}");
        Pattern yyyy_MM_HH = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
        Pattern yyMMdd = Pattern.compile("\\d{6}");
        Pattern yyyy_MM_dd_HH_mm_ss = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");

        SimpleDateFormat sdf = null;
        if (StringUtils.isBlank(date)) {
            return -1;
        }
        if (yyyyMMdd.matcher(date).matches()) {
            sdf = new SimpleDateFormat("yyyyMMdd");
        }
        else if (yyyy_MM_HH.matcher(date).matches()) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        else if (yyMMdd.matcher(date).matches()) {
            sdf = new SimpleDateFormat("yyMMdd");
        }
        else if (yyyy_MM_dd_HH_mm_ss.matcher(date).matches()) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if (yyyyMMddHHss.matcher(date).matches()) {
            sdf = new SimpleDateFormat("yyyyMMddHHmm");
        }

        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取比传入时间晚n天的时间，负数为早n天
     * @param date
     * @param days
     * @return
     */
    public static Date getDateAfter(Date date,int days){
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,days);
        return calendar.getTime();
    }

    public static int diffDays(Date start,Date end) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(DatePattern.yyyyMMdd.getName());
            long startTime = sdf.parse(sdf.format(start)).getTime();
            long endTime = sdf.parse(sdf.format(end)).getTime();
            long diffDays=(endTime-startTime)/(DAY_LONG);
            return Integer.parseInt(String.valueOf(diffDays));
        }catch (Exception e){
            LOGGER.error("获取时间差错误 ： start = "+start+";end = "+end);
        }
        return 0;
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

    public static String formatDate(Date date,DatePattern pattern){
        try {
            return new SimpleDateFormat(pattern.getName()).format(date);
        }catch (Exception e){
            LOGGER.error("format date error:"+e.getMessage());
            return null;
        }

    }
}
