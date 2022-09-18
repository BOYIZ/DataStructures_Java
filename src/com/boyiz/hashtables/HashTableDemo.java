package com.boyiz.hashtables;

import java.util.Scanner;

/**
 * @ClassName HashTableDemo
 * @Description 哈希表
 * @Author boyiz
 * @Date 2022/9/18 09:35
 * @Version 1.0
 **/
public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("show: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "show":
                    hashTab.showList();
                    break;
                case "find":
                    System.out.println("输入要查找的 id");
                    int no = scanner.nextInt();
                    hashTab.findById(no);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建hashtable，管理多条链表
class HashTab {

    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示共有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        //不要忘记分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //add
    public void add(Emp emp) {
        //根据id判断要加入哪条链表
        int no = hashFunction(emp.id);
        //将emp放入对应的链表中
        empLinkedListArray[no].add(emp);
    }

    //遍历
    public void showList() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].showList(i);
        }
    }

    //根据id查找
    public void findById(int id) {
        //根据id判断在哪条链表
        int no = hashFunction(id);
        Emp emp = empLinkedListArray[no].findEmpById(id);
        if (emp != null) {
            System.out.print("在第" + no + "条链表中找到id=" + id + "的雇员信息：");
            System.out.printf(" => id=%d , name=%s \n", emp.id, emp.name);
        } else {
            System.out.println("在哈希表中未找到id=" + id + "的雇员信息！！！");
        }
    }

    //散列函数，取模
    public int hashFunction(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList
class EmpLinkedList {
    //头指针，直接指向第一个Emp
    private Emp head;

    /*
    添加雇员到链表
    假设id自增，id从小到大，直接加入链表的最后
     **/
    public void add(Emp emp) {
        //添加第一个
        if (head == null) {
            head = emp;
            return;
        }
        //不是第一个使用临时指针，定位到最后一个
        Emp tempEmp = head;
        while (true) {
            if (tempEmp.next == null) {
                break;
            }
            tempEmp = tempEmp.next;
        }
        //将新雇员添加到最后
        tempEmp.next = emp;
    }

    //遍历链表
    public void showList(int no) {
        if (head == null) {
            System.out.println("链表:"+no+"为空~");
            return;
        }
        System.out.print("链表:"+no+"信息: ");
        Emp tempEmp = head;
        while (true) {
            System.out.printf(" => id=%d , name=%s \t", tempEmp.id, tempEmp.name);
            if (tempEmp.next == null) {
                break;
            }
            tempEmp = tempEmp.next;
        }
        System.out.println();
    }
    //根据id查找emp，未找到返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp tempEmp = head;
        while (true) {
            if (tempEmp.id == id) {
                break;  //tempEmp指向要查找的emp
            }
            if (tempEmp.next == null) {
                return null;
            }
            tempEmp = tempEmp.next;
        }
        return tempEmp;
    }
}
