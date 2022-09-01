package com.boyiz.recursion;

/**
 * @ClassName Queue8
 * @Description 八皇后问题
 * @Author boyiz
 * @Date 2022/9/1 14:47
 * @Version 1.0
 **/
public class Queue8 {
    //定义皇后数量
    int max = 8;
    //定义一个数组存放结果
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("count= " + count);
        System.out.println("judgeCount= " + judgeCount);

    }

    //放置n个皇后
    //特别注意: check 是 每一次递归时，进入到 check 中都有 for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if (n == 8) {  //n=8表示8个皇后放置完毕
            print();
            return;
        }
        //依次放置皇后
        for (int i = 0; i < max; i++) {
            //先把当前的第i个皇后放在该行的 第一列
            array[n] = i;
            //判断当放置的第n个皇后到第i列时，是否冲突
            if (judge(n)) { //不冲突
                //不冲突则开始放置下一个皇后
                check(n + 1);
            }
            //冲突：继续执行array[n] = i ，即：将第n个皇后放在该行的后一个位置
        }
    }

    /**
     * @return boolean 不冲突为true，冲突为false
     * @Author boyiz
     * @Description // 判断放置位置是否冲突
     * @Date 14:51 2022/9/1
     * @Param n 第n个皇后
     **/
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 表示第n个皇后与前面的n-1个皇后是否在同一列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第 n 个皇后是否和第 i 皇后是否在同一斜线上
            // 行数一直在自增，无需判断
            //（n - i）表示的是两皇后所在的行数的差值  ｜｜  array[n] - array[i]）表示的是两皇后所在的列数的差值。
            // n - i = array[n] - array[i]   可表示为——》 （n - i）/（array[n] - array[i]）= 1
            // 因为（n - i）/（array[n] - array[i]）= 1，而且1 = tan 45°
            // 说明此时两皇后（黑圈）在同一斜线上。
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将结果输出
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
