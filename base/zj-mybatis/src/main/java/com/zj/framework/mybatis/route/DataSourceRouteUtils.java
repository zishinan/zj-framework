package com.zj.framework.mybatis.route;

import org.springframework.util.Assert;

/**
 * 动态数据源管理工具类
 */
public class DataSourceRouteUtils {
    //线程本地环境
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
    //设置数据源
    public static void setDataSourceKey(String customerType) {
        Assert.notNull(customerType, "DataSourceKey cannot be null");
        dataSources.set(customerType);
    }
    //获取数据源
    public static String getDataSourceKey() {
        return dataSources.get();
    }
    //清除数据源
    public static void clearDataSourceKey() {
        dataSources.remove();
    }
}
