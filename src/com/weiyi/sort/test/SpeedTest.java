package com.weiyi.sort.test;

import com.weiyi.sort.sequencer.AbstractSort;
import com.weiyi.sort.sequencer.MergeSort;
import com.weiyi.sort.utils.ArrayUtils;

import java.util.Arrays;

/**
 * desc: 排序时间测试
 *
 * @author yuanwei
 * @version 1.0
 * @date 2021/7/22
 */
public class SpeedTest {

    public static void main(String[] args) {
        AbstractSort mySort = new MergeSort();

        // 排序的次数
        int sortCount = 5000;
        int maxSize = 10000;
        int maxValue = 1000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < sortCount; i++) {
            int[] arr = ArrayUtils.generateRandomValueArray(maxSize, maxValue);
            mySort.sort(arr);
            Arrays.sort(arr);
        }

        System.out.println("sort finished cost " + (System.currentTimeMillis() - start));
    }

}
