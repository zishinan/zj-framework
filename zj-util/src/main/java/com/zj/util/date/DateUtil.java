package com.zj.util.date;

import com.zj.util.string.StringUtil;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class DateUtil
{
	public static final Logger LOGGER = Logger.getLogger(DateUtil.class);
	/**
	 * HH:mm
	 */
	public static final String hh_mm   = "HH:mm";
	/**
	 * HH:mm:ss
	 */
	public static final String hh_mm_ss   = "HH:mm:ss";
	/**
	 * yyMMdd
	 */
	public static final String yyMMdd   = "yyMMdd";
	/**
	 * yyyyMM
	 */
	public static final String yyyyMM   = "yyyyMM";
	/**
	 * yyyyMMdd
	 */
	public static final String yyyyMMdd   = "yyyyMMdd";
	/**
	 * yyyyMMddHH
	 */
	public static final String yyyyMMddHH   = "yyyyMMddHH";
	/**
	 * yyyy-MM-dd
	 */
	public static final String yyyy_MM_dd   = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String yyyy_MM_dd_HH_mm_ss   = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy/MM/dd
	 */
	public static final String yyyy__MM__dd = "yyyy/MM/dd";
	/**
	 * yy/MM/dd
	 */
	public static final String yy__MM__dd = "yy/MM/dd";
	
	public static final Long SECOND_LONG = 1000L;
	public static final Long MINUTE_LONG = 60000L;
	public static final Long HOUR_LONG   = 3600000L;
	public static final Long DAY_LONG    = 86400000L;
	public static final Long WEEK_LONG   = 604800000L;
	public static final Long MONTH_LONG  = 2592000000L;
	public static final Long YEAR_LONG   = 31536000000L;
	
	public static void main(String[] args) {
		new DateUtil();
	}
	
	private DateUtil() {
		throw new Error("不要实例化!");
	}
	/**
	 * 根据long获得当前是周几（例：周一）
	 * 
	 * @param i
	 * @return
	 */
	public static String getDayOfWeek(long i)
	{
		String day = "错误";
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);
		switch (dayofweek)
		{
		case Calendar.MONDAY:
			day = "周一";
			break;
		case Calendar.TUESDAY:
			day = "周二";
			break;
		case Calendar.WEDNESDAY:
			day = "周三";
			break;
		case Calendar.THURSDAY:
			day = "周四";
			break;
		case Calendar.FRIDAY:
			day = "周五";
			break;
		case Calendar.SATURDAY:
			day = "周六";
			break;
		case Calendar.SUNDAY:
			day = "周日";
			break;
		default:
			break;
		}
		return day;
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
	
	/**
	 * 比较两个时间相差几天
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDiffDay(long start,long end){
		long diff = end - start;
		if(diff > 0){
			diff = diff + DAY_LONG - 1;
		}
		int diffDay = (int) ((diff)/DAY_LONG);
		return diffDay;
	}
	
	/**
	 * 获取格式化时间，昨天、今天、明天或者2016-01-01
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param time
	 * @return
	 */
	public static String getFmtTimeWithCurday(long time){
		long curDayLong = covertString2Long(getCurrentTimeString(yyyy_MM_dd));
		int diffDay = getDiffDay(time, curDayLong);
		switch (diffDay) {
		case 0:
			return "今天";
		case 1:
			return "昨天";
		case -1:
			return "明天";
		default:
			return fmtLong2String(time, yyyy_MM_dd);
		}
	}

	/**
	 * 根据long获得当前是几点（24小时制）
	 * 
	 * @param i
	 * @return
	 */
	public static int getHourOfDay(long i)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(i);
		return c.get(Calendar.HOUR_OF_DAY);
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
	
	/**
	 * 格式化long型时间为指定格式的String类型
	 * 
	 * @param time
	 * @param datePrex
	 *            指定时间格式
	 * @return
	 */
	public static String fmtLong2String(long time, String datePrex)
	{
		SimpleDateFormat format = new SimpleDateFormat(datePrex.toString());
		return format.format(new Date(time));
	}
	
	public static String getCurrentTimeString(String datePrex)
	{
		return fmtLong2String(System.currentTimeMillis(), datePrex);
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
	public static long covertString2Long(String date)
	{
		Pattern yyyyMMdd = Pattern.compile("\\d{8}");
		Pattern yyyyMMddHHss = Pattern.compile("\\d{12}");
		Pattern yyyy_MM_HH = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
		Pattern yyMMdd = Pattern.compile("\\d{6}");
		Pattern yyyy_MM_dd_HH_mm_ss = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");

		SimpleDateFormat sdf = null;
		if (StringUtil.isBlank(date))
		{
			return -1;
		}
		if (yyyyMMdd.matcher(date).matches())
		{
			sdf = new SimpleDateFormat("yyyyMMdd");
		}
		else if (yyyy_MM_HH.matcher(date).matches())
		{
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		else if (yyMMdd.matcher(date).matches())
		{
			sdf = new SimpleDateFormat("yyMMdd");
		}
		else if (yyyy_MM_dd_HH_mm_ss.matcher(date).matches())
		{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		else if (yyyyMMddHHss.matcher(date).matches())
		{
			sdf = new SimpleDateFormat("yyyyMMddHHmm");
		}

		try
		{
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(sdf.parse(date).getTime());
			return c.getTimeInMillis();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取本周第一天 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static String getNowWeekBegin() {
		int mondayPlus;
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			mondayPlus = 0;
		} else if(dayOfWeek == 0){
			mondayPlus = -6;
		}else{
			mondayPlus = 1 - dayOfWeek;
		}
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat df = new SimpleDateFormat(yyyy_MM_dd);
		String preMonday = df.format(monday);

		String dateString = preMonday + " 00:00:00";
		LOGGER.error(dateString);
		return dateString;
	}
	public static long getNowWeekBeginTime() {
		return covertString2Long(getNowWeekBegin());
	}

	public static long getNowWeekEndTime() {
		return getNowWeekBeginTime() + WEEK_LONG - 1;
	}
	
	public static String getNowWeekEnd() {
		return fmtLong2String(getNowWeekEndTime(), yyyy_MM_dd_HH_mm_ss);
	}

	/**
	 * 获取本日开始时间 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static long getNowDayBeginTime() {
		long curTime = System.currentTimeMillis();
		return covertString2Long(fmtLong2String(curTime, yyyy_MM_dd));
	}

	public static long getNowDayEndTime() {
		return getNowDayBeginTime() + DAY_LONG -1;
	}

	/**
	 * 获取本月开始时间 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static long getNowMonthBeginTime() {
		return covertString2Long(getNowMonthBegin());
	}

	public static long getNowMonthEndTime() {
		return getNowMonthBeginTime() + MONTH_LONG-1;
	}

	/**
	 * 获取本月第一天 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static String getNowMonthBegin() {
		SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return format.format(cal_1.getTime());
	}

	/**
	 * 获取本日年第一天 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static String getNowYearBegin() {
		SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_YEAR, 1);// 设置为1号,当前日期既为本月第一天
		return format.format(cal_1.getTime());
	}

	/**
	 * 获取本年开始时间 <a href="xi.yang@i-jia.net">yangxi</a>
	 * 
	 * @return
	 */
	public static long getNowYearBeginTime() {
		return covertString2Long(getNowYearBegin());
	}

	public static long getNowYearEndTime() {
		return getNowYearBeginTime() + YEAR_LONG-1;
	}

	public static long getNowQuarterBeginTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH);
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
			return covertString2Long(format.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
