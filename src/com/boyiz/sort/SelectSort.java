package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.jar.JarEntry;

/**
 * @ClassName SelectSort
 * @Description 选择排序
 * @Author boyiz
 * @Date 2022/9/2 16:20
 * @Version 1.0
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        //测试80000个数据 2s
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = new Date();
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);
//        selectSort(arr);
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void selectSort(int[] arr) {
        //原始数组 101，34，119，1
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min>arr[j]) {  //说明min不是最小
                    min = arr[j];  //重置min为最小值
                    minIndex = j;  //记录最小值的下标
                }
            }
            //交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


/*  推导过程

        int minIndex = 0;
        int min = arr[0];
        //第一轮 1，34，119，101
        for (int i = 1; i < arr.length; i++) {
            if (min>arr[i]) {  //说明min不是最小
                min = arr[i];  //重置min为最小值
                minIndex = i;  //记录最小值的下标
            }
        }
        //交换
        arr[minIndex] = arr[0];
        arr[0] = min;
        System.out.println("第一轮："+Arrays.toString(arr));

        //第二轮 1，34，119，101
        minIndex = 1;
        min = arr[1];
        for (int i = 2; i < arr.length; i++) {
            if (min>arr[i]) {  //说明min不是最小
                min = arr[i];  //重置min为最小值
                minIndex = i;  //记录最小值的下标
            }
        }
        //交换
        arr[minIndex] = arr[1];
        arr[1] = min;
        System.out.println("第二轮："+Arrays.toString(arr));


        //第三轮 1，34，101，119
        minIndex = 2;
        min = arr[2];
        for (int i = 3; i < arr.length; i++) {
            if (min>arr[i]) {  //说明min不是最小
                min = arr[i];  //重置min为最小值
                minIndex = i;  //记录最小值的下标
            }
        }
        //交换
        arr[minIndex] = arr[2];
        arr[2] = min;
        System.out.println("第二轮："+Arrays.toString(arr));

 */
    }
}
