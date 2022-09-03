package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName RadixSort
 * @Description 基数排序
 * @Author boyiz
 * @Date 2022/9/3 16:57
 * @Version 1.0
 **/
public class RadixSort {
    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));
        //测试80000个数据 0s   8000000 1s
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        radixSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void radixSort(int[] arr) {
        //找到最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶实际存放的数据个数，定义一个一维数组来记录存放个数
        int[] bucketElementCounts = new int[10];

        for (int z = 0, n = 1; z < maxLength; z++, n *= 10) {
            //针对每个元素对应的位进行排序
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素对应位的值
                int digitOfEle = arr[i] / n % 10;
                //放入对应的桶中
                bucket[digitOfEle][bucketElementCounts[digitOfEle]] = arr[i];
                bucketElementCounts[digitOfEle]++;
            }
            //按照桶的顺序依次取出放入原数组
            int index = 0;
            //遍历每一个桶，并将桶中数据放入原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                //如果桶中有数据
                if (bucketElementCounts[i] != 0) {
                    //循环该桶
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        //取出数据放入arr
                        arr[index] = bucket[i][j];
                        index++;
                    }
                }
                //第i+1处理后，将bucketElementCounts[i]置零
                bucketElementCounts[i] = 0;
            }
        }




        /*

        //        第一轮，针对个位数
        //        定义一个二位数组，表示10个桶，每个桶是一个一维数组
        //        防止溢出将桶大小定义为 arr.length
        //        基数排序是经典的空间换时间的算法

        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶实际存放的数据个数，定义一个一维数组来记录存放个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的个位数
            int digitOfEle = arr[i] % 10;
            //放入对应的桶中
            bucket[digitOfEle][bucketElementCounts[digitOfEle]] = arr[i];
            bucketElementCounts[digitOfEle]++;
        }
        //按照桶的顺序依次取出放入原数组
        int index = 0;
        //遍历每一个桶，并将桶中数据放入原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //如果桶中有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    //取出数据放入arr
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            //第一轮处理后，将bucketElementCounts[i]置零
            bucketElementCounts[i] = 0;
        }
        System.out.println("1. " + Arrays.toString(arr));
        //第二轮
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的十位数
            int digitOfEle = arr[i] / 10 % 10;
            //放入对应的桶中
            bucket[digitOfEle][bucketElementCounts[digitOfEle]] = arr[i];
            bucketElementCounts[digitOfEle]++;
        }
        //按照桶的顺序依次取出放入原数组
        index = 0;
        //遍历每一个桶，并将桶中数据放入原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //如果桶中有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    //取出数据放入arr
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("2. " + Arrays.toString(arr));
        //第三轮
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的百位数
            int digitOfEle = arr[i] / 100 % 10;
            //放入对应的桶中
            bucket[digitOfEle][bucketElementCounts[digitOfEle]] = arr[i];
            bucketElementCounts[digitOfEle]++;
        }
        //按照桶的顺序依次取出放入原数组
        index = 0;
        //遍历每一个桶，并将桶中数据放入原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //如果桶中有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    //取出数据放入arr
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("2. " + Arrays.toString(arr));

         */
    }
}
