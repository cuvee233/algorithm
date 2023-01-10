package com.weiyi.alorithm.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * <p>
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * <p>
 * 请你返回你可以参加的 最大 会议数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：贪心解法，按结束时间排序，找结束最早的会议安排，经历早的选择参加会议的天数，让会议室使用次数最大化
 *
 * @author weiyi
 * @date 2023-01-09
 */
public class MaxEvents {

    public static void main(String[] args) {
        MaxEvents maxEvents = new MaxEvents();
        System.out.println(maxEvents.maxEvents(new int[][]{{3, 3}, {1, 3}, {2, 3}, {3, 4}, {3, 4}}));
    }


    public int maxEvents(int[][] events) {
        // 按会议结束时间顺序
        Arrays.sort(events, ((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        }));

        Set<Integer> joinDay = new HashSet<>();

        int lastEndTime = Integer.MIN_VALUE;
        // 尽量早的参加会议。第一天不行，第二天，第二天不行第三天
        for (int[] event : events) {

            if (event[1] <= lastEndTime) {
                continue;
            }

            for (int i = event[0]; i <= event[1]; i++) {
                if (joinDay.add(i)) {
                    // 参加成功直接退出
                    lastEndTime = i;
                    break;
                }
            }
        }
        return joinDay.size();
    }

}
