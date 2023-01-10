package com.weiyi.alorithm.union;

/**
 * 给定一个由 0 和 1 组成的非空二维数组 grid ，用来表示海洋岛屿地图。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。如果没有岛屿，则返回面积为 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ZL6zAn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 递归深度优先遍历
 *
 * @author weiyi
 * @date 2023-01-10
 */
public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, infect(grid, i, j));
                }
            }
        }
        return ans;
    }

    /**
     * 当前位置等于‘1’,需要朝着上下左右四个方向做递归感染
     *
     * @param grid 岛屿矩阵
     * @param i    列
     * @param j    行
     */
    public int infect(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != 1) {
            return 0;
        }

        // 将元素修改为非'0''1'
        grid[i][j] = 2;

        // 上下左右递归感染
        int up = infect(grid, i - 1, j);
        int down = infect(grid, i + 1, j);
        int left = infect(grid, i, j - 1);
        int right = infect(grid, i, j + 1);
        return up + down + left + right + 1;
    }

}
