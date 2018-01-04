package com.zj.framework.service;

import com.zj.util.file.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * service启动器
 *
 * @author xi.yang(xi.yang@downjoy.com)
 * @create 2017-12-05 下午 3:03
 **/
public class Provider {
    private static Logger LOGGER = LoggerFactory.getLogger(Provider.class);
    private static final String STOP_TAG = "stop";
    private static volatile boolean running;
    private static ClassPathXmlApplicationContext context;
    private static Lock lock = new ReentrantLock();

    public static void execute(String[] args) {
        LOGGER.info("main.args: "+ Arrays.asList(args));
        PropertiesUtil.loadAll();
        String moduleName = PropertiesUtil.getProperty("");
        lock.lock();
        try {
            context = new ClassPathXmlApplicationContext(new String[]{moduleName+"-service-provider.xml"});
            String[] beans = context.getBeanDefinitionNames();
            for(String bean:beans){
                LOGGER.info(bean);
            }
            LOGGER.info(moduleName+"-service-provider.xml start successful !!!");
            running = true;
        } finally {
            lock.unlock();
        }
        lock.lock();
        try {
            if (running && args.length > 0 && STOP_TAG.equalsIgnoreCase(args[0])) {
                running = false;
            }
        } finally {
            lock.unlock();
        }
        while (running) {
            try {
                Thread.currentThread().join(5000L);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
