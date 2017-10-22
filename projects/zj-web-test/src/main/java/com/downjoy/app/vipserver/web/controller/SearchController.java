package com.downjoy.app.vipserver.web.controller;

import com.zj.framework.cilizhu.CilizhuSearchService;
import com.zj.framework.cilizhu.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String seaKey = request.getParameter("search");
        Map<String,Object> model = new HashMap<>();
        if(seaKey == null){
            return new ModelAndView("search/search",model);
        }
        String pageString = request.getParameter("page");
        int page = getPage(pageString);
        List<SearchResult> searchResults = CilizhuSearchService.search(seaKey,page);
        LOGGER.info("result =========="+searchResults.size());
        model.put("search",seaKey);
        model.put("data", searchResults);
        model.put("page",page);
        return new ModelAndView("search/search",model);
    }

    private int getPage(String pageString) {
        if(null == pageString){
            return 0;
        }
        try {
            return Integer.parseInt(pageString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @RequestMapping("/")
    public ModelAndView indexNull(HttpServletRequest request){
        LOGGER.info("=====null");
        return new ModelAndView("search/search");
    }

}
