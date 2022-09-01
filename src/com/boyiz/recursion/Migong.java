package com.boyiz.recursion;

/**
 * @ClassName Migong
 * @Description 迷宫回溯
 * @Author boyiz
 * @Date 2022/8/31 18:39
 * @Version 1.0
 **/
public class Migong {

    public static void main(String[] args) {
        //创建一个二维数组模拟迷宫
        int map[][] = new int[8][7];
        //使用1表示墙
        //上下全置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        //输出迷宫
        System.out.println("输出迷宫");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归回溯找路径
//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        System.out.println("输出路径");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param x,y 起始位置坐标(x,y)
     * @return boolean  找到路径返回true
     * @Author boyiz
     * @Description //TODO 最终能到达map[6][5]表示找到路径
     * 当map[x][y]表示为0，表示此点未走过
     * 为1，表示墙
     * 为2，表示通路路径
     * 为3，表示已走过但路径不通
     * 策略： 下 》右 》上 》左 ，走不通再回溯
     * @Date 20:18 2022/8/31
     * @Param map   地图
     **/
    public static boolean setWay(int[][] map, int x, int y) {
        //如果终点是2表示通路已找到
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[x][y] == 0) { //当map[x][y]表示为0，表示此点未走过
                //假设此点可以走通，设置为2
                map[x][y] = 2;
                //策略： 下 》右 》上 》左 ，走不通再回溯
                if (setWay(map, x + 1, y)) { //下
                    return true;
                } else if (setWay(map, x, y + 1)) { //右
                    return true;
                } else if (setWay(map, x - 1, y + 1)) { //上
                    return true;
                } else if (setWay(map, x, y - 1)) { //左
                    return true;
                } else {
                    //该点走不通
                    map[x][y] = 3;
                    return false;
                }
            } else {  //如果map[x][y] != 0，则可能是1，2，3
                return false;
            }
        }
    }

    //修改策略，上 》右 》下 》左
    public static boolean setWay2(int[][] map, int x, int y) {
        //如果终点是2表示通路已找到
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[x][y] == 0) { //当map[x][y]表示为0，表示此点未走过
                //假设此点可以走通，设置为2
                map[x][y] = 2;
                //策略： 上 》右 》下 》左 ，走不通再回溯
                if (setWay2(map, x - 1, y + 1)) { //上
                    return true;
                } else if (setWay2(map, x, y + 1)) { //右
                    return true;
                } else if (setWay2(map, x + 1, y)) { //下
                    return true;
                } else if (setWay2(map, x, y - 1)) { //左
                    return true;
                } else {
                    //该点走不通
                    map[x][y] = 3;
                    return false;
                }
            } else {  //如果map[x][y] != 0，则可能是1，2，3
                return false;
            }
        }
    }
}
