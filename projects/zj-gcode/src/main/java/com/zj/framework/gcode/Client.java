package com.zj.framework.gcode;

import com.zj.framework.excel.ExcelUtil;
import com.zj.framework.fastjson.JsonUtil;
import com.zj.util.file.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: velocity使用 <br/>
 * @date 2017-09-27 上午 9:33 <br/>
 */
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String LOCATION_EXCEL = "location.excel";
    private static final String GCODE_LOCATION_SQL = "gcode.location.sql";
    private static final String GCODE_LOCATION_JAVA = "gcode.location.java";
    private static final String GCODE_LOCATION_MYBATIS = "gcode.location.mybatis";
    private static final String GCODE_JAVA_PACKAGE = "gcode.java.package";


    private static final String CLASSPATH = "classpath";
    private static final String CLASSPATH_CLASS = "classpath.resource.loader.class";
    public static void main(String[] args) {
//        获取项目配置
        PropertiesUtil.load("gcode.properties");
        String excelLocation = PropertiesUtil.getProperty(LOCATION_EXCEL);
        logger.info("===="+excelLocation);
//        获取Excel配置
        List<Map<String, Object>> tables = ExcelUtil.getExcel2Maps(excelLocation,0);
        logger.info("===="+JsonUtil.toJsonString(tables));
//        生成sql文件
//        生成java文件
//        生成mybatis文件
    }

}
