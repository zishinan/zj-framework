package com.zj.util.file;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;

public class LogUtil {
	private static final Logger logger = LogManager.getLogger(LogUtil.class);
	private LogUtil() {
		throw new Error("不要实例化!");
	}
	/**
	 * 输入日志到文件
	 * @param filename 文件名
	 * @param txt 日志字符串
	 */
	public static void logToFile(String filename, String txt) {
		byte[] buff = new byte[] {};
		try {
			buff = txt.getBytes();
			FileOutputStream out = new FileOutputStream(filename);
			out.write(buff, 0, buff.length);
			out.close();
		} catch (Exception e) {
			logger.error("输出文件出错!");
		}
	}
	
	/*public static void logError(String message){
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		System.err.println("error=====file:" + ste.getFileName() + "; method:" + ste.getMethodName() + "; line:" + ste.getLineNumber() + "; message:" + message);
	}*/
}
