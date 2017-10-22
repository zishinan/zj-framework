package com.zj.util.string;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 将String类型的数字和数字类型的转换工具 <br/>
 * @date 2017-10-22 上午 10:20 <br/>
 */
public class StringNumberUtil {
    private static final Logger logger = LoggerFactory.getLogger(StringNumberUtil.class);
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
        if(StringUtils.isBlank(num)){
            return "0.00";
        }
        double fnum = Float.parseFloat(num);
        return fmtDouble(fnum);
    }
    public static float toFloat(String num){
        if(StringUtils.isBlank(num)){
            return 0;
        }
        float fnum = Float.parseFloat(num);
        return fmtFloat(fnum);
    }
    public static Long toLong(String num){
        if(StringUtils.isBlank(num)){
            return 0L;
        }
        try {
            return Long.parseLong(num);
        } catch (Exception e) {
            logger.error("string to long error: num = {}",num);
            return 0L;
        }
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
