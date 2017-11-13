package com.zj.framework.supersearch.type;

public enum DownloadType {
    magnet("magnet:?xt=urn:btih:"),
    torrent(""),
    ;
    private String urlTag;

    DownloadType(String urlTag) {
        this.urlTag = urlTag;
    }

    public String getUrlTag() {
        return urlTag;
    }
}
