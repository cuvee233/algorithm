package com.weiyi.sort.test;

import com.weiyi.sort.sequencer.AbstractSort;
import com.weiyi.sort.sequencer.MergeSort;
import com.weiyi.sort.sequencer.QuickSort;
import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * desc: 对数器测试
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/22
 */
public class ResultTest {

    public static void main(String[] args) {
        AbstractSort mySort = new QuickSort();

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
            mySort.sort(copyNums);

            boolean isEqual = ArrayUtils.isEqual(nums, copyNums);
            if (!isEqual) {
                ArrayUtils.printArr(copyNums);
                break;
            }
        }
    }


}
