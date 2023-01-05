package com.weiyi.alorithm.linked;

import com.weiyi.alorithm.linked.common.Node;
import com.weiyi.alorithm.linked.common.NodeUtils;

/**
 * 删除指定元素
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class RemoveElement {


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);
        Node node4 = new Node(1);
        Node node5 = new Node(1);
        Node node6 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println("===========删除前==========");
        NodeUtils.printNode(node1);
        System.out.println("===========删除后==========");
        NodeUtils.printNode(remove(node1, 1));
    }

    public static Node remove(Node head, int removeEle) {
        if (head == null) {
            return null;
        }

        // 删除头部所有的相同元素
        while (head != null && head.val == removeEle) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }

        // 删除中间元素
        Node ans = head;
        Node pre = null;
        while (head != null) {
            if (head.val == removeEle) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }

        return ans;
    }

}
