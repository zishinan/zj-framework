package com.zj.framework.cilizhu;

import com.zj.framework.httpclient.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 爬虫 <br/>
 * @date 2017-09-27 上午 9:33 <br/>
 */
public class Client {
//    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    static Set<String> codes = new HashSet<String>();
    static Set<String> titles = new HashSet<String>();
    private static final String mainUrl = "http://www.cilizhuzhu.org/torrent/%s.html";
    private static final String magTarget = "magnet:?xt=urn:btih:";
    public static void main(String[] args) throws IOException {
        String search = "无码";
        for (int i = 0; i < 10000; i++) {
            String itag = "";
            if(i > 0){
                itag = "_"+i;
            }
            String result = HttpUtil.get(String.format(mainUrl, search+itag));
            if(!result.contains("<div class=\"col-sm-2 col-lg-1 hidden-xs text-right size\">")){
                System.out.println("搜索完毕！");
                System.exit(0);
            }
            List<String> list = getAll(result);
        }

    }


    public static List<String> getAll(String result){
        List<String> list = new ArrayList<String>();
        for (String str : result.split("<a target=\"_blank\" href=\"http://www.cilizhuzhu.org/magnet/")) {
            String magnetCode = str.substring(0,str.indexOf(".html"));
            if(magnetCode.length() > 50){
                continue;
            }
            String title = str.substring(str.indexOf("title=\"")+7,str.indexOf("\">")).toLowerCase()
                    .replace(" ","")
                    .replace("-","")
                    .replace("_","")
                    .replace("+","");
            if(title.length() > 50){
                continue;
            }
            if(codes.contains(magnetCode)){
                continue;
            }
            if(titles.contains(title)){
                continue;
            }
            codes.add(magnetCode);
            titles.add(title);
            System.out.println(title + "===" + magTarget+magnetCode);
            list.add(magnetCode);
        }
        return list;
    }

}
