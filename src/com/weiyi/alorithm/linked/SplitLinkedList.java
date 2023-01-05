package com.weiyi.alorithm.linked;

import com.weiyi.alorithm.linked.common.NodeUtils;
import com.weiyi.leetcode.LinkedList.ListNode;
import com.weiyi.sort.utils.ArrayUtils;

/**
 * 给定一个整数，将链表按照小于当前数的元素放左边，等于这个元素放中间，大于这个元素的放右边的规则划分
 * 你不需要 保留 每个分区中各节点的初始相对位置。
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class SplitLinkedList {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(2);
//        ListNode node5 = new ListNode(2);
//        ListNode node6 = new ListNode(6);

//
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;


        ListNode partition = partition(node1, 2);

        NodeUtils.printListNode(partition);

    }

    /**
     * 快速解法，使用数组存放所有链表元素，在数组中进行分区
     *
     * @param head 链表头节点
     * @param x    分区值
     * @return 分好区的链表
     */
    public static ListNode partition2(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        // 求数组长度
        int length = 0;
        ListNode helper = head;
        while (helper != null) {
            length++;
            helper = helper.next;
        }

        // 将链表元素放进数组
        ListNode[] arr = new ListNode[length];
        helper = head;
        length = 0;
        while (helper != null) {
            arr[length++] = helper;
            helper = helper.next;
        }

        // 分区
        partition2(arr, x);

        // 数组转成链表
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                // 最后一个next置为null
                arr[i].next = null;
            } else {
                arr[i].next = arr[i + 1];
            }
        }

        return arr[0];

    }

    /**
     * 将数组分区
     *
     * @param arr 数组
     * @param x   分区值
     */
    public static void partition2(ListNode[] arr, int x) {

        int start = 0;
        int end = arr.length - 1;
        int cur = 0;

        while (cur <= end) {
            if (arr[cur].val < x) {
                ArrayUtils.swap(arr, cur++, start++);
            } else if (arr[cur].val == x) {
                cur++;
            } else {
                ArrayUtils.swap(arr, cur, end--);
            }
        }
    }

    /**
     * 使用六个辅助指针来控制，ltHead ltTall eqHead eqTail gtHead gtTail
     * <p>
     * 最终将三组头尾相连
     *
     * @param head 头节点
     * @param x    基准数
     * @return ListNode
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode ltHead = null;
        ListNode ltTall = null;
        ListNode eqHead = null;
        ListNode eqTail = null;
        ListNode gtHead = null;
        ListNode gtTail = null;

        ListNode next;
        while (head != null) {

            next = head.next;
            head.next = null;

            if (x > head.val) {
                if (ltHead == null) {
                    ltHead = head;
                    ltTall = head;
                } else {
                    ltTall.next = head;
                    ltTall = head;
                }
            } else if (x < head.val) {
                if (gtHead == null) {
                    gtHead = head;
                    gtTail = head;
                } else {
                    gtTail.next = head;
                    gtTail = head;
                }
            } else {
                if (eqHead == null) {
                    eqHead = head;
                    eqTail = head;
                } else {
                    eqTail.next = head;
                    eqTail = head;
                }
            }

            head = next;
        }

        // 组合链表,小于区域和等于相连
        if (ltTall != null) {
            ltTall.next = eqHead;
            eqTail = eqTail == null ? ltTall : eqTail;
        }

        // 等于与大于区域相连
        if (eqTail != null) {
            eqTail.next = gtHead;
        }

        // 返回头部
        return ltHead == null ? eqHead == null ? gtHead : eqHead : ltHead;
    }
}
