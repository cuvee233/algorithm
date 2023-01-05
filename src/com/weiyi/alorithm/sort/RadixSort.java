package com.weiyi.alorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * <p>
 * 1.求所有数里面的最高位
 * 2.建立count数组，for循环，依次从个位开始遍历，count数组记录每次遍历0-9数字出现的次数
 * 3.将0-9数字出现的次数的count数组变成累加数组
 * 4.从原始数组后往前遍历，找到对应的位置防止
 * 5.循环往复
 *
 * @author weiyi
 * @date 2023-01-03
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] nums = {1, 232, 12, 1113};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        sort(nums, 0, nums.length - 1, getRadix(nums));
    }

    public static void sort(int[] nums, int start, int end, int maxRadix) {
        // 每次排序好的数组
        int[] help = Arrays.copyOfRange(nums, start, end + 1);

        for (int i = 1; i <= maxRadix; i++) {
            // 0-9每一位出现次数的数组
            int[] radixCounts = new int[10];

            for (int j = start; j <= end; j++) {
                int curBit = getCurBit(nums[j], i);
                radixCounts[curBit]++;
            }

            // 转化成累加数组
            for (int j = 1; j < radixCounts.length; j++) {
                radixCounts[j] = radixCounts[j - 1] + radixCounts[j];
            }

            // 从后往前排序,依次排序
            for (int k = end; k >= start; k--) {
                int index = radixCounts[getCurBit(nums[k], i)]--;
                help[index - 1] = nums[k];
            }

            for (int j = 0; j < help.length; j++) {
                nums[j] = help[j];
            }
        }
    }

    /**
     * 求第几位数字（十进制）
     *
     * @param num  当前数值
     * @param dist 第几位，从1开始，1代表个位
     * @return 求第几位数字（十进制）
     */
    private static int getCurBit(int num, int dist) {
        return (num / (int) Math.pow(10, dist - 1)) % 10;
    }

    /**
     * 求数组元素的最大位数（十进制）
     *
     * @param nums 数组
     * @return 最大位数
     */
    private static int getRadix(int[] nums) {
        int maxRadix = 0;
        for (int num : nums) {
            int curRadix = 0;
            while (num > 0) {
                num /= 10;
                curRadix++;
            }
            maxRadix = Math.max(curRadix, maxRadix);
        }
        return maxRadix;
    }


}
