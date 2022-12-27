package com.weiyi.alorithm.serach;

/**
 * 二分查找法
 *
 * @author weiyi
 * @date 2022-12-27
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 8, 9, 12};
        System.out.println(search(arr, 12));
    }


    public static int search(int[] nums, int findValue) {

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start <= end) {
            // 防止数组下标过大导致int溢出，等同于(start + end) / 2
            mid = start + ((end - start) >> 1);

            if (nums[mid] == findValue) {
                return mid;
            } else if (nums[mid] > findValue) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;

    }

}
