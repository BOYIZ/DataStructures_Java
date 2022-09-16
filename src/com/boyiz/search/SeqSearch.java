package com.boyiz.search;

import java.util.ArrayList;

/**
 * @ClassName SeqSearch
 * @Description 线性查找
 * @Author boyiz
 * @Date 2022/9/16 09:12
 * @Version 1.0
 **/
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        System.out.println("下标为："+seqSearch(arr, 34));
        int[] arrMore = {1, 9, 11, -1, 34, 89, 1, 102, 99, 27, 1};
        System.out.println("下标为："+seqSearchMore(arrMore, 1));

    }

    //找到一个就返回
    public static int seqSearch(int arr[], int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -999;
    }
    public static ArrayList seqSearchMore(int arr[], int value) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                result.add(i);
            }
        }
        return result;
    }
}
