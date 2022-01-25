package com.weiyi.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统
 * ，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 1.递归+记忆化搜索
 * 2.动态规划dp[i] = Math.Max(dp[i - 2] + num[i], dp[i-1])
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/25
 */
public class Rob {

    public static void main(String[] args) {
        Rob rob = new Rob();
        int[] nums = {1,2,1,10,20};
        System.out.println(rob.rob(nums));
        System.out.println(rob.rob2(nums));
        System.out.println(rob.rob3(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> cache = new HashMap<>();
        return rob(nums, 0, cache);
    }

    public int rob(int[] nums, int index, Map<Integer, Integer> cache) {
        Integer cacheNum = cache.get(index);
        if (cacheNum != null) {
            return cacheNum;
        }

        if (index >= nums.length) {
            // 跳到空的位置
            cache.put(index, 0);
            return 0;
        }

        if (index == nums.length - 1) {
            // 跳到最后一位
            cache.put(index, nums[index]);
            return nums[index];
        }

        // 选当前元素 pk 不选当前元素
        int max = Math.max(nums[index] + rob(nums, index + 2, cache), rob(nums, index + 1, cache));
        cache.put(index, max);
        return max;
    }

    public int rob2(int[] nums) {
        // O(n)空间复杂度的动态规划
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < length; i++) {
            // 选当前位置元素和不选当前元素比较最大值
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int rob3(int[] nums) {
        // O(1)空间复杂度的动态规划
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int ans = 0;
        int lastlast = 0, last = 0;
        for (int num : nums) {
            // 选当前位置元素和不选当前元素比较最大值
            ans = Math.max(num + lastlast, last);
            lastlast = last;
            last = ans;
        }
        return ans;
    }
}
