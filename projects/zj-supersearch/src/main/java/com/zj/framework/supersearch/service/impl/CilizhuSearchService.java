package com.zj.framework.supersearch.service.impl;

import com.zj.framework.httpclient.HttpUtil;
import com.zj.framework.supersearch.service.SearchService;
import com.zj.framework.supersearch.to.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class CilizhuSearchService extends SearchService {
    private static final String mainUrl = "http://www.cilizhu1.com/torrent/%s.html";

    @Override
    protected List<SearchResult> searchPage(String searchKey, int i) {
        return null;
    }

    public static List<SearchResult> search(String seaKey, Integer page){
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        if(null == page || page < 0){
            page  = 0;
        }
        try {
            if(page > 0){
                seaKey = seaKey+"_"+page;
            }
            String result = HttpUtil.get(String.format(mainUrl, seaKey));
            System.out.println(result);
            if (!result.contains("col-sm-2 col-lg-1 hidden-xs text-right size")) {
                return searchResults;
            }
            searchResults.addAll(getAll(result));
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchResults;
    }


    public static List<SearchResult> getAll(String result){
        List<SearchResult> list = new ArrayList<SearchResult>();
        for (String str : result.split("<a target=\"_blank\" href=\"http://www.cilizhu1.com/magnet/")) {
            String magnetCode = str.substring(0,str.indexOf(".html"));
            if(magnetCode.length() > 50){
                continue;
            }
            String title = str.substring(str.indexOf("title=\"")+7,str.indexOf("\">")).toLowerCase();
            SearchResult searchResult = new SearchResult();
            searchResult.setName(title);
            searchResult.setUrlTag(magnetCode);
            list.add(searchResult);
        }
        return list;
    }
}
