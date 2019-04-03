package com.jiwenjie.test;//package com.jiwenjie.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main{
//    public static void main(String[] args) {
//        InputStreamReader is = new InputStreamReader(System.in);
//        BufferedReader read = new BufferedReader(is);
//        try {
//            String[] stDatas = read.readLine().split(" ");
//            int[] datas = new int[stDatas.length];
//            for(int i = 0; i < stDatas.length ;i++) {
//                datas[i] = Integer.parseInt(stDatas[i]);
//            }
//            System.out.println(find(datas));
//        } catch (IOException e) {
//            System.out.println("输入数据格式错误!!");
//            e.printStackTrace();
//        }
//    }
//
//    public static int find(int[] datas) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < datas.length; i++) {
//            if (map.containsKey(datas[i])) {
//                // 如果已经存在值，那么取出对应的次数加一
//                map.put(datas[i], map.get(datas[i]) + 1);     // key 是数值，value 是出现的次数
//            } else {
//                map.put(datas[i], 1);
//            }
//        }
//        Collection<Integer> count = map.values();
//        int maxCount = Collections.max(count);
//        int maxNum = 0;
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if(entry.getValue() == maxCount) {
//                maxNum = entry.getKey();    // 得到最大的值所对应的次数
//            }
//        }
//        return maxNum;
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader(is);
        try {
            String stKey = read.readLine();
            String[] stDatas = read.readLine().split(" ");
            int key = Integer.parseInt(stKey);
            int[] datas = new int[stDatas.length];
            for(int i = 0; i < stDatas.length ;i++) {
                datas[i] = Integer.parseInt(stDatas[i]);
            }
            System.out.println(find(key,datas));
        } catch (IOException e) {
            System.out.println("输入数据格式错误!!");
            e.printStackTrace();
        }
    }

    public static int find(int key, int[] datas) {
        // 如果数组为空，直接返回-1，即查找失败
        if (datas == null) {
            return -1;
        }

        // 起始位置
        int start = 0;
        // 结束位置
        int end = datas.length - 1;

        while (start <= end) {
            // 中间位置
            int middle = (start + end) / 2;
            // 中值
            int middleValue = datas[middle];

            if (key == middleValue) {
                // 等于中值直接返回
                return middle;
            } else if (key < middleValue) {
                // 小于中值时在中值前面找
                end = middle - 1;
            } else {
                // 大于中值在中值后面找
                start = middle + 1;
            }
        }
        // 返回-1，即查找失败
        return -1;
    }
}
