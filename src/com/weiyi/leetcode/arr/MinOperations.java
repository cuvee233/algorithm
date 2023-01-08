package com.weiyi.leetcode.arr;

/**
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * <p>
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * <p>
 * 递归解决一切问题
 *
 * @author weiyi
 * @date 2023-01-07
 */
public class MinOperations {

    public static void main(String[] args) {
        MinOperations minOperations = new MinOperations();
        System.out.println(minOperations.minOperations(new int[]{1, 1, 4, 2, 3}, 5));
    }

    public int minOperations(int[] nums, int x) {
        return minOperations(nums, 0, nums.length - 1, x, 0);
    }

    /**
     * 求num数组索引start到索引end位置移除x的最小操作数
     */
    public int minOperations(int[] nums, int start, int end, int x, int ans) {
        if (x == 0) {
            // 减到0了
            return ans;
        }
        if (start > end || x < 0) {
            return -1;
        }

        // 1.移除最左边元素求最小操作数
        int leftMin = minOperations(nums, start + 1, end, x - nums[start], ans + 1);
        // 2.移除最右边元素求最小操作数
        int rightMin = minOperations(nums, start, end - 1, x - nums[end], ans + 1);

        if (leftMin == -1) {
            return rightMin;
        }

        if (rightMin == -1) {
            return leftMin;
        }

        return Math.min(rightMin, leftMin);
    }

}
