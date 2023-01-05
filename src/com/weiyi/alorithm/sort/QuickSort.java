package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 快速排序
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class QuickSort {

    public static void main(String[] args) {
        // 排序对比的次数
        int sortCount = 1000;
        int maxSize = 100;
        int maxValue = 100;

        boolean success = true;
        for (int i = 0; i < sortCount; i++) {
            // 生成数组
            int[] nums = ArrayUtils.generateAllRandomArray(maxSize, maxValue);
            int[] copyNums = Arrays.copyOf(nums, nums.length);

            // 正确的排序
            ArrayUtils.rightMethod(nums);
            // 自定排序
            sortByRecursion(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                success = false;
                ArrayUtils.printArr(copyNums);
                break;
            }
        }
        System.out.println(success ? "isSuccess!!!" : "isFailed!!!");
//        sortByRecursion(new int[]{56, 23, 12, 45});
    }

    /**
     * 快速排序递归版本实现
     */
    public static void sortByRecursion(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public static void sort(int[] nums, int start, int end) {
        if (start < end) {
            // 分区
            int[] partArr = partition(nums, start, end);
            // 左边排序
            sort(nums, start, partArr[0] - 1);
            // 右边排序
            sort(nums, partArr[1] + 1, end);
        }
    }

    private static int[] partition(int[] nums, int start, int end) {
        // 标准值
        int stand = nums[start];

        int left = start;
        int right = end;
        int cur = start;
        while (cur <= right) {
            if (nums[cur] > stand) {
                // 大于
                ArrayUtils.swap(nums, right--, cur);
            } else if (nums[cur] < stand) {
                // 小于
                ArrayUtils.swap(nums, left++, cur++);
            } else {
                // 等于
                cur++;
            }

        }

        return new int[]{left, right};
    }


}
