package com.zj.framework.excel.test;

import com.zj.framework.excel.PoiExcel2k3Helper;
import com.zj.framework.excel.PoiExcel2k7Helper;
import com.zj.framework.excel.PoiExcelHelper;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 测试ExcelUtil <br/>
 * @date 2017-09-13 下午 3:18 <br/>
 */
public class ExcelUtilTest {
    @Test
    public void testRead() throws Exception {
        // 获取Excel文件的sheet列表
        testGetSheetList("c:/test.xlsx");

        // 获取Excel文件的第1个sheet的内容
        testReadExcel("c:/test.xls", 0);

        // 获取Excel文件的第2个sheet的第2、4-7行和第10行及以后的内容
        testReadExcel("c:/test.xlsx", 1, "2,4-7,10-");

        // 获取Excel文件的第3个sheet中a,b,g,h,i,j等列的所有内容
        testReadExcel("c:/test.xls", 2, new String[] {"a","b","g","h","i","j"});

        // 获取Excel文件的第4个sheet的第2、4-7行和第10行及以后，a,b,g,h,i,j等列的内容
        testReadExcel("c:/test.xlsx", 3, "2,4-7,10-", new String[] {"a","b","g","h","i","j"});
    }

    // 测试获取sheet列表
    private static void testGetSheetList(String filePath) {
        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 获取Sheet列表
        ArrayList<String> sheets = helper.getSheetList(filePath);

        // 打印Excel的Sheet列表
        printList(filePath, sheets);
    }

    // 测试Excel读取
    private static void testReadExcel(String filePath, int sheetIndex) {
        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 读取excel文件数据
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex);

        // 打印单元格数据
        printBody(dataList);
    }

    // 测试Excel读取
    private static void testReadExcel(String filePath, int sheetIndex, String rows) {
        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 读取excel文件数据
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows);

        // 打印单元格数据
        printBody(dataList);
    }

    // 测试Excel读取
    private static void testReadExcel(String filePath, int sheetIndex, String[] columns) {
        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 读取excel文件数据
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, columns);

        // 打印列标题
        printHeader(columns);

        // 打印单元格数据
        printBody(dataList);
    }

    // 测试Excel读取
    private static void testReadExcel(String filePath, int sheetIndex, String rows, String[] columns) {
        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 读取excel文件数据
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex, rows, columns);

        // 打印列标题
        printHeader(columns);

        // 打印单元格数据
        printBody(dataList);
    }

    // 获取Excel处理类
    private static PoiExcelHelper getPoiExcelHelper(String filePath) {
        PoiExcelHelper helper;
        if(filePath.indexOf(".xlsx")!=-1) {
            helper = new PoiExcel2k7Helper();
        }else {
            helper = new PoiExcel2k3Helper();
        }
        return helper;
    }

    // 打印Excel的Sheet列表
    private static void printList(String filePath, ArrayList<String> sheets) {
        System.out.println();
        for(String sheet : sheets) {
            System.out.println(filePath + " ==> " + sheet);
        }
    }

    // 打印列标题
    private static void printHeader(String[] columns) {
        System.out.println();
        for(String column : columns) {
            System.out.print("\t\t" + column.toUpperCase());
        }
    }

    // 打印单元格数据
    private static void printBody(ArrayList<ArrayList<String>> dataList) {
        int index = 0;
        for(ArrayList<String> data : dataList) {
            index ++;
            System.out.println();
            System.out.print(index);
            for(String v : data) {
                System.out.print("\t\t" + v);
            }
        }
    }
}
