package com.weiyi.leetcode.dp;

/**
 * desc: todo
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/29
 */
public class MaxProfit2 {

    public static void main(String[] args) {
        MaxProfit2 maxProfit2 = new MaxProfit2();
        System.out.println(maxProfit2.maxProfit(new int[]{3,9,4,5,8,10}));
    }

    public int maxProfit(int[] prices) {
        // 两种情况，一种是逢低必买，逢高必卖。另一种是买低卖高一次交易。比较两种情况的最大值
        int multipleMax = 0;
        int lastMin = prices[0];
        int onceMax = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 买低卖高一次性交易
            if (minPrice > prices[i]) {
                minPrice = Math.min(minPrice, prices[i]);
            } else {
                onceMax = Math.max(onceMax, prices[i] - minPrice);
            }

            // 逢低必买，逢高必卖
            if (lastMin == -1 || lastMin > prices[i]) {
                // 买
                lastMin = prices[i];
            } else if (lastMin < prices[i]) {
                // 卖
                multipleMax += (prices[i] - lastMin);
                // 卖出之后重置最小值
                lastMin = -1;
            }
        }
        return Math.max(multipleMax, onceMax);
    }

}
