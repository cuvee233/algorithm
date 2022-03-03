package com.weiyi.leetcode.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * desc: 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/15
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);

        System.out.println(lruCache.get(3));
    }

    /**
     * 容量
     */
    private final int capacity;

    /**
     * 当前元素数量
     */
    private int size;

    /**
     * 头节点，存放最近使用节点
     */
    private Node<Integer> head;

    /**
     * 尾节点，最少使用节点
     */
    private Node<Integer> tail;

    /**
     * 缓存hash表
     */
    private final Map<Integer, Node<Integer>> cache;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node<Integer> curNode = cache.get(key);
        if (curNode == null) {
            return -1;
        }

        // 更新使用频率
        updateLru(curNode);

        return curNode.val;
    }

    /**
     * 更新LRU缓存
     */
    private void updateLru(Node<Integer> curNode) {
        if (size == 1) {
            // 只有一个节点时不用更新
            return;
        }
        // 当前节点变成头节点
        moveToHead(curNode);
    }

    /**
     * 设置头节点
     */
    private void putAndSetHead(int key, int value) {
        // map
        Node<Integer> curNode = new Node<>(key, value);

        if (size >= capacity) {
            // remove Tail
            cache.remove(tail);
            removeNode(tail);
            size--;
        }

        // 设置头节点
        setHead(curNode);
        // 插入新节点
        cache.put(curNode.getKey(), curNode);
        size++;
    }

    private void moveToHead(Node<Integer> curNode) {
        removeNode(curNode);
        setHead(curNode);
    }

    private void removeNode(Node<Integer> curNode) {
        if (curNode.equals(tail)) {
            tail = tail.pre;
        } else {
            curNode.pre.next = curNode.next;
            curNode.next.pre = curNode.pre;

        }
    }


    private void setHead(Node<Integer> curNode) {
        // 当前节点设置为头节点
        curNode.pre = null;
        if (head != null) {
            curNode.next = head;
            head.pre = curNode;
        } else {
            tail = curNode;
        }
        head = curNode;
    }

    public void put(int key, int value) {
        Node<Integer> oldNode = cache.get(key);
        if (oldNode != null) {
            // 当前key存在，直接覆盖
            oldNode.setVal(value);
            // 更新lru缓存
            updateLru(oldNode);
        } else {
            putAndSetHead(key, value);
        }
    }

    static class Node<T> {
        private Node<T> pre;
        private Node<T> next;
        private T key;
        private T val;

        public Node() {
        }

        public Node(T key, T val) {
            this.val = val;
            this.key = key;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public Node<T> getPre() {
            return pre;
        }

        public void setPre(Node<T> pre) {
            this.pre = pre;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "capacity=" + capacity +
                ", size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                ", cache=" + cache +
                '}';
    }
}
