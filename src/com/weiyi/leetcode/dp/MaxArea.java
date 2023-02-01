package com.weiyi.leetcode.dp;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-01-31
 */
public class MaxArea {


    /**
     * 双指针解法，可有理解为贪心，贪心的策略是，左右两条边放弃较小的那条边
     *
     * @param height 容量数组
     * @return 最大水量
     */
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;

        int max = 0;
        while (start < end) {
            max = Math.max(Math.min(height[start], height[end]) * (end - start), max);
            if (height[start] > height[end]) {
                end--;
            } else {
                start++;
            }

        }
        return max;
    }


    /**
     * 动态规划(超出内存限制)
     *
     * @param height 容量数组
     * @return 最大水量
     */
    public int maxArea3(int[] height) {
        int length = height.length;
        int[][] dp = new int[length][length];

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < dp[i].length; j++) {
                if (j == i + 1) {
                    dp[i][j] = Math.min(height[i], height[j]);
                } else {
                    // 以start为头，以end结尾
                    int res = Math.min(height[i], height[j]) * (j - i);

                    // 以start为头，不以end结尾
                    res = Math.max(dp[i][j - 1], res);

                    // 不以start为头，以end结尾
                    res = Math.max(dp[i + 1][j], res);
                    dp[i][j] = res;
                }
            }
        }
        return dp[0][length - 1];
    }


    public int maxArea2(int[] height) {
        return maxArea2(height, 0, height.length - 1);
    }

    /**
     * 递归来解决问题，求height数组start到end位置可以储存的最大水量
     *
     * @param height 原始数组
     * @param start  开始索引
     * @param end    结束索引
     * @return 最大水量
     */
    public int maxArea2(int[] height, int start, int end) {
        if (start == end - 1) {
            return Math.min(height[start], height[end]);
        }
        // 以start为头，以end结尾
        int res = Math.min(height[start], height[end]) * (end - start);

        // 以start为头，不以end结尾
        res = Math.max(maxArea2(height, start, end - 1), res);

        // 不以start为头，以end结尾
        res = Math.max(maxArea2(height, start + 1, end), res);

        return res;
    }


}
