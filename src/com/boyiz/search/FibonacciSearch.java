package com.boyiz.search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch
 * @Description 斐波那契查找
 * @Author boyiz
 * @Date 2022/9/17 15:33
 * @Version 1.0
 **/
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr,189));
    }

    /**
     * @param key 需要查找的关键码
     * @return int 返回对应下标，没有返回-999
     * @Description 斐波那契查找算法
     * @Param arr
     **/
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放 mid 值
        int f[] = fib(); //获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为 f[k] 值 可能大于 arr 的 长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[] ,不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需求使用 arr 数组最后的数填充 temp
        //举例: temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for(int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        // 5. 循环判断
        while (low <= high){
            mid = low + f[k-1]-1;  // 计算查找点
            if (temp[mid] < key){  // 如果查找点小于目标值，说明在右区间
                low = mid + 1; // 右区间起点
                k -= 2; // 右区间长度是 f[i-2]，所以要把 i 换成 i-2
            }else if (temp[mid] > key){    // 如果查找点大于目标值，说明在左区间
                high = mid - 1; // 左区间终点
                k -= 1; // 左区间长度是 f[i-1]，根据所以要把 i 换成 i-1
            }else{  // 如果相等，说明找到了
                /* 找到存在两种可能：一是找到的是原查找表中的元素，二是找到的是填充值。因此需要判别*/
                if (mid <= high){  // 如果是原查找表中的元素，直接返回索引
                    return mid;
                }else{  // 如果找到的是填充值，则返回原查找表最后一个索引
                    return low;
                }
            }
        }
        return -999;
    }

    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


}
