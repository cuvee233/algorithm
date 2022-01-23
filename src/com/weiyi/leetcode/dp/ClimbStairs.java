package com.weiyi.leetcode.dp;

/**
 * 爬楼梯算法
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author weiyi
 * @date 2022/1/23
 */
public class ClimbStairs {

    /**
     * 第一种解法： 暴力递归，分两种情况递归
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int possibility = 0;

        // 第一种，爬一阶
        possibility += climbStairs(n - 1);

        // 第二中，爬两阶
        possibility += climbStairs(n - 2);

        return possibility;
    }

    /**
     * 第二种解法 动态规划
     * <p>
     * 爬楼梯算法类似斐波拉契数列，公式f(n) = f(n - 1) + f(n - 2)
     */
    public int climbStairs2(int n) {
        if (n <= 1) {
            return n;
        }

        // first=f(n - 2) last= f(n - 1) cur = fist + last
        int first = 1, last = 1, cur = 0;
        for (int i = 1; i < n; i++) {
            cur = (first + last);
            first = last;
            last = cur;
        }

        return cur;
    }

}
