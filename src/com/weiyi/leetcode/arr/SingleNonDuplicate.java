package com.weiyi.leetcode.arr;

/**
 * desc:
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 解题思路：题目基本上已经明示让你用二分查找法了，
 * <p>
 * [1,1,2,3,3,4,4,8,8]      4       偶数找一样的那一边
 * [3,3,7,7,10,11,11]       3       奇数找不一样的那一边
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/14
 */
public class SingleNonDuplicate {

    public static void main(String[] args) {
//        System.out.println(singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
        System.out.println(3 ^ 1);

        // 11   01  10
        // 10   01  11

    }


    public static int singleNonDuplicate(int[] nums) {
        int length = nums.length;

        int mid = length >> 1;
        int start = 0;
        int end = length;

        while (start <= end) {
            // 如果mid为第一个元素或者最后一个元素，或者mid和前一个元素、后一个元素都不想等，找到ans
            if ((mid == 0 || mid == length - 1) || (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])) {
                return nums[mid];
            }

            if (mid % 2 == 0) {
                // 偶数找和自己相等的那一边
                if (nums[mid - 1] == nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // 奇数找和nums[mid]不想等的那一边
                if (nums[mid - 1] != nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            mid = (end + start) >> 1;
        }
        return 0;
    }

}
