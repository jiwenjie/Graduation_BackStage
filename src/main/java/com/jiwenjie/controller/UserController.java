package com.jiwenjie.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiwenjie.common.Constant;
import com.jiwenjie.common.MD5Crypto;
import com.jiwenjie.common.auth.AdminPassport;
import com.jiwenjie.common.auth.AuthPassport;
import com.jiwenjie.entity.User;
import com.jiwenjie.entity.UserFile;
import com.jiwenjie.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2018-12-13
 * desc: User 的 controller 类。注意 Json 的底层是 HashMap 类型
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    /**
     * 注册用户接口
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @return 注册结果
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String phone) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("进入注册响应");

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        System.out.println("手机号：" + phone);

        /**
         * 是否存在相同用户名用户
         */
        User nUser = userService.findUserByName(username);
        User pUser = userService.findUserByPhone(phone);
        if (nUser != null) {
            System.out.println("the username has been register");
            map.put("message", "the username has been register");
            map.put("code", HttpStatus.BAD_REQUEST.value());
        } else if (pUser != null) {
            System.out.println("the phone number has been register");
            map.put("message", "the phone number has been register");
            map.put("code", HttpStatus.BAD_REQUEST.value());
        } else {
            password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
            try {
                userService.registerUser(username, password, phone);
                map.put("message", "succeed");
                System.out.println("succeed");
                map.put("code", HttpStatus.OK.value());
            } catch (Exception e) {
                System.out.println("failed");
                map.put("message", "failed");
                map.put("code", HttpStatus.BAD_REQUEST.value());
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userLogin(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("进入登陆接口");

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        password = MD5Crypto.encryptBASE64(MD5Crypto.MD5(password));
        User user = userService.userLogin(username, password);
        if (user != null) {
            session.setAttribute(Constant.SESSION_USER, user);
            map.put("user", user);
            map.put("code", HttpStatus.OK.value());
            map.put("message", "succeed");
            System.out.println("登陆成功");
        } else {
            map.put("message", "not register");
            map.put("code", HttpStatus.NOT_FOUND.value());
            System.out.println("登陆失败");
        }
        return map;
    }

    /**
     * 获取所有用户信息，限定管理员
     *
     * @return 用户列表
     */
    @AuthPassport
    @AdminPassport
    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllFiles() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userService.getAllUsers();
        if (users == null) {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "not found");
        } else {
            for (User user : users) {
                List<UserFile> files = userService.getUserFileList(user.getId());
                user.setFiles(files);
            }
            map.put("code", HttpStatus.OK.value());
            map.put("message", "succeed");
            map.put("users", users);
        }
        return map;
    }

    /**
     * 获取指定用户信息，限定管理员
     *
     * @param id 用户 id
     * @return 用户信息
     */
    @AuthPassport
    @AdminPassport
    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(Integer id) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.findUserById(id);
        if (user != null) {
            map.put("user", user);
            map.put("message", "succeed");
            map.put("code", HttpStatus.OK.value());
        } else {
            map.put("message", "not found");
            map.put("code", HttpStatus.NOT_FOUND.value());
        }
        return map;
    }

    /**
     * 设置管理员，限定管理员
     *
     * @param id 用户 id
     * @return 设置结果
     */
    @AuthPassport
    @AdminPassport
    @RequestMapping(value = "/set-staff", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> setStaffUser(Integer id) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.findUserById(id);
        if (user == null) {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "not found");
        } else {
            userService.setStaffUser(id);
            map.put("code", HttpStatus.OK.value());
            map.put("message", "set staff succeed");
        }
        return map;
    }

    /**
     * 用户头像上传
     *
     * @param avatar 头像文件
     * @return 上传结果
     */
    @AuthPassport
    @RequestMapping(value = "/modify-avatar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadAvatar(@RequestBody MultipartFile avatar) {
        Map<String, Object> map = new HashMap<>();
        if (!avatar.isEmpty()) {
            String originalFileName = avatar.getOriginalFilename();
            String mimeType = request.getServletContext().getMimeType(originalFileName);
            System.out.println("mimeType: " + mimeType);

            // 是否图片文件
            if (mimeType != null && mimeType.startsWith("image/")) {
                try {
                    User user = (User) session.getAttribute(Constant.SESSION_USER);
                    String suffix = originalFileName.split("\\.")[1];

//                    String avatarPath = request.getSession().getServletContext().getRealPath("/upload")
//                            + UserFile.separator + user.getUsername() + UserFile.separator + "avatar"
//                            + UserFile.separator + System.currentTimeMillis() + "." + suffix;

                    String avatarPath = "I:" + File.separator + "ProjectsFolder" + File.separator + "IdeaProject"
                            + File.separator + "MovieProject" + File.separator + "src" + File.separator + "main"
                            + File.separator + "webapp" + File.separator + "upload" + File.separator + user.getUsername()
                            + File.separator + "avatar" + File.separator + System.currentTimeMillis() + "." + suffix;

                    System.out.println("savePath: " + avatarPath);
                    File saveFile = new File(avatarPath);

                    if (!saveFile.getParentFile().exists()) {
                        saveFile.getParentFile().mkdirs();
                        saveFile.createNewFile();
                    }
                    avatar.transferTo(saveFile);
                    userService.uploadAvatar(user.getId(), avatarPath);
                    // 上传文件成功
                    map.put("code", HttpStatus.OK.value());
                    map.put("message", "upload succeed");
                } catch (IOException e) {
                    // 上传过程出错
                    map.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                    map.put("message", "upload fail");
                    e.printStackTrace();
                }
                // 不是图片文件返回相关信息
            } else {
                map.put("code", HttpStatus.BAD_REQUEST.value());
                map.put("message", "please upload an image file");
            }
            // 空文件返回相关
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "empty file");
        }
        return map;
    }

    /**
     * 单文件上传
     *
     * @param file 上传文件
     * @return 上传结果
     */
    @AuthPassport
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestBody MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                String originalFileName = file.getOriginalFilename();
                String fileName = originalFileName.split("\\.")[0];
                String suffix = originalFileName.split("\\.")[1];
                User user = (User) session.getAttribute(Constant.SESSION_USER);

                String filePath = "C:" + File.separator + "ProjectsFolder" + File.separator + "IdeaProject"
                        + File.separator + "MovieProject" + File.separator + "src" + File.separator + "main"
                        + File.separator + "webapp" + File.separator + "upload" + File.separator + user.getUsername()
                        + File.separator + "files" + File.separator + fileName + "_" + System.currentTimeMillis() + "." + suffix;

                System.out.println("savePath: " + filePath);
                File saveFile = new File(filePath);

                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                    saveFile.createNewFile();
                }

                file.transferTo(saveFile);
                userService.uploadFile(user.getId(), originalFileName, filePath);
                // 上传文件成功
                map.put("code", HttpStatus.OK.value());
                map.put("message", "upload succeed");
            } catch (IOException e) {
                // 上传过程出错
                map.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                map.put("message", "upload fail");
                e.printStackTrace();
            }
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "empty file");
        }
        return map;
    }

    /**
     * 多文件上传
     *
     * @param files 上传列表
     * @return 上传结果
     */
    @AuthPassport
    @RequestMapping(value = "/upload-files", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFiles(@RequestBody MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder emptyFile = new StringBuilder();
        if (files.length != 0) {
            for (MultipartFile file : files) {
                String originalFileName = file.getOriginalFilename();
                if (!file.isEmpty()) {
                    try {
                        String fileName = originalFileName.split("\\.")[0];
                        String suffix = originalFileName.split("\\.")[1];
                        User user = (User) session.getAttribute(Constant.SESSION_USER);

                        String filePath = "C:" + File.separator + "ProjectsFolder" + File.separator + "IdeaProject"
                                + File.separator + "MovieProject" + File.separator + "src" + File.separator + "main"
                                + File.separator + "webapp" + File.separator + "upload" + File.separator + user.getUsername()
                                + File.separator + "files" + File.separator + fileName + "_" + System.currentTimeMillis() + "." + suffix;

                        System.out.println("savePath: " + filePath);
                        File saveFile = new File(filePath);

                        if (!saveFile.getParentFile().exists()) {
                            saveFile.getParentFile().mkdirs();
                            saveFile.createNewFile();
                        }
                        file.transferTo(saveFile);
                        userService.uploadFile(user.getId(), originalFileName, filePath);
                    } catch (IOException e) {
                        // 上传过程出错
                        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                        map.put("message", "upload fail");
                        e.printStackTrace();
                    }
                } else {
                    emptyFile.append(originalFileName).append(",");
                }

                if (emptyFile.toString().equals("")) {
                    // 上传文件成功
                    map.put("code", HttpStatus.OK.value());
                    map.put("message", "upload succeed");
                } else {
                    // 返回空文件列表
                    map.put("code", HttpStatus.BAD_REQUEST.value());
                    map.put("message", emptyFile.deleteCharAt(emptyFile.length() - 1) + " are empty");
                }
            }
        } else {
            map.put("code", HttpStatus.BAD_REQUEST.value());
            map.put("message", "please select upload files");
        }
        return map;
    }

    /**
     * 获取当前用户的文件列表
     *
     * @return 当前用户下的文件列表
     */
    @AuthPassport
    @RequestMapping(value = "/get-files", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getFiles() {
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        List<UserFile> userFiles = userService.getUserFileList(user.getId());
        if (userFiles != null) {
            map.put("files", userFiles);
            map.put("code", HttpStatus.OK.value());
            map.put("message", "succeed");
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "fail");
        }
        return map;
    }

    /**
     * 文件下载
     *
     * @param id 文件 id
     * @return 二进制流结果
     */
    @AuthPassport
    @RequestMapping(value = "/download-file", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(int id) {
        ResponseEntity<byte[]> responseEntity = null;
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        UserFile file = userService.findUserFileById(user.getId(), id);

        if (file != null) {
            try {
                /**
                 * 待下载文件的路径，文件名
                 */
                String filePath = file.getFilePath();
                String fileName = file.getFileName();
                File downloadFile = new File(filePath);
                HttpHeaders headers = new HttpHeaders();
                /**
                 * 解决下载文件名中文乱码的问题
                 */
                String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
                /**
                 * 以 attachment 方式打开
                 */
                headers.setContentDispositionFormData("attachment", downloadFileName);
                /**
                 * 二进制数据流
                 */
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(downloadFile), headers, HttpStatus.CREATED);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * 删除用户文件
     *
     * @param id 文件 id
     * @return 删除结果
     */
    @AuthPassport
    @RequestMapping(value = "/delete-file", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteFile(int id) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        UserFile file = userService.findUserFileById(user.getId(), id);
        File delFile = new File(file.getFilePath());

        if (delFile.isFile() && delFile.exists()) {
            delFile.delete();
            userService.deleteFileById(user.getId(), id);
            map.put("code", HttpStatus.OK.value());
            map.put("message", "delete succeed");
        } else {
            map.put("code", HttpStatus.NOT_FOUND.value());
            map.put("message", "not found");
        }
        return map;
    }
}

/**
 * (https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
 * **/