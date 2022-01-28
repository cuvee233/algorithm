package com.weiyi.leetcode.dp;

/**
 * desc: 求环型数组的最大子数组和
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/27
 */
public class MaxSubarraySumCircular {

    public static void main(String[] args) {

    }

    /**
     * 分三种情况
     * 1.最大子数组和不成环    -- 最大子数组和的解法
     * 2.最大子数组成环        -- 最大子数组成环，最小子数组不成环，最大子数组 = 所有子数组和 - 最小子数组和
     * 3.全部为负数            -- 最小子数组和所有子数组和子数组和相等，判断最大子数组和是否小于0，小于直接返回
     */
    public int maxSubarraySumCircular(int[] nums) {
        // 当前位置的最大子数组和
        int curMax = 0;
        // 最大子数组和
        int max = nums[0];
        // 当前位置的最小组数组和
        int curMin = 0;
        // 最小子数组和
        int min = nums[0];
        // 总数组和
        int total = 0;

        for (int num : nums) {
            curMax = Math.max(curMax + num, num);
            max = Math.max(curMax, max);
            curMin = Math.min(curMin + num, num);
            min = Math.min(curMin, min);
            total += num;
        }

        return max < 0 ? max : Math.max(max, total - min);
    }
}
