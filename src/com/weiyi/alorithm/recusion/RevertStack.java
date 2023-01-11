package com.weiyi.alorithm.recusion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 不借助额外的空间，将stack倒叙
 *
 * @author weiyi
 * @date 2023-01-11
 */
public class RevertStack {

    public static void main(String[] args) {
        RevertStack revertStack = new RevertStack();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);

        System.out.println(revertStack.revertStackAndPop(stack));

        while(!stack.isEmpty()){
            System.out.println(stack.pollLast());
        }

    }

    public int revertStackAndPop(Deque<Integer> stack) {
        Integer poll = stack.pollLast();
        if (stack.isEmpty()) {
            return poll;
        } else {
            int res = revertStackAndPop(stack);
            stack.addFirst(poll);
            return res;
        }
    }

}
