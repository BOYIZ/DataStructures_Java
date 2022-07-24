package com.boyiz.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleList circleSingleList = new CircleSingleList();
        circleSingleList.addBoy(50);
        circleSingleList.show();

        circleSingleList.countBoy(12,2,50);

    }
}

//创建一个环形单向链表
class CircleSingleList {
    //新建一个first节点
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //数据校验
        if (nums < 1) {
            System.out.println("数据小于1，无法创建。");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据节点编号创建小孩节点
            Boy boy = new Boy(i);
            //如果当前是第一个节点
            if (i == 1) {
                first = boy;
                boy.setNext(first);  //构成环形
                curBoy = first;  //curBoy指向第一个
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环
    public void show() {
        //是否为空
        if (first == null) {
            System.out.println("");
            return;
        }
        //first不能动，加入辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("编号：%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                System.out.println("遍历完成！");
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移
        }
    }

    //根据用户输入，计算出圈顺序
    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入!");
            return;
        }
        //辅助变量helper，指向最后一个节点.
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //报数前，先将first和helper移动 k-1 （startNo-1）次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时，让fist和helper移动 m-1 （countNum-1）次，然后出圈
        //循环操作，直至圈中只剩一个
        while (true) {
            if (helper == first) {  //圈中只有一个节点
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时 first 指向的节点，就是要出圈的节点
            System.out.printf("节点：%d\n",first.getNo());
            //节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("last节点：%d\n",first.getNo());
    }
}

//创建一个class，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}