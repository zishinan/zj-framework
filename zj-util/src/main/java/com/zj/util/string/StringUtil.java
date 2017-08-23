package com.zj.util.string;

import java.util.*;

public class StringUtil
{
	public static final String SEPARATOR = String.valueOf((char) 29);
	public static final String SP_FENHAO = ";";
	public static final String SP_MAOHAO = ":";
	public static final String SP_DOUHAO = ",";
	
	public static String[] splitBySP_FENHAO(String content){
		String[] result = {};
		if (content == null) {
			return result;
		}
		String string = content.trim().replace("；", SP_FENHAO);
		return string.split(SP_FENHAO);
	}
	public static String[] splitBySP_MAOHAO(String content){
		String[] result = {};
		if (content == null) {
			return result;
		}
		String string = content.trim().replace("：", SP_MAOHAO);
		return string.split(SP_MAOHAO);
	}
	private StringUtil() {
		throw new Error("不要实例化!");
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
		
	}
	
	
	public static String getLikeTag(String in){
		return "%"+in.trim()+"%";
	}
	
	public static String getLikeTagById(int id){
		return "%"+getUseTagById(id)+"%";
	}
	
	/**
	 * 增加id集
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param id
	 * @return
	 */
	public static String addIds(String ids,int id){
		return ids + "{"+id+"}";
	}
	
	/**
	 * 是否包含id
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param ids
	 * @param id
	 * @return
	 */
	public static boolean containsId(String ids,int id){
		if(isBlank(ids)){
			return false;
		}
		String tag = "{"+id+"}";
		return ids.contains(tag);
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 根据ids字符串（形如“{1}{2}{3}”）获取id集
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param id
	 * @return
	 */
	public static List<Integer> getIdList(String ids){
		List<Integer> result = new ArrayList<Integer>();
		if(isBlank(ids)){
			return result;
		}
		String[] allIds = ids.replace("｛", "{").replace("｝", "}").split("\\}\\{");
		for (String idString : allIds) {
			if(StringUtil.isBlank(idString)){
				continue;
			}
			int id = 0;
			try {
				id = Integer.parseInt(idString.replace("{", "").replace("}", ""));
			} catch (Exception e) {
				continue;
			}
			result.add(id);
		}
		return result;
	}
	/**
	 * 可用于一对多的包含关系字段，比如参与人
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param id
	 * @return 例如{1}
	 */
	public static String getUseTagById(int id){
		return "{"+id+"}";
	}
	
	public static String getUseTagById(long id){
		return "{"+id+"}";
	}
	
	/**
	 * @return 四位数字验证码
	 */
	public static String getVariCode(){
		int num = (int) (Math.random()*9000+1000);
		String viriCode = num+"";
		return viriCode;
	}
	
	
	public static boolean isPhoneNumber(String phoneNumber){
		if(null == phoneNumber){
			return false;
		}
		return phoneNumber.matches("^(13|15|18|17)\\d{9}$");
	}
	
	/**
	 * 获取随机的图片名
	 * <a href="xi.yang@i-jia.net">yangxi</a>
	 * @param baseName 基础名
	 * @param maxNum 最大图片编号
	 * @return 完整图片名
	 */
	public static String getRandName(String baseName,int maxNum){
		int num = (int) (Math.random()*maxNum+1);
		return baseName+num+".png";
	}
	
	
	/**
	 * 邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (null == email) {
			return false;
		}
		final String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return email.matches(regex);
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param s
	 * @return trim后length>0 and not equals "null" and not equals "undefined" 返回true
	 */
	public static boolean isNotBlank(String s)
	{
		if(s == null){
			return false;
		}
		String ss = s.trim();
		if("".equals(ss) || "null".equals(ss) || ss.length() == 0 || "undefined".equals(ss)){
			return false;
		}
		return true;
	}
	
	public static boolean isBlank(String s)
	{
		if(isNotBlank(s))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 获取一个随机的小写字母
	 * @return
	 */
	public static char getRandomSmallChar()
	{
		return (char)(Math.random() * 26 + 'a');
	}
	
	/**
	 * 获取一个随机的大写字母
	 * @return
	 */
	public static char getRandomBigChar()
	{
		return (char)(Math.random() * 26 + 'A');
	}
	
	/**
	 * "~"缩略拼接","分隔的数字字符串
	 * @param ts
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getSortArrString(String ts) {
		String[] tsArr = ts.split(",");
		if(tsArr.length < 3){
			return ts;
		}
		Map<Integer, Integer> numMap = new TreeMap<Integer, Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for (int i = 0; i < tsArr.length; i++) {
			int num = Integer.parseInt(tsArr[i]);
			boolean flag = true;
			for (Integer key : numMap.keySet()) {
				if(key == num - 1 || numMap.get(key) == num - 1){
					numMap.put(key, num);
					flag = false;
				}
			}
			if(flag){
				numMap.put(num, num);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (Integer key : numMap.keySet()) {
			int value = numMap.get(key);
			if(value - key == 1){
				sb.append(",").append(key).append(",").append(value);
				continue;
			}
			if(value == key){
				sb.append(",").append(key);
				continue;
			}
			sb.append(",").append(key).append("~").append(value);
		}
		return sb.toString().replaceFirst(",", "");
	}
}
