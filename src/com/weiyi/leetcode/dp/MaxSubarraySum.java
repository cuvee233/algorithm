package com.weiyi.leetcode.dp;

/**
 * desc: 求最大子数组和
 * <p>
 * 动态规划
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/27
 */
public class MaxSubarraySum {

    public static void main(String[] args) {
        MaxSubarraySum maxSubarraySum = new MaxSubarraySum();
        System.out.println(maxSubarraySum.maxSubarraySum(new int[]{3,-1,2,-1}));
    }

    public int maxSubarraySum(int[] nums) {
        // 上一个位置的最大子数组和
        int lastMax = 0;
        // 最大子数组和
        int max = Integer.MIN_VALUE;
        // 上一个位置的最小组数组和
        int lastMin = 0;
        // 最小子数组和
        int min = Integer.MAX_VALUE;

        int total = 0;

        for (int num : nums) {
            // 求当前位置子数组的最大值
            lastMax = Math.max(lastMax, 0) + num;
            // 当前位置和目前为止的最大值取最大
            max = Math.max(lastMax, max);

            // 当前位置最小值
            lastMin = Math.min(lastMin, 0) + num;
            // 目前最小值
            min = Math.min(min, lastMin);

            total += num;
        }

        return max < 0 ? max:Math.max(max, total - min);
    }
}
