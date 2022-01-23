package com.weiyi.leetcode.LinkedList.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 模拟实现直接超出内存限制，看了官方题解之后找到了一种非常巧妙的算法：
 * 黑名单映射法，将黑名单的元素和白名单元素的建立一个映射关系，在有效索引范围内生产随机数，若当前是白名单数字直接返回，若是黑名单数字则返回映射的白名单数字
 * <p>
 * 例如：
 * n = 5 blackList = [1,2] 那么n得有效范围是 0-4，黑名单1、2，白名单0、3、4
 * 白名单有效长度为3，则有效的索引为0、1、2，建立映射关系1-4 2-3，这样在0-2索引范围内所有的数字都是有效的
 *
 * @author weiyi
 * @date 2022/1/16
 */
public class RondomBlackList710_2 {

    /**
     * 白名单数组的长度
     */
    private int whiteLength;

    /**
     * 黑白名单的映射关系
     */
    private Map<Integer, Integer> blackWhiteMap;

    private Random random;

    public static void main(String[] args) {
        RondomBlackList710_2 rondomBlackList710_2 = new RondomBlackList710_2(7, new int[]{2, 3, 5});
    }


    public RondomBlackList710_2(int n, int[] blacklist) {
        random = new Random();
        // 计算白名单的长度
        whiteLength = n - blacklist.length;

        // 建立黑名单map
        blackWhiteMap = new HashMap<>(blacklist.length);
        for (int black : blacklist) {
            // value目前只是占位，不重要
            blackWhiteMap.put(black, 0);
        }

        // n长度数组的最后一位下标
        int last = n - 1;

        for (int black : blacklist) {
            if (black >= whiteLength) {
                // 当前黑名单数字大于白名单最后一位索引，只需要处理[0,blackLength),如果当前元素已经在区间[blackLength,n]，可以直接忽略
                continue;
            }

            while (blackWhiteMap.containsKey(last)) {
                // 如果黑名单中有当前元素，直接忽略
                last--;
            }

            // 建立好黑->白的映射关系，
            blackWhiteMap.put(black, last--);
        }

    }

    public int pick() {
        // 如果当前随机值在黑名单中，则返回黑名单对应的白名单数值
        int i = random.nextInt(whiteLength);
        return blackWhiteMap.getOrDefault(i, i);
    }

}
