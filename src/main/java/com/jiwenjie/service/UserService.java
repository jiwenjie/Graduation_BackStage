package com.jiwenjie.service;

import com.jiwenjie.entity.User;
import com.jiwenjie.entity.UserFile;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc:
 */
public interface UserService {

    List<User> getAllUsers();

    User findUserById(int id);

    void setStaffUser(int id);

    void registerUser(String username, String password, String phone);

    User userLogin(String username, String password);

    User findUserByName(String username);

    User findUserByPhone(String phone);

    void uploadAvatar(int id, String avatar);

    void uploadFile(int id, String fileName, String filePath);

    List<UserFile> getUserFileList(int id);

    UserFile findUserFileById(int id, int fileId);

    void deleteFileById(int id, int fileId);
}
