package com.weiyi.leetcode.LinkedList.random;

import com.weiyi.leetcode.LinkedList.ListNode;

import java.util.Random;

/**
 * 382.给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 *
 * <p>
 * 1.数组实现，将链表的所有节点加入到数组中，随机访问数组的元素即可
 *
 * @author weiyi
 * @date 2022/1/16
 */
public class RandomNode382_1 {

    private int[] arr;
    private Random random;

    public RandomNode382_1(ListNode head) {
        // 长度
        int size = 0;
        ListNode help = head;
        while (help != null) {
            size++;
            help = help.next;
        }

        // 加入数组
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = head.val;
            head = head.next;
        }

        // 初始化随机值
        random = new Random();
    }

    public int getRandom() {
        return arr[random.nextInt(arr.length)];
    }
}
