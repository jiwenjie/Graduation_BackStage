package com.jiwenjie.controller;

import com.jiwenjie.common.CommonUtils;
import com.jiwenjie.common.Constant;
import com.jiwenjie.entity.User;
import com.jiwenjie.entity.UserArticle;
import com.jiwenjie.entity.WanAndroidBean;
import com.jiwenjie.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    private CollectService collectService;

//    @RequestMapping(value = "/getCollectArticle", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> getCollectArticle(@RequestParam("page") int page, @RequestParam("limit") int limit) {
//        User user = (User) session.getAttribute(Constant.SESSION_USER);
//        Map<String, Object> map = new HashMap<>();
//        if (user != null) {
//            if (page == 0) {
//                page = 1;
//            }
////            List<ArticleBaseBean> beanList = service.getCollectList(page, page * limit);
////            if (beanList.size() > 0) {  // 查询成功
////                map.put("data", beanList);
////                map.put("errorCode", 0);
////                map.put("errorMsg", "");
////                return map;
////            } else {    // 查询失败
////                map.put("data", "");
////                map.put("errorCode", HttpStatus.NOT_FOUND.value());
////                map.put("errorMsg", "未查找到数据");
////                return map;
////            }
//        } else {
//            map.put("data", "");
//            map.put("errorCode", HttpStatus.BAD_REQUEST.value());
//            map.put("errorMsg", "未登录");
//        }
//        return null;
//    }

    /**
     * 收藏文章
     */
    @RequestMapping(value = "/collectArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> collectArticle(@RequestParam("phoneuserid") String phoneUserId, @RequestParam("apklink") String apkLink,
                                              @RequestParam("author") String author, @RequestParam("chapterid") int chapterId,
                                              @RequestParam("chaptername") String chapterName, @RequestParam("collect") boolean collect,
                                              @RequestParam("courseid") int courseId, @RequestParam("desc") String desc, @RequestParam("envelopepic") String envelopePic,
                                              @RequestParam("fresh") boolean fresh, @RequestParam("id") int id, @RequestParam("link") String link,
                                              @RequestParam("nicedate") String niceDate, @RequestParam("origin") String origin, @RequestParam("projectlink") String projectLink,
                                              @RequestParam("publishtime") Long publishTime, @RequestParam("superchapterid") int superChapterId,
                                              @RequestParam("superchaptername") String superChapterName, @RequestParam("title") String title,
                                              @RequestParam("type") int type, @RequestParam("userid") int userId, @RequestParam("visible") int visible, @RequestParam("zan") int zan) {

        System.out.println("userId" + phoneUserId);
        Map<String, Object> map = new HashMap<>();
        // 首先检查本地有没有该文章
        WanAndroidBean localBean = collectService.findBeanMessageById(id);
        if (localBean != null) {
            // 本地有该文章
            int rows = collectService.addArticleIdInUserArticle(phoneUserId, id);  // 在中间表添加映射条件
            if (rows > 0) {     // 添加映射成功
                map = CommonUtils.operationSucceed(map);
            } else {    // 添加映射失败
                map = CommonUtils.operationFailed(map, "add Data Error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            // 本地没有该文章的时候，先把该文章写入数据库
            int writeRows = collectService.collectNewArticle(apkLink, author, chapterId, chapterName, collect,
                    courseId, desc, envelopePic, fresh, id, link, niceDate, origin, projectLink, publishTime,
                    superChapterId, superChapterName, title, type, userId, visible, zan);
            if (writeRows > 0) {
                // 写入成功, 此时在中间表的映射添加 userId 与 文章的 id 之间的关系映射
                int addMappingRows = collectService.addArticleIdInUserArticle(phoneUserId, id);
                if (addMappingRows > 0) {
                    // 此时添加映射成功
                    map = CommonUtils.operationSucceed(map);
                } else {
                    map = CommonUtils.operationFailed(map, "add Data Error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } else {
                map = CommonUtils.operationFailed(map, "unknown error, you can try later", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        return map;
    }

    /**
     * 取消收藏文章
     */
    @RequestMapping(value = "/cancelCollectArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> cancelCollectArticle(@RequestParam("userid") String userId, @RequestParam("id") int id) {
        System.out.println("用户 id 是" + userId + "\n" + "文章 id 是" + id);

        Map<String, Object> map = new HashMap<>();
        int rows = collectService.deleteArticleIdInUserArticle(userId, id); // 从 userArticle 的中间表中删除对应关系
        if (rows > 0) {
            // 删除成功,判断还有没有其他的用户收藏了该文章，没有就把这篇文章删除，有的话不进行其他操作
            map = CommonUtils.operationSucceed(map);
            List<UserArticle> userArticleList = collectService.getAllListInfo(id);
            if (userArticleList == null || userArticleList.size() == 0) {
                // 说明没有其他用户收藏该文章了
                int deleteRow = collectService.deleteArticle(id);
                if (deleteRow > 0) {
                    System.out.println("删除无用文章内容成功");
                }
            }
        } else {
            // 删除失败
            map = CommonUtils.operationFailed(map, "opeartion database error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return map;
    }

    /**
     * 获取收藏文章的列表
     */
    @RequestMapping(value = "/getListCollectArticle", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getListCollectArticle(@RequestParam("userid") String userId, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        System.out.println("userId" + userId);

        Map<String, Object> map = new HashMap<>();
        List<UserArticle> collectListId;
        if (page == 0 || page == 1) {
            collectListId = collectService.getListArticleId(userId, 0, limit);
        } else {
            collectListId = collectService.getListArticleId(userId, (page - 1) * limit, page * limit);
        }
        if (collectListId == null || collectListId.size() == 0) {
            // 说明此时没有找到收藏的数据
            if (page == 0 || page == 1) {
                // 说明用户还没有收藏文章
                map = CommonUtils.operationFailed(map, "you not collect article", HttpStatus.NOT_FOUND.value());
            } else {
                map = CommonUtils.operationFailed(map, "not more data", HttpStatus.NOT_FOUND.value());
            }
        } else {
            List<WanAndroidBean> beanList = new ArrayList<>();
            for (UserArticle userArticle : collectListId) {
                WanAndroidBean bean = collectService.findBeanMessageById(userArticle.getId());
                if (bean != null) {
                    beanList.add(bean);
                }
            }
            if (beanList.size() > 0) {
                map.put("data", beanList);
                map = CommonUtils.operationSucceed(map);
            }
        }
        return map;
    }
}
