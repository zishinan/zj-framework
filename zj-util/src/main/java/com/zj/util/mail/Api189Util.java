package com.zj.util.mail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.util.date.DateUtil;
import com.zj.util.net.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

public class Api189Util {
	public static void main(String[] args) {
//		String nums = "15882068471,13258280995,13668243256,13880322886,18180596106,18628183677,13348989981,13730894774,15982230805";
		String nums = "15882068471";
		String[] allNums = nums.split(",");
		for (String num : allNums) {
			String result = sendMsg(num);
			System.out.println(num + ":" + result);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static String getAccessToken(){
		String reqUrl = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
		nvps.add(new BasicNameValuePair("app_id", "428449690000043039"));
		nvps.add(new BasicNameValuePair("app_secret", "35917bfe3aad52de639d989e402082eb"));
		
		String result = "error";
		try {
			result =  HttpClientUtil.post(reqUrl, nvps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSON.parseObject(result);
		result = (String) jsonObject.get("access_token");
		return result;
	}
	
	public static String sendMsg(String num){
		String reqUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("app_id", "428449690000043039"));
		nvps.add(new BasicNameValuePair("access_token", getAccessToken()));
		nvps.add(new BasicNameValuePair("acceptor_tel", num));
		nvps.add(new BasicNameValuePair("template_id", "91005226"));
		Map<String, String> paramMap = new HashMap<String, String>();
		int ranInt = new Random().nextInt();
		paramMap.put("user", "阳熙"+ranInt);
		paramMap.put("shop", "材商城"+ranInt);
		paramMap.put("order", "164763268"+ranInt);
		paramMap.put("url", "http://www.baidu.com");
		nvps.add(new BasicNameValuePair("template_param", JSON.toJSONString(paramMap)));
		String now = DateUtil.fmtLong2String(System.currentTimeMillis(), DateUtil.yyyy_MM_dd_HH_mm_ss);
		nvps.add(new BasicNameValuePair("timestamp", now));
		
		
		String result = "error";
		try {
			result =  HttpClientUtil.post(reqUrl, nvps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
