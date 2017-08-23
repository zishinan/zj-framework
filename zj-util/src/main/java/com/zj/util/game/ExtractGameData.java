package com.zj.util.game;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class ExtractGameData{
	public static String extractData(String gameData) {
		return dateLoad(gameData);
	}
	
	@SuppressWarnings("finally")
	public static String dateLoad(String loadString){
		if (loadString.startsWith(versionUpdateKey())) {
			String parseString = loadString
					.replaceFirst(versionUpdateKey(), "");
			String result = "解压失败";
			try {
				result = uncompress(decodeBase64(parseString));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				return result;
			}
		} else {
			return loadString;
		}
	}
	
	private static String Version = "20121019";

	public static String versionUpdateKey() {
		return "#" + Version + "#";
	}
	
	public static String decodeBase64(String b64string) throws Exception {
		return new String(Base64.decodeBase64(b64string.getBytes()), "utf-8");
	}
	
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(
				str.getBytes("ISO-8859-1"));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		// toString()使用平台默认编码，也可以显式的指定如toString(\"GBK\")
		return out.toString("UTF-8");
	}

}
