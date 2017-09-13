package com.zj.util.collection;

import com.zj.util.file.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MapUtil {
	private static final Logger logger = LoggerFactory.getLogger(MapUtil.class);
	private MapUtil() {
		throw new Error("不要实例化!");
	}
	public static void main(String[] args) {
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
	}
	public static void listMap2Log(List<Map<String, String>> result,String fileName) {
		if(result.size() == 0){
			return;
		}
		StringBuilder sb = new StringBuilder();
		Set<String> keys = result.get(0).keySet();
		for (String key : keys) {
			sb.append(key + "\t");
		}
		sb.append("\n");
		for (Map<String, String> map : result) {
			for (String key : keys) {
				sb.append(MapUtil.getMapString(map, key) + "\t");
			}
			sb.append("\n");
		}
		LogUtil.logToFile(fileName, sb.toString());
	}
	
	public static void mapIntIncrement(Map<String, String> map,String key,String increm){
		int add = 0;
		try {
			add = Integer.parseInt(increm);
		} catch (Exception e) {
			logger.error("转换类型出错:" + increm);
			return;
		}
		mapIntIncrement(map, key, add);
	}
	
	public static void mapLongIncrement(Map<String, String> map,String key,String increm){
		long add = 0;
		try {
			add = Long.parseLong(increm);
		} catch (Exception e) {
			logger.error("转换类型出错:" + increm);
			return;
		}
		mapLongIncrement(map, key, add);
	}
	
	public static void mapIntIncrement(Map<String, String> map,String key,int increm){
		if(map == null){
			return;
		}
		if(!map.containsKey(key)){
			map.put(key, increm + "");
			return;
		}
		int old = 0;
		String oldString = map.get(key);
		try {
			old = Integer.parseInt(oldString);
		} catch (Exception e) {
			logger.error("转换类型出错:" + oldString);
			return;
		}
		map.put(key, (old + increm) + "");
	}
	
	public static void mapLongIncrement(Map<String, String> map,String key,long increm){
		if(map == null){
			return;
		}
		if(!map.containsKey(key)){
			map.put(key, increm + "");
			return;
		}
		long old = 0L;
		String oldString = map.get(key);
		try {
			old = Long.parseLong(oldString);
		} catch (Exception e) {
			logger.error("转换类型出错:" + oldString);
			return;
		}
		map.put(key, (old + increm) + "");
	}
	
	public static int getMapInt(Map<String,String> map,String key,int def){
		if(map == null){
			return 0;
		}
		int result = def;
		if(map.containsKey(key)){
			try {
				result = Integer.parseInt(map.get(key));
			} catch (Exception e) {
				logger.error("转换类型出错:" + map.get(key));
			}
		}
		return result;
	}
	
	public static long getMapLong(Map<String,String> map,String key,long def){
		if(map == null){
			return 0;
		}
		long result = def;
		if(map.containsKey(key)){
			try {
				result = Long.parseLong(map.get(key));
			} catch (Exception e) {
				logger.error("转换类型出错:" + map.get(key));
			}
		}
		return result;
	}
	
	public static String getMapString(Map<String,String> map,String key,String def){
		if(map == null){
			return "";
		}
		String result = def;
		if(map.containsKey(key)){
			result = map.get(key);
		}
		return result;
	}
	
	public static String getMapString(Map<String,String> map,String key){
		return getMapString(map, key, "");
	}
	
	public static long getMapLong(Map<String,String> map,String key){
		return getMapLong(map, key, 0);
	}
	
	public static int getMapInt(Map<String,String> map,String key){
		return getMapInt(map, key, 0);
	}
	
	public static void mapIntIncrement(
			Map<String, Map<String, String>> roleLevelMap, String roleId,
			String stageLevel,int num) {
		Map<String,String> oldMap = roleLevelMap.get(roleId);
		if(oldMap == null){
			oldMap = new HashMap<String, String>();
		}
		MapUtil.mapIntIncrement(oldMap, stageLevel, num);
		roleLevelMap.put(roleId, oldMap);
	}
}
