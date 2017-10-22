package com.zj.framework.cilizhu;

import com.zj.framework.httpclient.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class CilizhuSearchService {
    private static final String mainUrl = "http://www.cilizhuzhu.org/torrent/%s.html";
    private static final String magTarget = "magnet:?xt=urn:btih:";
    public static List<SearchResult> search(String seaKey,Integer page){
        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        if(null == page || page < 0){
            page  = 0;
        }
        try {
            if(page > 0){
                seaKey = seaKey+"_"+page;
            }
            String result = HttpUtil.get(String.format(mainUrl, seaKey));
            if (!result.contains("<div class=\"col-sm-2 col-lg-1 hidden-xs text-right size\">")) {
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
        for (String str : result.split("<a target=\"_blank\" href=\"http://www.cilizhuzhu.org/magnet/")) {
            String magnetCode = str.substring(0,str.indexOf(".html"));
            if(magnetCode.length() > 50){
                continue;
            }
            String title = str.substring(str.indexOf("title=\"")+7,str.indexOf("\">")).toLowerCase();
            SearchResult searchResult = new SearchResult();
            searchResult.setName(title);
            searchResult.setUrl(magTarget+magnetCode);
            list.add(searchResult);
        }
        return list;
    }
}
