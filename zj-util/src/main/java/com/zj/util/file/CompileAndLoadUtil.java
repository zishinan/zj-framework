package com.zj.util.file;

import org.apache.log4j.Logger;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


public class CompileAndLoadUtil {
	
	private static Logger logger = Logger.getLogger(CompileAndLoadUtil.class);
	
	private static String classpath;
	
	private static String javaFilePath;
	
	private static URL[] urLs;
	static{
		StringBuilder sb = new StringBuilder();
		URLClassLoader classloader = (URLClassLoader)CompileAndLoadUtil.class.getClassLoader();
		urLs = classloader.getURLs();
		for(URL url : urLs){
			String path = url.getFile();
			sb.append(path).append(File.pathSeparator);
		}
		classpath = sb.toString();
		javaFilePath = CompileAndLoadUtil.class.getClassLoader().getResource("").getPath();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Class<?> compileAndGetClass(String name){
//		try {
//			Class<?> c = Class.forName(name);
//			if(c != null){
//				return c;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			String fileName = javaFilePath + name.replace('.', '/') + ".java";
			File file = new File(fileName);
			if(!file.exists()){
//				throw new Exception("java文件不存在");
				//文件不存在时   已经编译过   跳过编译直接返回class
				logger.error("没有找到java文件:"+fileName+"\t跳过编译直接返回class");
				return  Class.forName(name);
			}
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
			Iterable sourcefiles = fileManager.getJavaFileObjects(fileName);
			List<String> options = new ArrayList<String>();
			options.add("-encoding");
			options.add("UTF-8");
			options.add("-classpath");
			options.add(classpath);
			CompilationTask task = compiler.getTask(null, fileManager, null, options, null, sourcefiles);
			boolean success = task.call();
			fileManager.close();
			if(success){
//				return Class.forName(name);
				return getClass(name);
			}
		} catch (Exception e) {
			logger.error("动态编译、加载类错误：",e);
		}
		return null;
	}
	public static Class<?> getClass( String name)
	{
			try {
				return new HotClassLoader(urLs,  CompileAndLoadUtil.class.getClassLoader()).loadClass(name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}		
	}
}
