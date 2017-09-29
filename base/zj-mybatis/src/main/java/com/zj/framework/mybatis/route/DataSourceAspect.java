package com.zj.framework.mybatis.route;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 拦截切面类
 */
@Component
@Aspect
@Order(0)
public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Resource
    private DataSourceKey dataSourceKey;

    // 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.zj.framework..*.*(..))")
    public void aspect() {
    }

    /**
     * 在dao层方法之前获取datasource对象之前在切面中指定当前线程数据源路由的key
     */
    @Before("aspect()")
    public void doBefore(JoinPoint point) {
        //dao方法上配置的注解
        DataSource datasource = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(DataSource.class);
        if (datasource != null) {
            String sourceKey = DataSource.master;
            if (datasource.name().equals(DataSource.master)) {
                sourceKey = dataSourceKey.getMaster();
            } else if (datasource.name().equals(DataSource.slave)) {
                sourceKey = dataSourceKey.getSlave();
            }
            DataSourceRouteUtils.setDataSourceKey(sourceKey);
            logger.info("Switch dataSource = " + sourceKey);
        }
    }

    @After("aspect()")
    public void doAfter(JoinPoint point) {
        DataSourceRouteUtils.clearDataSourceKey();
    }
}
