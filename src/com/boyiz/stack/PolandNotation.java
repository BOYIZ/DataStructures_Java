package com.boyiz.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class PolandNotation {

    public static void main(String[] args) {
        /**
         * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
         *  1.从左至右扫描，将 3 和 4 压入堆栈;
         *  2.遇到+运算符，因此弹出 4 和 3(4 为栈顶元素，3 为次顶元素)，计算出 3+4 的值，得 7，再将 7 入栈; 3.将 5 入栈;
         *  4.接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈;
         *  5.将 6 入栈;
         *  6.最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
         */
        // (3+4)x5-6 -->  3 4 + 5 x 6 -
//        String suffixExpression = "3 4 + 5 x 6 -";
        String suffixExpression = "4 5 x 8 - 60 + 8 2 / +";
        //思路分析
        //  1、先将 suffixExpression 转为一个List
        //  2、将 List 传给一个方法，完成遍历 配合栈 完成计算
        List<String> listString = getListString(suffixExpression);

        System.out.println(calculate(listString));

    }

    //先将 suffixExpression 转为一个List
    public static List<String> getListString(String suffixExpression) {
        String[] s = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : s) {
            list.add(ele);
        }
        return list;
    }

    //对list遍历 配合栈 完成计算
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack();
        for (String s : list) {
            //使用正则表达式匹配数字
            if (s.matches("\\d+")) { //匹配多位数
                //匹配数字则入栈
                stack.push(s);
            } else {
                //pop两个数并运算，将结果push
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (s) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "x":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push(result + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

}
