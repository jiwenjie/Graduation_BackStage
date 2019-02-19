package com.jiwenjie.test;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-08
 * desc:
 */
public class TestSubstring {

    public static void main(String[] args) {
        String path = "F:/test/upload/avatar/jackson.jpg";
        System.out.println(path);
        String savePath = path.substring(path.lastIndexOf("/upload"));
        System.out.println(savePath);
    }
}
