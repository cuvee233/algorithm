package com.weiyi.alorithm.linked;

import com.weiyi.leetcode.LinkedList.ListNode;

/**
 * 求两个无环链表相交的第一个节点，有则返回
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class IntersectionNode {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);


        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node5.next = node6;
        node6.next = node2;

        System.out.println(getIntersectionNode(node1, node5).val);
    }


    /**
     * 解题思路：
     * 容器解题:用hash表存储headA的所有元素，遍历headB的同时判断是否有相同元素
     * O（1）额外空间解题：两个链表拼在一起遍历，A+B B+A，判断有无相等元素
     *
     * @param headA 链表A
     * @param headB 链表A
     * @return 相交节点，无则为null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode helpA = headA;
        ListNode helpB = headB;

        while (helpA != helpB) {
            helpA = helpA == null ? headB : helpA.next;
            helpB = helpB == null ? headA : helpB.next;
        }


        return helpA;

    }

}
