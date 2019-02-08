package com.jiwenjie.service.impl;

import com.jiwenjie.dao.PhoneUserDao;
import com.jiwenjie.entity.PhoneUser;
import com.jiwenjie.service.PhoneUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-07
 * desc:
 */
@Service
public class PhoneUserServiceImpl implements PhoneUserService {

    @Autowired
    private PhoneUserDao userDao;

    @Override
    public void registerPhoneUser(long userid, String username, String password, String userphone) {
        userDao.registerPhoneUser(userid, username, password, userphone);
    }

    @Override
    public List<PhoneUser> findAllPhoneUser() {
        return userDao.findAllPhoneUser();
    }

    @Override
    public PhoneUser findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public PhoneUser findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public PhoneUser userLoginByName(String username, String password) {
        return userDao.userLoginByName(username, password);
    }

    @Override
    public PhoneUser userLoginByPhone(String phone, String password) {
        return userDao.userLoginByPhone(phone, password);
    }

    @Override
    public void updateUserAvatar(long userid, String avatar) {
        userDao.updateUserAvatar(userid, avatar);
    }

    @Override
    public void updateUserBgImage(long userid, String bgImageUrl) {
        userDao.updateUserBgImage(userid, bgImageUrl);
    }

    @Override
    public void updateUserInfo(long userid, String username, String profile) {
        userDao.updateUserInfo(userid, username, profile);
    }

    @Override
    public PhoneUser getUserInfo(long userid) {
        return userDao.getUserInfo(userid);
    }
}
