//package com.jiwenjie.test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * author: Jiwenjie
// * email: Jiwenjie97@gmail.com
// * time: 2019-03-17
// * desc:
// */
//public class as {
//        public static void main(String[] args) {
//            InputStreamReader is = new InputStreamReader(System.in);
//            BufferedReader read = new BufferedReader(is);
//            try {
//                String[] stDatas = read.readLine().split(" ");
//                int[] datas = new int[stDatas.length];
//                for(int i = 0; i < stDatas.length ;i++) {
//                    datas[i] = Integer.parseInt(stDatas[i]);
//                }
//                System.out.println(find(datas));
//            } catch (IOException e) {
//                System.out.println("输入数据格式错误!!");
//                e.printStackTrace();
//            }
//        }
//
//        public static int find(int[] datas) {
//            HashMap<Integer, Integer> map = new HashMap<>();
//            for (int i = 0; i < datas.length; i++) {
//                if (map.containsKey(datas[i])) {
//                    // 如果已经存在值，那么取出对应的次数加一
//                    map.put(datas[i], map.get(datas[i]) + 1);     // key 是数值，value 是出现的次数
//                } else {
//                    map.put(datas[i], 1);
//                }
//            }
//            Collection<Integer> count = map.values();
//            int maxCount = Collections.max(count);
//            int maxNum = 0;
//            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//                if(entry.getValue() == maxCount) {
//                    maxNum = entry.getKey();    // 得到最大的值所对应的次数
//                }
//            }
//            return maxNum;
//        }
//}
