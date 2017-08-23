package com.zj.util.file;

import com.owtelse.codec.Base64;
import org.apache.log4j.Logger;

import java.io.*;

public class Base64J2Pic {
	private static final Logger logger = Logger.getLogger(Base64J2Pic.class);
	public static void main(String[] args) {
		String path = "C:\\Users\\develop1\\Desktop\\222.gif";
		String imgStr = getImgString(path);
		System.out.println(imgStr);
//		String filePath = "C:\\Users\\Administrator\\Desktop\\image\\ddd.gif";
//		setImgFromString(imgStr,filePath);
	}

	public static boolean setImgFromString(String imgStr, String filePath) {
		boolean result = false;
		OutputStream os = null;
		try {
			byte[] imgByte = AbBase64.decode(imgStr);
			for (byte b : imgByte) {
				if(b < 0){
					b += 256;
				}
			}
			os = new FileOutputStream(filePath);
			os.write(imgByte);
			os.flush();
			result = true;
		} catch (Exception e) {
			logger.error("Set file error");
		}finally{
			closeOutputStream(os);
		}
		return result;
	}

	private static void closeOutputStream(OutputStream os) {
		try {
			os.close();
		} catch (IOException e) {
			logger.error("Close outputStream error!");
		}
	}

	public static String getImgString(String path) {
		InputStream is = null;
		byte[] data = null;
		try {
			is = new FileInputStream(path);
			data = new byte[is.available()];
			is.read(data);
			return Base64.encode(data);
		} catch (Exception e) {
			logger.error("Get file error");
		}finally{
			closeInputStream(is);
		}
		return null;
	}

	
	private static void closeInputStream(InputStream is) {
		try {
			if(null != is){
				is.close();
			}
		} catch (IOException e) {
			logger.error("Close inputstream error!");
		}
	}
}
