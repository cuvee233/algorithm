package com.weiyi.leetcode.arr;

import java.util.Arrays;

/**
 * desc:给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * <p>
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * <p>
 * 返回可能的 最小差值 。
 * <p>
 * 解题思路：先排序，然后按k个数对比
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/11
 */
public class MinimumDifference {

    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }

    public static int minimumDifference(int[] nums, int k) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= length - k; i++) {
            int res = nums[i + k - 1] - nums[i];
            ans = Math.min(ans, res);
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
