package com.zj.util.date;

import com.zj.framework.baseto.type.DatePattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: Date相关工具类 <br/>
 * @date 2017-06-15 上午 10:53 <br/>
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
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
            logger.error("format date error:"+e.getMessage());
            return null;
        }

    }
}
