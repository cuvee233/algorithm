package com.weiyi.leetcode.LinkedList;

/**
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * <p>
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-in-between-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author weiyi
 * @date 2023-01-30
 */
public class MergeInBetween {

    public static void main(String[] args) {
        System.out.println(getTest());
    }

    public static int getTest(){
        try {
            throw new NullPointerException();
        }catch (Exception e){
            return 1;
        }finally {
            System.out.println("final");
        }
    }


    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        // 找到list2的尾节点
        ListNode tail2 = list2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }

        ListNode cur = list1;
        ListNode next = cur.next;

        while (b >= 0) {
            if (a == 1) {
                // 与list2head合并
                cur.next = list2;
            }

            if (b == 0) {
                // 与list2tail合并
                cur.next = null;
                tail2.next = next;
            }

            cur = next;
            next = cur.next;
            a--;
            b--;
        }

        return list1;
    }

}
