package com.jiwenjie.dao;

import com.jiwenjie.entity.TodoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-10
 * desc: 有关用户自己新建任务的操作
 */
public interface UserTodoDao {

    /**
     * 新建
     */
    int createNewTask(@Param("userid") String userId, @Param("id") String id,
                      @Param("title") String title, @Param("content") String content,
                      @Param("time") String time, @Param("complete") boolean complete);

    /**
     * 删除
     */
    int deleteTask(@Param("userid") String userId, @Param("id") String id);

    /**
     * 更改任务的状态，只能从未完成变为已完成，不能从已完成变为未完成
     */
    int changeTaskStatus(@Param("userid") String userId, @Param("id") String id, @Param("complete") boolean complete);

    /**
     * 查询列表
     */
    List<TodoBean> getListTask(@Param("userid") String userid, @Param("start") int start,
                               @Param("end") int end, @Param("complete") boolean complete);

    /**
     * 获取详情
     */
    TodoBean getDetailById(@Param("userid") String userid, @Param("todoid") String todoid);
}
