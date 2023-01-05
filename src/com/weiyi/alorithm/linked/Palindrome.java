package com.weiyi.alorithm.linked;

import com.weiyi.alorithm.linked.common.NodeUtils;
import com.weiyi.leetcode.LinkedList.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 回文链表
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class Palindrome {

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
        node4.next = node5;
        node5.next = node6;

        Palindrome palindrome = new Palindrome();

        System.out.println(palindrome.isPalindrome(node1));

        NodeUtils.printListNode(node1);

    }

    /**
     * O(1)空间复杂度实现
     * 1.先找到链表中点
     * 2.从中点开始往后反转链表
     * 3.双指针遍历，头尾相互对比，不相等则返回false，直到尾节点为null时停止
     *
     * @param head 头节点
     * @return boolean
     */
    public boolean isPalindrome(ListNode head) {

        // 找到中点slow
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 反转中点后面的链表
        ListNode endNode = NodeUtils.revert(slow.next);
        ListNode revertNode = endNode;

        // 对比两个链表，是否节点为回文
        ListNode forwardNode = head;

        while (forwardNode != null && revertNode != null) {
            if (forwardNode.val != revertNode.val) {
                return false;
            }
            forwardNode = forwardNode.next;
            revertNode = revertNode.next;
        }

        // 反转后的链表转为正序列
        NodeUtils.revert(endNode);

        return true;
    }


    /**
     * 判断链表是否回文，借助栈实现(空间复杂度O（n）)
     *
     * @param head 头节点
     * @return boolean
     */
    public boolean isPalindrome2(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode helper = head;
        while (helper != null) {
            stack.addFirst(helper);
            helper = helper.next;
        }

        helper = head;
        while (!stack.isEmpty()) {
            if (stack.pollFirst().val != helper.val) {
                return false;
            }
            helper = helper.next;
        }

        return true;
    }

}
