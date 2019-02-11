package com.jiwenjie.dao;

import com.jiwenjie.entity.ArticleBaseBean;
import com.jiwenjie.entity.UserArticle;
import com.jiwenjie.entity.WanAndroidBean;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc: 收藏文章的 dao 层
 */
@Repository
public interface CollectDao {
    /**
     * 获取收藏文章的列表
     * start 表示从哪开始，end 表示结束在哪
     */
//    List<ArticleBaseBean> getCollectList(@Param("userid") String userId,
//                                         @Param("start") int page,
//                                         @Param("end") int limit);

    /**
     * 根据 userId 查找该用户收藏了哪些 article id
     * (同一个文章可能被多个用户收藏，所以有一张表单独维护)
     */
    List<UserArticle> getListArticleId(@Param("userid") String userId,
                                       @Param("start") int start,
                                       @Param("end") int end);

    /**
     * 在中间表中查询是否还有其他用户收藏了某篇文章
     */
    List<UserArticle> getAllListInfo(@Param("id") int id);

    /**
     * 根据文章 id 查询该条文章的所有信息并返回
     */
    WanAndroidBean findBeanMessageById(@Param("id") int id);

    /**
     * 新增收藏的文章，插入列表中（插入操作之前需要判断在文章 wanandroid 表中是否存在
     * 该信息，有则不用重复添加，没有的话再添加）
     */
    int collectNewArticle(@Param("apkLink") String apkLink,
                          @Param("author") String author,
                          @Param("chapterId") int chapterId,
                          @Param("chapterName") String chapterName,
                          @Param("collect") boolean collect,
                          @Param("courseId") int courseId,
                          @Param("desc") String desc,
                          @Param("envelopePic") String envelopePic,
                          @Param("fresh") boolean fresh,
                          @Param("id") int id,
                          @Param("link") String link,
                          @Param("niceDate") String niceDate,
                          @Param("origin") String origin,
                          @Param("projectLink") String projectLink,
                          @Param("publishTime") Long publishTime,
                          @Param("superChapterId") int superChapterId,
                          @Param("superChapterName") String superChapterName,
                          @Param("title") String title,
                          @Param("type") int type,
                          @Param("userId") int userId,
                          @Param("visible") int visible,
                          @Param("zan") int zan);

    /**
     * 取消收藏的文章，从列表中删除（删除之前需要判断在关系表中是否还有其他用户收藏了该文章，
     * 如果有的话不删除，没有的话删除）
     */
    int deleteArticle(@Param("id") int id);

    /**
     * 收藏文章的时候在中间表里添加映射关系（不管该文章有没有被其他用户收藏，在中间表里都需要添加映射）
     */
    int addArticleIdInUserArticle(@Param("userid") String userId, @Param("id") int id);

    /**
     * 取消收藏的时候删除在中间表的映射关系
     */
    int deleteArticleIdInUserArticle(@Param("userid") String userId, @Param("id") int id);

    /**
     * 收藏与取消收藏的操作
     */
    int operationCollect(@Param("userid") long userId, @Param("operation") Boolean operation);
}
