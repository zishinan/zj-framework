package com.zj.util.file;

import com.zj.util.date.DateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class PropertiesUtil extends TimerTask{
	private static final Logger logger = LogManager
			.getLogger(PropertiesUtil.class);
	private static Properties properties = new Properties();

	private Timer systemConfigTimer = new Timer("SystemConfigTimer");
	
	public PropertiesUtil() {
		loadAll();
		// 每10分钟重载一次配置文件及缓存
		systemConfigTimer.schedule(this, DateUtil.DAY_LONG, DateUtil.DAY_LONG);
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

	@Override
	public void run() {
		loadAll();
	}
}
