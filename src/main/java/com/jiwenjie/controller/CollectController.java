package com.jiwenjie.controller;

import com.jiwenjie.common.CommonUtils;
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

    /**
     * 收藏文章
     */
    @RequestMapping(value = "/collectArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collectArticle(@RequestParam("phoneuserid") String phoneUserId,
                                              @RequestParam("apklink") String apkLink,
                                              @RequestParam("author") String author,
                                              @RequestParam("chapterid") int chapterId,
                                              @RequestParam("chaptername") String chapterName,
                                              @RequestParam("collect") boolean collect,
                                              @RequestParam("courseid") int courseId,
                                              @RequestParam("desc") String desc,
                                              @RequestParam("envelopepic") String envelopePic,
                                              @RequestParam("fresh") boolean fresh,
                                              @RequestParam("id") int id,
                                              @RequestParam("link") String link,
                                              @RequestParam("nicedate") String nicedate,
                                              @RequestParam("origin") String origin,
                                              @RequestParam("projectlink") String projectlink,
                                              @RequestParam("publishtime") Long publishtime,
                                              @RequestParam("superchapterid") int superchapterid,
                                              @RequestParam("superchaptername") String superchaptername,
                                              @RequestParam("title") String title,
                                              @RequestParam("type") int type,
                                              @RequestParam("userid") int userid,
                                              @RequestParam("visible") int visible,
                                              @RequestParam("zan") int zan) {

        System.out.println("userId" + phoneUserId);
        System.out.println("收藏接口");
        System.out.println("传递过来的参数信息：" + phoneUserId + "\n" + apkLink + "\n"
                + author + "\n" + chapterId + "\n" + chapterName + "\n" + collect + "\n"
                + courseId + "\n" + desc + "\n" + envelopePic + "\n" + fresh + "\n" + link
                + "\n" + nicedate + "\n" + origin + "\n" + projectlink + "\n" + publishtime + "\n"
                + superchapterid + "\n" + superchaptername + "\n" + title);

        Map<String, Object> map = new HashMap<>();
        // 首先检查本地有没有该文章
        WanAndroidBean localBean = collectService.findBeanMessageById(id);
        if (localBean != null) {
            // 本地有该文章
            System.out.println("本地有文章");
            UserArticle userArticle = collectService.findMessageInUserArticle(phoneUserId, id);
            if (userArticle != null) {
                // 说明此时在中间表中已经有映射了
                map = CommonUtils.operationSucceed(map);
            } else {
                int rows = collectService.addArticleIdInUserArticle(phoneUserId, id);  // 在中间表添加映射条件
                if (rows > 0) {     // 添加映射成功
                    map = CommonUtils.operationSucceed(map);
                } else {    // 添加映射失败
                    map = CommonUtils.operationFailed(map, "this article has been collect", HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            }

        } else {
            // 本地没有该文章的时候，先把该文章写入数据库
            System.out.println("本地没有文章");
            int writeRows = collectService.collectNewArticle(apkLink, author, chapterId, chapterName, collect,
                    courseId, desc, envelopePic, fresh, id, link, nicedate, origin, projectlink, publishtime,
                    superchapterid, superchaptername, title, type, userid, visible, zan);
            if (writeRows > 0) {
                System.out.println("写入成功");
                // 写入成功, 此时在中间表的映射添加 userId 与 文章的 id 之间的关系映射
                int addMappingRows = collectService.addArticleIdInUserArticle(phoneUserId, id);
                if (addMappingRows > 0) {
                    map = CommonUtils.operationSucceed(map);
                    System.out.println("添加映射成功，把 userid 与 article 对应起来成功");
                } else {
                    System.out.println("添加映射失败，把 userid 与 article 对应失败");
                    map = CommonUtils.operationFailed(map, "add Data Error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } else {
                System.out.println("写入失败");
                map = CommonUtils.operationFailed(map, "unknown error, you can try later", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        return map;
    }

    /**
     * 取消收藏文章
     */
    @RequestMapping(value = "/cancelCollectArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> cancelCollectArticle(@RequestParam("userid") String userId, @RequestParam("id") int id) {
        System.out.println("用户 id 是" + userId + "\n" + "文章 id 是" + id);
        System.out.println("取消收藏接口");

        Map<String, Object> map = new HashMap<>();
        int rows = collectService.deleteArticleIdInUserArticle(userId, id); // 从 userArticle 的中间表中删除对应关系
        if (rows > 0) {
            map = CommonUtils.operationSucceed(map);
            // 删除成功,判断还有没有其他的用户收藏了该文章，没有就把这篇文章删除，有的话不进行其他操作
//            int count = collectService.reduceCollectCount(userId, collectService.findNowUserCollect(userId));
//            if (count > 0) {
//                System.out.println("PhoneUser data change success");
//            } else {
//                System.out.println("PhoneUser data change failed");
//            }
//
//            List<UserArticle> userArticleList = collectService.getAllListInfo(id);
//            if (userArticleList == null || userArticleList.size() == 0) {
//                // 说明没有其他用户收藏该文章了
//                int deleteRow = collectService.deleteArticle(id);
//                if (deleteRow > 0) {
//                    System.out.println("删除无用文章内容成功");
//                }
//            }
        } else {
            // 删除失败
            map = CommonUtils.operationFailed(map, "operation database error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return map;
    }

    /**
     * 获取收藏文章的列表
     */
    @RequestMapping(value = "/getListCollectArticle", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getListCollectArticle(@RequestParam("userid") String userId, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        System.out.println("进入收藏文章的列表");
        System.out.println("userId" + userId);

        Map<String, Object> map = new HashMap<>();
        List<UserArticle> collectListId;
        if (page == 0 || page == 1) {
            collectListId = collectService.getListArticleId(userId, 0, limit);
        } else {
            collectListId = collectService.getListArticleId(userId, (page - 1) * limit, page * limit);
        }
        if (collectListId == null || collectListId.size() == 0) {
            System.out.println("没有找到收藏的数据");
            // 说明此时没有找到收藏的数据
            if (page == 0 || page == 1) {
                // 说明用户还没有收藏文章
                map = CommonUtils.operationFailed(map, "you not collect article", HttpStatus.NOT_FOUND.value());
            } else {
                map = CommonUtils.operationFailed(map, "not more data", HttpStatus.NOT_FOUND.value());
            }
        } else {
            System.out.println("列表循环查询数据" + collectListId.size());
            System.out.println("articleId：" + collectListId.get(0).getArticleid());
            List<WanAndroidBean> beanList = new ArrayList<>();
            for (UserArticle userArticle : collectListId) {
                System.out.println("articleId：" + userArticle.getArticleid());
                WanAndroidBean bean = collectService.findBeanMessageById(userArticle.getArticleid());
                if (bean != null) {
                    beanList.add(bean);
                }
            }
            if (beanList.size() > 0) {
                System.out.println("列表不为空");
                System.out.println("chapterName" + beanList.get(0).getSuperchaptername() + "\n"
                + beanList.get(0).getChaptername());
                map.put("data", beanList);
                map = CommonUtils.operationSucceed(map);
            } else {
                System.out.println("列表查询失败");
            }
        }
        return map;
    }
}
