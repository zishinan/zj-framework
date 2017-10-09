package com.zj.framework.gcode;

import com.zj.framework.gcode.model.PropField;
import com.zj.framework.gcode.model.PropTable;
import com.zj.framework.gcode.type.SqlType;
import com.zj.framework.velocity.VmUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 生成javabean、mybatis配置、sql <br/>
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
//        PropertiesUtil.load("gcode.properties");
//        获取表配置
        List<PropTable> sqlTableList = getSqlTables();
//        生成sql文件
        Map<String,Object> map = new HashedMap();
        map.put("data",sqlTableList);
        VmUtil.vmToFile("E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs\\","sql.vm",map,"E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs\\init.sql");
//        生成java文件

//        生成mybatis文件
    }

    private static List<PropTable> getSqlTables() {
        List<PropTable> result = new ArrayList<>();
//        String excelLocation = PropertiesUtil.getProperty(LOCATION_EXCEL);
        for (int i = 0; i < 9; i++) {
            PropTable sqlTable = new PropTable();
            List<PropField> sqlFields = new ArrayList<>();
            for (int j = 0; j <i; j++) {
                PropField sqlField = new PropField();
                sqlField.setFieldName("testName"+j);
                sqlField.setSqlType(SqlType.DATETIME);
                sqlField.setContent("content test"+j);
                sqlField.setAutoIncrement(true);
                sqlFields.add(sqlField);
            }
            sqlTable.setPropFields(sqlFields);
            sqlTable.setTableName("TEST_TABLE_NAME"+i);
            sqlTable.setPrimaryKey("PRI_KEY"+i);
            result.add(sqlTable);
        }
        return result;
    }


}
