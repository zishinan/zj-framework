package com.zj.framework.springmvc.controller;

import com.zj.framework.baseto.values.Regex;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 通用的controller <br/>
 * @date 2017-09-14 上午 11:17 <br/>
 */
public class BaseController {
    private static final String HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String UNKNOWN = "unknown";
    private static final String UNKNOWN_LOCAL_IP = "0:0:0:0:0:0:0:1";
    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 根据request获取ip地址
     * @param request
     * @return
     */
    protected static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(HEADER_X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.indexOf(Regex.SP_DOUHAO) != -1) {
            ip = ip.split(Regex.SP_DOUHAO)[0];
        }
        return UNKNOWN_LOCAL_IP.equals(ip)?LOCAL_IP:ip;
    }
}
