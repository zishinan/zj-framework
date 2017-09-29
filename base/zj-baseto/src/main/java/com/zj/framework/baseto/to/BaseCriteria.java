package com.zj.framework.baseto.to;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public abstract class BaseCriteria implements Serializable {

    private static final long serialVersionUID=-8787679012357374525L;

    private Integer pageNo = 1;

    private Integer pageSize = 20;

    /**
     * 是否分页标示
     */
    private Boolean usePage = true;

    private List<SortKey> sortKeys;

    public Boolean getUsePage() {
        return usePage;
    }

    public void setUsePage(Boolean usePage) {
        this.usePage = usePage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<SortKey> getSortKeys() {
        return sortKeys;
    }

    public void setSortKeys(List<SortKey> sortKeys) {
        this.sortKeys = sortKeys;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /*
         * toString方法，格式为json形式
         * @see java.lang.Object#toString()
         */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
