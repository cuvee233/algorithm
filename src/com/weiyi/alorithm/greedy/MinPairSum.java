package com.weiyi.alorithm.greedy;

import java.util.Arrays;

/**
 * 一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。
 * <p>
 * 比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
 * 给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：
 * <p>
 * nums 中每个元素 恰好 在 一个 数对中，且
 * 最大数对和 的值 最小 。
 * 请你在最优数对划分的方案下，返回最小的 最大数对和 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimize-maximum-pair-sum-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：根据规律可以判断出，最大数对的最小值一定是由最大值加上最小值得到的，所以直接找到数组最大值和最小值，相加返回即可 ---- 错误的贪心
 * 反列：[4,1,5,1,2,5,1,5,5,4]
 * <p>
 * 正确解题：先给数组排序，然后从中间开始相加，找到最大值
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class MinPairSum {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length >> 1; i++) {
            if (nums[i] + nums[nums.length - 1 - i] > max) {
                max = nums[i] + nums[nums.length - 1 - i];
            }
        }
        return max;
    }

}
