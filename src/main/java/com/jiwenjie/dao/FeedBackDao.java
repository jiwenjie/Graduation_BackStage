package com.jiwenjie.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-20
 * desc:
 */
@Repository
public interface FeedBackDao {

    int feedBack(@Param("userid") String userid,
                 @Param("createtime") String time,
                 @Param("content") String content);
}
