package com.weiyi.leetcode.string;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * desc: 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/2/23
 */
public class ReverseOnlyLetters {

    public static void main(String[] args) {
        ReverseOnlyLetters reverseOnlyLetters = new ReverseOnlyLetters();
        System.out.println(reverseOnlyLetters.reverseOnlyLetters("ab_adadcde_qwe"));
    }

    public String reverseOnlyLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLetters(c)) {
                stack.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLetters(c)) {
                sb.append(stack.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private boolean isLetters(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}
