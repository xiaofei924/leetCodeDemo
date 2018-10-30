
import com.facebook.common.time.SystemClock;
import com.pptv.base.info.SystemInfo;

import java.util.Stack;

public class LongestValidParentheses {
    /**
     * Given a string containing just the characters'('and')', find the length of the longest valid (well-formed) parentheses substring.
     For"(()", the longest valid parentheses substring is"()", which has length = 2.
     Another example is")()())", where the longest valid parentheses substring is"()()", which has length = 4.
     求最长有效括号长度，一对"()"有效长度为2
     * @param args
     */
    public static void main(String[] args) {
//        int result = LongestValidParentheses1(")(((()()()()))");
        int result = LongestValidParentheses1(")()())");
        System.out.print("result: " + result);
    }

    /**
     * 解法一
     * 我的思路是：
     * 有效长度的括号是一段长度的左右括号数相等
     * 那么采用抵消的策略：
     * 栈为空直接入栈
     * 栈中存序号，左括号直接入栈，
     * 遇到右括号时看栈顶：栈顶是左括号直接出栈，出栈后栈为空的话则长度为当前序号i，不为空则前面有未抵消的右括号，i-栈顶序号记为长度
     * 使用Math.max函数不断地取最大值,遍历之后maxLength即为最大长度
     * 时间复杂度O(n)
     * @param parentness
     * @return
     */
    public static int LongestValidParentheses1(String parentness) {
        System.out.print("LongestValidParentheses1 enter" + System.currentTimeMillis() + "\n");
        Stack stack = new Stack();
        int maxLength = 0;
        for (int i = 0; i < parentness.length(); i++) {
            if (parentness.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.empty() && parentness.charAt((Integer) stack.peek()) == '(') {
                    stack.pop();
                    if (stack.empty()) {
                        maxLength = Math.max(maxLength, i);
                    } else {
                        maxLength = Math.max(maxLength, i - (Integer)stack.peek());
                    }
                } else {
                    stack.push(i);
                }
            }
        }
        return maxLength;
    }

    /**
     * 解法二：
     * 网上看到的，只入栈左括号，last为指针是有效括号串的起点
     * @param
     * @return
     */
    public static int LongestValidParentheses2(String s) {
        if(s == null || s.length() <= 0)
            return 0;

        // stack中保存左括弧的index
        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        int last = -1;
        for(int i = 0; i < s.length(); i++){
            // 遇到左括弧就压栈
            if(s.charAt(i) == '(')
                stack.push(i);
            else{
                // 栈为空，更新起始点的位置
                if(stack.isEmpty())
                    last = i;
                else{
                    stack.pop();
                    // 更新合法括弧的长度
                    if(stack.isEmpty())
                        maxLen = Math.max(maxLen, i - last);
                    else
                        maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
