package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class MergeSort {


    public static void main(String[] args) {

        // 排序对比的次数
        int sortCount = 4000;
        int maxSize = 100;
        int maxValue = 100;

        for (int i = 0; i < sortCount; i++) {
            // 生成数组
            int[] nums = ArrayUtils.generateAllRandomArray(maxSize, maxValue);
            int[] copyNums = Arrays.copyOf(nums, nums.length);

            // 正确的排序
            ArrayUtils.rightMethod(nums);
            // 自定排序
            sortByCycle(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                ArrayUtils.printArr(copyNums);
                break;
            }
        }
    }

    /**
     * 归并排序循环版本
     *
     * @param nums 排序数组
     */
    public static void sortByCycle(int[] nums) {
        // 步长
        int step = 1;
        while (true) {
            int length = nums.length;
            if (step >= length) {
                break;
            }
            int left = 0;

            while (left < length) {

                int mid = left + step - 1;
                if (mid >= length) {
                    break;
                }
                // 防止越界
                int right = Math.min(mid + step, length - 1);

                // 合并
                merge(nums, left, mid, right);

                left = right + 1;
            }


            step <<= 1;
        }

    }


    /**
     * 递归版本归并排序
     *
     * @param nums 排序数组
     */
    public static void sortByRecursion(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public static void sort(int[] nums, int left, int right) {
        // base case，当l=r时返回
        if (left == right) {
            return;
        }

        // 计算mid
        int mid = left + ((right - left) >> 1);

        // 左边排序
        sort(nums, left, mid);
        // 右边排序
        sort(nums, mid + 1, right);
        // 合并数据
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int curIndex = 0;
        // 按大小排列
        while (p1 <= mid && p2 <= right) {
            tempArr[curIndex++] = nums[p1] > nums[p2] ? nums[p2++] : nums[p1++];
        }

        // 左边没处理完
        while (p1 <= mid) {
            tempArr[curIndex++] = nums[p1++];
        }
        // 右边没处理完
        while (p2 <= right) {
            tempArr[curIndex++] = nums[p2++];
        }
        // 排序好的数元素写回原数组
        for (int i = 0; i < tempArr.length; i++) {
            nums[left + i] = tempArr[i];
        }
    }

}
