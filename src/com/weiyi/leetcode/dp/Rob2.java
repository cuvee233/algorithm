package com.weiyi.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * 1.递归，和rob1解题思路一致，只不过需要考虑两种情况，一种是选一个房子，一种是选最后一个房子，两种逻辑相排斥最终做比较
 * 2.动态规划
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/25
 */
public class Rob2 {

    public static void main(String[] args) {
        Rob2 rob2 = new Rob2();
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob2.rob(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> cache1 = new HashMap<>();
        Map<Integer, Integer> cache2 = new HashMap<>();
        return Math.max(rob(nums, 0, nums.length - 2, cache1), rob(nums, 1, nums.length - 1, cache2));
    }

    public int rob(int[] nums, int index, int end, Map<Integer, Integer> cache) {
        Integer cacheNum = cache.get(index);
        if (cacheNum != null) {
            return cacheNum;
        }

        if (index > end) {
            // 跳到空的位置
            cache.put(index, 0);
            return 0;
        }

        if (index == end) {
            // 跳到最后一位
            cache.put(index, nums[index]);
            return nums[index];
        }

        // 选当前元素 pk 不选当前元素
        int max = Math.max(nums[index] + rob(nums, index + 2, end, cache), rob(nums, index + 1, end, cache));
        cache.put(index, max);
        return max;
    }

    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 0-(length-1)求最大值、1-length求最大值
        return Math.max(rob2(nums, 0, length - 1), rob2(nums, 1, length));
    }

    public int rob2(int[] nums, int start, int end) {
        // O(1)空间复杂度的动态规划
        int ans = 0;
        int lastlast = 0, last = 0;
        for (int i = start; i < end; i++) {
            // 选当前位置元素和不选当前元素比较最大值
            ans = Math.max(nums[i] + lastlast, last);
            lastlast = last;
            last = ans;
        }
        return ans;
    }
}
