package com.boyiz.search;

import java.util.Arrays;

/**
 * @ClassName InsertValueSearch
 * @Description 插值查找
 * @Author boyiz
 * @Date 2022/9/16 18:35
 * @Version 1.0
 **/
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));
        System.out.println(insertValueSearch(arr,0, arr.length-1,1));
    }

    //插值查找算法

    /**
     * @param left      左索引
     * @param right     右索引
     * @param findValue 查找值
     * @return int 找到返回下标，否则返回-999
     * @Param arr 数组
     **/
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //当left>right，或者findValue 小于最小值，或者大于最大值，说明没找到直接返回
        // findValue < arr[0] 和 findValue > arr[arr.length - 1] 两个条件必须有，否则得到的mid可能越界  ex：findValue = 9000000
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -999;
        }
        //自适应
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue) { //向右递归
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }
}
