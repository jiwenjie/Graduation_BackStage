package com.jiwenjie.service.impl;

import com.jiwenjie.dao.UserTodoDao;
import com.jiwenjie.entity.TodoBean;
import com.jiwenjie.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-10
 * desc:
 */
@Service
public class UserTodoServiceImpl implements UserTodoService {

    @Autowired
    private UserTodoDao userTodoDao;

    @Override
    public int createNewTask(String userId, String id, String title, String content, String time, boolean complete) {
        return userTodoDao.createNewTask(userId, id, title, content, time, complete);
    }

    @Override
    public int deleteTask(String userId, String id) {
        return userTodoDao.deleteTask(userId, id);
    }

    @Override
    public int changeTaskStatus(String userId, String id, boolean complete) {
        return userTodoDao.changeTaskStatus(userId, id, complete);
    }

    @Override
    public List<TodoBean> getListTask(String userid, int start, int end, boolean complete) {
        return userTodoDao.getListTask(userid, start, end, complete);
    }

    @Override
    public TodoBean getDetailById(String userid, String todoid) {
        return userTodoDao.getDetailById(userid, todoid);
    }
}
