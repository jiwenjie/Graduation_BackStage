package com.jiwenjie.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiwenjie.common.CommonUtils;
import com.jiwenjie.common.Constant;
import com.jiwenjie.common.MD5Crypto;
import com.jiwenjie.entity.PhoneUser;
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
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户注册接口
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String userphone) {
        Map<String, Object> map = new HashMap<>();

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("手机号：" + userphone);

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
     * 用户登陆接口（byName）
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/loginByName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLoginByName(@RequestParam String username, @RequestParam String password) {
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
    public Map<String, Object> userLoginByPhone(@RequestParam("userphone") String phone, @RequestParam String password) {
        Map<String, Object> map = new HashMap<>();

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
            map = CommonUtils.operationFailed(map, "the phoneNum not exist", HttpStatus.NOT_FOUND.value());
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
        System.out.println("昵称：" + nickName + "\n" + "简介：" + description);
        PhoneUser user = userService.getUserInfo(userId);
        if (user != null) {
            int rows = userService.updateUserInfo(userId, nickName, description);
            if (rows > 0) {
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
     * 用户头像上传
     */
    @RequestMapping(value = "/modifyAvatar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadAvatar(@RequestBody MultipartFile avatar) {
        // 在 spring 家族中上传头像都是使用 MultipartFile 类。多个文件则是数组类型
        System.out.println("进入头像上传接口");
        System.out.println("文件名：" + avatar.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();
        if (!avatar.isEmpty()) {
            System.out.println("file is not empty");
            String originalFileName = avatar.getOriginalFilename();
            String mimeType = request.getServletContext().getMimeType(originalFileName);
            System.out.println("mimeType: " + mimeType);
            // 是否图片文件
            if (mimeType != null && mimeType.startsWith("image/")) {
                try {
                    PhoneUser user = (PhoneUser) session.getAttribute(Constant.SESSION_PHONE_USER);
                    String suffix = originalFileName.split("\\.")[1];   // 扩展名

                    // 上传到项目根目录的 upload 文件夹
                    String avatarPath = request.getSession().getServletContext().getRealPath("/upload") +
                            File.separator + user.getUsername() +
                            File.separator + "avatar" + 
                            File.separator + System.currentTimeMillis() + "." + suffix;

                    /**
                     * 上传到具体的硬盘路径，此时需要配置 tomcat 虚拟路径
                     */
//                    String avatarPath = "I:" + File.separator + "ProjectsFolder" + File.separator + "IdeaProject"
//                            + File.separator + "MovieProject" + File.separator + "src" + File.separator + "main"
//                            + File.separator + "webapp" + File.separator + "upload" + File.separator + user.getUsername()
//                            + File.separator + "avatar" + File.separator + System.currentTimeMillis() + "." + suffix;

                    System.out.println("savePath: " + avatarPath);

                    File saveFile = new File(avatarPath);
                    if (!saveFile.getParentFile().exists()) {
                        saveFile.getParentFile().mkdirs();
                        saveFile.createNewFile();
                    }
                    avatar.transferTo(saveFile);    //方法将上传文件写到服务器上指定的文件。
                    int rows = userService.updateUserAvatar(user.getUserid(), avatarPath);
                    if (rows > 0) {
                        // 上传文件成功
                        map = CommonUtils.operationSucceed(map);
                    } else {
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
            System.out.println("continue" + continuedays);
            if (continuedays >= 0 && totalday >=0) {
                continuedays ++;
                totalday ++;
            }
            if (!signIntoday) {
                signIntoday = true;
            }
            int rows = userService.signUp(userId, continuedays, signIntoday, signtime, totalday);
            if (rows > 0) {
                map = CommonUtils.operationSucceed(map);
            } else {
                map = CommonUtils.operationFailed(map,
                        "change data error, please try again", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            map = CommonUtils.operationFailed(map,
                    "don't find this userId, please check and try again", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }
}
