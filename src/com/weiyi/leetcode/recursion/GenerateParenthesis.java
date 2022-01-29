package com.weiyi.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * desc: 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author yuanwei
 * @version 1.0
 * @date 2022/1/29
 */
public class GenerateParenthesis {
    private int count;

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
//        generateParenthesis.generateParenthesis(8); // 131071
//        System.out.println(generateParenthesis.count);

        generateParenthesis.generateParenthesis2(8).forEach(System.out::println);
//        System.out.println(generateParenthesis.count);

    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis(new char[n * 2], 0, ans);
        return ans;
    }

    /**
     * @param chars       存放生成括号的数组，数组的长度为n*2，也就是n对括号的长度
     * @param cur         当前生成括号的数量
     * @param parenthesis 有效的括号组合
     */
    public void generateParenthesis(char[] chars, int cur, List<String> parenthesis) {
        count++;
        if (cur == chars.length) {
            if (validated(chars)) {
                parenthesis.add(String.valueOf(chars));
            }
        } else {
            // 先生成左括号
            chars[cur] = '(';
            generateParenthesis(chars, cur + 1, parenthesis);

            // 再生成右括号
            chars[cur] = ')';
            generateParenthesis(chars, cur + 1, parenthesis);
        }

    }

    private boolean validated(char[] chars) {
        int count = 0;
        for (char aChar : chars) {
            if (aChar == '(') {
                count++;
            }
            if (aChar == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<>();
        generateParenthesis2(new char[n * 2], 0, n, n, ans);
        return ans;
    }

    public void generateParenthesis2(char[] chars, int cur, int left, int right, List<String> parenthesis) {
        if (left < 0 || right < 0) {
            return;
        }

        if (chars.length == cur) {
            if (validated(chars)) {
                parenthesis.add(String.valueOf(chars));
            }
        } else {
            // 先生成左括号
            chars[cur] = '(';
            generateParenthesis2(chars, cur + 1, left - 1, right, parenthesis);

            // 再生成右括号
            chars[cur] = ')';
            generateParenthesis2(chars, cur + 1, left, right - 1, parenthesis);
        }

    }
}
