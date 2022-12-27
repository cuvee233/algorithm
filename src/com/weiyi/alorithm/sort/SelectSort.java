package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author weiyi
 * @date 2022-12-27
 */
public class SelectSort {

    public static void main(String[] args) {

        // 排序对比的次数
        int sortCount = 10;
        int maxSize = 100;
        int maxValue = 1000;

        for (int i = 0; i < sortCount; i++) {
            // 生成数组
            int[] nums = ArrayUtils.generateAllRandomArray(maxSize, maxValue);
            int[] copyNums = Arrays.copyOf(nums, nums.length);

            // 正确的排序
            ArrayUtils.rightMethod(nums);
            // 自定排序
            sort(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                ArrayUtils.printArr(copyNums);
                break;
            }
        }
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minInx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minInx]) {
                    minInx = j;
                }
            }
            if (minInx != i) {
                ArrayUtils.swap(arr, minInx, i);
            }

        }
    }

}
