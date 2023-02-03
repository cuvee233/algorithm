package com.weiyi.leetcode.dp;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-02
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange2(new int[]{2, 5}, 11));
        System.out.println(coinChange.coinChange(new int[]{2, 5}, 11));
    }

    /**
     * 二维动态规划
     */
    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // 初始化,金币不等于0表示没有结果，初始化为-1
        for (int i = 1; i < dp[length].length; i++) {
            dp[length][i] = Integer.MAX_VALUE;
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                int count = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * coins[i] <= j; zhang++) {
                    int res = dp[i + 1][j - (zhang * coins[i])];
                    if (res != Integer.MAX_VALUE) {
                        count = Math.min(count, zhang + res);
                    }
                }
                dp[i][j] = count;
            }
        }

        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    /**
     * 暴力递归尝试
     */
    public int coinChange2(int[] coins, int amount) {
        int count = coinChange2(coins, amount, 0);

        return count == Integer.MAX_VALUE ? -1 : count;
    }

    public int coinChange2(int[] coins, int amount, int cur) {
        if (cur >= coins.length) {
            return amount == 0 ? 0 : Integer.MAX_VALUE;
        }

        int count = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[cur] <= amount; zhang++) {
            int res = coinChange2(coins, amount - (zhang * coins[cur]), cur + 1);
            if (res != Integer.MAX_VALUE) {
                // 等于-1代表无法组成目标金币
                count = Math.min(zhang + res, count);
            }
        }
        return count;
    }



}
