package com.weiyi.leetcode.dp;

/**
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 * @author weiyi
 * @date 2022/1/25
 */
public class CanJump2 {

    public static void main(String[] args) {
        canJump(new int[]{3,2,1,0,4});
    }

    public static boolean canJump(int[] nums) {
        // 动态规划，数组长度为n，如果能跳到n的位置，则也能跳到n-1的位置

        // 当前能跳到的最远的位置
        int maxIndex = 0;
        for(int i = 0; i < nums.length; i++){
            int curMax = i + nums[i];
            if(i > maxIndex){
                // 到不了后续位置
                return false;
            }
            // 更新最远能到达的位置
            maxIndex = Math.max(maxIndex, curMax);
        }
        return true;
    }
}
