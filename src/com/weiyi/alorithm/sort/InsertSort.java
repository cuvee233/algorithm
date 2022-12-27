package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author weiyi
 * @date 2022-12-27
 */
public class InsertSort {


    public static void main(String[] args) {

        // 排序对比的次数
        int sortCount = 1000;
        int maxSize = 100;
        int maxValue = 1000;

        boolean isOk = true;

        for (int i = 0; i < sortCount; i++) {
            // 生成数组
            int[] orgNums = ArrayUtils.generateAllRandomArray(maxSize, maxValue);
//            int[] orgNums = {10, 2, 5, 14, 2};
            int[] nums = Arrays.copyOf(orgNums, orgNums.length);
            int[] copyNums = Arrays.copyOf(orgNums, orgNums.length);

            // 正确的排序
            ArrayUtils.rightMethod(nums);
            // 自定排序
            sort(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                isOk = false;
                ArrayUtils.printArr(orgNums);
                ArrayUtils.printArr(copyNums);
                break;
            }
        }

        System.out.println(isOk ? "isOk!!!" : "isNotOk!!!");
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) {
            return;
        }

//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 1; j <= i + 1 && arr[j - 1] > arr[j]; j++) {
//                ArrayUtils.swap(arr, j - 1, j);
//            }
//        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 0 && arr[j - 1] > arr[j]; j--) {
                ArrayUtils.swap(arr, j - 1, j);
            }
        }
    }

}
