package com.zj.framework.mybatis.route;

import java.lang.annotation.*;

/**
 * 数据源选择注释
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSource {
    String name() default DataSource.master;
    //写库类型
    public static final String master = "master";
    //读库类型
    public static final String slave = "slave";
}
