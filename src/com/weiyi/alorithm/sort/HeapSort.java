package com.weiyi.alorithm.sort;

import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author weiyi
 * @date 2022-12-30
 */
public class HeapSort {


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
            heapSort(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                ArrayUtils.printArr(copyNums);
                break;
            }
        }

    }

    public static void heapSort(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        // 将当前数组变为大根堆
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums, i);
        }

        // 拿到最大值交换
        int size = nums.length;
        ArrayUtils.swap(nums, 0, --size);

        while (size > 0) {
            heapify(nums, 0, size);
            ArrayUtils.swap(nums, 0, --size);
        }
    }

    /**
     * 从下向上找父节点比较
     *
     * @param nums  数据数组
     * @param index 当前位置开始做heapInsert
     */
    public static void heapInsert(int[] nums, int index) {
        // 父元素大于当前值
        while (nums[(index - 1) / 2] < nums[index]) {
            ArrayUtils.swap(nums, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    /**
     * 从上向下找子节点比较
     *
     * @param nums  数据数组
     * @param index 当前位置开始做heapInsert
     */
    public static void heapify(int[] nums, int index, int size) {
        int left = (2 * index) + 1;
        while (left < size) {
            // 当前节点和自己的左右子节点选一个最大的出来
            int largest = (((left + 1) < size) && (nums[left] < nums[left + 1])) ? left + 1 : left;
            largest = nums[largest] > nums[index] ? largest : index;

            if (largest == index) {
                // 当前节点已经比它所有子节点大，不用再向下寻找
                break;
            }

            // 和子节点交换
            ArrayUtils.swap(nums, largest, index);
            // 大的节点变为当前索引
            index = largest;
            // 向下找子节点
            left = (2 * index) + 1;
        }

    }
}
