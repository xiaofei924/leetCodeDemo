package com.snis.basic;


import java.util.Stack;

public class ReversePolishNotation {
    private static Stack mExpressionsStack;
    private static Stack mResultStack;
    public static void main(String args[]) {
        //逆波兰式
/*        Input: ["2", "1", "+", "3", "*"]
        Output: 9
        Explanation: ((2 + 1) * 3) = 9
        此题为给出逆波兰式（后续表达式）求中序表达式即为后序转中序表达式*/

        /*
        思路：建两个栈，一个存转化的中序表达式，另一个存放结果。给出后序表达式，操作数入栈，
        遇到操作符出栈两个操作数后出栈的在操作符左侧并加上括号后整体重新入栈
        * */
        //1.初始化两个栈,一个存表达式，一个存结果
        mExpressionsStack = new Stack();
        mResultStack = new Stack();
        //2.将输入的后续表达式入栈进行转换处理
        int result = convertResultExpressions(new String[]{"2", "1", "+", "3", "*"});
//        convertResultExpressions(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"});
        System.out.print("mid Expressions: " + mExpressionsStack + "\n");
        System.out.print("result: " + result + "\n");
    }

    private static int convertResultExpressions(String[] data) {
        if (data == null) {
            return -1;
        }
        for (int i = 0; i < data.length; i++) {
            try {
                convertPolicy(data[i]);
            } catch (Exception e) {
                return -1;
            }
        }
        return mResultStack.size() == 1 ? (int) mResultStack.pop() : -1;
    }

    /**
     * 转换策略
     * @param data
     */
    private static void convertPolicy(String data) {
        System.out.print("mExpressionsStack: " + mExpressionsStack + "\n");
        System.out.print("mResultStack: " + mResultStack + "\n");
        if (isOperator(data)) {
            StringBuilder str = new StringBuilder();
            String s2 = (String) mExpressionsStack.pop();
            String s1 = (String) mExpressionsStack.pop();
            int b = (int) mResultStack.pop();
            int a = (int) mResultStack.pop();
            str.append("(").append(s1).append(data).append(s2).append(")");
            mExpressionsStack.push(str + "");
            mResultStack.push(calculate(a, b, data));
        } else {
            mExpressionsStack.push(data);
            mResultStack.push(Integer.valueOf(data));
        }
    }

    /**
     * 判断是否是操作符
     * @param data
     * @return
     */
    private static boolean isOperator(String data) {
        switch (data) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
        }
        return false;
    }

    private static int calculate(int s1, int s2, String operator) {
        switch (operator) {
            case "+":
                return s1 + s2;
            case "-":
                return s1 - s2;
            case "*":
                return s1 * s2;
            case "/":
                return s1 / s2;
        }
        return -1;
    }
}
