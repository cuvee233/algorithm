package com.weiyi.alorithm.union;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * <p>
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * <p>
 * 请你返回 grid2 中 子岛屿 的 数目 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-sub-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路，两个矩阵同时递归遍历，都为1时开始感染步骤，最后返回感染的次数
 *
 * @author weiyi
 * @date 2023-01-10
 */
public class CountSubIslands {

    public static void main(String[] args) {
        System.out.println(countSubIslands(new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
                new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}}));
    }


    /**
     * 并查集解法，
     *
     * @param grid1 岛屿矩阵1
     * @param grid2 岛屿矩阵2
     * @return 子岛屿数量
     */
    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        UnionSet unionSet = new UnionSet(grid2);
        // 将grid2加入并查集
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid2[i][j] == 1) {
                    if (i - 1 >= 0 && grid2[i - 1][j] == 1) {
                        // 自己为岛屿且上边为岛屿，加入
                        unionSet.unionAll(i, j, i - 1, j);
                    }
                    if (j - 1 >= 0 && grid2[i][j - 1] == 1) {
                        // 自己为岛屿且下面为岛屿，加入
                        unionSet.unionAll(i, j, i, j - 1);
                    }
                }
            }
        }

        int ans = unionSet.setSize;

        // 移除grid2为陆地（==1）但grid1为水域的集合（==0）
        Set<Integer> removeSet = new HashSet<>();
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid1[i][j] != 1 && grid2[i][j] == 1) {
                    int parent = unionSet.findParent(unionSet.getIdx(i, j));
                    if (parent != -1 && removeSet.add(parent)) {
                        ans--;
                    }
                }
            }
        }
        return ans;
    }


    public static int countSubIslands1(int[][] grid1, int[][] grid2) {
        int ans = 0;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid1[i][j] == 1 && grid2[i][j] == 1) {
                    // 都等于1开始感染步骤,grid1完全包含grid2才算一子岛屿
                    if (infect(grid1, grid2, i, j)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static boolean infect(int[][] grid1, int[][] grid2, int i, int j) {
        if (i < 0 || i >= grid1.length || j < 0 || j >= grid1[i].length) {
            return true;
        }

        if (grid2[i][j] != 1) {
            // 不是陆地，直接返回
            return true;
        }

        if (grid1[i][j] != 1) {
            // 无法包含岛屿二，直接返回
            return false;
        }

        // 将陆地修改为已遍历过，避免重复检索
        grid2[i][j] = 2;


        // 此处需要用&，前面失败但是后面还需要遍历
        return infect(grid1, grid2, i - 1, j)
                & infect(grid1, grid2, i + 1, j)
                & infect(grid1, grid2, i, j - 1)
                & infect(grid1, grid2, i, j + 1);
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

        public UnionSet(int[][] grid) {
            totalColumn = grid[0].length;
            int length = grid.length * totalColumn;
            parents = new int[length];
            size = new int[length];
            help = new int[length];

            // 初始化parent和size数组
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < totalColumn; j++) {
                    int idx = getIdx(i, j);
                    if (grid[i][j] == 1) {
                        parents[idx] = idx;
                        size[idx] = 1;
                        setSize++;
                    } else {
                        parents[idx] = -1;
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
            while (parents[i] != i && parents[i] != -1) {
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
            if (iParent != jParent) {
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
