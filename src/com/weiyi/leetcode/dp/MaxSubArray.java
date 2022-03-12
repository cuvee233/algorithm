package com.weiyi.leetcode.dp;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 *
 * @author weiyi
 * @date 2022/1/26
 */
public class MaxSubArray {

    Integer count = 0;

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
//        2 4 3 6 2 3 1 - 1 4
        int[] nums = {1,322131,123345,132,-334,-23,24352,-98};
        System.out.println(maxSubArray.maxSubArray(nums));
        System.out.println(maxSubArray.maxSubArray2(nums));
        System.out.println(maxSubArray.maxSubArray3(nums));
    }

    /**
     * 暴力递归,O(n^2)
     */
    public int maxSubArray(int[] nums) {
        // 解题思路，后面子规模数组的和如果为正数与自己相加，如果为负数，则抛弃

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, maxSubArray(nums, i));
        }

        return max;
    }

    /**
     * 求从当前i位置往后的子数组最大值
     */
    public int maxSubArray(int[] nums, int index) {
//        System.out.println(++count); count = 54
        if (index == nums.length) {
            // 数组越界
            return 0;
        }

        // 后续子数组的最大值
        int nextMax = maxSubArray(nums, index + 1);

        // 后续最大值如果为负数则忽略
        return nums[index] + Math.max(nextMax, 0);
    }


    /**
     * 递归+记忆化搜索
     */
    public int maxSubArray2(int[] nums) {
        // 解题思路，后面子规模数组的和如果为正数与自己相加，如果为负数，则抛弃

        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length + 1];

        // 初始化dp表默认值
        Arrays.fill(dp, Integer.MIN_VALUE);

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, maxSubArray2(nums, i, dp));
        }

        return max;
    }

    /**
     * 求从当前i位置往后的子数组最大值
     */
    public int maxSubArray2(int[] nums, int index, int[] dp) {
        int cache = dp[index];
        if (cache != Integer.MIN_VALUE) {
            return cache;
        }

        if (index == nums.length) {
            dp[index] = 0;
            // 数组越界
            return 0;
        }

        // 后续子数组的最大值
        int nextMax = maxSubArray2(nums, index + 1, dp);

        // 后续最大值如果为负数则忽略
        int curMax = nums[index] + Math.max(nextMax, 0);
        dp[index] = curMax;
        return curMax;
    }


    /**
     * 动态规划，从后往前推
     */
    public int maxSubArray3(int[] nums) {
        int curMax = Integer.MIN_VALUE;
        int lastMax = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            lastMax = Math.max(0, lastMax) + nums[i];
            curMax = Math.max(curMax, lastMax);
        }
        return curMax;
    }
}
