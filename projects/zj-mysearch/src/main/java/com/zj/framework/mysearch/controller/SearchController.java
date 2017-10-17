package com.zj.framework.mysearch.controller;

import com.zj.framework.springmvc.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 搜索 <br/>
 * @date 2017-10-16 下午 2:32 <br/>
 */
@Controller
public class SearchController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    @RequestMapping("/index.html")
    public ModelAndView guildList(HttpServletRequest request){
        Map<String, Object> model = new HashMap<>();
        LOGGER.info("=================");
        return new ModelAndView("search/search", model);
    }
}
