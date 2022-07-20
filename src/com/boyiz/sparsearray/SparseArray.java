package com.boyiz.sparsearray;

public class SparseArray {
    /**
     * 稀疏数组
     *
     */
    public static void main(String[] args) {
        //创建一个原始的二维数组
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        //遍历输出原始数组
        System.out.println("原始数组为：");
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //遍历数组非零元素
        int nonZero = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    nonZero++;
                }
            }
        }
        System.out.println("非零元素个数为：" + nonZero);

        //创建稀疏数组
        int sparseArray[][] = new int[nonZero + 1][3];
        //稀疏数组初始化赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = nonZero;
        //遍历原始二维数组，将非零元素添加至稀疏数组
        int count = 0;      //用于记录当前为第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] =i;
                    sparseArray[count][1]=j;
                    sparseArray[count][2]=chessArray[i][j];
                }
            }
        }
        //遍历稀疏数组
        System.out.println("稀疏数组为：");
        for (int[] row : sparseArray) {
            for (int i : row) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        //稀疏数组恢复二维数组
        int[][] restoreArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            int row = sparseArray[i][0];
            int col = sparseArray[i][1];
            int value = sparseArray[i][2];
            restoreArray[row][col] = value;
        }

        //遍历输出恢复数组
        System.out.println("恢复数组为：");
        for (int[] row : restoreArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }
}
