package com.boyiz.stack;

public class Calculate {
    public static void main(String[] args) {
        String expression = "70*2*2-5+1-5+3-4";
        //创建两个栈
        CalculateArrayStack numStack = new CalculateArrayStack(10);
        CalculateArrayStack operStack = new CalculateArrayStack(10);
        int index = 0;
        int result = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char ch = ' ';
        String keepCh = "";
        //循环扫描 expression
        while (true) {
            //依次得到 expression 每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断 ch 是什么内容
            if (operStack.isOper(ch)) { //如果是运算符
                /**
                 * 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中 pop 出两个数,
                 * 再从符号栈中 pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                 */
                if (!operStack.isEmpty()) {
                    //当前的操作符的优先级小于或者等于栈中的操作符
                    if (operStack.operLevel(ch) <= operStack.operLevel(operStack.showTop())) {
                        //从数栈中 pop 出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //从符号栈中 pop 出一个符号
                        oper = operStack.pop();
                        //进行运算，将得到结果，入数栈
                        numStack.push(numStack.cal(num1, num2, oper));
                        //将当前的操作符入符号栈
                        operStack.push(ch);
                    } else {  //当前的操作符的优先级大于栈中的操作符
                        operStack.push(ch);
                    }
                } else {  //符号栈为空，直接入栈
                    operStack.push(ch);
                }
            } else { //如果是数，则直接入数栈
                //numStack.push(ch - 48); //ascii码转换


                //处理多位数
                /**
                 * 分析思路
                 *  1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                 *  2. 在处理数，需要向 expression 的表达式的 index 后再看一位,如果是数就进行扫描，如果是符号
                 *  3. 因此我们需要定义一个变量 字符串，用于拼接
                 */

                keepCh += ch;

                if (index == expression.length() - 1) {  //如果是最后一位则直接入数栈
                    numStack.push(Integer.parseInt(keepCh));
                } else {
                    //1、查看当前数的下一位是否为数，不是 index++
                    if (numStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //为操作符，将keepCh转int，入栈
                        numStack.push(Integer.parseInt(keepCh));
                        //将keepCh重置
                        keepCh = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {  //判断是否扫描完 expression
                break;
            }
        }
        //expression 扫描完毕，将顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到结果，数栈中的就是最后结果
            if (operStack.isEmpty()) {
                break;
            }
            //从数栈中 pop 出两个数
            num1 = numStack.pop();
            num2 = numStack.pop();
            //从符号栈中 pop 出一个符号
            oper = operStack.pop();
            //进行运算，将得到结果，入数栈
            numStack.push(numStack.cal(num1, num2, oper));
        }
        //将数栈中最后的数pop出来
        System.out.println("表达式的result = "+numStack.pop());
    }
}

class CalculateArrayStack {
    private int maxSize;
    private int[] arrayStack;
    private int top = -1;

    //判断操作符优先级
    public int operLevel(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否为操作符
    public boolean isOper(char oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    public CalculateArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arrayStack = new int[maxSize];
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is Full");
            return;
        }
        top++;
        arrayStack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        int value = arrayStack[top];
        top--;
        return value;
    }

    //显示
    public void show() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("Stack[%d] = %d \n", i, arrayStack[i]);
        }
    }

    //显示栈顶
    public int showTop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return arrayStack[top];
    }
}