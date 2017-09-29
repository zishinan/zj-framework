package com.zj.util.file;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class PathUtil {

	public static String getWebProjectPath() {
		String classPath = getClassPath();
		String webInfUrl = classPath.substring(0, classPath.indexOf("classes"));
		String WebProjectPath = webInfUrl.substring(0, classPath.indexOf("WEB-INF"));
		return WebProjectPath;
	}

	public static String getClassPath() {
		String classPath = getClassPath(PathUtil.class);
		String classPathUrl = null;

		try {
			if (classPath.endsWith(".jar")) {
				classPathUrl = classPath.substring(0, classPath.indexOf("lib"));
			} else {
				classPathUrl = classPath.substring(0, classPath.indexOf("classes"));
			}
		} catch (Exception e) {
			File file = new File("");
			classPathUrl = file.getAbsolutePath() + "/webapp/WEB-INF/";
		}
		classPathUrl += "classes/";
		return classPathUrl;
	}

	/**
	 * 方法描述：根据class获取web应用的路径绝对路径
	 * 
	 * @param c
	 *            用来定位的类
	 * @return ClassPath 绝对路径
	 */
	private static String getClassPath(Class<?> c) {
		ProtectionDomain pd = c.getProtectionDomain();
		if (pd == null) {
			return null;
		}
		CodeSource cs = pd.getCodeSource();

		if (cs == null) {
			return null;
		}

		URL url = cs.getLocation();
		if (url == null) {
			return null;
		}
		String classPath = url.getPath();
		return classPath;
	}

}
