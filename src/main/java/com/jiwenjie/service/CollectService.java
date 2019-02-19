package com.jiwenjie.service;

import com.jiwenjie.entity.UserArticle;
import com.jiwenjie.entity.WanAndroidBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc:
 */
public interface CollectService {

    /**
     * 根据 userId 查找该用户收藏了哪些 article id
     * (同一个文章可能被多个用户收藏，所以有一张表单独维护)
     */
    List<UserArticle> getListArticleId(String userId, int start, int end);

    /**
     * 在中间表中查询是否还有其他用户收藏了某篇文章
     */
    List<UserArticle> getAllListInfo(int id);

    /**
     * 根据文章 id 查询该条文章的所有信息并返回
     */
    WanAndroidBean findBeanMessageById(int id);

    /**
     * 新增收藏的文章，插入列表中（插入操作之前需要判断在文章 wanandroid 表中是否存在
     * 该信息，有则不用重复添加，没有的话再添加）
     */
    int collectNewArticle(String apkLink,
                          String author,
                          int chapterId,
                          String chapterName,
                          boolean collect,
                          int courseId,
                          String desc,
                          String envelopePic,
                          boolean fresh,
                          int id,
                          String link,
                          String niceDate,
                          String origin,
                          String projectLink,
                          Long publishTime,
                          int superChapterId,
                          String superChapterName,
                          String title,
                          int type,
                          int userId,
                          int visible,
                          int zan);

    /**
     * 取消收藏的文章，从列表中删除（删除之前需要判断在关系表中是否还有其他用户收藏了该文章，
     * 如果有的话不删除，没有的话删除）
     */
    int deleteArticle(int id);

    /**
     * 收藏文章的时候在中间表里添加映射关系（不管该文章有没有被其他用户收藏，在中间表里都需要添加映射）
     */
    int addArticleIdInUserArticle(String userId, int id);

    /**
     * 取消收藏的时候删除在中间表的映射关系
     */
    int deleteArticleIdInUserArticle(String userId, int id);

    int operationCollect(String userId, boolean operation);

    /**
     * 改变数据库中的用户收藏文章数量的值
     */
    int addCollectCount(String userId, int count);

    int reduceCollectCount(String userId, int count);

    /**
     * 获取用户此时收藏的文章数量
     */
    int findNowUserCollect(String userId);

    /**
     * 获取收藏表中信息
     */
    UserArticle findMessageInUserArticle(String userId, int articleid);
}
