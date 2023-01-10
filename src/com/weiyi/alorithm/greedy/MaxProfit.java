package com.weiyi.alorithm.greedy;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * 遍历数组的同时维护两个值，一个是直到当前位置到最小值min，一个是直到当前位置的最大利润maxProfit，maxProfit = cur - min;
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minVal = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minVal) {
                minVal = prices[i];
            } else {
                maxProfit = Math.max(prices[i] - minVal, maxProfit);
            }
        }
        return maxProfit;
    }

}
