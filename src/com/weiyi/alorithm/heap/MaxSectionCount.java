package com.weiyi.alorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * <p>
 * 给定很多线段，每个线段都有两个数[start,end]，表示线段开始位置和结束位置，
 * 返回线段数最多的重合区域中，包含了多少条线段
 * <p>
 * 1.小根堆存储线段（按start存储）
 * 2.建立另外一个小根堆（存储end），依次取出线段，判断存储end根的堆是否都是大于当前start，小于等于则移除，剩下的数量则为重合的线段数量
 * 3.找到最大的重合线段数
 *
 * @author weiyi
 * @date 2023-01-03
 */
public class MaxSectionCount {

    public static void main(String[] args) {
        System.out.println(getMaxCount(new int[][]{{3, 4}, {1


                , 5}, {3, 6}}));
    }


    public static int getMaxCount(int[][] sections) {

        // 根据start建立小根堆，存储线段
        PriorityQueue<int[]> sectionHeap = new PriorityQueue<>((o1, o2) -> {
            int start1 = o1[0];
            int start2 = o2[0];
            return start1 - start2;
        });
        sectionHeap.addAll(Arrays.asList(sections));

        int maxCount = 0;

        // 根据end值建立小根堆，只存储end值
        PriorityQueue<Integer> endHeap = new PriorityQueue<>();
        while (!sectionHeap.isEmpty()) {
            int[] section = sectionHeap.poll();
            // end值小于等于当前start代表不重合，需要移除
            while (!endHeap.isEmpty() && endHeap.peek() <= section[0]) {
                endHeap.poll();
            }
            endHeap.add(section[1]);
            maxCount = Math.max(maxCount, endHeap.size());
        }
        return maxCount;
    }

}
