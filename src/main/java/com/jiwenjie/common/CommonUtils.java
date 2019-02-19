package com.jiwenjie.common;

import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
        String time = String.valueOf(currentTime).substring(1, 7);
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

    /**
     * 清除手机号码中间的空格部分
     */
    public static String clearSpace(String phoneNum) {
        return phoneNum.replaceAll(" ", "");
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔, 查看是否相差够远
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 比较时间差是否超过一天
     */
    public static boolean timeDistance(String lastSignUpTime, String nowTime) {
        Long lastChangeNum = Long.parseLong((lastSignUpTime.substring(0, 10))   // 先把数据库中存储的数据切割成只有年月日 2019-02-18
                .replaceAll("-", ""));  // 把年月日中的 ‘-’ 去除，之后在转换成 long 类型
        Long nowChangeNum = Long.parseLong(nowTime.replaceAll("-", ""));
        if (nowChangeNum - lastChangeNum >= 1) {
            // 时间超过了一天
            return true;
        } else {
            // 时间还没有超过一天
            return false;
        }
    }
}
