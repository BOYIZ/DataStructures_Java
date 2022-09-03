package com.boyiz.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName ShellSort
 * @Description 希尔排序
 * @Author boyiz
 * @Date 2022/9/3 11:41
 * @Version 1.0
 **/
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));

        //测试80000个数据  交换法 5s   移动法 0s
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
        shellSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }

    public static void shellSort(int[] arr) {

        //交换法
//        for (int gap = arr.length; gap > 0; gap /= 2) {
//            int temp = 0;
//            for (int i = gap; i < arr.length; i++) {
//                //遍历各组中的元素，共gap组，，步长为gap (j -= gap)
//                for (int j = i - gap; j >= 0; j -= gap) {
//                    //如果当前元素大于步长元素，需要交换
//                    if (arr[j] > arr[j + gap]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + gap];
//                        arr[j + gap] = temp;
//                    }
//                }
//            }
//        }
        //优化：移动法
        for (int gap = arr.length; gap > 0; gap /= 2) {
            //从第gap个元素，诸葛对其所在组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出while表示找到位置
                    arr[j] = temp;
                }
            }
        }

/*
        //第一轮shell > [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        //将数据10个数据分为5组
        int temp = 0;
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中的元素，共五组，每组两个元素，步长为5 (j -= 5)
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于步长元素，需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮结果" + Arrays.toString(arr));

        //第二轮shell > [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        //将数据10个数据分为5/2 = 2组
        temp = 0;
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于步长元素，需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮结果" + Arrays.toString(arr));

        //第三轮shell > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        //将数据10个数据分为5/2/2 = 1组
        temp = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于步长元素，需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮结果" + Arrays.toString(arr));

*/

    }
}
