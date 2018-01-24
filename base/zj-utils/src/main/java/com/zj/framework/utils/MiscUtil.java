/**
 * $Id$
 */
package com.zj.framework.utils;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class MiscUtil {

    public static String ALL_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String NUMBER = "0123456789";

    public static String NUMBER_AND_LETTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Map<Long, Integer> userStatusMap=new HashMap<Long, Integer>();

    private static Map<Long, Integer> merchantStatusMap=new HashMap<Long, Integer>();

    public static void updateMerchantStatusMap(Integer value, Long key) {
        merchantStatusMap.put(key, value);
    }

    public static void updateUserStatusMap(Integer value, Long key) {
        userStatusMap.put(key, value);
    }

    public static Integer getMerchantStatus(Long id) {
        return merchantStatusMap.get(id);
    }

    public static Integer getUserStatus(Long id) {
        return userStatusMap.get(id);
    }

    /**
     * 随机生产有限位的字符串
     * @param passLenth
     * @return
     */
    public static String getNormalPass(int passLenth) {
        StringBuffer buffer=new StringBuffer(NUMBER_AND_LETTER);
        StringBuffer sb=new StringBuffer();
        Random r=new Random();
        int range=buffer.length();
        for(int i=0; i < passLenth; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 随机生产有限位的数字字符串
     * @param passLenth
     * @return
     */
    public static String getNumPass(int passLenth) {
        StringBuffer buffer=new StringBuffer(NUMBER);
        StringBuffer sb=new StringBuffer();
        Random r=new Random();
        int range=buffer.length();
        for(int i=0; i < passLenth; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 产生指定长度的随机字母字符串
     * @param length
     * @return
     */
    public static String getRandomLetter(int length) {
        if(length < 1) {
            return null;
        } else {
            char[] letters = ALL_LETTER.toCharArray();
            Random randGen = new Random();
            char[] randBuffer = new char[length];
            for(int i = 0; i < randBuffer.length; ++i) {
                randBuffer[i] = letters[randGen.nextInt(letters.length - 1)];
            }
            return new String(randBuffer);
        }
    }

    /**
     * 产生指定长度的随机字符串
     * @param seed 种子字符串
     * @param length
     * @return
     */
    public static String getRandomString(String seed, int length) {
        if(StringUtils.isBlank(seed) || length < 1) {
            return null;
        } else {
            char[] chars = seed.toCharArray();
            Random randGen = new Random();
            char[] randBuffer = new char[length];
            for(int i = 0; i < randBuffer.length; ++i) {
                randBuffer[i] = chars[randGen.nextInt(chars.length - 1)];
            }
            return new String(randBuffer);
        }
    }

    /**
     * 将append字符串随机添加到base中
     * @param base
     * @param append
     * @return
     */
    public static String randomAppend(String base, String append) {
        if(StringUtils.isBlank(base)) {
            return append;
        }
        if(StringUtils.isBlank(append)) {
            return base;
        }
        char[] baseChars = base.toCharArray();
        char[] appendChars = append.toCharArray();
        Random randGen = new Random();
        for(int i = 0; i < appendChars.length; ++i) {
            int index = randGen.nextInt(baseChars.length - 1);
            // 将apend的第i的位置与base的index位置交换
            char swap = appendChars[i];
            appendChars[i] = baseChars[index];
            baseChars[index] = swap;
        }
        String result = new String(baseChars) + new String(appendChars);
        return result;
    }

    /**
     * 四舍五入保留r位小数
     * @param num
     * @param r
     * @return
     */
    public static String roundDouble(Double num, Integer r) {
        if(null == num) {
            return "";
        }
        BigDecimal b=new BigDecimal(num);
        num=b.setScale(r, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num.toString();
    }

    /**
     * 计算增长率
     * @param newNum
     * @param oldNum
     * @return
     */
    public static String getRate(Double newNum, Double oldNum) {
        if(oldNum == 0) {
            return "基数为0.不作统计";
        }

        if(newNum == 0) {
            return "-100.0%";
        }
        return roundDouble((newNum - oldNum) * 100 / oldNum, 2) + "%";
    }

    /**
     * 计算率
     * @param newNum
     * @param oldNum
     * @return
     */
    public static String getRate2(Double newNum, Double oldNum, Integer rate) {
        if(oldNum == 0) {
            return "基数为0.不作统计";
        }
        if(newNum == 0) {
            return "0";
        }

        return roundDouble((newNum / oldNum) * rate, 2) + "";
    }

    public static boolean matchPattern(String str, String regex) {
        if (null == str || null == regex) {
            return false;
        }
        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(str).matches();
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 字符串是否匹配正则表达式中的一项
     * @param str
     * @param regexs 以;分割的正则表达式
     * @return
     */
    public static boolean matchPatterns(String str, String regexs) {
        if (null == str || null == regexs) {
            return false;
        }
        try {
            String[] regexArr = regexs.split(";");
            for (String regex : regexArr) {
                Pattern pattern = Pattern.compile(regex);
                boolean result = pattern.matcher(str).matches();
                if (result) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获得跳转邮件地址
     *
     * @param email
     * @return
     */
    public static String getMailUrl(String email) {
        if (StringUtils.isNotBlank(email) && email.contains("@")) {
            String mail = email.substring(email.indexOf("@") + 1);
            if("gmail.com".equals(mail)){
                return "http://mail.google.com";
            }else if("hotmail.com".equals(mail)){
                return "http://www.hotmail.com";
            }else {
                return "http://mail." + mail;
            }
        }
        return null;
    }

    /**
     * 切分字符串并获取列表
     * @param baseStr 要切分的字符串
     * @param splitTag 切分标记
     * @param type 返回类型（支持Integer、Long、String）
     * @param <T>
     * @return
     */
    public static <T> List<T> getSplitList(String baseStr, String splitTag, Class<T> type) {
        if (StringUtils.isBlank(baseStr) || StringUtils.isBlank(splitTag) || null == type) {
            return null;
        }
        try {
            String[] arrays = baseStr.split(splitTag);
            List<T> list = new ArrayList<T>();
            for (String item : arrays) {
                if (StringUtils.isBlank(item)) {
                    continue;
                }
                if (type == Integer.class) {
                    list.add((T)new Integer(item));
                }
                else if (type == Long.class) {
                    list.add((T)new Long(item));
                }
                else if (type == String.class) {
                    list.add((T)item);
                }
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 填充字符串到指定长度
     *
     * @param baseStr 需要填充的字符串
     * @param length 总长度
     * @param fillChar 填充字符
     * @param isBefore 是否从前面填充
     * @return
     */
    public static String fillWith(String baseStr, int length, char fillChar, boolean isBefore) {
        if (null == baseStr) {
            return null;
        }
        int fillLength = length - baseStr.length();
        if (fillLength <= 0) {
            return baseStr.substring(0, length);
        }
        StringBuilder fillBuf = new StringBuilder();
        for (int i = 0; i < fillLength; i++ ) {
            fillBuf.append(fillChar);
        }
        String fillStr = fillBuf.toString();
        if (isBefore) {
            return fillStr + baseStr;
        } else {
            return baseStr + fillStr;
        }
    }

    public static Integer parseToInt(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long parseToLong(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将版本名转换为版本号，去掉小数点，不超过9位
     * @param versionName
     * @return
     */
    public static int parseToVersionCode(String versionName) {
        if (StringUtils.isBlank(versionName)) {
            return 0;
        }
        try {
            versionName = versionName.replace(".", "");
            if (versionName.length() > 9) {
                versionName = versionName.substring(0, 9);
            }
            return Integer.parseInt(versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 对like查询的字符串进行转义，避免有特殊含义的%和_
     * @param keyword
     * @return
     */
    public static String transferLikeQuery(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return keyword;
        }
        if(keyword.contains("%") || keyword.contains("_")){
            keyword = keyword.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
        }
        return keyword;
    }

    /**
     * 返回字符串值，如果参数为null，指定returnEmpty为true，则返回空字符串，否则返回null
     * @param obj
     * @return
     */
    public static String getStringVal(Object obj, boolean returnEmpty) {
        if (null == obj) {
            return returnEmpty ? "" : null;
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断两个对象是否不一样
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean isDiffrent(Object obj1, Object obj2) {
        if (null == obj1 && null == obj2) {
            return false;
        }
        if ((null == obj1 && null != obj2) || (null != obj1 && null == obj2)) {
            return true;
        }
        return !obj1.equals(obj2);
    }

    public static void printFileInfo(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return;
        }
        System.out.println("----------File Path: " + filePath);
        try {
            // sleep
//            Thread.sleep(1000);

            File f = new File(filePath);
            if (f.exists() && f.isFile()){
                System.out.println("----------File Name: " + f.getName());
                System.out.println("----------File Size: " + f.length());
                System.out.println("----------File canRead: " + f.canRead());
            }else{
                System.out.println("File doesn't exist or is not a file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串截取成指定的长度，如果字符串小于指定的长度，则返回原字符串
     * @param str
     * @param length
     * @return
     */
    public static String substrToLength(String str, int length) {
        if (null == str) {
            return null;
        }
        return str.length() > length ? str.substring(0, length) : str;
    }

    /**
     * 转换为UTF-8编码字符串
     * @return
     */
    public static String parseToUTF8(String word) {
        try {
            if (StringUtils.isNotBlank(word)) {
                String str = new String(word.getBytes("ISO8859-1"), "ISO8859-1");
                if (word.equals(str)) {
                    word = new String(word.getBytes("ISO8859-1"), "UTF-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }

    /**
     * 调用shell脚本
     * @param shellString 命令参数
     * @return
     */
    public static boolean callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 == exitValue) {
                return true;
            } else {
                System.out.println("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将doc文档转换为pdf
     * @param docUrl
     * @return
     */
    public static boolean convertDocToPdf(String docUrl) {
        if (StringUtils.isBlank(docUrl)) {
            return false;
        }
        boolean convertResult = callShell("unoconv -f pdf " + docUrl);
        System.out.println("-----Convert doc to pdf result: |" + " file:" + docUrl + " | result:" + convertResult);
//        System.out.println("-----------current dt2:" + new Date());
        return convertResult;
    }

}
