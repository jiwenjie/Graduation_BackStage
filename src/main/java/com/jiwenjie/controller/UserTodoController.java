package com.jiwenjie.controller;

import com.jiwenjie.common.CommonUtils;
import com.jiwenjie.entity.TodoBean;
import com.jiwenjie.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-10
 * desc:
 */
@Controller
@RequestMapping(value = "/todoTask")
public class UserTodoController {

    @Autowired
    private UserTodoService userTodoService;

    /**
     * 新建任务
     */
    @RequestMapping(value = "/createNewTask", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> createNewTask(@RequestParam("userid") String userid, @RequestParam("title") String title,
                                             @RequestParam("content") String content) {
        System.out.println("用户 id 是" + userid);

        Map<String, Object> map = new HashMap<>();
        String taskId = CommonUtils.getUserTaskId();
        String createTime = CommonUtils.formatDateTime(new Date(), CommonUtils.TYPE_DATE_TIME);
        int rows = userTodoService.createNewTask(userid, taskId, title, content, createTime, false);   // 初始默认未完成
        if (rows > 0) {
            // 成功
            map = CommonUtils.operationSucceed(map);
        } else {
            map = CommonUtils.operationFailed(map, "operation error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return map;
    }

    /**
     * 删除创建的任务
     */
    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteUserTask(@RequestParam("userid") String userid, @RequestParam("todoid") String todoId) {
        System.out.println("用户 id 是" + userid);

        Map<String, Object> map = new HashMap<>();
        int rows = userTodoService.deleteTask(userid, todoId);
        if (rows > 0) {
            // 成功
            map = CommonUtils.operationSucceed(map);
        } else {
            map = CommonUtils.operationFailed(map, "operation error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return map;
    }

    /**
     * 改变状态，从未完成变为完成（一旦变为完成之后就不能在改成未完成）
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> changeStatus(@RequestParam("userid") String userid, @RequestParam("todoid") String todoId) {
        System.out.println("用户 id 是" + userid);

        Map<String, Object> map = new HashMap<>();
        int rows = userTodoService.changeTaskStatus(userid, todoId, true);
        if (rows > 0) {
            map = CommonUtils.operationSucceed(map);
        } else {
            map = CommonUtils.operationFailed(map, "change status failed, you can try agagin", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return map;
    }

    /**
     * 分页查询数据
     */
    @RequestMapping(value = "/getListDataAboutTask", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getListDataAboutTask(@RequestParam("userid") String userid, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        System.out.println("用户 id 是" + userid);

        Map<String, Object> map = new HashMap<>();

        List<TodoBean> beanList;
        if (page == 0 || page == 1) {
            beanList = userTodoService.getListTask(userid, 0, limit);
        } else {
            beanList = userTodoService.getListTask(userid, (page - 1) * limit, page * limit);
        }

        if (beanList != null && beanList.size() > 0) {
            // 此时查到数据
            map.put("data", beanList);
            map = CommonUtils.operationSucceed(map);
        } else {
            // 未查到数据
            if (page == 0 || page == 1) {
                map = CommonUtils.operationFailed(map, "you not create task", HttpStatus.NOT_FOUND.value());
            } else {
                map = CommonUtils.operationFailed(map, "no have more data", HttpStatus.NOT_FOUND.value());
            }
        }
        return map;
    }
}
