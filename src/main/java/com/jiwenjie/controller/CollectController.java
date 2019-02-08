package com.jiwenjie.controller;

import com.jiwenjie.common.Constant;
import com.jiwenjie.entity.ArticleBaseBean;
import com.jiwenjie.entity.User;
import com.jiwenjie.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-01-29
 * desc:
 */
@Controller
@RequestMapping(value = "/collect")
public class CollectController {

    @Autowired
    private CollectService service;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/getCollectArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCollectArticle(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        Map<String, Object> map = new HashMap<>();
        if (user != null) {
            if (page == 0) {
                page = 1;
            }
            List<ArticleBaseBean> beanList = service.getCollectArticle(page, page * limit);
            if (beanList.size() > 0) {  // 查询成功
                map.put("data", beanList);
                map.put("errorCode", 0);
                map.put("errorMsg", "");
                return map;
            } else {    // 查询失败
                map.put("data", "");
                map.put("errorCode", HttpStatus.NOT_FOUND.value());
                map.put("errorMsg", "未查找到数据");
                return map;
            }
        } else {
            map.put("data", "");
            map.put("errorCode", HttpStatus.BAD_REQUEST.value());
            map.put("errorMsg", "未登录");
        }
        return null;
    }
}
