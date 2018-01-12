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

    /**
     * 转换时间错误时默认返回值
     */
    private static final long DEFAULT_ERROR_DATE_TIME = -1;
    private static final String[] WEEK_DAY_STRS = {"日","一","二","三","四","五","六"};

    public static final Long SECOND_LONG = 1000L;
    public static final Long MINUTE_LONG = 60000L;
    public static final Long HOUR_LONG   = 3600000L;
    public static final Long DAY_LONG    = 86400000L;
    public static final Long WEEK_LONG   = 604800000L;
    public static final Long MONTH_LONG  = 2592000000L;
    public static final Long YEAR_LONG   = 31536000000L;

    private static final Pattern yyyyMMdd = Pattern.compile("\\d{8}");
    private static final Pattern yyyyMMddHHss = Pattern.compile("\\d{12}");
    private static final Pattern yyyy_MM_HH = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
    private static final Pattern yyyy_MM_dd = Pattern.compile("\\d{4}/\\d{1,2}/\\d{1,2}");
    private static final Pattern yyMMdd = Pattern.compile("\\d{6}");
    private static final Pattern yyyy_MM_dd_HH_mm_ss = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");

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
        return covertString2Long(date,DEFAULT_ERROR_DATE_TIME);
    }

    /**
     * 将String类型的格式化字符串转为long类型
     * @param date
     * @param defaultTime 转换错误默认返回值
     * @return
     */
    public static long covertString2Long(String date,long defaultTime) {
        if (StringUtils.isBlank(date)) {
            return defaultTime;
        }
        SimpleDateFormat sdf = null;

        try {
            if (yyyyMMdd.matcher(date).matches()) {
                sdf = new SimpleDateFormat("yyyyMMdd");
            }
            else if (yyyy_MM_HH.matcher(date).matches()) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            else if (yyyy_MM_dd.matcher(date).matches()) {
                sdf = new SimpleDateFormat("yyyy/MM/dd");
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
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultTime;
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

    /**
     * 对比时间差
     * @param start
     * @param end
     * @return
     */
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

    /**
     * 格式化时间
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date,DatePattern pattern){
        try {
            return new SimpleDateFormat(pattern.getName()).format(date);
        }catch (Exception e){
            LOGGER.error("format date error:"+e.getMessage());
            return null;
        }
    }

    /**
     * 获取定时器时间表达式
     * @param i
     * @return
     */
    public static String getConExpression(long i){
        int y = getYear(i);
        int mo = getMonth(i);
        int d = getDay(i);
        int h = getHour(i);
        int mm = getMinute(i);
        int s = getSecond(i);
        return s+" "+mm+" "+h+" "+d+" "+mo+" "+"?"+" "+y;
    }

    public static int getYear(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getWeek(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getDay(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.DATE);
    }

    public static int getHour(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.HOUR);
    }

    public static int getMinute(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(long i) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(i);
        return c.get(Calendar.SECOND);
    }
}
