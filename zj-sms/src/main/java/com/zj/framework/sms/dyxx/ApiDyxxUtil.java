package com.zj.framework.sms.dyxx;

import com.zj.framework.httpclient.HttpUtil;
import com.zj.util.file.PropertiesUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ApiDyxxUtil {
	private static final Logger logger = LoggerFactory.getLogger(ApiDyxxUtil.class);
	public static void main(String[] args) {
		PropertiesUtil.load("mail.properties");
//		String nums = "15882068471,13258280995,13668243256,13880322886,18180596106,18628183677,13348989981,13730894774,15982230805";
		String nums = "15882068471";
		String content = "您的订单下单成功，请下载手机客户端:www.baidu.com";
		boolean result = sendVariCode(nums, content);
		System.out.println(result);
		
	}
	
	public static boolean sendVariCode(String mobile,String variCode){ 
		String RIGHT_TAG = "提交成功";
		String content = PropertiesUtil.getProperty("mailContent",new String[]{variCode});
		String sendResult = sendMsg("web",mobile, content);
		if(sendResult != null && sendResult.contains(RIGHT_TAG)){
			return true;
		}
		logger.error("域名web提交短信失败："+sendResult);
		sendResult = sendMsg("sms", mobile, content);
		if(sendResult != null && sendResult.contains(RIGHT_TAG)){
			return true;
		}
		logger.error("域名sms提交短信失败："+sendResult);
		return false;
	}
	
	/**
	 * @param mobile 必填参数。手机号码。多个以英文逗号隔开
	 * @param content 必填参数。发送内容（1-500 个汉字）UTF-8编码
	 * @return
	 */
	public static String sendMsg(String urlTag,String mobile,String content){
		String reqUrl = PropertiesUtil.getProperty("Dyxx_reqUrl", new String[]{urlTag}); //请求地址
		String name = PropertiesUtil.getProperty("Dyxx_name"); // 	必填参数。用户账号
		String pwd = PropertiesUtil.getProperty("Dyxx_pwd"); //	必填参数。（web平台：基本资料中的接口密码）
		String type = "pt"; //	必填参数。固定值 pt
		String sign = PropertiesUtil.getProperty("Dyxx_sign");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", name));
		nvps.add(new BasicNameValuePair("pwd", pwd));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("sign", sign));
		
		String result = "error";
		try {
			result =  HttpUtil.post(reqUrl, nvps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
