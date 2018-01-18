package com.zj.framework.utils.test;

import com.zj.framework.utils.DigitsTransUtil;
import org.junit.Test;

/**
 * 进制转换测试
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 下午 5:36
 **/
public class DigitsTransUtilTest {
    @Test
    public void test64() throws Exception {
        long curtime = 301812309999999L;
        System.out.println(curtime);
        String hex = DigitsTransUtil.decimal2Hexadecimal(curtime);
        System.out.println(hex);
        long dig = DigitsTransUtil.hexadecimal2Decimal(hex);
        System.out.println(dig);
    }
}
