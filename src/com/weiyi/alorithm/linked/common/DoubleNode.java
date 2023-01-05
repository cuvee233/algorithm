package com.weiyi.alorithm.linked.common;

/**
 * 双向链表
 *
 * @author weiyi
 * @date 2022-12-29
 */
public class DoubleNode {

    public DoubleNode pre;

    public DoubleNode next;

    public int val;

    public DoubleNode(int val) {
        this.val = val;
    }
}
