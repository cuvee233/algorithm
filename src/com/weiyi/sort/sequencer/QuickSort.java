package com.weiyi.sort.sequencer;

/**
 * desc: 快速排序
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

            while (left < right && nums[left] < standard) {
                left++;
            }
            nums[right] = nums[left];

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


//    public static void quickSort(int[] arr) {
//        qsort(arr, 0, arr.length - 1);
//    }

//    private static void qsort(int[] arr, int low, int high) {
//        if (low < high) {
//            //将数组分为两部分
//            int pivot = partition(arr, low, high);
//            //递归排序左子数组
//            qsort(arr, low, pivot - 1);
//            //递归排序右子数组
//            qsort(arr, pivot + 1, high);
//        }
//    }

//    private static int partition(int[] arr, int low, int high) {
//        //枢轴记录
//        int pivot = arr[low];
//        while (low < high) {
//            while (low < high && arr[high] >= pivot) --high;
//            //交换比枢轴小的记录到左端
//            arr[low] = arr[high];
//            while (low < high && arr[low] <= pivot) ++low;
//            //交换比枢轴小的记录到右端
//            arr[high] = arr[low];
//        }
//        //扫描完成，枢轴到位
//        arr[low] = pivot;
//        //返回的是枢轴的位置
//        return low;
//    }
}
