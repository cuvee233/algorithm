package com.weiyi.sort.utils;

import java.util.Arrays;

/**
 * desc: 自定集合工具类
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/22
 */
public class ArrayUtils {

    public static void printArr(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            sb.append(nums[i]);
            if (i != length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * 绝对正确的排序方法
     *
     * @param arr 数组
     */
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 生成随机长度随机数值的数组
     * Math.random() -> double [0,1)
     * (int) ((size + 1) * Math.random()) -> [0,size]整数
     * 生成长度随机[0, size]的数组
     *
     * @param size  数组的长度
     * @param value 最大值
     * @return 数组
     */
    public static int[] generateAllRandomArray(int size, int value) {

        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // 一个随机数减去另一个随机数，生成[-value, value]的随机数
            arr[i] = (int) ((value + 1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }


    /**
     * 随机元素数组，长度固定
     *
     * @param size  数组长度
     * @param value 数组元素最大值
     * @return int[]
     */
    public static int[] generateRandomValueArray(int size, int value) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            // 一个随机数减去另一个随机数，生成[-value, value]的随机数
            arr[i] = (int) ((value + 1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }

    /**
     * 判断两个数组是否相等
     *
     * @param arr1 数组A
     * @param arr2 数组B
     * @return boolean
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }
        if ((arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
