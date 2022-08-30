package com.boyiz.stack;

/**
 * @ClassName PolandTransform
 * @Description 中缀表达式转后缀表达式
 * @Author boyiz
 * @Date 2022/8/21 17:43
 * @Version 1.0
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 思路分析：
 * 1. 初始化两个栈:运算符栈 s1 和储存中间结果的栈 s2;
 * 2. 从左至右扫描中缀表达式;
 * 3. 遇到操作数时，将其压 s2;
 * 4. 遇到运算符时，比较其与 s1 栈顶运算符的优先级:
 * 4.1. 如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈;
 * 4.2. 否则，若优先级比栈顶运算符的高，也将运算符压入 s1;
 * 4.3. 否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4.a)与 s1 中新的栈顶运算符相比较;
 * 5. 遇到括号时:
 * 5.1. 如果是左括号“(”，则直接压入 s1
 * 5.2. 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6. 重复步骤 2 至 5，直到表达式的最右边
 * 7. 将 s1 中剩余的运算符依次弹出并压入 s2
 * 8.  依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 **/


public class PolandTransform {
    public static void main(String[] args) {
        String expression = "1+((2+3)×4)-5";
        //将中缀表达式转为list
        List<String> list = toInfixExpressionList(expression);
        //将list转为后缀表达式
        List<String> parseSuffixExpreesionList = parseSuffixExpreesionList(list);
        System.out.println(parseSuffixExpreesionList);

    }

    //将 表达式 转为一个List
    public static List<String> toInfixExpressionList(String infixExpression) {
        List<String> list = new ArrayList<>();
        int i = 0;
        String str; //用于多位数的拼接
        char c; //遍历字符存放在c
        do {
            //判断c是非数字，直接加入list
            if ((infixExpression.charAt(i)) < 48 || (infixExpression.charAt(i)) > 57) {
                c = infixExpression.charAt(i);
                list.add("" + c);
                i++; //指针后移
            } else {
                //如果是一个数，需要考虑多位数
                str = ""; //每次都将str置空
                while (i < infixExpression.length() && (infixExpression.charAt(i)) >= 48 && (infixExpression.charAt(i)) <= 57) {
                    c = infixExpression.charAt(i);
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < infixExpression.length());
        return list;
    }

    //将得到的中缀表达式对应的 List => 后缀表达式对应的 List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); // 符号栈
        //说明:因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出 //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<>(); // 储存中间结果的 List s2
        //从左至右扫描中缀表达式;
        for (String l : ls) {
            //判断是否为数字
            boolean matches = l.matches("\\d+");
            if (matches) {  //遇到操作数时，将其压 s2;
                s2.add(l);
            } else if (l.equals("(")) { //如果是左括号“(”，则直接压入 s1
                s1.push(l);
            } else if (l.equals(")")) { //如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// 将( 弹出，消除括号
            } else {  //遇到运算符时，比较其与 s1 栈顶运算符的优先级
                //如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈;
                //当 l 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1) 与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) > Operation.getValue(l)) {
                    s2.add(s1.pop());
                }
                s1.push(l);
            }
        }
        //将 s1 中剩余的运算符依次弹出并加入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    public static int getValue(String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "x":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}