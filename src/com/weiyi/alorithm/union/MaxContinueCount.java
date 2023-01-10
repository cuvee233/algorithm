package com.weiyi.alorithm.union;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 1.HashMap，重点是需要找到一个数相邻的两个数，若当前遍历到100，需要找到99和101是否在集合里面，这个时候用HashSet来存储就非常方便了
 * <p>
 * 2.并查集解法，为数字建立并查集，相邻的数字放入同一个并查集，返回最终并查集的数量即可
 *
 * @author weiyi
 * @date 2023-01-10
 */
public class MaxContinueCount {

    public static void main(String[] args) {
        MaxContinueCount maxContinueCount = new MaxContinueCount();
        System.out.println(maxContinueCount.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /**
     * 并查集解法，将所有相邻的数放入同一个并查集，合并之后返回最大的并查集集合即可
     *
     * @param nums 原始数组
     * @return 最长序列
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        UnionSet unionSet = new UnionSet(nums);
        for (int num : nums) {
            // 前后合并
            unionSet.unionAll(num, num + 1);
            unionSet.unionAll(num, num - 1);
        }
        return unionSet.maxSize;
    }

    /**
     * HashSet解法
     * 重点是需要找到一个数相邻的两个数，若当前遍历到100，需要找到99和101是否在集合里面，这个时候用HashSet来存储就非常方便了
     * 第一遍将数组所有元素放入HashSet，第二遍遍历时循环找比当前元素大一和小一的元素，
     * 同时记录连续序列的数量（优化，可以再建一个set，存储所有已经遍历过的元素,防止重复遍历）
     *
     * @param nums 数组
     * @return int 最长序列
     */
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // 存放所有元素
        Set<Integer> allSet = new HashSet<>(nums.length);
        for (int num : nums) {
            allSet.add(num);
        }

        // 已经计算过序列的元素（防止重复遍历）
        Set<Integer> visitedSet = new HashSet<>(nums.length);

        int longest = 1;

        // 找到最长序列
        for (int num : nums) {
            // 计算过不用再重复计算
            if (visitedSet.contains(num)) {
                continue;
            }
            int curLongest = 1;

            // 循环找比当前小的连续序列
            int less = num - 1;
            while (allSet.contains(less)) {
                // 标记已经计算过
                visitedSet.add(less--);
                curLongest++;
            }

            // 循环找比当前大的连续序列
            int more = num + 1;
            while (allSet.contains(more)) {
                // 标记已经计算过
                visitedSet.add(more++);
                curLongest++;
            }

            longest = Math.max(longest, curLongest);
        }

        return longest;
    }

    static class UnionSet {

        /**
         * 父节点map
         */
        protected HashMap<Integer, Integer> parents;

        /**
         * 并查集长度map
         */
        protected HashMap<Integer, Integer> sizes;

        /**
         * 辅助扁平化parent数组
         */
        protected int[] help;

        /**
         * 最长的并查集长度
         */
        protected int maxSize;

        public UnionSet(int[] nums) {
            parents = new HashMap<>(nums.length);
            sizes = new HashMap<>(nums.length);
            help = new int[nums.length];
            maxSize = 1;

            for (int num : nums) {
                parents.put(num, num);
                sizes.put(num, 1);
            }
        }

        public Integer findParent(Integer cur) {
            int idx = 0;
            while (!Objects.equals(parents.get(cur), cur)) {
                help[idx++] = cur;
                cur = parents.get(cur);
            }
            if (cur == null) {
                return null;
            }

            // 扁平化数组
            for (--idx; idx >= 0; idx--) {
                parents.put(help[idx], cur);
            }
            return cur;
        }

        public void unionAll(Integer i1, Integer i2) {
            Integer parent1 = findParent(i1);
            Integer parent2 = findParent(i2);

            if (parent1 != null && parent2 != null && !parent1.equals(parent2)) {
                Integer size1 = sizes.get(parent1);
                Integer size2 = sizes.get(parent2);
                int curSize = size1 + size2;
                // 每次合并更新最大集合数
                maxSize = Math.max(curSize, maxSize);
                if (size1 > size2) {
                    parents.put(parent2, parent1);
                    sizes.put(parent1, curSize);
                } else {
                    parents.put(parent1, parent2);
                    sizes.put(parent2, curSize);
                }
            }
        }
    }

}
