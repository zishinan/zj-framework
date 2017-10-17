package com.zj.framework.mysearch.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证
 */
public class WebLoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebLoginInterceptor.class);
    private String[] excludeURIs;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 用户登录信息检测
        return true;
    }

    public void setExcludeURIs(String[] excludeURIs) {
        this.excludeURIs = excludeURIs;
    }

}
