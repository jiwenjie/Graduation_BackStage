package com.jiwenjie.service.impl;

import com.jiwenjie.dao.FeedBackDao;
import com.jiwenjie.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-20
 * desc:
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackDao feedBackDao;

    @Override
    public int feedBack(String userid, String time, String content) {
        return feedBackDao.feedBack(userid, time, content);
    }
}
