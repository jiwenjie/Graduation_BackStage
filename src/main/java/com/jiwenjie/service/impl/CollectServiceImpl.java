package com.jiwenjie.service.impl;

import com.jiwenjie.dao.CollectDao;
import com.jiwenjie.entity.UserArticle;
import com.jiwenjie.entity.WanAndroidBean;
import com.jiwenjie.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc:
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;

    @Override
    public List<UserArticle> getListArticleId(String userId, int start, int end) {
        return collectDao.getListArticleId(userId, start, end);
    }

    @Override
    public List<UserArticle> getAllListInfo(int id) {
        return collectDao.getAllListInfo(id);
    }

    @Override
    public WanAndroidBean findBeanMessageById(int id) {
        return collectDao.findBeanMessageById(id);
    }

    @Override
    public int collectNewArticle(String apkLink, String author, int chapterId, String chapterName, boolean collect, int courseId, String desc, String envelopePic, boolean fresh, int id, String link, String niceDate, String origin, String projectLink, Long publishTime, int superChapterId, String superChapterName, String title, int type, int userId, int visible, int zan) {
        return collectDao.collectNewArticle(apkLink, author, chapterId, chapterName, collect, courseId, desc, envelopePic, fresh, id, link, niceDate, origin, projectLink, publishTime, superChapterId, superChapterName, title, type, userId, visible, zan);
    }

    @Override
    public int deleteArticle(int id) {
        return collectDao.deleteArticle(id);
    }

    @Override
    public int addArticleIdInUserArticle(String userId, int id) {
        return collectDao.addArticleIdInUserArticle(userId, id);
    }

    @Override
    public int deleteArticleIdInUserArticle(String userId, int id) {
        return collectDao.deleteArticleIdInUserArticle(userId, id);
    }

    @Override
    public int operationCollect(String userId, boolean operation) {
        return collectDao.operationCollect(userId, operation);
    }

    @Override
    public int updateCollectCount(String userId, int count) {
        return collectDao.updateCollectCount(userId, count);
    }

    @Override
    public int reduceCollectCount(String userId, int count) {
        return collectDao.reduceCollectCount(userId, count);
    }

    @Override
    public int findNowUserCollect(String userId) {
        return collectDao.findNowUserCollect(userId);
    }

    @Override
    public UserArticle findMessageInUserArticle(String userId, int articleid) {
        return collectDao.findMessageInUserArticle(userId, articleid);
    }
}
