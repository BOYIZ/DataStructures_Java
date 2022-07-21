package com.boyiz.linkedlist;

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
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByAsc(hero1);
        singleLinkedList.addByAsc(hero3);
        singleLinkedList.addByAsc(hero4);
//        singleLinkedList.addByAsc(hero2);
//        singleLinkedList.addByAsc(hero5);
//        singleLinkedList.addByAsc(hero6);
//        singleLinkedList.showList();
//        singleLinkedList.update(new_hero6);
        singleLinkedList.showList();
//        singleLinkedList.del(6);
        singleLinkedList.del(1);
        singleLinkedList.del(3);
        singleLinkedList.del(4);
        singleLinkedList.showList();
    }
}

class SingleLinkedList {
    //设置头节点
    HeroNode head = new HeroNode(0, "", "");

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


