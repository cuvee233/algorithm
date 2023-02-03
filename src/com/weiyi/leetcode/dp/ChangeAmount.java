package com.weiyi.leetcode.dp;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * <p>
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * <p>
 * 假设每一种面额的硬币有无限个。 
 * <p>
 * 题目数据保证结果符合 32 位带符号整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-01
 */
public class ChangeAmount {

    public static void main(String[] args) {
        ChangeAmount changeAmount = new ChangeAmount();
        System.out.println(changeAmount.change3(5, new int[]{1, 2, 5}));
        System.out.println(changeAmount.change(5, new int[]{1, 2, 5}));
    }


    /**
     * 当前和=cur，求组成硬币组合数(暴力递归),此处有问题，重复选择了组合，比如1，1，2和1，2，1其实是一个答案
     *
     * @param amount 目标金额
     * @param coins  硬币数组
     * @return 组合数
     */
    public int change2(int amount, int[] coins) {
        if (amount == 0) {
            // 找到一次正确的组合
            return 1;
        }
        if (amount < 0) {
            // 超过目标硬币，直接返回
            return 0;
        }

        int res = 0;
        for (int i = 0; i < coins.length; i++) {
            res += change2(amount - coins[i], coins);
        }
        return res;
    }

    public int change3(int amount, int[] coins) {
        return change3(amount, coins, 0);
    }

    /**
     * 当前和=cur，求组成硬币组合数(暴力递归)
     *
     * @param amount 目标金额
     * @param coins  硬币数组
     * @return 组合数
     */
    public int change3(int amount, int[] coins, int cur) {

        if (amount == 0) {
            // 找到一次正确的组合
            return 1;
        }
        if (amount < 0 || cur >= coins.length) {
            // 超过目标硬币，遍历到最后一位，返回0
            return 0;
        }

        // 选当前
        int res = change3(amount - coins[cur], coins, cur);
        // 不选当前
        res += change3(amount, coins, cur + 1);

//        System.out.println("amount :" + amount + " cur " + cur + "res " + res);
        return res;
    }

    /**
     * 动态规划
     */
    public int change4(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = dp.length - 2; i >= 0; i--) {
            // 找到一次正确的组合
            dp[i][0] = 1;
            for (int j = 1; j < dp[i].length; j++) {
                if (j - coins[i] >= 0) {
                    dp[i][j] = dp[i][j - coins[i]] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][amount];
    }

    /**
     * 暴力递归
     */
    public int change5(int amount, int[] coins) {
        return change5(amount, coins, 0);
    }

    public int change5(int amount, int[] coins, int cur) {
        if (cur == coins.length) {
            // 判断是否组合成功
            return amount == 0 ? 1 : 0;
        }

        int res = 0;
        // 从0张coins[cur]开始，[1,2,5]
        // 1. 选0张1 选0张2 选0张5
        // 2. 选0张1 选0张2 选1张5
        // 3. 选0张1 选1张2 选0张5
        // 4. 选0张1 选1张2 选1张5
        // 5. 选0张1 选2张2 选0张5
        // 6. 选0张1 选2张2 选1张5

        /**
         *                       1
         *          选0张1            选1张1
         *     选0张2   选1张2      选0张2   选1张2
         */

        for (int zhang = 0; zhang * coins[cur] <= amount; zhang++) {
            res += change5(amount - (zhang * coins[cur]), coins, cur + 1);
        }
        return res;
    }

    /**
     * 动态规划（二维dp表）
     */
    public int change6(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // base case纸币用完，金币也用完，完成一种组合
        dp[length][0] = 1;

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j < dp[i].length; j++) {
                int coin = coins[i];
                for (int k = 0; coin * k <= j; k++) {
                    dp[i][j] += dp[i + 1][j - (coin * k)];
                }
            }
        }
        return dp[0][amount];
    }


    /**
     * 动态规划（二维dp表）,优化版本，状态转移方程式为 dp[i][j] = dp[i][j-1] + dp[i+1][j]
     */
    public int change(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        // base case纸币用完，金币也用完，完成一种组合
        dp[length][0] = 1;

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[0][amount];
    }

}
