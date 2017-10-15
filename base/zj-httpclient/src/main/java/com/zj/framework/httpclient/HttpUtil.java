package com.zj.framework.httpclient;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 * 
 * @author yangxi
 * 
 */
public class HttpUtil {

	private static final int STATE_OK = 200;
	
	private static final int CONNECTION_TIMEOUT = 5 * 1000;
	
	private static final int REQUEST_TIMEOUT = 10 * 1000;

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	// httpGet连接
	public static String get(String url, List<NameValuePair> nvps) throws Exception {
		StringBuilder responseString = new StringBuilder();
		HttpClient httpclient = HttpClientBuilder.create().build();
		String connectUrl = buildGetUrl(url, nvps);
		logger.info("http get request:" + connectUrl);
		HttpGet httpGet = new HttpGet(connectUrl);
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		httpGet.setConfig(requestConfig);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			responseString.append(EntityUtils.toString(entity));
		} else {
			logger.error("httpclient get connect error! status code: " + statusCode + " | url:" + connectUrl);
		}
		//logger.info("http response:" + responseString.toString());
		return responseString.toString();
	}

	// httpPost连接
	public static String post(String url, List<NameValuePair> nvps) throws Exception {
		StringBuilder responseString = new StringBuilder();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		if(nvps == null){
			nvps = new ArrayList<NameValuePair>();
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		logger.info("http post request:" + httpPost.getURI().toString());
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			responseString.append(EntityUtils.toString(entity));
		} else {
			logger.error("httpclient post connect error! status code: "+statusCode +" "+ httpPost.getURI().toString());
		}
		//logger.info("http response:" + responseString.toString());

		return responseString.toString();
	}
	
	// httpPost连接
	public static String postWithHeader(Map<String, String> header,String url, List<NameValuePair> nvps) throws Exception {
		StringBuilder responseString = new StringBuilder();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		for (String key : header.keySet()) {
			httpPost.addHeader(key, header.get(key));
		}
		if(nvps == null){
			nvps = new ArrayList<NameValuePair>();
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		logger.info("http post request:" + httpPost.getURI().toString());
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			responseString.append(EntityUtils.toString(entity));
		} else {
			logger.error("httpclient post connect error! status code: "+statusCode +" "+ httpPost.getURI().toString());
		}
		//logger.info("http response:" + responseString.toString());
		
		return responseString.toString();
	}
	
	public static String postWithHeader(Map<String, String> header,String url, Map<String, String> nvps) throws Exception {
		StringBuilder responseString = new StringBuilder();
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		for (String key : header.keySet()) {
			httpPost.addHeader(key, header.get(key));
		}
		List<NameValuePair> nvpsList = new ArrayList<NameValuePair>();
		if(nvps != null){
			for (String key : nvps.keySet()) {
				nvpsList.add(new BasicNameValuePair(key, nvps.get(key)));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvpsList, "UTF-8"));
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		logger.info("http post request:" + httpPost.getURI().toString());
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		if (entity != null && statusCode == 200) {
			responseString.append(EntityUtils.toString(entity));
		} else {
			logger.error("httpclient post connect error! status code: "+statusCode +" "+ httpPost.getURI().toString());
		}
		//logger.info("http response:" + responseString.toString());
		
		return responseString.toString();
	}
	
	public static String post(String url,String json) throws ParseException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpPost post = new HttpPost(url);
		StringEntity s = new StringEntity(json,"UTF-8");

		s.setContentType("application/json;charset=utf-8");
		s.setContentEncoding("utf-8");

		post.setEntity(s);

		HttpResponse res = client.execute(post);

		if(res.getStatusLine().getStatusCode() == STATE_OK){

			HttpEntity entity = res.getEntity();
			return EntityUtils.toString(entity);
		}
		else {
			return null;
		}
	}
	
	public static String get(String url) throws ParseException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet get = new HttpGet(url);
		
		HttpResponse res = client.execute(get);
		if(res.getStatusLine().getStatusCode() == STATE_OK){
			HttpEntity entity = res.getEntity();
			if (entity != null) {
				String charset = getContentCharSet(entity);
				// 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1
				return EntityUtils.toString(entity, charset);
			}
		}else {
			return null;
		}
		return null;
	}

	/**
	 * 默认编码utf -8
	 * Obtains character set of the entity, if known.
	 *
	 * @param entity must not be null
	 * @return the character set, or null if not found
	 * @throws ParseException if header elements cannot be parsed
	 * @throws IllegalArgumentException if entity is null
	 */
	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset" );
				if (param != null) {
					charset = param.getValue();
				}
			}
		}

		if(StringUtils.isEmpty(charset)){
			charset = "UTF-8";
		}
		return charset;
	}
	
	public static String getWithHeader(Map<String, String> header,String url) throws ParseException, IOException{
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet get = new HttpGet(url);
		for (String key : header.keySet()) {
			get.addHeader(key, header.get(key));
		}
		
		HttpResponse res = client.execute(get);
		
		if(res.getStatusLine().getStatusCode() == STATE_OK){
			
			HttpEntity entity = res.getEntity();
			return EntityUtils.toString(entity);
		}
		else {
			return null;
		}
	}

	// 构造get方法参数//没有URL转码
	public static String buildHttpGetParams(List<NameValuePair> params) {
		String paramStr = null;
		StringBuffer paramStrBuff = new StringBuffer();
		if (params.size() > 0) {
			paramStrBuff.append("?");
			for (NameValuePair nvp : params) {
				paramStrBuff.append(nvp.toString()).append("&");
			}
			String str = paramStrBuff.toString();
			paramStr = str.substring(0, str.length() - 1);
		}

		return paramStr;
	}

	// 构造get请求URL
	public static String buildGetUrl(String url, List<NameValuePair> nvps) {
		StringBuffer connectUrl = new StringBuffer(url);
		if (nvps != null) {
			if (url.indexOf("?") == -1) {
				if (nvps.size() != 0 && nvps.size() > 0) {
					connectUrl.append("?");
					connectUrl.append(URLEncodedUtils.format(nvps, "UTF-8"));
				}
			} else {
				if (nvps.size() != 0 && nvps.size() > 0) {
					connectUrl.append("&");
					connectUrl.append(URLEncodedUtils.format(nvps, "UTF-8"));
				}
			}
		}

		return connectUrl.toString();
	}
	public static void main(String[] args) throws Exception{
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("https://api.jpush.cn/v3/push");
		httpPost.addHeader("Authorization", "Basic N2Q0MzFlNDJkZmE2YTZkNjkzYWMyZDA0OjVlOTg3YWM2ZDJlMDRkOTVhOWQ4ZjBkMQ==");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(new StringEntity("{\"platform\":\"all\",\"audience\":\"all\",\"notification\":{\"alert\":\"Hi,JPush !\",\"android\":{\"extras\":{\"android-key1\":\"android-value1\"}},\"ios\":{\"sound\":\"sound.caf\",\"badge\":\"+1\",\"extras\":{\"ios-key1\":\"ios-value1\"}}}}"));
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();
		httpPost.setConfig(requestConfig);
		logger.info("http post request:" + httpPost.getURI().toString());
		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		StringBuilder responseString = new StringBuilder();
		if (entity != null && statusCode == 200) {
			responseString.append(EntityUtils.toString(entity));
		} else {
			logger.error("httpclient post connect error! status code: "+statusCode +" "+ httpPost.getURI().toString());
		}
		logger.info("http response:" + responseString.toString());
	}


}
