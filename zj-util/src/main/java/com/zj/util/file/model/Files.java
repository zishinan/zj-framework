package com.zj.util.file.model;

import java.util.List;

public class Files {
	public static final int TYPE_FILE = 0;
	public static final int TYPE_DIR = 1;
	
	private int type;//文件类型
	private String name;//名称
	private String path;//路径
	private List<Files> files;//type为TYPE_DIR时的子文件
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Files> getFiles() {
		return files;
	}
	public void setFiles(List<Files> files) {
		this.files = files;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
