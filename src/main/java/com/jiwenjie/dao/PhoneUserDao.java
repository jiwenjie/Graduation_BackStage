package com.jiwenjie.dao;

import com.jiwenjie.entity.PhoneUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc:
 */
public interface PhoneUserDao {

//    void registerPhoneUser(@Param("username") String username,
//                           @Param("password") String password,
//                           @Param("userphone") String userphone);   // 注册用户，其他的参数在后台代码中设置

    int registerPhoneUser(@Param("userid") String userid,
                           @Param("username") String username,
                           @Param("password") String password,
                           @Param("userphone") String userphone);   // 注册用户，其他的参数在后台代码中设置

    /**
     * 用户在注册页面输入手机号之后点击下一步调用该接口
     */
    int registerUserPhoneNum(@Param("userid") String userid, @Param("userphone") String userphone);

    /**
     * 用户在输入过手机号之后输入密码和昵称
     */
    int registerUserNameAndPass(@Param("userid") String userid, @Param("username") String username,
                                @Param("password") String password, @Param("signout") boolean signout);

    List<PhoneUser> findAllPhoneUser();     // 获取所有的手机用户

    // 这两个用来在用户注册的时候验证是否有重复的用户名或者手机号
    PhoneUser findUserByName(@Param("username") String username);

    PhoneUser findUserByPhone(@Param("userphone") String userphone);

    PhoneUser userLoginByName(@Param("username") String username,
                              @Param("password") String password);    // 用户登陆通过用户名

    PhoneUser userLoginByPhone(@Param("userphone") String userphone,
                               @Param("password") String password);   // 用户登陆通过手机号

    int updateUserAvatar(@Param("userid") String userid,
                          @Param("avatar") String avatar);  // 更改用户头像

    int updateUserBgImage(@Param("userid") String userid,
                           @Param("bgimageurl") String bgImageUrl); // 更改用户背景

    int updateUserInfo(@Param("userid") String userid,
                        @Param("username") String username,
                        @Param("profile") String profile);  // 更改用户信息，昵称、简介

    PhoneUser getUserInfo(@Param("userid") String userid);     // 根据 userid 获取用户的信息，用以和上次做对比等等；
                                                            // 登陆时长，连续多少天等等

    int findUserCollectCount(@Param("userid") String userid);   // 在中间表中查看该用户收藏了多少文章，
                                                                // 和查找出来的用户信息做对比进行更新

    int signUp(@Param("userid") String userId,
               @Param("continuesigndays") int continuedays,
               @Param("signintoday") boolean signintoday,
               @Param("signintime") String signtime,
               @Param("signintotaldays") int signtotalday);   // 用户签到

    /**
     * 用户在第一天签到后，在第二天的时候检查刷新把签到状态刷新
     */
    int changeSignUp(@Param("userid") String userId, @Param("signintoday") boolean signintoday);

    int logout(@Param("userid") String userId);   // 退出登陆
}
