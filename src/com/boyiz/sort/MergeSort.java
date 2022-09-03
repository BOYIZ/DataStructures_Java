package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName MergeSort
 * @Description 归并排序
 * @Author boyiz
 * @Date 2022/9/3 16:13
 * @Version 1.0
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};  //8个数据，merge 7次， 80000个数据，merge 80000-1次
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

        //测试80000个数据 0s   8000000 1s
//        int[] arr = new int[8000000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date1 = new Date();
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

    }

    //分解 + 合并
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, right, mid, temp);
        }

    }

    /**
     * @param left  初始索引
     * @param right 右边索引
     * @param mid   中间索引
     * @param temp  临时数组
     * @return void
     * @Author boyiz
     * @Description //合并方法
     * @Date 16:19 2022/9/3
     * @Param arr 原始数组
     **/
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left; //左边有序序列的初始索引
        int j = mid + 1; //右边有序序列的初始索引
        int tem = 0;//指向临时数组temp的索引，用来确定存放位置
        //将两边的数据按照规则填充至temp数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[tem] = arr[i];
                tem++; //临时数组下标+1
                i++; //i+1
            } else {
                temp[tem] = arr[j];
                tem++;
                j++;
            }
        }
        //将其中剩余的数据添加到temp
        while (i <= mid) {  //左边有剩余
            temp[tem] = arr[i];
            tem++;
            i++;
        }
        while (j <= right) { //右边有剩余
            temp[tem] = arr[j];
            tem++;
            j++;
        }

        //copy temp数组到 原arr数组
        //注意，并不是每次都拷贝所有
        tem = 0;
        int tempLeft = left;
//        System.out.println("tempLeft:"+tempLeft+", "+"right:"+right);
        //第一次合并 tempLeft = 0 , right = 1 // tempLeft = 2 right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0 right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[tem];
            tem += 1;
            tempLeft += 1;
        }


    }
}
