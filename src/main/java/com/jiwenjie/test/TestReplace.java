package com.jiwenjie.test;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-08
 * desc:
 */
public class TestReplace {

    public static void main(String[] args) {
//        String path = "\\upload\\avatar\\1550404091417.jpg";    // 把反斜杠替换成斜杠
//        String replaceAfter = path.replaceAll("\\\\", "/");
//        System.out.println(replaceAfter);

        String goodsday = "30天|12个月";
        String time = "2019-03-01T12:03:56+08:99";

        System.out.println(time.substring(14, 19));
//        System.out.println(goodsday.substring(0, goodsday.indexOf("|")));
//        System.out.println(goodsday.substring(goodsday.indexOf("|")));
    }
}
