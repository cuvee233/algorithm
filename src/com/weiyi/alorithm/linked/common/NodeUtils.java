package com.weiyi.alorithm.linked.common;

import com.weiyi.leetcode.LinkedList.ListNode;

/**
 * @author weiyi
 * @date 2022-12-29
 */
public class NodeUtils {

    public static void printNode(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    public static void printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    public static Node revert(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null;
        Node next = null;

        while (head != null) {
            // 记录下一个指针
            next = head.next;
            // 前一个节点变成后一个节点
            head.next = pre;
            // 当前节点变为前一个节点
            pre = head;
            // 跳到下一个节点
            head = next;
        }

        return pre;
    }


    public static ListNode revert(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            // 记录下一个指针
            next = head.next;
            // 前一个节点变成后一个节点
            head.next = pre;
            // 当前节点变为前一个节点
            pre = head;
            // 跳到下一个节点
            head = next;
        }

        return pre;
    }
}
