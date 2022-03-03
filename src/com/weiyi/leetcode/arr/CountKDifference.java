package com.weiyi.leetcode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * |3 - 4| = 1
 * |5 - 4| = 1
 * 给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。
 * <p>
 * 解题思路：利用map存储每个数字出现的次数，
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/9
 */
public class CountKDifference {
    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            int max = num + k;
            int min = num - k;
            // 符合|nums[i] - nums[j]｜条件的个数
            count += countMap.getOrDefault(max, 0) + countMap.getOrDefault(min, 0);
            // 当前元素出现的次数+1
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
