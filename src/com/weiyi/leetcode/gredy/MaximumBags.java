package com.weiyi.leetcode.gredy;

import java.util.PriorityQueue;

/**
 * 现有编号从 0 到 n - 1 的 n 个背包。给你两个下标从 0 开始的整数数组 capacity 和 rocks 。第 i 个背包最大可以装 capacity[i] 块石头，当前已经装了 rocks[i] 块石头。另给你一个整数 additionalRocks ，表示你可以放置的额外石头数量，石头可以往 任意 背包中放置。
 * <p>
 * 请你将额外的石头放入一些背包中，并返回放置后装满石头的背包的 最大 数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-bags-with-full-capacity-of-rocks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-01-12
 */
public class MaximumBags {

    /**
     * 贪心算法解，capacity[i] - rock[i] = 需要装多少石头才能放满背包
     * 贪心策略，优先放需要放石头数量较少的背包
     * 建一个小根堆，存入capacity[i] - rock[i]的数量，依次用additionalRocks减去小根堆弹出的元素，返回最终数量
     *
     * @param capacity        背包容量数组
     * @param rocks           背包已防止石子数组
     * @param additionalRocks 剩余放至石子数组
     * @return 最多能放满几个背包
     */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < capacity.length; i++) {
            heap.add(capacity[i] - rocks[i]);
        }

        int ans = 0;
        while (!heap.isEmpty()) {
            Integer last = heap.poll();
            if (additionalRocks >= last) {
                additionalRocks -= last;
                ans++;
            } else {
                break;
            }
        }

        return ans;
    }
}
