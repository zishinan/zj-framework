package com.zj.util.test;

import com.zj.util.date.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 时间测试 <br/>
 * @date 2017-10-22 下午 4:40 <br/>
 */
public class DateUtilTest {
    @Test
    public void testDiffDay() throws Exception {
        Date start = new Date(DateUtils.covertString2Long("2017-10-20 10:22:22"));
        Date end = new Date(DateUtils.covertString2Long("2017-10-21 00:00:01"));
        System.out.println(DateUtils.diffDays(start,end));
    }
}
