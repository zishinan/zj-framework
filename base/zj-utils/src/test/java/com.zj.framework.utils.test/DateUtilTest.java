package com.zj.framework.utils.test;

import com.zj.framework.baseto.type.DatePattern;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * 测试DateUtil
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 下午 2:42
 **/
public class DateUtilTest {
    @Test
    public void testAddDays() throws Exception {
        Date date = new Date();
        System.out.println(date + " ====" + date.getTime());
        Date date1 = DateUtils.addDays(date,1);
        System.out.println(date1 + " ====" + date1.getTime());
        Date date2 = DateUtils.addDays(date,-1);
        System.out.println(date2 + " ====" + date2.getTime());

        String strs = DateFormatUtils.format(date, DatePattern.yyyyMMdd_mini.getPattern());
        System.out.println(strs);
    }
}
