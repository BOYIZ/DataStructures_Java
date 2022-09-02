package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName InsertSort
 * @Description 插入排序
 * @Author boyiz
 * @Date 2022/9/2 18:22
 * @Version 1.0
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, 8, 200};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        //测试80000个数据 1s
//        int[] arr = new int[80000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = new Date();
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);
//        insertSort(arr);
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {

            int insertValue = arr[i]; //保存要插入的数
            int insertIndex = i - 1; //保存要插入数的前一个下标
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                //将前一个数往后移，即 arr[insertIndex] 后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //优化：判断是否需要赋值
            if (insertIndex + 1 == i ) {
                arr[insertIndex + 1] = insertValue;
            }
        }

//        for (int i = 0; i < arr.length - 1; i++) {
//
//            int insertValue = arr[i + 1]; //保存要插入的数
//            int insertIndex = i; //保存要插入数的前一个下标
//            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
//                //将前一个数往后移，即 arr[insertIndex] 后移
//                arr[insertIndex + 1] = arr[insertIndex];
//                insertIndex--;
//            }
//            arr[insertIndex + 1] = insertValue;
//
//        }


        /* 思路分析
        //第一轮 {34, 101, 119, 1}
        int insertValue = arr[1]; //保存要插入的数
        int insertIndex = 1 - 1; //保存要插入数的前一个下标

        // insertIndex >= 0 保证不越界
        // insertValue < arr[insertIndex]  表示需要移动位置
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            //将前一个数往后移，即 arr[insertIndex] 后移
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertValue;
        System.out.println("第一轮：" + Arrays.toString(arr));

        //第二轮
        insertValue = arr[2]; //保存要插入的数
        insertIndex = 2 - 1; //保存要插入数的前一个下标
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            //将前一个数往后移，即 arr[insertIndex] 后移
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertValue;
        System.out.println("第二轮：" + Arrays.toString(arr));

        //第三轮
        insertValue = arr[3]; //保存要插入的数
        insertIndex = 3 - 1; //保存要插入数的前一个下标
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            //将前一个数往后移，即 arr[insertIndex] 后移
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertValue;
        System.out.println("第三轮：" + Arrays.toString(arr));

         */
    }
}
