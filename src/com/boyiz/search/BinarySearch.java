package com.boyiz.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BinarySearch
 * @Description 二分查找
 * @Author boyiz
 * @Date 2022/9/16 10:19
 * @Version 1.0
 **/
public class BinarySearch {
    public static void main(String[] args) {
        //二分查找要求数组必须有序
        int[] arr = {1, 3, 6, 88, 999, 2048, 10000};
        System.out.println(binarySearch(arr, 0, arr.length-1, 100000));
        int[] arrMore = {1,3,9,9,10,11,12,22,35};
        System.out.println(binarySearchMore(arrMore, 0, arr.length-1, 9));

    }

    /**
     * @param left      左索引
     * @param right     右索引
     * @param findValue 查找值
     * @return int 找到返回下标，否则返回-999
     * @Param arr 数组
     **/
    public static int binarySearch(int[] arr, int left, int right, int findValue) {
        //当left>right，说明没找到直接返回
        if (left > right) {
            return -999;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue) { //向右递归
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return binarySearch(arr, left, mid-1, findValue);
        } else {
            return mid;
        }
    }
    //思考：查找值不止一个，如何查找所有的下标，返回一个结果集
    /*
    思路：
        1、找到mid索引值，先不返回
        2、向mid索引左边扫描，将所有满足 findValue 的元素下标加入ArrayList
        3、向mid索引右边扫描，将所有满足 findValue 的元素下标加入ArrayList
        4、返回ArrayList
     **/
    public static List binarySearchMore(int[] arr, int left, int right, int findValue) {
        //当left>right，说明没找到直接返回
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        if (findValue > midValue) { //向右递归
            return binarySearchMore(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return binarySearchMore(arr, left, mid-1, findValue);
        } else {
            //思路执行
            List<Integer> list = new ArrayList<>();
            //向mid索引左边扫描，将所有满足 findValue 的元素下标加入List
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }

            list.add(mid);

            //向mid索引右边扫描，将所有满足 findValue 的元素下标加入List
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }

            return list;
        }

    }
}
