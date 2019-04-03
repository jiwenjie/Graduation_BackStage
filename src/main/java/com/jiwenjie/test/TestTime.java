package com.jiwenjie.test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: Jiwenjie
 * email: Jiwenjie97@gmail.com
 * time: 2019-02-08
 * desc:
 */
public class TestTime {

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
        //new日期对
//        Date date = new Date();

        String number = "187 5510 4612";
        System.out.println(number);
        number = number.replaceAll(" ", "");
        System.out.println(number);

        //转换提日期输出格式
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("" + l);
//        System.out.println(dateFormat.format(date));
//        System.out.println(date1.format(date));
//        System.out.println(dateFormat1.format(date));



        }


    public static void quickSort(int[] arr, int low, int high){
        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right){
            while(left < right && arr[right] >= temp){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
}


    public static void candidate (int[] array)    // 找出数组中出现次数最多的那个数
    {
        // map的key存放数组中的数字，value存放该数字出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++)
        {
            if(map.containsKey(array[i]))
            {
                int formerValue = map.get(array[i]);
                map.put(array[i], formerValue + 1);    // 该数字出现的次数加1
            }
            else
            {
                map.put(array[i], 1);    // 该数字第一次出现
            }
        }
        Collection<Integer> count = map.values();
        // 找出map的value中最大值，也就是数组中出现最多的数字所出现的次数
        int maxCount = Collections.max(count);
        int maxNumber = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            //得到value为maxCount的key，也就是数组中出现次数最多的数字
            if(entry.getValue() == maxCount)
            {
                maxNumber = entry.getKey();
            }
        }
        System.out.println("出现次数最多的数字为：" + maxNumber);
        System.out.println("该数字一共出现" + maxCount + "次");
    }

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
}