package com.weiyi.leetcode.dp;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * https://leetcode.cn/problems/maximal-square/
 *
 * <p>
 * 当我们判断以某个点为正方形右下角时最大的正方形时，那它的上方，左方和左上方三个点也一定是某个正方形的右下角，否则该点为右下角的正方形最大就是它自己了。
 * 这是定性的判断，那具体的最大正方形边长呢？我们知道，该点为右下角的正方形的最大边长，最多比它的上方，左方和左上方为右下角的正方形的边长多1，最好的情况是是它的上方，
 * 左方和左上方为右下角的正方形的大小都一样的，这样加上该点就可以构成一个更大的正方形。 但如果它的上方，左方和左上方为右下角的正方形的大小不一样，
 * 合起来就会缺了某个角落，这时候只能取那三个正方形中最小的正方形的边长加1了。假设dpi表示以i,j为右下角的正方形的最大边长，
 * 则有 dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 当然，如果这个点在原矩阵中本身就是0的话，那dp[i]肯定就是0了。
 *
 * @author weiyi
 * @date 2023-02-02
 */
public class MaximalSquare {

    /**
     * [["1","1","1","1","1"],
     * ["1","1","1","1","1"],
     * ["0","0","0","0","0"],
     * ["1","1","1","1","1"],
     * ["1","1","1","1","1"]]
     */
    public static void main(String[] args) {
        MaximalSquare maximalSquare = new MaximalSquare();
        char[][] chars = new char[][]{{'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'0', '0', '0', '0', '0'}, {'1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1'}};
        char[][] chars2 = new char[][]{{'1', '1'}, {'1', '1'}};
        System.out.println(maximalSquare.maximalSquare(chars2));
        System.out.println(maximalSquare.maximalSquare1(chars2));
    }

    /**
     * 动态规划(一维dp表优化版本)
     *
     * @param matrix 矩阵
     * @return 最大正方形面积
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[] dp = new int[column];

        dp[0] = matrix[0][0] == '1' ? 1 : 0;
        int maxSide = dp[0];

        // 左上角
        int leftUp = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0) {
                        dp[j] = 1;
                    } else if (j == 0) {
                        leftUp = dp[j];
                        dp[j] = 1;
                    } else {
                        int lastCur = dp[j];
                        dp[j] = Math.min(leftUp, Math.min(dp[j - 1], dp[j])) + 1;
                        // 上一行的cur为下一个元素的左上位置
                        leftUp = lastCur;
                    }
                } else {
                    leftUp = dp[j];
                    dp[j] = 0;
                }
                maxSide = Math.max(dp[j], maxSide);
            }
        }

        return maxSide * maxSide;
    }


    /**
     * 动态规划
     *
     * @param matrix 矩阵
     * @return 最大正方形面积
     */
    public int maximalSquare2(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int[] dp = new int[column];

        dp[0] = matrix[0][0] == '1' ? 1 : 0;

        int maxSide = dp[0];

        // 初始化第一行
        for (int i = 1; i < column; i++) {
            if (matrix[0][i] == '1') {
                dp[i] = 1;
                maxSide = Math.max(dp[i], maxSide);
            }
        }

        // 依次填入剩下行
        for (int i = 1; i < row; i++) {
            // 左上的值
            int leftUp = dp[0];
            dp[0] = matrix[i][0] == '1' ? 1 : 0;
            maxSide = Math.max(dp[0], maxSide);
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == '1') {
                    int min = Math.min(leftUp, Math.min(dp[j], dp[j - 1]));
                    leftUp = dp[j];
                    // 左边、左上、上面的最小矩阵边长+1为当前最大矩阵边长
                    dp[j] = min + 1;
                    maxSide = Math.max(dp[j], maxSide);
                } else {
                    leftUp = 0;
                    dp[j] = 0;
                }
            }
        }

        return maxSide * maxSide;
    }

    public int maximalSquare1(char[][] matrix) {
        // 正方形最大边长
        int side = 0;
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    side = Math.max(maximalSquare1(matrix, i, j), side);
                }
            }
        }
        return side * side;
    }

    /**
     * 暴力递归（超时）
     * 以matrix[row][column]作为右下角的最大正方形边长
     *
     * @param matrix 矩阵
     * @param row    当前行
     * @param column 当前列
     * @return 最大正方形面积
     */
    public int maximalSquare1(char[][] matrix, int row, int column) {
        if (row < 0 || column < 0) {
            // 越界
            return 0;
        }

        // 无法组成正方形
        if (matrix[row][column] == '0') {
            return 0;
        }

        // 求左边
        int left = maximalSquare1(matrix, row, column - 1);

        // 求上面
        int up = maximalSquare1(matrix, row - 1, column);

        // 求左上
        int leftUp = maximalSquare1(matrix, row - 1, column - 1);

        // 最小值加一
        return Math.min(left, Math.min(up, leftUp)) + 1;
    }


    /**
     * 以matrix[i][j]为正方形的左上角,所能构成的最长边长
     * TLE
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    // public static int process(char[][] matrix, int i, int j) {
    //     if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
    //         return 0;
    //     }
    //     if (matrix[i][j] == '0') {
    //         return 0;
    //     }
    //     if (i == matrix.length - 1 && j == matrix.length - 1) {
    //         return 1;//矩阵的最右下角
    //     }
    //     //TODO 每次向右、右下、下搜索,查看其所能构成的最长边长
    //     //TODO 由于是正方形,所以三条边需要去最短的那一条(最先为0的那一条路)
    //     int p1 = process(matrix, i + 1, j);
    //     int p2 = process(matrix, i + 1, j + 1);
    //     int p3 = process(matrix, i, j + 1);
    //     int min = Math.min(Math.min(p1, p2), p3);
    //     return 1 + min;
    // }


}
