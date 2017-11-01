package com.zj.framework.excel;

import com.zj.framework.excel.usermodel.ExcelData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
	public static final String SEPARATOR = ",";
	public static final String CONNECTOR = "-";

	/**
	 * @Description 读取excel文件
	 * @param filePath 文件路径
	 * @param sheetIndex 第几个表，从0开始
	 * @return
	 */
	public static List<Map<String, Object>> getExcel2Maps(String filePath,int sheetIndex){
		InputStream inputStream = null;
		try {
			File file = new File(filePath);
			System.out.println(file.getAbsolutePath());
			inputStream = new FileInputStream(file);
			Sheet sheet = getSheet(filePath, inputStream, sheetIndex);
			ArrayList<ArrayList<String>> dataList  = readExcel(sheet, "1-", getColumnNumber(sheet, "1-"));

			List<Map<String, Object>> result = new ArrayList<>();
			if(dataList == null || dataList.size() <= 0){
				return result;
			}
			List<String> keys = dataList.get(0);
			for (int i = 1; i < dataList.size(); i++) {
				Map<String, Object> map = new HashMap<>();
				if(dataList.get(i).size() < keys.size()){
					LOGGER.error("数据长度和title不匹配");
					return null;
				}
				for (int j = 0; j < keys.size(); j++) {
					String key = keys.get(j);
					if(null == key){
						LOGGER.error("title 为空了");
						return null;
					}
					String value = dataList.get(i).get(j);
//				if(StringUtil.isNotBlank(value) && StringUtil.isNotBlank(value.trim())){
//					allNull = false;
//				}
					map.put(key.trim(), value.trim());
				}
				result.add(map);
			}
			return result;
		} catch (Exception e) {
			LOGGER.error("error to get inputStream");
		}
		return null;
	}

	/**
	 * 获取所有的表，限制表格数100000
	 * @param filePath
	 * @return
	 */
	public static Map<String,List<Map<String, Object>>> getAllExcel2Maps(String filePath){
		Map<String,List<Map<String, Object>>> result = new HashMap<>();
		try {
			for (int i = 0; i < 100000; i++) {
				File file = new File(filePath);
				System.out.println(file.getAbsolutePath());
				InputStream inputStream = new FileInputStream(file);
				Sheet sheet = getSheet(filePath, inputStream, i);
				if(null == sheet){
					return result;
				}
				String tabelName = sheet.getSheetName();
				if(tabelName == null){
					return result;
				}
				List<Map<String, Object>> dataList  = getExcel2Maps(filePath,i);
				result.put(tabelName,dataList);
			}
			return result;
		} catch (Exception e) {
			LOGGER.error("error to get inputStream");
		}
		return null;
	}
	
	// 获取Excel处理类
	private static Sheet getSheet(String filePath,InputStream inputStream,int sheetIndex) {
		try {
			if (filePath.indexOf(".xlsx") != -1) {
				XSSFWorkbook wb = new XSSFWorkbook(inputStream);
				return wb.getSheetAt(sheetIndex);
			} else {
				HSSFWorkbook wb = new HSSFWorkbook(inputStream);
				return wb.getSheetAt(sheetIndex);
			}
		} catch (Exception e) {
			LOGGER.error("获取表错误：{}",filePath+"=="+sheetIndex);
		}
		return null;
	}
	
	/** 读取Excel文件内容 */
	protected static ArrayList<ArrayList<String>> readExcel(Sheet sheet, String rows, int[] cols) {
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>> ();
		// 处理行信息，并逐行列块读取数据
		String[] rowList = rows.split(SEPARATOR);
		for (String rowStr : rowList) {
			if (rowStr.contains(CONNECTOR)) {
				String[] rowArr = rowStr.trim().split(CONNECTOR);
				int start = Integer.parseInt(rowArr[0]) - 1;
				int end;
				if (rowArr.length == 1) {
					end = sheet.getLastRowNum();
				} else {
					end = Integer.parseInt(rowArr[1].trim()) - 1;
				}
				dataList.addAll(getRowsValue(sheet, start, end, cols));
			} else {
				dataList.add(getRowValue(sheet, Integer.parseInt(rowStr) - 1, cols));
			}
		}
		return dataList;
	}
	
	
	
	/**
	 * Change excel column string to integer number array
	 * 
	 * @param sheet
	 *            excel sheet
	 * @param columns
	 *            column letter of excel file, like A,B,AA,AB
	 * @return
	 */
	protected static int[] getColumnNumber(Sheet sheet, String columns) {
		// 拆分后的列为动态，采用List暂存
		ArrayList<Integer> result = new ArrayList<Integer> ();
		String[] colList = columns.split(SEPARATOR);
		for(String colStr : colList){
			if(colStr.contains(CONNECTOR)){
				String[] colArr = colStr.trim().split(CONNECTOR);
				int start = Integer.parseInt(colArr[0]) - 1;
				int end;
				if(colArr.length == 1){
					end = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() - 1;
				}else{
					end = Integer.parseInt(colArr[1].trim()) - 1;
				}
				for(int i=start; i<=end; i++) {
					result.add(i);
				}
			}else{
				result.add(Integer.parseInt(colStr) - 1);
			}
		}
		
		// 将List转换为数组
		int len = result.size();
		int[] cols = new int[len]; 
		for(int i = 0; i<len; i++) {
			cols[i] = result.get(i).intValue();
		}

		return cols;
	}
	
	/** 获取连续行、不连续列数据 */
	private static ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int[] cols) {
		if (endRow < startRow) {
			return null;
		}
		
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		for (int i = startRow; i <= endRow; i++) {
			data.add(getRowValue(sheet, i, cols));
		}
		return data;
	}
	
	/** 获取行连续列数据 */
	private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int startCol, int endCol) {
		if(endCol < startCol) {
			return null;
		}
		
		Row row = sheet.getRow(rowIndex);
		ArrayList<String> rowData = new ArrayList<String>();
		for (int i = startCol; i <= endCol; i++) {
			rowData.add(getCellValue(row, i));
		}
		return rowData;
	}
	
	/** 获取行不连续列数据 */
	private static ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int[] cols) {
		Row row = sheet.getRow(rowIndex);
		ArrayList<String> rowData = new ArrayList<String>();
		for (int colIndex : cols) {
			rowData.add(getCellValue(row, colIndex));
		}
		return rowData;
	}
	
	/**
	 * 获取单元格内容
	 * 
	 * @param row
	 * @param col
	 *            a excel column index from 0 to 65535
	 * @return
	 */
	private static String getCellValue(Row row, int col) {
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(col);
		return getCellValue(cell);
	}

	/**
	 * 获取单元格内容
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		String value = cell.toString().trim();
		try {
			// This step is used to prevent Integer string being output with
			// '.0'.
			Float.parseFloat(value);
			value=value.replaceAll("\\.0$", "");
			value=value.replaceAll("\\.0+$", "");
			return value;
		} catch (NumberFormatException ex) {
			return value;
		}
	}
	
	/**
	 * 导出excel文件
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @throws IOException
	 */
	public static void poiExportExcel(ExcelData excelData) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(excelData.getTitle());
		
		//标题行
		Row row = sheet.createRow(0);
		for (int i = 0; i < excelData.getHeaders().length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(excelData.getHeaders()[i]);
		}
		//数据
		int rownum = 0;
		for (List<String> data : excelData.getDataSet()) {
			rownum ++;
			Row dataRow = sheet.createRow(rownum);
			for (int i = 0; i < data.size(); i++) {
				Cell cell = dataRow.createCell(i);
				cell.setCellValue(data.get(i));
			}
		}
		OutputStream outputStream = new FileOutputStream(excelData.getFileName());
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}
}
