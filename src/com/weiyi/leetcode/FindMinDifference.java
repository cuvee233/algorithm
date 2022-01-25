package com.weiyi.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * desc:给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * 1.堆排序，然后两两比较
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/18
 */
public class FindMinDifference {
    public int findMinDifference(List<String> timePoints) {
        // 排序
        int[] arr = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            arr[i] = getTimeMinute(timePoints.get(i));
        }
        Arrays.sort(arr);

        // 前后对比最小值
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] < ans) {
                ans = arr[i + 1] - arr[i];
            }
        }
        // 处理边界情况，00:00和23:59只相差一分钟
        return Math.min(ans, arr[0] + 1440 - arr[arr.length - 1]);
    }

    private int getTimeMinute(String timePoint) {
        return Integer.parseInt(timePoint.substring(0, 2)) * 60 + Integer.parseInt(timePoint.substring(3));
    }
}
