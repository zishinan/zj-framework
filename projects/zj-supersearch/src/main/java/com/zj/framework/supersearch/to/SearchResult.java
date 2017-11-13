package com.zj.framework.supersearch.to;

import com.zj.framework.supersearch.type.DownloadType;

import java.io.Serializable;

public class SearchResult implements Serializable {
    private DownloadType downloadType;
    private String urlTag;
    private String targets;
    private Long upPoint;
    private Long downPoint;
    private Double size;
    private String name;

    public DownloadType getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(DownloadType downloadType) {
        this.downloadType = downloadType;
    }

    public String getUrlTag() {
        return urlTag;
    }

    public void setUrlTag(String urlTag) {
        this.urlTag = urlTag;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public Long getUpPoint() {
        return upPoint;
    }

    public void setUpPoint(Long upPoint) {
        this.upPoint = upPoint;
    }

    public Long getDownPoint() {
        return downPoint;
    }

    public void setDownPoint(Long downPoint) {
        this.downPoint = downPoint;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
