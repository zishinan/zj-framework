package com.downjoy.app.vipserver.web.util;


import java.util.Properties;

/**
 * @author Mr. Zhaodong<br/>
 * @version V1.0 <br/>
 * @description: JSTL工具类<br/>
 * @date 2016/8/30 16:38 <br/>
 */
public class JSTLFuncs {

    public static Properties MODULE = PropsUtil.loadProps("module.properties");

    /**
     * 获取资源路径.
     *
     * @return
     */
    public static String getResourcePath() {
        return MODULE.getProperty("resource.path");
    }

    /**
     * 获取上下文路径.
     *
     * @return
     */
    public static String getContextPath() {
        return MODULE.getProperty("context.path");
    }

}
