package com.weiyi.alorithm.dp;

/**
 * 背包问题
 * <p>
 * 给定两个整型非负数组，weights[i]代表背包的重量，prices[i]代表背包的价值,给定capacity容量的背包，请你返回最大价值
 *
 * @author weiyi
 * @date 2023-01-12
 */
public class Knapsack {

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        System.out.println(knapsack.knapsack(new int[]{5, 3, 6, 2, 4}, new int[]{2, 3, 4, 10, 5}, 10));
        System.out.println(knapsack.knapsack2(new int[]{5, 3, 6, 2, 4}, new int[]{2, 3, 4, 10, 5}, 10));
    }

    public int knapsack2(int[] weights, int[] prices, int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];

        for (int i = dp.length - 2; i >= 0; i--) {
            // 从倒数第一排开始根据状态转移方程式推
            for (int j = 0; j < dp[i].length; j++) {
                int take = 0;
                if (j >= weights[i]) {
                    // 剩余容量大于等于当前需要的容量
                    take = prices[i] + dp[i + 1][j - weights[i]];
                }
                // 拿不下，去下一个
                int notTake = dp[i + 1][j];

                dp[i][j] = Math.max(take, notTake);
            }
        }
        return dp[0][capacity];
    }


    public int knapsack(int[] weights, int[] prices, int capacity) {
        return knapsack(weights, prices, capacity, 0);
    }


    public int knapsack(int[] weights, int[] prices, int capacity, int index) {
        // 从index位置做抉择，选择拿与不拿
        if (index == weights.length) {
            return 0;
        }

        int take = 0;
        if (capacity >= weights[index]) {
            // 剩余容量大于等于当前需要的容量
            take = prices[index] + knapsack(weights, prices, capacity - weights[index], index + 1);
        }
        // 拿不下，去下一个
        int notTake = knapsack(weights, prices, capacity, index + 1);

        return Math.max(take, notTake);
    }

}
