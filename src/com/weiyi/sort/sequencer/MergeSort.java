package com.weiyi.sort.sequencer;

import com.weiyi.sort.utils.ArrayUtils;

/**
 * desc: 归并排序
 * <p>
 * 两个元素一组相比较，按升序排列
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/22
 */
public class MergeSort implements AbstractSort {

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] nums = {2, 5, 3, 98, 4, 7, 3};
        sort.sort(nums);
        ArrayUtils.printArr(nums);
    }

    @Override
    public void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int left, int right) {
        // 左指针和右指针同一个位置，说明为最小粒度
        if (left == right) {
            return;
        }
        // 找到中间索引
        int mid = left + ((right - left) >> 1);
        // 左子数组排序
        sort(nums, left, mid);
        // 右子数组排序
        sort(nums, mid + 1, right);
        // 合并左右数组，按升序排列
        merge(nums, left, mid, right);
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int start = left;
        int rightIndex = mid + 1;
        int tempIndex = 0;
        // 左右集合遍历比较，按升序排列存入临时数组
        while (left <= mid && rightIndex <= right) {
            temp[tempIndex++] = nums[left] < nums[rightIndex] ? nums[left++] : nums[rightIndex++];
        }

        // 左边数组未遍历完
        while (left <= mid) {
            temp[tempIndex++] = nums[left++];
        }

        // 右边数组未遍历完
        while (rightIndex <= right) {
            temp[tempIndex++] = nums[rightIndex++];
        }

        // 复制数组
        for (int num : temp) {
            nums[start++] = num;
        }
    }

}
