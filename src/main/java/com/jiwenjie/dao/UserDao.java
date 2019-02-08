package com.jiwenjie.dao;

import com.jiwenjie.entity.User;
import com.jiwenjie.entity.UserFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc:
 */
@Repository
public interface UserDao {

    List<User> getAllUser();

    User findUserById(@Param("id") int id);

    void setStaffUser(@Param("id") int id);

    void registerUser(@Param("username") String username, @Param("password") String password, @Param("phone") String phone);

    User userLogin(@Param("username") String username, @Param("password") String password);

    User findUserByName(@Param("username") String username);

    User findUserByPhone(@Param("phone") String phone);

    void uploadAvatar(@Param("id") int id, @Param("avatar") String avatar);

    void uploadFile(@Param("id") int id, @Param("fileName") String fileName, @Param("filePath") String filePath);

    List<UserFile> getUserFileList(@Param("id") int id);

    UserFile findUserFileById(@Param("id") int id, @Param("fileId") int fileId);

    void deleteFileById(@Param("id") int id, @Param("fileId") int fileId);
}
