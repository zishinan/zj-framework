package com.downjoy.app.vipserver.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 首页 <br/>
 * @date 2017-07-13 上午 9:56 <br/>
 */
@Controller
public class SearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    @RequestMapping("/search.html")
    public ModelAndView index(HttpServletRequest request){
        LOGGER.info("index....");
        return new ModelAndView("search/search");
    }

    @RequestMapping("/")
    public ModelAndView indexNull(HttpServletRequest request){
        LOGGER.info("=====null");
        return new ModelAndView("search/search");
    }

}