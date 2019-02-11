package com.jiwenjie.common;

import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-08
 * desc: 公共的方法
 */
public class CommonUtils {

    // 有关时间的 type
    public static final int TYPE_DATE_TIME = 119;
    public static final int TYPE_DATE = 120;
    public static final int TYPE_TIME = 121;

    private static final int MAX_NUM = 7562;
    private static final int MIN_NUM = 1;

    /**
     * 格式化时间（日期，时分秒）
     */
    public static String formatDateTime(Date date, int type) {
        SimpleDateFormat dateFormat = null;
        switch (type) {
            case TYPE_DATE_TIME: {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            }
            case TYPE_DATE: {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            }
            case TYPE_TIME: {
                dateFormat = new SimpleDateFormat("HH:mm:ss");
                break;
            }
        }
        if (dateFormat != null) return dateFormat.format(date);
        return null;
    }

    /**
     * 获取用户的 userid
     */
    public static String getPhoneUserId() {
        long currentTime = System.currentTimeMillis();
        return String.valueOf(currentTime - Math.round(Math.random() * (MAX_NUM - MIN_NUM) + MAX_NUM) + MIN_NUM);
    }

    /**
     * 获取用户 todoTask 的 id
     */
    public static String getUserTaskId() {
        long currentTime = System.currentTimeMillis();
        String time = String.valueOf(currentTime).substring(1, 4);
        return String.valueOf(Long.parseLong(time) * Math.round(Math.random() * 7) + 151);
    }

    /**
     * 调用接口成功
     */
    public static Map<String, Object> operationSucceed(Map<String, Object> map) {
        return operationSucceed(map, "");
    }

    public static Map<String, Object> operationSucceed(Map<String, Object> map, String msg) {
        map.put("result", "succeed");
        map.put("msg", msg);
        map.put("code", HttpStatus.OK.value());
        return map;
    }

    /**
     * 调用接口失败
     */
    public static Map<String, Object> operationFailed(Map<String, Object> map, String msg, int code) {
        map.put("result", "failed");
        map.put("msg", msg);
        map.put("code", code);
        return map;
    }
}
