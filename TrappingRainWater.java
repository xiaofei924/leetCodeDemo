import java.util.Stack;

public class TrappingRainWater {
    /**
     * 题目：
     * Given[0,1,0,2,1,0,1,3,2,1,2,1], 的直方图，三个高中低的直方可以存雨水，求存的所有雨水的面积
     * return6.
     */

    /**
     * 给了边界的高度（黑色部分），让求能装的水量（蓝色部分）。 为啥能用单调栈来做呢？我们先来考虑一下，什么情况下可以装下水呢，
     * 是不是必须两边高，中间低呢？我们对低洼的地方感兴趣，就可以使用一个单调递减栈，将递减的边界存进去，一旦发现当前的数字大于栈顶元素了，
     * 那么就有可能会有能装水的地方产生。此时我们当前的数字是右边界，我们从栈中至少需要有两个数字，才能形成一个坑槽，先取出的那个最小的数字，
     * 就是坑槽的最低点，再次取出的数字就是左边界，我们比较左右边界，取其中较小的值为装水的边界，然后此高度减去水槽最低点的高度，
     * 乘以左右边界间的距离就是装水量了。由于需要知道左右边界的位置，所以我们虽然维护的是递减栈，但是栈中数字并不是存递减的高度，
     * 而是递减的高度的坐标。这应该属于单调栈的高级应用了，可能并不是那么直接就能想出正确的解法。
     * @param arg
     */

    public static final void main(String arg[]) {
        int result = trappingRainWaterAreaSum(new int[]{0, 1, 0, 2, 1, 0, 3, 2, 1, 2, 1});
        System.out.print("result: " + result + "");
    }

    /**
     * 解题思路:
     * 解法一：遍历int数组，
     *         1.栈为空直接序号直接入栈，栈不为空，若高度低于或等于栈顶则入栈；
     *         2.若高度高于栈顶将栈顶出栈并计算出栈后记为坑点，若出栈后栈为空重复上一步，不为空的栈顶和当前高度取低值和坑点高度差值计算可以接雨水面积，累加。
     * 时间复杂度O(n)
     *下面注掉的是之前错误的想法，没有利用好栈的特性，导致思路偏差，以后要注意！！！
     * @return
     */
    public static int trappingRainWaterAreaSum(int[] height){
/*        Stack<Integer> stack = new Stack<>();
        int rainWaterArea = 0;

        for (int i = 0;i< height.length;i++) {
            if (stack.empty()) {
                stack.push(i);
            } else {
                if (height[i] <= height[stack.peek()]) {
                    System.out.print("continue: 第 "+ i + " 个：" + height[i]+"\n");
                    continue;
                } else {
                    rainWaterArea += calculateRainWaterArea(stack.pop(), i, height);
                    stack.push(i);
                }
            }
            System.out.print("rainWaterArea: " + rainWaterArea + "\n");
        }
        return rainWaterArea;*/

        Stack<Integer> s = new Stack<Integer>();
        int i = 0, n = height.length, res = 0;
        while (i < n) {
            if (s.isEmpty() || height[i] <= height[s.peek()]) {
                s.push(i++);
            } else {
                int t = s.pop();
                if (s.isEmpty()) continue;
                res += (Math.min(height[i], height[s.peek()]) - height[t]) * (i - s.peek() - 1);
            }
        }
        return res;
    }

/*    *//**
     * （currentHeight - startHeight - 1）* height[startHeight] - (startHeight+1到currentHeight-1的高度)
     * @param startHeight
     * @param currentHeight
     * @param height
     * @return
     *//*
    private static int calculateRainWaterArea(int startHeight, int currentHeight, int[] height) {
        int diffArea = (currentHeight - startHeight - 1) * height[startHeight];
        System.out.print("diffArea1: " + diffArea + "\n");
//                - (startHeight+1到currentHeight-1的高度)
        for (int i = startHeight+1; i < currentHeight; i++) {
            diffArea -= height[i];
        }
        System.out.print("diffArea2: " + diffArea + "\n");
        return diffArea;
    }*/

}
