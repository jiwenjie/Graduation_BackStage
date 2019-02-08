package com.jiwenjie.dao;

import com.jiwenjie.entity.ArticleBaseBean;
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
     * 搜索收藏的文章
     *
     * start 表示从哪开始，end 表示结束在哪
     */
    List<ArticleBaseBean> searchCollectData(@Param("start") int page,
                                            @Param("end") int limit);
}
