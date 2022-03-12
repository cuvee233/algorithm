package com.weiyi.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费
 * 1.暴力递归+记忆化搜索
 * <p>
 * 2.动态规划
 *
 * @author weiyi
 * @date 2022/1/24
 */
public class MinCostClimbingStairs {

    public static void main(String[] args) {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        System.out.println(minCostClimbingStairs.minCostClimbingStairs2(new int[]{10, 15, 20}));
    }

    public int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> cache = new HashMap<>();
        return Math.min(minCostClimbingStairs(cost, 0, cache), minCostClimbingStairs(cost, 1, cache));
    }

    public int minCostClimbingStairs(int[] cost, int cur, Map<Integer, Integer> cache) {
        Integer res = cache.get(cur);
        if (res != null) {
            return res;
        }

        if (cur > cost.length - 1) {
            // 不够走了，返回0
            cache.put(cur, 0);
            return 0;
        }

        if (cur == cost.length - 1) {
            // 最后一个，返回最后一位值
            cache.put(cur, cost[cur]);
            return cost[cur];
        }

        // 递归计算走一步和走两步的最小花费，返回
        res = cost[cur] + Math.min(minCostClimbingStairs(cost, cur + 1, cache), minCostClimbingStairs(cost, cur + 2, cache));
        cache.put(cur, res);
        return res;
    }

    public int minCostClimbingStairs2(int[] cost) {
        // lastlast-》前两个的台阶的最小花费，last-》前一个台阶的最小花费
        int lastlast = 0, last = 0, ans = 0;

        for (int i = 2; i <= cost.length; i++) {
            // 计算前两个台阶的最小花费
            ans = Math.min(last + cost[i - 1], lastlast + cost[i - 2]);
            lastlast = last;
            last = ans;
        }
        return ans;
    }

    public int minCostClimbingStairs5(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }


    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }


}
