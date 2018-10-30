import java.util.Stack;

public class ValidParentheses {
    /**
     题目描述

     Given a string containing just the characters'(',')','{','}','['and']', determine if the input string is valid.
     The brackets must close in the correct order,"()"and"()[]{}"are all valid but"(]"and"([)]"are not.

     /**
     * 20. Valid ParentTheses
     * 有效的括号
     * <p>
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 示例 1:
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     */
    public static void main(String[] args) {
//        int result = IsValidParentheses(")(((()()()()))");
        boolean result = IsValidParentheses2(")(){}[](())");
//        boolean result = IsValidParentheses("(){}[](()");
        System.out.print("result: " + result);
    }

    /**
     * 解法一：
     * 这题比较简单，从左到右扫描，相邻两个是否是一对，是的话左半边出栈，
     * 不是的话当前坐标压栈，最后看栈是否为空，空的话字符串合法
     * 还需优化，写的比较麻烦
     * @param s
     * @return
     */
    public static boolean IsValidParentheses(String s) {
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (stack.empty()) {
                stack.push(i);
            } else {
                switch (s.charAt(i)) {
                    case ')':
                        if (s.charAt((Integer) stack.peek()) == '(') {
                            stack.pop();
                        } else {
                            stack.push(i);
                        }
                        break;
                    case ']':
                        if (s.charAt((Integer) stack.peek()) == '[') {
                            stack.pop();
                        } else {
                            stack.push(i);
                        }
                        break;
                    case '}':
                        if (s.charAt((Integer) stack.peek()) == '{') {
                            stack.pop();
                        } else {
                            stack.push(i);
                        }
                        break;
                    default:
                        stack.push(i);
                        break;
                }

            }

        }
        System.out.print("stack: " + stack + "\n");
        return stack.empty();
    }

    /**
     * 解法二：
     * 优化了一下，这个题不需要存序号，直接匹配括号就行
     * 能匹配上就出栈，匹配不上就是字符串无效返回false
     * 或者最后遍历完了，看一下栈是否为空，空的话全部匹配返回true，不为空返回false
     * @param s
     * @return
     */
    public static boolean IsValidParentheses2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else if (!stack.empty() && s.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (!stack.empty() && s.charAt(i) == '}' && stack.peek() == '{') {
                stack.pop();
            } else if (!stack.empty() && s.charAt(i) == ']' && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
}
