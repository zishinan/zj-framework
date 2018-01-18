package com.zj.framework.utils;

/**
 * 进制转换工具
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 下午 4:43
 **/
public class DigitsTransUtil {
    /**
     * 定制64位符号集，避开url保留符号和安全符号
     */
    public static final char[] digits = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9',
            '_','-',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    /**
     * 十进制转64进制
     * @param number
     * @return
     */
    public static String decimal2Hexadecimal(long number) {
        char[] buf = new char[64];
        int charPos = 64;
        long mask = 63L;
        do {
//            并集为下标
            buf[--charPos] = digits[(int) (number & mask)];
//            无符号右移
            number >>>= 6;
        } while (number != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    /**
     * 64进制转十进制
     * @param decompStr
     * @return
     */
    public static long hexadecimal2Decimal(String decompStr) {
        long result = 0;
        for (int i = decompStr.length() - 1; i >= 0; i--) {
            for (int j = 0; j < digits.length; j++) {
                if (decompStr.charAt(i) == digits[j]) {
                    result += ((long) j) << 6 * (decompStr.length() - 1 - i);
                }
            }
        }
        return result;
    }
}
