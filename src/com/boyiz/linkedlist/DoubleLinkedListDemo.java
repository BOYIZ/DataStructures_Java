package com.boyiz.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "1", "11");
        HeroNode2 hero2 = new HeroNode2(2, "2", "22");
        HeroNode2 hero3 = new HeroNode2(3, "3", "33");
        HeroNode2 hero4 = new HeroNode2(4, "4", "44");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.showList();
//        System.out.println("------update------");
//        HeroNode2 new_hero4 = new HeroNode2(4, "4444", "4444");
//        doubleLinkedList.update(new_hero4);
//        doubleLinkedList.showList();
//        System.out.println("------del------");
//        doubleLinkedList.del(4);
//        doubleLinkedList.showList();
        doubleLinkedList.addByAsc(hero1);
        doubleLinkedList.addByAsc(hero4);
        doubleLinkedList.addByAsc(hero3);
        doubleLinkedList.addByAsc(hero2);
        doubleLinkedList.showList();


    }
}

class DoubleLinkedList {
    //设置头节点
    HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }


    //新增一个节点
    public void add(HeroNode2 heroNode) {
        //设置临时节点
        HeroNode2 temp = head;
        //循环找到当前链表的最后位置
        while (true) {
            if (temp.next == null) { //链表的最后
                break;
            }
            temp = temp.next; //循环遍历
        }
        //将节点插入链表最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
        heroNode.next = null;
    }

    //按no顺序插入
    //如果有，则添加失败
    public void addByAsc(HeroNode2 heroNode) {
        //设置临时节点
        HeroNode2 temp = head;
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
            if (temp.next != null) {
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                heroNode.pre = temp;
            } else {
                temp.next = heroNode;
                heroNode.pre = temp;
            }

        }
    }

    //从双向链表中删除元素
    //直接找到要删除的节点，找到后直接删除。
    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //设置临时节点
        HeroNode2 temp = head.next;
        //判断节点no是否相同
        Boolean flag = false;
        while (true) {
            if (temp == null) {   //链表最后
                break;
            }
            if (temp.no == no) {  //链表no是否相同
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {  //链表no相同，删除节点
            temp.pre.next = temp.next;
            //如果所删除的节点为最后一个，就不需要执行 temp.next.pre = temp.pre; ，否则出现空指针异常。
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有此编号：%d节点！", no);
        }
    }


    //根据no修改节点信息，no无法修改  ————和单项链表一样
    public void update(HeroNode2 heroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
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

    //显示链表
    public void showList() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //设置临时节点
        HeroNode2 temp = head.next;
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

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向上一个节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

}

