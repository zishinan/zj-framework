package com.zj.framework.gcode;

import com.zj.framework.baseto.type.DatePattern;
import com.zj.framework.excel.ExcelUtil;
import com.zj.framework.fastjson.JsonUtil;
import com.zj.framework.gcode.model.PropField;
import com.zj.framework.gcode.model.PropTable;
import com.zj.framework.gcode.type.FieldType;
import com.zj.framework.gcode.type.SqlType;
import com.zj.framework.velocity.VmUtil;
import com.zj.util.date.DateUtils;
import com.zj.util.string.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String PROJECT_PACKAGE_NAME = "com.downjoy.customercenter.service";
    /**
     * 生成文项目路径
     */
    private static final String PROJECT_PATH = "E:\\myworkspace\\zj-framework\\projects\\zj-gcode\\src\\main\\resources\\docs\\";
    /**
     *
     */
    private static final String EXCEL_PATH = "F:\\当乐\\迭代记录\\2017_10_20_客服系统V3_0\\data.xlsx";


    public static void main(String[] args) {
//        获取excel配置
        List<PropTable> sqlTableList = getSqlTables();
        System.out.printf("sq==========="+ JsonUtil.toJsonString(sqlTableList));
//        完善数据
        compeleteDatas(sqlTableList);
//        生成sql文件
        Map<String,Object> map = new HashedMap();
        map.put("data",sqlTableList);
        VmUtil.vmToFile(VM_PATH,"sql.vm",map,PROJECT_PATH+"init.sql");
//        生成java文件
        for (PropTable propTable : sqlTableList) {
            Map<String,Object> tempMap = new HashedMap();
            tempMap.put("data",propTable);
            VmUtil.vmToFile(VM_PATH,"javabean.vm",tempMap,PROJECT_PATH+"to\\"+propTable.getToName()+".java");
            VmUtil.vmToFile(VM_PATH,"dao.vm",tempMap,PROJECT_PATH+"mapper\\"+propTable.getToName()+"Mapper.java");
            VmUtil.vmToFile(VM_PATH,"mybatis.vm",tempMap,PROJECT_PATH+"xml\\"+propTable.getToName()+"Mapper.xml");
            VmUtil.vmToFile(VM_PATH,"service.vm",tempMap,PROJECT_PATH+"service\\"+propTable.getToName()+"Service.java");
            VmUtil.vmToFile(VM_PATH,"serviceimpl.vm",tempMap,PROJECT_PATH+"serviceimpl\\"+propTable.getToName()+"ServiceImpl.java");
        }

//        生成mybatis文件
    }

    /**
     * 完善数据
     * @param sqlTableList
     */
    private static void compeleteDatas(List<PropTable> sqlTableList) {
        String createTime = DateUtils.formatDate(new Date(), DatePattern.yyyyMMdd_HHmmss);
        for (PropTable propTable : sqlTableList) {
            propTable.setPackageName(PROJECT_PACKAGE_NAME);
            propTable.setCreateTime(createTime);
            propTable.setToName(underline2Camel(propTable.getTableName(),false));
            propTable.setLowerToName(underline2Camel(propTable.getTableName(),true));
            List<PropField> propFields = propTable.getPropFields();
            for (PropField propField : propFields) {
                String fieldName = underline2Camel(propField.getSqlFieldName(),true);
                propField.setFieldName(fieldName);
                propField.setFirstUpFieldName(underline2Camel(propField.getSqlFieldName(),false));
                processFieldType(propField);
                if(propField.isPrimary()){
                    propTable.setPrimaryField(propField);
                }
            }
        }
    }

    private static void processFieldType(PropField propField) {
        if(FieldType.ID_ENUM.equals(propField.getFieldType()) || FieldType.JAVA_BEAN.equals(propField.getFieldType())){
            propField.setFieldTypeString(propField.getFirstUpFieldName());
            return;
        }
        if(propField.getFieldType() == null){
            propField.setFieldType(propField.getSqlType().getFieldType());
            if(FieldType.BOOLEAN.equals(propField.getFieldType())){
                propField.setGetMethodTarget("is");
            }
            propField.setFieldTypeString(underline2Camel(propField.getFieldType().toString(),false));
            return;
        }

    }

    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    private static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    private static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }

    /**
     * 从excel获取数据
     * @return
     */
    private static List<PropTable> getSqlTables() {
        List<PropTable> result = new ArrayList<>();
        Map<String,List<Map<String, Object>>> maps = ExcelUtil.getAllExcel2Maps(EXCEL_PATH);
        for (String sheetName : maps.keySet()) {
            String tabelContent = StringUtils.substring(sheetName,0,sheetName.indexOf("_"));
            String tabelName = StringUtils.substring(sheetName,sheetName.indexOf("_")+1,sheetName.length());
            PropTable propTable = new PropTable();
            propTable.setTableName(tabelName);
            propTable.setContent(tabelContent);
            List<Map<String,Object>> props = maps.get(sheetName);
            List<PropField> list = new ArrayList<>();
            for (Map<String, Object> prop : props) {
                PropField propField = new PropField();
                String name = String.valueOf(prop.get("字段名"));
                propField.setSqlFieldName(name);
                String type = String.valueOf(prop.get("类型"));
                propField.setSqlType(SqlType.getSqlTypeByName(type));
                int length = getIntDefault(prop.get("长度"));
                propField.setFieldLength(length);
                boolean allowNull = getBooleanDeDefault(prop.get("允许空"));
                propField.setAllowNull(allowNull);
                String defaultVal = String.valueOf(prop.get("默认值"));
                if(StringUtils.isNotBlank(defaultVal)){
                    propField.setDefaultValue(defaultVal);
                }
                String indexType = String.valueOf(prop.get("索引类型"));
                if(StringUtil.isNotWebBlank(indexType)){
                    if(indexType.contains("主键")){
                        propField.setPrimary(true);
                        propTable.setPrimaryKey(name);
                    }
                    if(indexType.contains("自增")){
                        propField.setAutoIncrement(true);
                    }
                    if(indexType.contains("索引")){
                        propField.setIndex(true);
                    }
                }
                String content = String.valueOf(prop.get("说明"));
                propField.setContent(content);
                String javaType = String.valueOf(prop.get("Java类型"));
                propField.setFieldType(FieldType.getFieldTypeByName(javaType));
                list.add(propField);
            }
            propTable.setPropFields(list);
            result.add(propTable);
        }
        return result;
    }

    private static int getIntDefault(Object obj) {
        if(null == obj){
            return 0;
        }
        try {
            return Integer.parseInt(obj+"");
        }catch (Exception e){
            return 0;
        }
    }
    private static boolean getBooleanDeDefault(Object obj) {
        if(null == obj){
            return false;
        }
        if(StringUtil.isWebBlank(obj+"")){
            return false;
        }
        if("0".equals(obj+"")){
            return false;
        }
        return true;
    }


}
