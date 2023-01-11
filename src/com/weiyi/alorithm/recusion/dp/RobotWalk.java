package com.weiyi.alorithm.recusion.dp;

import java.util.Arrays;

/**
 * 机器人散步问题
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class RobotWalk {
    public static void main(String[] args) {
        RobotWalk robotWalk = new RobotWalk();

        int n = 5;
        int start = 2;
        int distance = 4;
        int step = 6;
        int[][] dp = new int[n + 1][step + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(robotWalk.getGroupCount(n, start, distance, step, dp));
        System.out.println("=======================");
        System.out.println(robotWalk.getGroupCount(n, start, distance, step));
    }

    /**
     * 长度为0-n的距离数组，机器人初始在start位置，需要走step步到distance去，问有多少种步行方式
     *
     * @param n        数组长度
     * @param start    开始位置
     * @param distance 目标位置
     * @param step     必须行走的步数
     * @return 步行方式数量
     */
    public int getGroupCount(int n, int start, int distance, int step, int[][] dp) {
        if (dp[start][step] != -1) {
            return dp[start][step];
        }

        if (step == 0) {
            // 步数用完了，判断有没有走到终点
            return start == distance ? 1 : 0;
        }

        if (start == 1) {
            // 走到开头位置需要往后走
            return getGroupCount(n, start + 1, distance, step - 1, dp);
        }
        if (start == n) {
            // 走到结束位置需要往前走
            return getGroupCount(n, start - 1, distance, step - 1, dp);
        }

        // 往前走
        int rightCount = getGroupCount(n, start - 1, distance, step - 1, dp);

        // 往后走
        int leftCount = getGroupCount(n, start + 1, distance, step - 1, dp);

        dp[start][step] = rightCount + leftCount;

        // 两种方式的可能性相加
        return rightCount + leftCount;
    }


    /**
     * 动态规划来解决问题，递归的时候决定方法返回参数的是start和step两个参数，当两个参数一致时返回的参数一致，所以可以用动态规划来解决问题
     *
     * @param n     数组长度
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     总共步数
     * @return 从start位置开始到distance结束，走step步有多少种行走方式
     */
    public int getGroupCount(int n, int start, int aim, int K) {
        int[][] dp = new int[n + 1][K + 1];
        dp[aim][0] = 1;

        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }

        return dp[start][K];
    }

}
