package com.jiwenjie.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc: 手机 App 的用户
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // 去除返回接口值为 null 的字段
public class PhoneUser implements Serializable {

    private String userid;
    private String username;    // 昵称
    private String userphone;
    private String password;
    private int continuesigndays;   // 连续签到多少天，七天一周期
    private boolean signintoday;    // 在 mysql 中使用
                                    // tinyint 类型。true 为 1，false 0
    private String signintime;
    private int signintotaldays;    // 一共签到多少天
    private String logintime;       // 登陆时间节点
    private String logouttime;      // 退出登陆的时间点
    private String totaltime;       // 学习的总时间
    private int collectioncount;    // 收藏文章的数量
    private String avatar;      // 头像的网址
    private String bgimageurl;  // 背景图片的 url。默认是头像的高斯模糊

    private boolean signout;    // 标记是否退出账号

    // 个人简介，
    private String profile;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getContinuesigndays() {
        return continuesigndays;
    }

    public void setContinuesigndays(int continuesigndays) {
        this.continuesigndays = continuesigndays;
    }

    public boolean isSignintoday() {
        return signintoday;
    }

    public void setSignintoday(boolean signintoday) {
        this.signintoday = signintoday;
    }

    public String getSignintime() {
        return signintime;
    }

    public void setSignintime(String signintime) {
        this.signintime = signintime;
    }

    public int getSignintotaldays() {
        return signintotaldays;
    }

    public void setSignintotaldays(int signintotaldays) {
        this.signintotaldays = signintotaldays;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(String logouttime) {
        this.logouttime = logouttime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public int getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(int collectioncount) {
        this.collectioncount = collectioncount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBgimageurl() {
        return bgimageurl;
    }

    public void setBgimageurl(String bgimageurl) {
        this.bgimageurl = bgimageurl;
    }

    public boolean isSignout() {
        return signout;
    }

    public void setSignout(boolean signout) {
        this.signout = signout;
    }
}
