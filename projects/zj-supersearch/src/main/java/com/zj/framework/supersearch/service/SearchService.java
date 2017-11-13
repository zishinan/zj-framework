package com.zj.framework.supersearch.service;

import com.zj.framework.supersearch.to.SearchResult;
import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class SearchService {
    public void searchAndSave(String searchKey){
        int i = 1;
        while (true){
            List<SearchResult> perResult = searchPage(searchKey,i);
            if(CollectionUtils.isEmpty(perResult)){
                break;
            }
            this.saveResult(perResult);
            i++;
        }
    }

    private void saveResult(List<SearchResult> perResult) {

    }

    protected abstract List<SearchResult> searchPage(String searchKey, int i);


}
