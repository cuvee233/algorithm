package com.weiyi.leetcode.LinkedList.reverse;

import com.weiyi.leetcode.LinkedList.ListNode;

import java.util.Stack;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 思路：利用栈来解
 *
 * @author weiyi
 * @date 2022/1/22
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        Stack<ListNode> stack = new Stack<>();
        ListNode help = head;
        ListNode cur = null;
        head = null;

        while (help != null) {
            // 压栈
            for (int i = 0; i < k; i++) {
                stack.push(help);
                help = help.next;
            }

            // 反转
            cur = stack.peek();
            while (stack.size() >= 2) {
                ListNode pop = stack.pop();
                pop.next = stack.peek();
            }

            // 处理头和尾
            if (head != null) {
                head.next = cur;
                head = stack.peek();
            }
            if (help != null) {
                stack.peek().next = help;
            }


        }

        return null;
    }

}
