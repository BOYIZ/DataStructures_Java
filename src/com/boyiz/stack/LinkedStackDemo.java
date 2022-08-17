package com.boyiz.stack;


public class LinkedStackDemo {
    public static void main(String[] args) throws Exception {
        LinkedStack linkedStack = new LinkedStack();
        System.out.println(linkedStack.isEmpty());
        linkedStack.push("A");
        linkedStack.push("B");
        linkedStack.push("C");
        linkedStack.push("D");
        System.out.println(linkedStack.length());
//        linkedStack.show();
        System.out.println("-------------");
        linkedStack.pop();
//        linkedStack.show();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.show();

    }

}

class Node {
    public Object object;
    public Node next;

    public Node() {

    }


    public Node(Object object, Node next) {

        this.object = object;
        this.next = next;
    }


    public String toString() {

        return "Node{" +
                "object=" + object +
                ", next=" + next +
                '}';
    }
}

class LinkedStack {

    //这是首结点
    Node node;

    public LinkedStack() {
        node = new Node();
    }


    public void clear() {
        node.next = null;
        node.object = null;
    }


    public boolean isEmpty() {
        return node.object == null ? true : false;
    }


    public int length() {
        if (node.object == null)
            return 0;
        int j = 1;
        Node nodeNew = node;
        while (nodeNew.next != null) {
            j++;
            nodeNew = nodeNew.next;
        }
        return j;
    }

    public void show() {
        if (node.object == null)
            System.out.println("LinkedStack is Empty");
        while (true) {
            if (node.object == null) {
                return;
            }
            System.out.printf("LinkedStack=%s \n",node.object.toString());
            node = node.next;
        }
    }

//    public Object peek() {
//        return node.object;
//    }


    public void push(Object object) throws Exception {
        if (node.object == null) {
            node.object = object;
            return;
        }
        //头插法
        node = new Node(object, node);
    }


    public Object pop() {
        if (node.object == null)
            return new RuntimeException("LinkedStack is Empty");
        Node tem = node;
        node = node.next == null ? new Node() : node.next;
        return tem.object;
    }
}