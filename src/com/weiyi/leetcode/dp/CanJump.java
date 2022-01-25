package com.weiyi.leetcode.dp;

/**
 * desc: 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 1.递归
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/25
 */
public class CanJump {

    public static void main(String[] args) {
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(new int[]{2,3,1,1,4}));
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return nums[0] <= 1;
        }
        return canJump(nums, 0);
    }

    public boolean canJump(int[] nums, int curIndex) {
        if (curIndex == nums.length - 1) {
            // 跳到了最后一个位置（base case）
            return true;
        }
        if (curIndex >= nums.length) {
            // 跳超过了
            return false;
        }
        int cur = nums[curIndex];
        // 从最大步数开始跳,只要有一次能跳到最后，返回true
        for (int i = cur; i > 0; i--) {
            if (canJump(nums, curIndex + i)) {
                return true;
            }
        }
        return false;
    }
}
