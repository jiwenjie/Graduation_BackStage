package com.jiwenjie.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-10
 * desc: 单独维护的 userid 与收藏的文章 id。因为一个用户可以收藏多篇文章，
 * 同一个文章也可以被多个用户收藏。所以他们之间的关系是 多对多
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 去除返回接口值为 null 的字段
public class UserArticle implements Serializable {

    private int id;
    private String userid;
    private int articleid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getArticleid() {
        return articleid;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }
}
