package com.weiyi.leetcode.arr.test;


import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;

public class ListPerformance {

    private static final int addSize = 10000000;


    private static final int deleteSize = 1000;

    private static List<Integer> arrayList = new ArrayList<>();
    private static List<Integer> lindedList = new LinkedList<>();

    public static Collection fill(Collection c, int size) {
        for (int i = 0; i < size; i++) {
            c.add(Integer.toString(i));
        }
        return c;
    }

    private static void testAdd() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < addSize; i++) {
            arrayList.add(i);
        }
        System.out.println("ArrayList add cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();
        for (int i = 0; i < addSize; i++) {
            lindedList.add(i);
        }
        System.out.println("LinkedList add cost: " + (System.currentTimeMillis() - linedStart));
    }

    private static void testHeadDelete() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < deleteSize; i++) {
            arrayList.remove(0);
        }
        System.out.println("ArrayList head delete cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();

        for (int i = 0; i < deleteSize; i++) {
            lindedList.remove(0);
        }
        System.out.println("LinkedList head deleted cost: " + (System.currentTimeMillis() - linedStart));
    }

    private static void testRandomDelete() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < deleteSize; i++) {
            int index = (int) (Math.random() * arrayList.size());
            arrayList.remove(index);
        }
        System.out.println("ArrayList random delete cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();

        for (int i = 0; i < deleteSize; i++) {
            int index = (int) (Math.random() * arrayList.size());
            lindedList.remove(index);
        }
        System.out.println("LinkedList random deleted cost: " + (System.currentTimeMillis() - linedStart));
    }

    private static void testTailDelete() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < deleteSize; i++) {
            arrayList.remove(arrayList.size() - 1);
        }
        System.out.println("ArrayList tail delete cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();

        for (int i = 0; i < deleteSize; i++) {
            lindedList.remove(lindedList.size() - 1);
        }
        System.out.println("LinkedList tail deleted cost: " + (System.currentTimeMillis() - linedStart));
    }

    private static void testMidDelete() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < deleteSize; i++) {
            arrayList.remove((arrayList.size() - 1) / 2);
        }
        System.out.println("ArrayList mid delete cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();

        for (int i = 0; i < deleteSize; i++) {
            lindedList.remove((lindedList.size() - 1) / 2);
        }
        System.out.println("LinkedList mid deleted cost: " + (System.currentTimeMillis() - linedStart));
    }


    private static void testFor() {
        long start = System.currentTimeMillis();
        int aCount = 0;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            aCount = arrayList.get(i) + 1;
        }
        System.out.println("ArrayList fori cost: " + (System.currentTimeMillis() - start));

        int lCount = 0;
        long linedStart = System.currentTimeMillis();
//        for (int i = 0; i < lindedList.size(); i++) {
//            lCount = lindedList.get(i) + 1;
//        }
        System.out.println("LinkedList fori cost: " + (System.currentTimeMillis() - linedStart));
    }

    private static void testForEach() {
        long start = System.currentTimeMillis();
        int aCount = 0;
        for (Integer integer : arrayList) {
            aCount = integer + 1;
        }
        System.out.println("ArrayList forEach cost: " + (System.currentTimeMillis() - start));

        int lCount = 0;
        long linedStart = System.currentTimeMillis();
        for (Integer integer : lindedList) {
            lCount = integer + 1;
        }
        System.out.println("LinkedList forEach cost: " + (System.currentTimeMillis() - linedStart));
    }


    private static void testForEach2() {
        long start = System.currentTimeMillis();
        int aCount = 0;
        arrayList.forEach(item -> {
            int count = item + 1;
        });
        System.out.println("ArrayList forEach2 cost: " + (System.currentTimeMillis() - start));

        long linedStart = System.currentTimeMillis();
        int lCount = 0;
        lindedList.forEach(item -> {
            int count = item + 1;

        });
        System.out.println("LinkedList forEach2 cost: " + (System.currentTimeMillis() - linedStart));
    }


    private static void testIterator() {
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = arrayList.iterator();
        int acount = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            acount = next + 1;
        }

        System.out.println("ArrayList iterator cost: " + (System.currentTimeMillis() - start));

        int lcount = 0;
        long linedStart = System.currentTimeMillis();
        Iterator<Integer> iterator1 = lindedList.iterator();
        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            lcount = next + 1;
        }
        System.out.println("LinkedList iterator cost: " + (System.currentTimeMillis() - linedStart));
    }


    public static void main(String[] args) {
        testAdd();
        Exchanger<Object> objectExchanger = new Exchanger<>();
        testHeadDelete();
    }


}