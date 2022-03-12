package com.weiyi.leetcode.dp;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 *
 * @author weiyi
 * @date 2022/1/26
 */
public class CanJump3 {

    public static void main(String[] args) {
        CanJump3 canJump3 = new CanJump3();
        System.out.println(canJump3.jump(new int[]{1, 1, 1, 1}));
    }

    public int jump(int[] nums) {
        int length = nums.length;
        // 从后往前跳，动态规划
        if (length == 1) {
            return 0;
        }
        // dp表，存储每个位置的元素往最少多少步能跳到最后一个位置
        int[] dp = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            // 当前元素直接能跳到最后一位，dp[i]=0
            if (i + nums[i] >= length - 1) {
                dp[i] = 1;
                continue;
            }

            int min = Integer.MAX_VALUE - 1;
            // 当前元素一次挑不到最后一个位置，找到能跳到的位置次数最少的元素次数加一为当前元素最少步数
            for (int j = nums[i]; j > 0; j--) {
                min = Math.min(min, dp[j + i]);
            }
            // 处理为零的情况
            dp[i] = min + 1;
        }
        return dp[0];
    }

}
