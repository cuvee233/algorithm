package com.weiyi.leetcode.LinkedList.random;

import java.util.Random;

/**
 * 398.给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * @author weiyi
 * @date 2022/1/16
 */
public class RandomNode398 {
    private int[] nums;
    private Random random;

    public RandomNode398(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {
        int index = 1;
        int length = nums.length;
        int ans = -1;
        for (int i = 0; i < length; i++) {
            // 蓄水池采样法，保证每一个等于target的下标被选中的几率都是一致的
            if (nums[i] == target && random.nextInt(index++) == 0) {
                ans = i;
            }
        }
        return ans;
    }
}
