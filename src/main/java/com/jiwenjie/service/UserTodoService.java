package com.jiwenjie.service;

import com.jiwenjie.entity.TodoBean;
import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-10
 * desc:
 */
public interface UserTodoService {

    int createNewTask(String userId, String id, String title, String content,
                      String time, boolean complete);

    /**
     * 删除
     */
    int deleteTask(String userId, String id);

    /**
     * 更改任务的状态，只能从未完成变为已完成，不能从已完成变为未完成
     */
    int changeTaskStatus(String userId, String id, boolean complete);

    /**
     * 查询列表
     */
    List<TodoBean> getListTask(String userid, int start, int end);

}
