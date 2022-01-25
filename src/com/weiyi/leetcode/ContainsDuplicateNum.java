package com.weiyi.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * desc: 219.给你一个整数数组nums 和一个整数k ，判断数组中是否存在两个 不同的索引i和j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 1.用map存储 num[i] -> i  ,当遇到num[j] == num[i] 时，判断是否j - i <= k
 * <p>
 * 2.滑动窗口，set里面存储索引0-k的元素，如果存在相同的元素，则存在相同的元素，往后遍历时移除前面的元素
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/19
 */
public class ContainsDuplicateNum {

    public static void main(String[] args) {
        containsNearbyDuplicate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15);
    }

    // [1,2,3,4,5,6,7,8,9,10]
    //15
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer lastIndex = map.get(nums[i]);
            if (lastIndex != null && (i - lastIndex) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;

    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                // 遍历的元素大于k的个数，移除之前的元素
                set.remove(nums[i - k - 1]);
            }
            // 如果k范围内存在两个相同的值，找到结果
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
