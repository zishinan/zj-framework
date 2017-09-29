package com.zj.framework.excel.usermodel;

import java.util.List;

public class ExcelData {
	private String fileName;
	private String title;
	private String[] headers;
	private List<List<String>> dataSet;
	public ExcelData(String fileName,String title,String[] headers,List<List<String>> dataSet) {
		this.fileName = fileName;
		this.title = title;
		this.headers = headers;
		this.dataSet = dataSet;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getHeaders() {
		return headers;
	}
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public List<List<String>> getDataSet() {
		return dataSet;
	}
	public void setDataSet(List<List<String>> dataSet) {
		this.dataSet = dataSet;
	}
}
