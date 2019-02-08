package com.jiwenjie.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiwenjie.common.CommonUtils;
import com.jiwenjie.common.Constant;
import com.jiwenjie.common.MD5Crypto;
import com.jiwenjie.entity.PhoneUser;
import com.jiwenjie.entity.User;
import com.jiwenjie.service.PhoneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc:
 */
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
            map.put("result", "failed");
            map.put("msg", "the username has been register");
            map.put("code", HttpStatus.BAD_REQUEST.value());
        } else if (phoneUser != null) {
            System.out.println("the phone number has been register");
            map.put("result", "failed");
            map.put("msg", "the phone number has been register");
            map.put("code", HttpStatus.BAD_REQUEST.value());
        } else {
            password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
            System.out.println("加密后密码：" + password);
            try {
                userService.registerPhoneUser(CommonUtils.getPhoneUserId(), username, password, userphone);
                map.put("result", "succeed");
                map.put("msg", "succeed");
                System.out.println("succeed");
                map.put("code", HttpStatus.OK.value());
            } catch (Exception e) {
                System.out.println("failed");
                map.put("msg", "failed");
                map.put("code", HttpStatus.BAD_REQUEST.value());
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 用户登陆接口（byName）
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
            map = CommonUtils.getDataSuccess(map);
            System.out.println("登陆成功");
        } else {
            map = CommonUtils.getDataFailed(map, "not register");
            System.out.println("登陆失败");
        }

        return map;
    }

    /**
     * 用户登陆接口（byPhone）
     */

}
