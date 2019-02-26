package com.jiwenjie.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiwenjie.common.CommonUtils;
import com.jiwenjie.common.Constant;
import com.jiwenjie.common.MD5Crypto;
import com.jiwenjie.entity.PhoneUser;
import com.jiwenjie.service.FeedBackService;
import com.jiwenjie.service.PhoneUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc:
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/phoneUser")
public class PhoneUserController {

    @Autowired
    private PhoneUserService userService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户注册接口
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("userphone") String userphone) {
        Map<String, Object> map = new HashMap<>();

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("手机号：" + userphone);

        userphone = CommonUtils.clearSpace(userphone);

        /**
         * 是否存在相同用户名用户
         */
        PhoneUser nameUser = userService.findUserByName(username);
        PhoneUser phoneUser = userService.findUserByPhone(userphone);

        if (nameUser != null) {
            System.out.println("the username has been register");
            map = CommonUtils.operationFailed(map,
                    "the username has been register", HttpStatus.BAD_REQUEST.value());
        } else if (phoneUser != null) {
            System.out.println("the phone number has been register");
            map = CommonUtils.operationFailed(map,
                    "the phone number has been register", HttpStatus.BAD_REQUEST.value());
        } else {
            password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
            System.out.println("加密后密码：" + password);
            try {
                int rows = userService.registerPhoneUser(CommonUtils.getPhoneUserId(), username, password, userphone);
                if (rows > 0) {
                    System.out.println("影响行数：" + rows);
                    map = CommonUtils.operationSucceed(map);
                } else {
                    map = CommonUtils.operationFailed(map,
                            "insert data failed (dataBase)", HttpStatus.BAD_REQUEST.value());
                }
            } catch (Exception e) {
                System.out.println("failed");
                map = CommonUtils.operationFailed(map,
                        "failed", HttpStatus.BAD_REQUEST.value());
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 用户注册接口
     */
    @RequestMapping(value = "/registerPhone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUserPhone(@RequestParam("userphone") String userphone) {
        System.out.println("手机号：" + userphone);
        Map<String, Object> map = new HashMap<>();

        /* 查看是否存在相同的手机号 */
        userphone = CommonUtils.clearSpace(userphone);
        PhoneUser phoneUser = userService.findUserByPhone(userphone);
        if (phoneUser != null) {
            System.out.println("the phone has been register");
            map = CommonUtils.operationFailed(map,
                    "the phone has been register", HttpStatus.BAD_REQUEST.value());
        } else {
            try {
                String userId = CommonUtils.getPhoneUserId();
                int rows = userService.registerUserPhoneNum(userId, userphone);
                if (rows > 0) {
                    // 手机号注册成功, 并把 userId 返回
                    System.out.println("影响行数：" + rows);
                    map.put("data", userId);
                    map = CommonUtils.operationSucceed(map);
                } else {
                    map = CommonUtils.operationFailed(map,
                            "insert data failed (dataBase)", HttpStatus.BAD_REQUEST.value());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 在验证玩手机号之后，用户输入昵称和密码。确定后调用该接口
     */
    @RequestMapping(value = "/registerNameAndPass", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUserNameAndPass(@RequestParam("userid") String userId, @RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("userId：" + userId);
        Map<String, Object> map = new HashMap<>();

        PhoneUser user = userService.getUserInfo(userId);
        if (user != null) {
            // 初次注册默认直接登录，所以状态设置为 false
            password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
            int rows = userService.registerUserNameAndPass(userId, username, password, false);
            if (rows > 0) {
                PhoneUser nowUser = userService.getUserInfo(userId);
                map.put("data", nowUser);
                map = CommonUtils.operationSucceed(map);
            } else {
                map = CommonUtils.operationFailed(map, "user find but change data error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            map = CommonUtils.operationFailed(map, "operation error, check and try again", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }

    /**
     * 用户登陆接口（byName）
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/loginByName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLoginByName(@RequestParam("username") String username, @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
        PhoneUser user = userService.userLoginByName(username, password);
        if (user != null) {
            session.setAttribute(Constant.SESSION_PHONE_USER, user);
            map.put("data", user);
            map = CommonUtils.operationSucceed(map);
            System.out.println("登陆成功");
        } else {
            map = CommonUtils.operationFailed(map, "map be you not register", HttpStatus.NOT_FOUND.value());
            System.out.println("登陆失败");
        }

        return map;
    }

    /**
     * 用户登陆接口（byPhone）
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLoginByPhone(@RequestParam("userphone") String phone, @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();

        phone = CommonUtils.clearSpace(phone);  // 清除号码中的空格
        System.out.println("号码：" + phone);
        System.out.println("密码：" + password);
        password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
        PhoneUser user = userService.userLoginByPhone(phone, password);
        if (user != null) {
            session.setAttribute(Constant.SESSION_PHONE_USER, user);
            map.put("data", user);
            map = CommonUtils.operationSucceed(map);
            System.out.println("登陆成功");
        } else {
            map = CommonUtils.operationFailed(map, "the number or password error", HttpStatus.NOT_FOUND.value());
            System.out.println("登陆失败");
        }
        return map;
    }

    /**
     * change user's nickname(昵称) or brief(简短的，简洁的) introduction
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeUserInfo(@RequestParam("userid") String userId, @RequestParam("nickname") String nickName, @RequestParam("description") String description) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("userId:" + userId + "\n"
                + "昵称：" + nickName + "\n" + "简介：" + description);
        PhoneUser user = userService.getUserInfo(userId);
        if (user != null) {
            System.out.println("已找到该用户，将进行数据修改");
            int rows = userService.updateUserInfo(user.getUserid(), nickName, description);
            System.out.println("受影响行数为：" + rows);
            if (rows > 0) {
                System.out.println("修改用户名和简介成功");
                user.setUsername(nickName);
                user.setProfile(description);
                map.put("data", user);
                map = CommonUtils.operationSucceed(map);
            } else {
                map = CommonUtils.operationFailed(map,
                        "change data error about database", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            System.out.println("未找到该用户，请检查数据是否错误");
            map = CommonUtils.operationFailed(map,
                    "not find this user, please check message and try again",
                    HttpStatus.NOT_FOUND.value());
        }
        return map;
    }

    /**
     * 用户修改信息之后调用该接口刷新
     */
    @RequestMapping(value = "/getUserInfoNow", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserInfo(@RequestParam("userid") String userId) {
        System.out.println("getUserInfo 接口");
        System.out.println("getUserInfo userId：" + userId);

        Map<String, Object> map = new HashMap<>();
        PhoneUser user = userService.getUserInfo(userId);
        if (user != null) {
            // 查询成功
            System.out.println("已找到该用户");
            String nowDate = CommonUtils.formatDateTime(new Date(), CommonUtils.TYPE_DATE);
            boolean moreOneDay = CommonUtils.timeDistance(user.getSignintime(), nowDate);
            if (moreOneDay) {
                // 说明此时的已经到了第二天
                int rows = userService.changeSignUp(userId, false);
                if (rows > 0) {
                    user.setSignintoday(false);
                    System.out.println("改变签到状态成功");
                }
            } else {
                System.out.println("距离上次签到时间太短，不需要再次签到");
            }

            // 查询成功后需要查找下该用户收藏了多少文章，如果数值不同则重新赋值。相同则不必处理
            int count = userService.findUserCollectCount(userId);
            if (user.getCollectioncount() != count) {
                System.out.println("收藏文章数量不匹配，正在同步");
                user.setCollectioncount(count);
            }
            System.out.println("赋值，把 user 对象返回");
            map.put("data", user);
            map = CommonUtils.operationSucceed(map);
            System.out.println("操作成功");
        } else {
            // 查询失败
            System.out.println("没有找到该用户");
            map = CommonUtils.operationFailed(map, "not found this user, please try again", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }

    /**
     * 用户头像上传
     */
    @RequestMapping(value = "/modifyAvatar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadAvatar(@RequestParam("userid") String userid, @RequestBody MultipartFile image) {
        // 在 spring 家族中上传头像都是使用 MultipartFile 类。多个文件则是数组类型
        System.out.println("文件名：" + image.getOriginalFilename() + "\n" + "userid：" + String.valueOf(userid));
        Map<String, Object> map = new HashMap<>();
        if (!image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String mimeType = request.getServletContext().getMimeType(originalFileName);
            System.out.println("mimeType: " + mimeType);
            // 是否图片文件
            if (mimeType != null && mimeType.startsWith("image/")) {
                try {
//                    PhoneUser user = (PhoneUser) session.getAttribute(Constant.SESSION_PHONE_USER);
                    String suffix = originalFileName.split("\\.")[1];   // 扩展名

                    // 上传到项目根目录的 upload 文件夹
                    String avatarPath = request.getSession().getServletContext().getRealPath("/upload") +
//                            File.separator + user.getUsername() +
                            File.separator + "avatar" +
                            File.separator + System.currentTimeMillis() + "." + suffix;

                    String savePath = avatarPath.substring(avatarPath.indexOf("\\upload"));
                    String finPath = savePath.replaceAll("\\\\", "/");
                    System.out.println("savePath：" + savePath);
                    System.out.println("finPath：" + finPath);

                    /**
                     * 上传到具体的硬盘路径，此时需要配置 tomcat 虚拟路径
                     */
//                    String avatarPath = "I:" + File.separator + "ProjectsFolder" + File.separator + "IdeaProject"
//                            + File.separator + "MovieProject" + File.separator + "src" + File.separator + "main"
//                            + File.separator + "webapp" + File.separator + "upload" + File.separator + user.getUsername()
//                            + File.separator + "avatar" + File.separator + System.currentTimeMillis() + "." + suffix;

                    System.out.println("tomcatPath: " + avatarPath);

                    File saveFile = new File(avatarPath);
                    if (!saveFile.getParentFile().exists()) {
                        saveFile.getParentFile().mkdirs();
                        saveFile.createNewFile();
                    }
                    image.transferTo(saveFile);    //将文件上传到指定的服务器的位置
                    int rows = userService.updateUserAvatar(userid, finPath.substring(1));  // 存储在数据库中的路径就从 upload 开始就可以了,
                                                                                            // 这里的 sub 是为了去除第一个 ‘/’
                    if (rows > 0) {
                        System.out.println("上传头像成功");
//                        // 上传文件成功之后查询 user，之后把最新的 user 返回
                        PhoneUser user = userService.getUserInfo(userid);
                        if (user != null) {
                            map.put("data", user);
                            map = CommonUtils.operationSucceed(map);
                        } else {
                            map = CommonUtils.operationFailed(map, "other error", HttpStatus.NOT_FOUND.value());
                        }
                    } else {
                        System.out.println("上传头像失败");
                        map = CommonUtils.operationFailed(map,
                                "change data failed", HttpStatus.BAD_REQUEST.value());
                    }
                } catch (IOException e) {
                    // 上传过程出错
                    System.out.println(e.getMessage());
                    map = CommonUtils.operationFailed(map, "upload fail", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    e.printStackTrace();
                }
            } else {
                // 不是图片文件返回相关信息
                map = CommonUtils.operationFailed(map, "please upload an image file", HttpStatus.BAD_REQUEST.value());
            }
            // 空文件返回相关
        } else {
            System.out.println("empty file");
            map = CommonUtils.operationFailed(map, "empty file", HttpStatus.BAD_REQUEST.value());
        }
        return map;
    }

    /**
     * 签到的接口
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userSignUp(@RequestParam("userid") String userId, @Param("signtime") String signtime) {
        System.out.println("userId：" + userId + "\n" + "签到时间：" + signtime);

        Map<String, Object> map = new HashMap<>();
        PhoneUser phoneUser = userService.getUserInfo(userId);
        if (phoneUser != null) {
            // 需要参数
            int continuedays = phoneUser.getContinuesigndays();
            boolean signIntoday = phoneUser.isSignintoday();
            int totalday = phoneUser.getSignintotaldays();
            System.out.println("continue：" + continuedays);
            System.out.println("totalDay：" + totalday);
            System.out.println("signIntoday：" + signIntoday);
            if (continuedays >= 0 && totalday >=0) {
                continuedays ++;
                totalday ++;
            }
            if (!signIntoday) {
                signIntoday = true;
            }
            int rows = userService.signUp(userId, continuedays, signIntoday, signtime, totalday);
            if (rows > 0) {
                System.out.println("sign up success");
                PhoneUser user = userService.getUserInfo(userId);
                if (user != null) {
                    System.out.println("查询到 User");
                    map.put("data", user);
                    map = CommonUtils.operationSucceed(map);
                } else {
                    System.out.println("未查询到 User");
                    map = CommonUtils.operationFailed(map, "other error", HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
            } else {
                System.out.println("sign up failed");
                map = CommonUtils.operationFailed(map,
                        "change data error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            map = CommonUtils.operationFailed(map,
                    "don't find this userId, please check and try again", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }

    /**
     * 用户反馈的接口
     */
    @RequestMapping(value = "/feedBack", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> feedBack(@RequestParam("userid") String userid,
                                        @RequestParam("content") String content) {
        System.out.println("进入用户反馈接口");
        Map<String, Object> map = new HashMap<>();

        PhoneUser user = userService.getUserInfo(userid);
        if (user != null) {
            // 说明找到了用户
            String time = CommonUtils.formatDateTime(new Date(), CommonUtils.TYPE_DATE);
            int rows = feedBackService.feedBack(userid, time, content);
            if (rows > 0) {
                System.out.println("插入数据库成功");
                map = CommonUtils.operationSucceed(map, "反馈成功，谢谢您的反馈，我们会更努力");
            } else {
                System.out.println("插入数据库失败");
                map = CommonUtils.operationFailed(map, "request error， please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            System.out.println("未找到用户");
            map =  CommonUtils.operationFailed(map, "未找到该用户", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }
}
