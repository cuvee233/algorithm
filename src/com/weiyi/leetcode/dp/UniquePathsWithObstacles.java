package com.weiyi.leetcode.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-02-02
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        int[][] dp = {{0, 1, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(dp));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 建立一维dp数组
        int[] dp = new int[obstacleGrid.length];

        // 初始化第一列
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] != 1) {
                if (i - 1 >= 0) {
                    dp[i] = dp[i - 1];
                }
            }
        }
        // 处理后续列
        for (int i = 1; i < obstacleGrid[0].length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (obstacleGrid[j][i] != 1) {
                    dp[j] = dp[j] + (j - 1 >= 0 ? dp[j - 1] : 0);
                } else {
                    // 当前位置有障碍，答案为0
                    dp[j] = 0;
                }
            }
        }

        return dp[obstacleGrid.length - 1];

    }





}
