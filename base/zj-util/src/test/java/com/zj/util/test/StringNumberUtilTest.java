package com.zj.util.test;

import com.zj.util.string.StringNumberUtil;
import org.junit.Test;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 字符串和数字转换工具测试 <br/>
 * @date 2017-10-22 下午 2:17 <br/>
 */
public class StringNumberUtilTest {
    @Test
    public void testToChinese() throws Exception {
        System.out.println(StringNumberUtil.toChinese("123"));
    }
}
