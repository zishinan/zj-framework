package com.zj.framework.springmvc.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器Bean工具
 * Created by 徐楚风 on 2016/10/14 14:36.
 */
@Component
public class SpringBeanUtil implements BeanFactoryAware {

    /**
     * Spring的beanFactory
     */
    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringBeanUtil.beanFactory = beanFactory;
    }

    /**
     * 通过Spring的beanFactory获取bean
     * @param beanName
     * @param <T>
     * @return
     */
    public static<T> T getBean(String beanName){
        return (T) beanFactory.getBean(beanName);
    }
}
