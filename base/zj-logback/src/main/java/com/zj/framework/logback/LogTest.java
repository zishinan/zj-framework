package com.zj.framework.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试log
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2018-01-18 上午 10:16
 **/
public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        LOGGER.debug("==============debug");
        LOGGER.info("==============info");
        LOGGER.warn("==============warn");
        LOGGER.error("==============error");
        LOGGER.trace("==============trace");
    }

}
