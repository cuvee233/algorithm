package com.weiyi.leetcode.dp;

/**
 * desc: 求数组最大子集乘积
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/27
 */
public class MaxProduct {

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxProduct(new int[]{-2,3,-4}));
    }

    //  [2,3,-2,4]
    //  [-2,3,-4]
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(maxProduct(nums, i), max);
        }
        return max;
    }

    public int maxProduct(int[] nums, int index) {
        // 从当前index位置往后一个一个乘
        if (index == nums.length - 1) {
            return nums[index];
        }

        // 下个位置的最大值
        int lastMax = maxProduct(nums, index + 1);

        return Math.max(lastMax * nums[index], lastMax);
    }

}
