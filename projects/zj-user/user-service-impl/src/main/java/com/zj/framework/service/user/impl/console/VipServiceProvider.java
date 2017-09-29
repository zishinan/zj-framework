package com.zj.framework.service.user.impl.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class VipServiceProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(VipServiceProvider.class);

    private static volatile boolean running;
    private static ClassPathXmlApplicationContext context;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LOGGER.info("main.args: "+ Arrays.asList(args));
        lock.lock();
        try {
            context = new ClassPathXmlApplicationContext(new String[]{"vip-service-provider.xml"});
            String[] beans = context.getBeanDefinitionNames();
            for(String bean:beans){
                LOGGER.info(bean);
            }
            LOGGER.info("vip-service-provider.xml start successful !!!");
            running = true;
        } finally {
            lock.unlock();
        }
        lock.lock();
        try {
            if (running && args.length > 0 && "stop".equalsIgnoreCase(args[0])) {
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
