package com.weiyi.leetcode.dp;

import java.util.Arrays;

/**
 * 机器人散步问题
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-01
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(2, 9));
        System.out.println(uniquePaths.uniquePaths1(2, 9));
    }

    /**
     * 动态规划实现
     */
    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    // 终点的步数等于1
                    dp[i][j] = 1;
                } else {// 处理边界条件，防止越界
                    int down = i < m - 1 ? dp[i + 1][j] : 0;
                    int right = j < n - 1 ? dp[i][j + 1] : 0;
                    dp[i][j] = down + right;
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划实现(状态压缩)
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        // 初始化第一行
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                // 左边和上面的和
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }


}
