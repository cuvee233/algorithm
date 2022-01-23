package com.weiyi.leetcode.LinkedList.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 给定一个包含 [0，n) 中不重复整数的黑名单 blacklist ，写一个函数从 [0, n) 中返回一个不在 blacklist 中的随机整数。
 * <p>
 * 1.第一时间的想法就是模拟解法，初始化时直接将白名单存储，从白名单进行随机返回即可，但是官方用例n的长度是1 <= n <= 1000000000，直接超出内存限制，所以此种方法不行
 *
 * @author weiyi
 * @date 2022/1/16
 */
public class RondomBlackList710_1 {
    private Set<Integer> blackSet;
    private Random random;
    private int[] whitelist;

    public RondomBlackList710_1(int n, int[] blacklist) {
        this.blackSet = new HashSet<>(blacklist.length);
        for (int blackNum : blacklist) {
            blackSet.add(blackNum);
        }

        whitelist = new int[n - blacklist.length];
        this.random = new Random();

        int index = 0;
        for (n--; n >= 0; n--) {
            if (!blackSet.contains(n)) {
                whitelist[index++] = n;
            }
        }
    }

    public int pick() {
        return whitelist[random.nextInt(whitelist.length)];
    }

}
