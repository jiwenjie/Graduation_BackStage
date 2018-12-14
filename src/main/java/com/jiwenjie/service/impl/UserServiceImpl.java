package com.jiwenjie.service.impl;

import com.jiwenjie.dao.UserDao;
import com.jiwenjie.entity.User;
import com.jiwenjie.entity.UserFile;
import com.jiwenjie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUser();
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public void setStaffUser(int id) {
        userDao.setStaffUser(id);
    }

    @Override
    public void registerUser(String username, String password, String phone) {
        userDao.registerUser(username, password, phone);
    }

    @Override
    public User userLogin(String username, String password) {
        return userDao.userLogin(username, password);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public void uploadAvatar(int id, String avatar) {
        userDao.uploadAvatar(id, avatar);
    }

    @Override
    public void uploadFile(int id, String fileName, String filePath) {
        userDao.uploadFile(id, fileName, filePath);
    }

    @Override
    public List<UserFile> getUserFileList(int id) {
        return userDao.getUserFileList(id);
    }

    @Override
    public UserFile findUserFileById(int id, int fileId) {
        return userDao.findUserFileById(id, fileId);
    }

    @Override
    public void deleteFileById(int id, int fileId) {
        userDao.deleteFileById(id, fileId);
    }
}
