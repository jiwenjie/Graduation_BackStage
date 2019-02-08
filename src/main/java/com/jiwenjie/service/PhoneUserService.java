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

    void registerPhoneUser(long userid, String username, String password, String userphone);   // 注册用户，其他的参数在后台代码中设置

    List<PhoneUser> findAllPhoneUser();     // 获取所有的手机用户

    PhoneUser findUserByName(String username);

    PhoneUser findUserByPhone(String userphone);

    PhoneUser userLoginByName(String username, String password);    // 用户登陆通过用户名

    PhoneUser userLoginByPhone(String userphone, String password);   // 用户登陆通过手机号

    void updateUserAvatar(long userid, String avatar);   // 更改头像

    void updateUserBgImage(long userid, String bgImageUrl);  // 更改背景图片

    void updateUserInfo(long userid, String username, String profile);  // 更改用户信息，包括头像、昵称、简介

    PhoneUser getUserInfo(long userid);     // 根据 userid 获取用户的信息，用以和上次做对比等等；
                                                            // 登陆时长，连续多少天等等
}
