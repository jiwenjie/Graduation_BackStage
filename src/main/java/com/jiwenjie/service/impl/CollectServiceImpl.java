package com.jiwenjie.service.impl;

import com.jiwenjie.dao.CollectDao;
import com.jiwenjie.entity.ArticleBaseBean;
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

    /**
     * 分页查询
     * @param start 从哪个记录开始查询
     * @param end   到哪个记录结束
     * @return
     */
    @Override
    public List<ArticleBaseBean> getCollectArticle(int start, int end) {
        return collectDao.searchCollectData(start, end);
    }
}
