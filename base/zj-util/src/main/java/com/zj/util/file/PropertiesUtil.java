package com.zj.util.file;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PropertiesUtil{
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties properties = new Properties();

	static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
			new BasicThreadFactory.Builder().namingPattern("systemPropertiesReloadSchedule").daemon(true).build());

	public PropertiesUtil() {
		loadAll();
		// 每10分钟重载一次配置文件及缓存
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				loadAll();
			}
		},0,1, TimeUnit.DAYS);
	}
	
	public static void loadAll(){
		logger.info("载入系统配置文件成功！");
	}

	public static void load(String propertyName) {
		InputStream fileStream = null;
		try {
			fileStream = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(propertyName);
			properties.load(fileStream);
			logger.info("载入系统配置文件:"+propertyName+"成功！");
		} catch (Exception e) {
			logger.error("载入系统配置文件:"+propertyName+"错误：", e);
		} finally {
			try {
				fileStream.close();
			} catch (Exception e2) {
				logger.error("关闭流错误：", e2);
			}
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String[] args) {
		String result = properties.getProperty(key);
		if(null == result){
			return null;
		}
		if(args != null && args.length > 0){
			for (int i = 0; i < args.length; i++) {
				result = result.replace("${"+i+"}", args[i]);
			}
		}
		return result;
	}
	
	public static String getProperty(String key,String defaultvalue) {
		return properties.getProperty(key,defaultvalue);
	}
}
