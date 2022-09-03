package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName QuickSort
 * @Description 快速排序
 * @Author boyiz
 * @Date 2022/9/3 14:16
 * @Version 1.0
 **/
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        //测试80000个数据 0s   8000000 1s
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l_Index = left;
        int r_Index = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (l_Index < right) {
            //在pivot左边一直找，直到找到大于等于pivot才退出
            while (arr[l_Index] < pivot) {
                l_Index++;
            }
            //在pivot右边一直找，直到找到小于等于pivot才退出
            while (arr[r_Index] > pivot) {
                r_Index--;
            }
            //如果 l_Index >= r_Index 说明 pivot 的左右两的值，已经按照左边全部是小于等于 pivot 值，右边全部是大于等于 pivot 值
            if (l_Index >= r_Index) {
                break;
            }
            //交换
            temp = arr[l_Index];
            arr[l_Index] = arr[r_Index];
            arr[r_Index] = temp;
            //如果交换完后，发现这个 arr[l_Index] == pivot 值 相等 r_Index--， rIndex前移
            if (arr[l_Index] == pivot) {
                r_Index--;
            }
            if (arr[r_Index] == pivot) {
                l_Index++;
            }

        }
        // 如果 l_Index == r_Index, 必须 l++, r--, 否则为出现栈溢出
        if (l_Index == r_Index) {
            l_Index++;
            r_Index--;
        }
        //左边递归
        if (left < r_Index) {
            quickSort(arr, left, r_Index);
        }
        //右边递归
        if (right>r_Index) {
            quickSort(arr, l_Index, right);
        }

    }
}
