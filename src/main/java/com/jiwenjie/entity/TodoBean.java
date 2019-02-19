package com.jiwenjie.entity;

import java.io.Serializable;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-06
 * desc: 自己的任务记录安排（笔记）
 */
public class TodoBean implements Serializable {

    private String todoid;
    private String userid;
    private String title;
    private String content;
    private String time;
    private boolean complete;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTodoid() {
        return todoid;
    }

    public void setTodoid(String todoid) {
        this.todoid = todoid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

