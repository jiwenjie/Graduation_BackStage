package com.jiwenjie.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc: 收藏的 bean
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleBaseBean implements Serializable {
    private int curPage;
    private List<ArticleBean> datas;
    private int offset;
    private boolean over = false;   // 设置默认值
    private int pageCount;
    private int size;
    private int total;

    @Override
    public String toString() {
        return "总数：" + total + "curPage: " + curPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

/*
*       "curPage":1,
        "datas":Array[15],
        "offset":0,
        "over":false,
        "pageCount":19,
        "size":15,
        "total":276
 *
 */
