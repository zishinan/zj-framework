package com.zj.framework.mybatis.route;

import java.util.List;
import java.util.Random;

/**
 * 数据源关键字
 */
public class DataSourceKey {
    private String writeKey;
    private List<String> readKeys;

    public void setWriteKey(String writeKey) {
        this.writeKey = writeKey;
    }

    public String getMaster() {
        return writeKey;
    }

    public void setReadKeys(List<String> keys) {
        readKeys = keys;
    }

    public String getSlave() {
        int index = new Random().nextInt(readKeys.size());
        return readKeys.get(index);
    }
}
