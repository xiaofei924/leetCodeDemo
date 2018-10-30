import java.util.List;
import java.util.Stack;

import static java.lang.Math.max;

public class LargestRecTangleInHistogram {
    public static void main(String[] args) {
        int result = largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        System.out.print(result);
    }

//    public static int calculateLargestArea(int[] height) {
//
//        int n = height.length;
//        int result = 0;
//        Stack s = new Stack();
//        for (int i = 0; i < n; ++i) {
//            while ((!s.isEmpty()) && (height[(int) s.peek()] >= height[i])) {
//                int h = height[(int) s.peek()];
//                s.pop();
//                result = Math.max(result, (i - 1 - (s.isEmpty() ? (-1) : (int) s.peek())) * h);
//            }
//            s.push(i);
//        }
//        while (!s.isEmpty()) {
//            int h = height[(int) s.peek()];
//            s.pop();
//            result = Math.max(result, (n - 1 - (s.isEmpty() ? (-1) : (int) s.peek())) * h);
//        }
//        return result;
//    }


    public static int largestRectangleArea(int[] height) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int len = height.length;
        for (int i = 0; i < len; i++) {
            //栈中存储的是递增高度对应的index
            if (stack.empty() || height[stack.peek()] <= height[i]) {
                stack.push(i);
                System.out.print(i + "\n");
            } else {
                //碰到不满足递增的就干掉比当前高度大对应的index，直到当前高对应的index可以入栈
                int high = height[stack.pop()];
                //stack.peek()+1确定左边界，右边界是i，公式=高*(右边界-左边界)
                int width = stack.empty() ? i : i - stack.peek() - 1;
                result = Math.max(result, high * width);
                System.out.print(i + "\n");
                i--;
            }
            System.out.print(stack +  ", result: " + result + "\n");
        }
        while(!stack.empty()){
            int index = stack.pop();
            int high = height[index];
            //之前被干掉的也比当前的高度高，所以也被包括在左右边界之间
            int width = stack.empty() ? len : len - stack.peek() - 1;
            result = Math.max(result, high * width);
        }
        return result;
    }
}
