package com.weiyi.leetcode.dp;

/**
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-01
 */
public class MaxProfit3 {

    /**
     * 动态规划，状态压缩成两个字段即可
     */
    public int maxProfit(int[] prices, int fee) {
        // 买入的最大值
        int buyMax = 0;

        // 售出股票的最大值
        int sellMax = prices[prices.length - 1] - fee;

        for (int i = prices.length - 2; i >= 0; i--) {

            // 当前可买入，观望和买入的最大值
            int curBuyMax = Math.max(buyMax, sellMax - prices[i]);

            // 当前可出手，观望和出手的最大值
            sellMax = Math.max(sellMax, buyMax + prices[i] - fee);

            buyMax = curBuyMax;

        }

        return buyMax;
    }

    public int maxProfit1(int[] prices, int fee) {
        return maxProfit1(prices, fee, 0, false);
    }

    /**
     * 求股票盈利最大值（暴力递归）
     *
     * @param prices    股票买卖市场
     * @param fee       交易费用
     * @param cur       当前股票索引
     * @param hasProfit 当前是持有股票还是已抛售
     * @return 盈利最大值
     */
    public int maxProfit1(int[] prices, int fee, int cur, boolean hasProfit) {
        if (cur == prices.length) {
            return 0;
        }

        int max = 0;

        if (hasProfit) {
            // 当前持有股票，可以选择继续持有股票或者卖出股票，取最大值

            // 观望
            int wait = maxProfit1(prices, fee, cur + 1, true);

            // 卖出股票，扣去手续费
            int sell = maxProfit1(prices, fee, cur + 1, false) + prices[cur] - fee;

            max = Math.max(wait, sell);
        } else {
            // 当前未持有股票，选择买入或者观望，取最大值

            // 买入
            int buying = maxProfit1(prices, fee, cur + 1, true) - prices[cur];

            // 观望
            int wait = maxProfit1(prices, fee, cur + 1, false);

            max = Math.max(buying, wait);
        }
        return max;
    }


}
