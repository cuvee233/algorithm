package com.weiyi.leetcode.dp;

/**
 * desc:
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 1.递归
 * 2.动态规划
 * <p>
 * 创建一个数组sum，数组下标为i的值，sum[i]为i出现的总值，将当前数组做打家劫舍的动态规划即可，num[i]的最大值是10^4
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/25
 */
public class DeleteAndEarn {

    public static void main(String[] args) {
        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
        System.out.println(deleteAndEarn.deleteAndEarn(new int[]{3,4,2}));
    }

    public int deleteAndEarn(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        // 将重复元素累加存进数组
        int[] sum = new int[10001];
        for (int num : nums) {
            sum[num] += num;
        }
        // 动态规划求最大值
        return getMax(sum);
    }

    public int getMax(int[] sums) {
        int first = 0, second = 0, ans = 0;
        for (int sum : sums) {
            // 前两个位置的最大值加上当前元素和前一个位置的最大值做对比
            ans = Math.max(sum + first, second);
            // 前两个位置的最大值变成前一个最大值
            first = second;
            // 前一个位置变成当前最大值
            second = ans;
        }
        return ans;
    }

}
