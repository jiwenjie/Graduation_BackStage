package com.jiwenjie.service;

import com.jiwenjie.entity.ArticleBaseBean;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc:
 */
public interface CollectService {
    List<ArticleBaseBean> getCollectArticle(int start, int end);
}
