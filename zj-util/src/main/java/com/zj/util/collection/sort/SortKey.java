package com.zj.util.collection.sort;

/**
 * 排序关键字
 * Created by Yangxi on 2017/4/21 0021.
 */
public class SortKey {
    private String name;
    private Sort sort;

    public SortKey(String name, Sort sort) {
        this.name = name;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
