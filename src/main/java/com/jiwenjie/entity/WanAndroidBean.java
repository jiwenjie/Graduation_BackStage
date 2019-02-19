package com.jiwenjie.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-06
 * desc: App 中收藏的文章的 bean 类
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 去除返回接口值为 null 的字段
public class WanAndroidBean implements Serializable {

    private String apklink;
    private String author;
    private int chapterid;
    private String chaptername;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopepic;
    private boolean fresh;
    private int id;
    private String link;
    private String nicedate;
    private String origin;
    private String projectlink;
    private Long publishtime;
    private int superchapterid;
    private String superchaptername;
    private List<Tag> tags;
    private String title;
    private int type;
    private int userid;
    private int visible;
    private int zan;

    public String getApklink() {
        return apklink;
    }

    public void setApklink(String apklink) {
        this.apklink = apklink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopepic() {
        return envelopepic;
    }

    public void setEnvelopepic(String envelopepic) {
        this.envelopepic = envelopepic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNicedate() {
        return nicedate;
    }

    public void setNicedate(String nicedate) {
        this.nicedate = nicedate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectlink() {
        return projectlink;
    }

    public void setProjectlink(String projectlink) {
        this.projectlink = projectlink;
    }

    public Long getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Long publishtime) {
        this.publishtime = publishtime;
    }

    public int getSuperchapterid() {
        return superchapterid;
    }

    public void setSuperchapterid(int superchapterid) {
        this.superchapterid = superchapterid;
    }

    public String getSuperchaptername() {
        return superchaptername;
    }

    public void setSuperchaptername(String superchaptername) {
        this.superchaptername = superchaptername;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }
}
