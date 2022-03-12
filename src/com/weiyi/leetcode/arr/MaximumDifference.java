package com.weiyi.leetcode.arr;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * <p>
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 * <p>
 * 思路：选定第一个元素为最小元素，后续依次遍历，如果遍历到的元素大于之前最小元素，则计算之前的差值并判断是否目前最小。如果遍历到的元素小于当前最小元素，则替换最小元素
 *
 * @author weiyi
 * @date 2022/2/26
 */
public class MaximumDifference {

    public static void main(String[] args) {
        MaximumDifference maximumDifference = new MaximumDifference();
        System.out.println(maximumDifference.maximumDifference(new int[]{7,1,5,4}));
    }

    public int maximumDifference(int[] nums) {
        int maxDiff = -1;
        int minNum = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > minNum) {
                maxDiff = Math.max(num - minNum, maxDiff);
            } else if (num < minNum) {
                minNum = num;
            }
        }
        return maxDiff;
    }
}
