package com.boyiz.linkedlist;

import java.awt.font.TextMeasurer;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //Test
        //创建节点
        HeroNode hero1 = new HeroNode(1, "1", "11");
        HeroNode hero2 = new HeroNode(2, "2", "22");
        HeroNode hero3 = new HeroNode(3, "3", "33");
        HeroNode hero4 = new HeroNode(4, "4", "44");
        HeroNode hero5 = new HeroNode(5, "5", "55");
        HeroNode hero6 = new HeroNode(6, "6", "66");
        HeroNode new_hero6 = new HeroNode(6, "6", "6666");

        //加入
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.add(hero1);
        singleLinkedList1.add(hero3);
        singleLinkedList1.add(hero4);
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(hero2);
        singleLinkedList2.add(hero5);
        singleLinkedList2.add(hero6);
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.addByAsc(hero1);
//        singleLinkedList.addByAsc(hero3);
//        singleLinkedList.addByAsc(hero4);
//        singleLinkedList.addByAsc(hero2);
//        singleLinkedList.addByAsc(hero5);
//        singleLinkedList.addByAsc(hero6);
//        singleLinkedList.showList();
//        singleLinkedList.update(new_hero6);
//        singleLinkedList.showList();
//        System.out.println("length:"+SingleLinkedList.length(singleLinkedList.getHead()));
//        System.out.println("LastIndex:"+SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(),4));
//        SingleLinkedList.reversetList(singleLinkedList.getHead());
//        singleLinkedList.showList();
//        SingleLinkedList.reversePrint(singleLinkedList.getHead());
//        singleLinkedList.del(6);
//        singleLinkedList.del(1);
//        singleLinkedList.del(3);
//        singleLinkedList.del(4);
//        singleLinkedList.showList();
//        System.out.println(SingleLinkedList.length(singleLinkedList.getHead()));
//        SingleLinkedList.mergerLinkedList(singleLinkedList1, singleLinkedList2);

        SingleLinkedList.mergerLinkedList(singleLinkedList1, singleLinkedList2).showList();
    }
}

class SingleLinkedList {
    //设置头节点
    HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //查找单链表中的倒数第 k 个结点 【新浪面试题】 //思路
    //1. 编写一个方法，接收 head 节点，同时接收一个 index
    //2. index 表示是倒数第 index 个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    // 5. 如果找到了，则返回该节点，否则返回 null
    public static HeroNode findLastIndexNode(HeroNode heroNode, int index) {
        //判断链表是否为空
        if (heroNode.next == null) {
            return null;
        }
        int length = length(heroNode);
        if (index <= 0 || index > length) {
            return null;
        }
        //设置临时节点
        HeroNode temp = heroNode.next;
        for (int i = 1; i <= length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //将单链表反转
    public static void reversetList(HeroNode heroNode) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode temp = heroNode.next;
        HeroNode tempNext = null; //指向temp的下一个节点
        HeroNode reversetList = new HeroNode(0, "", "");

        while (temp != null) {
            tempNext = temp.next;
            temp.next = reversetList.next;
            reversetList.next = temp;
            temp = tempNext;//temp后移
        }
        heroNode.next = reversetList.next;
    }

    //从尾到头打印单链表，用栈实现
    public static void reversePrint(HeroNode heroNode) {
        //判断链表是否为空
        if (heroNode.next == null) {
            return;
        }
        //创建一个栈，将节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = heroNode.next;
        //遍历节点压入栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //将栈中节点pop出
        while (!stack.empty()) {  //判断stack是否为空，不为空pop并打印
            System.out.println(stack.pop());
        }
    }

    //合并两个有序链表
    public static SingleLinkedList mergerLinkedList(SingleLinkedList s1, SingleLinkedList s2) {
        if (s1 == null) {
            return s2;
        } else if (s2 == null) {
            return s1;
        }
        //设置新链表的头结点
        HeroNode newNode = new HeroNode(0, "", "");
        SingleLinkedList mergeList = new SingleLinkedList();
        //将新头节点加入新的单链表中
        mergeList.head = newNode;
        //两个临时指针分别指向两个单链表的第一个节点
        HeroNode temp1 = s1.head.next;
        HeroNode temp2 = s2.head.next;
        while (temp1 != null && temp2 != null) {
            //如果s1的no小于等于s2的no，将s1的当前节点加入到新的链表中，s1指针下移，否则s2加入新链表，s2下移
            if (temp1.no <= temp2.no) {
                newNode.next = temp1;
                temp1 = temp1.next;
            } else {
                newNode.next = temp2;
                temp2 = temp2.next;
            }
            newNode = newNode.next;
        }
        //其中一条链表结束后，将另一条单链表上剩余节点的加入新链表之后
        newNode.next = (temp1 == null) ? temp2 : temp1;
        return mergeList;
    }


    //获取节点有效个数
    public static int length(HeroNode heroNode) {
        //判断链表是否为空
        if (heroNode.next == null) {
            return 0;
        }
        int length = 0;
        //设置临时节点
        HeroNode temp = heroNode.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //新增一个节点
    public void add(HeroNode heroNode) {
        //设置临时节点
        HeroNode temp = head;
        //循环找到当前链表的最后位置
        while (true) {
            if (temp.next == null) { //链表的最后
                break;
            }
            temp = temp.next; //循环遍历
        }
        //将节点插入链表最后
        temp.next = heroNode;
    }

    //按no顺序插入
    //如果有，则添加失败
    public void addByAsc(HeroNode heroNode) {
        //设置临时节点
        HeroNode temp = head;
        //标识是否存在待插入节点no
        Boolean flag = false;
        //循环找到当前链表的最后位置
        while (true) {
            if (temp.next == null) {  //链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { //找到要插入的位置
                break;
            } else if (temp.next.no == heroNode.no) {  //判断节点no是否存在
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {   //节点存在
            System.out.printf("当前ID已存在：%d ，无法插入\n", heroNode.no);
        } else {  //插入temp后
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //根据no修改节点信息，no无法修改
    public void update(HeroNode heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        //判断节点是否找到
        Boolean flag = false;
        while (true) {
            if (temp == null) {  //链表最后
                break;
            }
            if (temp.no == heroNode.no) {  //找到no相同的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {  //修改节点信息
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有此编号：%d节点！", heroNode.no);

        }

    }

    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //设置临时节点
        HeroNode temp = head;
        //判断节点no是否相同
        Boolean flag = false;
        while (true) {
            if (temp.next == null) {   //链表最后
                break;
            }
            if (temp.next.no == no) {  //链表no是否相同
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {  //链表no相同，删除节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有此编号：%d节点！", no);
        }
    }


    //显示链表
    public void showList() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //设置临时节点
        HeroNode temp = head.next;
        while (true) {
            //判断是否为链表最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }


}


//定义HeroNode，每个HereNode对象是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一个节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

}


