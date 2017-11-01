package com.ouyang.test.to;

import com.ouyang.test.baseto.BaseTO;

/**
* @author Mr. xi.yang<br/>
* @version V1.0 <br/>
* @description: FAQ配置 <br/>
* @date 2017-11-01 18:20:49 <br/>
*/
public class CustomerFaq extends BaseTO {
    /**
    * faqid
    */
    private Long id;
    /**
    * 类型id
    */
    private Long categoryId;
    /**
    * 分类id
    */
    private Long seriseId;
    /**
    * 标题
    */
    private String title;
    /**
    * 文章
    */
    private String content;
    /**
    * 状态
    */
    private Status status;
    /**
    * 是否热门问题
    */
    private Boolean firstPage;
    /**
    * 排序字段
    */
    private Integer sort;
    /**
    * 访问数
    */
    private Long viewCount;
    /**
    * 有用数
    */
    private Long usefulCount;
    /**
    * 无用数
    */
    private Long unusefulCount;

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getSeriseId(){
        return seriseId;
    }
    public void setSeriseId(Long seriseId) {
        this.seriseId = seriseId;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Boolean isFirstPage(){
        return firstPage;
    }
    public void setFirstPage(Boolean firstPage) {
        this.firstPage = firstPage;
    }
    public Integer getSort(){
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Long getViewCount(){
        return viewCount;
    }
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
    public Long getUsefulCount(){
        return usefulCount;
    }
    public void setUsefulCount(Long usefulCount) {
        this.usefulCount = usefulCount;
    }
    public Long getUnusefulCount(){
        return unusefulCount;
    }
    public void setUnusefulCount(Long unusefulCount) {
        this.unusefulCount = unusefulCount;
    }
}