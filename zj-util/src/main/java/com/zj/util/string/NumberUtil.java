package com.zj.util.string;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;

public class NumberUtil
{
	private static final Logger logger = Logger.getLogger(NumberUtil.class);
	private NumberUtil() {
		throw new Error("不要实例化!");
	}
	public static void main(String[] args) {
		double num = 0.89;
		logger.info(fmtDouble(num));
	}
	public static String fmtDouble(Double num){
		if(num == null){
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(num);
	}
	/**
	 * 保留两位小数
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param num
	 * @return
	 */
	public static float fmtFloat(Float num){
		if(num == null){
			return 0;
		}
		return new Float(new DecimalFormat("0.00").format(num));
	}
	public static int toInt(String num){
		if(num == null){
			return 0;
		}
		try {
			return Integer.parseInt(num);
		} catch (Exception e) {
			return 0;
		}
	}
	public static String toDouble(String num){
		if(StringUtil.isBlank(num)){
			return "0.00";
		}
		double fnum = Float.parseFloat(num);
		return fmtDouble(fnum);
	}
	public static float toFloat(String num){
		if(StringUtil.isBlank(num)){
			return 0;
		}
		float fnum = Float.parseFloat(num);
		return fmtFloat(fnum);
	}
	
	/**
	 * 判断一个整数是否是奇数,用位运算提高性能
	 * @param i
	 * @return 是返回true,否则返回false
	 */
	public static boolean isOdd(int i){
		return (i & 1) != 0;
	}
	
	/**
	 * 求dived占div的百分比
	 * @param dived 被除数
	 * @param div 除数
	 * @return
	 */
	public static String getPointDecimail(int dived,int div){
		if(div == 0){
			return "0.0%";
		}
		return new Double( new DecimalFormat( "0.00" ).format(dived * 100.0 / div)) + "%";
	}
	
	/**
	 * 求dived占div的百分比
	 * @param dived 被除数
	 * @param div 除数
	 * @return
	 */
	public static String getPointDecimail(String dived,String div){
		if(dived == null || div == null){
			return "0.0%";
		}
		int tdived = 0;
		int tdiv = 0;
		try {
			tdived = Integer.parseInt(dived);
			tdiv = Integer.parseInt(div);
		} catch (Exception e) {
			logger.error("请检查数据:dived=" + dived + ";div=" + div);
		}
		String result = getPointDecimail(tdived, tdiv);
		return result;
	}
	
	/**
	 * 获取两个float类型的百分占比，小数直接省去,除数为0时返回“--”
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param dived
	 * @param div
	 * @return
	 */
	public static String getFloatPointString(float dived,float div){
		if(div <= 0){
			return "--";
		}
		return (int)(dived/div*100)+"%";
	}
	/**
	 * 获取两个float类型的百分占比，小数直接省去,除数为0时返回“--”
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param dived
	 * @param div
	 * @return
	 */
	public static String getDoublePointString(double dived,double div){
		if(div <= 0){
			return "--";
		}
		return (int)(dived/div*100)+"%";
	}
}
