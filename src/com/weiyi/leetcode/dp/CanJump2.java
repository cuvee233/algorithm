package com.weiyi.leetcode.dp;

/**
 * desc: todo
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/26
 */
public class CanJump2 {

    public static void main(String[] args) {
        CanJump2 canJump = new CanJump2();
        System.out.println(canJump.jump(new int[]{2, 3, 1, 1, 4, 3}));
    }

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        return jump(nums, 0);
    }

    /**
     * 返回当前index在nums数组中跳到最后一位的最小次数
     */
    public int jump(int[] nums, int index) {
        if (index + nums[index] >= nums.length - 1) {
            // 可以跳到最后一位
            System.out.println("index = " + index + " res = " + 1);
            return 1;
        }

        // 可以跳的步数
        int num = nums[index];

        int min = Integer.MAX_VALUE;
        for (int i = num + index; i > index; i--) {
            min = Math.min(min, jump(nums, i));
        }

        System.out.println("index = " + index + " res = " + (min + 1));
        return min + 1;
    }

}
