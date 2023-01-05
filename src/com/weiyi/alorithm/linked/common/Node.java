package com.weiyi.alorithm.linked.common;

/**
 * 单向链表
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class Node {

    public int val;

    public Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
