package com.weiyi.alorithm.union;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维岛屿，请你计算岛屿中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该岛屿的四条边均被水包围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 1.递归，把整个网格想象成为二位矩阵，遍历每个网格，把当前网格的上下左右四个方向为1的岛屿感染，
 * 感染之后将1修改为任意不为1的数字，感染完成，岛屿数量+1
 * 2.并查集，将每个岛屿作为一个集合加入并查集中，再遍历每个岛屿，当这个岛屿的左边或者上面为1时，合并并查集，最终返回并查集的数量
 * 2.1 用hashMap来实现并查集，但是因为二维数组里面存的是基本数据类型，0和1，所以需要建立一个二维矩阵，将0、1转换为其它唯一对象，这样集合才有了唯一性
 * 2.2 用数组实现并查集，将二维数组转为一维数组，i为二维数组行，j为二维数组列，n为一维数组索引，row为二维数组的总行数 n = j * n + i;
 *
 * @author weiyi
 * @date 2023-01-10
 */
public class NumberOfIsland {

    public static void main(String[] args) {
        NumberOfIsland numberOfIsland = new NumberOfIsland();
        System.out.println(numberOfIsland.numIslands(new char[][]{
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '0', '1'}}));
    }

    /**
     * 1.递归，把整个网格想象成为二位矩阵，遍历每个网格，把当前网格的上下左右四个方向为1的岛屿感染，
     * 感染之后将1修改为任意不为1的数字，感染完成，岛屿数量+1
     * 2.并查集，将每个岛屿作为一个集合加入并查集中，再遍历每个岛屿，当这个岛屿的左边或者上面为1时，合并并查集，最终返回并查集的数量
     * 2.1 用hashMap来实现并查集，但是因为二维数组里面存的是基本数据类型，0和1，所以需要建立一个二维矩阵，将0、1转换为其它唯一对象，这样集合才有了唯一性
     * 2.2 用数组实现并查集，将二维数组转为一维数组，i为二维数组行，j为二维数组列，n为一维数组索引，row为二维数组的总行数 n = j * n + i;
     */
    public int numIslands2(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    // 每次递归都会把当前所有相邻的陆地变为2，下次不会再继续遍历，所以统计递归的次数则代表陆地集的数量
                    infect(grid, i, j);
                    ans++;
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
    public int infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return 0;
        }

        // 将元素修改为非'0''1'
        grid[i][j] = '2';

        // 上下左右递归感染
        int up = infect(grid, i - 1, j);
        int down = infect(grid, i + 1, j);
        int left = infect(grid, i, j - 1);
        int right = infect(grid, i, j + 1);
        return up + down + left + right + 1;
    }

    /**
     * 并查集实现，判断左边和上边是否为岛屿，是的话加入并查集
     *
     * @param grid 岛屿
     * @return 岛屿数量
     */
    public int numIslands(char[][] grid) {

        UnionSet unionSet = new UnionSet(grid);

        // 初始化第一行和第一列，避免判空
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        // 自己为岛屿且上边为岛屿，加入
                        unionSet.unionAll(i, j, i - 1, j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        // 自己为岛屿且下面为岛屿，加入
                        unionSet.unionAll(i, j, i, j - 1);
                    }
                }
            }
        }

        return unionSet.setSize;
    }


    /**
     * 并查集
     */
    static class UnionSet {
        /**
         * parents[i] = i的父集合的索引
         */
        protected int[] parents;

        /**
         * 并查集的集合数量，size[i]代表以i为代表节点的并查集的数量
         */
        protected int[] size;

        /**
         * 总集合的大小,默认0
         */
        protected int setSize;

        /**
         * 总列数
         */
        protected int totalColumn;

        /**
         * 辅助数组，用来扁平化并查集
         */
        private int[] help;

        public UnionSet(char[][] grid) {
            totalColumn = grid[0].length;
            int length = grid.length * totalColumn;
            parents = new int[length];
            size = new int[length];
            help = new int[length];

            // 初始化parent和size数组
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < totalColumn; j++) {
                    if (grid[i][j] == '1') {
                        int idx = getIdx(i, j);
                        parents[idx] = idx;
                        size[idx] = 1;
                        setSize++;
                    }
                }
            }
        }

        /**
         * 二维矩阵的索引转为一维， idx = 行 * 总列数 + 当前行
         *
         * @param row    行
         * @param column 列
         * @return 一维索引
         */
        protected int getIdx(int row, int column) {
            return totalColumn * row + column;
        }


        /**
         * 找到i的代表节点
         *
         * @param i 对象
         * @return 代表节点
         */
        protected int findParent(int i) {
            // 找到代表节点
            int idx = 0;
            while (parents[i] != i) {
                help[idx++] = i;
                i = parents[i];
            }

            // 扁平化链
            while (--idx >= 0) {
                parents[help[idx--]] = i;
            }

            return i;
        }

        /**
         * i和j合并
         *
         * @param iRow    i行
         * @param iColumn i列
         * @param jRow    j行
         * @param jColumn j列
         */
        protected void unionAll(int iRow, int iColumn, int jRow, int jColumn) {

            int iParent = findParent(getIdx(iRow, iColumn));
            int jParent = findParent(getIdx(jRow, jColumn));
            System.out.println("zzz iRow: " + iRow + " iColumn: " + iColumn + " jRow: " + jRow + " jColumn: " + jColumn);
            if (iParent != jParent) {
                System.out.println("iRow: " + iRow + " iColumn: " + iColumn + " jRow: " + jRow + " jColumn: " + jColumn);
                // 代表节点不相等，说明不是一个并查集，可以合并

                // 数量较多的集合
                int maxParent = size[iParent] > size[jParent] ? iParent : jParent;
                // 数量较少的集合
                int minParent = maxParent == iParent ? jParent : iParent;

                // 更换代表节点
                parents[minParent] = maxParent;

                // 更新集合数量
                size[maxParent] += size[minParent];

                // 集合合并，size--
                setSize--;
            }
        }
    }

}
