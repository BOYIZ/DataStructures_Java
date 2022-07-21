package com.boyiz.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);//最大长度为3，实际只能存放两个，空出一个空间作为约定。
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出~~");
    }
}

class CircleArrayQueue {
    private int front;//队列头
    private int rear;//队列尾
    private int maxSize;//队列长度
    private int[] array;//存放数据，模拟队列

    //队列构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        array[rear] = n;
        //考虑取模
        rear = (rear + 1) % maxSize;
    }

    //出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        //1.将array[front]保存在临时变量中
        //2.将front后移
        //3.返回临时变量
        int temp = array[front];
        //考虑取模
        front = (front + 1) % maxSize;
        return temp;
    }

    //显示队列
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历，遍历多少个
        for (int i = front; i < front + size(); i++) {
            System.out.printf("array[%d]=%d\n", i % maxSize, array[i % maxSize]);
        }
    }

    //当前队列有效数据
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return array[front];
    }
}