package com.jiwenjie.service;

import com.jiwenjie.entity.PhoneUser;
import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc:
 */
public interface PhoneUserService {

    int registerPhoneUser(String userid, String username, String password, String userphone);   // 注册用户，其他的参数在后台代码中设置

    /**
     * 用户在注册页面输入手机号之后点击下一步调用该接口
     */
    int registerUserPhoneNum(String userid, String userphone);

    /**
     * 用户在输入过手机号之后输入密码和昵称
     */
    int registerUserNameAndPass(String userid, String username, String password, boolean signout);

    List<PhoneUser> findAllPhoneUser();     // 获取所有的手机用户

    PhoneUser findUserByName(String username);

    PhoneUser findUserByPhone(String userphone);

    PhoneUser userLoginByName(String username, String password);    // 用户登陆通过用户名

    PhoneUser userLoginByPhone(String userphone, String password);   // 用户登陆通过手机号

    int updateUserAvatar(String userid, String avatar);   // 更改头像

    int updateUserBgImage(String userid, String bgImageUrl);  // 更改背景图片

    int updateUserInfo(String userid, String username, String profile);  // 更改用户信息，包括头像、昵称、简介

    PhoneUser getUserInfo(String userid);     // 根据 userid 获取用户的信息，用以和上次做对比等等；
                                                            // 登陆时长，连续多少天等等

    int findUserCollectCount(String userid);

    int signUp(String userId, int continuedays, boolean signintoday, String signtime, int signtotalday);   // 签到

    int logout(String userId);   // 退出登陆

    /**
     * 用户在第一天签到后，在第二天的时候检查刷新把签到状态刷新
     */
    int changeSignUp(String userId, boolean signintoday);

}
