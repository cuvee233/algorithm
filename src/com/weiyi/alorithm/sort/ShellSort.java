package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 希尔排序
 * 1.循环分组，一般为数组长度除以二，依次除以二
 * 2.将同组别的元素进行插入排序
 * 3.直到最后分组为一的时候完成排序
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] nums = {2, 32, 12, 56, 3, 1, 4, 10};
        sort1(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void sort1(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    ArrayUtils.swap(arr, i, i - gap);
                    j -= gap;
                }
            }
            System.out.println();
        }
    }

    private static void sort(int[] nums) {

        int groupCount = nums.length >> 1;

        for (int i = groupCount; i > 0; i >>= 1) {
            // 4 - 2 - 1

            for (int j = 0; j < i; j++) {
                // 0 -> 4 -> 8
                // 1 -> 5
                // 2 -> 6
                // 3 -> 7

                for (int k = j + i; k < nums.length; k += i) {
                    // 插入排序
                    int curIdx = k;
                    int lastIdx = k - i;
                    while (lastIdx >= 0 && nums[curIdx] < nums[lastIdx]) {
                        ArrayUtils.swap(nums, curIdx, lastIdx);
                        curIdx -= i;
                        lastIdx -= i;
                    }
                }

            }

        }
    }
}
