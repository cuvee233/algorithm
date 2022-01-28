package com.weiyi.leetcode.dp;

/**
 * desc: 给你一个正整数数组 values，其中 values[i]表示第 i 个观光景点的评分，并且两个景点i 和j之间的 距离 为j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 * 求values[i] + values[j] + i - j的最大值
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/28
 */
public class MaxScoreSightseeingPair {

    public static void main(String[] args) {
        int[] values = {1, 2, 4, 2, 6, 23, 5, 35, 2};
        System.out.println(maxScoreSightseeingPair(values));
        System.out.println(maxScoreSightseeingPair2(values));
    }


    public static int maxScoreSightseeingPair(int[] values) {
        int ans = 0;
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length; j++) {
                ans = Math.max(values[i] + values[j] + i - j, ans);
            }
        }
        return ans;
    }

    public static int maxScoreSightseeingPair2(int[] values) {
        int maxI = values[0];
        int ans = 0;
        // values[i] + i    values[j] - j
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(values[i] - i + maxI, ans);
            maxI = Math.max(maxI, values[i] + i);
        }
        return ans;
    }

    // [8,1,5,2,6]

//    public int maxScoreSightseeingPair(int[] values, int curIndex) {
//        if(curIndex == values.length){
//            return 0;
//        }
//
//
//    }

}
