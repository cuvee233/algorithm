package com.weiyi.leetcode.LinkedList.random;

import com.weiyi.leetcode.LinkedList.ListNode;

import java.util.Random;

/**
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * <p>
 * <p>
 * 2.蓄水池算法，顺序遍历链表，访问到第i个元素的时候，以1/i的几率去尝试选择当前节点。难点在于如何让每个节点选中的几率都是1/n,蓄水池算法可以保证，具体证明过程可以参考视频：
 * https://www.bilibili.com/video/BV17i4y1j7wE?from=search&seid=7100437891824547270&spm_id_from=333.337.0.0
 * <p>
 * 例：第1个元素选中的几率 1/1,第二个1/2,第三个1/3,第n个1/n
 * <p>
 * 1                                               2                           3                               n
 * 1/1
 * 1(上次被选中的概率) * （1/2）（这一次继续保留的概率）    1/2
 * 1/2 * (1 - 1/3)                            1/2 * (1 - 1/3)                 1/3
 * 1 / n                                         1 / n                        1 / n                           1 / n
 * <p>
 * <p>
 * 第一次选择：只有元素1，选择1，1被选中的概率是1/1
 * 第一次选择: 1被继续保留的几率是 上一次选中的几率 * 这一次被选中的几率1 * 1/2 = 1/2, 2被选中的几率也为1/2
 * 第三次选择：1被继续保留的几率是 1/2 * (1 - 1/3) = 1/3 , 2被保留的几率也和1一样1/3,3被选中的几率也为1/3
 * 第n次选择：数学归纳法可得第n次选择所有元素选中的几率都为1/n
 *
 * @author weiyi
 * @date 2022/1/16
 */
public class RandomNode382_2 {

    private ListNode node;
    private Random random;

    public RandomNode382_2(ListNode head) {
        this.node = head;
        random = new Random();
    }

    public int getRandom() {
        int ans = 0;
        // 当前元素索引，从1开始
        int i = 1;

        for (ListNode help = node; help != null; help = help.next) {
            if (random.nextInt(i++) == 0) {
                // 等于0说明被选中,1/i的几率
                ans = help.val;
            }
        }
        return ans;
    }
}
