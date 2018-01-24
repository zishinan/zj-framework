package com.zj.framework.utils;


import com.zj.framework.baseto.to.SortKey;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 集合工具类
 */
public class CollectionsUtil {
	/**
	 * 根据map中多个key排序List<Map>
	 * @param result
	 * @param sortKeys
	 */
	public static void sortMaps(final List<Map<String, Object>> result, final List<SortKey> sortKeys){
		Collections.sort(result, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				for(SortKey key : sortKeys){
					Object v1 = o1.get(key.getName());
					Object v2 = o2.get(key.getName());
					int diff = getIntDiffValue(v1,v2);
					if(0 == diff){
						continue;
					}
					switch (key.getSort()){
						case ASC:
							return diff;
						case DESC:
							return -diff;
					}
				}
				return 0;
			}
		});
	}

	/**
	 * 对对象集合排序
	 * @param result
	 * @param sortKeys
	 * @param <T>
	 */
	public static <T> void sortBeans(List<T> result,final List<SortKey> sortKeys){
		Collections.sort(result, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				Class clazz=o1.getClass();
				for(SortKey key : sortKeys){
					Method method=
							null;
					int diff = 0;
					try {
						method = clazz.getMethod("get" + key.getName().substring(0, 1).toUpperCase() + key.getName().substring(1), new Class[]{});
						Object v1=method.invoke(o1);
						Object v2=method.invoke(o2);
						diff = getIntDiffValue(v1,v2);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if(0 == diff){
						continue;
					}
					switch (key.getSort()){
						case ASC:
							return diff > 0 ? 1:-1;
						case DESC:
							return diff > 0 ? -1:1;
					}
				}
				return 0;
			}
		});
	}

	/**
	 * 判断一个集合是否有数据
	 * @param list
	 * @return 非空并且长度大于0返回true
	 */
	public static boolean isNotEmpty(Collection list){
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}

	private static int getIntDiffValue(Object v1, Object v2) {
		if(v1 instanceof String) {
			if(0 == (((String)v1).compareTo((String)v2))){
				return 0;
			}
			return ((String)v1).compareTo((String)v2) > 0 ? 1 : -1;
		}

		if(v1 instanceof Integer) {
			if(0 == ((Integer)v1 - (Integer)v2)){
				return 0;
			}
			return (Integer)v1 - (Integer)v2 > 0 ? 1 : -1;
		}

		if(v1 instanceof Long) {
			if(0 == ((Long)v1 - (Long)v2)){
				return 0;
			}
			return (Long)v1 - (Long)v2 > 0 ? 1 : -1;
		}

		if(v1 instanceof Date) {
			if(((Date)v1).equals((Date)v2)){
				return 0;
			}
			return ((Date)v1).after((Date)v2) ? 1 : -1;
		}
		return 0;
	}
}
