package com.weiyi.alorithm.linked;

import com.weiyi.leetcode.LinkedList.ListNode;

/**
 * 两条可能有环的链表相交节点检测，有相交节点则返回任一相交节点，无则返回null
 * 1.先区分两条链表是否有环，有则返回第一个入环节点
 * 2.有三种情况：
 * -----------1）两条链表无环
 * ---------------1.0 历出两条链表长度m、n abs（m-n）求出长度差值，长一点的链表先走差值步，然后两个链表一起遍历判断，是否有相同节点，有则相交，无则不相交
 * ---------------1.1 A+B B+A 判断是否有节点相同，有则相交，无则不相交
 * -----------2）两条链表都有环
 * ---------------2.0 LoopA == LoopB 则相交,
 * ---------------2.1 LoopA ！= LoopB，LoopA继续往后遍历.直到回到LoopA之前都没有遇到LoopB则不相交
 * -----------3）其中一条有环，一条无环
 * --------------没有相交
 *
 * @author weiyi
 * @date 2023-01-04
 */
public class CycleIntersectionNode {

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

    }

    public static ListNode getIntersectNode(ListNode nodeA, ListNode nodeB) {
        if (nodeA == null || nodeB == null) {
            return null;
        }

        // 求A和B链表的第一个入环节点
        ListNode loopA = getDetectCycle(nodeA);
        ListNode loopB = getDetectCycle(nodeB);

        if (loopA == null && loopB == null) {
            // 无环求相交节点
            return getIntersectionNoLoop(nodeA, nodeB);
        }

        if (loopA != null && loopB != null) {
            // 有环求相交节点
            return getIntersectionInLoop(nodeA, loopA, nodeB, loopB);

        }

        // 其它情况为不相交
        return null;
    }

    private static ListNode getIntersectionInLoop(ListNode nodeA, ListNode loopA, ListNode nodeB, ListNode loopB) {
        if (loopA == loopB) {
            // 有相交节点
            ListNode helpA = nodeA;
            ListNode helpB = nodeB;

            // 求A到loopA和B到loopB的长度差值
            int length = 0;

            while (helpA != loopA) {
                length++;
                helpA = helpA.next;
            }

            while (helpB != loopB) {
                length--;
                helpB = helpB.next;
            }

            // 长的链表先走length步
            helpA = length > 0 ? nodeA : nodeB;
            helpB = helpA == nodeA ? nodeB : nodeA;

            // 绝对值
            length = Math.abs(length);
            while (length-- > 0) {
                helpA = helpA.next;
            }

            // 两个链表一起同步，找到第一个相交节点返回
            while (helpA != helpB) {
                helpA = helpA.next;
                helpB = helpB.next;
            }
            return helpA;

        }
        ListNode help = loopA.next;

        while (loopA != help) {
            if (help == loopB) {
                return help;
            }
            help = help.next;
        }
        return null;
    }

    /**
     * getIntersectionNoLoop2 优化版本
     */
    private static ListNode getIntersectionNoLoop(ListNode nodeA, ListNode nodeB) {
        // 找到A和B长度的差值

        int length = 0;
        ListNode helpA = nodeA;
        ListNode helpB = nodeB;
        while (helpA.next != null) {
            length++;
            helpA = helpA.next;
        }

        while (helpB.next != null) {
            length--;
            helpB = helpB.next;
        }

        if (helpA != helpB) {
            // 最后一个节点不相等代表不相交
            return null;
        }

        // 长的链表先走length步
        helpA = length > 0 ? nodeA : nodeB;
        helpB = helpA == nodeA ? nodeB : nodeA;

        // 绝对值
        length = Math.abs(length);

        while (length-- > 0) {
            helpA = helpA.next;
        }

        // 两个链表一起同步，找到第一个相交节点返回
        while (helpA != helpB) {
            helpA = helpA.next;
            helpB = helpB.next;
        }
        return helpA;
    }

    private static ListNode getIntersectionNoLoop2(ListNode nodeA, ListNode nodeB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode helpA = nodeA;
        ListNode helpB = nodeB;
        while (helpA != null || helpB != null) {

            if (helpA != null) {
                lengthA++;
                helpA = helpA.next;
            }

            if (helpB != null) {
                lengthB++;
                helpB = helpB.next;
            }
        }

        int forwardLength = Math.abs(lengthA - lengthB);

        helpA = nodeA;
        helpB = nodeB;
        while (forwardLength-- > 0) {
            if (lengthA > lengthB) {
                helpA = helpA.next;
            } else {
                helpB = helpB.next;
            }
        }

        while (helpA != null && helpB != null) {
            if (helpA == helpB) {
                return helpA;
            }
            helpA = helpA.next;
            helpB = helpB.next;
        }

        return null;
    }

    /**
     * 获取链表的环
     *
     * @param head 链表
     * @return 有环则返回入环位置，
     */
    public static ListNode getDetectCycle(ListNode head) {
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
