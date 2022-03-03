package com.weiyi.leetcode.arr;

import java.util.Arrays;

/**
 * desc:
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
 * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * <p>
 * nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/3/3
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums = {1, 2};
        System.out.println(removeDuplicates.removeDuplicates(nums));
        System.out.println();
        Arrays.stream(nums).forEach(System.out::println);
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pre = 0;
        int cur = 0;

        while (cur < nums.length) {
            if (pre == cur || nums[pre] == nums[cur]) {
                cur++;
            } else {
                nums[++pre] = nums[cur++];
            }
        }
        return pre + 1;
    }

}
