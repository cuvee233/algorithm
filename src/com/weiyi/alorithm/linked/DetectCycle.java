package com.weiyi.alorithm.linked;

import com.weiyi.leetcode.LinkedList.ListNode;

/**
 * 链表环检测
 * <p>
 * 通过快慢指针遍历，慢指针走一步，快指针走两步，如果走到next为null则代表链表无环，
 * 如果两个指针相遇时，快指针从头开始步长为一同步与慢指针一起走，当两个节点再次相遇时，则找到了入环节点
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class DetectCycle {

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
        node6.next = node3;

        DetectCycle detectCycle = new DetectCycle();
        ListNode listNode = detectCycle.detectCycle(node1);
        System.out.println(listNode == null ? null : listNode.val);
    }

    /**
     * 通过快慢指针遍历，慢指针走一步，快指针走两步，如果走到next为null则代表链表无环，
     * 如果两个指针相遇时，快指针从头开始步长为一同步与慢指针一起走，当两个节点再次相遇时，则找到了入环节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        do {
            // next等于null代表没有环
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);

        // 有环，
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }

}
