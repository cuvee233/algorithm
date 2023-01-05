package com.weiyi.alorithm.linked;

import com.weiyi.alorithm.linked.common.Node;
import com.weiyi.alorithm.linked.common.NodeUtils;

/**
 * 反转链表
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class RevertLinkedList {


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println("===========反转前==========");
        NodeUtils.printNode(node1);
        System.out.println("===========反转后==========");
        NodeUtils.printNode(revert(node1));
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

}
