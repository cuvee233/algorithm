package com.weiyi.alorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最盈利的项目规划
 * <p>
 * 正整数数组costs、正整数数组profits，正整数K、M
 * costs[i]代表完成项目i的成本，profits[i]代表完成项目i的盈利，K表示你只能串行的完成K个项目，M代表你的初始资金
 * 求你的最大盈利
 * <p>
 * 解题思路：将项目成本和盈利组装成一个对象，首先建立小根堆，按成本排序，
 * 将成本小于M的对象转移到以盈利排序的大根堆，移除最大盈利，同时M+当前最大盈利为下一次的初始资金，循环往复，直到完成k个项目，返回M
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class MaxProfitableProject {

    public int mxProfitableProject(int k, int m, int[] costs, int[] profits) {
        // 小根堆，按成本排序
        PriorityQueue<ProjectInfo> costHeap = new PriorityQueue<>(Comparator.comparingInt(ProjectInfo::getCost));
        // 大根堆，按盈利排序
        PriorityQueue<ProjectInfo> profitHeap = new PriorityQueue<>((p1, p2) -> p2.profit - p1.profit);

        for (int i = 0; i < costs.length; i++) {
            costHeap.add(new ProjectInfo(costs[i], profits[i]));
        }

        while (k-- > 0) {
            while (!costHeap.isEmpty() && costHeap.peek().cost <= m) {
                profitHeap.add(costHeap.poll());
            }

            if (!profitHeap.isEmpty()) {
                m += profitHeap.poll().profit;
            }
        }
        return m;
    }

    static class ProjectInfo {
        protected int cost;
        protected int profit;

        protected ProjectInfo(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

        protected int getCost() {
            return cost;
        }

        protected int getProfit() {
            return profit;
        }
    }

}
