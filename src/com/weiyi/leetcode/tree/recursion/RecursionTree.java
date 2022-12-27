package com.weiyi.leetcode.tree.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author weiyi
 * @date 2022/5/4
 */
public class RecursionTree {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(2);

        new Thread(() -> {
            System.out.println("准备开始");
            semaphore.release();
            semaphore.release();
            semaphore.release();
            System.out.println("解锁完成·");
        }).run();


        System.out.println("");
        semaphore.acquire();
    }



}
