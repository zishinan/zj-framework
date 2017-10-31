package com.zj.framework.gcode;

import com.zj.framework.excel.ExcelUtil;
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
    /**
     * 模版路径
     */
    private static final String VM_PATH = "E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\vm\\";
    /**
     * 代码定义包名
     */
    private static final String PROJECT_PACKAGE_NAME = "com.ouyang.test";
    /**
     * 生成文项目路径
     */
    private static final String PROJECT_PATH = "E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs";
    /**
     *
     */
    private static final String EXCEL_PATH = "F:\\当乐\\迭代记录\\2017_10_20_客服系统V3_0\\data.xlsx";


    public static void main(String[] args) {
//        获取excel配置
        List<PropTable> sqlTableList = getSqlTables();
//        完善数据
//        生成sql文件
        Map<String,Object> map = new HashedMap();
        map.put("data",sqlTableList);
//        VmUtil.vmToFile("E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs\\","sql.vm",map,"E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs\\init.sql");
//        生成java文件

//        生成mybatis文件
    }

    private static List<PropTable> getSqlTables() {
        List<PropTable> result = new ArrayList<>();
//        理论上不超过100000个表
        for (int i = 0; i < 100000; i++) {
            List<Map<String, Object>> datas = ExcelUtil.getExcel2Maps(EXCEL_PATH,i);
            if(datas == null || datas.size() == 0){
                return result;
            }
            logger.info("data size ==="+datas.size());
            PropTable propTable = new PropTable();
            result.add(propTable);
        }
        return result;
    }


}
