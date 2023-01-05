package com.weiyi.alorithm.linked;

import com.weiyi.alorithm.linked.common.Node;
import com.weiyi.alorithm.linked.common.NodeUtils;

/**
 * 快慢指针
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class DoublePinter {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        NodeUtils.printNode(node1);


        Node midOrLastMid = getMidOrLastMid(node1);
        System.out.println(midOrLastMid);

    }

    /**
     * 奇数个返回链表中点，偶数个返回中点前一个
     *
     * @param head 头节点
     * @return 中点
     */
    public static Node getMidOrLastMid(Node head) {
        Node fast = head.next;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 奇数个返回链表中点，偶数个返回中点后一个
     *
     * @param head 头节点
     * @return 中点
     */
    public static Node getMidOrNextMid(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 返回中点前一个节点
     *
     * @param head 头节点
     * @return 中点前一个节点
     */
    public static Node getLastMid(Node head) {
        Node fast = head.next;
        Node slow = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 返回中点后一个节点
     *
     * @param head 头节点
     * @return 中点后一个节点
     */
    public static Node getNextMid(Node head) {
        Node fast = head;
        Node slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
