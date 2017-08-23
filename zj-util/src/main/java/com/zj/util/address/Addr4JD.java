package com.zj.util.address;

import com.zj.util.net.HttpClientUtil;
import com.zj.util.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 从京东爬全国省市地址
 * @author ouyang
 *
 */
public class Addr4JD {
	public static void main(String[] args) {
		Map<String, String> rsMap = getTowns("49316");
		System.out.println(rsMap.size());
		for (String string : rsMap.keySet()) {
			System.out.println(string + "\t" + rsMap.get(string));
		}
	}

	private static Map<String, String> getProvinces() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String action = "getProvinces";
		return getMapByActionAndNvps(action, nvps);
	}
	
	private static Map<String, String> getCitys(String provinceId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("consigneeParam.provinceId", provinceId));
		String action = "getCitys";
		return getMapByActionAndNvps(action, nvps);
	}
	
	private static Map<String, String> getCountys(String cityId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("consigneeParam.cityId", cityId));
		String action = "getCountys";
		return getMapByActionAndNvps(action, nvps);
	}
	private static Map<String, String> getTowns(String countyId) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("consigneeParam.countyId", countyId));
		String action = "getTowns";
		return getMapByActionAndNvps(action, nvps);
	}
	
	private static Map<String, String> getMapByActionAndNvps(String action,List<NameValuePair> nvps) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String url = "http://trade.jd.com/shopping/dynamic/consignee/"+action+".action";
			String results = HttpClientUtil.post(url, nvps);
			String[] splits =  results.split("</option><option");
			for (String r : splits) {
				String key = StringUtils.substringBetween(r, "value=\"", "\"");
				if(StringUtil.isBlank(key)){
					continue;
				}
				String value = StringUtils.substringAfter(r, "\"  >").replace("</option></select>", "");
				result.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
