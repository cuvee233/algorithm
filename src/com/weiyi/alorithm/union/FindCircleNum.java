package com.weiyi.alorithm.union;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路
 * 直接用并查集解决，根据对角线切分数据，因为数据是对称的，所以只能遍历一半的数据
 * <p>
 * 数组和HashMap实现并查集时间复杂度都一样，但是数组寻址快，常数时间比map小，综合效率比map更佳
 *
 * @author weiyi
 * @date 2023-01-10
 */
public class FindCircleNum {

    public static void main(String[] args) {
        FindCircleNum findCircleNum = new FindCircleNum();
        System.out.println(findCircleNum.findCircleNum(new int[][]{{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}}));
    }

    /**
     * 并查集实现
     *
     * @param isConnected 城市矩阵
     * @return 省份数量
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        UnionSet unionSet = new UnionSet(isConnected.length);


        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 1 + i; j < isConnected.length; j++) {
                // 只遍历右半部分
                if (isConnected[i][j] == 1) {
                    System.out.println(i + " " + j);
                    unionSet.unionAll(i, j);
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
         * 总集合的大小
         */
        private int setSize;

        /**
         * 辅助数组，用来扁平化并查集
         */
        private int[] help;

        public UnionSet(int n) {
            parents = new int[n];
            size = new int[n];
            help = new int[n];
            setSize = n;

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
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
            while (idx > 0) {
                parents[help[idx--]] = i;
            }

            return i;
        }

        /**
         * i和j合并
         *
         * @param i 对象
         * @param j 对象
         */
        protected void unionAll(int i, int j) {
            int iParent = findParent(i);
            int jParent = findParent(j);

            if (iParent != jParent) {
                // 代表节点不相等，说明不是一个并查集，可以合并
                System.out.println(i + " 组合 " + j);

                // 数量较多的集合
                int maxParent = size[iParent] > size[jParent] ? iParent : jParent;
                // 数量较少的集合
                int minParent = maxParent == iParent ? jParent : iParent;

                // 更换代表节点
                parents[minParent] = maxParent;

                // 更新集合数量
                size[maxParent] += size[minParent];

                // 并查集总数减一
                setSize--;
            }
        }
    }
}
