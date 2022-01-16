package com.weiyi.sort.sequencer;

/**
 * desc: 快速排序实现。时间复杂度O（n²）
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/22
 */
public class QuickSort implements AbstractSort {
    @Override
    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int left, int right) {

        if (left < right) {
            // 数组分区，找到居中位置下标
            int mid = partition(nums, left, right);
            // 左边排序
            sort(nums, left, mid - 1);
            // 右边排序
            sort(nums, mid + 1, right);
        }
    }

    /**
     * 数组分区，返回中间坐标
     *
     * @param nums  数组
     * @param left  左边坐标
     * @param right 右边坐标
     * @return int
     */
    private int partition(int[] nums, int left, int right) {
        // 最后的坐标为标准值
        int standard = nums[right];

        // 遍历整个数组
        while (left < right) {

            // 如果左边元素比标准值小，左边指针右移
            while (left < right && nums[left] < standard) {
                left++;
            }
            // 当前左边元素大于标准值，移动到最后
            nums[right] = nums[left];

            // 如果右边元素大于等于标准值，右边指针左移
            while (left < right && nums[right] >= standard) {
                right--;
            }

            // 右边元素比中间值小，将右边元素交换至左边
            nums[left] = nums[right];

        }

        // 中间位置等于标准值
        nums[left] = standard;

        return left;
    }
}
